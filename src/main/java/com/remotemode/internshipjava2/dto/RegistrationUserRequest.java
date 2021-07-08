package com.remotemode.internshipjava2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationUserRequest {
    private final String login;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
}