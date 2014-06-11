package de.lempikbubar.src.blatt09;

import java.util.ArrayList;

public class Partition {

	private static final String SYNTAX_MSG = "Syntax des Programmaufrufs: Partition [Ganzzahl 1] [Ganzzahl 2] ...[Ganzzahl n]";

	/**
	 * Implementierung des Partitionierungs-Algorithmus
	 * @param ArrayList mit Integer Values
	 * @return true, wenn Partionierung möglich, false falls nicht
	 */
	public static boolean partition(ArrayList<Integer> list){
		int w = 0;
		
		for (int i = 0; i < list.size(); i++) {
			w = w + list.get(i);
		}
		
		if(w % 2 == 1) return false;
		
		int[][] matrix = new int[list.size()][w];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < w/2; j++) {
				if( j == 0 ){
					matrix[i][j] = 1;
				}else if ( i == 0){
					matrix[i][j] = 0;
				}else if ( matrix[i-1][j] == 1 || (list.get(i) <= j && matrix[i-1][j-list.get(i)] == 1)){
					matrix[i][j] = 1;
				}else{
					matrix[i][j] = 0;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		if(args.length > 1){
			
			try {
				ArrayList<Integer> list = new ArrayList<>();
				for(int i = 0; i< args.length; i++){
					list.add(Integer.valueOf(args[i]));		// Hinzufügen der eingegebenen Parameter in eine ArrayList
				}
				
				System.out.println( partition(list) );		// Aufruf des Partionierungs-Algorithmus
				
			} catch (NumberFormatException e) {
				System.out.println(SYNTAX_MSG);
				System.out.println(e.getLocalizedMessage());
			}
			
		}else{
			System.out.println(SYNTAX_MSG);
		}
		
	}
	
}
