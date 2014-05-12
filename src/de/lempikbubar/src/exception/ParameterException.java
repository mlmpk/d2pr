package de.lempikbubar.src.exception;

import de.lempikbubar.src.config.Statics;

@SuppressWarnings("serial")
/**
 * Einfache Exception Klasse, die Daten für das Werfen von Parameter Exceptions enthält
 * @author mlempik
 *
 */
public class ParameterException extends Exception {
	private int type;
	private String message;
	
	public ParameterException(int type) {
		this.type = type;
		setMsg(type);
	}
	
	private void setMsg(int key){
		switch (key) {
		
		case Statics.FIRST_PARAMETER_NOT_VALID:
			message = Statics.FIRST_PARAMETER_EXCEPTION_MESSAGE;
			break;
		case Statics.SECOND_PARAMETER_NOT_VALID:	
			message = Statics.SECOND_PARAMETER_EXCEPTION_MESSAGE;
			break;
		case Statics.BLATT06_PARAMETER_EXCEPTION:
			message = Statics.BLATT06_PARAMETER_EXCEPTION_MESSAGE;
			break;
		default:
			message = Statics.PARAMETER_EXCEPTION_DEFAULT_MESSAGE;
			break;
		}
	}
	
	
	// --- Getter / Setter ---

	public int getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
	
	
}
