package maets.screen.login.exceptions;

public class LoginEmptyEmailException extends LoginException {

	private static final long serialVersionUID = -22416956629637978L;

	public LoginEmptyEmailException() {
		super("Email field is empty.");
	}
}