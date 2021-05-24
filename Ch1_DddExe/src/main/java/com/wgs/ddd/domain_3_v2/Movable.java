package com.wgs.ddd.domain_3_v2;

public interface Movable {

    // 相当于组件
    Transform getPosition();
    Velocity getVelocity();

    // 行为
    void moveTo(long x, long y);
    void startMove(long velX, long velY);
    void stopMove();
    boolean isMoving();
}
