package com.megapapa.sk.auth.service;

import com.megapapa.sk.auth.entity.SystemUser;

public class SystemUserService implements ISystemUserService {

    private SystemUser currentSystemUser;

    @Override
    public SystemUser getSystemUser() {
        return currentSystemUser;
    }

    @Override
    public void setSystemUser(SystemUser systemUser) {
        this.currentSystemUser = systemUser;
    }
}
