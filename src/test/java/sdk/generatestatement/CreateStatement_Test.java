package sdk.generatestatement;

import config.EnvProperty;
import helper.PropFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.util.Map;

public class CreateStatement_Test
{
    private static final Logger APPLICATION_LOGGER = LoggerFactory.getLogger( CreateStatement_Test.class );
    EnvProperty envProperty;
    String filepath = "src/test/resources/config.ini";
    Map<String, String> help = PropFileHelper.getSensorValue( filepath, "GENERATE_STATEMENT" );

    GenerateStatementTest generateStatementTest= new GenerateStatementTest();
    CreateGenerateStatementData createGenerateStatementData= new CreateGenerateStatementData();

    @Test(description = "Create General Statement")
    public void createGenStatement()
    {
        try
        {
            APPLICATION_LOGGER.info( "Create General Statement has started" );
            int statusCode = Integer.valueOf( help.get( "statusCode" ) );
            createGenerateStatementData.setLocationId( help.get( "locationId" ).toString() ); ;
            createGenerateStatementData.setAccountId( help.get("accountId").toString() );

            generateStatementTest.creatGeneralStatement( createGenerateStatementData,statusCode );
        }
        catch( Exception exception )
        {
            APPLICATION_LOGGER.info( "Getting Error while sending request to Server" );
            exception.printStackTrace();
        }
    }
}
