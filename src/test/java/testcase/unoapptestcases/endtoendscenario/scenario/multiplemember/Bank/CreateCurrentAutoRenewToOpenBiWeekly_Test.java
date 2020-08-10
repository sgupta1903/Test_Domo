package testcase.unoapptestcases.endtoendscenario.scenario.multiplemember.Bank;
/*Created By : Sapna Batan
Date : 12/05/2019*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.paymentgatewaypage.CompanyTabPage;
import pagetest.paymentgatewaypage.MerchantAccountPage;
import pagetest.paymentgatewaypage.PaymentGatewayLoginPage;
import pagetest.paymentgatewaypage.ProcessorPage;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;
import static helper.DataProviderSection.*;
import static util.UtilityGeneric.set_section_name;

@Issue("A30TP-30563")
@Slf4j
public class CreateCurrentAutoRenewToOpenBiWeekly_Test extends AbstractAutoUITest {
    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay("A30TP-14514");
        set_section_name(CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK);
    }

    @Test
    @Description("Login to Uno App")
    @TmsLink("A30TP-8217")
    public void unoAppLogin() {
        log.info("Login into the Uno Application");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Creating Organization ")
    @TmsLink("A30TP-8221")
    public void createOrganization() {
        log.info("Creating an organization with valid data.");
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
    @Description("Creating Location for organization.")
    @TmsLink("A30TP-8212")
    public void createLocation() {
        log.info("Creating location for organization with valid data.");
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
    @Description("Sign In into the payment gateway.")
    @TmsLink("A30TP-12686")
    public void loginPg() {
        log.info("Sign-in into payment gateway with valid credential.");
        setTestResultOnXRay("A30TP-12686");
        getPage(PaymentGatewayLoginPage.class)
                .login_pg();
    }

    @Test
    @Description("Selecting company for processor.")
    @TmsLink("A30TP-12689")
    public void selectCompany() {
        log.info("Selecting the existing company.");
        setTestResultOnXRay("A30TP-12689");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES);
    }

    @Test
    @Description("Displaying the processor for company.")
    @TmsLink("A30TP-12691")
    public void displayProcessor() {
        log.info("Displaying the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Creating EFT processor configuration for the selected location for Payment Gateway.")
    @TmsLink("A30TP-20723")
    public void creatingMerchantEftProcessor() {
        setTestResultOnXRay("A30TP-20723");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create member for billing.")
    @TmsLink("A30TP-12693")
    public void logoutPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("Creating Item category.")
    @TmsLink("A30TP-8229")
    public void createItemCategory() {
        log.info("Creating Item Category for location with valid data.");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Creating Catalog item.")
    @TmsLink("A30TP-8241")
    public void createItem() {
        log.info("Creating item for Item catalog with valid data.");
        setTestResultOnXRay("A30TP-8241");
        getPage(UnoAppItemPage.class)
                .generate_item_name(ITEM_NAME)
                .create_item(getTestData(UNOAPP_INI, ITEM_SECTION, ITEM_TYPE,
                        AUTO_PAY, ITEM_CATEGORY_NAME, ITEM_NAME));
    }


    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    @TmsLink("A30TP-17872")
    public void addPrimaryMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-17872");
        getPage(UnoAppMemberPage.class)
                .navigate_and_click_add_member_btn()
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .enter_member_details(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    @TmsLink("A30TP-10652")
    public void addSecondaryMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-10652");
        getPage(UnoAppMemberPage.class)
                .click_add_member_button()
                .verify_secondary_member_section_displayed()
                .enter_secondary_member_details()
                .click_continue_button()
                .create_payor();
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    @TmsLink("A30TP-12221")
    public void enterScheduleInformation() {
        log.info("Entering the details of credit account ");
        setTestResultOnXRay("A30TP-12221");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,
                        SUB_DOWNPAYMENT))
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,
                        SUB_EXPIRY_DATE, SUB_DURATION));
    }

    @Test
    @Description("Create auto renew to term subscription begin date current date and frequency Bi Weekly with bank account.")
    @TmsLink("A30TP-12226")
    public void enterAutoRenewToTermDetails() {
        log.info("Entering the details related to Subscription schedule");
        setTestResultOnXRay("A30TP-12226");
        getPage(UnoAppSubscriptionPage.class)
                .enter_renewal_auto_to_open(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    @TmsLink("A30TP-18300")
    public void enterDetailsForSubscription() {
        log.info("Entering the details of credit account ");
        setTestResultOnXRay("A30TP-18300");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();
    }

    @Test
    @Description("Create auto renew to term subscription begin date current date and frequency Monthly with bank account.")
    @TmsLink("A30TP-18304")
    public void verifySubscriptionCreated() {
        log.info("Entering the details related to Subscription schedule");
        setTestResultOnXRay("A30TP-18304");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_FIRST_NAME));
    }

    @Test
    @Description("Verifying subscription tab and its data displayed")
    @TmsLink("A30TP-13574")
    public void verifySubscriptionTab() {
        log.info("Verify that subscription tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13574");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,SUB_TYPE));
    }

    @Test
    @Description("Verifying agreement tab and its data displayed")
    @TmsLink("A30TP-13578")
    public void verifyAgreementTab() {
        log.info("Verify that agreement tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13578");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_STATUS));
    }

    @Test
    @Description("Verifying wallet tab and its data displayed")
    @TmsLink("A30TP-13571")
    public void verifyWalletTab() {
        log.info("Verify that wallet tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13571");
        getPage(UnoAppMemberDetailPage.class)
                .click_payments_tab()
                .verify_wallet_payment_method_bank(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,PAYMENT_METHOD_BANK))
                .verify_wallet_bank_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,SUB_ACCOUNT_HOLDER_NAME));

    }

    /*
User will run the test for invoice generation when scheduler will run successfully

@Test
@Description( "Verifying invoice generation in the wallet tab" )
public void verifyInvoiceGeneration()
{
  log.info( "Verify that wallet tab for subscription invoice and statement generation" );
  setTestResultOnXRay( "A30TP-17394" );
  getPage( UnoAppMemberDetailPage.class )
          .select_wallet_tab()
          .verify_invoice_generated();
}*/

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
    @Description("Verifying Notes tab and it should be blank when no notes are added")
    @TmsLink("A30TP-17895")
    public void verifyNotesTab() {
        log.info("Verify that notes tab for subscription created for a member");
        setTestResultOnXRay("A30TP-17895");
        getPage(UnoAppNotesTabPage.class)
                .select_notes_tab()
                .verify_primary_member_notes(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_MEMBER_AUTORENEWTOOPEN_BIWEEKLY_BANK,MEMBER_NOTES));

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