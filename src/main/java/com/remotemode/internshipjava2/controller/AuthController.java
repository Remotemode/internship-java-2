package com.remotemode.internshipjava2.controller;

import com.remotemode.internshipjava2.dto.LoginUserRequest;
import com.remotemode.internshipjava2.dto.RegistrationUserRequest;
import com.remotemode.internshipjava2.service.AuthorizationService;
import com.remotemode.internshipjava2.util.AuthHelper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/login")
    public String loginUser(@RequestBody @NotNull LoginUserRequest loginUserRequest) {
        AuthHelper.checkAuthUserRequest(loginUserRequest);
        return authorizationService.loginUser(loginUserRequest);
    }

    @GetMapping("/logout")
    public void logout(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String authorizationHeaderValue = httpRequest.getHeader("Authorization");
        String jwtToken =  authorizationHeaderValue.replace("Bearer ", "");

        authorizationService.logout(jwtToken);
    }
}