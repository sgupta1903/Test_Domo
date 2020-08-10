package sdk.fee;


import config.EnvProperty;
import helper.PropFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.Map;



public class FeeCreate
{
    private static final Logger APPLICATION_LOGGER = LoggerFactory.getLogger(FeeCreate.class);
    EnvProperty envProperty;
    String filepath="src/test/resources/config.ini";
    Map< String , String> help = PropFileHelper.getSensorValue( filepath,"FEE_DATA" );


    FeeTest feeTest= new FeeTest();
    CreateFeeData createFeeData= new CreateFeeData();
@Test( description = "Create Fee")
    public void createFeeAcc( ) {
        try
        {
            APPLICATION_LOGGER.info( "Create fee has started" );
            int statusCode=Integer.valueOf( help.get("statusCode") );
            createFeeData.setAccountId( help.get("accountId").toString()) ;
            createFeeData.setFeeMode( help.get("feeMode").toString() );
            createFeeData.setFeeType(help.get("feeType").toString());
            createFeeData.setFeeTransactionType( help.get("feeTransactionType").toString() );
            createFeeData.setFeeValueType( help.get("feeValueType").toString() );
            createFeeData.setFeeValue( BigDecimal.valueOf( Integer.valueOf( help.get("feeValue") ) ) );
            feeTest.createBank( createFeeData,statusCode );
        }
        catch( Exception exception )
        {
            APPLICATION_LOGGER.info("Getting Error while sending request to Server");
            exception.printStackTrace();
        }
    }

}
