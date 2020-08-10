package helper;

import java.time.format.DateTimeFormatter;

public final class AppConstants {
    public static final String CONFIG_INI = "config.ini";
    public static final String AUTHORIZATION_INI = "authorization.ini";

    public static final String SECONDLOCATION = "secondLocation";
    public static final String DEDUCTIONS = "Deductions";
    public static final String REIMBURSEMENT = "Reimbursements";
    public static final String ADDDEDUCTIONS = "Add Deduction";
    public static final String ADDREIMBURSEMENT = "Add Reimbursement";
    public static final String ORGWITHTWOSUBSCRIPTION = "organizationWithTwoSubscirption";
    public static final String invoiceVerificationSection = "INVOICEVERIFICATION";
    public static final String ORGANIZATION_SECTION = "ORGANIZATION";
    public static final String ORGANIZATION_NAME = "organizationName";
    public static final String ORGANIZATION_URL = "organizationUrl";
    public static final String ORGANIZATION_FIRST_NAME = "orgFirstName";
    public static final String ORGANIZATION_LAST_NAME = "orgLastName";
    public static final String ORGANIZATION_PHONE_NUMBER = "orgPhoneNumber";
    public static final String ORGANIZATION_EMAIL = "orgEmail";
    public static final String ORGANIZATION_ADD1 = "orgAdd1";
    public static final String ORGANIZATION_ADD2 = "orgAdd2";
    public static final String ORGANIZATION_CITY = "orgCity";
    public static final String ORGANIZATION_STATE = "orgState";
    public static final String ORGANIZATION_ZIP = "orgZip";
    public static final String ORGANIZATION_WEBSITE = "orgWebSite";
    public static final String ORGANIZATION_TIMEZONE = "orgTimeZone";
    public static final String emailDomain = "@qa4life.com";
    public static final String ORGANIZATION_NOT_EXIST = "null";
    //location section
    public static final String LOCATION_SECTION = "LOCATION";
    public static final String LOCATION_NAME = "locationName";
    public static final String LOCATION_PHONE_NUMBER = "phoneNumber";
    public static final String LOCATION_EMAIL = "email";
    public static final String LOCATION_ADD1 = "locAdd1";
    public static final String LOCATION_ADD2 = "locAdd2";
    public static final String LOCATION_CITY = "city";
    public static final String LOCATION_ZIP = "zip";
    public static final String LOCATION_STATE = "state";
    public static final String LOCATION_TIMEZONE = "timezone";
    public static final String LOCATION_ACCOUNT_HOLDER_NAME = "accountHolderName";
    public static final String LOCATION_BANK_ROUTING_NUMBER = "bankRoutingNumber";
    //Item section
    public static final String ITEM_SECTION = "ITEM";
    public static final String ITEM_TAX_CODE = "taxCode";
    public static final String ITEM_TAX_RATE = "taxRate";
    public static final String ITEM_CATEGORY_NAME = "categoryName";
    public static final String SECOND_ITEM_CATEGORY_NAME = "secondCategoryName";
    public static final String ITEM_NAME = "itemName";
    public static final String SECOND_ITEM_NAME = "secondItemName";
    public static final String ITEM_TYPE = "itemType";
    public static final String AUTO_PAY = "autoPay";
    // Member Section
    public static final String MEMBER_FIRST_NAME = "firstName";
    public static final String MEMBER_LAST_NAME = "lastName";
    public static final String MEMBER_MIDDLE_NAME = "lastName";
    public static final String MEMBER_SECTION = "MEMBER";
    public static final String MEMBER_ADDRESS = "memberAddress";
    public static final String MEMBER_CITY = "city";
    public static final String MEMBER_STATE = "state";
    public static final String MEMBER_COUNTRY = "country";
    public static final String MEMBER_ZIP_CODE = "zipCode";
    public static final String MEMBER_NUMBER = "number";
    public static final String MEMBER_PHONE_NUMBER = "phoneNumber";
    public static final String AGREEMENT_INFO = "agreementInformation";
    public static final String AGREEMENT_STATUS = "agreementStatus";
    public static final String MEMBER_NOTES = "primaryMemberNotes";
    public static final String FREEZE_SECTION = "Freeze";
    public static final String SECOND_MEMBER = "secondMember";
    //Subscription
    public static final String SUB_DOWNPAYMENT = "downPayment";
    public static final String SUB_TYPE = "subType";
    public static final String SUB_DUE_DATE = "dueDate";
    public static final String SUB_BEGIN_DATE = "beginDate";
    public static final String SUB_EXPIRY_DATE = "expiryDate";
    public static final String SUB_RENEWAL_DATE = "textRenewalDate";
    public static final String SUB_PAY_FREQUENCY = "payFrequency";
    public static final String SUB_RENEWAL_FREQUENCY = "renewalFreq";
    public static final String SUB_PAY_AMOUNT = "payAmount";
    public static final String SUB_STMT_AMOUNT = "stmtAmount";
    public static final String SUB_DURATION = "textDuration";
    public static final String SUB_RENEWAL_TYPE = "renewalType";
    public static final String MULTIPLE_SUBS_TYPE = "subscriptionType";
    public static final String MULTIPLE_BEGIN_DATE = "beginDateMultiple";
    public static final String MULTIPLE_EXPIRATION_DATE = "expirationDateMultiple";
    public static final String MULTIPLE_FIRST_DUE_DATE = "dueDateMultiple";
    public static final String MULTIPLE_PAY_FREQUENCY = "payFrequencyMultiple";
    public static final String MULTIPLE_NO_OF_PAYMENTS = "noOfpaymentsMultiple";
    public static final String PROFIT_CENTRE = "profitCenter";

