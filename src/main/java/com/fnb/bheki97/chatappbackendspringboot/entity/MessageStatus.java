package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "message_statuses")
public class MessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "received_date")
    private Timestamp receiveDate;
    @Column(name = "read_date")
    private Timestamp readDate;

    @Column(name = "send_date")
    private Timestamp sendDate;
}
