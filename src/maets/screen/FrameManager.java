package maets.screen;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FrameManager {
	
	public enum TransitionSide {
		UP, DOWN, LEFT, RIGHT
	}
	
	public static void contentPaneTransition(JFrame parentFrame, JPanel newPanel, TransitionSide side, int speed) {
		Container contentPane = parentFrame.getContentPane();
		
		if(!isSpeedValid(speed, side, contentPane.getSize())) {
			System.out.println("\nInvalid speed given for FrameManager.contentPaneTransition()");
			return;
		}
		
		Component[] previousComponents = contentPane.getComponents();
		
		adjustPanelLocation(newPanel, side, contentPane);
		
		parentFrame.add(newPanel);
		
		int[] velocity = getVelocity(speed, side);
		
		moveComponents(previousComponents, newPanel, velocity, contentPane);
	}
	
	private static boolean isSpeedValid(int speed, TransitionSide side, Dimension frameSize) {
		switch(side) {
			case UP:
			case DOWN:
				if(frameSize.height % speed == 0) return true;
					return false;
					
			case LEFT:
			case RIGHT:
				if(frameSize.width % speed == 0) return true;
					return false;
				
			default: return false;
		}
	}
	
	private static int[] getVelocity(int speed, TransitionSide side) {
		// [0] -> X | [1] -> Y
		int[] velocity = new int[2];
		
		switch(side) {
			case UP : {
				velocity[0] = 0;
				velocity[1] = -speed;
				break;
			}
			
			case DOWN : {
				velocity[0] = 0;
				velocity[1] = speed;
				break;
			}
			
			case LEFT : {
				velocity[0] = speed;
				velocity[1] = 0;
				break;
			}
			
			case RIGHT : {
				velocity[0] = -speed;
				velocity[1] = 0;
				break;
			}
		}
		
		return velocity;
	}
	
	private static void moveComponents(Component[] previousComponents, JPanel newPanel, int[] velocity, Container contentPane) {
		final Timer timer = new Timer(15, null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component c : previousComponents) {
					c.setLocation(c.getX() + velocity[0], c.getY() + velocity[1]);
				}
				
				newPanel.setLocation(newPanel.getX() + velocity[0], newPanel.getY() + velocity[1]);
				
				if((velocity[0] != 0 && newPanel.getX() == 0) || (velocity[1] != 0 && newPanel.getY() == 0)) {
					timer.stop();
					removePreviousComponents(previousComponents, contentPane);
				}
			}
		});
		
		timer.start();
	}
	
	private static void removePreviousComponents(Component[] previousComponents, Container contentPane) {
		for(Component c : previousComponents) {
			contentPane.remove(c);
		}
	}
	
	private static void adjustPanelLocation(JPanel panel, TransitionSide side, Container contentPane) {
		switch(side) {
			case UP : panel.setLocation(contentPane.getX(), contentPane.getHeight()); break;
			case DOWN : panel.setLocation(contentPane.getX(), -contentPane.getHeight()); break;
			case LEFT: panel.setLocation(-contentPane.getWidth(), contentPane.getY()); break;
			case RIGHT: panel.setLocation(contentPane.getWidth(), contentPane.getY()); break;
		}
	}
}