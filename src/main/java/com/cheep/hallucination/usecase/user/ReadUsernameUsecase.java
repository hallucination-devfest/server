package com.cheep.hallucination.usecase.user;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.response.UserInfoResponseDto;
import java.util.UUID;

@UseCase
public interface ReadUsernameUsecase {
    UserInfoResponseDto execute(UUID userId);
}
