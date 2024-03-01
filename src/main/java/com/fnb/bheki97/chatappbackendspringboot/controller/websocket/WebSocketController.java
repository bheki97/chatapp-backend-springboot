package com.fnb.bheki97.chatappbackendspringboot.controller.websocket;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.MsgUpdateDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.OneReceiverSendMsgDto;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import com.fnb.bheki97.chatappbackendspringboot.service.message_update.MessageUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageManager msgManager;
    @Autowired
    private MessageUpdater msgUpdater;


    @MessageMapping("/chat/msg-update/received/{senderId}")
    public void notifyReceivedMessage(@DestinationVariable long senderId,@Payload MsgUpdateDto dto){
        msgUpdater.updateReceivedDate(dto);
        System.out.println(dto);
        messagingTemplate.convertAndSend("/topic/received-msg-statuses/"+senderId, dto);
    }
    @MessageMapping("/chat/msg-update/read/{senderId}")
    public void notifyReadMessage(@DestinationVariable long senderId, @Payload MsgUpdateDto dto){
        msgUpdater.updateReadDate(dto);
        System.out.println(dto);
        messagingTemplate.convertAndSend("/topic/read-msg-statuses/"+senderId, dto);
    }

    @MessageMapping("/chat/one2one")
    public void sendOneToOne(@Payload OneReceiverSendMsgDto dto){
        long receiver = dto.getReceiverId();

        //save to database
        MsgDto msgDto = msgManager.sendMessage(dto);
        dto.setMsgId(msgDto.getMsgId());
        dto.setStatus(msgDto.getStatus());

        dto.setReceiverId(receiver);
        System.out.println(dto);
        messagingTemplate.convertAndSend("/topic/own-messages/"+dto.getSenderId(), dto);

        messagingTemplate.convertAndSend("/topic/messages/"+receiver, dto);


    }

}
