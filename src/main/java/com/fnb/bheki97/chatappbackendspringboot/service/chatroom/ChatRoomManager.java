package com.fnb.bheki97.chatappbackendspringboot.service.chatroom;

import com.fnb.bheki97.chatappbackendspringboot.dto.NewRoomDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;

import java.util.List;

public interface ChatRoomManager {


    NewRoomDto addChatRoom(NewRoomDto dto);
    RoomDto startNewConversation(NewRoomDto dto);
    RoomDto getConversation(long roomId,long senderId);
    List<NewRoomDto> getAllGeekChatRoomsById(long id);
    RoomDto[] getAllGeekChatRoomsByGeekId(long geekId);
}
