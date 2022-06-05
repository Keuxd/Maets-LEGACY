package maets.screen.settingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import maets.screen.FrameManager;
import maets.screen.FrameManager.TransitionSide;
import maets.screen.mainpanel.MainPanel;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -5004871465253892818L;

	private JFrame parentFrame;
	
	public SettingsPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setLayout(null);
		setBounds(0, 0, 1920, 1080);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.contentPaneTransition(parentFrame, new MainPanel(parentFrame), TransitionSide.UP, 54);				
			}
		});
		btnNewButton.setBounds(10, 11, 313, 123);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(21, 156, 302, 128);
		add(btnNewButton_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 352, 254, 360);
		add(textPane);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(21, 291, 193, 54);
		add(chckbxNewCheckBox);
	}
}