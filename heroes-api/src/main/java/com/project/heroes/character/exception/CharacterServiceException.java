package com.project.heroes.character.exception;

import lombok.Getter;

@Getter
public class CharacterServiceException extends RuntimeException {

    private final CharacterServiceErrorCode characterServiceErrorCode;

    public CharacterServiceException(CharacterServiceErrorCode characterServiceErrorCode) {
        super(characterServiceErrorCode.getMessage());
        this.characterServiceErrorCode = characterServiceErrorCode;
    }
}
