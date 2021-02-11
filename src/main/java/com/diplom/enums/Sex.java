package com.diplom.enums;

public enum Sex {

    МУЖСКОЙ(0),
    ЖЕНСКИЙ(1);

    private final int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
