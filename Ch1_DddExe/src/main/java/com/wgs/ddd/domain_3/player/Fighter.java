package com.wgs.ddd.domain_3.player;

import com.wgs.ddd.domain_3.Weapon.Staff;
import com.wgs.ddd.domain_3.Weapon.Sword;
import com.wgs.ddd.domain_3.Weapon.Weapon;

public class Fighter extends Player {

    private Sword weapon;

    public Fighter(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Fighter fighter = new Fighter("Hero");

        Sword sword = new Sword();
        fighter.setWeapon(sword);

        Player player = fighter;
        Staff staff = new Staff();
        player.setWeapon(staff);
    }

}
