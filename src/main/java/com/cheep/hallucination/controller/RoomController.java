package com.cheep.hallucination.controller;

import com.cheep.hallucination.annotation.UserId;
import com.cheep.hallucination.dto.common.ResponseDto;
import com.cheep.hallucination.dto.response.CreateRoomResponseDto;
import com.cheep.hallucination.usecase.liegent.ChooseLiegentUsecase;
import com.cheep.hallucination.usecase.room.CreateRoomUsecase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final CreateRoomUsecase createRoomUsecase;
    private final ChooseLiegentUsecase chooseLiegentUsecase;

    @PostMapping("")
    public ResponseDto<?> createRoom (
            @UserId UUID userId
    ) {
        return ResponseDto.ok(createRoomUsecase.execute(userId));
    }

    @GetMapping("/{roomId}/lier/{lierId}")
    public ResponseDto<?> getRoom (@PathVariable Long roomId, @PathVariable Long lierId) {
        return ResponseDto.ok(chooseLiegentUsecase.execute(roomId, lierId));
    }
}
