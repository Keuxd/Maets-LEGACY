package maets.screen.mainpanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

public class ResizableButtonsResponsivity extends ButtonsResponsivityMouseAdapter{

	private Rectangle originalButtonBounds;
	private Timer resizeTimer;
	
	private int size;
	private int location;
	
	public ResizableButtonsResponsivity(JComponent button) {
		super(button);
		this.originalButtonBounds = button.getBounds();
		initResizeTimer();
	}
	
	private void initResizeTimer() {
		size = -4;//-6
		location = 2;//3
		
		this.resizeTimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if((size > 0 && button.getHeight() > originalButtonBounds.height * 1.16) || (location > 0 && button.getHeight() <= originalButtonBounds.height)) {
					resizeTimer.stop();
					return;
				}
				
				button.setSize(button.getWidth() + size, button.getHeight() + size);
				button.setLocation(button.getX() + location, button.getY() + location);
			}
		});
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		switchResizableTimer();
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		switchResizableTimer();
	}
	
	private void switchResizableTimer() {
		size *= -1;
		location *= -1;
		resizeTimer.start();
	}
}