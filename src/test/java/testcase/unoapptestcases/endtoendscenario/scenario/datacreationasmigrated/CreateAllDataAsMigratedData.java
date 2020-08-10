package testcase.unoapptestcases.endtoendscenario.scenario.datacreationasmigrated;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pagetest.paymentgatewaypage.CompanyTabPage;
import pagetest.paymentgatewaypage.MerchantAccountPage;
import pagetest.paymentgatewaypage.PaymentGatewayLoginPage;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;

import static helper.AppConstants.*;


@Issue("A30TP-36860")
public class CreateAllDataAsMigratedData extends AbstractAutoUITest {
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    String clubNumber, memberNumber, clubAddress, clubCity, clubZip, clubPhone, memberName, memberAddress, memberCity, memberZip, memberCountry, memberEmail, memberHomePhone, memberCellPhone,
            memberEmergencyNumber, memberSinceDate, subscriptionType, subscriptionAmount, lastFour, rountingNumber, creditCardType, paymentType;


    public CreateAllDataAsMigratedData(String clubNumber, String memberNumber, String clubAddress, String clubCity, String clubZip, String clubPhone, String memberName, String memberAddress, String memberCity, String memberZip,
                                       String memberCountry, String memberEmail, String memberHomePhone, String memberCellPhone,
                                       String memberEmergencyNumber, String memberSinceDate, String subscriptionType, String subscriptionAmount,
                                       String lastFour, String rountingNumber, String creditCardType, String paymentType) {

        this.clubNumber = clubNumber;
        this.memberNumber = memberNumber;
        this.clubAddress = clubAddress;
        this.clubCity = clubCity;
        this.clubZip = clubZip;
        this.clubPhone = clubPhone;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberCity = memberCity;
        this.memberZip = memberZip;
        this.memberCountry = memberCountry;
        this.memberEmail = memberEmail;
        this.memberHomePhone = memberHomePhone;
        this.memberCellPhone = memberCellPhone;
        this.memberEmergencyNumber = memberEmergencyNumber;
        this.memberSinceDate = memberSinceDate;
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
        this.lastFour = lastFour;
        this.rountingNumber = rountingNumber;
        this.creditCardType = creditCardType;
        this.paymentType = paymentType;
    }

    @Test
    public void storeMigratedData() {
        logger.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        getPage(UnoAppOrganizationPage.class)
                .store_migrated_data(clubAddress, clubCity, clubZip, clubPhone, memberName, memberAddress, memberCity, memberZip, memberCountry, memberEmail, memberHomePhone, memberCellPhone,
                        memberEmergencyNumber, memberSinceDate, subscriptionType, subscriptionAmount, lastFour, rountingNumber, creditCardType, paymentType);
    }

