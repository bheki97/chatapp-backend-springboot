package com.fnb.bheki97.chatappbackendspringboot.controller.auth;

import com.fnb.bheki97.chatappbackendspringboot.config.security.jwt.JwtService;
import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @PostMapping
    public String login(@RequestBody LoginDto dto){

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        if(auth.isAuthenticated()){
            return jwtService.generateToken(dto.getUsername());
        }

        throw new UsernameNotFoundException("Invalid credentials!!!");
    }
}
