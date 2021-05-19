package com.wgs.ddd;

import com.wgs.ddd.exception.InvalidParamException;
import lombok.Data;

@Data
public class UserId {
    private Long id;

    public UserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new InvalidParamException("Invalid param for id:" + userId);
        }
        this.id = userId;
    }

}
