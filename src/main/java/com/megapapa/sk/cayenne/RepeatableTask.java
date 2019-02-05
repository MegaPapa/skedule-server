package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._RepeatableTask;

public class RepeatableTask extends _RepeatableTask {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "repeatableTask:create";
    public static final String READ = "repeatableTask:read";
    public static final String DELETE = "repeatableTask:delete";
    public static final String UPDATE = "repeatableTask:update";
    public static final String LIST = "repeatableTask:list";
}
