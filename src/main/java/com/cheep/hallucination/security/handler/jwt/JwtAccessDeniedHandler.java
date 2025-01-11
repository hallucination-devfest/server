package com.cheep.hallucination.security.handler.jwt;

import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.security.handler.common.AbstractAuthenticationFailureHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler extends AbstractAuthenticationFailureHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        setErrorResponse(response, ErrorCode.ACCESS_DENIED);
    }
}

