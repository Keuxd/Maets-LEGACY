package maets.screen.secondarypanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maets.core.Main;
import maets.core.Resources;
import maets.core.Resources.ResourceType;
import maets.screen.FrameManager;
import maets.screen.FrameManager.TransitionSide;
import maets.screen.mainpanel.ResizableButtonsResponsivity;
import maets.screen.mainpanel.upperpanel.TimerPanel;

public class TopBarPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JFrame parentFrame;
	
	public <T extends JPanel> TopBarPanel(JFrame parentFrame, Class<T> previousPanelClass, String screenTitle) {
		this.parentFrame = parentFrame;
		setBounds(0, 0, 1920, 110);
		setLayout(null);
		
		JLabel returnButton = new JLabel(Resources.getImageIconResized("return.png", ResourceType.UI, 75, Image.SCALE_SMOOTH));
		returnButton.setBounds(23, 11, 85, 85);
		returnButton.addMouseListener(new ResizableButtonsResponsivity(returnButton) { 
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				try {
					T newClassInstance = previousPanelClass.getDeclaredConstructor(JFrame.class).newInstance(parentFrame);
					FrameManager.contentPaneTransition(parentFrame, newClassInstance, TransitionSide.DOWN, 60);
				} catch(Exception e1) {
					Main.unexpectedError(e1.getMessage(), parentFrame);
				}
			}
		});
		add(returnButton);
		
		JLabel screenTitleLabel = new JLabel(screenTitle);
		screenTitleLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 70));
		screenTitleLabel.setBounds(117, 5, 639, 100);
		add(screenTitleLabel);
		
		// TimerPanel was made for MainPanel bounds so we gotta adjust it manually
		TimerPanel timer = new TimerPanel();
		timer.setSize(293, 147);
		timer.setLocation(1627, -48);
		add(timer);
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