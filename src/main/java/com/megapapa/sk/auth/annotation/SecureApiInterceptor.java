package com.megapapa.sk.auth.annotation;

import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cache.IUserCacheService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class SecureApiInterceptor implements MethodInterceptor {

    private static final String SK_AUTH_CONNECTION_NAME = "sk-auth";

    private ConnectionFactory connectionFactory;
    private ISystemUserService systemUserService;
    private ServerRuntime serverRuntime;
    private IUserCacheService userCacheService;

    public SecureApiInterceptor(ConnectionFactory connectionFactory,
                                ISystemUserService systemUserService,
                                ServerRuntime serverRuntime,
                                IUserCacheService userCacheService) {
        this.connectionFactory = connectionFactory;
        this.systemUserService = systemUserService;
        this.serverRuntime = serverRuntime;
        this.userCacheService = userCacheService;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Connection connection = connectionFactory.forName(SK_AUTH_CONNECTION_NAME);
        Channel channel = connection.createChannel();
        // send token to auth server
        channel.basicPublish();

        channel.basicGet();
        // wait response and safe user data
        return null;
    }
}
