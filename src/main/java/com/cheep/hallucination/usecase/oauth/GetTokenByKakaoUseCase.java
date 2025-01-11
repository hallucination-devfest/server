package com.cheep.hallucination.usecase.oauth;

import com.cheep.hallucination.annotation.UseCase;

@UseCase
public interface GetTokenByKakaoUseCase {
    String execute(String authorizationCode);
}
