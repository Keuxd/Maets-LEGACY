package maets.games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Bio4 {
	
	public Bio4() {
		
	}
	
	public static String smh() throws IOException {
		File file = new File("C:\\Games\\Resident Evil 4 - HD Project\\Bin32\\Profile\\Player\\Saves\\savegame00.sav");
		System.out.println(file.exists());

		
		return file.getPath();
	}
	
	
}
