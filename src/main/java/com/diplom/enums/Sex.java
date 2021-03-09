package com.diplom.enums;

public enum Sex {

    MEN("Мужской"),
    WOMEN("Женский");

    private final String code;

    Sex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
