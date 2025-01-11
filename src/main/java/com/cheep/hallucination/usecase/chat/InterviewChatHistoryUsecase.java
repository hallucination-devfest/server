package com.cheep.hallucination.usecase.chat;

import com.cheep.hallucination.dto.response.InterviewHistoryResponseDto;

public interface InterviewChatHistoryUsecase {

    InterviewHistoryResponseDto execute(Long roomId, Long characterId);
}
