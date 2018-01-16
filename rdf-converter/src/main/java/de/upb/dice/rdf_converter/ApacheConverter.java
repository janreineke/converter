package de.upb.dice.rdf_converter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ApacheConverter {

	public static void ladeDatei(String dateiName) throws IOException {

		Reader in = new FileReader(dateiName);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
		for (CSVRecord record : records) {

			System.out.print("\"" + record.get(0) + "\", ");
			System.out.println();
//			System.out.print("\"" + record.get(1) + "\", ");
		}
	}
	
	
}

