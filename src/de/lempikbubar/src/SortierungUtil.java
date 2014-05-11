package de.lempikbubar.src;

import java.util.Random;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;
/**
 * 
 * @author mlempik & dbubar
 *
 */
public class SortierungUtil {

	/**
	 * getIntegerValueFromFirstParameter() prüft, ob sich der übergebene String zu einem Integer Wert casten lässt.
	 * Ist dies nicht der Fall, eine Exception geschmissen, die auf die richtige Syntax hinweist 
	 * @param Parameter als String
	 * @return Parameter als Integer Wert
	 * @throws ParameterException
	 */
	public static int getIntegerValueFromFirstParameter(String s) throws ParameterException {

		try {
			int intValue = Integer.valueOf(s); // Parsing der Integer Value aus dem String
			// ist der Angegebene Wert kleiner als 1 wird eine Exception geworfen, da ein Array mit einer solchen Größe keinen Sinn macht. 
			// Naja, 1 eigentlich auch nicht, da dies ja bereits sortiert wäre, wir lassen es hier trotzdem durch, weil es in der Aufgabenstellung nicht explizit erwähnt wird.
			if( intValue < 1){ 
				throw new ParameterException( Statics.FIRST_PARAMETER_NOT_VALID );
			}
			
			return intValue;
			
		} catch (NumberFormatException e) { // Die NumberFormatException wird hier gefangen, damit eine eigens erzeugte ParameterException geworfen werden kann.
			throw new ParameterException( Statics.FIRST_PARAMETER_NOT_VALID );
		}

	}

	/**
	 * getArrayFillingType() füllt das Array mit Daten entsprechend der Typ Angabe
	 * @param das zu füllende Array
	 * @param den Typ. Zulässig sind auf, ab oder rand
	 * @return das gefüllte Array
	 * @throws ParameterException
	 */
	public static int[] getArrayFillingType(int [] array, String type) throws ParameterException {

		// Aufsteigend füllen
		if(type.equals(Statics.PARAMETER_TYPE_ASC)){
			for (int i = 0; i < array.length; i++) {
				array[i] = i;
			}
		// Absteigend füllen	
		}else if(type.equals(Statics.PARAMETER_TYPE_DESC)){
			int j = array.length;
			for (int i = 0; i < array.length; i++) {
				array[i] = j;
				j--;
			}
		// mit randomisierten Werten füllen
		}else if(type.equals(Statics.PARAMETER_TYPE_RANDOM)){
			Random numberGenerator = new Random();
			for(int i=0; i < array.length;i++ ){
				array[i] = numberGenerator.nextInt();
			}
		// ein nicht zulässiger Parameter wurde angegeben	
		}else{
			throw new ParameterException(Statics.SECOND_PARAMETER_NOT_VALID);
		}
		
		System.out.println("Array mit Parameter [" + type + "] initialisiert. ");
		
		return array;

	}

	/**
	 * Die Methode initArray initialisiert das Array anhand der angegebenen Parameter
	 * @param args
	 * @return das angelegte und initialisierte Array
	 * @throws ParameterException
	 */
	public static int[] initArray(String[] args) throws ParameterException {
		
		if(args.length == 0){ // Fallunterscheidung: Es wurde kein Parameter angegeben
			throw new ParameterException(Statics.DEFAULT_PARAMETER_EXCEPTION);
		}else if(args.length == 1){// Fallunterscheidung: Es wurde 1 Parameter angegeben
		
			// Parameter 1 darf nur ein Integer Wert sein, das wird in der Methode getIntegerValueFromFirstParameter sichergestellt
			int size = getIntegerValueFromFirstParameter(args[0]); 
			int[] array = new int[size];
			
			return getArrayFillingType(array, Statics.PARAMETER_TYPE_RANDOM); // Bei der Angabe lediglich eines Parameters, soll das Array mit randomisierten Werten erzeugt werden
		
		}else if(args.length == 2){ // Fallunterscheidung: Es wurden 2 Parameter angegeben
		
			// Parameter 1 darf nur ein Integer Wert sein, das wird in der Methode getIntegerValueFromFirstParameter sichergestellt
			int size = getIntegerValueFromFirstParameter(args[0]);
			int[] array = new int[size];
			
			// Parameter 2 darf bei Angabe nur die Werte insert oder merge annehmen.
			// Falls ein anderer Wert übergeben wird, wird eine Exception geworfen
			if(args[1].equals(Statics.INSERTION_SORT)){
				Sortierung.sortAlgorithm = Statics.INSERTION_SORT;
			}else if(args[1].equals(Statics.MERGE_SORT)){
				Sortierung.sortAlgorithm = Statics.MERGE_SORT;	
			}else if(args[1].equals(Statics.QUICK_SORT)){
				Sortierung.sortAlgorithm = Statics.QUICK_SORT;
			}
			else
			{
				throw new ParameterException(Statics.THIRD_PARAMETER_NOT_VALID);
			}
			
			return getArrayFillingType(array, Statics.PARAMETER_TYPE_RANDOM); // Bei der Angabe zweier Parameter, soll das Array ebenfalls mit randomisierten Werten erzeugt werden
		
		}else if(args.length == 3){ // Fallunterscheidung: Es wurden 3 Parameter angegeben
			
			// Parameter 1 darf nur ein Integer Wert sein, das wird in der Methode getIntegerValueFromFirstParameter sichergestellt
			int size = getIntegerValueFromFirstParameter(args[0]);
			int[] array = new int[size];
			
			// Parameter 2 darf bei Angabe nur die Werte insert oder merge annehmen.
			// Falls ein anderer Wert übergeben wird, wird eine Exception geworfen
			if(args[1].equals(Statics.INSERTION_SORT)){
				Sortierung.sortAlgorithm = Statics.INSERTION_SORT;
			}else if(args[1].equals(Statics.MERGE_SORT)){
				Sortierung.sortAlgorithm = Statics.MERGE_SORT;	
			}else if(args[1].equals(Statics.QUICK_SORT)){
				Sortierung.sortAlgorithm = Statics.QUICK_SORT;	
			}else{
				throw new ParameterException(Statics.THIRD_PARAMETER_NOT_VALID);
			}
			
			return getArrayFillingType(array, args[2]); // Parameter wird zur Prüfung an Methode getArrayFillingType() übergeben
			
		}else{
			// Bei Angabe von mehr als 3 Parameter wird die Default Parameter Exception geworfen
			throw new ParameterException(Statics.DEFAULT_PARAMETER_EXCEPTION);
		}
		
	}

	/**
	 * printArray() gibt den Inhalt des Arrays auf der Konsole aus
	 * @param array
	 */
	public static void printArray(int[] array) {
		
		for (int i : array) {
			System.out.print(i+" ");
		}
		
	}

}
