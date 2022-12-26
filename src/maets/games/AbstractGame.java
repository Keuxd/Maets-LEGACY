package maets.games;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import maets.mega.Mega;
import maets.mega.exceptions.MegaException;
import maets.mega.exceptions.MegaInvalidRemotePathException;

public abstract class AbstractGame {
	
	private String defaultLocalSavePath;
	private String saveFolderName;
	private String defaultRemoteSavePath;
	
	public AbstractGame(String defaultLocalSavePath, String saveFolderName, String defaultRemoteSavePath) {
		this.defaultLocalSavePath = defaultLocalSavePath;
		this.saveFolderName = saveFolderName;
		this.defaultRemoteSavePath = defaultRemoteSavePath;
	}
	
	public abstract void download() throws Exception;

	public abstract void install() throws Exception;
	
	public abstract void uninstall();
	
	public abstract void run() throws Exception;
	
	public void updateRemoteSave(boolean createBackupSave) throws MegaException {
		String pathToRemove = (createBackupSave) ? "/Backup/" : "/Current/" ;
		
		try {
			Mega.remove(defaultRemoteSavePath + pathToRemove + saveFolderName + "/");
		} catch(MegaInvalidRemotePathException e) { // It'll happen in the first remote saving call
			System.out.println(e.getMessage() + " -> in updateRemoteSave(), trying to upload anyway");
		} catch(MegaException e) {
			e.printStackTrace();
		}
		
		if(createBackupSave) {
			try {
				Mega.mkdir(defaultRemoteSavePath + pathToRemove); // This is always will be "/Backup/"
				Mega.move(defaultRemoteSavePath + "/Current/" + saveFolderName + "/", defaultRemoteSavePath + pathToRemove);
			} catch(MegaInvalidRemotePathException e) {
				System.out.println(e.getMessage() + " -> in updateRemoteSave() backup version, trying to upload anyway");
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
				System.out.println("ERROR updating local save - Save folder exists, but couldn't be deleted, returning method");
				return;
			} else {
				System.out.println("Save folder exists, local save successfully deleted");
			}
		}
		
		localStorageFolder.getParentFile().mkdirs();
		Mega.download(defaultRemoteSavePath + "/Current/" + saveFolderName + "/", localStorageFolder.getParent());
	}

	protected boolean deleteDir(File file) {
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