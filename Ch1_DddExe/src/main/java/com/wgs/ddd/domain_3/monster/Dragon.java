package com.wgs.ddd.domain_3.monster;

import com.wgs.ddd.domain_3.Weapon.Weapon;
import com.wgs.ddd.domain_3.player.Dragoon;
import com.wgs.ddd.domain_3.player.Player;

public class Dragon extends Monster {

    @Override
    public void receiveDamageBy(Weapon weapon, Player player) {
        // 龙对物理和魔法攻击免疫，除非玩家是龙骑，则伤害加倍
        if (player instanceof Dragoon) {
            this.decrHealth(weapon.getDamage() * 2);
        }
    }
}
