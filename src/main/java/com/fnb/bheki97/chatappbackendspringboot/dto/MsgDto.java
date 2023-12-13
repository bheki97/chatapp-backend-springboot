package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MsgDto {

    private long msgId;
    private long roomId;
    private String senderId;
    private String message;
    private Timestamp sendDate;

}
