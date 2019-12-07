package com.wei.spring.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viruser on 2019/12/7.
 */
@Configuration
public class RedisConfiguration {

    private enum RedisTypeEnum{
        SINGLE("single"),
        SENTINEL("sentinel"),
        CLUSTER("cluster");
        private String code;
        private RedisTypeEnum(String code) {
            this.code = code;
        }
        public String getCode() {
            return this.code;
        }
    }

    @Value("${configuration.type}")
    private String type;

    @Value("${configuration.host}")
    private String host;

    @Value("${configuration.port}")
    private String port;

    @Value("${configuration.password}")
    private String password;

    @Value("${configuration.database}")
    private String database;

    @Value("${configuration.sentinel.master-name}")
    private String sentinelMasterName;

    @Value("${configuration.sentinel.addresses}")
    private String sentinelAddresses;

    @Value("${configuration.cluster.nodes}")
    private String clusterNodes;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        if (!StringUtils.isEmpty(type)) {
            if (RedisTypeEnum.SINGLE.getCode().equalsIgnoreCase(type)) {
                RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
                config.setHostName(host);
                config.setPort(Integer.valueOf(port));
                config.setPassword(RedisPassword.of(password));
                config.setDatabase(Integer.valueOf(database));
                return new JedisConnectionFactory(config);
            } else if (RedisTypeEnum.SENTINEL.getCode().equalsIgnoreCase(type)) {
                List<RedisNode> redisNodeList = new ArrayList<>(3);
                String[] addressArray = sentinelAddresses.split(",");
                for (String addr : addressArray) {
                    String[] addrPair = addr.split(":");
                    redisNodeList.add(new RedisNode(addrPair[0], Integer.valueOf(addrPair[1])));
                }
                RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
                sentinelConfig.master(sentinelMasterName);
                for (RedisNode node : redisNodeList) {
                    sentinelConfig.sentinel(node);
                }
                return new JedisConnectionFactory(sentinelConfig);
            } else if (RedisTypeEnum.CLUSTER.getCode().equalsIgnoreCase(type)) {
                RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
                String[] clusterNodeAddrs = clusterNodes.split(",");
                for (String nodeAddr : clusterNodeAddrs) {
                    String[] addr = nodeAddr.split(":");
                    clusterConfiguration.clusterNode(addr[0], Integer.valueOf(addr[1]));
                }
                return new JedisConnectionFactory(clusterConfiguration);
            }
        }
        return null;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory());
    }

}
