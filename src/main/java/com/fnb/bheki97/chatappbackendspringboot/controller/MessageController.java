package com.fnb.bheki97.chatappbackendspringboot.controller;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageManager msgManager;


    @GetMapping("/{id}")
    public List<MsgDto> retrievingAllChatRoomMessages(@PathVariable long id){
        return msgManager.getAllChatRoomMessagesByRoomId(id);
    }

}
