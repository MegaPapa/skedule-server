package com.megapapa.sk.cache;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;

public class CacheModule extends ConfigModule {

    private static final String CACHE_MODULE_PREFIX = "userCache";

    @Provides
    @Singleton
    public IUserCacheService providesCaffeineCache(ConfigurationFactory configFactory) {
        return configFactory
                .config(UserCacheServiceFactory.class, CACHE_MODULE_PREFIX)
                .produceCacheService();
    }
}
