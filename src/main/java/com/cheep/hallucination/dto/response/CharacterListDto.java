package com.cheep.hallucination.dto.response;

import lombok.Builder;

@Builder
public record CharacterListDto(
        Long characterId,
        String characterName
) {
}
