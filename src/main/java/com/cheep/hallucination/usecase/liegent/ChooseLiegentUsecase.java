package com.cheep.hallucination.usecase.liegent;

import com.cheep.hallucination.dto.response.ChooseLiegentResponseDto;

public interface ChooseLiegentUsecase {
    ChooseLiegentResponseDto execute(Long roomId, Long chooseCharacterId);
}
