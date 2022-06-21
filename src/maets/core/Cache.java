package maets.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
	
	public enum CacheType {
		USER_NAME;
	}

	private static Map<CacheType, Object> cacheMap = new ConcurrentHashMap<>(CacheType.values().length);
	
	public static void set(CacheType key, Object value) {
		cacheMap.put(key, value);
	}

	public static Object get(CacheType key) {
		return cacheMap.get(key);
	}
}