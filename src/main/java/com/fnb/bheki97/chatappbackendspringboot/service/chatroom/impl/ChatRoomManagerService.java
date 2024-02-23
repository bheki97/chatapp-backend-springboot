package com.fnb.bheki97.chatappbackendspringboot.service.chatroom.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.GeekDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.NewRoomDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.RoomDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.entity.Message;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.ChatRoomRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.MessageRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.chatroom.ChatRoomManager;
import com.twilio.twiml.video.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomManagerService implements ChatRoomManager {

    @Autowired
    private ChatRoomRepository roomRepository;

    @Autowired
    private GeekRepository geekRepository;

    @Autowired
    private MessageRepository msgRepository;

    @Override
    public NewRoomDto addChatRoom(NewRoomDto dto) {


        if(!geekRepository.existsById(dto.getGeekId1()) || !geekRepository.existsById(dto.getGeekId2())){
            throw new ChatAppException("Cannot Create a chat room of non existing users");
        }

        ChatRoom rm;

        if(roomRepository.existsByParticipant1GeekIdAndParticipant2GeekId(dto.getGeekId1(), dto.getGeekId2())){
            rm = roomRepository.findByParticipant1GeekIdAndParticipant2GeekId(dto.getGeekId1(), dto.getGeekId2());
        }else{
            rm = new ChatRoom();
            rm.setParticipant1(new Geek(dto.getGeekId1()));
            rm.setParticipant2(new Geek(dto.getGeekId2()));
            rm  = roomRepository.save(rm);
        }

        dto.setRoomId(rm.getRoomId());

        return dto;
    }

    @Override
    public List<NewRoomDto> getAllGeekChatRoomsById(long id) {

        if(!geekRepository.existsById(id)){
            throw new ChatAppException("Cannot get Chatroom's for a non existing GEEK");
        }

        List<ChatRoom> chatRooms = roomRepository.
                findAllByParticipant1GeekIdOrParticipant2GeekId(id,id);

        return chatRooms.stream().map(this::ChatroomToDto).toList();
    }

    @Override
    public RoomDto[] getAllGeekChatRoomsByGeekId(long geekId) {
        if(!geekRepository.existsById(geekId)){
            throw new ChatAppException("Cannot get Chatroom's for a non existing GEEK");
        }

        List<RoomDto> rooms = roomRepository
                .findAllByParticipant1GeekIdOrParticipant2GeekId(geekId,geekId)
                .stream()
                .map((ChatRoom room) -> roomToDto(room,geekId)).toList();

        RoomDto[] arr = new RoomDto[rooms.size()];

        return rooms.toArray(arr);
    }

    private RoomDto roomToDto(ChatRoom room,long geekId) {
        RoomDto dto = new RoomDto();
        List<MsgDto> messages = msgRepository.findAllByChatRoomRoomId(room.getRoomId())
                .stream().map(MsgDto::new).toList();
        dto.setRoomId(room.getRoomId());
        dto.setMessages(messages);

        if(geekId==room.getParticipant1().getGeekId()){
            dto.setReceiver(new GeekDto(room.getParticipant2()));
        }else{
            dto.setReceiver(new GeekDto(room.getParticipant1()));
        }


        return dto;

    }

    private NewRoomDto ChatroomToDto(ChatRoom room){
        NewRoomDto dto = new NewRoomDto();
        dto.setRoomId(room.getRoomId());
        dto.setGeekId1(room.getParticipant1().getGeekId());
        dto.setGeekId2(room.getParticipant2().getGeekId());

        return dto;
    }
}
