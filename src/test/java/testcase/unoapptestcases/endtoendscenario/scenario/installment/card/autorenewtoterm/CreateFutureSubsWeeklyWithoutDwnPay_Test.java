package testcase.unoapptestcases.endtoendscenario.scenario.installment.card.autorenewtoterm;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import listeners.XrayListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pagetest.paymentgatewaypage.CompanyTabPage;
import pagetest.paymentgatewaypage.MerchantAccountPage;
import pagetest.paymentgatewaypage.PaymentGatewayLoginPage;
import pagetest.paymentgatewaypage.ProcessorPage;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;

import static helper.AppConstants.*;
import static helper.DataProviderSection.FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD;
import static util.UtilityGeneric.set_section_name;

/*
Created By: Sonam
Date: 08/22/2019
*/


@Issue("A30TP-7793")

public class CreateFutureSubsWeeklyWithoutDwnPay_Test extends AbstractAutoUITest {
    /*protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @BeforeClass
    public void setSectionData() {
        logger.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD);
    }

    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    public void unoAppLogin() {
        logger.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Create a new Organization")
    public void createOrganization() {
        logger.info("Entering the details to create a new organization ");
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
        logger.info("Creating the location for the organization and adding bank account details for location ");
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
    @Description("Sin In into the payment gateway.")
    public void loginPg() {
        logger.info("Sin In into payment gateway with valid credential.");
        setTestResultOnXRay("A30TP-12686");
        getPage(PaymentGatewayLoginPage.class)
                .login_pg();
    }

    @Test
    @Description("Selecting company for processor.")
    public void selectingCompany() {
        logger.info("Selecting the existing company.");
        setTestResultOnXRay("A30TP-12689");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES);
    }

    @Test
    @Description("Selecting the processor for company.")
    public void selectingProcessor() {
        logger.info("Selecting the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Configuring VANTIV processor for the selected location for Payment Gateway.")
    public void configuringMerchantForVantivProcessor() {
        setTestResultOnXRay("A30TP-20723");
        getPage(MerchantAccountPage.class)
                .create_merchant_vantiv();
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create mermber for billing.")
    public void logoutPg() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("Create a Catalog-ItemCategory")
    public void createItemCategory() {
        logger.info("Entering the details for creating item category ");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Create Catalog-Item")
    public void createItem() {
        logger.info("Entering the details of item ");
        setTestResultOnXRay("A30TP-8241");
        getPage(UnoAppItemPage.class)
                .generate_item_name(ITEM_NAME)
                .create_item(getTestData(UNOAPP_INI, ITEM_SECTION, ITEM_TYPE,
                        AUTO_PAY, ITEM_CATEGORY_NAME, ITEM_NAME));
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    public void createSubsWithOneMember() {
        logger.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-8440");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create auto renew to term (Weekly) Subscription 'Credit Card' when begin and expiration date is future.")
    public void enterSubscriptionScheduleInformation() {
        logger.info("Create auto renew to term (Weekly) Subscription 'Credit Card' when begin and expiration date is future.");
        setTestResultOnXRay("A30TP-9565");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_term();

    }

    @Test
    @Description("Enter card details for subscription creation")
    public void enterCardDetailsForSubscription() {
        logger.info("Entering the details of card account ");
        setTestResultOnXRay("A30TP-8418");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_credit_card(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,
                        SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME, SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verifying that subscription creation is completed")
    public void verifySubscriptionCreation() {
        logger.info("Verify that subscription is created successfully");
        setTestResultOnXRay("A30TP-9571");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_FIRST_NAME));
    }

    @Test
    @Description("Verifying subscription tab and its data displayed for first location subscription ")
    public void verifySubscriptionTab() {
        logger.info("Verify that subscription tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13574");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,SUB_TYPE));
    }

    @Test
    @Description("Verifying agreement tab and its data displayed for first location subscription")
    public void verifyAgreementTab() {
        logger.info("Verify that agreement tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13578");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_STATUS));

    }

    @Test
    @Description("Verifying wallet tab and its data displayed for first location subscription")
    public void verifyWalletTab() {
        logger.info("Verify that wallet tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13571");
        getPage(UnoAppMemberDetailPage.class)
                .click_wallet_tab()
                .verify_wallet_payment_method_card(getTestData(UNOAPP_INI,MEMBER_SECTION,PAYMENT_METHOD_CARD))
                .verify_wallet_card_account_holder_name(getTestData(SUBSCRIPTION_INI,FUTURE_WITHOUT_DWNPAY_AUTORENEWTOTERM_WEEKLY_CARD,SUB_CARD_HOLDER_NAME));
    }

    @Test
    @Description("Verifying profile tab and its data displayed for first location subscription")
    public void verifyProfileTab() {
        logger.info("Verify that profile tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13572");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added for first location subscription")
    public void verifyNotesTab() {
        logger.info("Verify that notes tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13577");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_blank(getTestData(UNOAPP_INI,MEMBER_SECTION,NOTES_INFO));
    }

    @Test
    @Description("Logout from the Uno Application")
    public void unoAppLogout() {
        logger.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }*/
}