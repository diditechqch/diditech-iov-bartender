package com.diditech.iov.gps.api.core;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询请求对象，支持分页
 *
 * @author zhjd
 * @date 20190820
 */
@Data
public class QueryBase {
    //排序
    private Map<String, String> sortMap = new HashMap<>();

    public String getSorting() {
        if (CollectionUtils.isEmpty(this.sortMap)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("ORDER BY");
        sb.append(" ");
        this.sortMap.keySet().forEach(item -> {
            sb.append(item);
            sb.append(" ");
            sb.append(this.sortMap.get(item));
            sb.append(",");
        });
        return sb.toString().substring(0, sb.length() - 1);
    }
}
