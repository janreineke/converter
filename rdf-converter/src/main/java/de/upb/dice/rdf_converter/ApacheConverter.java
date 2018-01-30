package de.upb.dice.rdf_converter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDFS;

public class ApacheConverter {

	public static void ladeDatei(String dateiName) throws IOException {

		File output = new File("src/main/resources/output.txt");
		if(!output.exists())
			output.createNewFile();
		FileWriter writer = new FileWriter(output);
		
		Reader in = new FileReader(dateiName);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
		final long zeitStart = System.nanoTime();
		
		Model model = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();
		
		
		
		
		for (CSVRecord record : records) {

			String subject = "http://www.solide-projekt.de/resource/" + record.get(0);
			
			model.add(new ResourceImpl(subject), RDFS.label, record.get(1));
			model.add(new ResourceImpl(subject), RDFS.label, record.get(2));
			if(record.get(3) !="")	model2.add(new ResourceImpl(subject), RDFS.comment,record.get(3));
			model2.add(new ResourceImpl(subject), RDFS.subClassOf, record.get(4));
			if (record.get(4) !=null) model2.add(new ResourceImpl(subject), RDFS.subClassOf, record.get(5));
			
			
			
//			System.out.print("<http://www.solide-projekt.de/resource/#" + record.get(0) + "> ");
//			System.out.print("<http://www.w3.org/2000/01/rdf-schema#class" + record.get(1) + ">");
//			System.out.print(record.get(2) + ",");
//			System.out.print(record.get(3) + ",");
//			System.out.print(record.get(4) + ",");
//			System.out.print(record.get(5) + ",");
//	System.out.println();
	//		System.out.println();

		//	writer.write("<http://www.solide-projekt.de/resource/" + record.get(0) + "> " + System.getProperty("line.separator"));
		
		}
		 RDFDataMgr.write(System.out, model,Lang.NT) ;
		 RDFDataMgr.write(System.out, model2,Lang.NT) ;
		writer.flush();
		writer.close();
		final long zeitEnde= System.nanoTime();
		System.out.println("Zeitverbrauch:" + (zeitEnde - zeitStart) + " NanoSekunden");
		
		
				
	}
	
	
	
	
}

