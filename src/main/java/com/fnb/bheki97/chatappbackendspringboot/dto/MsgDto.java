package com.fnb.bheki97.chatappbackendspringboot.dto;

import com.fnb.bheki97.chatappbackendspringboot.entity.Message;
import com.fnb.bheki97.chatappbackendspringboot.entity.MessageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgDto {

    private long msgId;
    private long roomId;
    private long senderId;
    private String message;
    private MessageStatus status;

    public MsgDto(Message msg) {
        this.msgId = msg.getMessageId();
        this.roomId = msg.getChatRoom().getRoomId();
        this.status = msg.getStatus();
        this.message = msg.getMessage();
        this.senderId = msg.getSender().getGeekId();
    }
}
