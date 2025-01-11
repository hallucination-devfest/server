package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Character;
import com.cheep.hallucination.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT * FROM character_tb ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Character findRandomCharacter();
}
