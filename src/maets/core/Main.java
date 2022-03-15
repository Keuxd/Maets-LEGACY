package maets.core;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import maets.games.VampireSurvivors;
import maets.mega.Mega;
import maets.mega.exceptions.MegaException;

public class Main {
	
	public static void main(String[] args) {		
		
		try {
			Mega.init();
			VampireSurvivors vs = new VampireSurvivors();
		} catch(MegaException e) {
			e.printStackTrace();
		}
		
//		final JFileChooser fc = new JFileChooser();
//		int a = fc.showOpenDialog(null);
//		String name = fc.getSelectedFile().getName();
//		System.out.println(name + " -> " + a);
	}
	
	public static String getDesktopPath() {
		return FileSystemView.getFileSystemView().getHomeDirectory().getPath();
	}
	
	public static void test() {
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();

		// returns pathnames for files and directory
		paths = File.listRoots();
		// for each pathname in pathname array
		for(File path:paths)
		{
		    // prints file and directory paths
		    System.out.println("Drive Name: "+path);
		    System.out.println("Description: "+fsv.getSystemTypeDescription(path));
		}
	}
}
