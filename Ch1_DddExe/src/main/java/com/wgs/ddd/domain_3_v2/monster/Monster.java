package com.wgs.ddd.domain_3_v2.monster;

import com.wgs.ddd.domain_3_v2.Transform;
import com.wgs.ddd.domain_3_v2.Velocity;
import com.wgs.ddd.domain_3_v2.entity.Health;
import com.wgs.ddd.domain_3_v2.entity.MonsterClass;
import com.wgs.ddd.domain_3_v2.entity.MonsterId;
import lombok.Data;

import java.util.Vector;

@Data
public class Monster {
    private MonsterId id;
    private MonsterClass monsterClass; // enum
    private Health health;
    private Transform position = Transform.ORIGIN;
    private Velocity velocity = Velocity.ZERO;

    public void takeDamage(int damage) {
        this.health.decr(damage);
    }
}
