package de.upb.dice.rdf_converter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ApacheConverter {

	public static void ladeDatei(String dateiName) throws IOException {

		File output = new File("src/main/resources/output.txt");
		if(!output.exists())
			output.createNewFile();
		FileWriter writer = new FileWriter(output);
		
		Reader in = new FileReader(dateiName);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
		final long zeitStart = System.nanoTime();
		for (CSVRecord record : records) {

//			System.out.print("<http://www.solide-projekt.de/resource/#" + record.get(0) + "> ");
//			System.out.print("<http://www.w3.org/2000/01/rdf-schema#class" + record.get(1) + ">");
//			System.out.print(record.get(2) + ",");
//			System.out.print(record.get(3) + ",");
//			System.out.print(record.get(4) + ",");
//			System.out.print(record.get(5) + ",");
//			System.out.println();
			writer.write("<http://www.solide-projekt.de/resource/#" + record.get(0) + "> " + System.getProperty("line.separator"));
		
		}
		writer.flush();
		writer.close();
		final long zeitEnde= System.nanoTime();
		System.out.println("Zeitverbrauch:" + (zeitEnde - zeitStart) + " NanoSekunden");
		
		
				
	}
	
	
}

