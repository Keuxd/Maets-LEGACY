package maets.screen.secondarypanels.storepanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8271674911298891309L;

	private String gameName;
	
	public GamePanel() {
		this.gameName = "";
		setBounds(490, 110, 1510, 970);
		setLayout(null);
		
		populatePanel(this.gameName, new ImageIcon[1]);
	}
	
	protected void populatePanel(String gameName, ImageIcon[] samples) {
		this.gameName = gameName;
		
		JLabel gameTitle = new JLabel(gameName);
		gameTitle.setBounds(0, 0, 1510, 100);
		gameTitle.setFont(new Font("Nirmala UI", Font.PLAIN, 50));
		gameTitle.setForeground(Color.WHITE);
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		int stringWidth = (int) gameTitle.getFontMetrics(gameTitle.getFont()).stringWidth(gameTitle.toString());
		if(stringWidth > 8055) {
			gameTitle.setHorizontalAlignment(SwingConstants.LEFT);
			gameTitle.setSize(getWidth(), gameTitle.getHeight());
		}
		add(gameTitle);
		
		JLabel gameImage = new JLabel(samples[0]);
		gameImage.setBounds(20, 120, samples[0].getIconWidth(), samples[0].getIconHeight());
		add(gameImage);
		
		JLabel aboutTitle = new JLabel("About this game");
		aboutTitle.setBounds(900, 110, 300, 50);
		aboutTitle.setFont(new Font("Nirmala UI", Font.PLAIN, 30));
		add(aboutTitle);
		
		JLabel aboutDescription = new JLabel("bla\nbla\nbla\nbla\nbla");
		aboutDescription.setBounds(900, 170, 510, 160);
		add(aboutDescription);
		
		JLabel requirementsTitle = new JLabel("System Requirements");
		requirementsTitle.setBounds(900, 340, 300, 50);
		requirementsTitle.setFont(new Font("Nirmala UI", Font.PLAIN, 30));
		add(requirementsTitle);
		
		JLabel requirementsDescription = new JLabel("blo\nblo\nblo\nblo\nblo");
		requirementsDescription.setBounds(900, 400, 510, 300);
		add(requirementsDescription);
	}
	
	protected void repopulatePanel(String gameName, ImageIcon[] samples) {
		removeAll();
		repaint();
		populatePanel(gameName, samples);
	}
	
	protected String getGameName() {
		return this.gameName;
	}
}