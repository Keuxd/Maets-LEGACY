package maets.games;

import java.awt.Image;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

import maets.core.Resources;
import maets.core.Resources.ResourceType;

public class GamesTable {
	
	private Map<String, ImageIcon> games;
	
	public GamesTable(int iconsSize) {
		games = new ConcurrentHashMap<>();
		games.put("Vampire Survivors", Resources.getImageIconResized("vampire-survivors-icon.png", ResourceType.GAMES, iconsSize, Image.SCALE_DEFAULT));
		games.put("Resident Evil 4 UHD", Resources.getImageIconResized("re4.png", ResourceType.GAMES, iconsSize, Image.SCALE_DEFAULT));
	}
	
	public Set<String> getNames() {
		return games.keySet();
	}
	
	public Collection<ImageIcon> getIcons() {
		return games.values();
	}
	
	public ImageIcon getIcon(String iconName) {
		return games.get(iconName);
	}
}