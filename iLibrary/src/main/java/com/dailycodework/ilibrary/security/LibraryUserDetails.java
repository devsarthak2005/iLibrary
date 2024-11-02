package com.dailycodework.ilibrary.security;

import com.dailycodework.ilibrary.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class LibraryUserDetails implements UserDetails
{
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public LibraryUserDetails(User user)
    {
        username= user.getEmail();
        password = user.getPassword();
        this.authorities = Arrays.stream(user.getRoles()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
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
