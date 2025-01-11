package com.cheep.hallucination.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record CreateRoomResponseDto(
        Integer round,

        String category,

        Integer chatCount,

        List<CharacterListDto> characterList
) {
}
