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
	private Map<String, ImageIcon[]> samples;
	private Map<String, String[]> descriptions;
	
	public GamesTable(int bannerWidth, int bannerHeight, int sampleWidth, int sampleHeight) {
		games = new ConcurrentHashMap<>();
		samples = new ConcurrentHashMap<>();
		
		registerGame("Vampire Survivors",
				Resources.getImageIconResized("vampire-survivors-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
				Resources.getImageIconResized("vampire-survivors-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING));
		
		registerGame("Resident Evil 4 UHD",
				Resources.getImageIconResized("resident-evil-4-uhd-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
				Resources.getImageIconResized("resident-evil-4-uhd-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING));
		
		registerGame("Digimon Story Cyber Sleuth: Complete Edition",
				Resources.getImageIconResized("digimon-cyber-sleuth-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
				Resources.getImageIconResized("digimon-cyber-sleuth-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING));

	}
	
	private void registerGame(String name, ImageIcon banner, ImageIcon... samples) {
		this.games.put(name, banner);
		this.samples.put(name, samples);
	}
	
	public Set<String> getNames() {
		return games.keySet();
	}
	
	public Collection<ImageIcon> getBanners() {
		return games.values();
	}
	
	public Collection<ImageIcon[]> getSamples() {
		return samples.values();
	}
	
	public ImageIcon getBanner(String gameName) {
		return games.get(gameName);
	}
	
	public ImageIcon[] getSamples(String gameName) {
		return samples.get(gameName);
	}
}