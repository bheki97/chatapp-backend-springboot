package com.fnb.bheki97.chatappbackendspringboot.controller.room;

import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;
import com.fnb.bheki97.chatappbackendspringboot.service.chatroom.impl.ManageChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    private ManageChatRoomService chatRoomService;

    @PostMapping
    public RoomDto addNewRoom(@RequestBody RoomDto dto){
        return chatRoomService.addChatRoom(dto);
    }
}
