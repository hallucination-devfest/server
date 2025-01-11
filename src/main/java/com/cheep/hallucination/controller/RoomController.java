package com.cheep.hallucination.controller;

import com.cheep.hallucination.annotation.UserId;
import com.cheep.hallucination.dto.common.ResponseDto;
import com.cheep.hallucination.dto.response.CreateRoomResponseDto;
import com.cheep.hallucination.usecase.room.CreateRoomUsecase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final CreateRoomUsecase createRoomUsecase;

    @PostMapping("")
    public ResponseDto<?> createRoom (
            @UserId UUID userId
    ) {
        return ResponseDto.ok(createRoomUsecase.execute(userId));
    }
}
