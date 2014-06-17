package de.lempikbubar.src.blatt10;

public class HashTest {

	
	public static void main(String[] args) {
		HashTable tabletest = new HashTable(10);
		
		tabletest.put("Abgabe");
		tabletest.put("find");
		tabletest.put("Uhr");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		tabletest.put("Hallo");
		
		tabletest.printHashTable();
		
	}
	
}
