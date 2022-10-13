package maets.screen.secondarypanels.librarypanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.screen.mainpanel.MainPanel;
import maets.screen.secondarypanels.TopBarPanel;

public class LibraryPanel extends JPanel {
	
	private static final long serialVersionUID = -446617019024012558L;

	protected JFrame parentFrame;
	
	public LibraryPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JPanel topBarPanel = new TopBarPanel(parentFrame, MainPanel.class, "Library");
		add(topBarPanel);
		
		JPanel middleGamesPanel = new GamesPanel(parentFrame);
		add(middleGamesPanel);
	}
}