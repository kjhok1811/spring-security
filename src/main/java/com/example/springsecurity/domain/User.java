package com.example.springsecurity.domain;

import com.example.springsecurity.constant.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Builder
    public User(String username, String password, Roles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
