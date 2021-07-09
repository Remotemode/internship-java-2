package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.JwtUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenCacheService {

    private final Map<String, JwtUser> jwtTokenCache = new HashMap<>();

    void addToken(String jwtToken, JwtUser jwtUser) {
        jwtTokenCache.put(jwtToken, jwtUser);
    }

    void removeToken(String jwtToken) {
        jwtTokenCache.remove(jwtToken);
    }
}