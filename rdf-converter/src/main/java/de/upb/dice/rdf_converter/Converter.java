package de.upb.dice.rdf_converter;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Converter {

	private static final String SEP = ",";

	public static void ladeDatei(String dateiName) {

		try {
			File file = new File(dateiName);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {

				/*
				 * array -> Zeile jeweils bei "," trennen und Begriffe einzeln speichern
				 * 
				 * 
				 */
				String zeile = scanner.nextLine();
				String[] werte = zeile.split(SEP);
				// List<String> container = Arrays.asList(werte);
				// wert.add = (zeile.split(SEP;0));

				System.out.println(zeile);
				System.out.println("Abkürzung: " + werte[0]);
				// if (werte.length>=2) System.out.println("Label1: "+ container.get(1));
				if (werte.length >= 2)
					System.out.println("Label1: " + werte[1]);

				if (werte.length >= 2)
					System.out.println("Label2: " + werte[2]);
				// System.out.println("Abkürzung: "+ container.get(3));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * todo: Daten umwandeln und als rdf wieder in Datei "output.txt" schreiben.
		 */
	}
}
