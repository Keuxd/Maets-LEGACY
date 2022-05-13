package maets.screen.upperpanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.mega.exceptions.MegaException;

public class UpperPanel extends JPanel {

	private static final long serialVersionUID = -2496740336679650741L;
	
	private JFrame parentFrame;
	
	public UpperPanel(JFrame parentFrame) throws MegaException {
		this.parentFrame = parentFrame;
		setLayout(null);
		setBounds(0, 0, 1920, 200);
		
		JPanel userPanel = new UserPanel(parentFrame);
		add(userPanel);
		
		JPanel taskBarPanel = new TaskBarPanel(parentFrame);
		add(taskBarPanel);
		
		JPanel timePanel = new TimePanel();
		add(timePanel);
	}
}