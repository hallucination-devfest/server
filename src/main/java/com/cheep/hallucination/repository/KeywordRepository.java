package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
