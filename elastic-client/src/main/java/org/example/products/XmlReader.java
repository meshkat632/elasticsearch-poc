package org.example.products;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class XmlReader {

    private XMLEventReader xmlEventReader = null;
    private FeatureMappings mappings;

    public XmlReader(String path) throws IOException, XMLStreamException {
        InputStream xml = new FileInputStream(path);

        final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlEventReader = xmlInputFactory.createXMLEventReader(xml);

        mappings = new FeatureMappings();

    }

    public Map<String,String> getNextDocument() throws XMLStreamException {
        while (xmlEventReader.hasNext()) {
            final XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                final StartElement startElement = xmlEvent.asStartElement();

                if (startElement.getName().toString().equals("document")) {
                    Map<String,String> document = parseDoc(startElement);
                    return document;
                }
            }
        }
        return null;
    }

    private Map<String,String> parseDoc(StartElement startElement) throws XMLStreamException {

        QName nameOfAttributeId = new QName("id");
        Attribute attributeId = startElement.getAttributeByName(nameOfAttributeId);
        QName nameOfAttributeCategoryIds = new QName("categoryids");
        Attribute categoryIds = startElement.getAttributeByName(nameOfAttributeCategoryIds);

        Map<String,String> fields = new HashMap<>();
        fields.put(attributeId.getName().getLocalPart(), attributeId.getValue());
        fields.put(categoryIds.getName().getLocalPart(), categoryIds.getValue());

        while (xmlEventReader.hasNext()) {
            final XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                final StartElement startSubElement = xmlEvent.asStartElement();
                // Attribute attribute = startElement.getAttributeByName(new QName("name"));


                Map.Entry<String,String> field = parseField(startSubElement);
                if (field != null) {
                    fields.put(field.getKey(), field.getValue());
                }
            }

            if (xmlEvent.isEndElement()) {
                break;
            }
        }

        return fields;
    }

    private Map.Entry<String,String> parseField(StartElement startElement) throws XMLStreamException {

        QName nameOfAttribute = new QName("name");
        Attribute attribute = startElement.getAttributeByName(nameOfAttribute);

        String key = attribute.getValue();
        String value = null;

        // System.out.println(attribute.getName().getLocalPart() + " " + attribute.getValue());

        while (xmlEventReader.hasNext()) {
            final XMLEvent xmlEvent = xmlEventReader.nextEvent();

            if (xmlEvent.isCharacters()) {
                Characters characters = xmlEvent.asCharacters();
                value = characters.getData();
            }


            if (xmlEvent.isEndElement()) {
                break;
            }
        }

        if (value != null) {

            String keyToReturn = mappings.getMap().get(key);
            if (keyToReturn != null) {
                return new AbstractMap.SimpleEntry<>(keyToReturn,value);
            }
            return new AbstractMap.SimpleEntry<>(key,value);
        } else {
            return null;
        }
    }

}
