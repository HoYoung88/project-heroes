package com.project.heroes.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NexonOpenApiErrorCode {

    OPENAPI00001(500, "서버 내부 오류"),
    OPENAPI00002(403, "권한이 없는 경우"),
    OPENAPI00003(400, "유효하지 않은 식별자"),
    OPENAPI00004(400, "파라미터 누락 또는 유효하지 않음"),
    OPENAPI00005(400, "유효하지 않은 API KEY"),
    OPENAPI00006(400, "유효하지 않은 게임 또는 API PATH"),
    OPENAPI00007(429, "API 호출량 초과"),
    OPENAPI00009(400, "데이터 준비 중"),
    OPENAPI00010(400, "게임 점검 중"),
    OPENAPI00011(503, "API 점검 중");

    private final int code;
    private final String description;

    public String getDescription() {
        return "[" + name() + "] " + description;
    }
}
