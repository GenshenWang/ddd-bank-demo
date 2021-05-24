package com.wgs.ddd.domain_3_v2.weapon;

import com.wgs.ddd.domain_3_v2.entity.WeaponId;
import com.wgs.ddd.domain_3_v2.entity.WeaponType;
import lombok.Data;

@Data
public class Weapon {

    private WeaponId id;
    private String name;
    private WeaponType weaponType; // enum
    private int damage;

    // 0 - physical, 1 - fire, 2 - ice
    private int damageType;
}
