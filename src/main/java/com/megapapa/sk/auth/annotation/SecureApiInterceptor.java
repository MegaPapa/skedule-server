package com.megapapa.sk.auth.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.megapapa.sk.auth.AuthModule;
import com.megapapa.sk.auth.entity.AuthRequestMessage;
import com.megapapa.sk.auth.entity.SystemUser;
import com.megapapa.sk.auth.exception.InvalidAuthTokenException;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cache.IUserCacheService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.RpcClient;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.cayenne.configuration.server.ServerRuntime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class SecureApiInterceptor implements MethodInterceptor {

    private static final String SK_AUTH_CONNECTION_NAME = "sk-auth";
    public static final String AUTH_QUEUE_NAME = "auth";

    @Inject
    private ConnectionFactory connectionFactory;
    @Inject
    private ISystemUserService systemUserService;
    @Inject
    private ServerRuntime serverRuntime;
    @Inject
    private IUserCacheService userCacheService;
    @Inject
    private DefaultServletEnvironment environment;

    public SecureApiInterceptor() {}

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (environment.request().isPresent()) {
            HttpServletRequest httpServletRequest = environment.request().get();
            Cookie[] cookies = httpServletRequest.getCookies();
            boolean hasAuthCookie = false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthModule.AUTH_TOKEN_NAME)) {
                    String permissionPath = methodInvocation.getMethod().getAnnotation(SecureApi.class).permission();
                    processAuthCookie(cookie, permissionPath);
                    hasAuthCookie = true;
                    break;
                }
            }
            if (!hasAuthCookie) {
                throw new InvalidAuthTokenException("Auth cookie doesn't present in request!");
            }
            return null;
        } else {
            throw new InvalidAuthTokenException("HttpServlet request doesn't present!");
        }
    }

    private void processAuthCookie(Cookie cookie, String permissionPath) throws IOException, TimeoutException {
        String token = cookie.getValue();
        SystemUser user = userCacheService.getUser(token);
        if (user == null) {
            user = getSystemUserFromAuthServer(token, permissionPath);
        }

        if (user != null) {
            systemUserService.setSystemUser(user);
        } else {
            throw new InvalidAuthTokenException("Your auth token isn't valid.");
        }
    }

    private SystemUser getSystemUserFromAuthServer(String token, String permissionPath) throws IOException, TimeoutException {
        Connection connection = connectionFactory.forName(SK_AUTH_CONNECTION_NAME);
        Channel channel = connection.createChannel();
        // unique key, that will be used to identify who send request to the auth server
        UUID uuid = UUID.randomUUID();
        AuthRequestMessage requestMessage = new AuthRequestMessage();
        requestMessage.setMessageSource(uuid.toString());
        requestMessage.setToken(token);
        requestMessage.setPath(permissionPath);
        ObjectMapper mapper = new ObjectMapper();
        String requestMessageJson = mapper.writeValueAsString(requestMessage);

        RpcClient client = new RpcClient(channel, "", "");
        String response = client.stringCall(requestMessageJson);
        return null;
//
//        AMQP.BasicProperties props = new AMQP.BasicProperties
//                .Builder()
//                .replyTo(AUTH_QUEUE_NAME)
//                .build();
//        // send token to auth server
//        channel.basicPublish("", AUTH_QUEUE_NAME, props, requestMessageJson.getBytes());
//
//        channel.getDefaultConsumer();
    }
}
