package maets.screen.upperpanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TaskBarPanel extends JPanel {
 
	private static final long serialVersionUID = -4051125285167482804L;

	private JFrame parentFrame;
	
	public TaskBarPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(1220, 0, 407, 200);
	}
}