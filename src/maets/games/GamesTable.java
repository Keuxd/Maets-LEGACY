package maets.games;

import java.awt.Image;

import javax.swing.ImageIcon;

import maets.core.Resources;
import maets.core.Resources.ResourceType;

public class GamesTable {
	
	private String[] names;
	private ImageIcon[] icons;
	
	public GamesTable(int iconsSize) {
		this.names = new String[] {
				"Vampire Survivors",
				"Resident Evil 4 UHD"
		};
		
		this.icons = new ImageIcon[] {
			Resources.getImageIconResized("vampire-survivors-icon.png", ResourceType.GAMES, iconsSize, Image.SCALE_DEFAULT),	
			Resources.getImageIconResized("re4.png", ResourceType.GAMES, iconsSize, Image.SCALE_DEFAULT),
		};
	}
	
	public String[] getNames() {
		return this.names;
	}
	
	public String getName(int index) {
		return this.names[index];
	}
	
	public ImageIcon[] getIcons() {
		return this.icons;
	}
	
	public ImageIcon getIcon(int index) {
		return this.icons[index];
	}
}