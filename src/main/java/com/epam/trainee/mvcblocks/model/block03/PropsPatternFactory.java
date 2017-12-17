package com.epam.trainee.mvcblocks.model.block03;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class PropsPatternFactory {

    private static final String PROPERTIES_FILE_PATH = "regex03.properties";
    private static final Map<String, Pattern> PROPS_PATTERNS = new HashMap<>();

    private static Properties properties;

    static {
        properties = new Properties();
        URL propURL = ClassLoader.getSystemResource(PROPERTIES_FILE_PATH);
        String file = propURL.getFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                Pattern pattern = Pattern.compile((String) entry.getValue());
                PROPS_PATTERNS.put(key, pattern);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pattern getPattern(String attribute) {
        return PROPS_PATTERNS.get(attribute);
    }
}
