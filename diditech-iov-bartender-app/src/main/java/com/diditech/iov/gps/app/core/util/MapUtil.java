package com.diditech.iov.gps.app.core.util;

import java.util.List;
import java.util.Map;

/**
 * Map工具类
 * @author zhjd <br>
 * @date 2023/3/23 <br>
 */
public class MapUtil {

    public static <V, T> void mergeMap(Map<V, List<T>> source, Map<V, List<T>> target) {
        target.keySet()
                .parallelStream()
                .forEach(key -> {
                    if (!source.keySet().contains(key) || source.get(key).isEmpty()) {
                        return;
                    }
                    if (source.keySet().contains(key)) {
                        target.get(key).addAll(source.get(key));
                    } else {
                        target.put(key, source.get(key));
                    }
                });
    }
}
