package testcase.unoapptestcases.endtoendscenario.scenario.multiplelocation.Card;

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
import static helper.DataProviderSection.CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD;

import static util.UtilityGeneric.set_section_name;

/*
Created By :Sharwan Jha
Date : 12/12/2019
*/
@Issue("A30TP-22061")
@Slf4j
public class CreateCurrentAutoRenewToOpenSubsBiWeekly_Test extends AbstractAutoUITest {
    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay("A30TP-14514");
        set_section_name(CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD);
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
    @Description("Creating first Location for organization.")
    @TmsLink("A30TP-8212")
    public void createLocation() {
        log.info("Creating first location for oranization with valid data.");
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
    @TmsLink("A30TP-8229")
    public void createItemCategoryForFirstLocation() {
        log.info("Creating Item Category for location with valid data.");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Creating Catalog item.")
    @TmsLink("A30TP-8235")
    public void createItemForFirstLocation() {
        log.info("Creating item for Item catalog with valid data.");
        setTestResultOnXRay("A30TP-8235");
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
        log.info("Adding location tax rate for Unauthorized tax fee");
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
    @Description("Adding Payment - Deduction in the location")
    @TmsLink("30TP-18792")
    public void addDeduction() {
        log.info("Adding Payment - Deduction in the location with valid data.");
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
    @TmsLink("A30TP-18804")
    public void addReimbursement() {
        log.info("Adding Payment - Deduction in the location with valid data.");
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
    @Description("Creating second Location for same organization.")
    @TmsLink("A30TP-15906")
    public void createSecondLocation() {
        log.info("Creating second location for organization with valid data.");
        setTestResultOnXRay("A30TP-15906");
        getPage(UnoAppLocationPage.class)
                .click_back_button();
        getPage(UnoAppOrganizationPage.class)
                .select_organization_option()
                .verify_organization_visible(getTestData(UNOAPP_INI, ORGANIZATION_SECTION, ORGANIZATION_NAME))
                .select_organization(getTestData(UNOAPP_INI, ORGANIZATION_SECTION, ORGANIZATION_NAME));
        getPage(UnoAppLocationPage.class)
                .generate_location_name(SECONDLOCATION)
                .create_location(getTestData(UNOAPP_INI, LOCATION_SECTION, SECONDLOCATION,
                        LOCATION_PHONE_NUMBER, LOCATION_EMAIL, LOCATION_ADD1,
                        LOCATION_ADD2, LOCATION_CITY, LOCATION_ZIP, LOCATION_STATE,
                        LOCATION_TIMEZONE, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER));

    }

    @Test
    @Description("Adding banking details for the second location")
    @TmsLink("A30TP-8216")
    public void addBankingDetailsForSecondLocation() {
        log.info("Creating second location for oranization with valid data.");
        setTestResultOnXRay("A30TP-8216");
        getPage(UnoAppLocationPage.class)
                .add_location_bank_details(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER));
    }

    @Test
    @Description("Creating Item category.")
    @TmsLink("A30TP-8229")
    public void createItemCategory() {
        log.info("Creating Item Category for location with valid data.");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name_for_second_location(SECOND_ITEM_CATEGORY_NAME)
                .create_item_category_for_second_location(getTestData(UNOAPP_INI, ITEM_SECTION,
                        SECOND_ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Creating Catalog item.")
    @TmsLink("A30TP-8235")
    public void createItem() {
        log.info("Creating item for Item catalog with valid data.");
        setTestResultOnXRay("A30TP-8235");
        getPage(UnoAppItemPage.class)
                .generate_item_name_for_second_location(SECOND_ITEM_NAME)
                .create_item_for_second_location(getTestData(UNOAPP_INI, ITEM_SECTION, ITEM_TYPE,
                        AUTO_PAY, SECOND_ITEM_CATEGORY_NAME, SECOND_ITEM_NAME));
    }

    @Test
    @Description("Selecting tax rate tab and click on add tax rate button.")
    @TmsLink("A30TP-17313")
    public void selectTaxRateTabForSecondLocation() {
        log.info("Selecting tax rate tab and click on add tax rate button.");
        setTestResultOnXRay("A30TP-17313");
        getPage(UnoAppTaxRatePage.class)
                .click_tax_rate_tab()
                .click_on_add_tax();
    }

    @Test
    @Description("Adding location tax rate for ABC tax fee.")
    @TmsLink("A30TP-17317")
    public void addTaxRateAbcForSecondLocation() {
        log.info("Adding location tax rate for ABC tax fee");
        setTestResultOnXRay("A30TP-17317");
        getPage(UnoAppTaxRatePage.class)
                .add_abc_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for ACH tax fee")
    @TmsLink("A30TP-17319")
    public void addTaxRateAchForSecondLocation() {
        log.info("Adding location tax rate for ACH tax fee");
        setTestResultOnXRay("A30TP-17319");
        getPage(UnoAppTaxRatePage.class)
                .add_ach_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Statement tax fee")
    @TmsLink("A30TP-17320")
    public void addTaxRateStatementForSecondLocation() {
        log.info("Adding location tax rate for Statement tax fee");
        setTestResultOnXRay("A30TP-17320");
        getPage(UnoAppTaxRatePage.class)
                .add_statement_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for American Express tax fee")
    @TmsLink("A30TP-17321")
    public void addTaxRateAmericanExpressForSecondLocation() {
        log.info("Adding location tax rate for American Express tax fee");
        setTestResultOnXRay("A30TP-17321");
        getPage(UnoAppTaxRatePage.class)
                .add_american_express_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Master Card tax fee")
    @TmsLink("A30TP-17322")
    public void addTaxRateMasterCardForSecondLocation() {
        log.info("Adding location tax rate for Master Card tax fee");
        setTestResultOnXRay("A30TP-17322");
        getPage(UnoAppTaxRatePage.class)
                .add_master_card_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for VISA tax fee")
    @TmsLink("A30TP-17323")
    public void addTaxRateVisaForSecondLocation() {
        log.info("Adding location tax rate for VISA tax fee");
        setTestResultOnXRay("A30TP-17323");
        getPage(UnoAppTaxRatePage.class)
                .add_visa_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Discover tax fee")
    @TmsLink("A30TP-17324")
    public void addTaxRateDiscoverForSecondLocation() {
        log.info("Adding location tax rate for Discover tax fee");
        setTestResultOnXRay("A30TP-17324");
        getPage(UnoAppTaxRatePage.class)
                .add_discover_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Payments tax fee")
    @TmsLink("A30TP-17325")
    public void addTaxRatePaymentsForSecondLocation() {
        log.info("Adding location tax rate for Payments tax fee");
        setTestResultOnXRay("A30TP-17325");
        getPage(UnoAppTaxRatePage.class)
                .add_payments_add_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Club Fee tax fee")
    @TmsLink("A30TP-17356")
    public void addTaxRateClubFeeForSecondLocation() {
        log.info("Adding location tax rate for Club tax fee");
        setTestResultOnXRay("A30TP-17356");
        getPage(UnoAppTaxRatePage.class)
                .add_club_level_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Pre-notes tax fee")
    @TmsLink("A30TP-17326")
    public void addTaxRatePreNotesForSecondLocation() {
        log.info("Adding location tax rate for Pre-notes tax fee");
        setTestResultOnXRay("A30TP-17326");
        getPage(UnoAppTaxRatePage.class)
                .add_pre_note_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for CC Charge Back tax fee")
    @TmsLink("A30TP-17329")
    public void addTaxRateCCForSecondLocation() {
        log.info("Adding location tax rate for Charge Back tax fee");
        setTestResultOnXRay("A30TP-17329");
        getPage(UnoAppTaxRatePage.class)
                .add_cc_charge_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Unauthorized tax fee")
    @TmsLink("A30TP-17330")
    public void addTaxRateUnauthorizedForSecondLocation() {
        log.info("Adding location tax rate for Unauthorized tax fee");
        setTestResultOnXRay("A30TP-17330");
        getPage(UnoAppTaxRatePage.class)
                .add_unauthorized_tax_rate()
                .click_on_save_btn();
    }

    @Test
    @Description("Adding fee")
    @TmsLink("A30TP-18894")
    public void addFeeForSecondLocation() {
        log.info("Adding fee for the location.");
        setTestResultOnXRay("A30TP-18894");
        getPage(UnoAppFeePage.class)
                .click_fee_tab()
                .click_add_fee_btn()
                .add_fee();

    }

    @Test
    @Description("Adding Payment - Deduction in the location")
    @TmsLink("A30TP-18792")
    public void addDeductionForSecondLocation() {
        log.info("Adding Payment - Deduction in the location with valid data.");
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
    @TmsLink("A30TP-18804")
    public void addReimbursementForSecondLocation() {
        log.info("Adding Payment - Deduction in the location with valid data.");
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
    @Description("Sign In into the payment gateway.")
    @TmsLink("A30TP-12686")
    public void loginPg() {
        log.info("Sign In into payment gateway with valid credential.");
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
    @Description("Display the processor for company.")
    @TmsLink("A30TP-12691")
    public void displayProcessor() {
        log.info("Displaying the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Creating EFT processor configuration for the first location on Payment Gateway.")
    @TmsLink("A30TP-20723")
    public void configuringMerchant() {
        log.info("Creating EFT processor configuration for the first location on Payment Gateway.");
        setTestResultOnXRay("A30TP-20723");
        getPage(MerchantAccountPage.class)
                .create_merchant_vantiv();
    }

    @Test
    @Description("Creating Vantiv processor configuration for second location for Payment Gateway.")
    @TmsLink("A30TP-15917")
    public void creatingMerchantAccountForSecondLocation() {
        log.info("Creating Vantiv processor configuration for the second location on Payment Gateway.");
        setTestResultOnXRay("A30TP-15917");
        getPage(MerchantAccountPage.class)
                .create_merchant_vantiv_for_another_location(SECONDLOCATION);
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create member for billing.")
    @TmsLink("A30TP-12693")
    public void logoutFromPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("Select add member page for first location")
    @TmsLink("A30TP-20823")
    public void selectAddMember() {
        log.info("Selecting add member page for first location");
        setTestResultOnXRay("A30TP-20823");
        getPage(UnoAppMemberPage.class)
                .navigate_and_click_add_member_btn()
                .click_add_subscription_for_location(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME));
    }

    @Test
    @Description("Create Subscription for first location and enter the member details")
    @TmsLink("A30TP-15944")
    public void enterMemberDetailsForFirstLocation() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-15944");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .enter_member_details(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER))
                .click_continue_button()
                .create_payor();
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create Renew to open Subscription with Card.")
    @TmsLink("A30TP-8440")
    public void enterRenewToOpenSubscription() {
        log.info("Create Renew-to-open Subscription with credit card");
        setTestResultOnXRay("A30TP-8440");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT));
    }

    @Test
    @Description("Create Renew-to-open Subscription with credit card for past and frequency Bi-weekly.")
    @TmsLink("A30TP-31193")
    public void createNonRenewSubscription() {
        log.info("Create Installment subscription with 2nd location is align with one organization where subscription type is renew-to open begin date is current and frequency is bi-weekly and payment method is credit card and expiration date is future.");
        setTestResultOnXRay("A30TP-31193");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_open(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));
    }

    @Test
    @Description("Create Renew-to-open Subscription with credit card")
    @TmsLink("A30TP-31194")
    public void enterDetailsForSubscription() {
        log.info("Create Installment subscription with 2nd location is align with one organization where subscription type is renew-to open frequency is bi-weekly and payment method is credit card when begin date is current and expiration date is future");
        setTestResultOnXRay("A30TP-31194");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_credit_card(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME, SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE))
                .click_on_submit_btn()
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_FIRST_NAME));
    }

    @Test
    @Description("Verifying subscription tab and its data displayed for first location subscription ")
    @TmsLink("A30TP-13574")
    public void verifySubscriptionTab() {
        log.info("Verify that subscription tab for subscription created for a member");
        setTestResultOnXRay("A30TP-13574");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_TYPE));
    }

    @Test
    @Description("Verifying agreement tab and its data displayed for first location subscription")
    @TmsLink("A30TP-13578")
    public void verifyAgreementTab() {
        log.info("Verify that agreement tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13578");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_STATUS));

    }

    @Test
    @Description("Verifying wallet tab and its data displayed for first location subscription")
    @TmsLink("A30TP-13571")
    public void verifyWalletTab() {
        log.info("Verify that wallet tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13571");
        getPage(UnoAppMemberDetailPage.class)
                .click_payments_tab()
                .verify_wallet_payment_method_card(getTestData(UNOAPP_INI,MEMBER_SECTION,PAYMENT_METHOD_CARD))
                .verify_wallet_card_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_CARD_HOLDER_NAME));
    }

    @Test
    @Description("Verifying profile tab and its data displayed for first location subscription")
    @TmsLink("A30TP-13572")
    public void verifyProfileTab() {
        log.info("Verify that profile tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13572");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added for first location subscription")
    @TmsLink("A30TP-13577")
    public void verifyNotesTab() {
        log.info("Verify that notes tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13577");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_info(getTestData(UNOAPP_INI,MEMBER_SECTION,NOTES_INFO));
    }

    @Test
    @Description("Select add member page for second location")
    @TmsLink("A30TP-12135")
    public void AddMemberForSecondLocation() {
        log.info("Selecting add member page for second location");
        setTestResultOnXRay("A30TP-12135");
        getPage(UnoAppMemberPage.class)
                .navigate_and_click_add_member_btn();
    }

    @Test
    @Description("Select add member page details for second location")
    @TmsLink("A30TP-15960")
    public void selectAddMemberButton() {
        log.info("Selecting add member page details for second location");
        setTestResultOnXRay("A30TP-15960");
        getPage(UnoAppMemberPage.class)
                .click_add_subscription_for_location(getTestData(UNOAPP_INI, LOCATION_SECTION, SECONDLOCATION));
    }

    @Test
    @Description("Create Subscription for second location and enter the member details for second location")
    @TmsLink("A30TP-15962")
    public void enterMemberDetailsForSecondLcoation() {
        log.info("Subscription creation has started with member creation for second location");
        setTestResultOnXRay("A30TP-15962");
        getPage(UnoAppMemberPage.class)
                .generate_second_member_first_name(SECOND_MEMBER)
                .enter_member_for_second_location(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        SECOND_MEMBER, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER))
                .click_continue_button()
                .create_payor();
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create Installment subscription  where subscription type is renew-to open frequency is bi-weekly and payment method is credit card ")
    @TmsLink("A30TP-8440")
    public void enterSubscriptionForSecondLcoation() {
        log.info("Create Installment subscription  where subscription type is renew-to open frequency is bi-weekly and payment method is credit card ");
        setTestResultOnXRay("A30TP-8440");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_for_second_location(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_TYPE, SUB_DUE_DATE, SECOND_ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT));
    }

    @Test
    @Description("Create Installment subscription with 1st location is align with one organization where subscription type is renew-to open frequency is bi-weekly and payment method is credit card where begin date is current and expiration date is future.")
    @TmsLink("A30TP-31196")
    public void createSubscriptionforSecondLocation() {
        log.info("Create Installment subscription with 1st location is align with one organization where subscription type is renew-to open frequency is bi-weekly and payment method is credit card where begin date is current and expiration date is future.");
        setTestResultOnXRay("A30TP-31196");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_open(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));
    }

    @Test
    @Description("Create Installment subscription with 1st location is align with one organization where subscription type is renew-to open frequency is bi-weekly and payment method is credit card when begin date is current and due date is future.")
    @TmsLink("A30TP-31197")
    public void enterCardDetails() {
        log.info("Create Installment subscription with 1st location is align with one organization where subscription type is renew-to open frequency is bi-weekly and payment method is credit card when begin date is current and due date is future.");
        setTestResultOnXRay("A30TP-31197");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_credit_card(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,
                        SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME, SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE))
                .click_on_submit_btn()
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_second_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, SECOND_MEMBER));
    }

    @Test
    @Description("Verifying subscription tab and its data displayed for second location")
    @TmsLink("A30TP-12064")
    public void verifySubscriptionTabForSecondLcoation() {
        log.info("Verify that subscription tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-12064");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity_second_location_item(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SECOND_ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_TYPE));
    }

    @Test
    @Description("Verifying agreement tab and its data displayed for second location")
    @TmsLink("A30TP-15975")
    public void verifyAgreementTabForSecondLocation() {
        log.info("Verify that agreement tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-15975");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI,MEMBER_SECTION,AGREEMENT_STATUS));
    }

    @Test
    @Description("Verifying wallet tab and its data displayed for second location")
    @TmsLink("A30TP-15977")
    public void verifyWalletTabForSecondLocation() {
        log.info("Verify that wallet tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-15977");
        getPage(UnoAppMemberDetailPage.class)
                .click_payments_tab()
                .verify_wallet_payment_method_card(getTestData(UNOAPP_INI,MEMBER_SECTION,PAYMENT_METHOD_CARD))
                .verify_wallet_card_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLE_LOCATION_AUTORENEWTOOPEN_BIWEEKLY_CARD,SUB_CARD_HOLDER_NAME));
    }

    @Test
    @Description("Verifying profile tab and its data displayed for second location")
    @TmsLink("A30TP-15979")
    public void verifyProfileTabForSecondLocation() {
        log.info("Verify that profile tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-15979");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added for a member of second lcoation")
    @TmsLink("A30TP-15981")
    public void verifyNotesTabForSecondLocation() {
        log.info("Verify that notes tab for subscription created for a member of second location");
        setTestResultOnXRay("A30TP-15981");
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
