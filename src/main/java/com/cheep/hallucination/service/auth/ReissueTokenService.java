package com.cheep.hallucination.service.auth;

import com.cheep.hallucination.constant.Constants;
import com.cheep.hallucination.dto.response.JwtTokenDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.auth.ReissueTokenUseCase;
import com.cheep.hallucination.util.JwtUtil;
import io.jsonwebtoken.Claims;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueTokenService implements ReissueTokenUseCase {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public JwtTokenDto execute(String refreshToken) {
        UUID userId;

        try {
            Claims claims = jwtUtil.validateToken(refreshToken);
            userId = UUID.fromString(claims.get(Constants.USER_ID_CLAIM_NAME, String.class));
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        UserRepository.UserSecurityForm userSecurityForm = userRepository.findFormByIdAndRefreshToken(userId, refreshToken)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(
                userSecurityForm.getId(),
                userSecurityForm.getRole()
        );

        userRepository.updateRefreshToken(userSecurityForm.getId(), jwtTokenDto.refreshToken());

        return jwtTokenDto;
    }
}
