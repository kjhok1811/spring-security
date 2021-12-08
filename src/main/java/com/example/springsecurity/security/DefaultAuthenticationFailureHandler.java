package com.example.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String REDIRECT_URL = "/login?error=1";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("exception message : {}", exception.getMessage());
        response.sendRedirect(REDIRECT_URL);
    }
}

