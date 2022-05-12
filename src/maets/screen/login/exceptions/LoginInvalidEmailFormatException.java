package maets.screen.login.exceptions;

public class LoginInvalidEmailFormatException extends LoginException {

	private static final long serialVersionUID = 1138124598412610654L;

	public LoginInvalidEmailFormatException() {
		super("Invalid email format.");
	}
}