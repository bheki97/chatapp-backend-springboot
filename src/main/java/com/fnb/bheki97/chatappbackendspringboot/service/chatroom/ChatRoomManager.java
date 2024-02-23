package com.fnb.bheki97.chatappbackendspringboot.service.chatroom;

import com.fnb.bheki97.chatappbackendspringboot.dto.NewRoomDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;

import java.util.List;

public interface ChatRoomManager {


    NewRoomDto addChatRoom(NewRoomDto dto);

    List<NewRoomDto> getAllGeekChatRoomsById(long id);
    RoomDto[] getAllGeekChatRoomsByGeekId(long geekId);
}
