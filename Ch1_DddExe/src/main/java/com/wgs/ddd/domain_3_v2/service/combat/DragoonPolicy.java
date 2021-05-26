package com.wgs.ddd.domain_3_v2.service.combat;

import com.wgs.ddd.domain_3_v2.entity.MonsterClass;
import com.wgs.ddd.domain_3_v2.entity.PlayerClass;
import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public class DragoonPolicy implements DamagePolicy {
    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return player.getPlayerClass() == PlayerClass.DRAGOON
                && monster.getMonsterClass() == MonsterClass.EVIL;
    }

    @Override
    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamage() * 2;
    }

}
