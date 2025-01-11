package com.cheep.hallucination.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChooseKeywordRequestDto(
        @NotNull(message = "제시어를 입력해주세요.")
        String keyword
) {
}
