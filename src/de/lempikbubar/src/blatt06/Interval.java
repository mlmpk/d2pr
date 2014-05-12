package de.lempikbubar.src.blatt06;

public class Interval {

	private int startInterval;
	private int endInterval;
	
	public Interval(int startInterval, int endInterval) {
		super();
		this.startInterval = startInterval;
		this.endInterval = endInterval;
	}

	public int getStart() {
		return startInterval;
	}

	public int getEnd() {
		return endInterval;
	}

	@Override
	public String toString() {
		
		return "[" + this.getStart() + "," + this.getEnd() + "]";
	}


	
}
