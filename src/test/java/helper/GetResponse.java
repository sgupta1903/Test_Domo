package helper;

import com.jayway.restassured.response.ValidatableResponse;
//import com.sun.deploy.association.utility.AppConstants;
import config.EnvProperty;

public class GetResponse
{

    private EnvProperty envProperty = EnvProperty.getInstance( AppConstants.CONFIG_INI );
    private RestHandler restHandler = new RestHandler( envProperty, "REST_FEE" );
    private EnvProperty envProperty1=EnvProperty.getInstance( AppConstants.AUTHORIZATION_INI );
    private ValidatableResponse response;
    private String header = envProperty1.getConfigPropertyValue( "AUTHORIZATION", "AccessTokenUser" );

    // Post Response
    public ValidatableResponse postResponse( String methodData, String url, int statusCode )
    {

        restHandler
            .add_header( "Authorization",header )
            .add_header( "Content-Type", "application/json" )
            .add_body( methodData )
            .verify_status( statusCode )
            .POST( url );
        response = restHandler.getResponse();
        return response;

    }

    //Get Response
    public ValidatableResponse getResponse( String methodData, String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .add_body( methodData )
            .verify_status( statusCode )
            .GET( url );
        response = restHandler.getResponse();
        return response;

    }

    //Get Response
    public ValidatableResponse getResponse(String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .verify_status( statusCode )
            .GET( url );
        response = restHandler.getResponse();
        return response;

    }

    //Delete Response
    public ValidatableResponse deleteResponse( String methodData, String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .add_body( methodData )
            .verify_status( statusCode )
            .DELETE( url );
        response = restHandler.getResponse();
        return response;

    }
    //Delete Response
    public ValidatableResponse deleteResponse( String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .verify_status( statusCode )
            .DELETE( url );
        response = restHandler.getResponse();
        return response;

    }

    //Put Response
    public ValidatableResponse putResponse( String methodData, String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .add_body( methodData )
            .verify_status( statusCode )
            .PUT( url );
        response = restHandler.getResponse();
        return response;

    }

    //Put Response
    public ValidatableResponse putResponse( String url, int statusCode )
    {
        restHandler
            .add_header( "Authorization", header )
            .add_header( "Content-Type", "application/json" )
            .verify_status( statusCode )
            .PUT( url );
        response = restHandler.getResponse();
        return response;

    }

}
