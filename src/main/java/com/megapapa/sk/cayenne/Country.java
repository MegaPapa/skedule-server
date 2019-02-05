package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Country;

public class Country extends _Country {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "country:create";
    public static final String READ = "country:read";
    public static final String DELETE = "country:delete";
    public static final String UPDATE = "country:update";
    public static final String LIST = "country:list";
}
