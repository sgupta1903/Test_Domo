package testcase.unoapptestcases.endtoendscenario.scenario.AccountDetailsTab.BeforeScheduler;
/*
Created By: Shilpi Gupta
Date: 01/08/2020
*/

import config.EnvProperty;
import helper.AppConstants;
import helper.DataProviderSection;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
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
import static helper.DataProviderSection.ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK;
import static util.UtilityGeneric.getDate;
import static util.UtilityGeneric.set_section_name;


@Issue("A30TP-32829")
@Slf4j
public class CreatePastMonthlyWithoutDwnPay_Test extends AbstractAutoUITest { 
    
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(DataProviderSection.ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK);
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
    @Description("Selecting tax rate tab and click on add tax rate button.")
    public void selectTaxRateTab() {
        log.info("Selecting tax rate tab and click on add tax rate button.");
        setTestResultOnXRay("A30TP-17313");
        getPage(UnoAppTaxRatePage.class)
                .click_tax_rate_tab()
                .click_on_add_tax();
    }

    @Test
    @Description("Adding location tax rate for ABC tax fee.")
    public void addTaxRateAbc() {
        log.info("Adding location tax rate for ABC tax fee");
        setTestResultOnXRay("A30TP-17317");
        getPage(UnoAppTaxRatePage.class)
                .enter_abc_base_percentage(env.getConfigPropertyValue("TaxRate", "abcBasePercentage"))
                .enter_abc_rate_per_transaction(env.getConfigPropertyValue("TaxRate", "abcPerTransaction"))
                .enter_abc_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "abcStartDate"))))
                .enter_abc_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "abcEndDate"))))
                .click_on_save_btn();
    }

    @Test
    @Description("Adding Payment - Deduction in the location")
    public void addOneTimeDeduction() {
        log.info("Adding Payment - Deduction in the location with valid data.");
        setTestResultOnXRay("A30TP-18792");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(DEDUCTIONS)
                .click_add_button(ADDDEDUCTIONS)
                .verify_add_deduction_page_displayed()
                .add_one_time_deduction()
                .click_save_button()
                .verify_payment_option_added(ADDDEDUCTIONS);

    }

    @Test
    @Description("Adding Payment - Deduction in the location")
    public void addRecurringDeduction() {
        log.info("Adding Payment - Deduction in the location with valid data.");
        setTestResultOnXRay("A30TP-19329");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(DEDUCTIONS)
                .click_add_button(ADDDEDUCTIONS)
                .verify_add_deduction_page_displayed()
                .add_recurring_deduction()
                .click_save_button()
                .verify_payment_option_added(ADDDEDUCTIONS);
    }

    @Test
    @Description("Adding Payment - Reimbursement in the location")
    public void addOneTimeReimbursement() {
        log.info("Adding Payment - Reimbursement in the location with valid data.");
        setTestResultOnXRay("A30TP-18804");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(REIMBURSEMENT)
                .click_add_button(ADDREIMBURSEMENT)
                .verify_add_reimbursement_page_displayed()
                .add_one_time_reimbursement()
                .click_save_button()
                .verify_payment_option_added(ADDREIMBURSEMENT);
    }

    @Test
    @Description("Adding Payment - Reimbursement in the location")
    public void addRecurringReimbursement() {
        log.info("Adding Payment - Reimbursement in the location with valid data.");
        setTestResultOnXRay("A30TP-19333");
        getPage(UnoAppLocationPaymentTabPage.class)
                .select_payment_tab()
                .select_location_payment_option(REIMBURSEMENT)
                .click_add_button(ADDREIMBURSEMENT)
                .verify_add_reimbursement_page_displayed()
                .add_recurring_reimbursement()
                .click_save_button()
                .verify_payment_option_added(ADDREIMBURSEMENT);
    }

    @Test
    @Description("Sign In into the payment gateway.")
    public void loginPg() {
        log.info("Sign In into payment gateway with valid credential.");
        setTestResultOnXRay("A30TP-12686");
        getPage(PaymentGatewayLoginPage.class)
                .login_pg();
    }

    @Test
    @Description("Selecting company for processor.")
    public void selectCompany() {
        log.info("Selecting the existing company.");
        setTestResultOnXRay("A30TP-12689");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES);
    }

    @Test
    @Description("Displaying the processor for company.")
    public void displayPocessor() {
        log.info("Displaying the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Creating EFT processor configuration for the selected location for Payment Gateway.")
    public void creatingMerchantVantivProcessor() {
        log.info("Creating EFT processor configuration for the selected location for Payment Gateway");
        setTestResultOnXRay("A30TP-20722");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create mermber for billing.")
    public void logoutPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
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
        setTestResultOnXRay("A30TP-8440");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_DOWNPAYMENT));
    }

    @Test
    @Description("Create Installment auto-renewal to open Subscription when payment is done by Bank account")
    public void createRenewToOpenSubscription() {
        log.info("Create Installment auto-renewal to open Subscription when payment is done by Bank account.");
        setTestResultOnXRay("A30TP-9248");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_EXPIRY_DATE, SUB_DURATION))
                .enter_renewal_auto_to_open(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK, SUB_RENEWAL_TYPE,
                        SUB_RENEWAL_DATE, SUB_RENEWAL_FREQUENCY));
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    public void enterDetailsForSubscription() {
        log.info("Entering the details of credit account ");
        setTestResultOnXRay("A30TP-9246");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();

    }

    @Test
    @Description("Verify subscription is created successfully")
    public void verifySubscriptionCreated() {
        log.info("Verifying the subscription is created ");
        setTestResultOnXRay("A30TP-9246");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
    }
}
