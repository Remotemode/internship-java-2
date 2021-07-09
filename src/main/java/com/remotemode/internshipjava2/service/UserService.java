package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.UserEntity;
import com.remotemode.internshipjava2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            return null;
        } else {
            return userEntityOptional.get();
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}