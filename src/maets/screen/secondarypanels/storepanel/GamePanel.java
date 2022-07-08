package maets.screen.secondarypanels.storepanel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8271674911298891309L;

	private String gameName;
	
	public GamePanel() {
		this.gameName = "";
		setBounds(490, 110, 1510, 970);
		setLayout(null);
	}
	
	protected void populatePanel(String gameName) {
		this.gameName = gameName;
		
		JButton btnNewButton = new JButton(gameName);
		btnNewButton.setBounds(0, 852, 228, 169);
		add(btnNewButton);
	}
	
	protected void repopulatePanel(String gameName) {
		removeAll();
		repaint();
		populatePanel(gameName);
	}
	
	protected String getGameName() {
		return this.gameName;
	}
}