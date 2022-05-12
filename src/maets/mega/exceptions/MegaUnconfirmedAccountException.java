package maets.mega.exceptions;

public class MegaUnconfirmedAccountException extends MegaLoginFailedException {

	private static final long serialVersionUID = -7480918461890880337L;
	
	public MegaUnconfirmedAccountException() {
		super("Login Failed: Unconfirmed account. Please confirm your account.");
	}
}