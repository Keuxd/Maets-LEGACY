package maets.screen.login.exceptions;

public class LoginException extends Exception {

	private static final long serialVersionUID = 9207084872475595757L;

	public LoginException(String errorMessage) {
		super(errorMessage);
	}
}