    @Test
    @Description("Create a new Organization")
    public void organizationAndLocationSelection() {
        logger.info("Create a new Organization and location and if already exist search for the existing organization");
        setTestResultOnXRay("A30TP-38470");

        if ((getPage(UnoAppOrganizationPage.class).check_club_number_exist(clubNumber)).equals(ORGANIZATION_NOT_EXIST)) {
            logger.info("org creation has started");
            getPage(UnoAppOrganizationPage.class)
                    .generate_org_name(ORGANIZATION_NAME)
                    .generate_org_url(ORGANIZATION_URL)
                    .generate_org_email(ORGANIZATION_EMAIL)
                    .create_organization(getTestData(MIGRATED_INI, ORGANIZATION_SECTION, ORGANIZATION_PHONE_NUMBER,
                            ORGANIZATION_NAME, ORGANIZATION_URL, ORGANIZATION_FIRST_NAME, ORGANIZATION_LAST_NAME,
                            ORGANIZATION_EMAIL, ORGANIZATION_ADD1, ORGANIZATION_ADD2,
                            ORGANIZATION_CITY, ORGANIZATION_STATE, ORGANIZATION_EMAIL,
                            ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP))
                    .verify_organization_created_with_migrated_data()
                    .store_migrated_org_created(clubNumber,
                            (getTestData(MIGRATED_INI, ORGANIZATION_SECTION, ORGANIZATION_NAME).get(ORGANIZATION_NAME)).toString());
            getPage(UnoAppLocationPage.class)
                    .generate_location_name(LOCATION_NAME)
                    .create_location(getTestData(MIGRATED_INI, LOCATION_SECTION, LOCATION_NAME,
                            LOCATION_PHONE_NUMBER, LOCATION_EMAIL, LOCATION_ADD1,
                            LOCATION_ADD2, LOCATION_CITY, LOCATION_ZIP, LOCATION_STATE,
                            LOCATION_TIMEZONE, LOCATION_ACCOUNT_HOLDER_NAME,
                            LOCATION_BANK_ROUTING_NUMBER))
                    .add_location_bank_details(getTestData(MIGRATED_INI, LOCATION_SECTION, LOCATION_ACCOUNT_HOLDER_NAME,
                            LOCATION_BANK_ROUTING_NUMBER))
                    .store_migrated_location_created(clubNumber,
                            (getTestData(MIGRATED_INI, LOCATION_SECTION, LOCATION_NAME).get(LOCATION_NAME)).toString());
            getPage(PaymentGatewayLoginPage.class)
                    .login_pg();
            getPage(CompanyTabPage.class)
                    .search_company(ABC_FINANCIAL_BILLING_SERVICES);
            getPage(MerchantAccountPage.class)
                    .create_merchant_account(getTestData(MIGRATED_INI, LOCATION_SECTION, LOCATION_NAME, REGIONS_BANK));
            getPage(PaymentGatewayLoginPage.class)
                    .payment_gateway_logout();
        } else {
            getPage(UnoAppDashBoardPage.class)
                    .search_organization_by_name(getTestData(MIGRATEDORGCREATED_INI, clubNumber, ORGANIZATION_NAME))
                    .click_business_link();
            getPage(UnoAppLocationPage.class)
                    .select_location(getTestData(MIGRATEDORGCREATED_INI, clubNumber, LOCATION_NAME));
        }
    }

    @Test
    @Description("Create Subscription with one member and fill all the details in information page")
    public void createSubsWithOneMember() {
        logger.info("Subscription creation has started with member creation");
        setTestResultOnXRay("A30TP-38469");
        getPage(UnoAppMemberPage.class)
                .create_member(getTestData(MIGRATED_INI, MEMBER_SECTION, MEMBER_ADDRESS,
                        MEMBER_FIRST_NAME, MEMBER_LAST_NAME, MEMBER_CITY, MEMBER_STATE, MEMBER_COUNTRY, MEMBER_ZIP_CODE,
                        MEMBER_NUMBER, MEMBER_PHONE_NUMBER));
    }

    @Test
    @Description("Create Installment auto-renewal to open Subscription with down payment where begin date is current and frequency Bi Weekly when payment is done by Bank account")
    public void createRenewToOpenSubscription() {
        logger.info("Create Installment auto-renewal to open Subscription with down payment where begin date is current and frequency Bi Weekly when payment is done by Bank account.");
        setTestResultOnXRay("A30TP-38468");
        if (!subscriptionType.contains(SUBSCRIPTION_TYPE_PAID_UP_FRONT)) {
            getPage(UnoAppSubscriptionPage.class)
                    .enter_subscription_with_migrated_data();
        }
    }

    @Test
    @Description("Enter Payment details for subscription")
    public void enterPaymentInformation() {
        setTestResultOnXRay("A30TP-38460");
        if (!subscriptionType.contains(SUBSCRIPTION_TYPE_PAID_UP_FRONT)) {
            logger.info("Entering the details of payment method");
            getPage(UnoAppSubscriptionPage.class)
                    .enter_payment_information_for_migrated_data(getTestData(MIGRATED_INI, PAYMENTMETHOD_SECTION,
                            SUB_ACCOUNT_HOLDER_NAME, SUB_BANK_ROUTING_NUMBER, SUB_BANK_ACCOUNT_NUMBER, SUB_CARD_NUMBER, SUB_CARD_HOLDER_NAME,
                            SUB_CARD_EXPIRY_YEAR, SUB_CARD_POSTAL_CODE, SUB_LAST_FOUR, SUB_CARD_TYPE, PAYMENT_METHOD_CARD, SUB_MASTERCARD_NUMBER))
                    .click_on_submit_btn();
        } else {
            logger.info("Skipping because subscription type is  > " + subscriptionType);
            throw new SkipException("Skipping because subscription type is  > " + subscriptionType);

        }
    }

