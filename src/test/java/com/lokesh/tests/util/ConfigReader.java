package com.lokesh.tests.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConfigReader {

    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize(){
        //load default properties
        log.info("Loading default properties from: {}", DEFAULT_PROPERTIES);
        properties = loadProperties();

        //check for any overrides
        for(String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        //print loaded properties
        log.info("Loaded properties:");
        log.info("=========================================");
        for (String key : properties.stringPropertyNames()) {
            log.info("{} = {}", key, properties.getProperty(key));
        }
        log.info("=========================================");
    }

    public static String getProp(String key){
        log.info("Retrieving property for key: {}", key);
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ResourceLoader.getResourceAsStream(DEFAULT_PROPERTIES));
        } catch (Exception e) {
            log.error("Error loading properties from file: " + DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}
