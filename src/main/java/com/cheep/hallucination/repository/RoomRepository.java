package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
