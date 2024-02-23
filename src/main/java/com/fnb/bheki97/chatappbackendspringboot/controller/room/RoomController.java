package com.fnb.bheki97.chatappbackendspringboot.controller.room;

import com.fnb.bheki97.chatappbackendspringboot.dto.NewRoomDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;
import com.fnb.bheki97.chatappbackendspringboot.service.chatroom.impl.ChatRoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    private ChatRoomManagerService chatRoomService;

    @PostMapping
    public NewRoomDto addNewRoom(@RequestBody NewRoomDto dto){
        return chatRoomService.addChatRoom(dto);
    }

    @GetMapping("/geek/{geekId}")
    public RoomDto[] getAllGeekRooms(@PathVariable long geekId){
        return chatRoomService.getAllGeekChatRoomsByGeekId(geekId);
    }

    @GetMapping("/{id}")
    public List<NewRoomDto> getAllGeekChatroom(@PathVariable long id){

        return chatRoomService.getAllGeekChatRoomsById(id);
    }
}
