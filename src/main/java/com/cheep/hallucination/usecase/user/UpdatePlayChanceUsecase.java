package com.cheep.hallucination.usecase.user;

import com.cheep.hallucination.annotation.UseCase;
import com.cheep.hallucination.annotation.UserId;
import java.util.UUID;

@UseCase
public interface UpdatePlayChanceUsecase {
    Boolean execute(UUID userId);
}
