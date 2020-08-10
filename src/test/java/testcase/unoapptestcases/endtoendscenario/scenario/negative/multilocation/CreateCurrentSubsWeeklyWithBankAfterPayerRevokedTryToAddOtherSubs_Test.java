package testcase.unoapptestcases.endtoendscenario.scenario.negative.multilocation;

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
import static helper.DataProviderSection.CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS;
import static util.UtilityGeneric.set_section_name;
/*
Created By: Sharwan Jha
Date: 02/04/2020
*/

@Issue("A30TP-33302")
@Slf4j
public class CreateCurrentSubsWeeklyWithBankAfterPayerRevokedTryToAddOtherSubs_Test extends AbstractAutoUITest {
    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay("A30TP-14514");
        set_section_name(CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS);
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
    @Description("Create Organization ")
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
                        ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP))
                .verify_organization_by_name();
    }

    @Test
    @Description("Creating first Location for organization.")
    @TmsLink("A30TP-8212")
    public void createLocation() {
        log.info("Creating first location for organization with valid data.");
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
    public void selectTaxRateTabForFistLocation() {
        log.info("Selecting tax rate tab and click on add tax rate button.");
        setTestResultOnXRay("A30TP-17313");
        getPage(UnoAppTaxRatePage.class)
                .click_tax_rate_tab()
                .click_on_add_tax();
    }

    @Test
    @Description("Adding location tax rate for ABC tax fee.")
    @TmsLink("A30TP-17317")
    public void addTaxRateAbcForFistLocation() {
        log.info("Adding location tax rate for ABC tax fee");
        setTestResultOnXRay("A30TP-17317");
        getPage(UnoAppTaxRatePage.class)
                .add_abc_tax_rate();
    }

    @Test
    @Description("Adding location tax rate for Unauthorized tax fee")
    @TmsLink("A30TP-17330")
    public void addTaxRateUnauthorizedForFistLocation() {
        log.info("Adding location tax rate for Unauthorized tax fee");
        setTestResultOnXRay("A30TP-17330");
        getPage(UnoAppTaxRatePage.class)
                .add_unauthorized_tax_rate()
                .click_on_save_btn();
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
    @Description("Adding banking details in the second location")
    @TmsLink("A30TP-8216")
    public void addBankingDetailsForSecondLocation() {
        log.info("Adding bank details for the second location");
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
    @Description("Signing into the payment gateway")
    @TmsLink("A30TP-12686")
    public void loginPaymentGateway() {
        log.info("Signing into payment gateway with valid credential.");
        setTestResultOnXRay("A30TP-12686");
        getPage(PaymentGatewayLoginPage.class)
                .login_pg();
    }

    @Test
    @Description("Selecting ABC Financial Service company in payment gateway.")
    @TmsLink("A30TP-12689")
    public void searchAndSelectCompany() {
        log.info("Selecting the existing company.");
        setTestResultOnXRay("A30TP-12689");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES);
    }

    @Test
    @Description("Display the processor for company")
    @TmsLink("A30TP-12691")
    public void displayProcessor() {
        log.info("Displaying the processor for company");
        setTestResultOnXRay("A30TP-12691");
        getPage(ProcessorPage.class)
                .view_processor();
    }

    @Test
    @Description("Configure Merchant for first location with EFT processor.")
    @TmsLink("A30TP-20723")
    public void creatingMerchantAccountForFirstLocation() {
        log.info("Link EFT processor with merchant account");
        setTestResultOnXRay("A30TP-20723");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
    }

    @Test
    @Description("Configure Merchant for second location with EFT processor.")
    @TmsLink("A30TP-15917")
    public void creatingMerchantAccountForSecondLocation() {
        log.info("Link EFT processor with merchant account");
        setTestResultOnXRay("A30TP-15917");
        getPage(MerchantAccountPage.class)
                .create_merchant_account(getTestData(UNOAPP_INI, LOCATION_SECTION, SECONDLOCATION, REGIONS_BANK));
    }

    @Test
    @Description("Logout from payment gateway")
    @TmsLink("A30TP-12693")
    public void logoutPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test
    @Description("click on add member button and select first location")
    @TmsLink("A30TP-20823")
    public void addMemberForFirstLocation() {
        log.info("Add member for first location");
        setTestResultOnXRay("A30TP-20823");
        getPage(UnoAppMemberPage.class)
                .navigate_and_click_add_member_btn()
                .click_add_subscription_for_location(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME));
    }

    @Test
    @Description("Create Subscription for first location.Enter the member personal details and subscription downpayment")
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
    }

    @Test
    @Description("Create multiple location with one organization, for first location,Create Open-ended subscription with bank account where subscription with amount $40 when being date is current and frequency is Weekly.")
    @TmsLink("A30TP-35376")
    public void enterSubscriptionScheduleDetails() {
        log.info("Create multiple location with one organization, for first location,Create Open-ended subscription with bank account where subscription with amount $40 when being date is current and frequency is Weekly.");
        setTestResultOnXRay("A30TP-35376");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,
                        SUB_TYPE, SUB_DUE_DATE, ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verify the subscription is created successfully for first location.")
    @TmsLink("A30TP-34370")
    public void verifySubscriptionCreatedForFirstLocation() {
        log.info("Verify the subscription is created successfully with valid data for first location.");
        setTestResultOnXRay("A30TP-34370");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_FIRST_NAME));
    }

    @Test
    @Description("Verifying profile tab and its data displayed for first location subscription")
    @TmsLink("A30TP-13572")
    public void verifyProfileTab() {
        log.info("Verify that profile tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13572");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI,MEMBER_SECTION,PROFILE_CONTACT_INFO));

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
    @Description("Verifying subscription tab and its data displayed for first location subscription ")
    @TmsLink("A30TP-13574")
    public void verifySubscriptionTab() {
        log.info("Verify that subscription tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13574");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_TYPE));
    }

    @Test
    @Description("Verifying wallet tab and its data displayed for first location subscription")
    @TmsLink("A30TP-13571")
    public void verifyWalletTab() {
        log.info("Verify that wallet tab for subscription created for a member in first location subscription");
        setTestResultOnXRay("A30TP-13571");
        getPage(UnoAppMemberDetailPage.class)
                .click_payments_tab()
                .verify_wallet_payment_method_bank(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,PAYMENT_METHOD_BANK))
                .verify_wallet_bank_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_ACCOUNT_HOLDER_NAME));

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
    @Description("Verifying Notes tab and it should be blank when no notes are added")
    @TmsLink("A30TP-17895")
    public void verifyNotesTab() {
        log.info("Verify that notes tab for subscription created for a member");
        setTestResultOnXRay("A30TP-17895");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_info(getTestData(UNOAPP_INI,MEMBER_SECTION,NOTES_INFO));
    }

    @Test
    @Description("Select add member page for second location")
    @TmsLink("A30TP-12135")
    public void addMemberForSecondLocation() {
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
    public void enterMemberDetailsForSecondLocation() {
        log.info("Subscription creation has started with member creation for second location");
        setTestResultOnXRay("A30TP-15962");
        getPage(UnoAppMemberPage.class)
                .generate_second_member_first_name(SECOND_MEMBER)
                .enter_member_for_second_location(getTestData(UNOAPP_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        SECOND_MEMBER, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER))
                .click_continue_button()
                .create_payor();
    }

    @Test
    @Description("Create Open-ended subscription with bank account where subscription with amount $40 where being date is current and frequency is Weekly without down payment.")
    @TmsLink("A30TP-34796")
    public void createSubscriptionForSecondLocation() {
        log.info("Create Open-ended subscription with bank account where subscription with amount $40 where being date is current and frequency is Weekly without down payment.");
        setTestResultOnXRay("A30TP-34796");
        getPage(UnoAppSubscriptionPage.class)
                .enter_subscription_schedule_information_for_second_location(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,
                        SUB_TYPE, SUB_DUE_DATE, SECOND_ITEM_NAME, SUB_BEGIN_DATE, SUB_PAY_FREQUENCY, SUB_PAY_AMOUNT))
                .enter_payment_information_bank_account(getTestData(SUBSCRIPTION_INI, CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,
                        SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER))
                .click_on_submit_btn();
    }

    @Test
    @Description("Verify the subscription is created successfully for second location.")
    @TmsLink("A30TP-35382")
    public void verifySubscriptionCreatedForSecondLocation() {
        log.info("Verify the subscription is created successfully with valid data for second location.");
        setTestResultOnXRay("A30TP-35382");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .select_second_member_created(getTestData(UNOAPP_INI, MEMBER_SECTION, SECOND_MEMBER));
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
    @Description("Verifying subscription tab and its data displayed for second location")
    @TmsLink("A30TP-15974")
    public void verifySubscriptionTabForSecondLcoation() {
        log.info("Verify that subscription tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-15974");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_frequency(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_PAY_FREQUENCY))
                .verify_subscription_amenity_second_location_item(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SECOND_ITEM_NAME))
                .verify_subscription_type(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_TYPE));
    }

    @Test
    @Description("Verifying wallet tab and its data displayed for second location")
    @TmsLink("A30TP-15977")
    public void verifyWalletTabForSecondLcoation() {
        log.info("Verify that wallet tab for subscription created for a member in second location");
        setTestResultOnXRay("A30TP-15977");
        getPage(UnoAppMemberDetailPage.class)
                .click_payments_tab()
                .verify_wallet_payment_method_bank(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,PAYMENT_METHOD_BANK))
                .verify_wallet_bank_account_holder_name(getTestData(SUBSCRIPTION_INI,CURRENT_MULTIPLELOCATION_OPENENDED_WEEKLY_BANK_PAYOR_REVOKED_ANOTHER_SUBS,SUB_ACCOUNT_HOLDER_NAME));

    }

    @Test
    @Description("Clicking on Payment-method list and stop payment.")
    @TmsLink("A30TP-33134")
    public void clickOnPaymentMethod() {
        log.info("Clicking on Payment-method list and stop payment.");
        setTestResultOnXRay("A30TP-33134");
        getPage(UnoAppMemberDetailPage.class)
                .click_on_payment_method_list()
                .click_on_stop_payments_btn();
    }

    @Test
    @Description("Clicking on stop payment and then status is changes to 'Payer Revoked'.")
    @TmsLink("A30TP-34725")
    public void verifyPaymentMethodStatus() {
        log.info("Clicking on stop payment and then status is changes to 'Payer Revoked'.");
        setTestResultOnXRay("A30TP-34725");
        getPage(UnoAppMemberDetailPage.class)
                .confirm_to_stop_payment()
                .close_payment_dialog_box()
                .verify_payment_method_status();
    }

    @Test
    @Description("Clicking on stop payment-method")
    @TmsLink("A30TP-35070")
    public void createAnotherSubsAferPaymentMethodIsRevoked() {
        log.info("Clicking Add Subscription button");
        setTestResultOnXRay("A30TP-35070");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .add_subscription();
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
