package com.fnb.bheki97.chatappbackendspringboot.controller.websocket;

import com.fnb.bheki97.chatappbackendspringboot.dto.MsgDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.OneReceiverSendMsgDto;
import com.fnb.bheki97.chatappbackendspringboot.service.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageManager msgManager;



    @MessageMapping("/chat/one2one")
    public void sendOneToOne(OneReceiverSendMsgDto dto){
        String receiver = dto.getReceiverId();

        //save to database
        dto =(OneReceiverSendMsgDto) msgManager.sendMessage(dto);

        dto.setReceiverId(receiver);

        messagingTemplate.convertAndSend("/topic/messages/"+receiver, dto);

    }

}
