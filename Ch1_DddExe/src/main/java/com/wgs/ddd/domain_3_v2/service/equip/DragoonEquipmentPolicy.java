package com.wgs.ddd.domain_3_v2.service.equip;

import com.wgs.ddd.domain_3_v2.entity.PlayerClass;
import com.wgs.ddd.domain_3_v2.entity.WeaponType;
import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public class DragoonEquipmentPolicy implements EquipmentPolicy {
    
    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return weapon.getWeaponType() == WeaponType.DRAGON;
    }

    @Override
    public boolean canApply(Player player, Weapon weapon) {
        return player.getPlayerClass() == PlayerClass.DRAGOON;
    }
}
