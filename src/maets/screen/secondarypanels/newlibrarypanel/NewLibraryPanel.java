package maets.screen.secondarypanels.newlibrarypanel;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.core.Main;
import maets.games.GamesTable;
import maets.screen.mainpanel.MainPanel;
import maets.screen.secondarypanels.TopBarPanel;

public class NewLibraryPanel extends JPanel {

	private static final long serialVersionUID = -6647795449852373697L;

	protected JFrame parentFrame;
	
	public NewLibraryPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JPanel topBarPanel = new TopBarPanel(parentFrame, MainPanel.class, "Library");
		add(topBarPanel);
		
		GamesTable gt = (GamesTable)Cache.get(CacheType.GAMES_TABLE);
		
		SelectionPanel selectionPanel = new SelectionPanel(parentFrame, gt);
		add(selectionPanel);
		
		JPanel middlePanel = null;
		try {
			middlePanel = new GameTransitionPanel(parentFrame, selectionPanel, gt);
			add(middlePanel);
		} catch (IOException e) {
			Main.unexpectedError(e.getMessage(), parentFrame);
		}		
	}
}