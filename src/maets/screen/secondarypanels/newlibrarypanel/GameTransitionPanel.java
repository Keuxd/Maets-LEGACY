package maets.screen.secondarypanels.newlibrarypanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import maets.core.ConfigFile.OnlineConfigs;
import maets.core.Main;
import maets.games.GamesTable;

public class GameTransitionPanel extends JPanel {
	
	private static final long serialVersionUID = 5712700084887478951L;

	protected JFrame parentFrame;
	
	private JLabel middleLabel;
	private JLabel leftLabel;
	private JLabel rightLabel;
	
	private JButton moveLeft;
	private	JButton moveRight;
	
	private int currentGameInArray;
	
	public GameTransitionPanel(JFrame parentFrame, SelectionPanel selectionPanel, GamesTable gt) throws IOException {
		this.parentFrame = parentFrame;
		setBounds(0, 110, 1920, 315);
		setLayout(null);
		
		final String[] games = Main.online.getValuesFromConfig(OnlineConfigs.GAMES_IN_LIBRARY);
		
		if(games == null) Main.unexpectedError("No Games In Library", parentFrame);
		
		this.currentGameInArray = 0;
		
		middleLabel = new JLabel(gt.getBanner(games[currentGameInArray]));
		middleLabel.setBounds(getWidth() / 2 - 460 / 2, 50, 460, 215);
		middleLabel.setBorder(UIManager.getBorder("Button.border"));
		add(middleLabel);
		
		leftLabel = new JLabel();
		leftLabel.setBounds(middleLabel.getX() - 460 - 100, 50, 460, 215);
		leftLabel.setEnabled(false);
		leftLabel.setVisible(false);
		add(leftLabel);
		
		rightLabel = new JLabel();
		rightLabel.setBounds(middleLabel.getX() + 460 + 100, 50, 460, 215);
		rightLabel.setEnabled(false);
		if(games.length > 1) {
			rightLabel.setIcon(gt.getBanner(games[currentGameInArray + 1]));
		}
		else {
			rightLabel.setVisible(false);
		}
		add(rightLabel);
		
		moveLeft = new JButton("<");
		moveLeft.setBounds(40, 50, 80, 215);
		moveLeft.setBorder(null);
		moveLeft.setFont(new Font("Nirmala UI", Font.PLAIN, 40));
		moveLeft.setForeground(Color.WHITE);
		moveLeft.setVisible(false);
		moveLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentGameInArray--;
				updateComponents(gt, games);
				selectionPanel.repopulate(games[currentGameInArray]);
			}
		});
		add(moveLeft);
		
		moveRight = new JButton(">");
		moveRight.setBounds(1800, 50, 80, 215);
		moveRight.setBorder(null);
		moveRight.setFont(new Font("Nirmala UI", Font.PLAIN, 40));
		moveRight.setForeground(Color.WHITE);
		moveRight.setVisible(games.length > 1);
		moveRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentGameInArray++;
				updateComponents(gt, games);
				selectionPanel.repopulate(games[currentGameInArray]);
			}
		});
		selectionPanel.populate(games[currentGameInArray]);
		add(moveRight);
	}
	
	private void updateComponents(GamesTable gt, String[] games) {
		if(currentGameInArray == 0) { // If it's the first game
			moveLeft.setVisible(false);
			leftLabel.setVisible(false);
			if(games.length == 2) { // Corner case for 2 games only
				moveRight.setVisible(true);
				rightLabel.setVisible(true);
			}
		} else if(currentGameInArray == games.length - 1) { // If It's the last game
			moveRight.setVisible(false);
			rightLabel.setVisible(false);
			if(games.length == 2) { // Corner case for 2 games only
				moveLeft.setVisible(true);
				leftLabel.setVisible(true);
			}			
		} else { // If it's the middle game
			moveLeft.setVisible(true);
			leftLabel.setVisible(true);
			moveRight.setVisible(true);
			rightLabel.setVisible(true);
		}

		middleLabel.setIcon(gt.getBanner(games[currentGameInArray]));
		
		if(leftLabel.isVisible()) 
			leftLabel.setIcon(gt.getBanner(games[currentGameInArray - 1]));
		
		if(rightLabel.isVisible())
			rightLabel.setIcon(gt.getBanner(games[currentGameInArray + 1]));
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        Graphics2D g2 = (Graphics2D) g;

        Color c1 = new Color(0x4E5052); // Lighter LAF background color
        Color c2 = new Color(0x3C3F41); // Darker LAF background color
        
        final int LINE_HEIGHT = 3;
        
        GradientPaint gradientDownLeft = new GradientPaint(0, getHeight() - LINE_HEIGHT, c2, getWidth() / 2, getHeight(), c1);
        g2.setPaint(gradientDownLeft);
        g2.fill(new Rectangle2D.Double(0, getHeight() - LINE_HEIGHT, getWidth() / 2, LINE_HEIGHT));
        
        GradientPaint gradientDownRight = new GradientPaint(getWidth() / 2, getHeight() - LINE_HEIGHT, c1, getWidth(), getHeight(), c2);
        g2.setPaint(gradientDownRight);
        g2.fill(new Rectangle2D.Double(getWidth() / 2, getHeight() - LINE_HEIGHT, getWidth() / 2, LINE_HEIGHT));
	}
}