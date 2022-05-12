package maets.screen.login.exceptions;

public class LoginEmptyPasswordException extends LoginException {

	private static final long serialVersionUID = 56664582898511343L;

	public LoginEmptyPasswordException() {
		super("Password field is empty.");
	}
}