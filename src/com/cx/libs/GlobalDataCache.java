package com.cx.libs;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author CX
 */
public class GlobalDataCache {

	private static HashMap<String, Object> globalDataShareMap = new HashMap<String, Object>();
	private static HashMap<String, Object> globalCacheMap = new HashMap<String, Object>();

	/**
	 * 保存到静态hashmap
	 * 
	 * @param key
	 * @param obj
	 */
	public static void putShare(String key, Object obj) {
		globalDataShareMap.put(key, obj);
	}

	/**
	 * 从静态hashmap中取
	 * 
	 * @param key
	 * @return
	 */
	public static Object getShare(String key) {
		return globalDataShareMap.get(key);
	}

	public static void removeShare(String key) {
		globalDataShareMap.remove(key);
	}

	public static void putCache(String key, Object obj) {
		SoftReference<Object> sr = new SoftReference<Object>(obj);
		globalCacheMap.put(key, sr);
	}

	@SuppressWarnings("unchecked")
	public static Object getCache(String key) {
		SoftReference<Object> sr = (SoftReference<Object>) globalCacheMap.get(key);
		return (sr != null) ? sr.get() : null;
	}

	public static void clearData() {
		globalCacheMap.clear();
		globalDataShareMap.clear();
	}

}
