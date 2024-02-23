package com.fnb.bheki97.chatappbackendspringboot.repository;

import com.fnb.bheki97.chatappbackendspringboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    List<ChatRoom> findAllByParticipant1GeekIdOrParticipant2GeekId(long username1, long username2);

    boolean existsByParticipant1GeekIdAndParticipant2GeekId(long geekId1,long geekId2);
    ChatRoom findByParticipant1GeekIdAndParticipant2GeekId(long geekId1, long geekId2);

}
