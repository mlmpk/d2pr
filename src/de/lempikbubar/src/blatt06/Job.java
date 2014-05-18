package de.lempikbubar.src.blatt06;

public class Job implements Comparable<Job>{
	
	private int dauer;
	private int	deadline;
	
	public Job(int dauer, int deadline) {
		super();
		this.dauer = dauer;
		this.deadline = deadline;
	}

	public int getDauer() {
		return dauer;
	}

	public int getDeadline() {
		return deadline;
	}
	
	@Override
	public String toString() {
		
		return "[" + this.getDauer() + "," + this.getDeadline() + "]";
	}

	@Override
	public int compareTo(Job o) {
		if( this.getDeadline()  > o.getDeadline()) return 1;
		else if(this.getDeadline() < o.getDeadline()) return -1;
		else return 0;
	}
	

}
