package maets.core;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import maets.core.Cache.CacheType;
import maets.core.ConfigFile.LocalConfigs;
import maets.core.ConfigFile.OnlineConfigs;
import maets.games.GamesTable;
import maets.mega.Mega;
import maets.screen.MainFrame;
import maets.screen.login.LoginFrame;

public class Main {
	
	public static ConfigFile local;
	public static ConfigFile online;

	public static void main(String[] args) throws IOException {
		
		System.setProperty("sun.java2d.uiScale", "1.0");
		System.setProperty("sun.java2d.uiScale.enabled", "false");
		
		FlatLightLaf.setup(new FlatDarculaLaf());
		UIManager.put("ToolTip.font", new Font("Nirmala UI", Font.PLAIN, 20));
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(true);
		ToolTipManager.sharedInstance().setReshowDelay(0);
		
		try {
			Mega.init();
			Cache.set(CacheType.GAMES_TABLE, new GamesTable(460, 215, 960, 640));
			initializeConfigFiles();
			
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
	
	private static void initializeConfigFiles() throws IOException {
		final String defaultFolder = "C:\\Maets";
		
		local = new ConfigFile(LocalConfigs.class);
		local.initializeFile(defaultFolder, "local");
		
		online = new ConfigFile(OnlineConfigs.class);
		online.initializeFile(defaultFolder, "online");
		
		
		String[] gameStatus = local.getValuesFromConfig(LocalConfigs.GAMES_STATUS);
		int gamesAmount = ((GamesTable) Cache.get(CacheType.GAMES_TABLE)).getNames().size();
		
		// Initialize games status for first execution 
		if(gameStatus == null) {
			for(int i = 0; i < gamesAmount; i++) {
				local.addValueToConfig(LocalConfigs.GAMES_STATUS, "0");
			}
			// In case games were added on the way
		} else if(gameStatus.length < gamesAmount) {
			for(int i = 0; i < gamesAmount - gameStatus.length; i++) {
				local.addValueToConfig(LocalConfigs.GAMES_STATUS, "0");
			}
		}
		
		String[] playTime = online.getValuesFromConfig(OnlineConfigs.PLAY_TIME);
		
		if(playTime == null) {
			for(int i = 0; i < gamesAmount; i++) {
				online.addValueToConfig(OnlineConfigs.PLAY_TIME, "00:00:00");
			}
		} else if(playTime.length < gamesAmount) {
			for(int i = 0; i < gamesAmount - gameStatus.length; i++) {
				online.addValueToConfig(OnlineConfigs.PLAY_TIME, "00:00:00");
			}
		}
		
		String[] lastSession = online.getValuesFromConfig(OnlineConfigs.LAST_SESSION);
		if(lastSession == null) {
			for(int i = 0; i < gamesAmount; i++) {
				online.addValueToConfig(OnlineConfigs.LAST_SESSION, "0");
			}
		} else if(lastSession.length < gamesAmount) {
			for(int i = 0; i < gamesAmount - lastSession.length; i++) {
				online.addValueToConfig(OnlineConfigs.LAST_SESSION, "0");
			}
		}
		
		
	}
	
	public static void unexpectedError(String errorMessage, JFrame currentFrame) {
		try {
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if(runnable != null)
				runnable.run();
			
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