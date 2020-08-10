package helper;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import listeners.SuiteInitilizationListerner;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static helper.AppConstants.*;
import static helper.AppConstants.XRAY_TESTNG_EXECUTION_CREATION_URL;

public class XrayExecutionResultGeneration {

    private static final Logger LOGGER = LoggerFactory.getLogger(XrayExecutionResultGeneration.class);

    public void generateXrayResult() {
        LOGGER.info("Xray Result generation has started");
        RequestSpecification requestBody = RestAssured.given();
        requestBody.header(CONTENT_TYPE, APPLICATION_JSON);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(XRAY_CLIENT_ID_KEY, XRAY_CLIENT_ID);
        jsonObject.put(XRAY_CLIENT_SECRET_ID_Key, XRAY_CLIENT_SECRET_ID);
        requestBody.body(jsonObject.toJSONString());

        Response response = requestBody.post(XRAY_AUTHORIZATION_URL);
        String authToken = response.asString();

        LOGGER.info("*****authentication token is *****" + authToken);

        String finalToken = authToken.replace("\"", "");
        try {
            String path = System.getProperty("user.dir") + TESTNG_RESULT_XML;
            String absolutePath = path.replace("/", "//");
            String token = "Bearer " + finalToken;
            // read cucumber json file
            String requestBodyFile = null;

            requestBodyFile = new String(Files.readAllBytes(Paths.get(absolutePath + "")));
            LOGGER.info("File is" + requestBodyFile);
//            Response requestResponse = RestAssured.given().header(AUTHORIZATION_HEADER, token)
//                    .header(CONTENT_TYPE, TEXT_XML)
//                    .body(requestBodyFile).expect().statusCode(200).when()
//                    .post(XRAY_TESTNG_EXECUTION_CREATION_URL).then().extract().response();
//            LOGGER.info("#################" + requestResponse.asString());

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
