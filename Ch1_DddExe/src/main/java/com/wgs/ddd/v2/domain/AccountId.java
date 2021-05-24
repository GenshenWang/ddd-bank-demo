package com.wgs.ddd.v2.domain;

import com.wgs.ddd.repository_2.Identifier;
import lombok.Data;

@Data
public class AccountId implements Identifier {
    private Long id;

}
