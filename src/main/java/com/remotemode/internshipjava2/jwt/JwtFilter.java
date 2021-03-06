package com.remotemode.internshipjava2.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.remotemode.internshipjava2.model.JwtUser;
import com.remotemode.internshipjava2.service.JwtTokenCacheService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final JwtTokenCacheService jwtTokenCacheService;

    public static final String AUTHORIZATION = "Authorization";

    public JwtFilter(JwtProvider jwtProvider, JwtTokenCacheService jwtTokenCacheService) {
        this.jwtProvider = jwtProvider;
        this.jwtTokenCacheService = jwtTokenCacheService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String url = ((HttpServletRequest) servletRequest).getRequestURL().toString();

        String authorizationHeaderValue = httpRequest.getHeader(AUTHORIZATION);

        if (url.contains("/user") || url.contains("/logout")) {
            checkAuthorizationHeader(authorizationHeaderValue, httpResponse);

            if (Objects.nonNull(authorizationHeaderValue)) {
                String jwtToken = authorizationHeaderValue.replace("Bearer ", "");

                checkIfJwtTokenIsPresent(jwtToken, httpResponse);

                JwtUser userFromToken = getJwtUserFromToken(jwtToken, httpResponse);
                checkIsMethodAllowed(userFromToken, url, httpRequest, httpResponse);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void checkAuthorizationHeader(String authorizationHeaderValue, HttpServletResponse httpResponse) throws IOException {
        if (Objects.isNull(authorizationHeaderValue)) {
            httpResponse.sendError(401);
        }

        if (Objects.nonNull(authorizationHeaderValue) && !authorizationHeaderValue.contains("Bearer ")) {
            httpResponse.sendError(401);
        }
    }

    private void checkIfJwtTokenIsPresent(String authorizationHeaderValue, HttpServletResponse httpResponse) throws IOException {
        String jwtToken = authorizationHeaderValue.replace("Bearer ", "");
        JwtUser userFromToken = getJwtUserFromToken(jwtToken, httpResponse);

        if (Objects.isNull(userFromToken)) {
            httpResponse.sendError(401);
        }

        checkIfJwtTokenPresentsInCache(jwtToken, httpResponse);
    }

    private JwtUser getJwtUserFromToken(String jwtToken, HttpServletResponse httpResponse) throws IOException {
        try {
            return jwtProvider.getUserFromToken(jwtToken);
        } catch (JsonProcessingException e) {
            httpResponse.sendError(401);
            return null;
        }
    }

    private void checkIfJwtTokenPresentsInCache(String jwtToken, HttpServletResponse httpResponse) throws IOException {
        JwtUser jwtUser = jwtTokenCacheService.getJwtUserByToken(jwtToken);
        if (Objects.isNull(jwtUser)) {
            httpResponse.sendError(401);
        }
    }

    private void checkIsMethodAllowed(JwtUser jwtUser, String url, HttpServletRequest httpServletRequest, HttpServletResponse httpResponse)
            throws IOException {

        if (url.contains("/all") && !jwtUser.getRole().equals("ADMIN")) {
            httpResponse.sendError(403);
        }
    }
}