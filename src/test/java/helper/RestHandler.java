package helper;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import config.EnvProperty;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RestHandler {
    private EnvProperty envProperty;
    private String section;
    private RequestSpecification reqSpec;
    private ResponseSpecification resSpec;
    private ValidatableResponse response;

    public RestHandler( EnvProperty envProperty, String section) {
        RestAssured.reset();
        RestAssured.useRelaxedHTTPSValidation();
        this.envProperty = envProperty;
        this.section = section;
        this.reqSpec = build_request_spec();
        this.resSpec = build_response_spec();
        //loadCertificates(reqSpec);
        // reqSpec.baseUri(getConfigParamValue("baseUrl"));
    }

    public RestHandler OPTIONS(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .options(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler OPTIONS(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .options(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler HEAD(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .head(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler HEAD(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .head(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler GET(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .get(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler GET(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .get(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler POST(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .post(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler POST(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .post(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler PUT(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .put(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler PUT(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .put(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler DELETE(String resource) {
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .delete(resource)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler DELETE(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec) {
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .delete(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RestHandler PATCH(String uri){
        response = RestAssured
            .given()
            .spec(reqSpec)
            .when()
            .patch(uri)
            .then()
            .spec(resSpec);
        return this;
    }

    public RestHandler PATCH(String resource, RequestSpecification requestSpec,ResponseSpecification responseSpec){
        loadCertificates(requestSpec);
        requestSpec.baseUri(getConfigParamValue("baseUrl"));

        response = RestAssured
            .given()
            .spec(requestSpec)
            .when()
            .patch(resource)
            .then()
            .spec(responseSpec);
        return this;
    }

    public RequestSpecification build_request_spec() {
        return new RequestSpecBuilder().build();
    }

    public ResponseSpecification build_response_spec() {
        return new ResponseSpecBuilder().build();
    }

    public RestHandler verify_status(int statusCode) {
        //resSpec.statusCode(statusCode);
        return this;
    }

   /* public RestHandler verify_header(String name, String value) {
        resSpec.header(name, containsString(value));
        return this;
    }*/

    /*public RestHandler verify_content(String value ) {
        resSpec.body(containsString(value));
        return this;
    }*/

    /*public RestHandler verify_content_with_xsd(String path) {
        resSpec.body(matchesXsd(getFile(path)));
        return this;
    }*/

    /*public RestHandler verify_content_with_dtd(String path) {
        resSpec.body(matchesXsd(getFile(path)));
        return this;
    }*/

    /*public RestHandler verify_content_with_xPath(String xpath) {
        resSpec.body(hasXPath(xpath));
        return this;
    }*/

    /*public RestHandler verify_content_with_xPath(String xpath, String value ) {
        resSpec.body(hasXPath(xpath), containsString(value));
        return this;
    }
*/
  /*  public RestHandler verify_content_with_jsonPath(String jsonPath, String value ) {
        resSpec.body(jsonPath, containsString(value));
        return this;
    }*/

    public RestHandler add_proxy() {
        reqSpec.proxy(getConfigParamValue("proxyHost"), Integer.parseInt(getConfigParamValue("proxyPort")));
        return this;
    }

    public RestHandler add_header(String name, String value) {
        reqSpec.header(name, value);
        return this;
    }

    public RestHandler add_headers(String name, String value, Object... nameValuePairs) {
        reqSpec.headers(name, value, nameValuePairs);
        return this;
    }

    public RestHandler add_url_param(String name, String value) {
        reqSpec.queryParam(name, value);
        return this;
    }

    public RestHandler add_body(String content) {
        reqSpec.body(content);
        return this;
    }

    public RestHandler add_file_content_to_body(String path) {
        reqSpec.body(getFile(path));
        return this;
    }

    public RestHandler add_form_data(String name, String value) {
        reqSpec.multiPart(name, value);
        return this;
    }

    public RestHandler add_form_data(String name, String value, String mimeType) {
        reqSpec.multiPart(name, value, mimeType);
        return this;
    }

    public RestHandler add_form_file(String name, String path) {
        reqSpec.multiPart(name, getFile(path));
        return this;
    }

    public RestHandler add_req_content_type(ContentType contentType) {
        RestAssured.requestContentType(contentType);
        return this;
    }

    public RestHandler add_res_content_type(ContentType contentType) {
        RestAssured.responseContentType(contentType);
        return this;
    }

    public RestHandler basic_authentication(String username, String password) {
        reqSpec.auth().basic(username, password);
        return this;
    }

    public int get_status_code() {
        return response.extract().statusCode();
    }

    public String get_body() {
        return response.extract().body().asString() != null ? response.extract().body().asString() : "";
    }

    public void download_body(String path) {
        InputStream in = response.extract().body().asInputStream();
        try {
            FileUtils.copyInputStreamToFile(in, getFile(path));
        } catch (IOException e) {
            Assert.fail( "Failure downloading body to file " + path + ".");
        }
    }

    public String get_headers() {
        return response.extract().headers().toString();
    }

    public String get_header(String name) {
        return response.extract().header(name);
    }

    public String get_header_trail(String name) {
        return response.extract().header(name).replaceFirst(".*/([^/?]+).*", "$1");
    }

    private void loadCertificates(RequestSpecification requestSpec) {
        Map<String,String> certificateStores =  this.envProperty.get("CERTIFICATES");
        Assert.assertNotNull(certificateStores, "No configuration section provided in configuration file with the name: CERTIFICATES");
        Set<String> keys = certificateStores.keySet();
        Iterator<String> itr = keys.iterator();

        while(itr.hasNext()) {
            String key = itr.next();
            if ( StringUtils.isNotBlank(key) && !(key.contains("Password"))) {
                requestSpec.keystore(getConfigParamValue("CERTIFICATES", key), getConfigParamValue("CERTIFICATES", key + "Password"));
            }
        }
    }

    private String getConfigParamValue(String param) {
        return getConfigParamValue(this.section,param);
    }

    private String getConfigParamValue(String section, String param) {
        return this.envProperty.getConfigPropertyValue(section, param);
    }

    private File getFile(String path) {
        return new File(path);
    }
    public ValidatableResponse getResponse()
    {
        return this.response;
    }
}
