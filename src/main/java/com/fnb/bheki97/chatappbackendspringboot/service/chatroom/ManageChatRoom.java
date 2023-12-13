package com.fnb.bheki97.chatappbackendspringboot.service.chatroom;

import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;

import java.util.List;

public interface ManageChatRoom {


    RoomDto addChatRoom(RoomDto dto);

    List<RoomDto> getAllGeekChatRoomsById(String id);
}
