package com.mobitide.common.data;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author CX
 */
public class MGlobalDataCache {

    private static HashMap<String, Object> globalDataShareMap = new HashMap<String, Object>();

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



    public static void clearData() {
        globalDataShareMap.clear();
    }

}
