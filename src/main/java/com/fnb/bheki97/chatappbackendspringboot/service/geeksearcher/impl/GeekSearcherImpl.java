package com.fnb.bheki97.chatappbackendspringboot.service.geeksearcher.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.SearchedGeekDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.geeksearcher.GeekSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeekSearcherImpl implements GeekSearcher {

    @Autowired
    private GeekRepository geekRepository;


    @Override
    public List<SearchedGeekDto> searchForGeekExcludingById(long geekId) {

        if(geekId<0) throw new ChatAppException("");

        return geekRepository.findByGeekIdIsNot(geekId).stream().map(this::GeekToDto).toList();
    }

    private SearchedGeekDto GeekToDto(Geek geek){
        SearchedGeekDto dto = new SearchedGeekDto();

        dto.setGeekId(geek.getGeekId());
        dto.setUsername(geek.getUsername());

        return dto;
    }
}
