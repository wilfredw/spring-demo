package com.wei.spring.app.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viruser on 2019/10/29.
 */
public class MongoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Autowired
    private MongoProperty mongoProperty;

    @Bean
    public MongoClient mongoClient() {
        return createMongoClient(mongoProperty.getHost(),
                Integer.valueOf(mongoProperty.getPort()),
                mongoProperty.getAuthority(),
                mongoProperty.getReplicaSet());
    }

    public MongoClient createMongoClient(String host, int port, String authority, String replicaSet) {
        MongoClient mongoClient = null;
        try {
            ServerAddress serverAddress = null;
            List<ServerAddress> seeds = new ArrayList<>();
            List<MongoCredential> credentials = new ArrayList<>();
            if (!StringUtils.isEmpty(replicaSet)) {
                String[] replicaArray = replicaSet.split(",");
                for (String replica : replicaArray) {
                    String[] hostPortPair = replica.split(":");
                    seeds.add(new ServerAddress(hostPortPair[0], Integer.parseInt(hostPortPair[1])));
                }
            } else {
                serverAddress = new ServerAddress(host, port);
            }
            if (!StringUtils.isEmpty(authority)) {
                String[] authorityArr = authority.split(":");
                if (authorityArr.length != 2) {
                    throw new Exception("authority lenght is wrong.");
                } else {
                    String username = authorityArr[0];
                    String[] passDatabasePair = authorityArr[1].split("@");
                    if (passDatabasePair.length != 2) {
                        throw new Exception("authority lenght is wrong. password and database required");
                    } else {
                        String password = passDatabasePair[0];
                        String database = passDatabasePair[1];
                        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
                        credentials.add(mongoCredential);
                    }
                }
            }
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(100);
            builder.threadsAllowedToBlockForConnectionMultiplier(4);
            builder.connectTimeout(30000);
            builder.maxWaitTime(1000);
            builder.socketKeepAlive(true);
            builder.socketTimeout(1000);
            builder.heartbeatSocketTimeout(10000);

            if (seeds != null && seeds.size() > 0) {
                mongoClient = new MongoClient(seeds, credentials, builder.build());
            } else {
                if (null != credentials && !credentials.isEmpty()) {
                    mongoClient = new MongoClient(serverAddress, credentials, builder.build());
                } else {
                    mongoClient = new MongoClient(serverAddress, builder.build());
                }
            }
        } catch (Exception e) {
            logger.error("error: ", e);
        }
        return mongoClient;
    }

}
