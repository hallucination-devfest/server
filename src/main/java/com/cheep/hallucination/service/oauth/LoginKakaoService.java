package com.cheep.hallucination.service.oauth;

import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.domain.type.EProvider;
import com.cheep.hallucination.domain.type.ERole;
import com.cheep.hallucination.dto.response.JwtTokenDto;
import com.cheep.hallucination.dto.response.LoginResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.util.JwtUtil;
import com.cheep.hallucination.util.OAuth2Util;
import com.cheep.hallucination.util.PasswordUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginKakaoService {
    private final OAuth2Util oauthUtil;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginResponseDto socialSignIn(String authCode) {

        String kakaoAccessToken = oauthUtil.getKakaoAccessToken(authCode);
        Map<String, String> userInfo = oauthUtil.getKakaoUserInformation(kakaoAccessToken);

        String serialId = userInfo.get("id");
        UserRepository.UserSecurityForm userSecurityForm = userRepository.findFormBySerialIdAndProvider(serialId, EProvider.KAKAO)
                .orElseGet(() -> {
                    User user = userRepository.save(
                            User.builder()
                                    .serialId(serialId)
                                    .provider(EProvider.KAKAO)
                                    .role(ERole.USER)
                                    .nickname(userInfo.get("nickname"))
                                    .email(userInfo.get("email"))
                                    .password(bCryptPasswordEncoder.encode(PasswordUtil.generateRandomPassword())).build()
                    );
                    return UserRepository.UserSecurityForm.of(user);
                });

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(
                userSecurityForm.getId(),
                userSecurityForm.getRole()
        );
        User user = userRepository.findById(userSecurityForm.getId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        userRepository.updateRefreshToken(userSecurityForm.getId(), jwtTokenDto.refreshToken());

        return LoginResponseDto.builder()
                .jwtTokenDto(jwtTokenDto)
                .username(user.getNickname())
                .build();
    }
}
