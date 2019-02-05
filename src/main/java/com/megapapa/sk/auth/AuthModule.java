package com.megapapa.sk.auth;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.annotation.SecureApiInterceptor;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.auth.service.PermissionServiceFactory;
import com.megapapa.sk.auth.service.SystemUserService;
import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class AuthModule extends ConfigModule {

    private static final String AUTH_MODULE_PREFIX = "auth";
    public static final String AUTH_TOKEN_NAME = "sk-auth-token";

    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        SecureApiInterceptor secureApiInterceptor = new SecureApiInterceptor(
                binder.getProvider(ConnectionFactory.class).get(),
                binder.getProvider(ISystemUserService.class).get(),
                binder.getProvider(ServerRuntime.class).get(),
                binder.getProvider(IUserCacheService.class).get(),
                binder.getProvider(DefaultServletEnvironment.class).get()
        );
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(SecureApi.class), secureApiInterceptor);
    }

    @Provides
    @Singleton
    public IPermissionService providePermissionService(ConfigurationFactory configFactory) {
        return configFactory
                .config(PermissionServiceFactory.class, AUTH_MODULE_PREFIX)
                .createPermissionService();
    }

    @Provides
    @Singleton
    public ISystemUserService provideSystemUserService() {
        return new SystemUserService();
    }
}
