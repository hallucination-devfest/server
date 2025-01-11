package com.cheep.hallucination.usecase.room;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.response.CreateRoomResponseDto;
import java.util.UUID;

@UseCase
public interface CreateRoomUsecase {
    CreateRoomResponseDto execute(UUID userId);
}
