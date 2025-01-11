package com.cheep.hallucination.controller;

import com.cheep.hallucination.annotation.UserId;
import com.cheep.hallucination.dto.common.ResponseDto;
import com.cheep.hallucination.usecase.user.ReadUsernameUsecase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final ReadUsernameUsecase readUsernameUsecase;

    @GetMapping("")
    public ResponseDto<?> readUsername(
            @UserId UUID userId
    ) {
        return ResponseDto.ok(readUsernameUsecase.execute(userId));
    }


}
