package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByChatRoomRoomId(long roomId);
}
