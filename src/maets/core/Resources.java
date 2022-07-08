package maets.core;

import javax.swing.ImageIcon;

public class Resources {

	public enum ResourceType {
		GAMES, UI
	}
	
	public static ImageIcon getImageIconResized(String imageNameWithExtension, ResourceType type, int width, int height, int imageFlag) {
		return new ImageIcon(getImageIcon(imageNameWithExtension, type).getImage().getScaledInstance(width, height, imageFlag));
	}
	
	public static ImageIcon getImageIconEvenResized(String imageNameWithExtension, ResourceType type, int size, int imageFlag) {
		return new ImageIcon(getImageIcon(imageNameWithExtension, type).getImage().getScaledInstance(size, size, imageFlag));
	}
	
	public static ImageIcon getImageIcon(String imageNameWithExtension, ResourceType type) {
		return new ImageIcon(Resources.class.getResource("/images/" + type.name() + "/" + imageNameWithExtension));
	}
}