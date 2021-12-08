package com.example.springsecurity.init;

import com.example.springsecurity.constant.Roles;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class initializeConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        User admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("1234"))
            .roles(Roles.ADMIN)
            .build();

        User user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("1234"))
            .roles(Roles.USER)
            .build();
        userRepository.saveAll(Arrays.asList(admin, user));
    }
}
