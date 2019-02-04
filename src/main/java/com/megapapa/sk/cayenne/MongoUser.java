package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._MongoUser;

public class MongoUser extends _MongoUser {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "mongoUser:create";
    public static String READ = "mongoUser:read";
    public static String DELETE = "mongoUser:delete";
    public static String UPDATE = "mongoUser:update";
    public static String LIST = "mongoUser:list";
}
