package com.wgs.ddd.domain_3_v2.entity;

public enum WeaponType {
    SWORD(1, "剑"),
    GUN(2, "枪"),
    DRAGON(3, "龙")

    ;
    int code;
    String desc;

    WeaponType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
