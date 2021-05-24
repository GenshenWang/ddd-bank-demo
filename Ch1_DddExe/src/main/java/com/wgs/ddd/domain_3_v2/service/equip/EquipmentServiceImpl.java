package com.wgs.ddd.domain_3_v2.service.equip;

import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentManager equipmentManager;


    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return equipmentManager.canEquip(player, weapon);
    }
}
