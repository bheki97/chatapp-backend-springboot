package com.fnb.bheki97.chatappbackendspringboot.service.message_update.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgUpdateDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.MessageStatus;
import com.fnb.bheki97.chatappbackendspringboot.repository.MessageStatusRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.message_update.MessageUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageUpdaterImpl implements MessageUpdater {

    @Autowired
    private MessageStatusRepository msgStatusRepository;

    @Override
    public void updateReadDate(MsgUpdateDto dto) {
        MessageStatus status = msgStatusRepository.findById(dto.getStatusId()).orElse(null);
        if(status!=null && status.getReadDate()==null){
            status.setReadDate(dto.getDate());
            msgStatusRepository.save(status);
        }
    }

    @Override
    public void  updateReceivedDate(MsgUpdateDto dto) {
        MessageStatus status = msgStatusRepository.findById(dto.getStatusId()).orElse(null);

        if(status!=null && status.getReceiveDate()==null){

            status.setReceiveDate(dto.getDate());
            System.out.println(status);
            msgStatusRepository.save(status);
        }
    }
}
