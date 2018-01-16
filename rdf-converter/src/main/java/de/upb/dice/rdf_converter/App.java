package de.upb.dice.rdf_converter;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		String datei = "src/main/resources/input.csv";
		ApacheConverter.ladeDatei(datei);
	}
}
