package de.lempikbubar.src.blatt09;

import java.util.ArrayList;

public class Partition {

	private static final String SYNTAX_MSG = "Syntax des Programmaufrufs: Partition [Ganzzahl 1] [Ganzzahl 2] [Ganzzahl ...n]";

	public static boolean partition(ArrayList<Integer> list){
		
		return true;
	}
	
	public static void main(String[] args) {
		
		if(args.length > 1){
			
			try {
				ArrayList<Integer> list = new ArrayList<>();
				for(int i = 0; i< args.length; i++){
					list.add(Integer.valueOf(args[i]));
				}
				
				System.out.println(partition(list));
				
			} catch (NumberFormatException e) {
				System.out.println(SYNTAX_MSG);
			}
			
			
			
		}else{
			System.out.println(SYNTAX_MSG);
		}
		
		
	}
	
}
