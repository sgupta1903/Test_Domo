package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class ServicePropertyFileReader {
    private static ServicePropertyFileReader instanceConf = null;
    private Properties props = null;

    private ServicePropertyFileReader(String filename) throws IOException {
        this.props = new Properties();

        try {
            this.props.load(ServicePropertyFileReader.class.getClassLoader().getResourceAsStream(filename + ".properties"));
        } catch (Exception var8) {
            var8.printStackTrace();
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader) cl).getURLs();
            URL[] var4 = urls;
            int var5 = urls.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                URL url = var4[var6];
            }
        }

    }

    public static synchronized ServicePropertyFileReader getInstance(String filename) {
        try {
            instanceConf = new ServicePropertyFileReader(filename);
        } catch (IOException var1) {
        }

        return instanceConf;
    }

    public String getValue(String propKey) {
        return this.props.getProperty(propKey);
    }

    public String getPropertyValue(String propertyKey) {

        String propertyValue = this.props.getProperty(propertyKey);

        if ("Linux".equals(propertyValue)) {
            return "/opt/selenium/drivers/chromedriver";
        }
        return propertyValue;
    }

}
