package com.megapapa.sk.auth.annotation;

import com.megapapa.sk.auth.AuthModule;
import com.megapapa.sk.auth.exception.InvalidAuthTokenException;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cache.IUserCacheService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.cayenne.configuration.server.ServerRuntime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SecureApiInterceptor implements MethodInterceptor {

    private static final String SK_AUTH_CONNECTION_NAME = "sk-auth";

    private ConnectionFactory connectionFactory;
    private ISystemUserService systemUserService;
    private ServerRuntime serverRuntime;
    private IUserCacheService userCacheService;
    private DefaultServletEnvironment environment;

    public SecureApiInterceptor(ConnectionFactory connectionFactory,
                                ISystemUserService systemUserService,
                                ServerRuntime serverRuntime,
                                IUserCacheService userCacheService,
                                DefaultServletEnvironment environment) {
        this.connectionFactory = connectionFactory;
        this.systemUserService = systemUserService;
        this.serverRuntime = serverRuntime;
        this.userCacheService = userCacheService;
        this.environment = environment;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (environment.request().isPresent()) {
            HttpServletRequest httpServletRequest = environment.request().get();
            Cookie[] cookies = httpServletRequest.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthModule.AUTH_TOKEN_NAME)) {

                }
            }

            Connection connection = connectionFactory.forName(SK_AUTH_CONNECTION_NAME);
            Channel channel = connection.createChannel();
            // send token to auth server
            channel.basicPublish();

            channel.basicGet();
            return null;
        } else {
            throw new InvalidAuthTokenException("HttpServlet request doesn't present!");
        }
    }

    private void processAuthCookie(Cookie cookie) {

    }
}
