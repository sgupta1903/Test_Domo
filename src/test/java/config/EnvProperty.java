package config;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import static helper.AppConstants.SRC_TO_RESOURCE_PATH;

public class EnvProperty {

    private static EnvProperty envProperty;
    private Ini ini;

    private EnvProperty(String filename) throws IOException {

        try {
            String path = System.getProperty("user.dir");
            String absolutePath = path.replace("/", "//");
            Ini ini = new Wini(new File(absolutePath + "/src/test/resources/" + filename));
            this.ini = ini;

        } catch (Exception exception) {
            exception.printStackTrace();
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader) cl).getURLs();
            URL[] var4 = urls;
            int var5 = urls.length;

            for (int var6 = 0; var6 < var5; var6++) {
                URL url = var4[var6];
            }
        }

    }

    public EnvProperty(Ini ini) {
        this.ini = ini;
    }

    public static synchronized EnvProperty getInstance(String filename) {
        try {
            envProperty = new EnvProperty(filename);
        } catch (IOException var) {
            var.printStackTrace();
        }

        return envProperty;
    }

    public String getConfigPropertyValue(String section, String key) {
        String value = ini.get(section, key);
        return value;
    }

    public Map<String, String> get(String section) {
        return this.ini.get(section);
    }

    public void writeIniFile(String sectionName, String key, String value) {
        ini.put(sectionName, key, value);
        try {
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeIniSectionName(String sectionName) {
        ini.add(sectionName);
    }

    public void clearIniFile() {
        ini.clear();
        try {
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getTimeout() {
        return Integer.parseInt(ini.get("Default", "timeout"));
    }

    public void loadFile(String fileName) {
        try {
            ini.load(new File(System.getProperty("user.dir") + SRC_TO_RESOURCE_PATH +fileName));
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
