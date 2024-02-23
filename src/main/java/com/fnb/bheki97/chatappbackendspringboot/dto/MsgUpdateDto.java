package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MsgUpdateDto {

    private long receiverId;
    private long msgId;
    private long statusId;
    private long roomId;
    private Timestamp date;
}
