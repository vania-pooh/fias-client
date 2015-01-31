package org.meridor.fias;

import org.meridor.fias.loader.ReferenceProvider;
import org.meridor.fias.loader.XMLLoader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class FiasClient implements FiasAware {
    
    private final XMLLoader xmlLoader;
    
    public FiasClient(Path xmlDirectory) throws FileNotFoundException {
        if (!Files.exists(xmlDirectory)) {
            throw new FileNotFoundException(String.format(
                    "Specified path [%s] does not exist",
                    xmlDirectory
            ));
        }
        xmlLoader = new XMLLoader(xmlDirectory);
    }
    
    @Override
    public List<AddressObjects.Object> load(Predicate<AddressObjects.Object> predicate) {
        return xmlLoader.loadRaw(predicate);
    }
}
