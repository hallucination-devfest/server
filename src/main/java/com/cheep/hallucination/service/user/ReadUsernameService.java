package com.cheep.hallucination.service.user;

import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.dto.response.UserInfoResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.user.ReadUsernameUsecase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadUsernameService implements ReadUsernameUsecase {
    private final UserRepository userRepository;

    @Override
    public UserInfoResponseDto execute(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return UserInfoResponseDto.builder()
                .username(user.getNickname())
                .build();
    }

}
