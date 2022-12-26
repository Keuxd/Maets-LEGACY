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
	private Map<String, ImageIcon> samples;
	private Map<String, String[]> descriptions;
	private Map<String, Integer> ids;
	
	public GamesTable(int bannerWidth, int bannerHeight, int sampleWidth, int sampleHeight) {
		games = new ConcurrentHashMap<>();
		samples = new ConcurrentHashMap<>();
		descriptions = new ConcurrentHashMap<>();
		ids = new ConcurrentHashMap<>();
		
		registerGame("Vampire Survivors",
				Resources.getImageIconResized("vampire-survivors-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
				Resources.getImageIconResized("vampire-survivors-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING),
				new String[] {
						"<html>Vampire Survivors is a time survival game with minimalistic gameplay and roguelite elements. <br>There's no place where to hide, all you can do is try to survive a cursed night and get as much gold as possible for the next survivor, before Death inevitably puts an end to your struggles.</html>",
						"<html>Requires a 64-bit processor and operating system<br>OS: Windows 7 64bit<br>Processor: Intel Pentium 4 processor or later that's SSE2 capable<br>Memory: 1 GB RAM<br>Storage: 250 MB available space</html>"
		},
				0
				);

		//		registerGame("Digimon Story Cyber Sleuth: Complete Edition",
//				Resources.getImageIconResized("digimon-cyber-sleuth-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
//				Resources.getImageIconResized("digimon-cyber-sleuth-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING),
//				new String[] {
//						"<html></html>",
//						"<html></html>"
//				},
//				1
//		);
		
//		
//		registerGame("Secrets of Grindea",
//				Resources.getImageIconResized("secrets-of-grindea-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
//				Resources.getImageIconResized("secrets-of-grindea-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING),
//				new String[] {
//						"<html></html>",
//						"<html></html>"
//				},
//				2
//		);
//		
//		registerGame("Resident Evil 4 UHD",
//				Resources.getImageIconResized("resident-evil-4-uhd-banner.jpg", ResourceType.GAMES, bannerWidth, bannerHeight, Image.SCALE_AREA_AVERAGING),
//				Resources.getImageIconResized("resident-evil-4-uhd-sample-1.jpg", ResourceType.GAMES, sampleWidth, sampleHeight, Image.SCALE_AREA_AVERAGING),
//				new String[] {
//						"<html>Special agent Leon S. Kennedy is sent on a mission to rescue the U.S. President’s daughter who has been kidnapped. Leon battles new creatures infested by a new threat called Las Plagas and faces off against an aggressive group of enemies including mind-controlled villagers that are tied to Los Illuminados, the mysterious cult which is behind the abduction.</html>",
//						"<html>MINIMUM:<br>OS: Windows® 8 / Windows® 10<br>Processor: Intel® Core™ 2 Duo 2.4 Ghz, AMD Athlon™ X2 2.8 Ghz<br>Memory: 2 GB RAM<br>Graphics: NVIDIA® GeForce® 8800GTS, ATI Radeon™ HD 4850<br>DirectX: Version 9.0c<br>Storage: 15 GB available space<br><br>RECOMMENDED:<br>OS: Windows 8.1 / Windows® 10<br>Processor: Intel® Core™ 2 Quad 2.7 Ghz, AMD Phenom™ II X4 3 Ghz<br>Memory: 4 GB RAM<br>Graphics: NVIDIA® GeForce® GTX 560<br>DirectX: Version 9.0c<br>Storage: 15 GB available space</html>"
//				},
//				3
//		);
	}
	
	private void registerGame(String name, ImageIcon banner, ImageIcon samples, String[] descriptions, int id) {
		this.ids.put(name, id);
		this.games.put(name, banner);
		this.samples.put(name, samples);
		this.descriptions.put(name, descriptions);
	}
	
	public AbstractGame solveGameObject(Integer gameId) {		
		switch(gameId) {
			case 0: return new VampireSurvivors();
			case 1: return null;
			case 2: return new SecretsOfGrindea();
			case 3: return new Bio4UHD();
			default: return null;
		}
	}
	
	public Set<String> getNames() {
		return games.keySet();
	}
	
	public Collection<ImageIcon> getBanners() {
		return games.values();
	}
	
	public Collection<ImageIcon> getSamples() {
		return samples.values();
	}
	
	public Collection<String[]> getDescriptions() {
		return descriptions.values();
	}
	
	public ImageIcon getBanner(String gameName) {
		return games.get(gameName);
	}
	
	public ImageIcon getSample(String gameName) {
		return samples.get(gameName);
	}
	
	public String[] getDescriptions(String gameName) {
		return descriptions.get(gameName);
	}
	
	public Integer getIndex(String gameName) {
		return ids.get(gameName);
	}
}