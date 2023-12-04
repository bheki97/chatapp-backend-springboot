package com.fnb.bheki97.chatappbackendspringboot.config.security.userdetails;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GeekSecurityDetailsService implements UserDetailsService {

    @Autowired
    private GeekRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Geek> user = repository.findById(username);

        return user.map(GeekSecurityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Geek not found " + username));
    }
}
