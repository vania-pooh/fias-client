package org.meridor.fias.enums;

import java.math.BigInteger;

public enum CenterStatus {
    
    NOT_A_CENTER(0),
    AREA_CENTER(1),
    REGION_CENTER(2),
    AREA_AND_REGION_CENTER(3);

    private final Integer centerStatus;

    CenterStatus(Integer centerStatus) {
        this.centerStatus = centerStatus;
    }

    public BigInteger getCenterStatus() {
        return new BigInteger(centerStatus.toString());
    }
}
