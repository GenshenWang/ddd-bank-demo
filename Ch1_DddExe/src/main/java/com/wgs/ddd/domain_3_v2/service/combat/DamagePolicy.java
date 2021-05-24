package com.wgs.ddd.domain_3_v2.service.combat;

import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public interface DamagePolicy {

    boolean canApply(Player player, Weapon weapon, Monster monster);

    int calculateDamage(Player player, Weapon weapon, Monster monster);
}
