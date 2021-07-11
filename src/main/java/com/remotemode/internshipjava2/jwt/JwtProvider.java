package com.remotemode.internshipjava2.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotemode.internshipjava2.exception.BadRequestException;
import com.remotemode.internshipjava2.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(JwtUser jwtUser) {
        String jsonSubject;
        try {
            jsonSubject = objectMapper.writeValueAsString(jwtUser);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Exception when generate jwtToken");
        }
        return Jwts.builder()
                .setSubject(jsonSubject)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public JwtUser getUserFromToken(String jwtToken) throws JsonProcessingException {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
        String subject = claims.getSubject();
        return objectMapper.readValue(subject, JwtUser.class);
    }
}