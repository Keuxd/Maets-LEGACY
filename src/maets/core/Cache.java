package maets.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
	
	public enum CacheType {
		USER_NAME;
	}

	private static Map<CacheType, String> cacheMap = new ConcurrentHashMap<>(CacheType.values().length);
	
	public static void set(CacheType key, String value) {
		cacheMap.put(key, value);
	}

	public static String get(CacheType key) {
		return cacheMap.get(key);
	}
	
//	public static <T> T get(CacheType key, Class<T> type) {
//		return type.cast(cacheMap.get(key));
//	}
}