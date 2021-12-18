package com.bakulovas.tta.config;

public class StaticData {

    public static final String INVALID_PASSWORD = "Password must contains at least one lowercase letter, one uppercase letter and one digit.";
    public static final String INVALID_LOGIN = "Login must contains only latin letters";
    public static final String PASSWORD_VALIDATION_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    public static final String NAME_VALIDATION_REGEXP = "^[A-Za-z0-9]+$";
}
