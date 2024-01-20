package com.jkb.prov1.common.types;

public enum LoginStatus {
    SUCCESS(true, "login success"),
    FAIL(false, "login fail"),
    INVALID_PASSWORD(false, "wrong password");

    private boolean status;
    private String message;

    LoginStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
