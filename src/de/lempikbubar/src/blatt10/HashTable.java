package de.lempikbubar.src.blatt10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {

	public static LinkedList<HashItem>[] hashArray;
	private int collisions;

	@SuppressWarnings("unchecked")
	public HashTable(int hashSize) {
		hashArray = new LinkedList[hashSize];

		// initialisiere das Array mit LinkedList an jeder Stelle
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new LinkedList<HashItem>();
		}

	}

	/**
	 * Speichert ein Element in die Hashtable
	 * 
	 * @param key
	 */
	public void put(String key) {

		int hash = Math.abs((int) GeneralHashFunctionLibrary.RSHash(key)
				% hashArray.length);

		HashItem item = new HashItem(key, 1);

		LinkedList<HashItem> currentlist = hashArray[hash];

		if (!currentlist.isEmpty()) { // Liste an der Stelle hash ist nicht leer, daher jetzt Suche nach Vorhandensein des Items

			boolean isItemInList = false;
			for (int i = 0; i < currentlist.size(); i++) {
				if (currentlist.get(i).getKey().equals(item.getKey())) {
					isItemInList = true;
					break;
				}

			}

			if (!isItemInList) {
				currentlist.add(item);
				collisions++;
			}

		} else { // Liste ist leer, item kann direkt eingefügt werden
			currentlist.add(item);
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
		int hash = (int) GeneralHashFunctionLibrary.RSHash(key) % hashArray.length;
		// Hole die Liste am entsprechenden Hash
		LinkedList<HashItem> currentList = hashArray[hash];

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
		new HashTable(hashArray.length);
	}

	/**
	 * Gibt die Anzahl der Kollisionen zurück
	 * 
	 * @return Anzahl d. Kollisionen
	 */
	public int numberOfCollisions() {
		// wird direkt bei Einfügen berechnett und zwar, wenn der Fall
		// vorliegt, dass bereits ein Item zum gleichen Hashwert existiert.
		// Alternativ müsste man an dieser Stelle, über das HashArray und über alle
		// Listen iterieren, wobei Listen mit nur einem Element nicht mitgezählt werden,
		// da es sich dabei um keine Kollisionen handeln kann.
		return collisions;
	}

	/**
	 * Gibt die Hashtable auf der Konsole aus
	 */
	public void printHashTable() {

		for (int i = 0; i < hashArray.length; i++) {
			System.out.println("Hash items with hash value " + i);
			for (int j = 0; j < hashArray[i].size(); j++) {
				HashItem item = hashArray[i].get(j);

				System.out.println("key: " + item.getKey() + " -- info: " + item.getInfo());

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
			while ((zeile = in.readLine()) != null && list.size() <= lines) {
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
