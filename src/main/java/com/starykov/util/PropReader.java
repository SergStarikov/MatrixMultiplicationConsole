package com.starykov.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public enum PropReader {
    INSTANCE;
    private final Properties properties;

    PropReader() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("Prop.properties"));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}
