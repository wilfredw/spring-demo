package com.wei.spring.app;

import com.wei.spring.app.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by viruser on 2019/6/18.
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.path("/testfun"),
                request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
        return route;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //builder会报红，开始我以为那个地方有错误，但是后面调试发现并没有影响
        //@formatter:off
        return builder.routes()
                .route(r -> r.path("/webp")
                        .filters(f ->
                                f.addResponseHeader("X-AnotherHeader", "baz"))
                        .uri("http://httpbin.org:80")
                )
                .build();
        //@formatter:on
    }

    @Bean
    public RouteLocator testMyFilterRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(r ->
                r.path("/aa")
                        //转发路由
                        .uri("http://localhost:8003/provider/test")
                        //注册自定义过滤器
                        .filters(new MyFilter())
                        //给定id
                        .id("user-service"))
                .build();
    }
}
