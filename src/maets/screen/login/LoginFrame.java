package maets.screen.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import maets.mega.exceptions.MegaException;
import maets.mega.exceptions.MegaLoginFailedException;
import maets.mega.exceptions.MegaUnconfirmedAccountException;
import maets.screen.CloseApplicationWindowAdapter;
import maets.screen.MainFrame;
import maets.screen.login.exceptions.LoginEmptyEmailException;
import maets.screen.login.exceptions.LoginEmptyPasswordException;
import maets.screen.login.exceptions.LoginException;
import maets.screen.login.exceptions.LoginInvalidEmailFormatException;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 6069736323832559194L;
	
	private JTextField textField;
	private JPasswordField passwordField;
	
	private Container mainContainer;
	private Container loadingContainer;

	public LoginFrame() {
		setTitle("Login");
		setSize(490, 490);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		addWindowListener(new CloseApplicationWindowAdapter(this));

		JPanel a = (JPanel)getContentPane();
		a.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		JLabel loginLabel = new JLabel("Log in");
		loginLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 50));
		loginLabel.setBounds(170, 11, 150, 70);
		getContentPane().add(loginLabel);
		
		JLabel yourEmailLabel = new JLabel("Your Email");
		yourEmailLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 40));
		yourEmailLabel.setBounds(95, 148, 200, 40);
		getContentPane().add(yourEmailLabel);
		
		JLabel pwdLabel = new JLabel("Password");
		pwdLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 40));
		pwdLabel.setBounds(95, 248, 200, 40);
		getContentPane().add(pwdLabel);
		
		
		ImageIcon warningIcon = new ImageIcon(new ImageIcon(LoginFrame.class.getResource("/resources/warning.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		JLabel warningEmail = new JLabel(warningIcon);
		warningEmail.setBounds(45, 199, 40, 40);
		warningEmail.setVisible(false);
		getContentPane().add(warningEmail);
		
		JLabel warningPassword = new JLabel(warningIcon);
		warningPassword.setBounds(45, 299, 40, 40);
		warningPassword.setVisible(false);
		getContentPane().add(warningPassword);		

		JButton loginButton = new JButton("Log in");
		loginButton.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
		loginButton.setBounds(255, 350, 140, 46);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						try {
							switchPanel(loadingContainer);
							
							warningEmail.setVisible(false);
							warningPassword.setVisible(false);
							
							LoginForm lf = new LoginForm(textField.getText(), passwordField.getPassword());
							lf.login();
							
							setVisible(false);
							new MainFrame().setVisible(true);
							dispose();
						} catch(LoginInvalidEmailFormatException e1) {
							warningToolTipUpdate("Invalid email format.", warningEmail);
						} catch(LoginEmptyEmailException e1) {
							warningToolTipUpdate("Email field is empty.", warningEmail);
						} catch(LoginEmptyPasswordException e1) {
							warningToolTipUpdate("Password field is empty.", warningPassword);
						} catch(LoginException e1) { // Please don't
							e1.printStackTrace();	
						} catch(MegaUnconfirmedAccountException e1) {
							showErrorMessage("Unconfirmed account: please remember to confirm your account on your email.");
						} catch(MegaLoginFailedException e1) {
							showErrorMessage("Invalid credentials: please put a valid mega account.");
						} catch(MegaException e1) { // Please don't part 2
							e1.printStackTrace(); 
						}
						return null;
					}
					
				}.execute();
			}
		});
		getContentPane().add(loginButton);
		
		KeyAdapter enterConfirm = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		};
		addKeyListener(enterConfirm);		
		
		textField = new JTextField();
		textField.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		textField.setBounds(95, 199, 300, 40);
		textField.addKeyListener(enterConfirm);
		getContentPane().add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		passwordField.setBounds(95, 299, 300, 40);
		passwordField.addKeyListener(enterConfirm);
		getContentPane().add(passwordField);
		
		String tags = "<HTML><a href";
		String labelText = ">Where do I create my account?";
		JLabel createAccountLabel = new JLabel(tags + labelText);
		createAccountLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		createAccountLabel.setBounds(105, 420, 280, 25);
		createAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccountLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://mega.nz/register"));
				} catch(URISyntaxException | IOException exc) {
					exc.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				createAccountLabel.setText(tags + " style=\"text-decoration:none;\"" + labelText);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createAccountLabel.setText(tags + labelText);
			}
		});
		getContentPane().add(createAccountLabel);
		mainContainer = getContentPane();
		
		int defaultEchoChar = passwordField.getEchoChar();
		JCheckBox showPasswordCheckBox = new JCheckBox(" Show password");
		showPasswordCheckBox.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		showPasswordCheckBox.setBounds(95, 350, 154, 46);
		showPasswordCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar((char) defaultEchoChar);
				}
			}
			
		});
		getContentPane().add(showPasswordCheckBox);
		
		loadingContainer = createLoadingScreen();
	}
	
	private void switchPanel(Container panel) {
		setContentPane(panel);
		repaint();
	}
	
	private void warningToolTipUpdate(String toolTipText, JLabel warningLabel) {
		switchPanel(mainContainer);
		warningLabel.setToolTipText(toolTipText);
		warningLabel.setVisible(true);
		Toolkit.getDefaultToolkit().beep();
	}
	
	private void showErrorMessage(String error) {
		switchPanel(mainContainer);
		JLabel errorMessage = new JLabel(error);
		errorMessage.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(null, errorMessage, null, JOptionPane.ERROR_MESSAGE);
	}
	
	private JPanel createLoadingScreen() {
		JPanel loadingPanel = new JPanel();
		loadingPanel.setLayout(null);
		loadingPanel.setSize(getSize());
		loadingPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		int gifSize = 100;
		int centerPosition = (loadingPanel.getWidth() / 2) - (gifSize / 2);
		
		ImageIcon loadingGif = new ImageIcon(LoginFrame.class.getResource("/resources/loading.gif"));
		ImageIcon loadingGif1 = new ImageIcon(loadingGif.getImage().getScaledInstance(gifSize, gifSize, Image.SCALE_DEFAULT));
		
		JLabel gifLabel = new JLabel(loadingGif1);
		gifLabel.setBounds(centerPosition, centerPosition, gifSize, gifSize);
		
		loadingPanel.add(gifLabel);
		return loadingPanel;
	}
}