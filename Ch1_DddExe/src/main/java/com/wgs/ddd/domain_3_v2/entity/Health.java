package com.wgs.ddd.domain_3_v2.entity;

import lombok.Data;

@Data
public class Health {
    private int value;

    public void add(int delta) {
        this.value += delta;
        if (this.value > 100) {
            this.value = 100;
        }
    }

    public void decr(int delta) {
        this.value -= delta;
        if (this.value <= 0) {
            this.value = 0;
        }

    }
}
