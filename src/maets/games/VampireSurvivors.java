package maets.games;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import maets.mega.Mega;
import maets.mega.exceptions.MegaException;
import maets.mega.exceptions.MegaInvalidRemotePathException;

public class VampireSurvivors {

	private String defaultLocalSavePath;
	private String defaultRemoteSavePath;
	
	public VampireSurvivors() {
		defaultRemoteSavePath = "Maets/VampireSurvivors";
		defaultLocalSavePath = System.getenv("APPDATA") + "\\Vampire_Survivors\\Local Storage";
	}
	
	public void updateRemoteSave(boolean createBackupSave) throws MegaException {
		String pathToRemove = (createBackupSave) ? "/Backup/" : "/Current/" ;
		
		try {
			Mega.remove(defaultRemoteSavePath + pathToRemove + "Local Storage/");
		} catch(MegaInvalidRemotePathException e) { // It'll happen in the first remote saving call
			System.out.println(e.getMessage() + " -> in updateRemoteState() trying to upload anyway");
		} catch(MegaException e) {
			e.printStackTrace();
		}
		
		if(createBackupSave) {
			try {
				Mega.mkdir(defaultRemoteSavePath + pathToRemove); // This is always will be "/Backup/"
				Mega.move(defaultRemoteSavePath + "/Current/Local Storage/", defaultRemoteSavePath + pathToRemove);
			} catch(MegaInvalidRemotePathException e) {
				System.out.println(e.getMessage() + " -> in updateRemoteState() backup version trying to upload anyway");
			} catch(MegaException e) {
				e.printStackTrace();
			}
		}
		
		Mega.upload(defaultLocalSavePath, defaultRemoteSavePath + "/Current/", true);
	}
	
	public void updateLocalSave() throws MegaException {
		File localStorageFolder = Paths.get(defaultLocalSavePath).toFile();
		if(localStorageFolder.exists()) {
			if(!deleteDir(localStorageFolder)) {
				System.out.println("ERROR - Local Storage folder exists, but couldn't be deleted, returning call");
				return;
			} else {
				System.out.println("Local Storage folder exists, local save successfully deleted");
			}
		}
		
		localStorageFolder.getParentFile().mkdirs();
		Mega.download(defaultRemoteSavePath + "/Current/Local Storage/", localStorageFolder.getParent());
	}
	
	private boolean deleteDir(File file) {
		try {
			File[] contents = file.listFiles();
			if(contents != null) {
				for(File f : contents) {
					if(!Files.isSymbolicLink(f.toPath())) {
						deleteDir(f);
					}
				}
			}
			file.delete();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}