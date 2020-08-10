package sdk.generatestatement;

import com.jayway.restassured.response.ValidatableResponse;
import helper.ConvertJavaObjectToJson;
import helper.GetResponse;
import helper.GetUrl;
import helper.JsonHandler;
import org.slf4j.LoggerFactory;


import java.util.Map;

public class GenerateStatementTest
{
    private static final org.slf4j.Logger APPLICATION_LOGGER = LoggerFactory.getLogger(GenerateStatementTest.class);
    private GetResponse getResponse;
    private ValidatableResponse response;
    // private ValidatableResponse getResponse1;
    private String url;
    private String statementData;
    private GetUrl getUrl;

    public ValidatableResponse creatGeneralStatement(CreateGenerateStatementData createGenerateStatementData,int statusCode)
    {
        getResponse = new GetResponse();
        getUrl = new GetUrl();
        url = getUrl.createGenerateStatementUrl();
        statementData = new ConvertJavaObjectToJson().javaToJson( createGenerateStatementData );
        response = getResponse.postResponse( statementData, url, statusCode );
        Map<String, Object> jsonMapResponse = JsonHandler.jsonToMap(response.extract().body().asString());

        for(Map.Entry map:jsonMapResponse.entrySet())
        {
            System.out.println(map.getKey() + " :--> " + map.getValue());
        }
        return response;
    }
}
