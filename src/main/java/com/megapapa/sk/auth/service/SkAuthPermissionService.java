package com.megapapa.sk.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.megapapa.sk.auth.AuthModule;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.entity.AuthRequestMessage;
import com.megapapa.sk.auth.entity.AuthResponseMessage;
import com.megapapa.sk.auth.entity.SystemUser;
import com.megapapa.sk.auth.exception.InvalidAuthTokenException;
import com.megapapa.sk.auth.rabbitmq.AuthMessageConsumer;
import com.megapapa.sk.cache.IUserCacheService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.RpcClient;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.apache.cayenne.configuration.server.ServerRuntime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

public class SkAuthPermissionService implements IPermissionService {

    private static final String SK_AUTH_CONNECTION_NAME = "sk-auth";
    private static final String AUTH_EXCHANGE_NAME = "sk-auth-exchange";
    public static final String AUTH_QUEUE_NAME = "sk-auth";

    private ConnectionFactory connectionFactory;
    private ISystemUserService systemUserService;
    private IUserCacheService userCacheService;
    private DefaultServletEnvironment environment;

    private Map<UUID, AuthResponseMessage> brokerResponses;

    public SkAuthPermissionService(DefaultServletEnvironment environment,
                                   ISystemUserService systemUserService,
                                   IUserCacheService userCacheService,
                                   ConnectionFactory connectionFactory) {

        this.environment = environment;
        this.systemUserService = systemUserService;
        this.userCacheService = userCacheService;
        this.connectionFactory = connectionFactory;
        this.brokerResponses = new ConcurrentHashMap<>();
    }

    @Override
    public boolean hasAccess(String permissionPath) {
        if (environment.request().isPresent()) {
            HttpServletRequest httpServletRequest = environment.request().get();
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies == null) {
                return false;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthModule.AUTH_TOKEN_NAME)) {
                    try {
                        processAuthCookie(cookie, permissionPath);
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } else {
            return false;
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

//        channel.basicPublish("", AUTH_QUEUE_NAME, null, requestMessageJson.getBytes());
//        channel.basicConsume(AUTH_QUEUE_NAME, new AuthMessageConsumer(channel, brokerResponses));
        RpcClient client = new RpcClient(channel, AUTH_EXCHANGE_NAME, "");
        String response = client.stringCall(requestMessageJson);
        return null;
//
//        AMQP.BasicProperties props = new AMQP.BasicProperties
//                .Builder()
//                .replyTo(AUTH_QUEUE_NAME)
//                .build();
        // send token to auth server

//
//        channel.getDefaultConsumer();
    }


    /*

     */
}
