package com.project.heroes.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BatchErrorEvent {
    private final String message;
}
