package com.fnb.bheki97.chatappbackendspringboot.service.message.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.entity.Message;
import com.fnb.bheki97.chatappbackendspringboot.entity.MessageStatus;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.ChatRoomRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.MessageRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageManagerService implements MessageManager {

    @Autowired
    private MessageRepository msgRepository;
    @Autowired
    ChatRoomRepository roomRepository;

    @Autowired
    private GeekRepository geekRepository;

    @Override
    public List<MsgDto> getAllChatRoomMessagesByRoomId(long roomId) {

        if(!roomRepository.existsById(roomId)){
            throw new ChatAppException("Cannot retrieve messages of non existing Chatroom");
        }

        List<Message> msgs  = msgRepository.findAllByChatRoomRoomId(roomId);

        return msgs.stream().map(this::messageToDto).toList();
    }

    @Override
    public MsgDto sendMessage(MsgDto dto) {
        System.out.println(dto);

        ChatRoom room = roomRepository.findById(dto.getRoomId()).orElseThrow(()-> new ChatAppException("Cannot send Message with an Invalid Chatroom!"));


        if (room.getParticipant1().getGeekId() != dto.getSenderId() && room.getParticipant2().getGeekId() != dto.getSenderId()) {
            throw new ChatAppException("Cannot send Message with an Invalid Sender!");
        }


        Message msg = new Message();
        msg.setChatRoom(new ChatRoom(dto.getRoomId()));
        msg.setSender(new Geek(dto.getSenderId()));
        msg.setMessage(dto.getMessage());

        MessageStatus status;
        if(dto.getStatus()==null){
             status = new MessageStatus();
        }else{
            status = dto.getStatus();
        }

        if(status.getSendDate()==null){
            status.setSendDate(new Timestamp(System.currentTimeMillis()));
        }

        msg.setStatus(status);


        return messageToDto(msgRepository.save(msg));
    }

    private MsgDto messageToDto(Message msg){

        MsgDto dto = new MsgDto();

        dto.setRoomId(msg.getChatRoom().getRoomId());
        dto.setMsgId(msg.getMessageId());
        dto.setMessage(msg.getMessage());
        dto.setSenderId(msg.getSender().getGeekId());

        dto.setStatus(msg.getStatus());
        return dto;
    }
}
