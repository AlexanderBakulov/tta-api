package com.bakulovas.tta.serviceimpl;


import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.mappers.CommonMapper;
import com.bakulovas.tta.repository.jpa.UserRepository;
import com.bakulovas.tta.security.jwt.JwtProvider;
import com.bakulovas.tta.service.UserService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CommonMapper commonMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, CommonMapper commonMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.commonMapper = commonMapper;
    }

    @Override
    @Transactional
    public LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException {
        User user = getUser(request);
        String token = jwtProvider.generateToken(user.getLogin());
        log.info("LOGIN user with id " + user.getId());
        return commonMapper.convertToDto(user, token);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    private User getUser(LoginUserDtoRequest request) throws ServerException {
        User user = findByLogin(request.getLogin());
        if(user == null || passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        if(!user.isActive()) {
            throw new ServerException(ServerError.INACTIVE_USER);
        }
        return user;
    }
}
