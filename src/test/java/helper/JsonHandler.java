package helper;

import com.github.wnameless.json.flattener.JsonFlattener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;

public class JsonHandler
{
    public static Map<String, Object> jsonToMap( String jsonInString )
    {
        Map<String, Object> flattenedJsonMap = null;
        JSONParser parser = new JSONParser(  );
        try
        {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse( jsonInString );
            String flattenedJson = JsonFlattener.flatten( jsonObject.toString() );
            flattenedJsonMap = JsonFlattener.flattenAsMap( jsonObject.toString() );
        }
        catch( Exception exception )
        {
            exception.printStackTrace();
        }
        return flattenedJsonMap;
    }
}
