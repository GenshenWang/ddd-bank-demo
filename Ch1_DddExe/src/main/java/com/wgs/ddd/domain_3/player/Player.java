package com.wgs.ddd.domain_3.player;

import com.wgs.ddd.domain_3.Weapon.Weapon;
import com.wgs.ddd.domain_3.monster.Monster;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public abstract class Player {

    //@Setter(AccessLevel.PROTECTED)
    private Weapon weapon;

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void attack(Monster monster) {
        monster.receiveDamageBy(weapon, this);
    }
}
