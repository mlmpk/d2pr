package de.lempikbubar.src.blatt10;

import java.util.ArrayList;

public class HashTest {

	public static void main(String[] args) {

		
//		System.out.println("---> " + -98381897 % 10+" ???");
		
//		HashTable ht = new HashTable(10);
//		ht.put("382952709");
//		ht.printHashTable();
		
		String hashMethod = "RSHash";
		
		try {

			if (args.length == 2) {
				
				int hashSize = Integer.valueOf(args[0]);
				int lines = Integer.valueOf(args[1]);
				
				if(hashSize > 0 && lines > 0){
					
					HashTable ht = new HashTable(hashSize, hashMethod);
					ArrayList<String> entries = HashTable.readLines(lines);

//					System.out.println("Größe: "+entries.size());
				
					for (String entry : entries) {
						
//						if(entry.equals("382952709")){
//							long bla = GeneralHashFunctionLibrary.RSHash(entry);
//							System.out.print("longHash: "+bla+" ");
//							if(bla <0) bla = bla * -1;
//							System.out.print("longHash positive: "+bla+" ");
//							int blaInt = (int) bla;
//							System.out.print("longHash integer: "+blaInt+" ");
//							if(blaInt <0) blaInt = blaInt * -1;
//							System.out.print("longHash integer positive: "+blaInt+" ");
//							System.out.print("Entry: "+entry+" Hash: "+blaInt % 10+"\n");
//						}
						
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
