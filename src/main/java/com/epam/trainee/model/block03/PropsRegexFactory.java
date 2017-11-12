package com.epam.trainee.model.block03;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropsRegexFactory {

    private static final String PROPERTIES_FILE_PATH = "regex03.properties";
    private static Properties properties;

    static  {
        properties = new Properties();
        URL propURL = ClassLoader.getSystemResource(PROPERTIES_FILE_PATH);
        String file = propURL.getFile();

        try (FileInputStream fis = new FileInputStream(file)){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRegex(RecordAttribute attribute) {
        return properties.getProperty(attribute.name());
    }
}
