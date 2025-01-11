package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Query(value = "SELECT * FROM keyword_tb ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Keyword findRandomKeyword();
}
