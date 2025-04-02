package com.project.heroes.base;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(staticName = "body")
public class ApiResponse<T> {

    private final String code;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> body(T data) {
        return ApiResponse.body("0000", "SUCCESS", data);
    }

    public static ApiResponse<Void> body(String code, String message) {
        return ApiResponse.body(code, message, null);
    }

}
