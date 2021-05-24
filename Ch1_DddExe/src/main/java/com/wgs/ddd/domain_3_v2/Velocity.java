package com.wgs.ddd.domain_3_v2;

import lombok.Value;

/**
 * 速度
 */
@Value
public class Velocity {
    public static final Velocity ZERO = new Velocity(0, 0);
    long x;
    long y;
}
