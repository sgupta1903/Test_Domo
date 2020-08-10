package testcase.unoapptestcases.endtoendscenario.scenario.installment.card.nonrenewable;

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
import static helper.DataProviderSection.CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD;
import static util.UtilityGeneric.set_section_name;

/*
Created By: Shilpi Gupta
Date: 08/28/2019
*/

@Issue("A30TP-7792")
@Slf4j
public class CreateCurrentSubsSemiAnnualWithOutDwnPay_Test extends AbstractAutoUITest {
    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD);
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
        log.info("Creating location for oranization with valid data.");
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
    @Description("Adding Payment - Deduction in the location")
    @TmsLink("A30TP-18792")
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
    @Description("Displaying the processor for company.")
    @TmsLink("A30TP-12691")
    public void displayPocessor() {
        log.info("Displaying the processor for company.");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Creating VANTIV processor configuration for the selected location for Payment Gateway.")
    @TmsLink("A30TP-20722")
    public void creatingMerchantVantivProcessor() {
        log.info("Creating VANTIV processor configuration for the selected location for Payment Gateway");
        setTestResultOnXRay("A30TP-20722");
        getPage(MerchantAccountPage.class)
                .create_merchant_vantiv();
    }

    @Test
    @Description("Exit from Payment Gateway and switch to create mermber for billing.")
    @TmsLink("A30TP-12693")
    public void logoutPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
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
    @TmsLink("A30TP-11917")
    public void createSubsWithOneMember() {
        log.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-11917");
        getPage(UnoAppMemberPage.class)
                .generate_member_first_name(MEMBER_FIRST_NAME)
                .create_member(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_downpayment(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,
                        SUB_DOWNPAYMENT));

    }

    @Test
    @Description("Enter schedule information while creating a subscription")
    @TmsLink("A30TP-11915")
    public void createNonRenewSubscription() {
        log.info("Enter schedule information while creating a subscription");
        setTestResultOnXRay("A30TP-11915");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_subscription_schedule_information_installment(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,
                        SUB_EXPIRY_DATE, SUB_DURATION));
    }

    @Test
    @Description("Enter Bank details for subscription creation")
    @TmsLink("A30TP-9388")
    public void enterDetailsForSubscription() {
        log.info("Entering the details of credit account ");
        setTestResultOnXRay("A30TP-9388");
        getPage(UnoAppSubscriptionPage.class)
                .enter_payment_information_credit_card(getTestData(SUBSCRIPTION_INI, CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,
                        SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME, SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verify the subscription is created successfully.")
    @TmsLink("A30TP-20984")
    public void verifySubscriptionCreated() {
        log.info("Verify the subscription is created successfully with valid data.");
        setTestResultOnXRay("A30TP-20984");
        getPage(UnoAppSubscriptionPage.class)
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
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,SUB_TYPE));
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
                .verify_wallet_card_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_WITHOUT_DWNPAY_NONRENEW_SEMIANNUAL_CARD,SUB_CARD_HOLDER_NAME));
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
    @Description("Logout from the Uno Application")
    @TmsLink("A30TP-8219")
    public void unoAppLogout() {
        log.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }
}
