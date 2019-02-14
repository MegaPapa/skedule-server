package com.megapapa.sk.resource.error;

import io.agrest.SimpleResponse;

import javax.ws.rs.core.Response;

public class HasNoAccessResponse extends SimpleResponse {

    private static final String ERROR_MESSAGE_PATTERN = "You haven't access to resource: %s";

    private Response.Status errorCode;

    public HasNoAccessResponse(String resourcePath) {
        super(false, String.format(ERROR_MESSAGE_PATTERN, resourcePath));
        this.errorCode = Response.Status.FORBIDDEN;

    }

    public Response.Status getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Response.Status errorCode) {
        this.errorCode = errorCode;
    }
}
