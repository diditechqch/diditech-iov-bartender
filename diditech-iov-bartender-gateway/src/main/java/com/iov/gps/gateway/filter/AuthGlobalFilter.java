package com.iov.gps.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * @author hefan
 * @date 2020/8/18 10:25
 */
@Component
@Order(0)
@Slf4j
public class AuthGlobalFilter implements GlobalFilter {
    private static final String TOKEN_PREFIX = "BEARER ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        log.info("AuthGlobalFilter.filter() token:{}", token);
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = StrUtil.replaceIgnoreCase(token, TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter.filter() user:{}", userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header("client", userStr).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }
}
