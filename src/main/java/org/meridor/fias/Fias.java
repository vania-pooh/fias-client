package org.meridor.fias;

import org.meridor.fias.beans.Area;
import org.meridor.fias.beans.City;
import org.meridor.fias.beans.FiscalCodes;
import org.meridor.fias.beans.Region;
import org.meridor.fias.enums.AddressLevel;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Fias {

    private final FiasAware fiasAware;

    private Fias(FiasAware fiasAware) {
        this.fiasAware = fiasAware;
    }
    
    public static Fias withXMLDirectory(Path xmlDirectory) throws FileNotFoundException {
        return new Fias(new FiasClient(xmlDirectory));
    }

    public List<Region> getRegions() {
        List<AddressObjects.Object> rawRegions = loadByAddressLevel(AddressLevel.REGION);
        return rawRegions.stream().map(o -> new Region(
                getName(o),
                o.getREGIONCODE(),
                getFiscalCodes(o)
        )).collect(Collectors.toList());
    }

    public List<Area> getAreas(Region region) {
        List<AddressObjects.Object> rawAreas = loadByAddressLevelAndParentCode(AddressLevel.AREA, region.getCode());
        return rawAreas.stream().map(o -> new Area (
                getName(o),
                o.getAREACODE(),
                region,
                getFiscalCodes(o)
        )).collect(Collectors.toList());
    }
    
    public List<City> getCities(Area area) {
        List<AddressObjects.Object> rawCities = loadByAddressLevelAndParentCode(AddressLevel.CITY, area.getCode());
        return rawCities.stream().map(o -> new City (
                getName(o),
                o.getCITYCODE(),
                area,
                getFiscalCodes(o)
        )).collect(Collectors.toList());
    }
    
    private static String getName(AddressObjects.Object object) {
        return object.getOFFNAME() + " " + object.getSHORTNAME();
    }
    
    private static FiscalCodes getFiscalCodes(AddressObjects.Object object) {
        return new FiscalCodes(
                object.getPOSTALCODE(),
                object.getIFNSFL(),
                object.getIFNSUL(),
                object.getOKATO(),
                object.getOKTMO()
        );
    }

    public List<AddressObjects.Object> loadByAddressLevel(AddressLevel addressLevel) {
        return fiasAware.load(getPredicateByAddressLevelAndParentCode(addressLevel, null));
    }
    
    public List<AddressObjects.Object> loadByAddressLevelAndParentCode(AddressLevel addressLevel, String parentCode) {
        return fiasAware.load(getPredicateByAddressLevelAndParentCode(addressLevel, parentCode));
    }
    
    private static Predicate<AddressObjects.Object> getPredicateByAddressLevelAndParentCode(AddressLevel addressLevel, String parentCode) {
        BigInteger addressLevelVal = addressLevel.getAddressLevel();
        switch (addressLevel) {
            default:
            case REGION: return o -> o.getAOLEVEL().equals(addressLevelVal);
            case AUTONOMY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getREGIONCODE().equals(parentCode);
            case AREA: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getREGIONCODE().equals(parentCode);
            case CITY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getAREACODE().equals(parentCode);
            case COMMUNITY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getCITYCODE().equals(parentCode);
            case LOCATION: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getCTARCODE().equals(parentCode);
            case STREET: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getPLACECODE().equals(parentCode);
            case SUPPLEMENTARY_TERRITORY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getSTREETCODE().equals(parentCode);
            case CHILD_SUPPLEMENTARY_TERRITORY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getEXTRCODE().equals(parentCode);
        }
    }

}
