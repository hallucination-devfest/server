package com.cheep.hallucination.usecase.oauth;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.dto.response.JwtTokenDto;
import com.cheep.hallucination.dto.response.LoginResponseDto;

@UseCase
public interface LoginByKakaoUseCase {
    LoginResponseDto execute(String accessToken);
}
