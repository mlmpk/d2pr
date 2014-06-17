package de.lempikbubar.src.blatt10;

import java.io.File;
import java.util.Scanner;

public class CountWords {
	
	static String file;
	static String hashMethod = "";
	static HashTable hashTable;
	
	// Festgelegte Menge der interessierenden Wörter
	static String[] words = { "a", "about", "all", "an", "and", "are",
			"around", "as", "at", "away", "back", "be", "beach", "beat",
			"black", "body", "brown", "but", "by", "can", "close", "come",
			"cut", "day", "did", "do", "door", "down", "eyes", "face", "find",
			"for", "from", "get", "gets", "go", "going", "gonna", "got", "has",
			"have", "he", "her", "here", "him", "his", "how", "i", "if", "in",
			"inside", "into", "is", "it", "jungle", "just", "know", "library",
			"like", "look", "looks", "lost", "main", "man", "matrix", "me",
			"mean", "my", "now", "of", "off", "oh", "on", "one", "open", "out",
			"over", "phone", "right", "room", "see", "sees", "she",
			"something", "sun", "tank", "that", "the", "their", "them", "then",
			"there", "they", "think", "this", "through", "to", "turns", "two",
			"up", "us", "walks", "wanna", "want", "was", "we", "well", "what",
			"when", "where", "who", "why", "with", "would", "yeah", "you",
			"your" };

	/**
	 * Liest Wörter aus der in path angegeben Datei, packt diese in eine Hashtabelle und gibt selbige zurück
	 * @param Pfad zur Datei
	 * @param hashSize
	 * @param fileLength
	 * @param hashMethod
	 * @return die Hashtabelle
	 */
	public static HashTable readFileAndHash(String path, int hashSize, String hashMethod) {
	
		try {
			Scanner scanner = new Scanner(new File(path));
			scanner.useDelimiter("[.,-?!#+ \\s]+");
			String token;
		
			while (scanner.hasNext()) {
				token = scanner.next();
				hashTable.put(token);
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Fehler mit der Datei  "+e.getLocalizedMessage());
			return null;
		}
		return hashTable;
	}

	/**
	 * Gibt die in der Hashtabelle gespeicherten Wörter und deren Vorkommnisse aus
	 * @param hashtable
	 */
	public static void printStatistics(HashTable hashtable) {
		for (int i = 0; i < words.length; i++) {
			if (hashtable.get(words[i]) != null) {
				System.out.println(words[i] + " : " + hashtable.get(words[i]).getInfo());
			}
		}
	}

	public static void main(String[] args) {
	
		int hashSize = 0;
		
		if (args.length == 0) {
			throw new IllegalArgumentException("Syntax: CountsWords Datei [hashSize][RSHash|JSHash]");
		}
		
		if (args.length >= 1) file = args[0];
		if (args.length >= 2) {
			try {
				hashSize = Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
				hashMethod = args[1];
				hashSize = 10;
			}
		}
		
		if ( args.length == 3 ) 		hashMethod = args[2];	
		if ( hashMethod.equals("") ) 	hashMethod = "RSHash";
		
		if (hashSize > 0) 				hashTable = new HashTable(hashSize, hashMethod);
		else throw new NumberFormatException("Die Hashsize muss größer als 0 sein !");
		
		try {
			hashTable = readFileAndHash(file, hashSize, hashMethod);
			if(hashTable != null){
				printStatistics(hashTable);
			}
			System.out.println("Anzahl der Kollisionen: " + hashTable.numberOfCollisions());
		} catch (Exception e) {
			System.out.println("Syntax: CountsWords Datei [hashSize][RSHash|JSHash]");
		}

	}

}