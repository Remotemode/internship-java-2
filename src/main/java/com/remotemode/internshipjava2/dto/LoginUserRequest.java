package com.remotemode.internshipjava2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserRequest {
    private final String login;
    private final String password;
}