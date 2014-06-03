package de.lempikbubar.src.blatt08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EditDistanceUtil {
	
	/**
	 * Liest Strings aus einer Datei zeilenweise
	 * @param path
	 * @return ArrayList vom Typ String
	 * @throws IOException
	 */
	public static ArrayList<String> readLines(String path) 	throws IOException {
		ArrayList<String> list = new ArrayList<>();

		BufferedReader in = new BufferedReader(new FileReader(path));
		String zeile = null;
		while ((zeile = in.readLine()) != null) {
			list.add(zeile);
		}
		in.close();

		return list;
	}

}
