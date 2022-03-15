package maets.mega.exceptions;

public class MegaLoginFailedException extends MegaException{
	
	private static final long serialVersionUID = -2729693702696036303L;

	public MegaLoginFailedException() {
		super("Invalid email or password");
	}
}
