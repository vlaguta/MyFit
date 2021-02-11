package com.diplom.enums;

public enum Activity {

    НИЗКАЯ(0),
    СРЕДНЯЯ(1),
    ВЫСОКАЯ(2);

    private final int code;

    Activity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
