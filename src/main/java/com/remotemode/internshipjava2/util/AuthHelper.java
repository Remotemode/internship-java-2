package com.remotemode.internshipjava2.util;

import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.exception.BadRequestException;

public class AuthHelper {
    public static void checkRegistrationUserReguest(RegistrationUserRequest registrationUserRequest) {
        String login = registrationUserRequest.getLogin();
        String password = registrationUserRequest.getPassword();
        String email = registrationUserRequest.getEmail();
        String firstName = registrationUserRequest.getFirstName();
        String lastName = registrationUserRequest.getLastName();

        if (isNullOrBlank(login)) {
            throw new BadRequestException("Login is missed");
        }
        if (isNullOrBlank(password)) {
            throw new BadRequestException("Password is missed");
        }
        if (isNullOrBlank(email)) {
            throw new BadRequestException("Email is missed");
        }
        if (isNullOrBlank(firstName)) {
            throw new BadRequestException("FirstName is missed");
        }
        if (isNullOrBlank(lastName)) {
            throw new BadRequestException("LastName is missed");
        }
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
}