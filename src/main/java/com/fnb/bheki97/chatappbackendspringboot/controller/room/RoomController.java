package com.fnb.bheki97.chatappbackendspringboot.controller.room;

import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;
import com.fnb.bheki97.chatappbackendspringboot.service.chatroom.impl.ManageChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    private ManageChatRoomService chatRoomService;

    @PostMapping
    public RoomDto addNewRoom(@RequestBody RoomDto dto){
        return chatRoomService.addChatRoom(dto);
    }

    @GetMapping("/{id}")
    public List<RoomDto> getAllGeekChatroom(@PathVariable String id){

        return chatRoomService.getAllGeekChatRoomsById(id);
    }
}
