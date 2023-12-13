package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus,Long> {
}
