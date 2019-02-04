package com.megapapa.sk.cache;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class CacheModuleProvider implements BQModuleProvider {


    @Override
    public Module module() {
        return new CacheModule();
    }
}
