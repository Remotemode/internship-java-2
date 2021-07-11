package com.remotemode.internshipjava2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements Serializable {
    private String login;
    private String password;
    private Long userId;
    private String role;
}