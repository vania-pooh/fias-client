package org.meridor.fias.beans;

public class City {
    
    private final String name;
    
    private final String code;
    
    private final Area area;
    
    private final FiscalCodes fiscalCodes;

    public City(String name, String code, Area area, FiscalCodes fiscalCodes) {
        this.name = name;
        this.code = code;
        this.area = area;
        this.fiscalCodes = fiscalCodes;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Area getArea() {
        return area;
    }

    public FiscalCodes getFiscalCodes() {
        return fiscalCodes;
    }
}
