package maets.games;

public class Bio4UHD extends AbstractGame {
	
	public Bio4UHD() {
		super("C:\\Games\\Resident Evil 4 - HD Project\\Bin32\\Profile\\Player\\Saves","Saves","Maets/Bio4UHD");
	}
	
	@Override
	public void download() {
		System.out.println("BIO4 DOWNLOAD");
	}

	@Override
	public void install() {
		
	}

	@Override
	public void uninstall() {

	}
}