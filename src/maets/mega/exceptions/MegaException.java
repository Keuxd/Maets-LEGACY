package maets.mega.exceptions;

public class MegaException extends Exception {

	private static final long serialVersionUID = 4937175874529082950L;	
	
	public MegaException(int code) {
		super("Unexpected error, code: " + code);
	}
	
	public MegaException(String errorMessage) {
		super(errorMessage);
	}
}