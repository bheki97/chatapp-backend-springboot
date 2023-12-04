package com.fnb.bheki97.chatappbackendspringboot.config.security.userdetails;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class GeekSecurityDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> grantedAuthorities;


    public GeekSecurityDetails(Geek geek) {
        this.username = geek.getUsername();
        this.password = geek.getPassword();
        this.grantedAuthorities = Arrays.stream("ROLE_geek".split("#"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
