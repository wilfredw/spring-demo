package com.wei.spring.app.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wei on 2019/8/30.
 */
@Component
public class SaveResponseAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<SaveResponseAuthGatewayFilterFactory.Config> {

    public SaveResponseAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpResponse originalResponse = exchange.getResponse();
            WebSession session = exchange.getSession().block();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
//                @Override
//                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                    System.out.println("SaveResponseAuth ResponseDecorator writeWith");
//                    if (body instanceof Flux) {
//                        Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//                        return super.writeWith(fluxBody.map(dataBuffer -> {
//                            // probably should reuse buffers
//                            byte[] content = new byte[dataBuffer.readableByteCount()];
//                            dataBuffer.read(content);
//                            //释放掉内存
//                            DataBufferUtils.release(dataBuffer);
//                            String s = new String(content, Charset.forName("UTF-8"));
//                            //TODO，s就是response的值，想修改、查看就随意而为了
//                            System.out.println(s);
//                            JSONObject jsonObject = JSONObject.parseObject(s);
//                            Integer code = jsonObject.getInteger("code");
//                            String userId = jsonObject.getString("user_id");
//                            if (null != code && code.equals(0)) {
//                                // TODO do save session
//                                session.getAttributes().put("user_id", userId);
//                            }
//                            byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
//                            return bufferFactory.wrap(uppedContent);
//                        }));
//                    }
//                    // if body is not a flux. never got there.
//                    return super.writeWith(body);
//                }

                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    //body 拦截处理器处理响应
                    return Flux.from(body)
                            .map(dataBuffer -> {
                                System.out.println("SaveResponseAuth writeWith");
                                //响应信息转换为字符串
                                String reqBody = null;
                                try {
                                    //dataBuffer 转换为String
                                    reqBody = IOUtils
                                            .toString(dataBuffer.asInputStream(), "UTF-8")
                                            .replaceAll(">\\s{1,}<", "><");
                                    System.out.println("writeWith reqBody: " + reqBody);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return reqBody;
                            }).flatMap(orgBody -> {
                                //根据原有的响应信息构建新响应信息并写入到resp中
                                //此处可以根据业务进行组装新的响应信息，
                                // 例如：登录后创建会话
                                //- 拿到登录请求的响应信息，判断登录是否成功
                                //- 登录成功调用创建会话接口，根据创建会话接口返回组装登录的真实响应
                                System.out.println("orgBody= " + orgBody);
                                JSONObject jsonObject = JSONObject.parseObject(orgBody);
                                Integer code = jsonObject.getInteger("code");
                                String userId = jsonObject.getString("user_id");
                                if (null != code && code.equals(0)) {
                                    // TODO do save session
                                    session.getAttributes().put("user_id", userId);
                                }
                                String rbody = orgBody + "new1234";
                                HttpHeaders headers = getDelegate().getHeaders();
                                headers.setContentLength(rbody.length());
                                return getDelegate().writeWith(Flux.just(rbody)
                                        .map(bx -> getDelegate().bufferFactory()
                                                .wrap(bx.getBytes())));
                            }).then();
                }

                @Override
                public Mono<Void> writeAndFlushWith(
                        Publisher<? extends Publisher<? extends DataBuffer>> body) {
                    return writeWith(Flux.from(body).flatMapSequential(p -> p));
                }
            };

            // replace response with decorator
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        }, -2);
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
