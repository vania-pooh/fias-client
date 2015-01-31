package org.meridor.fias;

import java.util.List;
import java.util.function.Predicate;

public interface FiasAware {
    
    List<AddressObjects.Object> load(Predicate<AddressObjects.Object> predicate);
    
}
