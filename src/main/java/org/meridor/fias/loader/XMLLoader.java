package org.meridor.fias.loader;

import org.meridor.fias.AddressObjects;
import org.meridor.fias.enums.FiasFile;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.meridor.fias.enums.FiasFile.ADDRESS_OBJECTS;

public class XMLLoader {
    
    private final Path xmlDirectory;

    public XMLLoader(Path xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }

    private Path getPathByPattern(String startsWith) throws IOException {
        Optional<Path> filePath = Files.list(xmlDirectory)
                .map(xmlDirectory::relativize)
                .filter(path -> path.toString().startsWith(startsWith) && path.toString().toLowerCase().endsWith("xml"))
                .findFirst();
        if (!filePath.isPresent()) {
            throw new FileNotFoundException(String.format("Can't find XML file with name starting with [%s]", startsWith));
        }
        return xmlDirectory.resolve(filePath.get());
    }

    public <T> T loadReferenceTable(FiasFile fiasFile, Class<T> someClass) throws JAXBException, IOException {
        String fileName = fiasFile.getName();
        Path filePath = getPathByPattern(fileName);
        return JAXB.unmarshal(filePath.toFile(), someClass);
    }

    public List<AddressObjects.Object> loadRaw(Predicate<AddressObjects.Object> predicate) {
        if (predicate == null) {
            return Collections.emptyList();
        }
        try {
            Path filePath = getPathByPattern(ADDRESS_OBJECTS.getName());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()));
            try (PartialUnmarshaller<AddressObjects.Object> partialUnmarshaller = new PartialUnmarshaller<>(inputStream, AddressObjects.Object.class)) {
                List<AddressObjects.Object> results = new ArrayList<>();
                while (partialUnmarshaller.hasNext()) {
                    AddressObjects.Object addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        results.add(addressObject);
                    }
                }
                return results;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
