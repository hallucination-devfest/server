package com.cheep.hallucination.usecase.auth;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.response.JwtTokenDto;

@UseCase
public interface ReissueTokenUseCase {
    JwtTokenDto execute(String refreshToken);
}
