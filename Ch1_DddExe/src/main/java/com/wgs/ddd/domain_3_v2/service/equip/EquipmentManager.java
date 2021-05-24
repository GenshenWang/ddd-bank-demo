package com.wgs.ddd.domain_3_v2.service.equip;

import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class EquipmentManager {
    private static final List<EquipmentPolicy> POLICYS = new ArrayList<>();

    static {
        POLICYS.add(new FighterEquipmentPolicy());
        POLICYS.add(new DragoonEquipmentPolicy());
    }

    public boolean canEquip(Player player, Weapon weapon) {
        for (EquipmentPolicy policy : POLICYS) {
            if (!policy.canApply(player, weapon)) {
                continue;
            }

            return policy.canEquip(player, weapon);
        }

        return false;
    }
}
