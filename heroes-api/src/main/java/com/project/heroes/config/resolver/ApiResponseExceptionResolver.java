package com.project.heroes.config.resolver;

import com.project.heroes.api.exception.NexonOpenApiErrorCode;
import com.project.heroes.api.exception.NexonOpenApiException;
import com.project.heroes.api.response.error.NexonOpenApiErrorResponse.Error;
import com.project.heroes.character.exception.CharacterServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiResponseExceptionResolver extends ResponseEntityExceptionHandler {

    private final HttpStatusCode badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(NexonOpenApiException.class)
    protected ProblemDetail handleNexonOpenApiException(NexonOpenApiException ex) {
        NexonOpenApiErrorCode nexonOpenApiErrorCode = ex.getErrorCode();
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(nexonOpenApiErrorCode.getCode());
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatusCode);
        problemDetail.setDetail(nexonOpenApiErrorCode.getDescription());

        return problemDetail;
    }

    @ExceptionHandler(CharacterServiceException.class)
    protected ProblemDetail handleCharacterServiceException(CharacterServiceException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(badRequest);
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
}
