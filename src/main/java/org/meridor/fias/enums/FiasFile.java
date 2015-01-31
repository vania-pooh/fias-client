package org.meridor.fias.enums;

public enum FiasFile {
    
    ACTUAL_STATUS("AS_ACTSTAT"),
    ADDRESS_OBJECTS("AS_ADDROBJ"),
    CENTER_STATUS("AS_CENTERST"),
    CURRENT_STATUS("AS_CURENTST"),
    ESTATE_STATUS("AS_ESTSTAT"),
    HOUSE("AS_HOUSE"),
    HOUSE_INTERVALS("AS_HOUSEINT"),
    HOUSE_STATE_STATUS("AS_HSTSTAT"),
    INTERVAL_STATUS("AS_INTVSTAT"),
    LANDMARK("AS_LANDMARK"),
    NORMATIVE_DOCUMENT_TYPE("AS_NORMDOC"),
    OPERATION_STATUS("AS_OPERSTAT"),
    ADDRESS_OBJECT_TYPE("AS_SOCRBASE"),
    STRUCTURE_STATUS("AS_STRSTAT");

    private final String name;
    
    FiasFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
