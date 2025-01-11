package com.cheep.hallucination.dto.response;

import lombok.Builder;

@Builder
public record ChooseKeywordResponseDto(
        Boolean isSuccess
) {
}
