package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeekRepository extends JpaRepository<Geek,String> {

    boolean existsByEmail(String email);
    boolean existsByCellNumber(String cellNumber);
}
