package de.lempikbubar.src.blatt10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {

	private LinkedList<HashItem>[] hashtable;
	private String hashMethod = "RSHash";

	@SuppressWarnings("unchecked")
	public HashTable(int hashSize, String hashMethod) {
		
		hashtable = new LinkedList[hashSize];
		
		// initialisiere das Array mit LinkedList an jeder Stelle
		for (int i = 0; i < hashtable.length; i++) {
			hashtable[i] = new LinkedList<HashItem>();
		}

		this.hashMethod = hashMethod;
	}
	
	/**
	 * Bildet den Hashwert
	 * @param sequence
	 * @return Hashwert
	 */
	private long getHashCode(String sequence){
		if (hashMethod.equals("RSHash")) 
			return GeneralHashFunctionLibrary.RSHash(sequence);
		else
			return GeneralHashFunctionLibrary.JSHash(sequence);
	}

	/**
	 * Speichert Element in die Hashtable
	 * 
	 * @param key
	 */
	public void put(String key) {
	
		/*
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
		*/
		
		int hash = Math.abs((int)getHashCode(key));
		hash = hash % hashtable.length;

		HashItem item = get(key);
		if (item != null ) {
			item.setInfo(get(key).getInfo()+1);
		}else {
			hashtable[hash].add(new HashItem(key));
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
		//int hash = (int)(Math.abs(GeneralHashFunctionLibrary.RSHash(key)) % hashtab.length);
		int hash = Math.abs((int)getHashCode(key));
		hash = hash % hashtable.length;
		
		for (int i = 0; i < hashtable[hash].size(); i++) {
			// wenn der Key übereinstimmt, return das HashItem
			if (hashtable[hash].get(i).getKey().equals(key)) {
				return hashtable[hash].get(i);
			}
		}
		
		return null;
	}

	/**
	 * Löscht alles Einträge in der Hashtable
	 */
	public void clear() {
		for (int i = 0; i<hashtable.length; i++) {
			hashtable[i].clear();
		}
	}

	/**
	 * Gibt die Anzahl der Kollisionen zurück
	 * 
	 * @return Anzahl d. Kollisionen
	 */
	public int numberOfCollisions() {
		int collisions = 0;
		for(int i = 0; i < hashtable.length; i++){
			if(hashtable[i].size() > 1){
				collisions = collisions + hashtable[i].size() -1;
			}
		}
		return collisions;
	}

	/**
	 * Gibt die Hashtable auf der Konsole aus
	 */
	public void printHashTable() {

		for (int i = 0; i < hashtable.length; i++) {
			System.out.println("Hash items with hash value " + i);
			for (int j = 0; j < hashtable[i].size(); j++) {
				HashItem item = hashtable[i].get(j);

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
