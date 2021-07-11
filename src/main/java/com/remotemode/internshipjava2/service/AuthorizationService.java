package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.dto.LoginUserRequest;
import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.exception.BadRequestException;
import com.remotemode.internshipjava2.jwt.JwtProvider;
import com.remotemode.internshipjava2.model.JwtUser;
import com.remotemode.internshipjava2.model.UserEntity;
import com.remotemode.internshipjava2.repository.UserRepository;
import com.remotemode.internshipjava2.util.UserAdapter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtTokenCacheService jwtTokenCacheService;

    public AuthorizationService(UserRepository userRepository,
                                JwtProvider jwtProvider,
                                JwtTokenCacheService jwtTokenCacheService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.jwtTokenCacheService = jwtTokenCacheService;
    }

    public Long registerUser(RegistrationUserRequest registrationUserRequest) {
        UserEntity userEntity = UserAdapter.toUserEntity(registrationUserRequest);

        UserEntity savedEntry = userRepository.save(userEntity);

        return savedEntry.getUserId();
    }

    public String loginUser(LoginUserRequest loginUserRequest) {
        String login = loginUserRequest.getLogin();
        String password = loginUserRequest.getPassword();

        UserEntity userEntity = userRepository.findByLoginAndPassword(login, password);

        JwtUser jwtUser = UserAdapter.toJwtUser(userEntity);
        String jwtToken = jwtProvider.generateToken(jwtUser);

        jwtTokenCacheService.addToken(jwtToken, jwtUser);

        if (Objects.nonNull(userEntity.getUserId())) {
            return jwtToken;
        } else {
            throw new BadRequestException("Incorrect login or password");
        }
    }
}