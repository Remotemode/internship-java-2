package com.remotemode.internshipjava2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtUser implements Serializable {
    private String login;
    private String password;
    private Long userId;
    private String role;
}