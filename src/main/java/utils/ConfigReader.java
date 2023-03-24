package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;
    private static ConfigReader configReader;

    private ConfigReader() {
        BufferedReader reader;
        String propertyFilePath = "src/main/resources/Configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static ConfigReader getInstance( ) {
        if(configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if(baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }

    public String allPosts() {
        return properties.getProperty("base_Url")+properties.getProperty("allPosts");
    }
    public String postNumber(String route) {
        return properties.getProperty("base_Url")+properties.getProperty("allPosts")+route;
    }

    public String allUsers() {
        return properties.getProperty("base_Url")+properties.getProperty("allUsers");
    }
    public String userNumber(String route) {
        return properties.getProperty("base_Url")+properties.getProperty("allUsers")+route;
    }
    public String test5route() {
        String getTest5route= properties.getProperty("test5route");
        if(getTest5route != null) return getTest5route;
        else throw new RuntimeException("Route not specified in the Configuration.properties file.");
    }
}

