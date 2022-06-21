package maets.screen.secondarypanels.storepanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.screen.mainpanel.MainPanel;
import maets.screen.secondarypanels.TopBarPanel;

public class StorePanel extends JPanel {

	private static final long serialVersionUID = -7135230823508269053L;

	protected JFrame parentFrame;
	
	public StorePanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JPanel topBarPanel = new TopBarPanel(parentFrame, MainPanel.class, "Store");
		add(topBarPanel);
		
		JPanel searchBoxPanel = new SearchBoxPanel(parentFrame);
		add(searchBoxPanel);
	}
}