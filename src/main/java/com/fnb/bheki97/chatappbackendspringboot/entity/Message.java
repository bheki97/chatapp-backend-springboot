package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "msg_id")
    private Long messageId;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private Geek sender;
    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    private String message;
    @OneToOne
    @JoinColumn(name = "status_id")
    private MessageStatus status;



}
