package com.cheep.hallucination.security.usecase;

import com.cheep.hallucination.annotation.UseCase;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;

@UseCase
public interface LoadUserPrincipalByIdUseCase {

    UserDetails execute(UUID userId, String redirectURI);
}
