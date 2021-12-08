package com.example.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final String REDIRECT_URL = "/main";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
        log.info("로그인 유저정보 : {}", userDetails);

        response.sendRedirect(REDIRECT_URL);
    }
}
