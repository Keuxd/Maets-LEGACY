package maets.screen.secondarypanels.newlibrarypanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.core.ConfigFile;
import maets.core.ConfigFile.LocalConfigs;
import maets.core.ConfigFile.OnlineConfigs;
import maets.core.Main;
import maets.core.Resources;
import maets.core.Resources.ResourceType;
import maets.games.AbstractGame;
import maets.games.GamesTable;
import maets.screen.mainpanel.ResizableButtonsResponsivity;

public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = 2844410420699148362L;

	protected JFrame parentFrame;
	
	private JButton mainButton;
	private JLabel gameStatusLabel;
	
	private AbstractGame game;
	
	private GamesTable gt;
	
	public SelectionPanel(JFrame parentFrame, GamesTable gt) {
		this.parentFrame = parentFrame;
		setLayout(null);
		setBounds(0, 425, 1920, 655);
		populate(null);
		this.gt = gt;
	}
	
	protected void populate(String gameName) {
		if(gameName == null) return;
		
		JLabel l = new JLabel(gameName);
		l.setVerticalAlignment(SwingConstants.TOP);
		l.setFont(new Font("Verdana", Font.PLAIN, 35));
		l.setBounds(10, 381, 677, 135);
		add(l);
		
		mainButton = new JButton("");
		mainButton.setBounds(101, 70, 400, 160);
		mainButton.setIcon(Resources.getImageIconEvenResized("play-icon.png", ResourceType.UI, 80, Image.SCALE_AREA_AVERAGING));
		mainButton.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		mainButton.setBorder(null);
		mainButton.setHorizontalTextPosition(SwingConstants.LEFT);
		mainButton.addMouseListener(new ResizableButtonsResponsivity(mainButton) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				switch(getGameStatus(gameName)) {
					// Not Installed
					case "0" : {
						try {
							game.download();
							game.install();
							
							int gameIndex = ((GamesTable) Cache.get(CacheType.GAMES_TABLE)).getIndex(gameName);
							Main.local.changeValueInConfig(ConfigFile.LocalConfigs.GAMES_STATUS, gameIndex, "1");
							repopulate(gameName);
						} catch (Exception e1) {
							try {
								int gameIndex = ((GamesTable)Cache.get(CacheType.GAMES_TABLE)).getIndex(gameName);
								Main.local.changeValueInConfig(ConfigFile.LocalConfigs.GAMES_STATUS, gameIndex, "2");
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							Main.unexpectedError(game.getClass() + " error during download:\n" + e1.getMessage(), parentFrame);
						}
						break;
					}
				
					// Ready to play
					case "1" : {
						try {
							LocalTime ltBefore = LocalTime.now();
							
							game.run();
							
							// After game being closed
							LocalDateTime ldtAfter = LocalDateTime.now();
							Duration diff = Duration.between(ltBefore, ldtAfter.toLocalTime());
							
							int gameIndex = gt.getIndex(gameName);
							String playTime = Main.online.getValuesFromConfig(OnlineConfigs.PLAY_TIME)[gameIndex];
							
							DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
							LocalTime timeAfterGameplay = LocalTime.parse(playTime, format).plus(diff);
							
							Main.online.changeValueInConfig(OnlineConfigs.PLAY_TIME, gameIndex, timeAfterGameplay.format(format));
							
							System.out.println("Previous time -> " + playTime + "\nCurrent time -> " + timeAfterGameplay);
						} catch (Exception e1) {
							Main.unexpectedError(game.getClass() + " error during ready to play:\n" + e1.getMessage(), parentFrame);
						}
						break;
					}
					
					// Error during download
					case "2" : {
						try {
							game.uninstall();
							
							int gameIndex = ((GamesTable) Cache.get(CacheType.GAMES_TABLE)).getIndex(gameName);
							Main.local.changeValueInConfig(ConfigFile.LocalConfigs.GAMES_STATUS, gameIndex, "0");

							repopulate(gameName);
						} catch (IOException e1) {
							Main.unexpectedError(game.getClass() + " error in 'Error during download'\n" + e1.getMessage(), parentFrame);
						}
						break;
					}
				}
			}
		});
		add(mainButton);
		
		gameStatusLabel = new JLabel("");
		gameStatusLabel.setBounds(530, 70, 580, 70);
		gameStatusLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		add(gameStatusLabel);
		
		try {
			JLabel lblNewLabel_1;
			lblNewLabel_1 = new JLabel(Main.online.getValuesFromConfig(OnlineConfigs.PLAY_TIME)[gt.getIndex(gameName)]);
			lblNewLabel_1.setBounds(530, 180, 203, 52);
			lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
			add(lblNewLabel_1);
		} catch (IOException e1) {
			Main.unexpectedError("Error during read of play time for " + gameName + " -> " + e1.getMessage(), parentFrame);
		}
		
		JLabel lblNewLabel_1_1 = new JLabel("CC");
		lblNewLabel_1_1.setBounds(780, 180, 203, 52);
		lblNewLabel_1_1.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		add(lblNewLabel_1_1);
		
		
		applyGameStatusInComponents(gameName);
	}
	
	protected void repopulate(String gameName) {
		removeAll();
		repaint();
		populate(gameName);
	}
	
	private String getGameStatus(String gameName) {
		try {
			int gameIndex = gt.getIndex(gameName);
			String gameStatus = Main.local.getValuesFromConfig(LocalConfigs.GAMES_STATUS)[gameIndex];
			
			// Initialize it's AbstractGame object
			this.game = gt.solveGameObject(gameIndex);
			
			return gameStatus;
		} catch (IOException e) {
			e.printStackTrace();
			Main.unexpectedError(e.getMessage(), parentFrame);
			return null;
		}
	}
	
	private void applyGameStatusInComponents(String gameName) {
		switch(getGameStatus(gameName)) {
			// Not Installed
			case "0" : {
				gameStatusLabel.setText("Not Installed");
				mainButton.setText("Download    ");
				break;
			}
			
			case "1" : {
				gameStatusLabel.setText("Ready To Play");
				mainButton.setText("Play     ");
				break;
			}
			
			case "2" : {
				gameStatusLabel.setText("Error during download");
				mainButton.setText("Retry     ");
				break;
			}
			
			default : {
				Main.unexpectedError("Error in SelectionPanel.applyGameStatusInComponents()", parentFrame);
			}
			
		}
		
	}
}