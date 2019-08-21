package com.wei.spring.app.controller;


import com.wei.spring.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

/**
 * Created by viruser on 2019/8/14.
 */
@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.setName("犬小哈");
        user.setDesc("欢迎关注我的公众号: 小哈学Java");
        return Mono.just(user);
    }

    @GetMapping("/websession")
    public Mono<String> getSession(WebSession session) {
        session.getAttributes().putIfAbsent("note", "Howdy Cosmic Spheroid!");
        return Mono.just((String) session.getAttributes().get("note"));
    }

    @GetMapping("/websession2")
    public Mono<String> getSession2(WebSession session) {
        String value = (String)session.getAttribute("note");
        if (StringUtils.isEmpty(value)) {
            session.getAttributes().putIfAbsent("note", "Howdy Cosmic Spheroid!222");
            value = "first time 222";
        }
        return Mono.just(value);
    }
}