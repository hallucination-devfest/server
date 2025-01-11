package com.cheep.hallucination.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record InterviewHistoryResponseDto(

        boolean isEmpty,
        List<ChatHistoryDto> chatHistoryList
) {
}
