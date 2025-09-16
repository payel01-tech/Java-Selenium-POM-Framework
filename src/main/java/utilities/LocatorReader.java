package utilities;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorReader {
    private Properties prop;

    public void loadLocatorReader()
    {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/locator.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public By getProperties(String key)
    {
        if(prop == null)
            loadLocatorReader();
        String value = prop.getProperty(key);
        String vals[] = value.split(":",2);
        String locatorType = vals[0];
        String locatorValue = vals[1];
        switch(locatorType.toLowerCase())
        {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "classname":
                return By.className(locatorValue);
            case "tagname":
                return By.tagName(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }
}
