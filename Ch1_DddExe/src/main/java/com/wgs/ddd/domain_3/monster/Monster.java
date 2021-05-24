package com.wgs.ddd.domain_3.monster;

import com.wgs.ddd.domain_3.Weapon.Weapon;
import com.wgs.ddd.domain_3.player.Player;

public abstract class Monster {
    Long health;

    public void incrHealth(long delta) {
        this.health += delta;
    }

    public void decrHealth(long delta) {
        this.health -= delta;
    }

    public Long getHealth() {
        return health;
    }

    public void receiveDamageBy(Weapon weapon, Player player) {
        // 基础规则
        this.health -= weapon.getDamage();
    }

}