    //subscription bank payment method
    public static final String SUB_ACCOUNT_HOLDER_NAME = "accountHolderName";
    public static final String SUB_BANK_ROUTING_NUMBER = "bankRouting";
    public static final String SUB_BANK_ACCOUNT_NUMBER = "accountNo";
    public static final String PAYMENT_METHOD_BANK = "payMethod";

    //paid up front
    public static final String SUBSCRIPTION_TYPE_PAID_UP_FRONT = "Paid Up Front ";

    public static final String PAYMENT_METHOD_STATUS = "paymentMethodTransferred";

    //Migrated
    public static final String SUB_LAST_FOUR = "lastFour";
    public static final String SUB_MASTERCARD_NUMBER = "masterCardNumber";
    public static final String SUB_CARD_TYPE = "creditCardType";
    //subscription card payment method
    public static final String SUB_CARD_NUMBER = "creditCardNumber";
    public static final String SUB_CARD_HOLDER_NAME = "cardHolderName";
    public static final String SUB_CARD_EXPIRY_YEAR = "expYear";
    public static final String SUB_CARD_POSTAL_CODE = "postalCode";
    public static final String PAYMENT_METHOD_CARD = "payMethod";
    public static final String PAYMENT_METHOD_AMEX = "payMethodAmex";
    public static final String SUBSCRIPTION_INI = "subscription.ini";
    public static final String MIGRATEDORGCREATED_INI = "migratedOrgCreationDone.ini";
    public static final String UNOAPP_INI = "UnoApp.ini";
    public static final String ORGID_INI = "OrgId.ini";
    public static final String MERCHANTID_INI = "MerchantId.ini";
    public static final String ABC = "ABC Fee";
    public static final String VISA = "Visa";
    public static final String CC_CARGE_BACK_FEE = "CC Charge Back Fee";
    public static final String MASTERCARD = "Master Card";
    public static final String UNAUTHORIZED_FEE = "Unauthorized Fee";
    public static final String STATEMENT = "Statement";
    public static final String AMEX = "American Express";
    public static final String DISCOVER = "Discover";
    public static final String PROFILE_PERSONAL_INFO = "profilePersonalInfo";
    public static final String PROFILE_CONTACT_INFO = "contactInformation";
    public static final String NOTES_INFO = "An Item";
    public static final String MIGRATED_INI = "storemigrateddata.ini";
    //AccountDetails static data
    public static final String DEDUCTION_AMOUNT_ZERO = "$0.00";
    public static final String REIMBURSEMENT_AMOUNT_ZERO = "$0.00";
    // numberic numbers
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";
    public static final String TEN = "10";
    public static final int SIXTY_THOUSAND = 60000;

