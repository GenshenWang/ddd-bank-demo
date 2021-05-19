package com.wgs.ddd;

import com.wgs.ddd.exception.InvalidParamException;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@ToString
public class Money {
    private BigDecimal value;
    private Currency currency;

    public Money(BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) < 0) {
            throw new InvalidParamException("Invalid param for money:" + value);
        }
        this.value = value;
    }

    public void add(Money amount) {
        if (amount == null) {
            throw new InvalidParamException();
        }
        this.value.add(amount.value);
    }

    public void subtract(Money amount) {
        if (amount == null) {
            throw new InvalidParamException();
        }
        this.value.subtract(amount.value);
    }

    public int compareTo(Money amount) {
        if (amount == null) {
            throw new InvalidParamException();
        }

        if (this.value.compareTo(amount.value) > 0) {
            return 1;
        } else {
            return -1;
        }
    }


}
