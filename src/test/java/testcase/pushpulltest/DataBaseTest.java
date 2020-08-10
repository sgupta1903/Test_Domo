package testcase.pushpulltest;

import helper.DataBaseHandler;
import org.testng.annotations.Test;
import ui.AbstractAutoUITest;

public class DataBaseTest extends AbstractAutoUITest {

    @Test
    public void dbCountVerify() {
        System.out.println("Verifying DB Count........");

        DataBaseHandler dataBaseHandler = getDBClient("RegShiftDB");
        dataBaseHandler.verify_query_result("Select count(*) from fact.delinquent_snapshot", 7546);


    }

}
