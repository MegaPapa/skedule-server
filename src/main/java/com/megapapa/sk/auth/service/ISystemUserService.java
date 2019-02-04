package com.megapapa.sk.auth.service;

import com.megapapa.sk.auth.entity.SystemUser;

public interface ISystemUserService {

    SystemUser getSystemUser();

    void setSystemUser(SystemUser systemUser);
}
