package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {
}
