package com.cheep.hallucination.usecase.auth;

import com.cheep.hallucination.annotation.UseCase;
import java.util.UUID;

@UseCase
public interface WithdrawalUseCase {
    void execute(UUID userId);
}
