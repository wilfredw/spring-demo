package com.wei.spring.app.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

/**
 * Created by viruser on 2019/7/29.
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("1111111111111111");
//        return exchange.getSession().map(s -> s.<String>getAttribute("user_id"))
//                .doOnNext(System.out::print)
//                .then(chain.filter(exchange));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}