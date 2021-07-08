package com.remotemode.internshipjava2.controller;

import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.service.AuthorizationService;
import com.remotemode.internshipjava2.util.AuthHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthorizationService authorizationService;

    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody @NotNull RegistrationUserRequest registrationUserRequest) {
        AuthHelper.checkRegistrationUserReguest(registrationUserRequest);
        authorizationService.registerUser(registrationUserRequest);
    }
}