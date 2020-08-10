package sdk.fee;

import com.jayway.restassured.response.ValidatableResponse;
import helper.ConvertJavaObjectToJson;
import helper.GetResponse;
import helper.GetUrl;
import helper.JsonHandler;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FeeTest
{
    private static final org.slf4j.Logger APPLICATION_LOGGER = LoggerFactory.getLogger(FeeTest.class);
    private GetResponse getResponse;
    private ValidatableResponse response;
   // private ValidatableResponse getResponse1;
    private String url;
    private String feeData;
    private GetUrl getUrl;


    public ValidatableResponse createBank( CreateFeeData createFeeData, int statusCode )
    {
        getResponse = new GetResponse();
        getUrl = new GetUrl();
        url = getUrl.createFeeUrl();
        feeData = new ConvertJavaObjectToJson().javaToJson( createFeeData );
        response = getResponse.postResponse( feeData, url, statusCode );
        Map<String, Object> jsonMapResponse = JsonHandler.jsonToMap(response.extract().body().asString());

        for(Map.Entry map:jsonMapResponse.entrySet())
        {
            System.out.println(map.getKey() + " :--> " + map.getValue());
        }
        return response;
    }
  /*  public GetResponse getFee(CreateFeeData createFeeData ,int statusCode)
    {
        getResponse = new GetResponse();
        getUrl = new GetUrl();
        url = getUrl.getFeeUrl();
        feeData = new ConvertJavaObjectToJson().javaToJson( createFeeData );
        response=getResponse.getResponse( url,statusCode );

        return getResponse;
    }*/
}
