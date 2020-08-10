package helper;

import config.EnvProperty;

import java.io.FileInputStream;
import java.util.Properties;

public class GetUrl
{
    private String url;
    private String api;
    private EnvProperty envProperty;
    private Properties properties;
    private FileInputStream in;


    public String getUrl()
    {
        try
        {
            envProperty = EnvProperty.getInstance(AppConstants.CONFIG_INI );
            properties = new Properties();
            String accountId=envProperty.getConfigPropertyValue( "FEE_DATA","accountId" );
            in = new FileInputStream( "src/test/resources/env.properties" );
            properties.load( in );
            url = properties.getProperty( "env" );
        }
        catch( Exception exception )
        {
            exception.printStackTrace();
        }
        if( "DEV".equals( url ) )
        {
            url = envProperty.getConfigPropertyValue( "REST_FEE", "DEV_URL" );
        }
        else if( "QA".equals( url ) )
        {
            url = envProperty.getConfigPropertyValue( "REST_FEE", "QA_URL" );
        }
        return url;
    }

    public String   createFeeUrl()
    {
        url = getUrl();
        api = url + "/configure-fee";
        return api;

    }
    public String getFeeUrl()
    {
        url=getUrl();
        api=url+"/fee/account/accountId";
        return api;
    }
    public String createTaxUrl()
    {
        url=getUrl();
        api = url + "/tax-rate";
        return api;
    }
    public String createGenerateStatementUrl()
    {
        url=getUrl();
        api = url + "/statement";
        return api;
    }
}
