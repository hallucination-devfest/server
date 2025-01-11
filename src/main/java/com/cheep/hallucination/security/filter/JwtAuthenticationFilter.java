package com.cheep.hallucination.security.filter;

import com.cheep.hallucination.constant.Constants;
import com.cheep.hallucination.domain.type.ERole;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.security.info.UserPrincipal;
import com.cheep.hallucination.security.usecase.LoadUserPrincipalByIdUseCase;
import com.cheep.hallucination.util.HeaderUtil;
import com.cheep.hallucination.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final LoadUserPrincipalByIdUseCase loadUserPrincipalByIdUseCase;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_HEADER_ERROR));
        Claims claims = jwtUtil.validateToken(token);

        UUID userId = UUID.fromString(claims.get(Constants.USER_ID_CLAIM_NAME, String.class));
        ERole role = ERole.fromName(claims.get(Constants.USER_ROLE_CLAIM_NAME, String.class));

        UserPrincipal userPrincipal = (UserPrincipal) loadUserPrincipalByIdUseCase.execute(userId);

        if (!userPrincipal.getRole().equals(role)) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userPrincipal, null, userPrincipal.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return Constants.NO_NEED_AUTH_URLS.stream()
                .anyMatch(pattern -> antPathMatcher.match(pattern, requestURI));
    }
}

