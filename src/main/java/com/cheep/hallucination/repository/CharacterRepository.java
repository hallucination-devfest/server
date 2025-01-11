package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
