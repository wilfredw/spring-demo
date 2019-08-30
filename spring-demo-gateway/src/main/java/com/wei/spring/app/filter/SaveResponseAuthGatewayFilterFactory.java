package com.wei.spring.app.filter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * Created by viruser on 2019/8/30.
 */
public class SaveResponseAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<SaveResponseAuthGatewayFilterFactory.Config> {

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
            String user_id = (String)session.getAttribute("userid");
            if (StringUtils.isEmpty(user_id)) {
                System.out.println("no user_id");
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            } else {
                System.out.println("get  user_id: " + user_id);
                return chain.filter(exchange);
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
