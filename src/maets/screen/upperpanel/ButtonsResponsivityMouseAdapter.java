package maets.screen.upperpanel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class ButtonsResponsivityMouseAdapter extends MouseAdapter {

	private JLabel button;
	private ImageIcon originalImageIcon;
	
	public ButtonsResponsivityMouseAdapter(JLabel button) {
		this.button = button;
		this.originalImageIcon = convertIconToImageIcon(button.getIcon());
	}
	
	// mouseClicked() should be implemented by it's parent class
	
	@Override
	public void mouseEntered(MouseEvent e) {
		button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setBorder(null);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		button.setIcon(this.originalImageIcon);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Image scaledImage = this.originalImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(scaledImage));
	}
	
	private ImageIcon convertIconToImageIcon(Icon icon) {
		BufferedImage bi = new BufferedImage(icon.getIconHeight(), icon.getIconWidth(), BufferedImage.TYPE_INT_ARGB);
		icon.paintIcon(new JCheckBox(), bi.getGraphics(), 0, 0);
		return new ImageIcon(bi);
	}
}