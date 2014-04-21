package de.lempikbubar.src;

import java.util.Arrays;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;

public class Sortierung {

	public static String sortAlgorithm = Statics.MERGE_SORT;
	//public static String sortType = Statics.PARAMETER_TYPE_RANDOM;
	
	public static void insertionSort(int[] array) {
		// Invariante: Initialisierung
		// i = 1 => A[0 ... i-1] = A[0] enthält nur ein Element, welches trivialerweise sortiert ist.
		for (int i = 1; i < array.length; i++) {
			
			int key = array[i];
			int j = i-1;
			while (j>=0 && array[j] > key) {
				
				array[j+1] = array[j];
				// INVARIANTE: 
				// Es liegt immer ein sortierter Bereich vor. 
				// In jedem Iterationsschritt A[1..j-1] ist array[j] <= array[i] eine wahre Aussage
				assert array[j] <= array[i] : "Invariante falsch. Array["+j+"] <= Array["+i+"] trifft nicht zu.";
				j--;
			}
			array[j+1] = key;
			
		}
		
	}
	
	
	public static void mergeSort(int[] array){
		
		if (array.length > 1) {
            int q = array.length/2;

            int[] leftArray = Arrays.copyOfRange(array, 0, q);
            int[] rightArray = Arrays.copyOfRange(array,q,array.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array,leftArray,rightArray);
        }
		
	}


	private static void merge(int[] array, int[] links, int[] rechts) {
		
		int totElem = links.length + rechts.length;
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) {
            if ((li < links.length) && (ri<rechts.length)) {
                if (links[li] < rechts[ri]) {
                    array[i] = links[li];
                    i++;
                    li++;
                }
                else {
                    array[i] = rechts[ri];
                    i++;
                    ri++;
                }
            }
            else {
                if (li >= links.length) {
                    while (ri < rechts.length) {
                        array[i] = rechts[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= rechts.length) {
                    while (li < links.length) {
                        array[i] = links[li];
                        li++;
                        i++;
                    }
                }
            }
        }
		
	}


	public static boolean isSorted(int[] array) {

		for(int i = 1; i < array.length; i++)
		{
			if(array[i] < array[i-1])
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
			
			if(array.length <= 100) {
				System.out.println("\nUnsortiertes Array:");
				SortierungUtil.printArray(array);	
			}
		
			if(isSorted(array)){
				System.out.println("Feld sortiert!");
			}else{
				System.out.println("Feld NICHT sortiert!");
			}
			
			long runtime = 0;
			
			if(sortAlgorithm.equals( Statics.INSERTION_SORT )){
				System.out.println("\nNutze Sortieralgorithmus: "+ Statics.INSERTION_SORT);
				// Aufruf der Sortierungsroutine
				long start = System.currentTimeMillis();
				insertionSort(array);
				runtime = System.currentTimeMillis() - start;
			}else if(sortAlgorithm.equals( Statics.MERGE_SORT )){
				System.out.println("\nNutze Sortieralgorithmus: "+ Statics.MERGE_SORT);
				// Aufruf der Sortierungsroutine
				long start = System.currentTimeMillis();
				mergeSort(array);
				runtime = System.currentTimeMillis() - start;
			}
			
			System.out.println("\nDie Laufzeit des Sortieralgorithmus beträgt: " + runtime + "ms.");
			
			if(array.length <= 100){
				System.out.println("\nSortiertes Array:");
				SortierungUtil.printArray(array);
			}
			
			if( isSorted(array) ){
				System.out.println("Feld sortiert!");
			}else{
				System.out.println("Feld NICHT sortiert!");
			}
			

		} catch (ParameterException e) {
			System.out.println(e.getMessage());
		}

	}

}
