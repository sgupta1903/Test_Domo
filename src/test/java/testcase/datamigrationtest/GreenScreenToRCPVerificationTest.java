package testcase.datamigrationtest;

import config.EnvProperty;
import data.DataProviderClass;
import helper.AppConstants;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.datamigrationpage.*;
import pagetest.obcUIPage.CashStoragePage;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;
import util.UtilityGeneric;

import static helper.AppConstants.ERROR_MESSAGE;
import static helper.AppConstants.TEST_STATUS;


@Slf4j
public class GreenScreenToRCPVerificationTest extends AbstractAutoUITest {
    EnvProperty env = EnvProperty.getInstance(AppConstants.OUTPUT_INI);

    @BeforeClass
    public void verifyFileExist() {
        UtilityGeneric.check_file_existence_in_resources(AppConstants.AUTO_JOB_FINISH_NOTIFY, 5);
        UtilityGeneric.check_file_existence_in_resources(AppConstants.GREEN_SCREEN_INI, 5);
        env.clearIniFile();
        env.loadFile(AppConstants.GREEN_SCREEN_INI);
    }


    @Test(description = "Login 'UNO' Application")
    @TmsLink("A30TP-13920")
    public void loginUnoApplication() {
        log.info("Login 'UNO' Application");
        setTestResultOnXRay("A30TP-13920");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test(description = "Verifying cash storage", dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-45133")
    public void verifyCashStorageDetails(String clubNumber) {

        log.info("Verifying cash storage details");
        setTestResultOnXRay("A30TP-45133");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen  " + clubNumber + "  Error is :" + env.getConfigPropertyValue(clubNumber, ERROR_MESSAGE));
        } else {
            getPage(CashStoragePage.class)
                    .verify_cash_storage_details(clubNumber);
        }
    }


    @Test(description = "Verifying cash storage data", dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-10834")
    public void verifyClientProfileDetails(String clubNumber) {

        log.info("Verifying Client Profile details");
        setTestResultOnXRay("A30TP-10834");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen  " + clubNumber + "  Error is :" + env.getConfigPropertyValue(clubNumber, ERROR_MESSAGE));
        } else {
            getPage(SearchClientProfilePage.class)
                    .verify_banking_details(clubNumber);
        }

    }

    @Test(description = "Verifying Payor Profile details",dataProvider = "migration-data-with_member_number", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-11887")
    public void verifyPayorProfileDetails(String clubNumber) {
        log.info("Verifying Payor Profile details");
        setTestResultOnXRay("A30TP-11887");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen  " + clubNumber + "  Error is :" + env.getConfigPropertyValue(clubNumber, ERROR_MESSAGE));
        } else {
            getPage(MemberAccountPage.class)
                    .verify_payor_profile_from_green_screen(clubNumber);
        }
    }
    @Test(description = "Verifying ABC Rates", dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-28338")
    public void verifyABCRate(String clubNumber) {
        log.info("Verifying ABC Rates has started ...");
        setTestResultOnXRay("A30TP-28338");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen" + clubNumber);
        }
        else
        {
            getPage(ABCRatePage.class)
                    .verify_abc_rate_details(clubNumber);
        }

    }

    @Test(description = "Verifying Item And Item Category details",dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-10722")
    public void verifyItemAndItemCategoryDetails(String clubNumber) {
        log.info("Verifying Item And Item Category details");
        setTestResultOnXRay("A30TP-10722");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen  " + clubNumber + "  Error is :" + env.getConfigPropertyValue(clubNumber, ERROR_MESSAGE));
        } else {
            getPage(CatalogPage.class)
                    .verify_categories_details(clubNumber)
                    .verify_catalog_item_details(clubNumber);
        }
    }
    @Test(description = "Verifying Deduction and Reimbursement", dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-18556")
    public void verifyDeductionAndReimbursement(String clubNumber) {
        log.info("Verifying Deduction and Reimbursement has started ...");
        setTestResultOnXRay("A30TP-18556");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen" + clubNumber);
        } else {
            getPage(LocationPaymentPage.class)
                    .verify_payment_details(clubNumber);
        }
    }

    @Test(description = "Verifying Item Fee details",dataProvider = "migration-cash-storage", dataProviderClass = DataProviderClass.class)
    @TmsLink("A30TP-11880")
    public void verifyItemFeeDetails(String clubNumber) {
        log.info("Verifying Item Fee details");
        setTestResultOnXRay("A30TP-11880");
        if (env.getConfigPropertyValue(clubNumber, TEST_STATUS).equalsIgnoreCase("Fail")) {
            throw new SkipException("Test Case has been Failed for fetching Data from Green Screen  " + clubNumber + "  Error is :" + env.getConfigPropertyValue(clubNumber, ERROR_MESSAGE));
        } else {
            getPage(CatalogPage.class)
                    .verify_item_fee_details(clubNumber);
        }
    }
    @Test(description = "Logout from Uno Application")
    @TmsLink("A30TP-8219")
    public void unoAppLogout() {
        log.info("Logout from Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout();
    }
}