package com.wgs.ddd.domain_3_v2;

import lombok.Value;

/**
 * 位置
 */
@Value
public class Transform {
    public static final Transform ORIGIN = new Transform(0, 0);
    long x;
    long y;
}
