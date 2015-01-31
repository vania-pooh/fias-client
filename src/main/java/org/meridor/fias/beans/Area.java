package org.meridor.fias.beans;

public class Area {
    
    private final String name;
    
    private final String code;
    
    private final Region region;
    
    private final FiscalCodes fiscalCodes;

    public Area(String name, String code, Region region, FiscalCodes fiscalCodes) {
        this.name = name;
        this.code = code;
        this.region = region;
        this.fiscalCodes = fiscalCodes;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Region getRegion() {
        return region;
    }

    public FiscalCodes getFiscalCodes() {
        return fiscalCodes;
    }
}
