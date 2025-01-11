package com.cheep.hallucination.service.auth;

import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.auth.WithdrawalUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawalService implements WithdrawalUseCase {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void execute(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        userRepository.delete(user);
    }
}
