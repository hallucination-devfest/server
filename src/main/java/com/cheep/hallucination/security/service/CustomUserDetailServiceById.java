package com.cheep.hallucination.security.service;

import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.security.info.UserPrincipal;
import com.cheep.hallucination.security.usecase.LoadUserPrincipalByIdUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceById implements UserDetailsService, LoadUserPrincipalByIdUseCase {
    private final UserRepository userRepository;

    /* TODO: 만약 사용할 시 구현 필요이 필요하지만 사용하지 않으므로 주석 처리함 */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails execute(UUID userId) {
        UserRepository.UserSecurityForm userSecurityForm = userRepository.findFormById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        if(userSecurityForm.getChancePlay() == 0)
            throw new CommonException(ErrorCode.LAKE_OF_CHANCE);

        return UserPrincipal.create(userSecurityForm);
    }
}
