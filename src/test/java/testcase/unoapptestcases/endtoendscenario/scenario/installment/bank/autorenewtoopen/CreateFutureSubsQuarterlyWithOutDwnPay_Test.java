package testcase.unoapptestcases.endtoendscenario.scenario.installment.bank.autorenewtoopen;

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
import static helper.AppConstants.NOTES_INFO;

import static helper.DataProviderSection.FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK;
import static util.UtilityGeneric.set_section_name;

@Issue("A30TP-21454")

public class CreateFutureSubsQuarterlyWithOutDwnPay_Test extends AbstractAutoUITest {
    /*protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @BeforeClass
    public void setSectionData() {
        logger.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK);
    }

    @Test
    @Description("Login to Uno App")
    public void unoAppLogin() {
        logger.info("Login into the Uno Application");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Creating Organization ")
    public void createOrganization() {
        logger.info("Creating an organization with valid data.");
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
    public void createLocation() {
        logger.info("Creating location for oranization with valid data.");
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
    @Description("Selecting tax rate tab and click on add tax rate button.")
    public void selectTaxRateTab() {
        logger.info("Selecting tax rate tab and click on add tax rate button.");
        setTestResultOnXRay("A30TP-17313");
        getPage(UnoAppTaxRatePage.class)
                .click_tax_rate_tab()
                .click_on_add_tax();
    }

    @Test
    @Description("Adding location tax rate for ABC tax fee.")
    public void addTaxRateAbc() {
        logger.info("Adding location tax rate for ABC tax fee");
        setTestResultOnXRay("A30TP-17317");
        getPage(UnoAppTaxRatePage.class)
                .add_abc_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for ACH tax fee")
    public void addTaxRateAch() {
        logger.info("Adding location tax rate for ACH tax fee");
        setTestResultOnXRay("A30TP-17319");
        getPage(UnoAppTaxRatePage.class)
                .add_ach_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Statement tax fee")
    public void addTaxRateStatement() {
        logger.info("Adding location tax rate for Statement tax fee");
        setTestResultOnXRay("A30TP-17320");
        getPage(UnoAppTaxRatePage.class)
                .add_statement_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for American Express tax fee")
    public void addTaxRateAmericanExpress() {
        logger.info("Adding location tax rate for American Express tax fee");
        setTestResultOnXRay("A30TP-17321");
        getPage(UnoAppTaxRatePage.class)
                .add_american_express_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Master Card tax fee")
    public void addTaxRateMasterCard() {
        logger.info("Adding location tax rate for Master Card tax fee");
        setTestResultOnXRay("A30TP-17322");
        getPage(UnoAppTaxRatePage.class)
                .add_master_card_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for VISA tax fee")
    public void addTaxRateVisa() {
        logger.info("Adding location tax rate for VISA tax fee");
        setTestResultOnXRay("A30TP-17323");
        getPage(UnoAppTaxRatePage.class)
                .add_visa_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Discover tax fee")
    public void addTaxRateDiscover() {
        logger.info("Adding location tax rate for Discover tax fee");
        setTestResultOnXRay("A30TP-17324");
        getPage(UnoAppTaxRatePage.class)
                .add_discover_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Payments tax fee")
    public void addTaxRatePayments() {
        logger.info("Adding location tax rate for Payments tax fee");
        setTestResultOnXRay("A30TP-17325");
        getPage(UnoAppTaxRatePage.class)
                .add_payments_add_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Club Fee tax fee")
    public void addTaxRateClubFee() {
        logger.info("Adding location tax rate for Club tax fee");
        setTestResultOnXRay("A30TP-17356");
        getPage(UnoAppTaxRatePage.class)
                .add_club_level_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Pre-notes tax fee")
    public void addTaxRatePreNotes() {
        logger.info("Adding location tax rate for Pre-notes tax fee");
        setTestResultOnXRay("A30TP-17326");
        getPage(UnoAppTaxRatePage.class)
                .add_pre_note_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for CC Charge Back tax fee")
    public void addTaxRateCC() {
        logger.info("Adding location tax rate for Charge Back tax fee");
        setTestResultOnXRay("A30TP-17329");
        getPage(UnoAppTaxRatePage.class)
                .add_cc_charge_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Unauthorized tax fee")
    public void addTaxRateUnauthorized() {
        logger.info("Adding location tax rate for Unauthozed tax fee");
        setTestResultOnXRay("A30TP-17330");
        getPage(UnoAppTaxRatePage.class)
                .add_unauthorized_tax_rate()
                .click_on_save_btn();
    }

    @Test
    @Description("Adding fee")
    public void addFee() {
        logger.info("Adding fee for the location.");

        setTestResultOnXRay("A30TP-18894");
        getPage(UnoAppFeePage.class)
                .click_fee_tab()
                .click_add_fee_btn()
                .add_fee();

    }

    @Test
    @Description("Adding Payment - Deduction in the location")
    public void addDeduction() {
        logger.info("Adding Payment - Deduction in the location with valid data.");
        setTestResultOnXRay("A30TP-18792");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(DEDUCTIONS)
                .click_add_button(ADDDEDUCTIONS)
                .verify_add_deduction_page_displayed()
                .add_details_for_one_time_deduction()
                .click_save_button()
                .verify_payment_option_added(ADDDEDUCTIONS);

    }

    @Test
    @Description("Adding Payment - Reimbursement in the location")
    public void addReimbursement() {
        logger.info("Adding Payment - Deduction in the location with valid data.");
        setTestResultOnXRay("A30TP-18804");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(REIMBURSEMENT)
                .click_add_button(ADDREIMBURSEMENT)
                .verify_add_reimbursement_page_displayed()
                .add_details_for_one_time_reimbursement()
                .click_save_button()
                .verify_payment_option_added(ADDREIMBURSEMENT);
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
    public void select_company_and_processor() {
        logger.info("Selecting the existing company.");
        setTestResultOnXRay("A30TP-12689");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES);
    }

    @Test
    @Description("Creatin the processor for company.")
    public void createProcessor() {
        logger.info("Creatin the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Configuring Merchant EFT processor for the selected location for Payment Gateway.")
    public void configuringMerchantEFTProcessor() {
        logger.info("Configuring Merchant EFT processor for the selected location for Payment Gateway");
        setTestResultOnXRay("A30TP-20722");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create mermber for billing.")
    public void logoutPg() {
        logger.info("Exit from Payment Gateway and switch to create mermber for billing.");
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("Creating Item category.")
    public void createItemCategory() {
        logger.info("Creating Item Category for location with valid data.");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Creating Catalog item.")
    public void createItem() {
        logger.info("Creating item for Item catalog with valid data.");
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
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create auto-renewal to open subscription begin date is future date and frequency is quarterly without downpayment when payment is done by bank")
    public void createAutoRenewToOpenSubscription() {
        logger.info("Create auto-renewal to open subscription begin date is future date and frequency is quarterly without downpayment when payment is done by bank");
        setTestResultOnXRay("A30TP-36681");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_open(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));

    }

    @Test
    @Description("Enter Card details for subscription creation")
    public void enterDetailsForSubscription() {
        logger.info("Entering the details of credit account ");
        setTestResultOnXRay("A30TP-36682");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verifying that subscription creation is completed")
    public void verifySubscriptionCreation() {
        logger.info("Verify that subscription is created successfully");
        setTestResultOnXRay("A30TP-16568");
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
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, SUB_TYPE));
    }

    @Test
    @Description("Verifying agreement tab and its data displayed for first location subscription")
    public void verifyAgreementTab() {
        logger.info("Verify that agreement tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13578");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI, MEMBER_SECTION, AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI, MEMBER_SECTION, AGREEMENT_STATUS));

    }

    @Test
    @Description("Verifying wallet tab and its data displayed for first location subscription")
    public void verifyWalletTab() {
        logger.info("Verify that wallet tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13571");
        getPage(UnoAppMemberDetailPage.class)
                .click_wallet_tab()
                .verify_wallet_payment_method_bank(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, PAYMENT_METHOD_BANK))
                .verify_wallet_bank_account_holder_name(getTestData(SUBSCRIPTION_INI, FUTURE_WITHOUT_DWNPAY_AUTORENEWTOOPEN_QUARTERLY_BANK, SUB_ACCOUNT_HOLDER_NAME));
    }

    @Test
    @Description("Verifying profile tab and its data displayed for first location subscription")
    public void verifyProfileTab() {
        logger.info("Verify that profile tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13572");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI, MEMBER_SECTION, PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI, MEMBER_SECTION, PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added for first location subscription")
    public void verifyNotesTab() {
        logger.info("Verify that notes tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13577");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_blank(getTestData(UNOAPP_INI, MEMBER_SECTION, NOTES_INFO));
    }


    @Test
    @Description("Logout from the Uno Application")
    public void unoAppLogout() {
        logger.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }
*/
}
