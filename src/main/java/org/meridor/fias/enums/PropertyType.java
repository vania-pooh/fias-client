package org.meridor.fias.enums;

import java.math.BigInteger;

public enum PropertyType {
    PROPERTY(1),
    HOUSE(2),
    HOUSING_ESTATE(3),
    GROUND(4);

    private final Integer propertyType;

    PropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public BigInteger getPropertyType() {
        return new BigInteger(propertyType.toString());
    }
}
