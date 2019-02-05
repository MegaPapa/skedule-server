package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Company;

public class Company extends _Company {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "company:create";
    public static final String READ = "company:read";
    public static final String DELETE = "company:delete";
    public static final String UPDATE = "company:update";
    public static final String LIST = "company:list";
}
