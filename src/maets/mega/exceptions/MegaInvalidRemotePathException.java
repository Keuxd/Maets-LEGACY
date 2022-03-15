package maets.mega.exceptions;

public class MegaInvalidRemotePathException extends MegaException {

	private static final long serialVersionUID = 356529294246527456L;

	public MegaInvalidRemotePathException() {
		super("No such file or directory");
	}
}