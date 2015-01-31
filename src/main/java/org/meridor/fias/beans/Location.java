package org.meridor.fias.beans;

public class Location {
    
    private final String name;
    
    private final String type;
    
    private final Area area;

    public Location(String name, String type, Area area) {
        this.name = name;
        this.type = type;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Area getArea() {
        return area;
    }
    
}
