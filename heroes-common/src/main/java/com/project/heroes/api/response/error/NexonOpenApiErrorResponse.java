package com.project.heroes.api.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.heroes.api.exception.NexonOpenApiErrorCode;

public record NexonOpenApiErrorResponse(Error error) {

    public record Error(@JsonProperty("name") NexonOpenApiErrorCode errorCode,
                        String message) {

    }
}
