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
	
	public GamesTable(int bannerWidth, int bannerHeight) {
		games = new ConcurrentHashMap<>();
		games.put("Vampire Survivors", Resources.getImageIconResized("vampire-survivors-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING));
		games.put("Resident Evil 4 UHD", Resources.getImageIconResized("resident-evil-4-uhd-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING));
		games.put("Digimon Story Cyber Sleuth: Complete Edition", Resources.getImageIconResized("digimon-cyber-sleuth-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING));
		games.put("Ruined King: A League of Legends Story™", Resources.getImageIconResized("ruined-king-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING));
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