package com.wgs.ddd.domain_3_v2.entity;

public enum PlayerClass {
    FIGHTER(1, "战士"),
    DRAGOON(2, "龙骑士"),
    MAGICER(3, "法师"),
    LEVEL_4(4, "星耀"),

    ;
    int code;
    String desc;

    PlayerClass(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
