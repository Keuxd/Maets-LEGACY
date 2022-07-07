package maets.games;

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
		games.put("Vampire Survivors", Resources.getImageIcon("vampire-survivors-banner.jpg", ResourceType.GAMES));
		games.put("Resident Evil 4 UHD", Resources.getImageIcon("resident-evil-4-uhd-banner.jpg", ResourceType.GAMES));
		games.put("Digimon Story Cyber Sleuth: Complete Edition", Resources.getImageIcon("digimon-cyber-sleuth-banner.jpg", ResourceType.GAMES));
		games.put("Ruined King: A League of Legends Story™", Resources.getImageIcon("ruined-king-banner.jpg", ResourceType.GAMES));
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