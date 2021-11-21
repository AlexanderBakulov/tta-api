package com.bakulovas.tta.serviceimpl;


import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.repository.jpa.UserRepository;
import com.bakulovas.tta.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log
@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public LoginUserDtoResponse login(LoginUserDtoRequest request) throws ServerException {
        User user = getUser(request);

        return null;
    }

    private User getUser(LoginUserDtoRequest request) throws ServerException {
        User user = userRepository.findByLoginName(request.getLoginName());
        if(user == null || !user.getPassword().equals(request.getPassword())) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        if(!user.isActive()) {
            throw new ServerException(ServerError.INACTIVE_USER);
        }
        return user;
    }
}
