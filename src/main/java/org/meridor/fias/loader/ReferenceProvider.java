package org.meridor.fias.loader;

import org.meridor.fias.ActualStatuses;
import org.meridor.fias.AddressObjectTypes;
import org.meridor.fias.CenterStatuses;
import org.meridor.fias.CurrentStatuses;
import org.meridor.fias.EstateStatuses;
import org.meridor.fias.HouseStateStatuses;
import org.meridor.fias.NormativeDocumentTypes;
import org.meridor.fias.OperationStatuses;
import org.meridor.fias.StructureStatuses;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.meridor.fias.enums.FiasFile.ACTUAL_STATUS;
import static org.meridor.fias.enums.FiasFile.ADDRESS_OBJECT_TYPE;
import static org.meridor.fias.enums.FiasFile.CENTER_STATUS;
import static org.meridor.fias.enums.FiasFile.CURRENT_STATUS;
import static org.meridor.fias.enums.FiasFile.ESTATE_STATUS;
import static org.meridor.fias.enums.FiasFile.HOUSE_STATE_STATUS;
import static org.meridor.fias.enums.FiasFile.NORMATIVE_DOCUMENT_TYPE;
import static org.meridor.fias.enums.FiasFile.OPERATION_STATUS;
import static org.meridor.fias.enums.FiasFile.STRUCTURE_STATUS;

@Deprecated
public class ReferenceProvider {

    private final XMLLoader xmlLoader;

    //TODO: add other references
    private boolean loaded = false;
    private final Map<BigInteger, AddressObjectTypes.AddressObjectType> addressObjectTypeMap = new HashMap<>();
    private final Map<BigInteger, OperationStatuses.OperationStatus> operationStatusMap = new HashMap<>();
    
    public ReferenceProvider(XMLLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }

    private void load() throws Exception {
        AddressObjectTypes addressObjectTypes = xmlLoader.loadReferenceTable(ADDRESS_OBJECT_TYPE, AddressObjectTypes.class);
        addressObjectTypes.getAddressObjectType().forEach(t -> addressObjectTypeMap.put(t.getLEVEL(), t));
        
        ActualStatuses actualStatuses = xmlLoader.loadReferenceTable(ACTUAL_STATUS, ActualStatuses.class);
        CenterStatuses centerStatuses = xmlLoader.loadReferenceTable(CENTER_STATUS, CenterStatuses.class);
        CurrentStatuses currentStatuses = xmlLoader.loadReferenceTable(CURRENT_STATUS, CurrentStatuses.class);
        EstateStatuses estateStatuses = xmlLoader.loadReferenceTable(ESTATE_STATUS, EstateStatuses.class);
        NormativeDocumentTypes normativeDocumentTypes = xmlLoader.loadReferenceTable(NORMATIVE_DOCUMENT_TYPE, NormativeDocumentTypes.class);
        HouseStateStatuses houseStateStatuses = xmlLoader.loadReferenceTable(HOUSE_STATE_STATUS, HouseStateStatuses.class);
        StructureStatuses structureStatuses = xmlLoader.loadReferenceTable(STRUCTURE_STATUS, StructureStatuses.class);
        OperationStatuses operationStatuses = xmlLoader.loadReferenceTable(OPERATION_STATUS, OperationStatuses.class);
    }
    
    private void loadIfNeeded() {
        if (!loaded) {
            try {
                load();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            loaded = true;
        }
    }
    
    public Optional<OperationStatuses.OperationStatus> getOperationStatus(BigInteger id) {
        loadIfNeeded();
        return Optional.ofNullable(operationStatusMap.get(id));
    }
    
    public Optional<AddressObjectTypes.AddressObjectType> getAddressObjectType(BigInteger id) {
        loadIfNeeded();
        return Optional.ofNullable(addressObjectTypeMap.get(id));
    }
    
}
