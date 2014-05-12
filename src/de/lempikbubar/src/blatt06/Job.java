package de.lempikbubar.src.blatt06;

public class Job {
	
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
	

}