    //Boolean value
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    //Data Migration
    public static final String DATA_MIGRATION_INI = "datamigration.ini";
    public static final String REGEX = "[\\[\\](){}]";
    public static final String CLUB_DETAILS = "CLUB_DETAILS";
    public static final String MEMBER_DETAILS = "MEMBER_DETAILS";
    public static final String MEMBER_RENEW_DETAILS = "MEMBER_RENEW_DETAILS";
    public static final String AGREEMENT_DETAILS = "AGREEMENT_DETAILS";
    public static final String SUBSCRIPTION_DETAILS = "SUBSCRIPTION_DETAILS";
    public static final String PAYMENT_METHOD_DETAILS = "PAYMENT_METHOD_DETAILS";
    public static final String ADDITIONAL_DETAILS_MEMBER = "ADDITIONAL_DETAILS_MEMBER";
    public static final String SECONDARY_PAYMENT_METHOD_DETAILS = "SECONDARY_PAYMENT_METHOD_DETAILS";
    public static final String DUES = "DUES";
    public static final String ENHANCE = "ENHANCE";
    public static final String MAINTENANCE_FEE = "MAINTENANCE FEE";
    public static final String MONTHLY_RENTAL_REAL_ESTATE = "MONTHLY RENTAL REAL ESTATE";
    public static final String CHARGE_BACK = "CHARGE BACK";
    public static final String CHARGEBACK="CHARGEBACK";
    public static final String UNASSIGNED = "UNASSIGNED";
    public static final String INSTALLATION_FEE = "INSTALLATION FEE";
    public static final String AUTO_RENEW_TO_OPEN = "Auto-Renew to Open";
    public static final String NON_RENEWAL_AGREEMENT = "Non-Renewal Agreement";
    public static final String PAID_UP_FRONT = "Paid Up Front";
    public static final String OPEN = "Open";
    public static final String INSTALLMENT = "Installment";
    public static final String INSTALLMENT_COUPON = "InstallmentCoupon";
    public static final String OPEN_END = "Open-End";
    public static final String AUTO_RENEW_TO_TERM = "Auto-Renew to Term";
    public static final String ACTIVE = "Active";
    public static final String CREDIT_CARD = "Credit Card";
    public static final String BANK_ACCOUNT = "EF";
    public static final String NO_BILLING_INFO = "No Billing Information";
    public static final String CANCEL_POLICY_PDF_URL = "https://client2.abcfinancial.com/abcLiveDocsTemp/_clients/cancel_policies/";
    public static final String EXPIRATION_YEAR_PREFIX = "20";
    public static final String CREDIT_CARD_TYPE = "Credit";
    public static final String MASTER_CARD = "Masterсard";
    public static final String BANK_ACCOUNT_PAYMENT_METHOD = "Bank Account";
    public static final String EFT = "EFT";
    public static final String BAD_ADDRESS = "Bad Address";

