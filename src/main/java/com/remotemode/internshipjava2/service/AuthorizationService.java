package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.dto.LoginUserRequest;
import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.model.UserEntity;
import com.remotemode.internshipjava2.repository.UserRepository;
import com.remotemode.internshipjava2.util.UserAdapter;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser(RegistrationUserRequest registrationUserRequest) {
        UserEntity userEntity = UserAdapter.toUserEntity(registrationUserRequest);

        UserEntity savedEntry = userRepository.save(userEntity);

        return savedEntry.getUserId();
    }

    public void loginUser(LoginUserRequest loginUserRequest) {
        String login = loginUserRequest.getLogin();
        String password = loginUserRequest.getPassword();

        userRepository.findByLoginAndPassword(login, password);
    }
}