package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id")
    private Long roomId;
    @ManyToOne
    @JoinColumn(name = "participant_id1")
    private Geek participant1;

    @ManyToOne
    @JoinColumn(name = "participant_id2")
    private Geek participant2;

    public ChatRoom() {
    }

    public ChatRoom(Long roomId) {
        this.roomId = roomId;
    }
}