    public static final String FEE_DETAILS = "FEE DETAILS";
    //DATA MIGRATION INI KEY
    public static final String LATE_FEE_COUNT = "lateFeeCount";
    public static final String LATE_FEE_AMOUNT = "lateFeeAmount";
    public static final String SERVICE_FEE_AMOUNT = "serviceFeeAmount";
    public static final String SERVICE_FEE_COUNT = "serviceFeeCount";
    public static final String DEBIT="Debit";
    public static final String CLUB_ADDRESS ="address";
    public static final String CLUB_CITY ="city";
    public static final String CLUB_ZIP ="zip";
    public static final String CLUB_PHONE ="phone";
    public static final String CLUB_NUMBER="clubNumber";
    public static final String TEST_CASE_ID="testCaseId";
    public static final String CLUB_NAME="clubName";
    public static final String AGREEMENT_NUMBER="agreementNumber";
    public static final String SECONDARY_MEMBER_NAME="secondaryMemberName";
    public static final String SUBSCRIPTION_CANCEL_STATUS="subscriptionCancelStatus";
    public static final String SUBSCRIPTION_FREEZE_STATUS="subscriptionFreezeStatus";
    public static final String MEMBER_STATUS="memberStatus";
    public static final String MEMBER_HOME_PHONE="memberHomePhone";
    public static final String MEMBER_WORK_PHONE="memberWorkPhone";
    public static final String MEMBER_CELL_PHONE="memberCellPhone";
    public static final String MEMBER_EMER_PHONE="memberEmerPhone";
    public static final String MEMBER_SINCE_DATE="memberSinceDate";
    public static final String MEMBERSHIP_TYPE="membershipType";
    public static final String CONTRACT_BEGAN_DATE="contractBeganDate";
    public static final String NEXT_DUE_DATE="nextDueDate";
    public static final String PAST_DUE_DATE="pastDueDte";
    public static final String DUE_AMOUNT="dueAmount";
    public static final String SUBSCRIPTION_TYPE="subscriptionType";
    public static final String SUBSCRIPTION_BEGAN_DATE="subscriptionBeganDate";
    public static final String SUBSCRIPTION_DUE_AMOUNT="subscriptionDueAmount";
    public static final String SUBSCRIPTION_NEXT_DUE_DATE ="subscriptionNextDueDate";
    public static final String SUBSCRIPTION_EXPIRATION_DATE="subscriptionExpirationDate";
    public static final String SUBSCRIPTION_ITEM="subscriptionItem";
    public static final String PAYMENT_TYPE="paymentType";
    public static final String PAYOR_NAME="payorName";
    public static final String ROUTING_NUMBER="routingNumber";
    public static final String LAST_FOUR="lastFour";
    public static final String BANK_ACCOUNT_TYPE="bankAccountType";
    public static final String BANK_LAST_FOUR="bankLastFour";
    public static final String BANK_ROUTING_NUMBER="bankRoutingNumber";
    public static final String BANK_PAYMENT_TYPE="bankPaymentType";
    public static final String EXPIRATION_DATE="expirationDate";
    public static final String CREDIT_CARDS_TYPE="creditCardType";
    public static final String CREDIT_CARD_LAST_FOUR="creditCardlastFour";
    public static final String CREDIT_CARD_EXPIRATION_DATE="creditCardExpirationDate";
    public static final String CREDIT_CARD_PAYMENT_TYPE="creditCardPaymentType";
    public static final String ENHANCE_FEE="ENHANCEFEE";
    public static final String RENT="RENT";
    public static final String PROFITCTR1="PROFITCTR1";
    public static final String MEMBER_NAME="memberName";
    public static final String FROZEN_ACCOUNT="FROZEN ACCOUNT";
    public static final String MEMBER_EMAIL="memberEmail";
    public static final String DRIVING_LICENCE="drivingLicense";
    public static final String SSN="SSN";
    public static final String BIRTH_DATE="birthDate";
    public static final String EMPLOYER="employer";
    public static final String OCCUPATION="occupation";
    public static final String RENEWAL_FLAG="renewalFlag";
    public static final String RENEWAL_TYPE="renewalType";
    public static final String RENEWAL_DATE="renewalDate";
    public static final String RENEWAL_AMOUNT="renewalAmount";
    public static final String NO_OF_PAYMENTS="noOfPayments";
    public static final String SECONDARY_BANK_ACCOUNT_NUMBER="secondaryBankAccountNumber";
    public static final String ACCOUNT_HOLDER="accountHolder";
    public static final String SECONDARY_ROUTING_NUMBER="secondaryRoutingNumber";
    public static final String SECONDARY_CREDIT_CARD_NUMBER="secondaryCreditCardNumber";
    public static final String SECONDARY_PAYMENT_METHOD_PRESENCE="secondaryPaymentMethodPresence";
    public static final String M = "Mastercard";
    public static final String D = "Discover";
    public static final String CASH_STORAGE = "CASH_STORAGE";
    public static final String CASH_STORAGE_INI = "cashstorage.ini";
    public static final String GREEN_SCREEN_INI = "greenscreen.ini";
    public static final String CASH_STORAGE_FEE = "Cash Storage Fees";
    public static final String CLUB_BEGIN_DATE = "Club_Begin_Date";
    public static final String MEMBER_BEGIN_DATE = "Member_Begin_Date";
    public static final String CASH_CHARGE = "Cash_Charge";
    public static final String TEST_STATUS = "Status";
    public static final String AUTO_JOB_FINISH_NOTIFY = "autojobfinishnotify.ini";
    public static final String OUTPUT_INI = "output.ini";
    public static final String ERROR_MESSAGE = "Message";
    public static final String CLUB_WITH_MEMBER_NUMBER = "clubWithMemberNumber";
    public static final String MEMBER_TEST_CASE_ID = "memberTestCaseId";
    //Notes
    public static final String addNotesCodeName = "ARN";
    //Payment gateway
    public static final String ABC_FINANCIAL_BILLING_SERVICES = "ABC Financial Billing Services";
    public static final String REGIONS_BANK = "regionsBank";
    //MIGRATED SUBSCRIPTION
    public static final String SUBSCRIPTION_SECTION = "SUBSCRIPTION";
    public static final String PAYMENTMETHOD_SECTION = "PAYMENTMETHOD";

