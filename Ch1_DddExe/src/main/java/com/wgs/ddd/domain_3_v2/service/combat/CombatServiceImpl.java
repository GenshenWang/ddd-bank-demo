package com.wgs.ddd.domain_3_v2.service.combat;

import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.player.Player;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;

public class CombatServiceImpl implements CombatService {

    private CombatManager combatManager;
    private WeaponRepository weaponRepository;

    @Override
    public void attack(Player player, Monster monster) {

        Weapon weapon = weaponRepository.find(player.getWeaponId());
        int damage = combatManager.calculateDamage(player, weapon, monster);

        // 怪兽血量变低
        if (damage > 0) {
            monster.takeDamage(damage);
        }

        // player
        player.addHealth(damage, monster);
    }
}
