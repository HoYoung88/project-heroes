package com.project.heroes.character.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CharacterServiceErrorCode {

    CHARACTER_NOT_FOUND("Character not found"),;

    private final String message;

}
