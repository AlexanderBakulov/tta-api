package com.bakulovas.tta.serviceimpl;

import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CommonService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommonService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public void validatePassword(User user, String password) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
    }



}
