package com.cheep.hallucination.usecase.room;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.request.ChooseKeywordRequestDto;
import java.util.UUID;

@UseCase
public interface ChooseKeywordUsecase {
    Boolean execute(ChooseKeywordRequestDto requestDto, Long roomId, UUID userId);
}
