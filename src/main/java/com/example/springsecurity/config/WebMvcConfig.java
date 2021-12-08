package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String REQUEST_URL = "/";
    private static final String REDIRECT_URL = "/login";
    private static final String ERROR_URL = "/error/**";
    private static final String ERROR_PAGE_CLASSPATH = "classpath:templates/error/";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(REQUEST_URL, REDIRECT_URL);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ERROR_URL)
                .addResourceLocations(ERROR_PAGE_CLASSPATH);
    }
}
