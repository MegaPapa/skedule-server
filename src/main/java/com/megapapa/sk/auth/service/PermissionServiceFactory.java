package com.megapapa.sk.auth.service;

import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;

public class PermissionServiceFactory {

    private String resourcesPackage;
    private boolean debug;

    public IPermissionService createPermissionService(DefaultServletEnvironment environment,
                                                      ISystemUserService systemUserService,
                                                      IUserCacheService userCacheService,
                                                      ConnectionFactory connectionFactory) {
        if (!debug) {
            return new SkAuthPermissionService(environment, systemUserService, userCacheService, connectionFactory);
        } else {
            return new DebugPermissionService();
        }
    }

    public String getResourcesPackage() {
        return resourcesPackage;
    }

    public void setResourcesPackage(String resourcesPackage) {
        this.resourcesPackage = resourcesPackage;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }
}
