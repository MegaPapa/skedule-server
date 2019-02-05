package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._MongoUser;

public class MongoUser extends _MongoUser {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "mongoUser:create";
    public static final String READ = "mongoUser:read";
    public static final String DELETE = "mongoUser:delete";
    public static final String UPDATE = "mongoUser:update";
    public static final String LIST = "mongoUser:list";
}
