package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.domain.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.user = :user ORDER BY r.createdAt DESC LIMIT 1")
    Optional<Room> findMostRecentByUserId(@Param("user") User user);

}
