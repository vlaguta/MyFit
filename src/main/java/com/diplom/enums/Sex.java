package com.diplom.enums;

public enum Sex {

    MEN(0),
    WOMEN(1);

    private final int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
