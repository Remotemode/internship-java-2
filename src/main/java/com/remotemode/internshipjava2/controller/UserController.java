package com.remotemode.internshipjava2.controller;

import com.remotemode.internshipjava2.exception.BadRequestException;
import com.remotemode.internshipjava2.model.UserEntity;
import com.remotemode.internshipjava2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        if (Objects.isNull(userId)) {
            throw new BadRequestException("UserId can't be null");
        }
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }
}