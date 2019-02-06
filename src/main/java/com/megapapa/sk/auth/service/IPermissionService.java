package com.megapapa.sk.auth.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IPermissionService {

    boolean hasAccess(String path);
}
