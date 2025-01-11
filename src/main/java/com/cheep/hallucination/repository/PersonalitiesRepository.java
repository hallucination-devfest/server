package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Personalities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalitiesRepository extends JpaRepository<Personalities, Long> {
}
