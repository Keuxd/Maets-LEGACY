package maets.screen;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import maets.screen.mainpanel.MainPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public MainFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1920, 1080);
		addWindowListener(new CloseApplicationWindowAdapter(this));
		setLayout(null);
		
		MainPanel mp = new MainPanel(this);
		setContentPane(mp);
	}
	
	public static BufferedImage resize(BufferedImage img, int newSize) { 
	    Image tmp = img.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    
	    return dimg;
	}
	
	public static boolean isMouseWithinComponent(Component c) {
		try {
		    Point mousePos = MouseInfo.getPointerInfo().getLocation();
		    Rectangle bounds = c.getBounds();
		    bounds.setLocation(c.getLocationOnScreen());
		    return bounds.contains(mousePos);
		} catch(Exception e) {
			return false;
		}
	}
	
	public void setLocation(int screen, double x, double y) {
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[]    d = g.getScreenDevices();

        if ( screen >= d.length ) {
                screen = d.length - 1;
        }

        Rectangle bounds = d[screen].getDefaultConfiguration().getBounds();
        
        // Is double?
        if ( x == Math.floor(x) && !Double.isInfinite(x) ) {
                x *= bounds.x;  // Decimal -> percentage
        }
        if ( y == Math.floor(y) && !Double.isInfinite(y) ) {
                y *= bounds.y;  // Decimal -> percentage
        }

        x = bounds.x      + x;
        y = getY() + y;

        if ( x > bounds.x) x = bounds.x;
        if ( y > bounds.y) y = bounds.y;

        // If double we do want to floor the value either way 
        setLocation((int)x, (int)y);
}
}