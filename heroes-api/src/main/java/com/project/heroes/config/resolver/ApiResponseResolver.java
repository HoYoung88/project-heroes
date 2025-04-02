package com.project.heroes.config.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.heroes.base.ApiResponse;
import java.lang.reflect.ParameterizedType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiResponseResolver implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        try {
            Class<?> type = getParameterClass(returnType);
            return !ApiResponse.class.isAssignableFrom(type);
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    private Class<?> getParameterClass(MethodParameter returnType)
        throws ClassCastException, ArrayIndexOutOfBoundsException {
        Class<?> type = returnType.getParameterType();

        if (ResponseEntity.class.isAssignableFrom(type)) {
            ParameterizedType parameterizedType = (ParameterizedType) returnType.getGenericParameterType();
            type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return type;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (MappingJackson2HttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            if (body instanceof ApiResponse<?>) {
                return body;
            } else if (body instanceof ProblemDetail problemDetail) {
                return ApiResponse.body(String.valueOf(problemDetail.getStatus()), problemDetail.getDetail());
            } else {
                return ApiResponse.body(body);
            }
        }
        return writeBodyAsJson(body, response);
    }

    private Object writeBodyAsJson(Object body, ServerHttpResponse response) {
        try {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return this.objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
