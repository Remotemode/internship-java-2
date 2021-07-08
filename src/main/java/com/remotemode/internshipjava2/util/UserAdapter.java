package com.remotemode.internshipjava2.util;

import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.model.UserEntity;
import com.remotemode.internshipjava2.model.UserRole;

public class UserAdapter {
    public static UserEntity toUserEntity(RegistrationUserRequest registrationUserRequest) {
        return new UserEntity(
                registrationUserRequest.getLogin(),
                registrationUserRequest.getPassword(),
                registrationUserRequest.getEmail(),
                registrationUserRequest.getFirstName(),
                registrationUserRequest.getLastName(),
                UserRole.USER);
    }
}