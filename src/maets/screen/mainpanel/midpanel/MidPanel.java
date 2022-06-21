package maets.screen.mainpanel.midpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.core.Main;
import maets.screen.FrameManager;
import maets.screen.FrameManager.TransitionSide;
import maets.screen.mainpanel.ResizableButtonsResponsivity;
import maets.screen.secondarypanels.storepanel.StorePanel;

public class MidPanel extends JPanel {

	private static final long serialVersionUID = 3896718565332803227L;

	public MidPanel(JFrame parentFrame) {
		setBounds(0, 200, 1920, 400);
		setLayout(null);

		JButton storeButton = new JButton("STORE");
		storeButton.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 50));
		storeButton.setIcon(Main.getImageIconInResources("store.png", 130, Image.SCALE_SMOOTH));
		storeButton.setBounds(255, 95, 450, 210);
		storeButton.setHorizontalTextPosition(JButton.CENTER);
		storeButton.setVerticalTextPosition(JButton.BOTTOM);
		storeButton.setFocusable(false);
		storeButton.addMouseListener(new ResizableButtonsResponsivity(storeButton) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				FrameManager.contentPaneTransition(parentFrame, new StorePanel(parentFrame), TransitionSide.UP, 60);
			}
		});
		add(storeButton);

		JButton libraryButton = new JButton("LIBRARY");
		libraryButton.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 50));
		libraryButton.setIcon(Main.getImageIconInResources("library.png", 130, Image.SCALE_SMOOTH));
		libraryButton.setBounds(735, 95, 450, 210);
		libraryButton.setHorizontalTextPosition(JButton.CENTER);
		libraryButton.setVerticalTextPosition(JButton.BOTTOM);
		libraryButton.setFocusable(false);
		libraryButton.addMouseListener(new ResizableButtonsResponsivity(libraryButton) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 2");
			}
		});
		add(libraryButton);
		
		JButton button3 = new JButton("???");
		button3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 50));
		button3.setIcon(Main.getImageIconInResources("warning.png", 145, Image.SCALE_SMOOTH));
		button3.setBounds(1215, 95, 450, 210);
		button3.setHorizontalTextPosition(JButton.CENTER);
		button3.setVerticalTextPosition(JButton.BOTTOM);
		button3.setFocusable(false);
		button3.addMouseListener(new ResizableButtonsResponsivity(button3) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 3");
			}
		});
		add(button3);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        Graphics2D g2 = (Graphics2D) g;

        Color c1 = new Color(0x4E5052); // Lighter LAF background color
        Color c2 = new Color(0x3C3F41); // Darker LAF background color
        
        final int LINE_HEIGHT = 3;
        
        GradientPaint gradientUpLeft = new GradientPaint(0, 0, c2, getWidth() / 2, LINE_HEIGHT, c1);
        g2.setPaint(gradientUpLeft);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth() / 2, LINE_HEIGHT));
        
        GradientPaint gradientUpRight = new GradientPaint(getWidth() / 2, 0, c1, getWidth(), LINE_HEIGHT, c2);
        g2.setPaint(gradientUpRight);
        g2.fill(new Rectangle2D.Double(getWidth() / 2, 0, getWidth() / 2, LINE_HEIGHT));
        
        GradientPaint gradientDownLeft = new GradientPaint(0, getHeight() - LINE_HEIGHT, c2, getWidth() / 2, getHeight(), c1);
        g2.setPaint(gradientDownLeft);
        g2.fill(new Rectangle2D.Double(0, getHeight() - LINE_HEIGHT, getWidth() / 2, LINE_HEIGHT));
        
        GradientPaint gradientDownRight = new GradientPaint(getWidth() / 2, getHeight() - LINE_HEIGHT, c1, getWidth(), getHeight(), c2);
        g2.setPaint(gradientDownRight);
        g2.fill(new Rectangle2D.Double(getWidth() / 2, getHeight() - LINE_HEIGHT, getWidth() / 2, LINE_HEIGHT));
	}
}