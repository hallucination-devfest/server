package com.cheep.hallucination.usecase.oauth;

import com.cheep.hallucination.annotation.UseCase;

@UseCase
public interface RedirectToKakaoLoginUseCase {
    String execute();
}
