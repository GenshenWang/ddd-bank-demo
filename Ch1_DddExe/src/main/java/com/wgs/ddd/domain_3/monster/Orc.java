package com.wgs.ddd.domain_3.monster;

import com.wgs.ddd.domain_3.Weapon.Weapon;
import com.wgs.ddd.domain_3.player.Player;

public class Orc extends Monster {

    @Override
    public void receiveDamageBy(Weapon weapon, Player player) {
        // 兽人对物理攻击伤害减半
        if (weapon.getDamageType() == 0) {
            // Orc的物理防御规则
            this.incrHealth(weapon.getDamage() / 2);
        } else {
            super.receiveDamageBy(weapon, player);
        }
    }
}
