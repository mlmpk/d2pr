package de.lempikbubar.src.exception;

import de.lempikbubar.src.config.Statics;

@SuppressWarnings("serial")
public class ParameterException extends Exception {
	private int type;
	private String message;
	
	public ParameterException(int type) {
		this.type = type;
		setMsg(type);
	}
	
	private void setMsg(int key){
		switch (key) {
		
		case Statics.FIRST_PARAMETER_EXCEPTION:
			message = Statics.FIRST_PARAMETER_EXCEPTION_MESSAGE;
			break;
		case Statics.SECOND_PARAMETER_EXCEPTION:	
			message = Statics.SECOND_PARAMETER_EXCEPTION_MESSAGE;
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
