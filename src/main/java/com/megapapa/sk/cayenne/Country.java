package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Country;

public class Country extends _Country {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "country:create";
    public static String READ = "country:read";
    public static String DELETE = "country:delete";
    public static String UPDATE = "country:update";
    public static String LIST = "country:list";
}
