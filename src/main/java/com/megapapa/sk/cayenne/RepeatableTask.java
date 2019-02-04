package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._RepeatableTask;

public class RepeatableTask extends _RepeatableTask {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "repeatableTask:create";
    public static String READ = "repeatableTask:read";
    public static String DELETE = "repeatableTask:delete";
    public static String UPDATE = "repeatableTask:update";
    public static String LIST = "repeatableTask:list";
}
