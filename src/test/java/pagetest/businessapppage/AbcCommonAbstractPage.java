package pagetest.businessapppage;

/*
Created By: Shilpi Gupta sgupta@bhavnacorp.com
Updated By:
Date: 8/9/2018
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import config.EnvProperty;
import helper.AppConstants;
import helper.JsonHandler;
import helper.RestHandler;
import org.apache.commons.lang3.RandomStringUtils;

import org.ini4j.Ini;
import org.ini4j.Wini;
import org.json.JSONObject;
import ui.AbstractPage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static helper.AppConstants.*;
import static java.time.ZonedDateTime.now;

public class AbcCommonAbstractPage<T extends AbcCommonAbstractPage<T>> extends AbstractPage<T> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    EnvProperty envMerchantId = EnvProperty.getInstance(AppConstants.MERCHANTID_INI);
    public RestHandler restHandler;

    public String generate_random_number_by_range(int minRange, int maxRange) {
        logger.info("Generate random number");
        Integer randomNumber = ThreadLocalRandom.current().nextInt(minRange, maxRange);
        String randomNumberString = randomNumber.toString();
        return randomNumberString;
    }

    public String generate_random_string() {
        logger.info("Generate random String");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String randomString = "";
        Random random = new Random();
        char c = alphabet.charAt(random.nextInt(3));
        Integer randomNumber = random.nextInt(9000000) + 1000000;
        String randomAlphaNumbericString = randomNumber.toString();
        randomString = randomAlphaNumbericString + c;
        return randomString;
    }

    public EnvProperty load_subscription_file() {

        EnvProperty envProperty = null;
        try {
            Ini ini = new Wini(this.getClass().getResourceAsStream('/' + "subscription.ini"));
            envProperty = new EnvProperty(ini);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return envProperty;
    }

    public String get_subscription_test_data(String section, String key) {
        return load_subscription_file().getConfigPropertyValue(section, key);
    }

    public String get_current_date(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String get_future_date_with_required_format(int numOfDays, String format) {
        logger.info("Get future date with required format");
        return now().plusDays(numOfDays).format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
    }

    public String get_past_date_with_required_format(int numOfDays, String format) {
        logger.info("Get past date with required format");
        return now().minusDays(numOfDays).format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
    }

    public String get_future_date_with_time(int numberOfDays, int minutes, String format) {
        logger.info("Fetching required date with Time");
        return ZonedDateTime.now(ZoneOffset.UTC).plusDays(numberOfDays).plusMinutes(minutes).plusSeconds(RANDOM_UPPER_LIMIT)
                .format(DateTimeFormatter.ofPattern(format));
    }

    public String get_alphanumeric_data(int length) {
        logger.info("Generate random String");
        return RandomStringUtils.randomAlphanumeric(length);

    }

    public String get_numeric_data(int length) {
        logger.info("Generate random String");
        return RandomStringUtils.randomNumeric(length);

    }

    public String get_two_decimal_point_value(String value) {
        logger.info("return required values after decimal point");
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(Double.parseDouble(value)));
    }

    public String get_alphabetical_data(int length) {
        logger.info("Generate random String");
        return RandomStringUtils.randomAlphabetic(length);

    }

    public void auth_token(RestHandler restHandler) {
        String responseBody = restHandler
                .add_header("Content-Type", getConfigParamValue("REST_CLIENT_DETAILS", "content_type"))
                .add_header("Authorization", getConfigParamValue("REST_CLIENT_DETAILS", "authorization"))
                .add_url_param("grant_type", getConfigParamValue("REST_CLIENT_DETAILS", "grant_type"))
                .add_url_param("username", decode_content(getConfigParamValue("REST_CLIENT_DETAILS", "userName")))
                .add_url_param("password", decode_content(getConfigParamValue("REST_CLIENT_DETAILS", "passWord")))
                .add_url_param("password", getConfigParamValue("REST_CLIENT_DETAILS", "passWord"))
                .verify_status(200)
                .POST(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/token")
                .get_body();
        Map<String, Object> response = null;
        try {
            response = new ObjectMapper().readValue(responseBody, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String accessToken = "Bearer " + response.get("access_token");
        env.writeIniFile("Authorization", "accessToken", accessToken);
    }

    public void auth_token_client(RestHandler restHandler) {
        String responseBody = restHandler
                .add_header("Content-Type", getConfigParamValue("REST_CLIENT_DETAILS", "content_type"))
                .add_header("Authorization", getConfigParamValue("REST_CLIENT_DETAILS", "authorization"))
                .add_url_param("client_id", getConfigParamValue("REST_CLIENT_DETAILS", "client_id"))
                .add_url_param("grant_type", getConfigParamValue("REST_CLIENT_DETAILS", "grant_type_client"))
                .verify_status(200)
                .POST(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/token")
                .get_body();
        Map<String, Object> response = null;

        {
            try {
                response = new ObjectMapper().readValue(responseBody, HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String accessToken = "Bearer " + response.get("access_token");
        env.writeIniFile("Authorization", "accessTokenClient", accessToken);
    }

    public void delete_org_data_by_org_id(RestHandler restHandler, String orgId) {

        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("Authorization", "accessToken"))
                .verify_status(200)
                .DELETE(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/organization/" + env.getConfigPropertyValue("ORGANIZATION", "organizationId"))
                .get_body();
        System.out.println(responseBody);

    }

    public void delete_catalog_data_by_org_id(RestHandler restHandler) {

        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("Authorization", "accessToken"))
                .add_header("ABCFS-ORGANIZATION-ID", env.getConfigPropertyValue("ORGANIZATION", "organizationId"))
                .verify_status(200)
                .DELETE(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/catalog")
                .get_body();
        System.out.println(responseBody);

    }

    public void delete_billing_data(RestHandler restHandler) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("operationType", "DELETE");

        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("Authorization", "accessTokenClient"))
                .add_header("ABCFS-ORGANIZATION-ID", env.getConfigPropertyValue("ORGANIZATION", "organizationId"))
                .add_body(requestParams.toString())
                .verify_status(202)
                .POST(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/abcblg/billing-operations/organization-data")
                .get_body();
        System.out.println(responseBody);

    }

    public void delete_taxation_data(RestHandler restHandler) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("operationType", "DELETE");
        JSONObject operationPayload = new JSONObject();
        operationPayload.put("organizationId", env.getConfigPropertyValue("ORGANIZATION", "organizationId"));
        requestParams.put("operationPayload", operationPayload);

        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("Authorization", "accessTokenClient"))
                .add_body(requestParams.toString())
                .verify_status(202)
                .POST(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrl") + "/taxation/taxation-operations/organization-data")
                .get_body();
        System.out.println(responseBody);

    }

    public void pg_auth_token(RestHandler restHandler) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", decode_content(getConfigParamValue("REST_CLIENT_DETAILS", "userName")));
        requestParams.put("password", decode_content(getConfigParamValue("REST_CLIENT_DETAILS", "passWord")));
        String responseBody = restHandler
                .add_header("Content-Type", getConfigParamValue("REST_CLIENT_DETAILS", "content_type"))
                .add_header("Authorization", getConfigParamValue("REST_CLIENT_DETAILS", "pgAuthorization"))
                .add_body(requestParams.toString())
                .verify_status(200)
                .POST(getConfigParamValue("REST_CLIENT_DETAILS", "pgAuthorizationUrl"))
                .get_body();
        Map<String, Object> response = null;
        try {
            response = new ObjectMapper().readValue(responseBody, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String accessToken = "Bearer " + response.get("accessToken");
        env.writeIniFile("pgAuthorization", "pgAccessToken", accessToken);
    }

    public void get_Merchant(RestHandler restHandler) {
        String locationName = env.getConfigPropertyValue("LOCATION", "locationName");
        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("pgAuthorization", "pgAccessToken"))
                .verify_status(200)
                .GET(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrlPG") + "/merchant?q=name==\"" + locationName + "\"")
                .get_body();
        Map<String, Object> mapValue = JsonHandler.jsonToMap(responseBody);
        String merchantId = (String) mapValue.get("content[0].id");
        env.writeIniFile("Merchant", "id", merchantId);
        //Save merchant Id
        envMerchantId.writeIniFile("Merchant", locationName, merchantId);
    }

    public void delete_merchant_data_by_merchant_id(RestHandler restHandler) {
        String responseBody = restHandler
                .add_header("Content-Type", "application/json")
                .add_header("Authorization", env.getConfigPropertyValue("pgAuthorization", "pgAccessToken"))
                .add_header("ABCFS-TENANT-ID", env.getConfigPropertyValue("pg", "companyId"))
                .DELETE(getConfigParamValue("REST_CLIENT_DETAILS", "baseUrlPG") + "/merchant/" + env.getConfigPropertyValue("Merchant", "id"))
                .get_body();
        logger.info(responseBody);
    }

    protected RestHandler getRestClient(String section) {

        return new RestHandler(env, section);
    }

    public String getValueWhenSectionExist(String fileName, String sectionName) {
        EnvProperty envProperty = EnvProperty.getInstance(fileName);
        String organizationName = ORGANIZATION_NOT_EXIST;
        String path = System.getProperty("user.dir");
        String absolutePath = path.replace("/", "//");
        Ini iniFile = null;
        try {
            iniFile = new Wini(new File(absolutePath + "/src/test/resources/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Iterator<String> itSections = iniFile.keySet().iterator(); itSections.hasNext(); ) {
            String iniSectionName = itSections.next();
            if (iniSectionName.equals(sectionName)) {
                organizationName = envProperty.getConfigPropertyValue(sectionName, ORGANIZATION_NAME);
                break;
            } else organizationName = ORGANIZATION_NOT_EXIST;
        }
        return organizationName;
    }
}
