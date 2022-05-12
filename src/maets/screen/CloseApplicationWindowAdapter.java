package maets.screen;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import maets.mega.Mega;

public class CloseApplicationWindowAdapter extends WindowAdapter {
	
	private JFrame frame;
	
	public CloseApplicationWindowAdapter(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			frame.dispose();
			Mega.quit();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		System.exit(1);
	}
}