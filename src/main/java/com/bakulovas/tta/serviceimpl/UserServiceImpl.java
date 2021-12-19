package com.bakulovas.tta.serviceimpl;


import com.bakulovas.tta.api.dto.request.UpdateUserDtoRequest;
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

import java.time.LocalDateTime;
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
        Office office = findOfficeByName(request.getOffice());
        Role role = findRoleByName(request.getRole());
        User user = userMapper.convertToUser(request, office, role);
        user.setPassword(passwordService.encodePassword(request.getPassword()));
        user.setCreated(LocalDateTime.now());
        //todo add real creator
        user.setCreator("Admin");
        userRepository.save(user);
        log.info("ADD user with id " + user.getId());
        return userMapper.convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse getUser(int id) {
        User user = findById(id);
        log.info("GET user with id " + user.getId());
        return userMapper.convertToDto(user);
    }



    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public User getUser(String login) throws ServerException {
        Optional<User> u = userRepository.findByLogin(login);
        if(u.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        return u.get();
    }

    @Override
    @Transactional
    public UserDtoResponse updateUser(int id, UpdateUserDtoRequest request) {
        User user = findById(id);
        if(request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if(request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if(request.getOffice() != null) {
            user.setOffice(findOfficeByName(request.getOffice()));
        }
        if(request.getRole() != null) {
            user.setRole(findRoleByName(request.getRole()));
        }
        userRepository.save(user);
        log.info("UPDATE user with id " + user.getId());
        return userMapper.convertToDto(user);
    }


    private User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ServerException(ServerError.USER_NOT_FOUND);
        }
        return user.get();
    }

    private Role findRoleByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if(role.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_ROLE);
        }
        return role.get();
    }

    private Office findOfficeByName(String name) {
        Optional<Office> office = officeRepository.findByName(name);
        if(office.isEmpty()) {
            throw new ServerException(ServerError.INCORRECT_OFFICE_NAME);
        }
        return  office.get();
    }

}
