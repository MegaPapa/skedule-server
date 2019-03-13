package com.megapapa.sk.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megapapa.sk.auth.AuthModule;
import com.megapapa.sk.auth.entity.AuthToken;
import com.megapapa.sk.auth.entity.Payload;
import com.megapapa.sk.auth.entity.ValidationMessage;
import com.megapapa.sk.cache.IUserCacheService;
import io.bootique.jetty.servlet.DefaultServletEnvironment;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SkAuthHttpPermissionService implements IPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(SkAuthHttpPermissionService.class);

    private static final String AUTH_SERVER_ACCESS_ADDRESS = "http://127.0.0.1:7070/secure/user/access";
    private static final String AUTH_SERVER_GET_USER_ADDRESS = "http://127.0.0.1:7070/secure/user/info";

    private final ISystemUserService systemUserService;
    private final IUserCacheService userCacheService;
    private final DefaultServletEnvironment environment;
    private final CloseableHttpClient accessHttpClient;
    private final CloseableHttpClient getUserHttpClient;

    public SkAuthHttpPermissionService(DefaultServletEnvironment environment, ISystemUserService systemUserService, IUserCacheService userCacheService) {
        this.environment = environment;
        this.systemUserService = systemUserService;
        this.userCacheService = userCacheService;
        accessHttpClient = HttpClientBuilder.create().build();
        getUserHttpClient = HttpClientBuilder.create().build();
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
                    if (authToken == null) {
                        return false;
                    }
                    HttpPost postRequest = new HttpPost(AUTH_SERVER_ACCESS_ADDRESS);
                    try {
                        ValidationMessage validationMessage = createValidationMessage(path, authToken);
                        ObjectMapper mapper = new ObjectMapper();
                        String jsonRequest = mapper.writeValueAsString(validationMessage);
                        StringEntity entity = new StringEntity(jsonRequest);
                        postRequest.setHeader("Accept", "application/json");
                        postRequest.setHeader("Content-type", "application/json");
                        postRequest.setEntity(entity);
                        CloseableHttpResponse response = accessHttpClient.execute(postRequest);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            // HERE WE NEED TO DO REQUEST TO /secure/user/userInfo
                            response.close();
                            return true;
                        } else {
                            response.close();
                            return false;
                        }
                    } catch (Exception e) {
                        logger.error(e.getLocalizedMessage());
                    }
                }
            }
            return false;
        }
        return false;
    }

    private ValidationMessage createValidationMessage(String path, AuthToken token) {
        ValidationMessage validationMessage = new ValidationMessage();
        Payload payload = new Payload();
        payload.setPath(path);
        payload.setEmail(token.getEmail());
        payload.setNickname(token.getNickname());
        payload.setToken(token.getToken());
        validationMessage.setCode(6);
        validationMessage.setMessage("Resource server trying to get access...");
        validationMessage.setPayload(payload);
        return validationMessage;
    }

    private AuthToken extractToken(String cookieValue) {
        ObjectMapper mapper = new ObjectMapper();
        AuthToken token = null;
        try {
            token = mapper.readValue(cookieValue, AuthToken.class);
        } catch (IOException e) {
            logger.info("Can't map auth token from cookies. Cookie: {}", cookieValue);
        }
        return token;
    }
}
