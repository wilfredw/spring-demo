package com.wei.spring.app.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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

        WebSession session = exchange.getSession().block();
        String user_id = (String)session.getAttribute("user_id");
        if (StringUtils.isEmpty(user_id)) {
            System.out.println("no user_id");
        } else {
            System.out.println("get  user_id: " + user_id);
        }
        String note = (String)session.getAttribute("note");
        if (StringUtils.isEmpty(note)) {
            System.out.println("no note");
        } else {
            System.out.println("get note: " + note);
        }
//        Object value;
//        Exception resultEx = null;
//        exchange.getSession().map(s -> validSession(s))
//                .onErrorResume(e -> {
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete();
//                })
//                .then(() -> {
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete()})
//                .then(chain.filter(exchange));
        return chain.filter(exchange);
    }

    private String validSession(WebSession session) {
        String value = session.getRequiredAttribute("user_id");
        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("no user id");
        }
        return value.toString();
    }

    @Override
    public int getOrder() {
        return 0;
    }


}