package de.upb.dice.rdf_converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDFS;

public class ApacheConverter {

	public static void ladeDatei(String dateiName, String klassifizierung) throws IOException {

		File output_klassen = new File("src/main/resources/klassen.ttl");
		if (!output_klassen.exists())
			output_klassen.createNewFile();

		File output_ontologie = new File("src/main/resources/ontologie.ttl");
		if (!output_ontologie.exists())
			output_ontologie.createNewFile();

		// csv Abkürzungen einlesen
		Reader in = new FileReader(dateiName);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

		// csv Klassifzierung einlesen
		Reader inKlassen = new FileReader(klassifizierung);
		Iterable<CSVRecord> recordsKlassen = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(inKlassen);

		final long zeitStart = System.nanoTime();

		Model model0 = ModelFactory.createDefaultModel();
		Model model1 = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();

		for (CSVRecord reKlasse : recordsKlassen) {

			String subj = "http://www.solide-projekt.de/resource/" + reKlasse.get(0);
			String obj = "http://www.solide-projekt.de/resource/" + reKlasse.get(1);
			model0.add(new ResourceImpl(subj), RDFS.subClassOf, new ResourceImpl(obj));

		}

		for (CSVRecord record : records) {

			String subject = "http://www.solide-projekt.de/resource/" + record.get(0);

			model1.add(new ResourceImpl(subject), RDFS.label, record.get(1));
			model1.add(new ResourceImpl(subject), RDFS.label, record.get(2));
			if (!record.get(3).isEmpty())
				model2.add(new ResourceImpl(subject), RDFS.comment, record.get(3));
			model2.add(new ResourceImpl(subject), RDFS.subClassOf, record.get(4));
			if (!record.get(5).isEmpty())
				model2.add(new ResourceImpl(subject), RDFS.subClassOf, record.get(5));

		}

		// write rdf turtle to file; Ontologie & Klassen
		RDFDataMgr.write(new FileOutputStream(output_ontologie), model1, Lang.NT);
		RDFDataMgr.write(new FileOutputStream(output_ontologie,true), model2, Lang.NT);
		// RDFDataMgr.write(System.out, model2,Lang.NT) ;
		RDFDataMgr.write(new FileOutputStream(output_klassen), model0, Lang.NT);

		final long zeitEnde = System.nanoTime();
		System.out.println("Zeitverbrauch:" + (double) (zeitEnde - zeitStart) / 1000000000 + " Sekunden");

	}

}
