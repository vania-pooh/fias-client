package org.meridor.fias.enums;

import java.math.BigInteger;

public enum IntervalStatus {
    
    EVEN_AND_ODD(1),
    ONLY_EVEN(2),
    ONLY_ODD(3);
    
    private final Integer intervalStatus;

    IntervalStatus(Integer intervalStatus) {
        this.intervalStatus = intervalStatus;
    }

    public BigInteger getIntervalStatus() {
        return new BigInteger(intervalStatus.toString());
    }
}
