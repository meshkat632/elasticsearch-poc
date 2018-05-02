package org.example.xml.stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        try (InputStream is = Main.class.getResourceAsStream("/data/books.xml")) {
            if (is == null)
                throw new IOException("Failed to open resource");

            BooksReader reader = new BooksReader();
            System.out.println(reader.readFromXML(is));
            
            Stream.iterate(1, n -> n + 1 )
            .limit(50)
            .forEach(System.out::println);
            
            XmlStreamReader bookReader = new XmlStreamReader();
            Stream<Book> books = bookReader.readAsStream(Book.class, is);
            
            books.limit(5).forEach(System.out::println);
            
        }
    }
}