package maets.mega.exceptions;

public class MegaNotLoggedInException extends MegaException{

	private static final long serialVersionUID = -3185705670358994967L;

	public MegaNotLoggedInException() {
		super("Needs logging in/Not logged in.");
	}
}