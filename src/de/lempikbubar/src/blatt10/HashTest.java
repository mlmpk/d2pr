package de.lempikbubar.src.blatt10;

import java.util.ArrayList;

public class HashTest {

	public static void main(String[] args) {

		try {

			if (args.length == 2) {

				int size = Integer.valueOf(args[0]);
				int lines = Integer.valueOf(args[1]);
				
				if(size > 0 && lines > 0){
					
					HashTable ht = new HashTable(size);
					ArrayList<String> entries = HashTable.readLines(lines);
					
					for (String entry : entries) {
						ht.put(entry);
					}
					
					ht.printHashTable();
					
				}else{
					System.out.println("Bitte positive Ganzzahlen eingeben!");
				}

			} else {
				System.out.println("Syntax: HashTest [Größe des Arrays][Anzahl der zulesenden Zahlen]");
			}

		} catch (NumberFormatException e) {
			System.out.println("Eingabe Falsch! Bitte korrigieren.");
		}

	}

}
