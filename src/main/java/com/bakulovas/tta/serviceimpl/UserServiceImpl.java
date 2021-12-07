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
    private final UserOptionsRepository userOptionsRepository;
    private final CommonMapper commonMapper;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeRepository officeRepository, UserOptionsRepository userOptionsRepository, CommonMapper commonMapper, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.userOptionsRepository = userOptionsRepository;
        this.commonMapper = commonMapper;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @Transactional
    public LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException {
        User user = getUser(request);
        String token = jwtProvider.generateToken(user);
        String officeName = officeRepository.getById(user.getOfficeId()).getName();
        log.info("LOGIN user with id " + user.getId());
        return commonMapper.convertToDto(user, token, officeName);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    @Transactional
    public UserDtoResponse addUser(AddUserDtoRequest request) throws ServerException {
        validateRole(request.getRole());
        Office office = officeRepository.getByName(request.getOffice());
        if(office == null) {
            throw new ServerException(ServerError.INCORRECT_OFFICE_NAME);
        }
        User user = commonMapper.convertToUser(request,office.getId());
        userRepository.save(user);
        log.info("Add user with id " + user.getId());
        return commonMapper.convertToDto(user, office.getName());
    }

    private User getUser(LoginUserDtoRequest request) throws ServerException {
        User user = getByLogin(request.getLogin());
        if(user == null || !request.getPassword().equals(user.getPassword())) {
            throw new ServerException(ServerError.INCORRECT_LOGIN_OR_PASSWORD);
        }
        if(!user.isActive()) {
            throw new ServerException(ServerError.INACTIVE_USER);
        }
        return user;
    }

    private void validateRole(String role) throws ServerException {
        if(role == null) {
            throw new ServerException(ServerError.EMPTY_ROLE);
        }
        if(!role.equals(Role.USER) && !role.equals(Role.MANAGER) && !role.equals(Role.SUPPORT) && !role.equals(Role.ADMIN)) {
            throw new ServerException(ServerError.INCORRECT_ROLE);
        }
    }
}
