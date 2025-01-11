package com.cheep.hallucination.intercepter.pre;

import com.cheep.hallucination.annotation.UserId;
import com.cheep.hallucination.constant.Constants;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import java.util.UUID;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserIDArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UUID.class)
                && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        final Object userIdObj = webRequest.getAttribute(Constants.USER_ID_ATTRIBUTE_NAME, WebRequest.SCOPE_REQUEST);

        if (userIdObj == null) {
            throw new CommonException(ErrorCode.INVALID_HEADER_ERROR);
        }

        return UUID.fromString((String) userIdObj);
    }
}
