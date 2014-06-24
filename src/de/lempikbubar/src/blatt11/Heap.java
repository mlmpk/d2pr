package de.lempikbubar.src.blatt11;

import java.util.NoSuchElementException;

public class Heap {

	private int[] h;
	private int heapSize;
	private boolean verbose = false;

	/**
	 * Konstruktor
	 * @param n
	 */
	public Heap(int n) {
		
		try {
			if(n<1) throw new Exception("Ein Heap mit einem Größe von kleiner als 1 macht wenig Sinn!");
			h = new int[n+1];
			heapSize = 0;
		} catch (Exception e) {	
			System.err.println(e.getMessage());
		}
	
	}
	
	/**
	 * Gibt den Index des linken Kindes zurück
	 * @param i
	 * @return Index linker Knoten
	 */
	private int left(int i) {
        return i * 2;
	}

	/**
	 * Gibt den Index des rechten Kindes zurück
	 * @param i
	 * @return Index rechter Knoten
	 */
	private int right(int i) {
		return i * 2 + 1;
	}

	/**
	 * Gibt den Index des Vaters zurück
	 * @param i
	 * @return Index Vater
	 */
	private int parent(int i) {
		return i/2;
	}
	
	/**
	 * Stellt die Heap Bedingung wieder her
	 * @param i
	 */
	private void heapify(int i) {

		int largest=Integer.MIN_VALUE;
		int l = left(i);
		int r = right(i);
		
		if(l <= heapSize && h[l]>h[i]){
			largest = l;
		}else{
			largest = i;
		}
		
		if(r <= heapSize && h[r]>h[largest]){
			largest = r;
		}
		
		if(largest != i){
			swap(i,largest);
			heapify(largest);
		}
		
	}
	
	/**
	 * Tauscht zwei Felder im Array
	 * @param erster Index
	 * @param zweiter Index
	 */
	private void swap(int i, int largest) {
		int temp = h[largest];
		h[largest] = h[i];
		h[i] = temp;
	}
	
	/**
	 * Fügt ein Element in den Heap ein
	 * @param key
	 */
	public void insert(int key){
		if( verbose ) System.out.println("insert key: "+key);
		
		try {
			
			if(isFull()) throw new ArrayStoreException("Insert von key \""+key+"\" fehlgeschlagen. Der Heap fasst "+(h.length-1)+" Elemente und ist daher voll!");
			
			heapSize++;
			int i = heapSize;
			
			while(i>1 && h[parent(i)] < key){
				h[i] = h[parent(i)];
				i = parent(i);
			}
			
			h[i] = key;
			
			if( verbose ) printArray();
			
		} catch (ArrayStoreException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Nimmt das Größte Element aus dem Heap
	 * @return Maximum
	 */
	public int extractMax() {
		if( heapSize < 1 ) throw new NoSuchElementException("Es gibt nichts zu löschen!");
		
		int max = h[1];
		System.out.println("Entferne key = " +max+" aus Heap...");
		h[1] = h[heapSize];
		heapSize--;
		
		heapify(1);
		return max;
	}
	
	private boolean isFull(){
		return heapSize == h.length-1;
	}
	
	/**
	 * Zeigt den Heap auf der Konsole an
	 */
	public void printHeap() {
		
//		Das Limit für den Zeilenumbruch ist eine 2er Potenz entsprechend der max. Anzahl der Knoten je Ebene.
//		In der ersten Ebene daher 2^0 = 1, in der zweiten Ebene 2^1 = 2 usw.
//		Der Exponent wird immer dann inkrementiert, wenn das Limit erreicht wurde. 
		
		System.out.println("\nHeapSize: "+heapSize);	
		
		int limit = 1, counter = 0, exp = 0; 
		
		for (int i = 1; i <= heapSize; i++) {
			
			System.out.print(h[i] + " ");
			counter++;	// zählt die Knoten auf jeder Ebene
			
			if(counter == limit){ 
				System.out.println();
				limit=(int) Math.pow( 2, ++exp);
				counter=0;
			}
			
		}		
		
		System.out.println();
		
	}
	
	/**
	 * Zeigt das Heap als Array auf der Konsole
	 */
	private void printArray(){
		for (int i = 1; i < h.length; i++) {
			System.out.print("["+h[i]+"] ");
		}
		System.out.println();
	}
	
}
