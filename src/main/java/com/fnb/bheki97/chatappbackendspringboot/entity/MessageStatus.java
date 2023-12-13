package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "message_statuses") public class MessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
    @Column(name = "received_date")
    private Timestamp receiveDate;
    @Column(name = "read_date")
    private Timestamp readDate;
}
