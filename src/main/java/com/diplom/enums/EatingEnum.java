package com.diplom.enums;

public enum EatingEnum {

    BREAKFAST(0),

    DINNER(1),

    SUPPER(2);

    private final int code;

    EatingEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    //табл id name для кажд пользователя, в продуктах ссылка на ка
    //перечень типов еды
}
