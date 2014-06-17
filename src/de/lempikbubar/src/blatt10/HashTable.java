package de.lempikbubar.src.blatt10;

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

		int hash = Math.abs((int) GeneralHashFunctionLibrary.RSHash(key) % hashArray.length);

		HashItem item = new HashItem(key, 1);

		LinkedList<HashItem> currentlist = hashArray[hash];

		if (!currentlist.isEmpty()) { // Liste an der Stelle hash ist nicht // leer, daher Suche nach Vorhandensein // des Items
		
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
	 * @param key als String
	 * @return HashItem
	 */
	public HashItem get(String key) {

		// berechne zunächst den Hashwert des Schlüssels
		int hash = (int) GeneralHashFunctionLibrary.RSHash(key) % hashArray.length;
		// Hole die Liste am entsprechenden Hash
		LinkedList<HashItem> currentList = hashArray[hash];
		
		if (!currentList.isEmpty()) {
			for(int i = 0; i < currentList.size();i++){
				// wenn der Key übereinstimmt, return das HashItem
				if (currentList.get(i).getKey().equals(key)) {
					return currentList.get(i);
				}
			}
		}
		
		return null;
	}

	public void clear() {
		new HashTable(hashArray.length);
	}

	public int numberOfCollisions() {
		
		return collisions;
	}

	public void printHashTable() {
		
		for (int i = 0; i < hashArray.length; i++) {
			System.out.println("Hash items with hash value " + i);
			for (int j = 0; j < hashArray[i].size(); j++) {
				HashItem item = hashArray[i].get(j);
				
				System.out.println("key: "+item.getKey() + " -- info: " + item.getInfo());
				
			}
			System.out.println("---------------------------------------");
			
		}
		
		System.out.println("The number of collisions is " + numberOfCollisions());
	}
}
