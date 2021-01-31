package com.diplom.enums;

public enum Sex {

    MALE(0),
    FEMALE(1);

    private final int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
