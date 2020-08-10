package testcase.unoapptestcases.endtoendscenario.scenario.updatedetails;


import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;
import static helper.DataProviderSection.*;
import static helper.DataProviderSection.CURRENT_WITH_DWNPAY_OPENEND_ANNUALLY_CARD;
import static helper.DataProviderSection.PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK;
import static util.UtilityGeneric.set_section_name;

/*
Created By : Sapna Batan
Date :01/14/2020
*/

@Issue("A30TP-32366")
@Slf4j
public class UpdateMemberDetails_Test extends AbstractAutoUITest {

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(CURRENT_WITH_DWNPAY_OPENEND_ANNUALLY_CARD);
    }

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
                        ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP));
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
    @Description("Create a Catalog-ItemCategory")
    @TmsLink("A30TP-8229")
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
    @TmsLink("A30TP-8241")
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
    @TmsLink("A30TP-8451")
    public void createSubsWithOneMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-8451");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENEND_ANNUALLY_CARD,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create 'Open-End' Subscription with Payment Method Type as 'Credit Card' with begin date current when frequency is annually with down payment")
    @TmsLink("A30TP-12374")
    public void enterDetailsForSubscription() {
        log.info("Create 'Open-End' Subscription with Payment Method Type as 'Credit Card' with begin date current when frequency is annually with down payment");
        setTestResultOnXRay("A30TP-12374");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENEND_ANNUALLY_CARD,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_payment_information_credit_card(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENEND_ANNUALLY_CARD,
                        SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME, SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verifying that subscription creation is completed")
    @TmsLink("A30TP-12375")
    public void verifySubscriptionCreation() {
        log.info("Verify that subscription is created successfully");
        setTestResultOnXRay("A30TP-12375");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_FIRST_NAME));

    }
    @Test
    @Description("Verifying profile tab and its data displayed")
    @TmsLink("A30TP-13572")
    public void verifyProfileTab() {
        log.info("Verify that profile tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13572");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Click update member button")
    @TmsLink("A30TP-33719")
    public void selectMemberProfileTab() {
        log.info("Click update member button");
        setTestResultOnXRay("A30TP-33719");
        getPage(UnoAppMemberPage.class)
                .click_update_member();
    }

    @Test
    @Description("Update member firstname and lastname")
    @TmsLink("A30TP-33722")
    public void updateMemberName() {
        log.info("Updating member firstname and lastname");
        setTestResultOnXRay("A30TP-33722");
        store_test_data("updatedFirstName", "updatedFirstName");
        store_test_data("updatedLastName", "updatedLastName");
        getPage(UnoAppMemberPage.class)
                .enter_first_name(retrieve_test_data("updatedFirstName"));
        getPage(UnoAppMemberPage.class)
                .enter_last_name(retrieve_test_data("updatedLastName"));

    }

    @Test
    @Description("Update member address")
    @TmsLink("A30TP-33802")
    public void updateAddress() {
        log.info("Updating member address");
        setTestResultOnXRay("A30TP-33802");
        store_test_data("updatedAddress", "Updated Address Test");
        getPage(UnoAppMemberPage.class)
                .enter_member_add(retrieve_test_data("updatedAddress"));
    }

    @Test
    @Description("Update member city")
    @TmsLink("A30TP-33728")
    public void updateCity() {
        log.info("Updating member city");
        setTestResultOnXRay("A30TP-33728");
        store_test_data("updatedCity", "Updated City Test");
        getPage(UnoAppMemberPage.class)
                .enter_mem_city(retrieve_test_data("updatedCity"));
    }

    @Test
    @Description("Update member zip")
    @TmsLink("A30TP-33801")
    public void updateZipCode() {
        log.info("Updating member zip");
        setTestResultOnXRay("A30TP-33801");
        store_test_data("updatedZip", "34786-7865");
        getPage(UnoAppMemberPage.class)
                .enter_mem_zip(retrieve_test_data("updatedZip"));
    }

    @Test
    @Description("Save updated details")
    @TmsLink("A30TP-33725")
    public void clickSubmitButton() {
        log.info("Save updated details");
        setTestResultOnXRay("A30TP-33725");
        getPage(UnoAppMemberPage.class)
                .click_submit_update();
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