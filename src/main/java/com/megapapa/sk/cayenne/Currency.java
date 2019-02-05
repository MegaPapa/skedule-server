package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Currency;

public class Currency extends _Currency {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "currency:create";
    public static final String READ = "currency:read";
    public static final String DELETE = "currency:delete";
    public static final String UPDATE = "currency:update";
    public static final String LIST = "currency:list";
}
