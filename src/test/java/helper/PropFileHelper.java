package helper;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropFileHelper {

    private static PropFileHelper instanceProp = null;
    String path = System.getProperty("user.dir");
    String absolutePath = path.replace("\\", "\\\\");
    private static Ini ini;
    private PropFileHelper() {
    }


    public static synchronized PropFileHelper getInstance( )
    {
        try
        {
            instanceProp = new PropFileHelper(  );
        }
        catch( Exception ex )
        {
        }

        return instanceProp;
    }

    public void generatePropFile(String prefix, HashMap<String, String> propertyKeyValues) {
        Properties prop = new Properties();
        FileOutputStream output = null;

        try {
            if (propertyKeyValues.size() > 0) {
                output = new FileOutputStream(absolutePath+"/src/test/resources/" + prefix + ".properties");
                this.setProperties(prop, propertyKeyValues);
                String propertyComment = " Generic Property File Created: \n Updated by: " + System.getenv("USER");
                prop.store(output, propertyComment);
            } else {
                System.out.println("No key,value(s) passed into function to create properties");
            }
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

    }

    public void setProperties(Properties props, HashMap<String, String> propertyKeyValues) {
        propertyKeyValues.forEach((key, value) -> {
            props.setProperty(key, value);
        });
    }

    public static Map<String, String> getSensorValue(String filePath , String section) {

        Map< String , String>  getValues = new HashMap<>(  );
        try
        {
         ini = new Ini( new File(filePath));
         getValues = ini.get(section);

     }catch(Exception e)
     {

     }

        return getValues;
    }
}
