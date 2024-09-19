package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSOPPORTED("Произошла не предвиденная ошибка"),
    UNKNOW("Не поддерживаемая ошибка"),
    VALIDATIONUID("Ошибка uid");
    private final String description;
    ErrorMessages(String description){
        this.description = description;
    }
    @JsonValue
    public String getName(){
        return description;
    }
}
