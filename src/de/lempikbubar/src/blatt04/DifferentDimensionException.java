package de.lempikbubar.src.blatt04;

@SuppressWarnings("serial")
public class DifferentDimensionException extends Exception {

	@Override
	public String getMessage() {
		return "Die Dimensionen der zu untersuchenden Punkte stimmen nicht überein!";
	}
	
}
