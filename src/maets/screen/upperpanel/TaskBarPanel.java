package maets.screen.upperpanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TaskBarPanel extends JPanel {
 
	private static final long serialVersionUID = -4051125285167482804L;

	private JFrame parentFrame;
	
	public TaskBarPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(1220, 0, 407, 200);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 65, 70, 70);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(80, 65, 70, 70);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setBounds(160, 65, 70, 70);
		add(lblNewLabel_1_1);
		
		ImageIcon gearIcon = new ImageIcon(new ImageIcon(TaskBarPanel.class.getResource("/resources/gear-settings.png")).getImage().getScaledInstance(66, 66, Image.SCALE_SMOOTH));
		JLabel configsLabel = new JLabel(gearIcon);
		configsLabel.setBounds(240, 65, 70, 70);
		configsLabel.addMouseListener(new ButtonsResponsivityMouseAdapter(configsLabel) {

		});
		add(configsLabel);
		
		ImageIcon closeIcon = new ImageIcon(new ImageIcon(TaskBarPanel.class.getResource("/resources/close.png")).getImage().getScaledInstance(66, 66, Image.SCALE_SMOOTH));
		JLabel closeLabel = new JLabel(closeIcon);
		closeLabel.setBounds(320, 65, 70, 70);
		closeLabel.addMouseListener(new ButtonsResponsivityMouseAdapter(closeLabel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel confirmLabel = new JLabel("Are you sure you want to close Maets ?");
				confirmLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
				int option = JOptionPane.showConfirmDialog(null, confirmLabel, null, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				
				if(option == 0) {
					parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		add(closeLabel);
	}
}