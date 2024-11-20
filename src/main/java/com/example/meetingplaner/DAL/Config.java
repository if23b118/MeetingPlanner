package com.example.meetingplaner.DAL;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {
    private static final Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(Config.class);

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            logger.info("config file successfully loaded.");
        } catch (IOException e) {
            logger.error("Error loading config file.");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
