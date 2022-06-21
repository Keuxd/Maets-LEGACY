package maets.screen.secondarypanels.settingspanel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import maets.screen.mainpanel.MainPanel;
import maets.screen.secondarypanels.TopBarPanel;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -5004871465253892818L;

	protected JFrame parentFrame;
	
	public SettingsPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		add(new TopBarPanel(parentFrame, MainPanel.class, "Settings"));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(856, 294, 313, 123);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(437, 482, 302, 128);
		add(btnNewButton_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 352, 254, 360);
		add(textPane);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(21, 291, 193, 54);
		add(chckbxNewCheckBox);
		

	}
}