package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Character;
import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.domain.RoomInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomInterviewRepository extends JpaRepository<RoomInterview, Long> {

    @Query("SELECT ri FROM RoomInterview ri WHERE ri.room = :room AND ri.character = :character")
    Optional<List<RoomInterview>> findByRoomAndCharacter(Room room, Character character);

}
