package com.cheep.hallucination.repository;

import com.cheep.hallucination.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
