package com.wgs.ddd.domain_3_v2.service.combat;

import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class CombatManager {

    private static final List<DamagePolicy> POLICIES = new ArrayList<>();

    static {
        POLICIES.add(new DragoonPolicy());
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        for (DamagePolicy policy : POLICIES) {
            if (!policy.canApply(player, weapon, monster)) {
                continue;
            }

            return policy.calculateDamage(player, weapon, monster);
        }

        return 0;
    }


}
