package maets.screen.mainpanel.midpanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import maets.core.Main;
import maets.screen.mainpanel.ButtonsResponsivityMouseAdapter;

public class MidPanel extends JPanel {

	private static final long serialVersionUID = 3896718565332803227L;

	public MidPanel() {
		setBounds(0, 200, 1920, 400);
		setLayout(null);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_SMOOTH));
		btnNewButton_1.setBounds(255, 95, 450, 210);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_1) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 1");
			}
			
//			private Dimension originalSize;
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				super.mouseEntered(e);
//				
//				originalSize = btnNewButton_1.getSize();
//				
//				int h = originalSize.height + (originalSize.height * 5/100);
//				int w = originalSize.width + (originalSize.width * 5/100);
//				
//				btnNewButton_1.setSize(w, h);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				super.mouseExited(e);
//				
//				btnNewButton_1.setSize(originalSize);
//			}
		});
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_DEFAULT));
		btnNewButton_2.setBounds(735, 95, 450, 210);
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_2) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 2");
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_SMOOTH));
		btnNewButton_3.setBounds(1215, 95, 450, 210);
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_3) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 3");
			}
		});
		add(btnNewButton_3);
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