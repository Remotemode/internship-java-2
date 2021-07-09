package com.remotemode.internshipjava2.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtFilter {

    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}