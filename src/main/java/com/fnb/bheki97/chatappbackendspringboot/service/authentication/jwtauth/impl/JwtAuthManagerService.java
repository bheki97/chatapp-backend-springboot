package com.fnb.bheki97.chatappbackendspringboot.service.authentication.jwtauth.impl;

import com.fnb.bheki97.chatappbackendspringboot.config.security.jwt.JwtService;
import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;
import com.fnb.bheki97.chatappbackendspringboot.service.authentication.jwtauth.JwtAuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthManagerService implements JwtAuthManager {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @Override
    public String authenticateGeek(LoginDto dto) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        if(auth.isAuthenticated()){
            return jwtService.generateToken(dto.getUsername());
        }

        throw new UsernameNotFoundException("Invalid credentials!!!");
    }


}
