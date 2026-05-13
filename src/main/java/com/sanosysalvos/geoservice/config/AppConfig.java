package com.sanosysalvos.geoservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class AppConfig {

    private static AppConfig instance;

    @Value("${spring.application.name:geo-service}")
    private String serviceName;

    @Value("${server.port:3002}")
    private int port;

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/geoservice}")
    private String databaseUrl;

    @Value("${logging.level.com.sanosysalvos:INFO}")
    private String logLevel;

    @Value("${geo.default-zone:Santiago}")
    private String defaultZone;

    @Value("${geo.radius.default:5}")
    private double defaultRadius;

    @Value("${geo.cache.enabled:true}")
    private boolean cacheEnabled;

    private AppConfig() {
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AppConfig not initialized");
        }
        return instance;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getDefaultZone() {
        return defaultZone;
    }

    public double getDefaultRadius() {
        return defaultRadius;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public String getServiceInfo() {
        return String.format("Service: %s, Port: %d, Default Zone: %s",
            serviceName, port, defaultZone);
    }
}