package com.diplom.enums;

public enum Activity {

    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int code;

    Activity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
