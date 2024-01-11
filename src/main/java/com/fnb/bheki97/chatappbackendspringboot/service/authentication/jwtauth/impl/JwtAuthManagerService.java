package com.fnb.bheki97.chatappbackendspringboot.service.authentication.jwtauth.impl;

import com.fnb.bheki97.chatappbackendspringboot.config.security.jwt.JwtService;
import com.fnb.bheki97.chatappbackendspringboot.dto.AuthDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.TokenAuthDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.authentication.jwtauth.JwtAuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtAuthManagerService implements JwtAuthManager {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    GeekRepository geekRepository;


    @Override
    public AuthDto authenticateGeek(LoginDto dto) {

        TokenAuthDto authDto = new TokenAuthDto();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        if(auth.isAuthenticated()){

            authDto.setAuthToken(jwtService.generateToken(dto.getUsername()));
            Optional<Geek> geek = geekRepository.findByUsername(dto.getUsername());

            geek.ifPresent(authDto::setGeek);

            if(authDto.getGeek()==null){
                throw new UsernameNotFoundException("Account Does not exist");
            }

            return authDto;
        }

        throw new UsernameNotFoundException("Invalid credentials!!!");
    }


}
