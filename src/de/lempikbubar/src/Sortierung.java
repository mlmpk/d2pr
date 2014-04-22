package de.lempikbubar.src;

import java.util.Arrays;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;
/**
 * Sortierung Klasse gemäß Praktikumsblatt 02 DAP2 - Ausgabe 11.04.14 - Abgabe Mittwoch, 23.04.14
 * @author mlempik & dbubar
 *
 */
public class Sortierung {

	// Der Sortier-Algorithmus ist standardmäßig auf Merg Sort eingestellt.
	// Dies geschieht, um der Aufgabenstellung Rechnung zu tragen ;)
	public static String sortAlgorithm = Statics.MERGE_SORT;
	
	/**
	 * Implementierung des Insertion Sort Algorithmus
	 * @param array
	 */
	public static void insertionSort(int[] array) {
		// Invariante: Initialisierung
		// i = 1 => A[0 ... i-1] = A[0] enthält nur ein Element, welches trivialerweise sortiert ist.
		for (int i = 1; i < array.length; i++) {
			
			int key = array[i];
			int j = i-1;
			while (j>=0 && array[j] > key) {
				// INVARIANTE: 
				// Es liegt immer ein sortierter Bereich vor. 
				// In jedem Iterationsschritt A[1..j-1] ist array[j] <= array[i] eine wahre Aussage
				array[j+1] = array[j];
				assert array[j] <= array[i] : "Invariante falsch. Array["+j+"] <= Array["+i+"] trifft nicht zu.";
				j--;
			}
			array[j+1] = key;
			
		}
		
	}
	
	/**
	 * Rekursive Implementierung des Merge Sort Algorithmus
	 * @param array
	 */
	public static void mergeSort(int[] array){
		
		if (array.length > 1) {
            int q = array.length/2; // finde die Mitte

            int[] leftArray = Arrays.copyOfRange(array, 0, q); // kopiert den Teil von 0 bis zur Mitte in ein neues Array namens leftArray
            int[] rightArray = Arrays.copyOfRange(array,q,array.length); // kopiert den Teil von der Mitte bis zum Ende in ein neues Array namens rightArray

            mergeSort(leftArray);  // sortiert den linken Teil
            mergeSort(rightArray); // sortiert den rechten Teil

            merge(array,leftArray,rightArray); // fügt beide Teile wieder zusammen
        }
		
	}

	/**
	 * Die Methode merge() fügt geteilten Arrays aufsteigend sortiert wieder zusammen
	 * @param array
	 * @param links
	 * @param rechts
	 */
	private static void merge(int[] array, int[] links, int[] rechts) {
		
		int size = links.length + rechts.length; // Größe des zusammenzusetzenden Arrays ermitteln
        
		//Initialisierung der Laufvariablen
		int i,li,ri;
        i = li = ri = 0;
        
        while ( i < size) {
            if ((li < links.length) && (ri < rechts.length)) { // die Laufvariablen müssen sich noch im Bereich der jeweilige Arrays befinden
                if (links[li] < rechts[ri]) { // Falls, der Wert im linken Array kleiner ist als im rechten, füge es in das Ergebnisarray ein
                    array[i] = links[li];
                    i++;
                    li++;
                }
                else {	// Ansonsten ist der  Wert im rechten Array größer >> Füge diesen ins Ergebnisarray ein
                    array[i] = rechts[ri];
                    i++;
                    ri++;
                }
            }
            else { 							     // sofern eine der beiden Laufvariablen dies nicht mehr ist, 
                if (li >= links.length) {		 // prüfe, ob es sich um das linke Array handelt,
                    while (ri < rechts.length) { // falls es so ist, fülle bis zum Ende
                        array[i] = rechts[ri];	 // die restlichen Werte des rechten Arrays in das Ergebnisarray
                        i++;
                        ri++;
                    }
                }
                if (ri >= rechts.length) {		// oder im umgekehrten Fall, rechts wurde bereits vollständig durchlaufen
                    while (li < links.length) { // falls es so ist, fülle bis zum Ende
                        array[i] = links[li];	// die restlichen Werte des linken Arrays in das Ergebnisarray
                        li++;
                        i++;
                    }
                }
            }
        }
		
	}

	/**
	 * Zeigt an, ob das angegebene Array sortiert ist
	 * @param array
	 * @return true, wenn sortiert, false wenn nicht
	 */
	public static boolean isSorted(int[] array) {

		for(int i = 1; i < array.length; i++)
		{
			if(array[i] < array[i-1]) // falls es einen Nachfolger gibt, der kleiner ist als der Vorgänger ist das Array nicht sortiert
			{
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {

		try {

			// Abfrage der Korrektheit der angegebenen Parameter
			int[] array = SortierungUtil.initArray(args);
			
			// Bei einer Array Größe <= 100 soll das Array ausgegeben werden. 
			// hier: vor dem Sortieren
			if(array.length <= 100) {
				System.out.println("\nUnsortiertes Array:");
				SortierungUtil.printArray(array);	
			}
		
			// Ausgabe, ob Array sortiert vorliegt oder nicht
			if( isSorted(array) ){
				System.out.println("Feld sortiert!");
			}else{
				System.out.println("Feld NICHT sortiert!");
			}
			
			long runtime = 0;
			
			// INSERTION SORT
			if( sortAlgorithm.equals( Statics.INSERTION_SORT ) ){
				System.out.println("\nNutze Sortieralgorithmus: "+ Statics.INSERTION_SORT);
				
				long start = System.currentTimeMillis(); 	  // Zeitstempel für Laufzeit-Messung für Insertion Sort
				insertionSort(array); 						  // Aufruf der Sortierungsroutine
				runtime = System.currentTimeMillis() - start; // Laufzeitberechnung
			// MERGE SORT	
			}else if( sortAlgorithm.equals( Statics.MERGE_SORT ) ){
				System.out.println("\nNutze Sortieralgorithmus: "+ Statics.MERGE_SORT);
				
				long start = System.currentTimeMillis();   	   // Zeitstempel für Laufzeit-Messung für Insertion Sort
				mergeSort(array); 							   // Aufruf der Sortierungsroutine
				runtime = System.currentTimeMillis() - start;  // Laufzeitberechnung
			}
			
			System.out.println("\nDie Laufzeit des Sortieralgorithmus beträgt: " + runtime + "ms.");
			
			// Bei einer Array Größe <= 100 soll das Array ausgegeben werden. 
			// hier: nach dem Sortieren
			if(array.length <= 100){
				System.out.println("\nSortiertes Array:");
				SortierungUtil.printArray(array);
			}
			
			// Ausgabe, ob Array sortiert vorliegt oder nicht
			if( isSorted(array) ){
				System.out.println("Feld sortiert!");
			}else{
				System.out.println("Feld NICHT sortiert!");
			}
			

		} catch (ParameterException e) { // Wir fangen hier sämtliche ParameterExceptions und geben den angegebenen Grund aus
			System.out.println(e.getMessage());
		}

	}

}
