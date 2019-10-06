package com.starykov.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public enum PropReader {
    INSTANCE;
    private final Properties properties;
    private final Logger logger = Logger.getLogger(PropReader.class);
    PropReader() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("Prop.properties"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }


}
