package maets.games;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import com.github.junrar.exception.RarException;

import maets.core.ExtractUtil;
import maets.download.cloudservices.Drive;

public class VampireSurvivors extends AbstractGame {
	
	private final String DEFAULT_PATH = "C:\\Maets\\VampireSurvivors"; 
	
	public VampireSurvivors() {
		super(System.getenv("APPDATA") + "\\Vampire_Survivors\\Local Storage","Local Storage","Maets/VampireSurvivors");
	}
	
	@Override
	public void download() throws IOException {
		File dir = new File(DEFAULT_PATH);
		dir.mkdirs();
		
		Drive.downloadFile("12kVjCc4Cc48ZROvjKu3qTcP-EIVLwTc5", DEFAULT_PATH + "\\VampireS.zip");
	}

	@Override
	public void install() throws IOException, RarException {		
		File zip = new File(DEFAULT_PATH).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".zip");
			}
		})[0];
		
		System.out.println("Starting extraction...");
		ExtractUtil.extract(zip.getPath(), DEFAULT_PATH);
		System.out.println("Finishing extraction...");
		
		if(zip.delete()) {
			System.out.println("Zip file deleted successfully");
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
	
	@Override
	public void run() throws Exception {
		FileFilter dirFilter = new FileFilter() {
			@Override
			public boolean accept(File dir) {
				return dir.isDirectory();
			}
		};
		
		File firstGameFolder = new File(DEFAULT_PATH).listFiles(dirFilter)[0];
		File secondGameFolder = firstGameFolder.listFiles(dirFilter)[0];
		
		Runtime.getRuntime().exec(secondGameFolder.getPath() + "\\VampireSurvivors.exe", null, secondGameFolder).waitFor();
	}
}