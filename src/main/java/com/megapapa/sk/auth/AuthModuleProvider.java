package com.megapapa.sk.auth;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class AuthModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new AuthModule();
    }
}
