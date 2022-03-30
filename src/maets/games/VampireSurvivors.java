package maets.games;

import java.io.File;
import java.io.IOException;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;

import maets.download.AnonFiles;

public class VampireSurvivors extends AbstractGame {
	
	private final String DEFAULT_PATH = "C:\\Maets\\VampireSurvivors"; 
	
	public VampireSurvivors() {
		super(System.getenv("APPDATA") + "\\Vampire_Survivors\\Local Storage","Local Storage","Maets/VampireSurvivors");
	}
	
	@Override
	public void download() throws IOException {
		File dir = new File(DEFAULT_PATH);
		dir.mkdirs();
		
		AnonFiles.downloadFile("L9tae6Q7x6", DEFAULT_PATH + "\\VampireS.rar");
	}

	@Override
	public void install() throws IOException, RarException {		
		File rar = new File(DEFAULT_PATH).listFiles()[0];

		System.out.println("Starting extraction...");
		Junrar.extract(rar.getPath(), DEFAULT_PATH);
		System.out.println("Finishing extraction...");
		
		if(rar.delete()) {
			System.out.println("Rar file deleted successfully");
		} else {
			System.out.println("ERROR - Rar file not deleted");
		}
	}
	
	@Override
	public void uninstall() {
		if(deleteDir(new File(DEFAULT_PATH))) {
			System.out.println("Vampire Survivors uninstalled successfully");
		} else {
			System.out.println("Error uninstalling Vampire Survivors");
		}
	}
	
	public void run() throws IOException {
		String gameVersionFolderName = new File(DEFAULT_PATH).list()[0];
		File gameFolder = new File(DEFAULT_PATH + "\\" + gameVersionFolderName + "\\" + gameVersionFolderName + "\\");
		
		Runtime.getRuntime().exec(gameFolder.getPath() + "\\VampireSurvivors.exe", null, gameFolder);
	}
}