package de.lempikbubar.src.blatt07;

import java.util.Random;

public class LCS {

	/**
	 * Die Methode randStr liefert einen Zufalls-String in der angegebenen Länge n
	 * @param n Länge der Folge
	 * @param r Random Object
	 * @return Zufalls-String
	 */
	public static String randStr(int n, Random r) {

		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while (--n >= 0) {
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString();
	}
	
	/**
	 * Die Methode longestCommonSubsequence initialisiert das Nummernfeld und ermittelt die längste gemeinsame Teilfolge
	 * @param firstSequence
	 * @param secondSequence
	 * @return das Nummernfeld
	 */
	public static int[][] longestCommonSubsequence(String firstSequence, String secondSequence){
		
		int m = firstSequence.length();
		int n = secondSequence.length();
		
		int[][] c = new int[m][n];
		
		for (int i = 0; i < m; i++) { 	// Initialisierung der Zeilen
			c[i][0] = 0;
		}
		
		for(int j = 0; j < n; j++){		// Initialisierung der Spalten 
			c[0][j] = 0;
		}
		
		for(int i = 1; i < m;i++){
			for(int j = 1; j<n;j++){
				getLengths(firstSequence, secondSequence, c, i, j);
			}
		}
		
		return c;
	}
	
	/**
	 * Die Methode getLengths ermitteln äber zwei gegebenen Folgen die Länge der längsten Teilfolge
	 * @param firstSequence ist die erste Folge
	 * @param secondSequence ist die zweite Folge
	 * @param c ist das Nummernfeld zu Zwischenspeicherung der Längen
	 * @param i ist der Laufindex der Zeilen
	 * @param j ist der Laufindes der Spalten
	 */
	private static void getLengths(String firstSequence, String secondSequence, int[][] c, int i, int j) {
		
		char xi = firstSequence.charAt(i);
		char yj = secondSequence.charAt(j);
		
		/* Hier unten Kommentar entfernen, um zu sehen was im Einzelnen passiert */
		//printLCS(c);
		//System.out.println("Betrachte : x("+i+")="+xi+ " y("+j+")="+yj);
		
		if( xi == yj){ // wenn die Zeichen an den Positionen i,j der beiden Folgen gleich sind.
			c[i][j] = c[i-1][j-1] + 1;	//nimm den Wert aus der linken oberen Position, addiere 1 hinzu und speicher ihn an die Stelle i,j
		}else{
			if(c[i-1][j] >= c[i][j-1]){	// falls der Wert der Zelle über dem aktuellen Element größer oder gleich dem der Zelle links vom aktuellen Element ist,...
				c[i][j] = c[i-1][j];	// überschreibe die aktuelle Zelle mit dem Wert der oberen Zelle
			}else{						// ansonsten
				c[i][j] = c[i][j-1];	// überschreibe aktuelle Zelle mit dem Wert der linken Zelle
			}
		}
		
	}

	public static void main(String[] args) {
		try {

			if (args.length == 1) { // wurde ein Parameter angegeben
				
				int n = Integer.valueOf(args[0]); // Parameter sollte eine Ganzzahl sein

				Random r = new Random();
				
				String firstSequence = randStr(n, r);	// Erzeuge die erste zufällige Folge
				String secondSequence = randStr(n, r);	// Erzeuge die zweite zufällige Folge
				
				
				System.out.println("Längste gemeinsame Teilfolge");
				System.out.println("-------------------------------------------");
				System.out.println("1. Folge: " + firstSequence);
				System.out.println("2. Folge: " + secondSequence);
				System.out.println("Länge n : " + n);
				System.out.println("-------------------------------------------");
				
				long start = System.currentTimeMillis();			// Start der Zeitmessung
				
				int[][] lcs = longestCommonSubsequence(firstSequence, secondSequence); 	// berechne die längste gemeinsame Teilfolge
				
				long runtime = System.currentTimeMillis() - start;	// Ende der Zeitmessung
				
				printLCS(lcs,firstSequence,secondSequence);
				
				//System.out.println("Längste gemeinsame Teilfolge: " + lcs);
				System.out.println("-------------------------------------------");
				System.out.println("Laufzeit: " + runtime + "ms.");
				
			} else {
				System.out.println("Bitte Größe der zu erzeugenden Teilfolgen angeben!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Der eingegebene Parameter ist keine Ganzzahl. Bitte korrigieren.");
		}
	}

	/**
	 * Gibt das ZahlenArray auf der Konsole aus
	 * @param lcs
	 * @param secondSequence 
	 * @param firstSequence 
	 */
	private static void printLCS(int[][] lcs, String firstSequence, String secondSequence) {
		if(lcs.length <= 20){
			System.out.print("\n    ");
			for(int j=0;j< secondSequence.length(); j++){
				System.out.print(secondSequence.charAt(j)+ " ");
			}
			System.out.println();
			
			for (int i = 0; i < lcs.length; i++) {
				System.out.print(firstSequence.charAt(i)+" - ");
				for (int j = 0; j < lcs.length; j++) {
					System.out.print(lcs[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("Die längeste gemeinsame Teilfolge hat die Länge: "+lcs[lcs.length-1][lcs.length-1]);
			System.out.println();
		}
		else{
			System.out.println("Die längeste gemeinsame Teilfolge hat die Länge: "+lcs[lcs.length-1][lcs.length-1]);
		}	
	}
}
