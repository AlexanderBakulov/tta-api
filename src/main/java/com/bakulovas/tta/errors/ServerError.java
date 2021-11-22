package com.bakulovas.tta.errors;

public enum ServerError {

    USER_NOT_AUTHORIZED("User not authorized"),
    USER_NOT_AUTHENTICATED("User not authenticated."),
    INCORRECT_ROLE("Incorrect role."),
    INCORRECT_OFFICE_NAME("There is no office with this name."),
    INCORRECT_DIVISION_NAME("There is no division with this name."),
    INACTIVE_USER("Inactive user."),
    INCORRECT_TOKEN("Incorrect token."),
    INCORRECT_LOGIN_OR_PASSWORD("Incorrect login or password."),
    USER_NOT_FOUND("User not found.");

    private final String errorString;

    ServerError(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return this.errorString;
    }
}