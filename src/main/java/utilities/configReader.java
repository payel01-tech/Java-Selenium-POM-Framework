package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configReader {

    private static Properties prop;

    public static void loadConfig()
    {
        try{
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key)
    {
        if(prop == null)
            loadConfig();
        return prop.getProperty(key);
    }
}
