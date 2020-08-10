package testcase.unoapptestcases.endtoendscenario.scenario.updatedetails;

/*
Created By: Sapna Batan
Updated By : Shilpi Gupta
Date: 12/27/2019
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppLoginPage;
import pagetest.unoapppage.UnoAppOrganizationPage;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;

@Issue("A30TP-32441")
@Slf4j
public class UpdateOrganizationDetails_Test extends AbstractAutoUITest {
    
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
    @Description("Selecting Organization update icon")
    @TmsLink("A30TP-32482")
    public void selectOrganizationUpdateIcon() {
        log.info("Selecting Organization update icon");
        setTestResultOnXRay("A30TP-32482");
        getPage(UnoAppOrganizationPage.class)
                .select_edit_icon();
    }

    @Test
    @Description("Update email and location name")
    @TmsLink("A30TP-32479")
    public void updateOrganizationNameAndEmail() {
        log.info("Updating email and location name");
        setTestResultOnXRay("A30TP-32479");
        getPage(UnoAppOrganizationPage.class)
                .generate_org_email(ORGANIZATION_EMAIL)
                .generate_org_name(ORGANIZATION_NAME)
                .update_org_name_email(getTestData(UNOAPP_INI, ORGANIZATION_SECTION,ORGANIZATION_EMAIL,ORGANIZATION_NAME ));

    }

    @Test
    @Description("Update phone number")
    @TmsLink("A30TP-32478")
    public void UpdateOrganizationPhoneNumber() {
        log.info("Updating phone number");
        setTestResultOnXRay("A30TP-32478");
        store_test_data("updatedPhoneNumber", "787-878-9123");
        getPage(UnoAppOrganizationPage.class)
                .enter_org_phone(retrieve_test_data("updatedPhoneNumber"));
    }

    @Test
    @Description("Save the changes ")
    @TmsLink("A30TP-32481")
    public void saveDetails() {
        log.info("Clicking on save button");
        setTestResultOnXRay("A30TP-32481");
        getPage(UnoAppOrganizationPage.class)
                .org_save_btn();
    }

    @Test
    @Description("Verify Organization name after updating")
    @TmsLink("A30TP-32477")
    public void verifyOrganizationNameUpdated() {
        log.info("Verifying Organization name");
        setTestResultOnXRay("A30TP-32477");
        getPage(UnoAppOrganizationPage.class)
                .verify_organization_by_name();
    }

    @Test
    @Description("Verify organization phone number after updating")
    @TmsLink("A30TP-32475")
    public void verifyOrganizationPhoneNumberUpdated() {
        log.info("Verifying location phone number");
        setTestResultOnXRay("A30TP-32475");
        getPage(UnoAppOrganizationPage.class)
                .verify_org_phone_number(retrieve_test_data("updatedPhoneNumber"));
    }

    @Test
    @Description("Verify organization email after updating")
    @TmsLink("A30TP-32476")
    public void verifyOrganizationEmailUpdated() {
        log.info("Verifying organization email");
        setTestResultOnXRay("A30TP-32476");
        getPage(UnoAppOrganizationPage.class)
                .verify_org_email(getTestData(UNOAPP_INI, ORGANIZATION_SECTION,ORGANIZATION_EMAIL));
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
