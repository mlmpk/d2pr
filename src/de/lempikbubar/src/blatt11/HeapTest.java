package de.lempikbubar.src.blatt11;

public class HeapTest {

	public static void main(String[] args) {
		
		Heap heap = new Heap(18);
		
		int size = 18;
		for (int i = 1; i <= size ; i++) {
			heap.insert(i);
		}
	
		heap.printHeap();
		
		System.out.println("\n-------------------------------------------------\n");
		
		heap.extractMax();
		
		heap.printHeap();

	}

}
