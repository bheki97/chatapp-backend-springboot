package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeekRepository extends JpaRepository<Geek,String> {

    boolean existsByEmail(String email);
    Optional<Geek> findByUsername(String username);
    boolean existsByCellNumber(String cellNumber);
}
