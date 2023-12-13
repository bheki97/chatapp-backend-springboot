package com.fnb.bheki97.chatappbackendspringboot.service.message.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.entity.Message;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.ChatRoomRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.repository.MessageRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return msgs.stream().map(this::MessageToDto).toList();
    }

    @Override
    public MsgDto sendMessage(MsgDto dto) {


        if(!geekRepository.existsById(dto.getSenderId())){
            throw new ChatAppException("Cannot send Message with an Invalid Sender!");
        }
        if(!roomRepository.existsById(dto.getRoomId())){
            throw new ChatAppException("Cannot send Message with an Invalid Chatroom!");
        }


        Message msg = new Message();
        msg.setChatRoom(new ChatRoom(dto.getRoomId()));
        msg.setSender(new Geek(dto.getSenderId()));
        msg.setMessage(msg.getMessage());
        msg.setSendDate(dto.getSendDate());


        msg = msgRepository.save(msg);

        dto.setMsgId(msg.getMessageId());
        dto.setSendDate(msg.getSendDate());

        return dto;
    }

    private MsgDto MessageToDto(Message msg){

        MsgDto dto = new MsgDto();

        dto.setRoomId(msg.getMessageId());
        dto.setMsgId(msg.getMessageId());
        dto.setMessage(msg.getMessage());
        dto.setSenderId(msg.getSender().getUsername());
        dto.setSendDate(msg.getSendDate());
        return dto;
    }
}
