package com.example.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {
    private static final String REDIRECT_URL = "/error/error403.html";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("접근권한 없음");
        log.info("요청 URL : {}", request.getRequestURL());

        DefaultUserDetails userDetails = (DefaultUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("요청사용자 권한 : {}", userDetails.getAuthorities());

        response.sendRedirect(REDIRECT_URL);
    }
}
