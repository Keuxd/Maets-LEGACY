package maets.screen.secondarypanels.gamepanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class OptionButtonLabel extends JLabel {

	private static final long serialVersionUID = -2752457893819570033L;
	
	public OptionButtonLabel(String labelName) {
		super(labelName);
		setFont(new Font("Nirmala UI", Font.PLAIN, 60));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(isClickValid()) {
					setHorizontalAlignment(SwingConstants.CENTER);
					fixedMouseClicked(e);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				repaint();
			}
		});
	}
	
	// Should be implemented by it's child class
	public void fixedMouseClicked(MouseEvent e) {}
	
	private boolean isClickValid() {
		try {
		    Point mousePos = MouseInfo.getPointerInfo().getLocation();
		    Rectangle bounds = getBounds();
		    bounds.setLocation(getLocationOnScreen());
		    return bounds.contains(mousePos);
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	
	}
}
