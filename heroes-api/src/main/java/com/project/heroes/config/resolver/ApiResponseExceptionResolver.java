package com.project.heroes.config.resolver;

import com.project.heroes.api.exception.NexonOpenApiErrorCode;
import com.project.heroes.api.exception.NexonOpenApiException;
import com.project.heroes.character.exception.CharacterServiceException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiResponseExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NexonOpenApiException.class)
    protected ProblemDetail handleNexonOpenApiException(NexonOpenApiException ex) {
        log.error("Nexon Open API Exception: {}", ex.getMessage(), ex);
        
        NexonOpenApiErrorCode nexonOpenApiErrorCode = ex.getErrorCode();
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(nexonOpenApiErrorCode.getCode());
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatusCode);
        problemDetail.setDetail(nexonOpenApiErrorCode.getDescription());
        problemDetail.setTitle("Nexon Open API Error");

        return problemDetail;
    }

    @ExceptionHandler(CharacterServiceException.class)
    protected ProblemDetail handleCharacterServiceException(CharacterServiceException ex) {
        log.warn("Character Service Exception: {}", ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("Character Service Error");

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("Method Argument Type Mismatch: {}", ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(String.format("'%s' 파라미터의 타입이 올바르지 않습니다.", ex.getName()));
        problemDetail.setTitle("Invalid Parameter Type");

        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ProblemDetail handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("Constraint Violation: {}", ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("Validation Error");

        return problemDetail;
    }

    @Override
    protected ProblemDetail handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        org.springframework.http.HttpHeaders headers,
        HttpStatusCode status,
        org.springframework.web.context.request.WebRequest request) {
        
        log.warn("Method Argument Not Valid: {}", ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Validation Error");
        
        StringBuilder detail = new StringBuilder("입력값 검증 실패: ");
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                detail.append(String.format("[%s] %s; ", fieldError.getField(), error.getDefaultMessage()));
            } else {
                detail.append(error.getDefaultMessage()).append("; ");
            }
        });
        problemDetail.setDetail(detail.toString());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    protected ProblemDetail handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setDetail("서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        problemDetail.setTitle("Internal Server Error");

        return problemDetail;
    }
}
