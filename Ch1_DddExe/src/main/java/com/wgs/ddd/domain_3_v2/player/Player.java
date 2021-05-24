package com.wgs.ddd.domain_3_v2.player;

import com.wgs.ddd.domain_3_v2.Movable;
import com.wgs.ddd.domain_3_v2.Transform;
import com.wgs.ddd.domain_3_v2.Velocity;
import com.wgs.ddd.domain_3_v2.entity.*;
import com.wgs.ddd.domain_3_v2.monster.Monster;
import com.wgs.ddd.domain_3_v2.service.equip.EquipmentService;
import com.wgs.ddd.domain_3_v2.weapon.Weapon;
import lombok.Data;

@Data
public class Player implements Movable {

    private PlayerId id;
    private String name;
    private PlayerClass playerClass; // enum
    private WeaponId weaponId; // （Note 1）
    private Health health;

    private Transform position = Transform.ORIGIN;
    private Velocity velocity = Velocity.ZERO;

    @Override
    public Transform getPosition() {
        return this.position;
    }

    @Override
    public Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public void moveTo(long x, long y) {
        this.position = new Transform(x, y);
    }

    @Override
    public void startMove(long velX, long velY) {
        this.velocity = new Velocity(velX, velY);
    }

    @Override
    public void stopMove() {
        this.velocity = Velocity.ZERO;
    }

    @Override
    public boolean isMoving() {
        return this.velocity.getX() != 0 || this.velocity.getY() != 0;
    }

    /**
     * 用于判断是否支持某种武器
     * EquipmentService为Domain Service，不应该通过@Autowired注入
     *
     */
    public void equip(Weapon weapon, EquipmentService equipmentService) {
        if (!equipmentService.canEquip(this, weapon)) {
            throw new IllegalArgumentException("Cannot equip weapon");
        }

        this.weaponId = weapon.getId();
    }

    /**
     * 如果是攻击仙草、三狼，攻击者会恢复血量
     *
     * @param health
     * @param monster
     */
    public void addHealth(int health, Monster monster) {
        if (monster.getMonsterClass() == MonsterClass.MAGEIC_PLAN) {
            this.health.add(health / 2);
        }
    }
}
