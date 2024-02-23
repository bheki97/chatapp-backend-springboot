package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private Long roomId;
    private List<MsgDto> messages;
    private GeekDto receiver;

}
