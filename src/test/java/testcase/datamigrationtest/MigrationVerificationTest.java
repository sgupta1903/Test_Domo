package testcase.datamigrationtest;

import config.EnvProperty;
import helper.AppConstants;
import helper.DataBaseHandler;
import helper.ServicePropertyFileReader;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagetest.datamigrationpage.ABCRatePage;
import ui.AbstractAutoUITest;

import java.io.IOException;

import static helper.AppConstants.*;

@Slf4j
public class MigrationVerificationTest extends AbstractAutoUITest {

    Ini ini = new Wini(this.getClass().getResourceAsStream('/'+ ServicePropertyFileReader.getInstance("env").getPropertyValue("env")+'/' + "property.ini"));
    EnvProperty env = new EnvProperty(ini);
    public MigrationVerificationTest() throws IOException {
    }


    @Test(dataProvider = "migration-sql-provider")
    @Issue("A30TP-41334")
    public void migrationDataSetFailedTest(String queryString) {
        log.info("Testing running for " + queryString + "");
        DataBaseHandler dataBaseHandler = getDBClient("MIGRATION_REDSHIFT");
        dataBaseHandler.verify_query_result(queryString, 0);
    }


    @DataProvider(name = "migration-sql-provider")
    public Object[][] dataProviderMethod()  {
        return new Object[][]{
                { "SELECT count(*) from central.subscription where status <> 201" },
                { "SELECT count(*) from central.agreement where status <> 201" },
                { "SELECT count(*) from central.cancel_action_code where status <> 410" },
                { "SELECT count(*) from central.cancel_subscription where status <> 200" },
                { "SELECT count(*) from central.client_configure_fee where status <> 201" },
                { "SELECT count(*) from central.client_payment_method where status <> 201" },
                { "SELECT count(*) from central.employee where status <> 201" },
                { "SELECT count(*) from central.freeze_subscription where status <> 201" },
                { "SELECT count(*) from central.item where status <> 201" },
                { "SELECT count(*) from central.item_category where status <> 201" },
                { "SELECT count(*) from central.location where status <> 201" },
                { "SELECT count(*) from central.location_action_code where status <> 410" },
                { "SELECT count(*) from central.location_note where status <> 201" },
                { "SELECT count(*) from central.member_action_code where status <> 410" },
                { "SELECT count(*) from central.member_configure_fee where status <> 201" },
                { "SELECT count(*) from central.member_info where status <> 201" },
                { "SELECT count(*) from central.member_note where status <> 201" },
                { "SELECT count(*) from central.membership_type where status <> 201" },
                { "SELECT count(*) from central.organization where status <> 201" },
                { "SELECT count(*) from central.reason_code where status <> 200" },
                { "SELECT count(*) from central.scheduled_fee_config where status <> 201" },
                { "SELECT count(*) from central.subscription_item where status <> 201" }
        };
    }

}
