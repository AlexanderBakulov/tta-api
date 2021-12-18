package com.bakulovas.tta.security;

import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public void validatePassword(String password, String passwordFromRequest) {
        if(!passwordEncoder.matches(passwordFromRequest, password)) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
    }

    public String encodePassword(String password) {
        return  passwordEncoder.encode(password);
    }


}
