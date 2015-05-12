package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class ImageProperties {

    private final Properties properties = new Properties();
    static final Logger logger = LoggerFactory.getLogger(ImageProperties.class);

    public ImageProperties() {
        try {
            properties.load(new FileInputStream("./config/images.properties"));
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }

    public String getImagePath(String key) {
        String BASE_PATH = "./resources/images/";
        return BASE_PATH + properties.getProperty(key);
    }

}
