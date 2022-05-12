package maets.games;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;

import maets.core.ExtractUtil;
import maets.mega.Mega;
import maets.mega.exceptions.MegaException;

public class BloonsTD6 extends AbstractGame {
	
	private final String DEFAULT_PATH = "C:\\Maets\\BloonsTD6";
	
	public BloonsTD6() {
		super(System.getenv("APPDATA") + "\\Goldberg SteamEmu Saves\\960090","960090","Maets/BloonsTD6");
	}

	@Override
	public void download() throws MegaException {
		File dir = new File(DEFAULT_PATH);
		dir.mkdirs();
		
		Mega.download("https://mega.nz/file/CfplVaAK#s8yu0h_AujWBW6teC8llsPOx7abFhPnHihSqDMwkYfI", DEFAULT_PATH);
	}

	@Override
	public void install() throws IOException, RarException {
		File rar = new File(DEFAULT_PATH).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".rar");
			}
		})[0];
		
		System.out.println("Starting extraction...");
		ExtractUtil.extract(rar.getPath(), DEFAULT_PATH);
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
			System.out.println("BloonsTD6 uninstalled successfully");
		} else {
			System.out.println("Error uninstalling BloonsTD6");
		}
	}
	
	public void run() throws IOException {
		String firstGameFolderName = new File(DEFAULT_PATH).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return dir.isDirectory();
			}
		})[0];
		
		File secondGameFolder = new File(DEFAULT_PATH + "\\" + firstGameFolderName + "\\" + firstGameFolderName);
		Runtime.getRuntime().exec(secondGameFolder + "\\BloonsTD6.exe", null, secondGameFolder);
	}
}