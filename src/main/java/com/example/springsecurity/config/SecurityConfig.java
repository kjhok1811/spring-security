package com.example.springsecurity.config;

import com.example.springsecurity.constant.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String MAIN_PATH = "/main/**";
    private static final String ADMIN_PATH = "/admin/**";
    private static final String USER_PATH = "/user/**";
    private static final String IGNORE_FAVICON = "/favicon.ico";
    private static final String LOGIN_PAGE = "/login";
    private static final String LOGIN_PROCESSING_URL = "/loginProcess";
    private static final String LOGOUT_URL = "/logout";
    private static final String LOGOUT_SUCCESS_URL = "/";
    private static final String REMEMBER_ME_TOKEN_KEY = "uniqueKey";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 86400 * 14;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORE_FAVICON);
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(MAIN_PATH).authenticated()
            .antMatchers(ADMIN_PATH).hasRole(Roles.ADMIN.name())
            .antMatchers(USER_PATH).hasRole(Roles.USER.name())
        .and()
            .csrf().disable()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint);

        http
            .formLogin()
            .loginPage(LOGIN_PAGE)
            .loginProcessingUrl(LOGIN_PROCESSING_URL)
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
        .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
            .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
            .invalidateHttpSession(Boolean.TRUE)
        .and()
            .rememberMe()
            .key(REMEMBER_ME_TOKEN_KEY)
            .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
