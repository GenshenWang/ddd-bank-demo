package com.wgs.ddd.domain_3.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerEnum {
    Fighter(1, "战士"),
    Mage(2, "法师"),
    Dragoon(3, "龙骑")
    ;

    int code;
    String name;


}
