package com.cheep.hallucination.usecase.liegent;

import com.cheep.hallucination.dto.response.ChooseLiegentResponseDto;
import java.util.UUID;

public interface ChooseLiegentUsecase {
    ChooseLiegentResponseDto execute(Long roomId, Long chooseCharacterId, UUID userId);
}
