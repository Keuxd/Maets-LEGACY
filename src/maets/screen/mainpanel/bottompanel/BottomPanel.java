package maets.screen.mainpanel.bottompanel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import maets.core.Resources;
import maets.core.Resources.ResourceType;

import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = 5697467637035608845L;
	private JTextField textField;

	public BottomPanel() {
		setBounds(0, 600, 1920, 480);
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(964, 125, 279, 59);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("BOROM");
		lblNewLabel.setBounds(848, 195, 253, 59);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ETA PORRA");
		btnNewButton.setBounds(762, 310, 199, 88);
		add(btnNewButton);
	}
}