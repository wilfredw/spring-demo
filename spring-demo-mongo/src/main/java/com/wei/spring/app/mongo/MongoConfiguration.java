package com.wei.spring.app.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by viruser on 2019/10/29.
 */
public class MongoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Autowired
    private MongoProperty mongoProperty;



}
