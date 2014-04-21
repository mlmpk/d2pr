package de.lempikbubar.src;

import java.util.Random;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;

public class SortierungUtil {

	public static int getIntegerValueFromFirstParameter(String s) throws ParameterException {

		try {
			int intValue = Integer.valueOf(s);
			if( intValue < 1){
				throw new ParameterException( Statics.FIRST_PARAMETER_EXCEPTION );
			}
			return intValue;
		} catch (NumberFormatException e) {
			throw new ParameterException( Statics.FIRST_PARAMETER_EXCEPTION );
		}

	}

	public static int[] getArrayFillingType(int [] array, String type) throws ParameterException {

		if(type.equals(Statics.PARAMETER_TYPE_ASC)){
			for (int i = 0; i < array.length; i++) {
				array[i] = i;
			}
		}else if(type.equals(Statics.PARAMETER_TYPE_DESC)){
			int j = array.length;
			for (int i = 0; i < array.length; i++) {
				array[i] = j;
				j--;
			}
		}else if(type.equals(Statics.PARAMETER_TYPE_RANDOM)){
			Random numberGenerator = new Random();
			for(int i=0; i < array.length;i++ ){
				array[i] = numberGenerator.nextInt();
			}
		}else{
			throw new ParameterException(Statics.SECOND_PARAMETER_EXCEPTION);
		}
		
		System.out.println("Array mit Parameter [" + type + "] initialisiert. ");
		
		return array;

	}

	public static int[] initArray(String[] args) throws ParameterException {
		
		if(args.length == 0){
			throw new ParameterException(Statics.DEFAULT_PARAMETER_EXCEPTION);
		}else if(args.length == 1){
		
			int size = getIntegerValueFromFirstParameter(args[0]);
			int[] array = new int[size];
			
			return getArrayFillingType(array,Statics.PARAMETER_TYPE_RANDOM);
		
		}else if(args.length == 2){
		
			int size = getIntegerValueFromFirstParameter(args[0]);
			int[] array = new int[size];
			
			if(args[1].equals(Statics.INSERTION_SORT)){
				Sortierung.sortAlgorithm = Statics.INSERTION_SORT;
			}else if(args[1].equals(Statics.MERGE_SORT)){
				Sortierung.sortAlgorithm = Statics.MERGE_SORT;	
			}else{
				throw new ParameterException(Statics. THIRD_PARAMETER_EXCEPTION);
			}
			
			return getArrayFillingType(array, Statics.PARAMETER_TYPE_RANDOM);
		
		}else if(args.length == 3){
			
			int size = getIntegerValueFromFirstParameter(args[0]);
			int[] array = new int[size];
			
			if(args[1].equals(Statics.INSERTION_SORT)){
				Sortierung.sortAlgorithm = Statics.INSERTION_SORT;
			}else if(args[1].equals(Statics.MERGE_SORT)){
				Sortierung.sortAlgorithm = Statics.MERGE_SORT;	
			}else{
				throw new ParameterException(Statics. THIRD_PARAMETER_EXCEPTION);
			}
			
			return getArrayFillingType(array, args[2]);
			
		}else{
			throw new ParameterException(Statics.DEFAULT_PARAMETER_EXCEPTION);
		}
		
	}

	public static void printArray(int[] array) {
		
		for (int i : array) {
			System.out.print(i+" ");
		}
		
	}

}
