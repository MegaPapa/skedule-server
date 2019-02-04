package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._SelfTask;

public class SelfTask extends _SelfTask {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "selfTask:create";
    public static String READ = "selfTask:read";
    public static String DELETE = "selfTask:delete";
    public static String UPDATE = "selfTask:update";
    public static String LIST = "selfTask:list";
}
