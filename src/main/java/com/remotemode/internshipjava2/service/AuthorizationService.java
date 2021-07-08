package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}