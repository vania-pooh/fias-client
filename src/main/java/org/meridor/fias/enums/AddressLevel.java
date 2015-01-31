package org.meridor.fias.enums;

import java.math.BigInteger;

public enum AddressLevel {
    
    REGION(1),
    AUTONOMY(2),
    AREA(3),
    CITY(4),
    COMMUNITY(5),
    LOCATION(6),
    STREET(7),
    SUPPLEMENTARY_TERRITORY(90),
    CHILD_SUPPLEMENTARY_TERRITORY(91);

    private final Integer addressLevel;

    AddressLevel(Integer addressLevel) {
        this.addressLevel = addressLevel;
    }

    public BigInteger getAddressLevel() {
        return new BigInteger(addressLevel.toString());
    }
}
