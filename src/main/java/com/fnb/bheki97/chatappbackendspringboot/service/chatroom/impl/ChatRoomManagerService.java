package com.fnb.bheki97.chatappbackendspringboot.service.chatroom.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.ChatRoomRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.chatroom.ChatRoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomManagerService implements ChatRoomManager {

    @Autowired
    private ChatRoomRepository roomRepository;

    @Autowired
    private GeekRepository geekRepository;

    @Override
    public RoomDto addChatRoom(RoomDto dto) {

        if(!geekRepository.existsById(dto.getUsername1()) || !geekRepository.existsById(dto.getUsername2())){
            throw new ChatAppException("Cannot Create a chat room of non existing users");
        }

        ChatRoom rm;
        if(roomRepository.existsByParticipant1UsernameAndParticipant2Username(dto.getUsername1(), dto.getUsername2())){
            rm = roomRepository.findByParticipant1UsernameAndParticipant2Username(dto.getUsername1(), dto.getUsername2());
        }else{
            rm = new ChatRoom();
            rm.setParticipant1(new Geek(dto.getUsername1()));
            rm.setParticipant2(new Geek(dto.getUsername2()));
            rm  = roomRepository.save(rm);
        }

        dto.setRoomId(rm.getRoomId());

        return dto;
    }

    @Override
    public List<RoomDto> getAllGeekChatRoomsById(String id) {

        if(!geekRepository.existsById(id)){
            throw new ChatAppException("Cannot get Chatroom's for a non existing GEEK");
        }

        List<ChatRoom> chatRooms = roomRepository.
                findAllByParticipant1UsernameOrParticipant2Username(id,id);

        return chatRooms.stream().map(this::ChatroomToDto).toList();
    }

    private RoomDto ChatroomToDto(ChatRoom room){
        RoomDto dto = new RoomDto();
        dto.setRoomId(room.getRoomId());
        dto.setUsername1(room.getParticipant1().getUsername());
        dto.setUsername2(room.getParticipant2().getUsername());

        return dto;
    }
}
