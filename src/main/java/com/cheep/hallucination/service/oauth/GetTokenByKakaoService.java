package com.cheep.hallucination.service.oauth;

import com.cheep.hallucination.usecase.oauth.GetTokenByKakaoUseCase;
import com.cheep.hallucination.util.OAuth2Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTokenByKakaoService implements GetTokenByKakaoUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute(String authorizationCode) {
        return oAuth2Util.getKakaoAccessToken(authorizationCode);
    }
}
