package org.meridor.fias.loader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.DTD;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;

/**
 * Chunks XML unmarshaller
 * @see <a href="http://stackoverflow.com/questions/1134189/can-jaxb-parse-large-xml-files-in-chunks">StackOverflow question</a>
 * @param <T>
 */
public class PartialUnmarshaller<T> implements Iterator<T>, Closeable {
    
    private final XMLStreamReader reader;
    private final Class<T> destinationClass;
    private final Unmarshaller unmarshaller;

    public PartialUnmarshaller(InputStream inputStream, Class<T> destinationClass) throws Exception {
        this.destinationClass = destinationClass;
        this.unmarshaller = JAXBContext.newInstance(destinationClass).createUnmarshaller();
        this.reader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);

        // ignore headers
        skipElements(START_DOCUMENT, DTD);
        // ignore root element
        reader.nextTag();
        // if there's no tag, ignore root element's end
        skipElements(END_ELEMENT);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        try {
            T value = unmarshaller.unmarshal(reader, destinationClass).getValue();
            skipElements(CHARACTERS, END_ELEMENT);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasNext() {
        try {
            return reader.hasNext();
        } catch (XMLStreamException e) {
            return false;
        }
    }

    public void close() throws IOException {
        try {
            reader.close();
        } catch (XMLStreamException e) {
            throw new IOException(e);
        }
    }

    private void skipElements(Integer...elements) throws Exception {
        int eventType = reader.getEventType();

        List<Integer> types = Arrays.asList(elements);
        while (types.contains(eventType))
            eventType = reader.next();
    }
}