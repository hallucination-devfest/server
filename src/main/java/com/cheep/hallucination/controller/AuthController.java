package com.cheep.hallucination.controller;

import com.cheep.hallucination.annotation.UserId;
import com.cheep.hallucination.constant.Constants;
import com.cheep.hallucination.dto.common.ResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.usecase.auth.ReissueTokenUseCase;
import com.cheep.hallucination.usecase.auth.WithdrawalUseCase;
import com.cheep.hallucination.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final ReissueTokenUseCase reissueTokenUseCase;
    private final WithdrawalUseCase withdrawalUseCase;


    @PostMapping("/reissue")
    public ResponseDto<?> reissueToken(
            HttpServletRequest request
    ) {
        String refreshToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_AUTHORIZATION_HEADER));

        return ResponseDto.ok(reissueTokenUseCase.execute(refreshToken));
    }

    @PostMapping("/withdrawal")
    public ResponseDto<?> withdrawal(
            @UserId UUID userId
    ) {
        withdrawalUseCase.execute(userId);

        return ResponseDto.ok(null);
    }

}
