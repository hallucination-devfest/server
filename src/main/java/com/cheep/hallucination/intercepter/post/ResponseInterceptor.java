package com.cheep.hallucination.intercepter.post;

import com.cheep.hallucination.dto.common.ResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "org.goormthon.beotkkotthon.rebook")
public class ResponseInterceptor implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (returnType.getParameterType() == ResponseDto.class) {
            HttpStatus status = ((ResponseDto<?>) body).httpStatus();
            response.setStatusCode(status);
        }

        return body;
    }
}
