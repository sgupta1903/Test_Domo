package testcase.datamigrationtest.filereadertest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.datamigrationpage.DataStoragePage;
import ui.AbstractAutoUITest;

import static helper.AppConstants.*;

@Slf4j
public class StoreClubCashChargeDataTest extends AbstractAutoUITest {

    @Test(description = "Storing club charge data from text file to ini according to club number")

    public void storeClubChargeData() {
        log.info("Storing club charge data in ini file");
        getPageWithoutUI(DataStoragePage.class)
                .clear_ini_file(CASH_CHARGE_INI)
                .read_store_text_file_to_ini(CASH_CHARGE_TEXT_FILE, CASH_CHARGE_INI,
                        getPageWithoutUI(DataStoragePage.class).getAllTestCaseIds(CASH_CHARGE_TEST_CASE1 ,CASH_CHARGE_TEST_CASE2));
    }

    @Test
    public void verifyFileExist() {
        getPageWithoutUI(DataStoragePage.class)
                .check_file_exist("config.ini" , 1); //provide time frame in minutes

    }


}