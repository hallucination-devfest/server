package com.cheep.hallucination.controller;

import com.cheep.hallucination.dto.common.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    @GetMapping
    public ResponseDto<?> getHealth() {
        return ResponseDto.ok("Healthy");
    }
}
