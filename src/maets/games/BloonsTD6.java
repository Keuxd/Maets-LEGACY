package maets.games;

import java.io.File;
import java.io.IOException;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;

import maets.mega.Mega;
import maets.mega.exceptions.MegaException;

public class BloonsTD6 extends AbstractGame {
	
	private final String DOWNLOAD_FILE_NAME = "Bloons.TD.6.v30.2.5032.rar";
	
	public BloonsTD6() {
		super(System.getenv("APPDATA") + "\\Goldberg SteamEmu Saves\\960090","960090","Maets/BloonsTD6");
	}

	@Override
	public void download() throws MegaException {
		String defaultPath = "C:\\Maets\\BloonsTD6";
		File dir = new File(defaultPath);
		dir.mkdirs();
		
		Mega.download("https://mega.nz/file/CfplVaAK#s8yu0h_AujWBW6teC8llsPOx7abFhPnHihSqDMwkYfI", defaultPath);
	}

	@Override
	public void install() {
		try {
			String defaultDownloadFilePath = "C:\\Maets\\BloonsTD6\\" + DOWNLOAD_FILE_NAME;
			
			System.out.println("Starting extraction...");
			Junrar.extract(defaultDownloadFilePath, "C:\\Maets\\BloonsTD6");
			System.out.println("Finishing extraction...");
			
			if(new File(defaultDownloadFilePath).delete()) {
				System.out.println("Rar file deleted successfully");
			} else {
				System.out.println("ERROR - Rar file not deleted");
			}
		} catch (IOException | RarException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws IOException {
		String downloadFileNameWithoutExtension = DOWNLOAD_FILE_NAME.substring(0, DOWNLOAD_FILE_NAME.length()-4);
		String defaultInstalationPath = "C:\\Maets\\BloonsTD6\\" + downloadFileNameWithoutExtension + "\\" + downloadFileNameWithoutExtension + "\\";
		Runtime.getRuntime().exec("C:\\Maets\\BloonsTD6\\Bloons.TD.6.v30.2.5032\\Bloons.TD.6.v30.2.5032\\BloonsTD6.exe", null, new File(defaultInstalationPath));
	}
}