    @Test
    @Description("Create Paid up front subscription if migrated user has subscription type as paid upfront")
    public void enterPaidUpFrontInformation() {
        setTestResultOnXRay("A30TP-38776");
        if (subscriptionType.contains(SUBSCRIPTION_TYPE_PAID_UP_FRONT)) {
            logger.info("Create Paid up front subscription");
            getPage(UnoAppSubscriptionPage.class)
                    .enter_subscription_type_data()
                    .click_on_submit_btn();
        } else {
            logger.info("Skipping because subscription type is  > " + subscriptionType);
            throw new SkipException("Skipping because subscription type is  > " + subscriptionType);
        }
    }

    @Test
    @Description("Verify the member is created successfully")
    public void verifyAndSelectMember() {
        logger.info("Verify the member created as migrated data  and select the migrated member created");
        setTestResultOnXRay("A30TP-38467");
        getPage(UnoAppSubscriptionPage.class)
                .verify_subscription_created();
        getPage(UnoAppMemberPage.class)
                .search_member(getTestData(MIGRATED_INI, MEMBER_SECTION, MEMBER_LAST_NAME))
                .select_member_migrated_created(getTestData(MIGRATED_INI, MEMBER_SECTION, MEMBER_LAST_NAME));
    }

    @Test
    @Description("Verifying subscription tab and its data displayed")
    public void verifySubscriptionTab() {
        logger.info("Verify that subscription tab for subscription created for a member");
        setTestResultOnXRay("A30TP-38457");
        getPage(UnoAppMemberDetailPage.class)
                .select_subscription_tab()
                .verify_subscription_type(getTestData(MIGRATED_INI, SUBSCRIPTION_SECTION, SUB_TYPE));
        if (!subscriptionType.contains(SUBSCRIPTION_TYPE_PAID_UP_FRONT)) {
            getPage(UnoAppMemberDetailPage.class)
                    .select_subscription_tab()
                    .verify_subscription_frequency(getTestData(MIGRATED_INI, SUBSCRIPTION_SECTION, SUB_PAY_FREQUENCY))
                    .verify_subscription_amenity(getTestData(MIGRATED_INI, SUBSCRIPTION_SECTION, PROFIT_CENTRE));
        }

    }

    @Test
    @Description("Verifying agreement tab and its data displayed")
    public void verifyAgreementTab() {
        logger.info("Verify that agreement tab for subscription created for a member");
        setTestResultOnXRay("A30TP-38444");
        getPage(UnoAppMemberDetailPage.class)
                .select_agreement_tab()
                .verify_agreement_information_presence(getTestData(UNOAPP_INI, MEMBER_SECTION, AGREEMENT_INFO))
                .verify_agreement_status_ok(getTestData(UNOAPP_INI, MEMBER_SECTION, AGREEMENT_STATUS));
    }

    @Test
    @Description("Verifying wallet tab and its data displayed")
    public void verifyWalletTab() {
        logger.info("Verify that wallet tab for subscription created for a member");
        setTestResultOnXRay("A30TP-38442");
        if (!subscriptionType.contains(SUBSCRIPTION_TYPE_PAID_UP_FRONT)) {
            getPage(UnoAppMemberDetailPage.class)
                    .click_payments_tab()
                    .verify_holder_name_migrated_data(getTestData(MIGRATED_INI, PAYMENTMETHOD_SECTION, PAYMENT_METHOD_BANK, SUB_ACCOUNT_HOLDER_NAME, SUB_CARD_HOLDER_NAME));
        }
    }

    @Test
    @Description("Verifying profile tab and its data displayed")
    public void verifyProfileTab() {
        logger.info("Verify that profile tab for subscription created for a member");
        setTestResultOnXRay("A30TP-38455");
        getPage(UnoAppMemberDetailPage.class)
                .click_profile_tab()
                .verify_personal_info_section_present(getTestData(UNOAPP_INI, MEMBER_SECTION, PROFILE_PERSONAL_INFO))
                .verify_contact_info_section_present(getTestData(UNOAPP_INI, MEMBER_SECTION, PROFILE_CONTACT_INFO));
    }

    @Test
    @Description("Verifying Notes tab and it should be blank when no notes are added")
    public void verifyNotesTab() {
        logger.info("Verify that notes tab for subscription created for a member");
        setTestResultOnXRay("A30TP-38453");
        getPage(UnoAppMemberDetailPage.class)
                .click_notes_tab()
                .verify_notes_tab_info(getTestData(UNOAPP_INI, MEMBER_SECTION, NOTES_INFO));
    }
}