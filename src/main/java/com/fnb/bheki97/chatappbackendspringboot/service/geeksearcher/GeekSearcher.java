package com.fnb.bheki97.chatappbackendspringboot.service.geeksearcher;

import com.fnb.bheki97.chatappbackendspringboot.dto.SearchedGeekDto;

import java.util.List;

public interface GeekSearcher {

    List<SearchedGeekDto> searchForGeekExcludingById(long geekId);
}
