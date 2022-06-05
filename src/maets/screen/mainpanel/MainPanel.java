package maets.screen.mainpanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maets.screen.mainpanel.bottompanel.BottomPanel;
import maets.screen.mainpanel.midpanel.MidPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 3696506270919324384L;
	
	private JFrame parentFrame;
	
	public MainPanel(JFrame parentFrame) {
		setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight());
		setLayout(null);
		
//		JPanel upperPanel = null;
//		try {
//			upperPanel = new UpperPanel(parentFrame);
//		} catch(MegaException e1) {
//			Main.unexpectedError(e1.getMessage(), parentFrame);
//		}
//		add(upperPanel);
		
		MidPanel mp = new MidPanel();
		add(mp);
		
		BottomPanel bp = new BottomPanel();
		add(bp);
		
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
		        Graphics2D g2 = (Graphics2D) g;

		        GradientPaint btg = new GradientPaint(0,150,Color.red, 1920, 100, Color.blue);
		        g2.setPaint(btg);
		        g2.fill(new Rectangle2D.Double(0, 150, 1920, 100));
			}
		};
		p.setBounds(0,0,1920,200);
		
		add(p);
	}
}