package com.bakulovas.tta.serviceimpl;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.mappers.CommonMapper;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.repository.UserOptionsRepository;
import com.bakulovas.tta.repository.UserRepository;
import com.bakulovas.tta.security.JwtProvider;
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
    private final OfficeRepository officeRepository;
    private final RoleRepository roleRepository;
    private final UserOptionsRepository userOptionsRepository;
    private final CommonMapper commonMapper;
    private final CommonService commonService;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeRepository officeRepository,
                           RoleRepository roleRepository, UserOptionsRepository userOptionsRepository,
                           CommonMapper commonMapper, CommonService commonService, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.roleRepository = roleRepository;
        this.userOptionsRepository = userOptionsRepository;
        this.commonMapper = commonMapper;
        this.commonService = commonService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @Transactional
    public LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException {
        User user = getUser(request.getLogin());
        if(user == null) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        commonService.validatePassword(user, request.getPassword());
        if(!user.isActive()) {
            throw new ServerException(ServerError.INACTIVE_USER);
        }
        String token = jwtProvider.generateToken(user);
        log.info("LOGIN user with id " + user.getId());
        return commonMapper.convertToDto(user, token);
    }

    @Override
    @Transactional
    public User getUser(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    @Transactional
    public UserDtoResponse addUser(AddUserDtoRequest request) throws ServerException {
        Office office = officeRepository.getByName(request.getOffice());
        commonService.isEmpty(office);
        Role role = roleRepository.getByName(request.getRole());
        commonService.isEmpty(role);
        User user = commonMapper.convertToUser(request, office, role);
        userRepository.save(user);
        log.info("ADD user with id " + user.getId());
        return commonMapper.convertToDto(user);
    }

    @Override
    @Transactional
    public UserDtoResponse getUser(int id) throws ServerException {
        User user = userRepository.getById(id);
        if(user == null) {
            throw new ServerException(ServerError.USER_NOT_FOUND);
        }
        log.info("GET user with id " + user.getId());
        return commonMapper.convertToDto(user);
    }




}
