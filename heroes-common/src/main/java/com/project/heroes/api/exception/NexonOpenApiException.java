package com.project.heroes.api.exception;

import lombok.Getter;

@Getter
public class NexonOpenApiException extends RuntimeException {
    private final NexonOpenApiErrorCode errorCode;

    public NexonOpenApiException(NexonOpenApiErrorCode errorCode, String message) {
        super("[" + errorCode.name() + "] " + message);
        this.errorCode = errorCode;
    }

    public boolean isOpenApi00004() {
        return NexonOpenApiErrorCode.OPENAPI00004.equals(errorCode);
    }

}
