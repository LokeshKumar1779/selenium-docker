package com.lokesh.tests.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getTestData(String jsonFilePath, Class<T> valueType) {
        log.info("Loading test data from JSON file: {}", jsonFilePath);
        try {
            return objectMapper.readValue(ResourceLoader.getResourceAsStream(jsonFilePath), valueType);
        } catch (Exception e) {
            log.error("Failed to load test data from JSON file: {}", jsonFilePath, e);
            throw new RuntimeException("Error loading test data", e);
        }
    }
}
