package de.lempikbubar.src.blatt10;

import java.util.LinkedList;

public class HashTable {

	public static Object[] hashArray;
	
	public HashTable(int hashSize) {
		hashArray = new Object[hashSize];
		
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new LinkedList<HashItem>();
		}
		
	}

	public void put(String key) {
		
		long hash = GeneralHashFunctionLibrary.RSHash(key) % hashArray.length;
		
		HashItem item = new HashItem(key, 1);
		
		
		
		
	}

	public HashItem get(String key) {

		return null;
	}

	public void clear() {
		
	}

	public int numberOfCollisions() {
		return 0;
	}
	
	public void printHashTable(){}
}
