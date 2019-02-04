package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._TaskMessage;

public class TaskMessage extends _TaskMessage {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "taskMessage:create";
    public static String READ = "taskMessage:read";
    public static String DELETE = "taskMessage:delete";
    public static String UPDATE = "taskMessage:update";
    public static String LIST = "taskMessage:list";
}
