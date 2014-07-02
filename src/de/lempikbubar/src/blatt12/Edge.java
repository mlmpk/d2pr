package de.lempikbubar.src.blatt12;

public class Edge {

	private Node src;
	private Node dst;
	
	// Konstruktor
	public Edge(Node n1, Node n2) {
		src = n1;
		dst = n2;
	}


	public Node getSrc() {
		return src;
	}


	public void setSrc(Node src) {
		this.src = src;
	}


	public Node getDst() {
		return dst;
	}


	public void setDst(Node dst) {
		this.dst = dst;
	}
	
}
