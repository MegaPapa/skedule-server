package com.megapapa.sk.auth.service;

public interface IPermissionService {

    boolean hasAccess(String token, String path);
}
