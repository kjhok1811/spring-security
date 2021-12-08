package com.example.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final String REDIRECT_URL = "/error/error401.html";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("사용자 인증자격 만료");
        response.sendRedirect(REDIRECT_URL);
    }
}
