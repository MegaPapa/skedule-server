package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Currency;

public class Currency extends _Currency {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "currency:create";
    public static String READ = "currency:read";
    public static String DELETE = "currency:delete";
    public static String UPDATE = "currency:update";
    public static String LIST = "currency:list";
}
