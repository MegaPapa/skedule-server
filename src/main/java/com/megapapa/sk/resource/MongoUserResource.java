package com.megapapa.sk.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

@Path("user")
public class MongoUserResource {

    @Context
    private Configuration config;
}
