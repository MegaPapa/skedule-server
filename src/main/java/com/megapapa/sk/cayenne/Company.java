package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Company;

public class Company extends _Company {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "company:create";
    public static String READ = "company:read";
    public static String DELETE = "company:delete";
    public static String UPDATE = "company:update";
    public static String LIST = "company:list";
}
