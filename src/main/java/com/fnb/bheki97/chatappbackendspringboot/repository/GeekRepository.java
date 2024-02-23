package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeekRepository extends JpaRepository<Geek,Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Geek> findByUsername(String username);
    List<Geek> findByGeekIdIsNot(Long id);
    boolean existsByCellNumber(String cellNumber);
}
