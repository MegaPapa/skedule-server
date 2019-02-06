package com.megapapa.sk.auth.service;

import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;

public class PermissionServiceFactory {

    private String resourcesPackage;

    public IPermissionService createPermissionService(DefaultServletEnvironment environment,
                                                      ISystemUserService systemUserService,
                                                      IUserCacheService userCacheService,
                                                      ConnectionFactory connectionFactory) {
        return new SkAuthPermissionService(environment, systemUserService, userCacheService, connectionFactory);
    }

    public String getResourcesPackage() {
        return resourcesPackage;
    }

    public void setResourcesPackage(String resourcesPackage) {
        this.resourcesPackage = resourcesPackage;
    }
}
