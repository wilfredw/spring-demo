package com.wei.spring.app.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Created by viruser on 2019/8/21.
 */
@Component
public class AuthenticateGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticateGatewayFilterFactory.Config> {

    public AuthenticateGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            WebSession session = exchange.getSession().block();
            String user_id = (String)session.getAttribute("user_id");
            if (StringUtils.isEmpty(user_id)) {
                System.out.println("no user_id");
                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().add("Location", "/security/");
                return response.setComplete();
            } else {
                System.out.println("get  user_id: " + user_id);
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .header("Auth-User-Id", user_id)
                        .build();
                return chain.filter(exchange.mutate().request(request).build());
            }
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public Config setName(String name) {
            this.name = name;
            return this;
        }
    }

}
