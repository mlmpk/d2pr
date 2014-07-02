package de.lempikbubar.src.blatt12;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<Edge> edgeList = new ArrayList<>();
	private int id;
	
	public Node(int id) {
		this.id = id;
	}
	
	/**
	 * Fügt eine neue Kante zu einem Knoten hinzu
	 * @param dst
	 */
	public void addEdge(Node dst) {
		Edge edge = new Edge(this, dst);
		edgeList.add(edge);
	}
	
	/**
	 * Prüft, ob die IDs der Knoten gleich sind
	 */
	public boolean equals(Object other) {
		
		if(other instanceof Node){
			if(this.id == ((Node) other).id){
				return true;
			}else{
				return false;
			}
		}
			
		return false;
	}

	public int getId() {
		return id;
	}

	
	public String toString() {
		
		String out = "Kanten am Knoten ("+this.getId()+"):\n";
		
		for(Edge edge : edgeList){
			out += edge.getSrc().getId() + ","+edge.getDst().getId()+"\n";
		}
		
		return out;
	}
	
}
