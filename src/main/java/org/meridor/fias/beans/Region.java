package org.meridor.fias.beans;

public class Region {
    
    private final String name;
    
    private final String code;
    
    private final FiscalCodes fiscalCodes;

    public Region(String name, String code, FiscalCodes fiscalCodes) {
        this.name = name;
        this.code = code;
        this.fiscalCodes = fiscalCodes;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public FiscalCodes getFiscalCodes() {
        return fiscalCodes;
    }
}
