package com.megapapa.sk.auth.service;

import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import io.bootique.rabbitmq.client.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionServiceFactory {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceFactory.class);

    private static final String HTTP_PERMISSION_SERVICE = "http";
    private static final String RABBITMQ_PERMISSION_SERVICE = "rabbitmq";

    private String resourcesPackage;
    private boolean debug;
    private String type;

    public IPermissionService createPermissionService(DefaultServletEnvironment environment,
                                                      ISystemUserService systemUserService,
                                                      IUserCacheService userCacheService,
                                                      ConnectionFactory connectionFactory) {
        if (!debug) {
            switch (type) {
                case HTTP_PERMISSION_SERVICE:
                    logger.info("Permission service starting work in HTTP mode");
                    return new SkAuthHttpPermissionService(environment, systemUserService, userCacheService);
                case RABBITMQ_PERMISSION_SERVICE:
                    logger.info("Permission service starting work in RABBITMQ mode.");
                    return new SkAuthPermissionService(environment, systemUserService, userCacheService, connectionFactory);
                default:
                    throw new IllegalStateException("Invalid permission service type!");
            }
        } else {
            logger.info("Permission service starting work in DEBUG mode.");
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
