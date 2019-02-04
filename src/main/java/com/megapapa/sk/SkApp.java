package com.megapapa.sk;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.megapapa.sk.resource.MongoUserResource;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

public class SkApp implements Module {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(SkApp.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule.extend(binder).addPackage(MongoUserResource.class.getPackage());
    }
}
