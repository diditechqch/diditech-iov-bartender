package com.diditech.iov.gps.app.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin
 * @ClassName: ThreadLocalUtils
 * @Description: TreadLocal工具类
 * @date 2017年3月9日 上午11:29:51
 */
public class ThreadLocalUtils {
    private static final ThreadLocal<Map<String, Object>> local = ThreadLocal.withInitial(HashMap::new);

    private ThreadLocalUtils() {
    }

    public static <T> T put(String key, T value) {
        local.get().put(key, value);
        return value;
    }

    public static void remove(String key) {
        local.get().remove(key);
    }

    public static void clear() {
        local.remove();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return ((T) local.get().get(key));
    }
}
