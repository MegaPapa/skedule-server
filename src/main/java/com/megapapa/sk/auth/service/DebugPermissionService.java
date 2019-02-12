package com.megapapa.sk.auth.service;

/**
 * Permission service for development.
 * Always returns true on permission checking.
 */
public class DebugPermissionService implements IPermissionService {
    @Override
    public boolean hasAccess(String path) {
        return true;
    }
}
