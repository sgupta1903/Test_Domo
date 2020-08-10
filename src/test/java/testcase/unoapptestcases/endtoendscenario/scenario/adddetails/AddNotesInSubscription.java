package testcase.unoapptestcases.endtoendscenario.scenario.adddetails;
/*
Created By: Shilpi Gupta
Date: 02/04/2020
*/

import config.EnvProperty;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppLocationPage;
import pagetest.unoapppage.UnoAppLoginPage;
import pagetest.unoapppage.UnoAppOrganizationPage;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;
import static helper.DataProviderSection.PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK;
import static util.UtilityGeneric.set_section_name;


@Issue("A30TP-14542")
@Slf4j
public class AddNotesInSubscription extends AbstractAutoUITest {
    EnvProperty env = EnvProperty.getInstance(UNOAPP_INI);

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK);
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

   /* @Test
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
                        ITEM_RATE, ITEM_CATEGORY_NAME, ITEM_NAME));
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    public void createSubsWithOneMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-8440");
        getPage(UnoAppMemberPage.class)
                .create_member_test(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment_Test(getTestData(SUBSCRIPTION_INI, PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Enter details in subscritpion page related to type,frequency and downpayment")
    public void enterSubscriptionScheduleInformation() {
        log.info("Entering the details related to Subscription schedule");
        setTestResultOnXRay("A30TP-32335");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_test(getTestData(SUBSCRIPTION_INI, PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                   .enter_subscription_schedule_information_installment_test(getTestData(SUBSCRIPTION_INI, PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_open_test(getTestData(SUBSCRIPTION_INI, PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    public void enterDetailsForSubscription() {
        log.info("Entering the details of bank account ");
        setTestResultOnXRay("A30TP-32336");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_bank_account_test(getTestData(SUBSCRIPTION_INI, PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();

    }

    @Test
    @Description("Verifying that subscription creation is completed")
    public void verifySubscriptionCreation() {
        log.info("Verify that subscription is created successfully");
        setTestResultOnXRay("A30TP-8415");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI , MEMBER_SECTION,MEMBER_FIRST_NAME ));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added")
    public void verifyNotesTab() {
        log.info("Verify that notes tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13577");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_blank();
    }

    @Test
    @Description("Add notes")
    public void addNotesForSubscription() {
        log.info("Adding notes for subscription");
        setTestResultOnXRay("A30TP-35401");
        store_test_data("codeName", "ARN");
        store_test_data("notesText", "Testing notes addition");
        getPage(UnoAppNotesTabPage.class)
                .select_notes_tab()
                .click_add_button()
                .enter_code(retrieve_test_data("codeName"))
                .enter_notes_text(retrieve_test_data("notesText"))
                .click_save_button();
    }

    @Test
    @Description("Verify the code and description in  notes")
    public void verifyNotesForSubscription() {
        log.info("Verify notes for subscription");
        setTestResultOnXRay("A30TP-35403");
        getPage(UnoAppNotesTabPage.class)
                .verify_notes_code(retrieve_test_data("codeName"))
                .verify_notes_description(retrieve_test_data("notesText"));
    }*/
}
