package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class OneReceiverSendMsgDto extends MsgDto{

    private String receiverId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OneReceiverSendMsgDto that = (OneReceiverSendMsgDto) o;
        return Objects.equals(receiverId, that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), receiverId);
    }
}
