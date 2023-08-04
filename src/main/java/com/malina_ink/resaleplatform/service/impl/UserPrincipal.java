package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.UserDetailsDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final UserDetailsDto userDetailsDto;

    public UserPrincipal(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+userDetailsDto.getRole().name());
        return List.of(grantedAuthority);
    }

    public String getUserName(){
        return userDetailsDto.getName();
    }

    @Override
    public String getPassword() {
        return userDetailsDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsDto.getUsername();
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
