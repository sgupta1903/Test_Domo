package sdk.tax;

import com.jayway.restassured.response.ValidatableResponse;
import helper.ConvertJavaObjectToJson;
import helper.GetResponse;
import helper.GetUrl;
import helper.JsonHandler;
import org.slf4j.LoggerFactory;
import sdk.fee.CreateFeeData;
import sdk.fee.FeeTest;

import java.util.Map;

public class TaxTest
{
    private static final org.slf4j.Logger APPLICATION_LOGGER = LoggerFactory.getLogger(FeeTest.class);
    private GetResponse getResponse;
    private ValidatableResponse response;
    // private ValidatableResponse getResponse1;
    private String url;
    private String taxData;
    private GetUrl getUrl;


    public ValidatableResponse createtax( CreateTaxData createTaxData, int statusCode )
    {
        getResponse = new GetResponse();
        getUrl = new GetUrl();
        url = getUrl.createTaxUrl();
        taxData = new ConvertJavaObjectToJson().javaToJson( createTaxData );
        response = getResponse.postResponse( taxData, url, statusCode );
        Map<String, Object> jsonMapResponse = JsonHandler.jsonToMap(response.extract().body().asString());

        for(Map.Entry map:jsonMapResponse.entrySet())
        {
            System.out.println(map.getKey() + " :--> " + map.getValue());
        }
        return response;
    }
}
