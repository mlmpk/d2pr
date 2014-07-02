package de.lempikbubar.src.blatt12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

	private ArrayList<Node> nodelist;

	/**
	 * Konstruktor für neuen Graphen
	 */
	public Graph() {
		nodelist = new ArrayList<Node>();
	}

	/**
	 * Prüft, ob ein Node bereits im Graphen ist
	 * @param id
	 * @return true / false
	 */
	public boolean contains(int id) {

		for (Node node : nodelist) {
			if (node.getId() == id) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Fügt einen Node in den Graphen ein, sofern er nicht existiert
	 * @param id
	 */
	public void addNode(int id) {
		if (!contains(id)) {
			nodelist.add(  new Node(id) );
		}
	}

	/**
	 * Gibt einen Node anhand seiner Id zurück und null falls der nicht vorhanden ist.
	 * @param id
	 * @return Node
	 */
	public Node getNode(int id) {

		for (Node node : nodelist) {
			if (node.getId() == id) {
				return node;
			}
		}

		return null;
	}

	/**
	 * Fügt eine neue Kante hinzu. Quell- und Zielknoten müssen dafür vorhanden sein
	 * @param src
	 * @param dst
	 */
	public void addEdge(int src, int dst) {

		if (contains(src) && contains(dst)) {

			Node srcNode = getNode(src);
			Node dstNode = getNode(dst);

			srcNode.addEdge(dstNode);

		}

	}

	/**
	 * Liest Graph aus einer Datei
	 * @param filepath
	 * @return Graph
	 */
	public static Graph fromFile(String filepath) {

		try {

			ArrayList<String[]> list = new ArrayList<>();
			// lese Datei zeilenweise
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				list.add(zeile.split(","));	// Splitte am Komma
			}
			in.close();

			Graph graph = new Graph();

			// füge Nodes in dem Graphen ein 
			for (String[] node : list) {
				graph.addNode(Integer.valueOf(node[0]));	
				graph.addNode(Integer.valueOf(node[1]));
			}
			
			// Füge Kanten in den Graphen ein
			for (String[] node : list) {
				graph.addEdge(Integer.valueOf(node[0]), Integer.valueOf(node[1]));				
			}
			
			return graph;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	
	public String toString(){
		
		String out = "Nodes im Graph:\n";
		
		for (Node node : nodelist) {
			out += node.getId() + " ";
		}
		
		out += "\n";
		
		for(Node node: nodelist){
			out += node.toString();
		}
		
		return out;
	}
	
}
