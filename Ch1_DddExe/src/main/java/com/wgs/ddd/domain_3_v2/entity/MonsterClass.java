package com.wgs.ddd.domain_3_v2.entity;

public enum MonsterClass {
    MORMAL(1, "普通"),
    EVIL(2, "怪兽"),
    MAGEIC_PLAN(2, "仙草"),

    ;
    int code;
    String desc;

    MonsterClass(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
