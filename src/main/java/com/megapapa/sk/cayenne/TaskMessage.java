package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._TaskMessage;

public class TaskMessage extends _TaskMessage {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "taskMessage:create";
    public static final String READ = "taskMessage:read";
    public static final String DELETE = "taskMessage:delete";
    public static final String UPDATE = "taskMessage:update";
    public static final String LIST = "taskMessage:list";
}
