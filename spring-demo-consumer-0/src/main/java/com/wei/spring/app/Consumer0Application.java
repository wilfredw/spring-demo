package com.wei.spring.app;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class Consumer0Application {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**自定义配置ribbon负载均衡算法
     * @return
     */
    @Bean
    public IRule myRule(){
        //return new RoundRobinRule();//轮询
        //return new RetryRule();//重试
        return new BestAvailableRule();
    }


    @Primary
    @Bean(name="lbcRestTemplate")
    RestTemplate lbcRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Consumer0Application.class, args);
    }
}
