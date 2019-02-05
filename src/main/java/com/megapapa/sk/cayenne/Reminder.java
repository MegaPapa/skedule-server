package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._Reminder;

public class Reminder extends _Reminder {

    private static final long serialVersionUID = 1L;
    // Access operations
    public static final String CREATE = "reminder:create";
    public static final String READ = "reminder:read";
    public static final String DELETE = "reminder:delete";
    public static final String UPDATE = "reminder:update";
    public static final String LIST = "reminder:list";
}
