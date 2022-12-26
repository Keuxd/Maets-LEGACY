package maets.screen.secondarypanels.librarypanel;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.core.ConfigFile.OnlineConfigs;
import maets.core.Main;
import maets.games.GamesTable;
import maets.screen.FrameManager;
import maets.screen.FrameManager.TransitionSide;
import maets.screen.mainpanel.ButtonsResponsivityMouseAdapter;
import maets.screen.secondarypanels.gamepanel.GamePanel;

public class GamesPanel extends JPanel {

	private static final long serialVersionUID = 284308892863919798L;
	
	protected JFrame parentFrame;
	
	public GamesPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setBounds(0, 110, 1920, 970);
		setLayout(null);
		
		String[] games = null;
		try {
			games = Main.local.getValuesFromConfig(OnlineConfigs.GAMES_IN_LIBRARY);
		} catch (IOException e) {
			Main.unexpectedError(e.getMessage(), parentFrame);
		}
		
		if(games == null) return;
		if(games.length > 16) Main.unexpectedError("Max of 16 games in library reached.", parentFrame);
		
		GamesTable gt = (GamesTable) Cache.get(CacheType.GAMES_TABLE);
		
		// Those values will be calculated later on, for now it's hardcoded.
		final int GAMES_LABEL_X_INSETS = 16;
		final int GAMES_LABEL_Y_INSETS = 20;
		final int GAMES_LABEL_WIDTH = 460;
		final int GAMES_LABEL_HEIGHT = 215;

		for(int i = 0, x = 0, y = 0; i < games.length; i++, x++) {
			if(x == 4) {
				y++;
				x = 0;
			}
			
			final String currentGame = games[i];
			JLabel newGameLabel = new JLabel(gt.getBanner(currentGame));
			newGameLabel.setBounds(GAMES_LABEL_WIDTH * x + GAMES_LABEL_X_INSETS * x + GAMES_LABEL_X_INSETS, GAMES_LABEL_HEIGHT * y + GAMES_LABEL_Y_INSETS * y + GAMES_LABEL_Y_INSETS, GAMES_LABEL_WIDTH, GAMES_LABEL_HEIGHT);
			newGameLabel.addMouseListener(new ButtonsResponsivityMouseAdapter(newGameLabel) {
				@Override
				public void mousePressed(MouseEvent e) {
					int thirdWidth = (int) Math.round(Math.ceil((double) newGameLabel.getWidth() / 1.5));
					int thirdHeight = (int) Math.round(Math.ceil((double) newGameLabel.getHeight() / 1.5));
					
					Image scaledImage = originalImageIcon.getImage().getScaledInstance(thirdWidth, thirdHeight, Image.SCALE_SMOOTH);
					setIconInComponent(new ImageIcon(scaledImage));
					
					newGameLabel.setBorder(null);
					
					button.setFont(button.getFont().deriveFont((float) button.getFont().getSize() / 2));
				}
				
				@Override
				public void fixedMouseClicked(MouseEvent e) {
					FrameManager.contentPaneTransition(parentFrame, new GamePanel(parentFrame, currentGame, this.originalImageIcon), TransitionSide.UP, 60);
				}
			});
			add(newGameLabel);
		}
	}
}
