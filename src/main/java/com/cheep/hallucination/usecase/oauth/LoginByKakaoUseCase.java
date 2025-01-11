package com.cheep.hallucination.usecase.oauth;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.response.JwtTokenDto;

@UseCase
public interface LoginByKakaoUseCase {
    JwtTokenDto execute(String accessToken);
}
