package com.wgs.ddd.v2.domain;

import lombok.Data;

@Data
public class UserId {
    private Long id;

    public UserId(Long id) {
        if (id == null) {
            throw new RuntimeException("Invalid id");
        }
        this.id = id;
    }
}
