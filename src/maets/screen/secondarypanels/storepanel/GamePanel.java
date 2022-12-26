package maets.screen.secondarypanels.storepanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import maets.core.ConfigFile.OnlineConfigs;
import maets.core.Main;
import maets.core.Resources;
import maets.core.Resources.ResourceType;
import maets.screen.mainpanel.ResizableButtonsResponsivity;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8271674911298891309L;

	private String gameName;
	private JFrame parentFrame;
	
	public GamePanel(JFrame parentFrame) {
		this.gameName = "";
		this.parentFrame = parentFrame;
		setBounds(490, 110, 1510, 970);
		setLayout(null);
	}
	
	protected void populatePanel(String gameName, ImageIcon sample, String[] gameDescription) {
		this.gameName = gameName;
		
		JLabel gameTitle = new JLabel(gameName);
		gameTitle.setBounds(20, 0, 960, 100);
		gameTitle.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		gameTitle.setForeground(Color.WHITE);
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		int stringWidth = (int) gameTitle.getFontMetrics(gameTitle.getFont()).stringWidth(gameTitle.toString());
		if(stringWidth > 8055) {
			gameTitle.setHorizontalAlignment(SwingConstants.LEFT);
			gameTitle.setSize(getWidth(), gameTitle.getHeight());
		}
		add(gameTitle);
		
		JLabel gameImage = new JLabel(sample);
		gameImage.setBounds(20, 120, sample.getIconWidth(), sample.getIconHeight());
		gameImage.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
		add(gameImage);
		
		JLabel aboutTitle = new JLabel("<html><u>About this game</u></html>");
		aboutTitle.setBounds(1000, 120, 300, 50);
		aboutTitle.setFont(new Font("Nirmala UI", Font.BOLD, 35));
		add(aboutTitle);
		
		JLabel aboutDescription = new JLabel(gameDescription[0]);
		aboutDescription.setBounds(1000, 170, 420, 220);
		aboutDescription.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		aboutDescription.setVerticalAlignment(SwingConstants.TOP);
		aboutDescription.setHorizontalAlignment(SwingConstants.LEFT);
		add(aboutDescription);
		
		JLabel requirementsTitle = new JLabel("<html><u>System Requirements</u></html>");
		requirementsTitle.setBounds(1000, 390, 380, 50);
		requirementsTitle.setFont(new Font("Nirmala UI", Font.BOLD, 35));
		add(requirementsTitle);
		
		JLabel requirementsDescription = new JLabel(gameDescription[1]);
		requirementsDescription.setBounds(1000, 440, 420, 640);
		requirementsDescription.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		requirementsDescription.setVerticalAlignment(SwingConstants.TOP);
		requirementsDescription.setHorizontalAlignment(SwingConstants.LEFT);
		add(requirementsDescription);
		
		
		try {
			if(Main.online.isValueInConfig(OnlineConfigs.GAMES_IN_LIBRARY, gameName))
				return;
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		JButton addToLibrary = new JButton("Add to library");
		addToLibrary.setBounds(((gameImage.getWidth() / 2) + gameImage.getX()) - (420 / 2), gameImage.getY() + gameImage.getHeight() + 45, 420, 120);
		addToLibrary.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 50));
		addToLibrary.setFocusable(false);
		addToLibrary.setIcon(Resources.getImageIconEvenResized("library.png", ResourceType.UI, 60, Image.SCALE_AREA_AVERAGING));
		addToLibrary.addMouseListener(new ResizableButtonsResponsivity(addToLibrary) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				try {
					addToLibrary.setVisible(false);
					Main.online.addValueToConfig(OnlineConfigs.GAMES_IN_LIBRARY, gameName);
				} catch (IOException e1) {
					Main.unexpectedError("Couldn't add '" + gameName + "' to library: " + e1.getMessage(), parentFrame);
				}
			}
		});
		add(addToLibrary);
	}
	
	protected void repopulatePanel(String gameName, ImageIcon gameSample, String[] gameDescriptions) {
		removeAll();
		repaint();
		populatePanel(gameName, gameSample, gameDescriptions);
	}
	
	protected String getGameName() {
		return this.gameName;
	}
}