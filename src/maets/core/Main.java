package maets.core;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import maets.mega.Mega;
import maets.screen.MainFrame;
import maets.screen.login.LoginFrame;

public class Main {

	public static void main(String[] args) throws IOException {
		
		FlatLightLaf.setup(new FlatDarculaLaf());
		System.setProperty("sun.java2d.uiScale", "1.0");
		UIManager.put("ToolTip.font", new Font("Nirmala UI", Font.PLAIN, 20));
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(true);
		ToolTipManager.sharedInstance().setReshowDelay(0);
		
		try {
			Mega.init();
			
			if(!Mega.isLoggedIn()) {
				new LoginFrame().setVisible(true);
			} else {
				new MainFrame().setVisible(true);
			}
			System.out.println("FINISH");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//		new Ayo().setVisible(true);
//		final JFileChooser fc = new JFileChooser();
//		fc.setBackground(new Color(255,0,0,64));
//		int a = fc.showOpenDialog(null);
//		String name = fc.getSelectedFile().getName();
//		System.out.println(name + " -> " + a);
	}
	
	public static void unexpectedError(String errorMessage, JFrame currentFrame) {
		try {
			JLabel errorLabel = new JLabel(errorMessage);
			errorLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
			JOptionPane.showMessageDialog(null, errorLabel, "Unexpected Error", JOptionPane.ERROR_MESSAGE);
			
			currentFrame.dispose();
			
			Mega.quit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public static ImageIcon getImageIconInResources(String imageNameWithExtension, Integer size, Integer imageFlag) {
		ImageIcon ic = new ImageIcon(Main.class.getResource("/resources/" + imageNameWithExtension));
		
		if(size != null && imageFlag != null) {
			ic = new ImageIcon(ic.getImage().getScaledInstance(size, size, imageFlag));
		}
		
		return ic;
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
		    System.out.println("Drive Name: "+ path);
		    System.out.println("Description: "+ fsv.getSystemTypeDescription(path));
		}
	}
}