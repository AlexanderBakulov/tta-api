package com.bakulovas.tta.serviceimpl;

import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public void validatePassword(User user, String password) {
        if(!user.getPassword().equals(password))
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
    }

    public void isEmpty(Office office) {
        if(office == null) {
            throw new ServerException(ServerError.INCORRECT_OFFICE_NAME);
        }
    }

    public void isEmpty(Role role) {
        if(role == null) {
            throw new ServerException(ServerError.EMPTY_ROLE);
        }
    }


}
