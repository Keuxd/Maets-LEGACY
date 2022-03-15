package maets.mega.exceptions;

public class MegaInvalidRemoteNodesException extends MegaException {

	private static final long serialVersionUID = 3167243817717561388L;

	public MegaInvalidRemoteNodesException() {
		super("Nodes not found");
	}
}