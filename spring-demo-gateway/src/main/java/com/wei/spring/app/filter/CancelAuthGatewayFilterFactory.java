package com.wei.spring.app.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.WebSession;

import java.util.Arrays;
import java.util.List;

/**
 * Created by viruser on 2019/9/3.
 */
@Component
public class CancelAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CancelAuthGatewayFilterFactory.Config> {

    public CancelAuthGatewayFilterFactory() {
        super(CancelAuthGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(CancelAuthGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            WebSession session = exchange.getSession().block();
            session.getAttributes().remove("user_id");
            return chain.filter(exchange);
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public CancelAuthGatewayFilterFactory.Config setName(String name) {
            this.name = name;
            return this;
        }
    }

}
