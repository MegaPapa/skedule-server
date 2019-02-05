package com.megapapa.sk.cayenne;

import com.megapapa.sk.cayenne.auto._BookReadTask;

public class BookReadTask extends _BookReadTask {

    private static final long serialVersionUID = 1L;

    // Access operations
    public static final String CREATE = "bookReadTask:create";
    public static final String READ = "bookReadTask:read";
    public static final String DELETE = "bookReadTask:delete";
    public static final String UPDATE = "bookReadTask:update";
    public static final String LIST = "bookReadTask:list";
}
