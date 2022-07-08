package maets.screen.mainpanel.upperpanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.core.Main;
import maets.core.Resources;
import maets.core.Resources.ResourceType;
import maets.mega.Mega;
import maets.mega.exceptions.MegaException;
import maets.screen.login.LoginFrame;

public class UserPanel extends JPanel {
	
	private static final long serialVersionUID = -8359478721677085694L;
	
	protected JFrame parentFrame;

	public UserPanel(JFrame parentFrame) throws MegaException {
		this.parentFrame = parentFrame;
		setLayout(null);
		setBounds(0, 0, 1220, 200);
		
		JLabel userImageLabel = new JLabel(Resources.getImageIconEvenResized("person-512.png", ResourceType.UI, 175, Image.SCALE_AREA_AVERAGING));
		userImageLabel.setBounds(295, 45, 128, 128);
		userImageLabel.setBorder(BorderFactory.createLineBorder(new Color(227, 229, 232), 4, true));
		add(userImageLabel);
		
		JLabel lastNameLabel = new JLabel(getConcatedUserNames());
		lastNameLabel.setForeground(new Color(227, 229, 232));
		lastNameLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 55));
		lastNameLabel.setBounds(440, 45, 800, 74);
		add(lastNameLabel);
		
		String tags = "<HTML><U>";
		String labelText = "Logout";
		JLabel logoutLabel = new JLabel(tags + labelText);
		logoutLabel.setForeground(new Color(227, 229, 232));
		logoutLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 32));
		logoutLabel.setBounds(440, 125, 98, 43);
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel confirmLabel = new JLabel("Are you sure you want to logout from your mega account ?");
				confirmLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
				int option = JOptionPane.showConfirmDialog(null, confirmLabel, null, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				
				if(option == 0) {
					new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {
							try {
								parentFrame.setVisible(false);
								Mega.logout();
								new LoginFrame().setVisible(true);
								parentFrame.dispose();
							} catch(MegaException e) {
								Main.unexpectedError(e.getMessage(), parentFrame);
							}
							return null;
						}
					}.execute();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutLabel.setText(labelText);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				logoutLabel.setText(tags + labelText);
			}
		});
		add(logoutLabel);
	}
	
	private String getConcatedUserNames() throws MegaException {
		String concatedUserNames = (String) Cache.get(CacheType.USER_NAME);
		
		if(concatedUserNames == null) {
			concatedUserNames = Mega.getUserName("firstname").concat(Mega.getUserName("lastname"));
			Cache.set(CacheType.USER_NAME, concatedUserNames);
		}
		
		return concatedUserNames;
	}
}