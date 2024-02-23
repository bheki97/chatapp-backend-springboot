package com.fnb.bheki97.chatappbackendspringboot.service.message_update;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgUpdateDto;

public interface MessageUpdater {


    void updateReadDate(MsgUpdateDto dto);
    void updateReceivedDate(MsgUpdateDto dto);
}
