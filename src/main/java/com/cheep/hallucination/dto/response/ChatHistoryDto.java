package com.cheep.hallucination.dto.response;

import lombok.Builder;

@Builder
public record ChatHistoryDto(
        Long id,
        String question,
        String answer
) {
}
