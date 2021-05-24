package com.wgs.ddd.domain_3_v2.service.combat;

import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.player.Player;

public interface CombatService {

    void attack(Player player, Monster monster);
}
