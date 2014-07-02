package de.lempikbubar.src.blatt12;

public class TestGraph {

//	BspGraphGross.graph
//	BspGraphKlein.graph
//	BspGraphMittel.graph
	
	private static String filepath = "/Users/MaikLempik/Documents/test/BspGraphKlein.graph";

	public static void main(String[] args) {
		
		Graph graph = Graph.fromFile( filepath );
		
		System.out.println( graph.toString() );
		
	}
	
}
