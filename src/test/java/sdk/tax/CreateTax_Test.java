package sdk.tax;

import config.EnvProperty;
import helper.PropFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import sdk.fee.FeeCreate;

import java.math.BigDecimal;

import sdk.fee.FeeTest;

import java.math.BigDecimal;
import java.util.Map;

public class CreateTax_Test
{
    private static final Logger APPLICATION_LOGGER = LoggerFactory.getLogger( FeeCreate.class );
    EnvProperty envProperty;
    String filepath = "src/test/resources/config.ini";
    Map<String, String> help = PropFileHelper.getSensorValue( filepath, "TAX_DATA" );

    // private AuthService authService = new AuthService();
    //private String header = "Bearer".concat(authService.getAccessToken());

    TaxTest taxTest = new TaxTest();
    CreateTaxData createTaxData = new CreateTaxData();

    @Test( description = "Create Location Tax" )
    public void createLocationTax()
    {
        try
        {
            APPLICATION_LOGGER.info( "Create tax has started" );
            int statusCode = Integer.valueOf( help.get( "statusCode" ) );
            createTaxData.setLocationId( help.get( "locationId" ).toString() ); ;
            createTaxData.setTaxCode( help.get( "taxCode" ).toString() );
            createTaxData.setIsOverriden( help.get( "isOverriden" ).toString() );
            //createTaxData.setSuggestedTaxRate( String.valueOf(BigDecimal.valueOf(Integer.valueOf( help.get("suggestedTaxRate"))) ) );
            createTaxData.setSuggestedTaxRate( String.valueOf( BigDecimal.valueOf( Integer.valueOf( help.get( "suggestedTaxRate" ) ) ) ) );
            createTaxData.setItemCategoryId( help.get( "itemCategoryId" ).toString() );
            createTaxData.setEmpId( help.get("empId").toString() );

            taxTest.createtax( createTaxData, statusCode );
        }
        catch( Exception exception )
        {
            APPLICATION_LOGGER.info( "Getting Error while sending request to Server" );
            exception.printStackTrace();
        }
    }

}
