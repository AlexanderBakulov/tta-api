package com.bakulovas.tta.serviceimpl;


import com.bakulovas.tta.api.dto.request.ChangeUserDtoRequest;
import com.bakulovas.tta.config.ServerConfig;
import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.mappers.UserMapper;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.repository.UserOptionsRepository;
import com.bakulovas.tta.repository.UserRepository;
import com.bakulovas.tta.security.JwtProvider;
import com.bakulovas.tta.security.PasswordService;
import com.bakulovas.tta.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Log
@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final RoleRepository roleRepository;
    private final UserOptionsRepository userOptionsRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;
    private final JwtProvider jwtProvider;
    private final ServerConfig serverConfig;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeRepository officeRepository,
                           RoleRepository roleRepository, UserOptionsRepository userOptionsRepository,
                           UserMapper userMapper, PasswordService passwordService, JwtProvider jwtProvider, ServerConfig serverConfig) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.roleRepository = roleRepository;
        this.userOptionsRepository = userOptionsRepository;
        this.userMapper = userMapper;
        this.passwordService = passwordService;
        this.jwtProvider = jwtProvider;
        this.serverConfig = serverConfig;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginUserDtoResponse loginUser(LoginUserDtoRequest request) {
        User user = new User();
        if(request.getLogin().equals(serverConfig.getAdminLogin()) && passwordService.validatePassword(serverConfig.getAdminPassword(), request.getPassword())) {
            user.setId(0);
            user.setLogin(request.getLogin());
            user.setOffice(officeRepository.getByName(serverConfig.getDefaultOffice()));
            user.setRole(roleRepository.getByName("ADMIN"));
        } else {
            user = getUser(request.getLogin());
            passwordService.validatePassword(user.getPassword(), request.getPassword());
            if (!user.isActive()) {
                throw new ServerException(ServerError.INACTIVE_USER);
            }
        }
            String token = jwtProvider.generateToken(user);
            log.info("LOGIN user with id " + user.getId());
        return userMapper.convertToDto(user, token);
    }

    @Override
    @Transactional
    public UserDtoResponse addUser(AddUserDtoRequest request) {
        Optional<Office> o = officeRepository.findByName(request.getOffice());
        if(o.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_OFFICE_NAME);
        }
        Office office = o.get();
        Optional<Role> r = roleRepository.findByName(request.getRole());
        if(r.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_ROLE);
        }
        Role role = r.get();
        User user = userMapper.convertToUser(request, office, role);
        user.setPassword(passwordService.encodePassword(request.getPassword()));
        userRepository.save(user);
        log.info("ADD user with id " + user.getId());
        return userMapper.convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse getUser(int id) {
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()) {
            throw new ServerException(ServerError.USER_NOT_FOUND);
        }
        User user = u.get();
        log.info("GET user with id " + user.getId());
        return userMapper.convertToDto(user);
    }



    @Override
    public Set<UserDtoResponse> getUsers(String login, String lastname) {
        Set<User> users = new HashSet<>();
        Optional<User> u;
        if(!login.isEmpty() && !lastname.isEmpty()) {
            u = userRepository.findByLoginAndLastName(login, lastname);
            if(u.isPresent()) {
                users.add(u.get());
            }
        }
        if(login.isEmpty() && !lastname.isEmpty()) {
            users = userRepository.findByLastName(lastname);
        }
        if(!login.isEmpty() && lastname.isEmpty()) {
            u = userRepository.findByLogin(login);
            if(u.isPresent()) {
                users.add(u.get());
            }
        }
        if(login.isEmpty() && lastname.isEmpty()) {
            List<User> allUsers = userRepository.findAll();
            users.addAll(allUsers);
        }
        log.info("Get users list " + users);
        return userMapper.usersToDto(users);
    }

    @Override
    public User getUser(String login) throws ServerException {
        Optional<User> u = userRepository.findByLogin(login);
        if(u.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        return u.get();
    }

    @Override
    public UserDtoResponse updateUser(int id, ChangeUserDtoRequest request) {
        return null;
    }


}
