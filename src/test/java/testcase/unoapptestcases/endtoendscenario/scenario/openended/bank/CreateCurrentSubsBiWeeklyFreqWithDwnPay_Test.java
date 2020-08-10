package testcase.unoapptestcases.endtoendscenario.scenario.openended.bank;


/*
Created By: Shilpi Gupta
Date: 07/25/2019
*/

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


@Issue("A30TP-7874")
@Slf4j
public class CreateCurrentSubsBiWeeklyFreqWithDwnPay_Test extends AbstractAutoUITest {

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY);
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
    @Description("Creating Organization in UNO App")
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
    @Description("Creating Location for organization and adding bank account details in Location.")
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
    @Description("Creating Item category.")
    @TmsLink("30TP-8229")
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
    @Description("Selecting tax rate tab and click on add tax rate button.")
    @TmsLink("A30TP-17313")
    public void selectTaxRateTab() {
        log.info("Selecting tax rate tab and click on add tax rate button.");
        setTestResultOnXRay("A30TP-17313");
        getPage(UnoAppTaxRatePage.class)
                .click_tax_rate_tab()
                .click_on_add_tax();
    }

    @Test
    @Description("Adding location tax rate for ABC tax fee.")
    @TmsLink("A30TP-17317")
    public void addTaxRateAbc() {
        log.info("Adding location tax rate for ABC tax fee");
        setTestResultOnXRay("A30TP-17317");
        getPage(UnoAppTaxRatePage.class)
                .add_abc_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Reversal tax fee.")
    @TmsLink("A30TP-20941")
    public void addTaxReversalRateAbc() {
        log.info("Adding location tax rate for Reversal tax fee");
        setTestResultOnXRay("A30TP-20941");
        getPage(UnoAppTaxRatePage.class)
                .add_reversal_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for ACH tax fee")
    @TmsLink("A30TP-17319")
    public void addTaxRateAch() {
        log.info("Adding location tax rate for ACH tax fee");
        setTestResultOnXRay("A30TP-17319");
        getPage(UnoAppTaxRatePage.class)
                .add_ach_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Statement tax fee")
    @TmsLink("A30TP-17320")
    public void addTaxRateStatement() {
        log.info("Adding location tax rate for Statement tax fee");
        setTestResultOnXRay("A30TP-17320");
        getPage(UnoAppTaxRatePage.class)
                .add_statement_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for American Express tax fee")
    @TmsLink("A30TP-17321")
    public void addTaxRateAmericanExpress() {
        log.info("Adding location tax rate for American Express tax fee");
        setTestResultOnXRay("A30TP-17321");
        getPage(UnoAppTaxRatePage.class)
                .add_american_express_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Master Card tax fee")
    @TmsLink("A30TP-17322")
    public void addTaxRateMasterCard() {
        log.info("Adding location tax rate for Master Card tax fee");
        setTestResultOnXRay("A30TP-17322");
        getPage(UnoAppTaxRatePage.class)
                .add_master_card_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for VISA tax fee")
    @TmsLink("A30TP-17323")
    public void addTaxRateVisa() {
        log.info("Adding location tax rate for VISA tax fee");
        setTestResultOnXRay("A30TP-17323");
        getPage(UnoAppTaxRatePage.class)
                .add_visa_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Discover tax fee")
    @TmsLink("A30TP-17324")
    public void addTaxRateDiscover() {
        log.info("Adding location tax rate for Discover tax fee");
        setTestResultOnXRay("A30TP-17324");
        getPage(UnoAppTaxRatePage.class)
                .add_discover_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Payments tax fee")
    @TmsLink("A30TP-17325")
    public void addTaxRatePayments() {
        log.info("Adding location tax rate for Payments tax fee");
        setTestResultOnXRay("A30TP-17325");
        getPage(UnoAppTaxRatePage.class)
                .add_payments_add_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Club Fee tax fee")
    @TmsLink("A30TP-17356")
    public void addTaxRateClubFee() {
        log.info("Adding location tax rate for Club tax fee");
        setTestResultOnXRay("A30TP-17356");
        getPage(UnoAppTaxRatePage.class)
                .add_club_level_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Pre-notes tax fee")
    @TmsLink("A30TP-17326")
    public void addTaxRatePreNotes() {
        log.info("Adding location tax rate for Pre-notes tax fee");
        setTestResultOnXRay("A30TP-17326");
        getPage(UnoAppTaxRatePage.class)
                .add_pre_note_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for CC Charge Back tax fee")
    @TmsLink("A30TP-17329")
    public void addTaxRateCC() {
        log.info("Adding location tax rate for Charge Back tax fee");
        setTestResultOnXRay("A30TP-17329");
        getPage(UnoAppTaxRatePage.class)
                .add_cc_charge_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Unauthorized tax fee")
    @TmsLink("A30TP-17330")
    public void addTaxRateUnauthorized() {
        log.info("Adding location tax rate for Unauthozed tax fee");
        setTestResultOnXRay("A30TP-17330");
        getPage(UnoAppTaxRatePage.class)
                .add_unauthorized_tax_rate()
                .click_on_save_btn();
    }

    @Test
    @Description("Adding fee")
    @TmsLink("A30TP-18894")
    public void addFee() {
        log.info("Adding fee for the location.");
        setTestResultOnXRay("A30TP-18894");
        getPage(UnoAppFeePage.class)
                .click_fee_tab()
                .click_add_fee_btn()
                .add_fee();
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
    @Description("Dispalying existing processor for company.")
    @TmsLink("A30TP-12691")
    public void displayProcessor() {
        log.info("Displaying existing processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Creating EFT processor configuration for the selected location for Payment Gateway.")
    @TmsLink("A30TP-20723")
    public void configuringMerchantEFTProcessor() {
        log.info("Creating merchant for EFT processor.");
        setTestResultOnXRay("A30TP-20723");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create mermber for billing.")
    @TmsLink("A30TP-12693")
    public void logoutPg() {
        log.info("Exit from Payment Gateway and switch to create mermber for billing.");
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    @TmsLink("A30TP-8300")
    public void createSubsWithOneMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-8300");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    @TmsLink("A30TP-8304")
    public void enterDetailsForSubscription() {
        log.info("Entering the details of bank account ");
        setTestResultOnXRay("A30TP-8304");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT));

    }

    @Test
    @Description("Create Open Ended Subscription without down payment when begin date is Current date and frequency is Bi Weekly with Payment Method Type as 'Bank Account'.")
    @TmsLink("A30TP-9261")
    public void createSubscription() {
        log.info("Entering schedule information has started");
        setTestResultOnXRay("A30TP-9261");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();

    }

    @Test
    @Description("Verifying Subscription creation is done successfully")
    @TmsLink("A30TP-8673")
    public void verifyOpenEndedSubscription() {
        log.info("Verify subscription creation is done");
        setTestResultOnXRay("A30TP-8673");
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
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,SUB_TYPE));
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
                .verify_wallet_payment_method_bank(getTestData(SUBSCRIPTION_INI,CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,PAYMENT_METHOD_BANK))
                .verify_wallet_bank_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_WITH_DWNPAY_OPENENDED_BIWEEKLY,SUB_ACCOUNT_HOLDER_NAME));

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
    @TmsLink("A30TP-13577")
    public void verifyNotesTab() {
        log.info("Verify that notes tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13577");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_info(getTestData(UNOAPP_INI,MEMBER_SECTION,NOTES_INFO));
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