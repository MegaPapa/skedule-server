package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Reminder;

public class Reminder extends _Reminder {

    private static final long serialVersionUID = 1L;
    // Access operations
    public static String CREATE = "reminder:create";
    public static String READ = "reminder:read";
    public static String DELETE = "reminder:delete";
    public static String UPDATE = "reminder:update";
    public static String LIST = "reminder:list";
}
