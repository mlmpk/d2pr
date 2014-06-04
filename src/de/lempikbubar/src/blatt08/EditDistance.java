package de.lempikbubar.src.blatt08;

import java.io.IOException;
import java.util.ArrayList;

public class EditDistance {

	public static boolean extendedOutput = false;
	public static char[] tmp;
	public static String REPLACE = "Ersetze";
	public static String INSERT = "Fuege";
	public static String DELETE = "Loesche";
	public static int[][] m;

	public static int distance(String a, String b) {
		int matrix[][] = new int[a.length() + 1][b.length() + 1];
		
		// Matrix initialisieren
		for (int i = 0; i < b.length() + 1; i++) {
			matrix[0][i] = i;
		}
		
		
		for (int i = 1; i < a.length() + 1; i++) {
			matrix[i][0] = i;
			for (int j = 1; j < b.length() + 1; j++) {
				
				//int match = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1; // wenn chars gleich sind, dann match = 1, sonst 0
				
//				int mini = matrix[i - 1][j] + 1;
//				
//				if (matrix[i][j - 1] + 1 < mini) {
//					mini = matrix[i][j - 1] + 1;
//				}
//				
//				if (matrix[i - 1][j - 1] + match < mini) {
//					mini = matrix[i - 1][j - 1] + match;
//				}
//				matrix[i][j] = mini;
				
					matrix[i][j] = matrix[i-1][j-1];
					if(a.charAt(i-1) != (b.charAt(j-1))){
						matrix[i][j]++;
					}
					if(matrix[i][j] > matrix[i-1][j] +1){
						matrix[i][j] = matrix[i-1][j] +1;
					}
					if(matrix[i][j] > matrix[i][j-1] +1){
						matrix[i][j] = matrix[i][j-1] +1;
					}
				
			}
			
//			printMatrix(matrix, a, b);
			
		}
		
		printMatrix(matrix, a, b);
		m = matrix;
		
		return matrix[a.length()][b.length()];

	}
	
	public static void printMatrix(int[][] matrix, String a, String b){
		System.out.print("    ");
		for(int i = 0; i<b.length();i++)
		{
			System.out.print(b.charAt(i)+" ");
		}
		System.out.println();
		for (int i = 0; i < matrix.length; i++) {
			
			if(i>0)
			System.out.print( a.charAt(i-1)+" ");
			else
				System.out.print("  ");
			for (int j = 0; j < matrix.length-1; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	public static void printEditOperations(int d[][], int i, int j, char[] y){
  		if (i>0 && d[i-1][j] + 1 == d[i][j]){
   			printEditOperations(d,i-1, j, y);
    		tmp[j] = y[j];
    		System.out.println("Kosten 1: fuege " + y[j] + " an Position " + (j+1) + " ein --> " + new String(tmp));
  		}
  		if (j>0 && d[i][j-1] + 1 == d[i][j]){
      		printEditOperations(d,i, j-1, y);
      		int n = j+1;
      		while(tmp[n] != ' ' && n < tmp.length-1){
        		tmp[n-1] = tmp[n];
        		n++;
      		}
      		tmp[n] = ' ';
      		System.out.println("Kosten 1: Loesche "+y[j]+" --> "+ new String(tmp));
  		}
 		if (i>0 && j>0 && d[i-1][j-1] + 1 == d[i][j]){
     	 	printEditOperations(d,i-1, j-1, y);
      		System.out.print("Kosten 1: Ersetze " + tmp[j-1]+"  durch "+  y[i-1]);
      		tmp[j-1] = y[i-1];
     		System.out.println(" --> " + new String(tmp));
  		}
  		if (i>0 && j>0 && d[i-1][j-1]  == d[i][j]){
   	 	  	printEditOperations(d,i-1, j-1, y);
   			System.out.println("Kosten 0: " + tmp[j-1] + " bleibt unverändert an Stelle " + j + " --> " + new String(tmp));
  		}
	}

	public static void main(String[] args) {

		switch (args.length) {

		case 1: // Pfad zu einer Datei erwartet

			getDistancesForFile(args);

			break;
		case 2: // Zwei Strings erwartet ODER Datei mit Option -o

			if (args[1].equals("-o")) {
				System.out.println("Erweiterte Ausgabe aktiviert...");

				extendedOutput = true;
				getDistancesForFile(args);

			} else {
				getDistancesForTwoStrings(args);
			}

			break;

		case 3: // Zwei Strings mit Option -o erwartet
			if (args[2].equals("-o")) {
				System.out.println("Erweiterte Ausgabe aktiviert...");
				extendedOutput = true;

				getDistancesForTwoStrings(args);
				
			} else {
				System.out.println(Statics.SYNTAX_MSG);
			}
			break;
		default:
			System.out.println(Statics.SYNTAX_MSG);
			break;
		}

	}

	private static void getDistancesForTwoStrings(String[] args) {
		String a = args[0];
		String b = args[1];
		
		System.out.println("Loesung fuer \"" + a
				+ "\" und \"" + b + "\" mit Gesamtkosten  " + distance(a, b) + ".");
		
		System.out.println("------------------------------------------------------------------");
		if(extendedOutput){
			tmp = a.toCharArray();
			printEditOperations(m, m.length-1, m[0].length-1,b.toCharArray());
		}
		
	}

	private static void getDistancesForFile(String[] args) {
		String path = args[0];
		try {
			ArrayList<String> lines = EditDistanceUtil.readLines(path);
			if (lines.size() > 1) {

				Distance minDistance = null;

				System.out.println("Berechne Editierdistanzen in jeder Kombination...");
				System.out.println("-------------------------------------------------");

				for (int i = 0; i < lines.size(); i++) {
					for (int j = 1; j < lines.size(); j++) {
						if (!lines.get(i).equals(lines.get(j))) { // nur, wenn sich die  Strings unterscheiden
							String a = lines.get(i);
							String b = lines.get(j);
							int distance = distance(a, b);

							System.out
									.println("Die minimale Editierdistanz von \""
											+ a
											+ "\" und \""
											+ b
											+ "\" ist "
											+ distance + ".");

							if (minDistance == null) {
								minDistance = new Distance(a, b, distance);
							} else {
								if (distance < minDistance.getDistance()) {
									minDistance.setA(a);
									minDistance.setB(b);
									minDistance.setDistance(distance);
								}
								
								if(extendedOutput){
									tmp = a.toCharArray();
									printEditOperations(m, m.length-1, m[0].length-1,b.toCharArray());
								}
							}

						}
					}
				}

				if (minDistance != null) {
					String output = "\nDie minimalste Editierdistanz hat die Kombination der Einträge \""
							+ minDistance.getA()
							+ "\" und \""
							+ minDistance.getB()
							+ "\" mit dem Wert "
							+ minDistance.getDistance();
					for (int i = 0; i < output.length(); i++) {
						System.out.print("-");
					}
					System.out.println(output);
				}

			} else {
				System.out.println("Die Datei muss wenigstens 2 Zeilen umfassen. Bitte korrigieren.");
			}
		} catch (IOException e) {
			System.out.println("Bitte Pfadangabe und Datei überprüfen!");
			System.out.println(e.getMessage());
		}
	}

}
