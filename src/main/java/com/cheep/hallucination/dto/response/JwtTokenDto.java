package com.cheep.hallucination.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record JwtTokenDto(
        @NotNull(message = "AccessToken은 null이 될 수 없습니다.")
        String accessToken,
        @NotNull(message = "RefreshToken은 null이 될 수 없습니다.")
        String refreshToken
) {
    @Builder
    public JwtTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
