package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._BookReadTask;

public class BookReadTask extends _BookReadTask {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static String CREATE = "bookReadTask:create";
    public static String READ = "bookReadTask:read";
    public static String DELETE = "bookReadTask:delete";
    public static String UPDATE = "bookReadTask:update";
    public static String LIST = "bookReadTask:list";
}
