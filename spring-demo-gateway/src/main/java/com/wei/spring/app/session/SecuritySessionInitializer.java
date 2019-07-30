package com.wei.spring.app.session;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by viruser on 2019/7/30.
 */
public class SecuritySessionInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecuritySessionInitializer() {
        super(SecurityConfig.class, SessionConfiguration.class);
    }
}