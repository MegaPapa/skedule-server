package com.megapapa.sk.auth.service;

import com.megapapa.sk.auth.entity.SystemUser;

public class StubSystemUserService implements ISystemUserService {

    private SystemUser defaultSystemUser;

    public StubSystemUserService() {
        defaultSystemUser = new SystemUser();
        defaultSystemUser.setId(1);
        defaultSystemUser.setFirstName("John");
        defaultSystemUser.setLastName("Doe");
        defaultSystemUser.setGender("M");
        defaultSystemUser.setPassword("AGAGAGAGAGA");
        defaultSystemUser.setUsername("TestUser");
    }

    @Override
    public SystemUser getSystemUser() {
        return defaultSystemUser;
    }

    @Override
    public void setSystemUser(SystemUser systemUser) {
        this.defaultSystemUser = systemUser;
    }
}
