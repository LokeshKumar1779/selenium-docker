package com.lokesh.tests.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {

    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResourceAsStream(String resourcePath) throws IOException {
        log.info("reading resource from path: {}", resourcePath);
        InputStream inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(resourcePath);
        if (Objects.nonNull(inputStream)) {
            log.info("Resource found in classpath: {}", resourcePath);
            return inputStream;
        }
        log.warn("Resource not found in classpath, trying file system: {}", resourcePath);
        Path path = Path.of(resourcePath);
        if (!Files.exists(path)) {
            log.error("Resource not found at path: {}", resourcePath);
            throw new IOException("Resource not found at path: " + resourcePath);
        }
        log.info("Resource found in file system: {}", resourcePath);
        return Files.newInputStream(path);
    }
}
