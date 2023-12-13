package com.fnb.bheki97.chatappbackendspringboot.service.message;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;

import java.util.List;

public interface MessageManager {

    List<MsgDto> getAllChatRoomMessagesByRoomId(long roomId);
    MsgDto sendMessage(MsgDto dto);
}
