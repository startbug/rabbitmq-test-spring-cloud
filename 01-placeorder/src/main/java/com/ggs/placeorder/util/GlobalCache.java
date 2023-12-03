package com.ggs.placeorder.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 21:39
 */
public class GlobalCache {

    public static final Map<String, Object> map = new HashMap<>();

    public static void set(String key, Object value) {
        map.put(key, value);
    }

    public static Object get(String key) {
        Object value = map.get(key);
        return value;
    }

    public static void remove(String key) {
        map.remove(key);
    }

}
