package testcase.unoapptestcases.endtoendscenario.scenario.fieldvalidation;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;
import static helper.DataProviderSection.*;
import static util.UtilityGeneric.set_section_name;

/*
Created By: Sapna Batan
Date: 12/30/2019
*/

@Issue("A30TP-32580")
@Slf4j
public class CreateCurrentSubsWithoutAmenity_InvalidTest extends AbstractAutoUITest {

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(CURRENT_WITHOUT_PAYMENT_AMOUNT_OPENENDED_MONTHLY);
    }

    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    public void unoAppLogin() {
        log.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Create a new Organization")
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
                        ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP));
    }

    @Test
    @Description("Create a Location for the organization")
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
    @Description("Create a Catalog-ItemCategory")
    public void createItemCategory() {
        log.info("Entering the details for creating item category ");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Create Catalog-Item")
    public void createItem() {
        log.info("Entering the details of item ");
        setTestResultOnXRay("A30TP-8241");
        getPage(UnoAppItemPage.class)
                .generate_item_name(ITEM_NAME)
                .create_item(getTestData(UNOAPP_INI, ITEM_SECTION, ITEM_TYPE,
                        AUTO_PAY, ITEM_CATEGORY_NAME, ITEM_NAME));
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    public void createSubsWithOneMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-8274");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_PAYMENT_AMOUNT_OPENENDED_MONTHLY,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Enter subscription details without amenity")
    public void enterDetailsForSubscriptionWithoutAmenity() {
        log.info("Enter subscription details without amenity");
        setTestResultOnXRay("A30TP-32583");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_without_amenity(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_PAYMENT_AMOUNT_OPENENDED_MONTHLY,
                        SUB_TYPE, SUB_DUE_DATE, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT));
    }

    @Test
    @Description("Verify missing amenity error message")
    public void verifyMissingAmenityErrorMessage() {
        log.info("Verify missing amenity error message");
        setTestResultOnXRay("A30TP-32582");
        getPage(UnoAppSubscriptionPage.class)
                .verify_amenity_error();
    }

    @Test
    @Description("Verify disabled submit button")
    public void verifyDisabledSubmitButton() {
        log.info("Verify disabled submit button");
        setTestResultOnXRay("A30TP-32585");
        getPage(UnoAppSubscriptionPage.class)
                .verify_disabled_submit_btn()
                .click_cancel_subscription();
        getPage(UnoAppMemberDetailPage.class)
                .confirm_to_stop_payment();
    }

    @Test
    @Description("Logout from the Uno Application")
    public void unoAppLogout() {
        log.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }
}
