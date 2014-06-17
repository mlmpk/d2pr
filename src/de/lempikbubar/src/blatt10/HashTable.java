package de.lempikbubar.src.blatt10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {

	private LinkedList<HashItem>[] hashtab;
	private int hashsize;

	@SuppressWarnings("unchecked")
	public HashTable(int hashSize) {
		this.hashsize = hashSize;
		hashtab = new LinkedList[hashSize];
		
		// initialisiere das Array mit LinkedList an jeder Stelle
		for (int i = 0; i < hashtab.length; i++) {
			hashtab[i] = new LinkedList<HashItem>();
		}

	}

	/**
	 * Speichert ein Element in die Hashtable
	 * 
	 * @param key
	 */
	public void put(String key) {
	
		long hashvalue = GeneralHashFunctionLibrary.RSHash(key);
		int hash = -1;
		if(hashvalue < 0 ){
			hashvalue = hashvalue * -1;
			int hashvalueint = (int) hashvalue;
			if (hashvalueint < 0) hashvalueint = hashvalueint * -1;
			hash = hashvalueint % hashtab.length;
			
		}else{
			long hashvalueLongmod = hashvalue % hashtab.length;
			int hashvalueint = (int) hashvalueLongmod;
			if (hashvalueint < 0) hashvalueint = hashvalueint * -1;
			hash = hashvalueint;
		}
		
		
		LinkedList<HashItem> currentlist = hashtab[hash];

		if (!currentlist.isEmpty()) { // Liste an der Stelle hash ist nicht leer, daher jetzt Suche nach Vorhandensein des Items

			boolean isItemInList = false;
			for (int i = 0; i < currentlist.size(); i++) {
				if (currentlist.get(i).getKey().equals(key)) {
					isItemInList = true;
					break;
				}

			}

			if (!isItemInList) {
				currentlist.add( new HashItem(key) );

			}

		} else { // Liste ist leer, item kann direkt eingefügt werden
			currentlist.add(new HashItem(key) );
		}

	}

	/**
	 * Gibt HashItem zurück mit jeweiligem Key
	 * 
	 * @param key als String
	 * @return HashItem
	 */
	public HashItem get(String key) {

		// berechne zunächst den Hashwert des Schlüssels
		int hash = (int)(Math.abs(GeneralHashFunctionLibrary.RSHash(key)) % hashtab.length);
		// Hole die Liste am entsprechenden Hash
		LinkedList<HashItem> currentList = hashtab[hash];

		if (!currentList.isEmpty()) {
			for (int i = 0; i < currentList.size(); i++) {
				// wenn der Key übereinstimmt, return das HashItem
				if (currentList.get(i).getKey().equals(key)) {
					return currentList.get(i);
				}
			}
		}

		return null;
	}

	/**
	 * Löscht alles Einträge in der Hashtable
	 */
	public void clear() {
		new HashTable(hashtab.length);
	}

	/**
	 * Gibt die Anzahl der Kollisionen zurück
	 * 
	 * @return Anzahl d. Kollisionen
	 */
	public int numberOfCollisions() {
		int collisions = 0;
		for(int i = 0; i < hashtab.length; i++){
			if(hashtab[i].size() > 1){
				collisions = collisions + hashtab[i].size() -1;
			}
		}
		return collisions;
	}

	/**
	 * Gibt die Hashtable auf der Konsole aus
	 */
	public void printHashTable() {

		for (int i = 0; i < hashtab.length; i++) {
			System.out.println("Hash items with hash value " + i);
			for (int j = 0; j < hashtab[i].size(); j++) {
				HashItem item = hashtab[i].get(j);

				System.out.println("\tkey: " + item.getKey() + " -- info: " + item.getInfo());

			}
			System.out.println("---------------------------------------");

		}

		System.out.println("The number of collisions is " + numberOfCollisions());
	}

	/**
	 * Die Methode liest zeilenweise aus der Datei zahlen.txt
	 * @param Anzahl der Zeilen, die verarbeitet werden sollen
	 * @return ArrayList vom Typ String
	 */
	public static ArrayList<String> readLines(int lines) {

		String file = System.getProperty("user.dir") + "/vorgaben10/zahlen.txt";

		ArrayList<String> list = new ArrayList<>();

		try {
			
			BufferedReader in = new BufferedReader(new FileReader(file));

			String zeile = null;
			while ((zeile = in.readLine()) != null && list.size() < lines) {
				list.add(zeile);
			}
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("Die Datei wurde nicht gefunden! " + e.getLocalizedMessage());

		} catch (IOException e) {
			System.out.println("Es gab ein Fehler in Zusammenhang mit der Datei" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return list;

	}

}
