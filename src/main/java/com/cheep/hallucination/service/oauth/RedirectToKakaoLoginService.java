package com.cheep.hallucination.service.oauth;

import com.cheep.hallucination.usecase.oauth.RedirectToKakaoLoginUseCase;
import com.cheep.hallucination.util.OAuth2Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedirectToKakaoLoginService implements RedirectToKakaoLoginUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute() {
        return oAuth2Util.getKakaoRedirectUrl();
    }
}
