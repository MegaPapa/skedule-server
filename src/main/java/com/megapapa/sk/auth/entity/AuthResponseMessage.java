package com.megapapa.sk.auth.entity;

import java.io.Serializable;

public class AuthResponseMessage implements Serializable {

    private AuthRequestMessage returnedMessage;
    private boolean hasAccess;

    public AuthRequestMessage getReturnedMessage() {
        return returnedMessage;
    }

    public void setReturnedMessage(AuthRequestMessage returnedMessage) {
        this.returnedMessage = returnedMessage;
    }

    public boolean isHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }
}
