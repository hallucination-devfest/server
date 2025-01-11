package com.cheep.hallucination.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDto(
        JwtTokenDto jwtTokenDto,
        String username
) {
}
