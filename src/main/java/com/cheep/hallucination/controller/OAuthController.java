package com.cheep.hallucination.controller;

import com.cheep.hallucination.constant.Constants;
import com.cheep.hallucination.dto.common.ResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.usecase.oauth.GetTokenByKakaoUseCase;
import com.cheep.hallucination.usecase.oauth.LoginByKakaoUseCase;
import com.cheep.hallucination.usecase.oauth.RedirectToKakaoLoginUseCase;
import com.cheep.hallucination.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final LoginByKakaoUseCase loginByKakaoUseCase;
    private final RedirectToKakaoLoginUseCase redirectToKakaoLoginUseCase;
    private final GetTokenByKakaoUseCase getTokenByKakaoUseCase;

    @PostMapping("/login/kakao")
    public ResponseDto<?> loginByKakao(
            HttpServletRequest request
    ) {
        String accessToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_AUTHORIZATION_HEADER));

        return ResponseDto.ok(loginByKakaoUseCase.execute(accessToken));
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<?> loginByKakao(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // redirectToKakaoLoginUseCase로 리다이렉트 URL을 받아온다.
        String redirectUrl = redirectToKakaoLoginUseCase.execute();

        // ResponseEntity로 리다이렉트 URL을 반환한다.
        return ResponseEntity.status(302).header("Location", redirectUrl).build();
    }

    @GetMapping("/login/kakao/callback")
    public ResponseDto<?> callbackByKakao(
            @RequestParam("code") String authorizationCode
    ) {
        return ResponseDto.ok(getTokenByKakaoUseCase.execute(authorizationCode));
    }
}
