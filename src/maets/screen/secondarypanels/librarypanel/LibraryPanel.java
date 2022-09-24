package maets.screen.secondarypanels.librarypanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.screen.mainpanel.MainPanel;
import maets.screen.secondarypanels.TopBarPanel;

import javax.swing.JButton;

public class LibraryPanel extends JPanel {
	
	private static final long serialVersionUID = -446617019024012558L;

	protected JFrame parentFrame;
	
	public LibraryPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JPanel topBarPanel = new TopBarPanel(parentFrame, MainPanel.class, "Library");
		add(topBarPanel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(76, 183, 596, 173);
		add(btnNewButton);
		
	}
}