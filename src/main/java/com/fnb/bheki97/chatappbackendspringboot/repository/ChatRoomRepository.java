package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    List<ChatRoom> findAllByParticipant1UsernameOrParticipant2Username(String username1,String username2);

    boolean existsByParticipant1UsernameAndParticipant2Username(String username1,String username2);
    ChatRoom findByParticipant1UsernameAndParticipant2Username(String username1,String username2);
}
