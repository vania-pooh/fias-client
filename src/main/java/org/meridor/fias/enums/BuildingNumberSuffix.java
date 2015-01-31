package org.meridor.fias.enums;

import java.math.BigInteger;

public enum BuildingNumberSuffix {
    
    BUILDING(1),
    CONSTRUCTION(2),
    LETTER(3);

    private final Integer buildingNumberSuffix;

    BuildingNumberSuffix(Integer buildingNumberSuffix) {
        this.buildingNumberSuffix = buildingNumberSuffix;
    }

    public BigInteger getBuildingNumberSuffix() {
        return new BigInteger(buildingNumberSuffix.toString());
    }
}
