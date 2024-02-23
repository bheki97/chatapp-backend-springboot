package com.fnb.bheki97.chatappbackendspringboot.controller.search_geek;

import com.fnb.bheki97.chatappbackendspringboot.dto.SearchedGeekDto;
import com.fnb.bheki97.chatappbackendspringboot.service.geeksearcher.GeekSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/geek")
public class SearchGeekController {

    @Autowired
    private GeekSearcher geekSearcher;

    @GetMapping("/search/{id}")
    public List<SearchedGeekDto> returnSearchedGeeks(@PathVariable long id){
        return geekSearcher.searchForGeekExcludingById(id);
    }
}
