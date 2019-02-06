package com.megapapa.sk.cache.caffeine;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.entity.SystemUser;
import com.megapapa.sk.cache.IUserCacheService;

public class CaffeineUserCacheService implements IUserCacheService {

    private LoadingCache<String, SystemUser> cache;

    public CaffeineUserCacheService(LoadingCache<String, SystemUser> cache) {
        this.cache = cache;
    }

    @Override
    public void putUser(String token, SystemUser user) {
        cache.put(token, user);
    }

    @Override
    public SystemUser getUser(String token) {
        return cache.get(token);
    }
}
