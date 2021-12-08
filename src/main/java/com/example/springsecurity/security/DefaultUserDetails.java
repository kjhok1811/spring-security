package com.example.springsecurity.security;

import com.example.springsecurity.constant.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class DefaultUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Roles roles;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public DefaultUserDetails(Long id, String username, String password, Roles roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.authorities = AuthorityUtils.createAuthorityList(roles.getSecurityRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
