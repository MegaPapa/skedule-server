package com.megapapa.sk.auth.service;

public class PermissionServiceFactory {

    private String resourcesPackage;

    public IPermissionService createPermissionService() {
        return new SkAuthPermissionService();
    }

    public String getResourcesPackage() {
        return resourcesPackage;
    }

    public void setResourcesPackage(String resourcesPackage) {
        this.resourcesPackage = resourcesPackage;
    }
}
