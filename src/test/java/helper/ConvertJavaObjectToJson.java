package helper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConvertJavaObjectToJson
{
    public String javaToJson( Object object )
    {
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = null;
        try
        {

            // Convert object to JSON string
            jsonInString = mapper.writeValueAsString( object );

            System.out.println( jsonInString );

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString( object );
            System.out.println( jsonInString );
        }
        catch( JsonGenerationException exception )
        {
            exception.printStackTrace();
        }
        catch( JsonMappingException exception )
        {
            exception.printStackTrace();
        }
        catch( IOException exception )
        {
            exception.printStackTrace();
        }
        return jsonInString;
    }

}
