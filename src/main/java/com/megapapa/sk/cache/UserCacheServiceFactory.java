package com.megapapa.sk.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.megapapa.sk.auth.entity.SystemUser;
import com.megapapa.sk.cache.caffeine.CaffeineUserCacheService;

import java.util.concurrent.TimeUnit;

public class UserCacheServiceFactory {

    private static final String CAFFEINE_TYPE = "caffeine";

    private long cacheLifeTimeMillis;
    private long cacheMaxSize;
    private String type;

    IUserCacheService produceCacheService() {
        switch (type) {
            case CAFFEINE_TYPE:
                return produceCaffeineCache();
            default:
                throw new IllegalStateException("Illegal cache type: " + type);
        }
    }

    private IUserCacheService produceCaffeineCache() {
        // Key in this cache is valid token, value - SystemUser credentials
        LoadingCache<String, SystemUser> cache = Caffeine.newBuilder()
                .maximumSize(cacheMaxSize)
                .expireAfterWrite(cacheLifeTimeMillis, TimeUnit.MILLISECONDS)
                .refreshAfterWrite(cacheLifeTimeMillis, TimeUnit.MILLISECONDS)
                // If user isn't present in cache - return null
                .build(key -> null);
        return new CaffeineUserCacheService(cache);
    }

    public long getCacheLifeTimeMillis() {
        return cacheLifeTimeMillis;
    }

    public void setCacheLifeTimeMillis(long cacheLifeTimeMillis) {
        this.cacheLifeTimeMillis = cacheLifeTimeMillis;
    }

    public long getCacheMaxSize() {
        return cacheMaxSize;
    }

    public void setCacheMaxSize(long cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
