package org.example.products;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestReadFile {

	public static void main(String args[]) throws IOException {

		String fileName = "notes.xml";

		//read file into stream, try-with-resources		
		try (BufferedReader buffer = Files.newBufferedReader(Paths.get(fileName))) {
			while(true) {
				int x = buffer.read();
				if(x == -1)
					break;
				
				System.out.println((char)x);			
				
			}
			
			
            //return buffer.lines().collect(Collectors.joining("\n"));
        }
		
		/*
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
		//try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

}