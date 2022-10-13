package maets.screen.secondarypanels.gamepanel;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OptionsListPanel extends JPanel {

	private static final long serialVersionUID = -3443802812769712962L;

	protected List<OptionButtonLabel> buttons;
	
	public OptionsListPanel(ImageIcon gameBanner) {
		setBounds(0, 110, 516, 970);
		setLayout(null);
		
		buttons = new ArrayList<OptionButtonLabel>();
		
		JLabel panel = new JLabel(gameBanner);
		panel.setBounds(36, 28, 460, 215);
		add(panel);
		
		OptionButtonLabel playButton = new OptionButtonLabel("Play") {
			
			private static final long serialVersionUID = 658908966097976279L;

			@Override
			public void fixedMouseClicked(MouseEvent e) {
				super.fixedMouseClicked(e);
				buttons.get(1).setHorizontalAlignment(SwingConstants.LEFT);
			}
		};
		playButton.setBounds(36, 254, 460, 96);
		add(playButton);
		buttons.add(playButton);
		
		OptionButtonLabel aa = new OptionButtonLabel("Test") {
			
			private static final long serialVersionUID = 658908966097976279L;

			@Override
			public void fixedMouseClicked(MouseEvent e) {
				super.fixedMouseClicked(e);
				buttons.get(0).setHorizontalAlignment(SwingConstants.LEFT);
			}
		};
		aa.setBounds(36, 254+128, 460, 96);
		add(aa);
		buttons.add(aa);
		
	}
}