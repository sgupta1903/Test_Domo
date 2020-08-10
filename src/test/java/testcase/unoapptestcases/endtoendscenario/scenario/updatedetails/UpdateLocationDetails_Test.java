package testcase.unoapptestcases.endtoendscenario.scenario.updatedetails;

/*
Created By: Shilpi Gupta
Date: 11/13/2019
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppLocationPage;
import pagetest.unoapppage.UnoAppLocationProfileTabPage;
import pagetest.unoapppage.UnoAppLoginPage;
import pagetest.unoapppage.UnoAppOrganizationPage;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;

@Issue("A30TP-14533")
@Slf4j
public class UpdateLocationDetails_Test extends AbstractAutoUITest {
    
    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    @TmsLink("A30TP-8217")
    public void unoAppLogin() {
        log.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Create a new Organization")
    @TmsLink("A30TP-8221")
    public void createOrganization() {
        log.info("Entering the details to create a new organization ");
        setTestResultOnXRay("A30TP-8221");
        getPage(UnoAppOrganizationPage.class)
                .generate_org_name(ORGANIZATION_NAME)
                .generate_org_url(ORGANIZATION_URL)
                .generate_org_email(ORGANIZATION_EMAIL)
                .create_organization(getTestData(UNOAPP_INI, ORGANIZATION_SECTION, ORGANIZATION_PHONE_NUMBER,
                        ORGANIZATION_NAME, ORGANIZATION_URL, ORGANIZATION_FIRST_NAME, ORGANIZATION_LAST_NAME,
                        ORGANIZATION_EMAIL, ORGANIZATION_ADD1, ORGANIZATION_ADD2,
                        ORGANIZATION_CITY, ORGANIZATION_STATE, ORGANIZATION_EMAIL,
                        ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP))
                .verify_organization_by_name();
    }

    @Test
    @Description("Create a Location for the organization")
    @TmsLink("A30TP-8212")
    public void createLocation() {
        log.info("Creating the location for the organization and adding bank account details for location ");
        setTestResultOnXRay("A30TP-8212");
        getPage(UnoAppLocationPage.class)
                .generate_location_name(LOCATION_NAME)
                .create_location(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME,
                        LOCATION_PHONE_NUMBER, LOCATION_EMAIL, LOCATION_ADD1,
                        LOCATION_ADD2, LOCATION_CITY, LOCATION_ZIP, LOCATION_STATE,
                        LOCATION_TIMEZONE, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER))
                .add_location_bank_details(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER));
    }

    @Test
    @Description("Selecting location profile tab")
    @TmsLink("A30TP-22041")
    public void selectLocationProfileTab() {
        log.info("selecting location profile tab");
        setTestResultOnXRay("A30TP-22041");
        getPage(UnoAppLocationProfileTabPage.class)
                .select_location_profile_tab()
                .select_edit_icon();
    }

    @Test
    @Description("Update email and location name")
    @TmsLink("A30TP-22044")
    public void updateLocationNameAndEmail() {
        log.info("Updating email and location name");
        setTestResultOnXRay("A30TP-22044");
        store_test_data("updatedEmail", "noreplyupdated@qa4life.com");
        getPage(UnoAppLocationProfileTabPage.class)
                .update_location_name_email(retrieve_test_data("updatedEmail"));

    }

    @Test
    @Description("Update phone number")
    @TmsLink("A30TP-22046")
    public void UpdateLocationPhoneNumber() {
        log.info("Updating phone number");
        setTestResultOnXRay("A30TP-22046");
        store_test_data("updatedPhoneNumber", "787-878-9123");
        getPage(UnoAppLocationProfileTabPage.class)
                .update_location_phone_number(retrieve_test_data("updatedPhoneNumber"));
    }

    @Test
    @Description("Save the changes ")
    @TmsLink("A30TP-22051")
    public void saveDetails() {
        log.info("Clicking on save button");
        setTestResultOnXRay("A30TP-22051");
        getPage(UnoAppLocationProfileTabPage.class)
                .clickSaveButton();
    }

    @Test
    @Description("Verify location name after updating")
    @TmsLink("A30TP-22054")
    public void verifyLocationNameUpdated() {
        log.info("Verifying location name");
        setTestResultOnXRay("A30TP-22054");
        getPage(UnoAppLocationProfileTabPage.class)
                .verify_loc_name();
    }

    @Test
    @Description("Verify location phone number after updating")
    @TmsLink("A30TP-22055")
    public void verifyLocationPhoneNumberUpdated() {
        log.info("Verifying location phone number");
        setTestResultOnXRay("A30TP-22055");
        getPage(UnoAppLocationProfileTabPage.class)
                .verify_loc_phone_number(retrieve_test_data("updatedPhoneNumber"));
    }

    @Test
    @Description("Verify location email after updating")
    @TmsLink("A30TP-22056")
    public void verifyLocationEmailUpdated() {
        log.info("Verifying location email");
        setTestResultOnXRay("A30TP-22056");
        getPage(UnoAppLocationProfileTabPage.class)
                .verify_loc_email(retrieve_test_data("updatedEmail"));
    }

    @Test
    @Description("Logout from the Uno Application")
    @TmsLink("A30TP-8219")
    public void unoAppLogout() {
        log.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }
}
