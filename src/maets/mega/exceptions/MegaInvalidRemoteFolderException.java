package maets.mega.exceptions;

public class MegaInvalidRemoteFolderException extends MegaException {

	private static final long serialVersionUID = 1424063916096180699L;

	public MegaInvalidRemoteFolderException() {
		super("Couldn't find destination folder");
	}
}