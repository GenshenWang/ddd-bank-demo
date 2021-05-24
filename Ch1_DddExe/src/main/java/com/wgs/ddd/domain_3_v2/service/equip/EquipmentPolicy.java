package com.wgs.ddd.domain_3_v2.service.equip;

import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public interface EquipmentPolicy extends EquipmentService {
    boolean canApply(Player player, Weapon weapon);

}
