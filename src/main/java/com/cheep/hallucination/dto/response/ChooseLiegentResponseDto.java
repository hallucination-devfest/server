package com.cheep.hallucination.dto.response;

import lombok.Builder;

@Builder
public record ChooseLiegentResponseDto (
        Boolean isSuccess
) {
}
