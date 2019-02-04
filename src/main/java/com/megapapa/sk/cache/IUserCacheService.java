package com.megapapa.sk.cache;

import com.megapapa.sk.auth.entity.SystemUser;

public interface IUserCacheService {

    void putUser(String token, SystemUser user);

    SystemUser getUser(String token);
}
