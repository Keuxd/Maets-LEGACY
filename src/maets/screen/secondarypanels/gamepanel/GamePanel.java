package maets.screen.secondarypanels.gamepanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.screen.secondarypanels.TopBarPanel;
import maets.screen.secondarypanels.librarypanel.LibraryPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 2970729061815351154L;

	protected JFrame parentFrame;
	
	public GamePanel(JFrame parentFrame, String gameName, ImageIcon gameBanner) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JPanel topBarPanel = new TopBarPanel(parentFrame, LibraryPanel.class, gameName);
		add(topBarPanel);
		
		JPanel leftPanel = new OptionsListPanel(gameBanner);
		add(leftPanel);
	}
}
