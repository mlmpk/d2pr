package de.lempikbubar.src.blatt08;

public class Distance {

	private String a;
	private String b;
	private int distance;
	
	public Distance(String a, String b, int distance){
		this.a = a;
		this.b = b;
		this.distance = distance;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int length) {
		this.distance = length;
	}
	
}
