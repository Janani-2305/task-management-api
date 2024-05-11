package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.entity.Registration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TmsUserDetails implements UserDetails {

    private String userName;
    private String password;
    private Long userId;
    private String name;

    public TmsUserDetails(Registration registration) {
        this.userName = registration.getEmail();
        this.password = registration.getPassword();
        this.userId = registration.getId();
        this.name = registration.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public String getName(){
        return this.name;
    }
    public Long getUserId(){
        return this.userId;
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
