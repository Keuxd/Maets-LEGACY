package maets.screen.mainpanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.core.Main;
import maets.mega.exceptions.MegaException;
import maets.screen.mainpanel.bottompanel.BottomPanel;
import maets.screen.mainpanel.midpanel.MidPanel;
import maets.screen.mainpanel.upperpanel.UpperPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 3696506270919324384L;
	
	private JFrame parentFrame;
	
	public MainPanel(JFrame parentFrame) {
		setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight());
		setLayout(null);
		
		JPanel upperPanel = null;
		JPanel midPanel = null;
		try {
			upperPanel = new UpperPanel(parentFrame);
			midPanel = new MidPanel(parentFrame);
		} catch(MegaException e1) {
			Main.unexpectedError(e1.getMessage(), parentFrame);
		}
		add(upperPanel);
		add(midPanel);
		
		BottomPanel bp = new BottomPanel();
		add(bp);
	}
}