    /*
    Freeze details
    * */
    public static final String FREEZE_TYPE = "freezeType";
    public static final String FREEZE_START_DATE = "startDate";
    public static final String FREEZE_END_DATE = "endDate";
    public static final String FREEZE_STATUS = "freezeStatus";

    /*
    Xray Detail
     */

    public static final String XRAY_CLIENT_ID = "3DF7C9182BA84BA7A7D69A7F7BD9A918";
    public static final String XRAY_CLIENT_ID_KEY = "client_id";
    public static final String XRAY_CLIENT_SECRET_ID = "63dba01df2b533c1a30d0c308e5a499bc5ba967f860b2df92a911b90d3532ade";
    public static final String XRAY_CLIENT_SECRET_ID_Key = "client_secret";
    public static final String TESTNG_RESULT_XML = "/test-output/new/testng-results.xml";
    public static final String XRAY_AUTHORIZATION_URL = "http://xray.cloud.xpand-it.com/api/v1/authenticate";
    public static final String XRAY_TESTNG_EXECUTION_CREATION_URL = "https://xray.cloud.xpand-it.com/api/v1/import/execution/testng?projectKey=A30TP";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_XML = "text/xml";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ALLURE_RESULTS = "/target/allure-results";
    public static final String ALLURE_REPORT = "target/allure-report";
    public static final String PROPERTY_INI = "property.ini";
    public static final String ENV = "env";
    public static final String ENVIRONMENT = "Environment";
    public static final String BROWSER = "Browser";
    public static final String OPERATING_SYSTEM = "OperatingSystem";
    public static final String BROWSER_LAUNCH = "browserLaunch";
    public static final String OS = "os";
    public static final String SRC_TO_RESOURCE_PATH = "/src/test/resources/";
    public static final String CASH_CHARGE_AMOUNT = "cashChargeAmount";
    public static final String CASH_CHARGE_TEST_CASE1 = "A30TP-45133";
    public static final String CASH_CHARGE_TEST_CASE2 = "A30TP-45132";
    public static final String CASH_CHARGE_TEXT_FILE = "CashStorage.txt";
    public static final String CASH_CHARGE_INI = "CashStorageFile.ini";


    //Common Date FormatEnvironment
    public static final String MM_DD_YYYY_HH_MM_FORMAT = "MM-dd-yyyy hh:mm";
    public static final String MM_DD_YYYY_WITH_SLASH = "MM/dd/yyyy";
    public static final String MM_DD_YYYY = "MM-dd-yyyy";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final int RANDOM_UPPER_LIMIT = 30;
    // JIRA
    public static final String JIRA="JIRA";
    public static final String JIRA_USERNAME="jiraUserName";
    public static final String JIRA_PASSWORD="jiraPassword";
    public static final String JIRA_URL="jiraUrl";
    public static final String JIRA_BASE_PATH="/rest/api/2/issue/{issueIdOrKey}";
    public static final String FIELDS="fields";
    public static final String STATUS="status";
    public static final String ISSUE_KEY="issueIdOrKey";
    public static final String STATUS_NAME="fields.status.name";


    //Color
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLUE_UNDERLINED = "\033[4;34m";

    //Ares(Live Reporting)
    public static final String CREATERUNIDURL="CreateRunIDUrl_ARES";
    public static final String ADDMODULEDATAURL="AddModuleDataUrl_ARES";
    public static final String TESTDETAILSURL="TestDetailsUrl_ARES";
    public static final String PROJECTNAME="ProjectName_ARES";
    public static final String PROJECTID="ProjectKey_ARES";
    public static final String TOKEN="UserToken_ARES";
    public static final String PROJECTUSER="ProjectUser_ARES";
    public static final String TESTDEVICE="TestDevice_ARES";
    public static final String WSNAME="Ws_Name_ARES";
    public static final String PRODUCTNAME="ProductName_ARES";
    public static final String POSTRESULTTOARESDASHBOARD="postResultsToARESdashboard";
    public static final String USER_TOKEN="usertoken";
    public static final String PROJECT_ID="ProjectId";
    public static final String CONTENTTYPE="Content-type";
    public static final String HEADER="application/json";
    public static final String MODEOFEXECUTION="null";
    public static final String OS_NAME="os.name";
}