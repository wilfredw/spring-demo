package com.wei.spring.app.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by viruser on 2019/10/29.
 */
@Service
public class MongoProperty {
    @Value("mongo.host")
    private String host;
    @Value("mongo.port")
    private String port;
    @Value("mongo.authority")
    private String authority;
    @Value("mongo.replica-set")
    private String replicaSet;
    @Value("mongo.db")
    private String db;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getReplicaSet() {
        return replicaSet;
    }

    public void setReplicaSet(String replicaSet) {
        this.replicaSet = replicaSet;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
