package com.megapapa.sk.auth.service;

import com.megapapa.sk.auth.AuthModule;
import com.megapapa.sk.auth.entity.AuthToken;
import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SkAuthHttpPermissionService implements IPermissionService {

    private final ISystemUserService systemUserService;
    private final IUserCacheService userCacheService;
    private final DefaultServletEnvironment environment;
    private final HttpClient httpClient;

    public SkAuthHttpPermissionService(DefaultServletEnvironment environment, ISystemUserService systemUserService, IUserCacheService userCacheService) {
        this.environment = environment;
        this.systemUserService = systemUserService;
        this.userCacheService = userCacheService;
        httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public boolean hasAccess(String path) {
        if (environment.request().isPresent()) {
            HttpServletRequest httpServletRequest = environment.request().get();
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies == null) {
                return false;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthModule.AUTH_TOKEN_NAME)) {
                    AuthToken authToken = extractToken(cookie.getValue());
                    // IN AUTH COOKIE MAY BE JSON WITH EMAIL, TOKEN, NICKNAME
                    // DO HTTP REQUEST TO AUTH SERVER HERE
                }
            }
            return false;
        }
        return false;
    }

    private AuthToken extractToken(String cookieValue) {

    }
}
