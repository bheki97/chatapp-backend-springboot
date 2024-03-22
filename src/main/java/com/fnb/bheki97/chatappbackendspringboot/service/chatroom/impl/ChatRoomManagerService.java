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
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import com.fnb.bheki97.chatappbackendspringboot.service.message.impl.MessageManagerService;
import com.twilio.twiml.video.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomManagerService implements ChatRoomManager {

    @Autowired
    private ChatRoomRepository roomRepository;

    @Autowired
    private GeekRepository geekRepository;

    @Autowired
    private MessageRepository msgRepository;
    @Autowired
    private MessageManager messageManagerService;

    @Override
    public NewRoomDto addChatRoom(NewRoomDto dto) {


        ChatRoom rm = createRoom(dto);

        dto.setRoomId(rm.getRoomId());

        return dto;
    }

    private ChatRoom createRoom(NewRoomDto dto){
        System.out.println(dto);
        if(!geekRepository.existsById(dto.getGeekId1()) || !geekRepository.existsById(dto.getGeekId2())){
            throw new ChatAppException("Cannot Create a chat room of non existing users");
        }

        ChatRoom rm;

        if(roomRepository.existsByParticipant1GeekIdAndParticipant2GeekId(dto.getGeekId1(), dto.getGeekId2())){
            rm = roomRepository.findByParticipant1GeekIdAndParticipant2GeekId(dto.getGeekId1(), dto.getGeekId2());
        }else{
            rm = new ChatRoom();
            rm.setParticipant1(geekRepository.findById(dto.getGeekId1()).get());
            rm.setParticipant2(geekRepository.findById(dto.getGeekId2()).get());
            rm  = roomRepository.save(rm);
        }

        return rm;
    }

    @Override
    public RoomDto startNewConversation(NewRoomDto dto) {

        ChatRoom rm = roomRepository.findById(createRoom(dto).getRoomId()).get();
        System.out.println("Created Room: "+rm);

        MsgDto msg = dto.getMsg();
        msg.setRoomId(rm.getRoomId());
        msg = messageManagerService.sendMessage(msg);

        RoomDto room = roomToDto(rm, msg.getSenderId());


        if(room.getMessages()==null ||room.getMessages().isEmpty()){
            List<MsgDto> msgs = new ArrayList();
            msgs.add(msg);
            room.setMessages(msgs);
        }

        
        return room;
    }

    @Override
    public RoomDto getConversation(long roomId,long senderId) {
        ChatRoom room = roomRepository.findById(roomId)
                .orElseThrow(() ->new ChatAppException("Chat room does not exist"));

        if(room.getParticipant1().getGeekId()==senderId ||
                room.getParticipant2().getGeekId()==senderId ){
            return roomToDto(room,senderId);
        }

        throw new ChatAppException("Geek not part of the Chatroom");
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
            System.out.println(room.getParticipant2());
            dto.setReceiver(new GeekDto(room.getParticipant2()));
        }else{
            dto.setReceiver(new GeekDto(room.getParticipant1()));
            System.out.println(room.getParticipant1());
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
