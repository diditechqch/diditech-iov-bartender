package com.iov.gps.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.iov.gps.gateway.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 白名单路径访问时需要移除JWT请求头
 * @author hefan
 * @date 2020/8/18 10:25
 */
@Component
@Order(-1)
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        request = markIfInWhitelistIp(request);
        request = markIfIsIgnorePath(request);
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    private ServerHttpRequest markIfInWhitelistIp(ServerHttpRequest request) {
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String ignoreIp : ignoreUrlsConfig.getIps()) {
            if (pathMatcher.match(ignoreIp, request.getRemoteAddress().getAddress().getHostAddress())) {
                // 属于白名单IP的在HEADER添加标识
                return request.mutate().header("inner", StrUtil.BACKSLASH).build();
            }
        }
        return request;
    }

    private ServerHttpRequest markIfIsIgnorePath(ServerHttpRequest request) {
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String ignoreUrl : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(ignoreUrl, request.getURI().getPath())) {
                // 属于白名单路径的移除JWT请求头
                request = request.mutate().header("Authorization", "").build();
            }
        }
        return request;
    }
}
