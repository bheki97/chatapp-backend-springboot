package com.fnb.bheki97.chatappbackendspringboot.controller.message;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageManager msgManager;

    @PostMapping
    public MsgDto sendMessage(@RequestBody MsgDto dto){
        return msgManager.sendMessage(dto);
    }


    @GetMapping("/room/{id}")
    public List<MsgDto> retrievingAllChatRoomMessages(@PathVariable long id){
        return msgManager.getAllChatRoomMessagesByRoomId(id);
    }

}
