package maets.mega.exceptions;

public class MegaInvalidLocalPathException extends MegaException {

	private static final long serialVersionUID = 659184327473325131L;

	public MegaInvalidLocalPathException(int code) {
		super(getErrorMessage(code));
	}
	
	private static String getErrorMessage(int code) {
		switch(code) {
			case 53 : return "Unable to open local path";
			case 55 : return "Not a valid download folder";
			default : return "Unexpected 'InvalidLocalPath' error, code: " + code; 
		}
	}
}