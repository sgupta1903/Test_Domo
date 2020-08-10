package pagetest.datamigrationpage;


import config.EnvProperty;
import datastore.*;
import helper.AppConstants;
import helper.DataBaseHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pagetest.businessapppage.AbcCommonAbstractPage;


import java.time.Month;
import java.util.List;
import java.util.Map;
//Created By Monika Phoughat
//Date : 20-09-2019

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static util.UtilityGeneric.convertObjectIntoString;

public class WalletPage extends AbcCommonAbstractPage<WalletPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.DATA_MIGRATION_INI);
    private String expMonth;
    private By memberLink = By.xpath("//a[@href='/uno-app/app/member-management']/i");
    private By searchIcon = By.xpath("//div[@id='searchBy']//div[contains(@class,'FormSelect__control css')]");
    private By phoneNumber = By.xpath("//div[contains(text(),'Phone Number')]");
    private By search = By.xpath("//input[@id='searchInput']");
    private By profileTab = By.xpath("//div[contains(text(),'Profile')]");
    private By email = By.xpath("//input[@id='email']");
    private By memberCount = By.xpath("//ul[contains(@class,'ui-infinite-list list')]/li");
    private By backIcon = By.xpath("//i[contains(@class,'icon-arrow-left-thin ui-icon')]");
    private By paymentTab = By.xpath("//i[@data-abc-id='paymentsTitleIcon']");
    private By paymentMethodList = By.xpath("//strong[contains(text(),'Default')]");
    private By edit = By.xpath("//i[@data-abc-id='paymentInfoHeaderButtonIcon']");
    private By type = By.xpath("//time[@data-abc-id='type']");
    private By accountHolder = By.xpath("//input[@id='accountHolderName']");
    protected String member = "//ul[contains(@class,'ui-infinite-list list')]/li[%s]";
    //Bank Account
    private By routingNumber = By.xpath("//input[@id='routingNumber']");
    private By accountNumberLastFour = By.xpath("//input[@id='accountNumberLastFour']");
    private By bankAccountType = By.xpath("//input[@id='bankAccountTypeInput']");
    //Credit Card
    private By creditCardNumberLastFour = By.xpath("//input[@id='creditCardNumberLastFour']");
    private By expirationYear = By.xpath("//div//input[@id='expirationYearInput']");
    private By zip = By.xpath("//input[@id='postalCode']");
    private By creditCardType = By.xpath("//div//input[@id='creditCardTypeInput']");
    private By creditType = By.xpath("//time[@data-abc-id='lastFour']");
    private By expiryDate = By.xpath("//td[@data-abc-id='expirationDate']");
    private By accountHolderOnRow = By.xpath("//strong[@data-abc-id='cardLabelText']//following::td[1]");

    private By closeButton = By.xpath("//i[@data-abc-id='closeDrawerButtonIcon']");
    //private By closeButton = By.xpath("//button[@data-abc-id='closeDrawerButton']");
    private By statementPM = By.xpath("//span[contains(text(),'Statement')]");
    private By secondaryPM = By.xpath("//tr[@data-abc-id='paymentMethodsListRow'][3]");
    private By bankAccount = By.xpath("//input[contains(@placeholder,'Bank Account')]");
    private By paymentMethodType = By.xpath("//div//input[@id='modeInput']");
    private By expirationMonth = By.xpath("//div//input[@id='expirationMonthInput']");
    private By accountHolderName = By.xpath("//*[@data-abc-id='cardLabelText']//following::td[1]");
    private By secondaryPaymentMethod = By.xpath("//*[@data-abc-id='cardLabelText']//following::td[12]");
    private By secondaryAccountHolder = By.xpath("//*[@data-abc-id='cardLabelText']//following::td[13]");
    private By secondaryExpirationDate = By.xpath("//*[@data-abc-id='cardLabelText']//following::td[15]");
    private By paymentDetailsButton = By.xpath("//th[contains(text(),'Status')]//following::button[1]");
    private By lateFeeTransaction = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Late')]");
    private By serviceFeeTransaction = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Service')]");
    private By lateFeeTransactionAmount = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Late')]//following::td[4]");
    private By serviceFeeTransactionAmount = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Service')]//following::td[4]");
    private By lateFeeDebitType = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Late')]//following::td[3]");
    private By serviceFeeDebitType = By.xpath("//table//td[@data-abc-id='subType'][contains(text(),'Service')]//following::td[3]");
    private By lateFeeZeroAmount = By.xpath("//td[contains(text(),'Late Fee:')]//following::td[1]");
    private By paymentMethodZipCode = By.xpath("//input[@id='postalCode']");
    private String attributeValue = "paymentMethodsListRow";
    private String secondaryPaymentRow = "//tr[@data-abc-id='%s']/td/span[text()='%s']//../../td[text()='%s']//../../td[text()='%s']";

    public By get_secondary_pay_row_xpath(String attributeValue, String paymentMethod, String accountHolder, String expirationDate) {
        return By.xpath(String.format(secondaryPaymentRow, attributeValue, paymentMethod, accountHolder, expirationDate));
    }

    @Step("Verifying Deduction/Reimbursement item")
    public WalletPage verify_secondary_pay_row_value(String paymentMethod, String accountHolder, String expirationDate) {
        verify(visibilityOfAllElementsLocatedBy(get_secondary_pay_row_xpath(attributeValue, paymentMethod, accountHolder, expirationDate)));
        return me();
    }


    @Step("Click on Wallet Tab")
    public WalletPage click_wallet_Tab() {
        logger.info("Click on Wallet Tab");
        verify(elementToBeClickable(paymentTab), 60, 2000);
        wait_until(2);
        return click(paymentTab);
    }

    @Step("Click on Payment Method List")
    public WalletPage click_payment_method_list() {
        logger.info("Click on Payment Method List");
        verify(elementToBeClickable(paymentMethodList), 60, 2000);
        wait_until(2);
        return click(paymentMethodList);
    }

    @Step("Search member")
    public WalletPage search_member(String name) {
        logger.info("Searching Member");
        click(searchIcon);
        click(phoneNumber);
        enter(search, name);
        wait_until(2);
        return me();
    }

    @Step("Get member count")
    public int get_member_count() {
        int memberCount = find_element_count(this.memberCount);
        return memberCount;
    }

    public By get_member(String no) {
        return By.xpath(String.format(member, no));
    }

    @Step("Clicking on Member Profile Tab")
    public WalletPage click_profile_tab() {
        logger.info("Clicking on Member Profile Tab");
        verify(elementToBeClickable(profileTab), 60, 2000);
        return click(profileTab);
    }

    @Step("Clicking on Searched Member")
    public WalletPage select_member(String email) {
        logger.info("Clicking on Searched Member");
        Boolean memberFind = true;
        int memberNo = get_member_count() - 1;
        for (int i = 1; i <= memberNo; i++) {
            if (is_element_exist(backIcon)) {
                click(backIcon);
            }
            verify(elementToBeClickable(get_member(String.valueOf(i))), 60, 2000);
            wait_until(3);
            click(get_member(String.valueOf(i)));
            wait_until(1);
            click_profile_tab();
            wait_until(1);
            if (email.equals(find_element_value(this.email))) {
                if (!memberFind) {
                    memberFind = true;
                }
                break;
            } else {
                memberFind = false;
            }
        }
        if (!memberFind) {
            Assert.assertTrue(false);

        }
        return me();
    }

    @Step("Clicking on Edit Section for Wallet Tab")
    public WalletPage click_wallet_edit_btn() {
        logger.info("Clicking on Edit Section for Wallet Tab");
        verify(elementToBeClickable(edit), 60, 2000);
        return click(edit);
    }

    public String get_payment_method() {
        return find_element_text(this.type);
    }

    @Step("Verifying Account Holder Name on Payment method row")
    public WalletPage verify_account_holder_name(String accountHolderName) {
        logger.info("Verifying Account Holder Name on Payment method row");
        if(is_text_present_on_page(accountHolderName)){
            logger.info("Account holder present on page");
        }
        else {
            Assert.assertTrue(false,accountHolderName + " not present on page");
        }
        return me();
    }

    @Step("Verifying Account Holder Name on Payment method row")
    public WalletPage verify_secondary_account_holder_name(String expectedAccountHolderName) {
        logger.info("Verifying Account Holder Name on Payment method row");
        verify_value_matches(find_element_text(this.secondaryAccountHolder), expectedAccountHolderName);
        return me();
    }

    @Step("Verifying Routing Number")
    public WalletPage verify_routing_number(String routingNumber) {
        logger.info("Verifying Routing Number");
        String actualRoutingNumber = find_element_attribute_value(this.routingNumber, "value");
        verify_value_matches(actualRoutingNumber, routingNumber);
        return me();
    }


    @Step("Verifying Bank Account Type")
    public WalletPage verify_bank_account_type(String bankAccountType) {
        logger.info("Verifying Bank Account Type");
        String actualBankAccountType = find_element_attribute_value(this.bankAccountType, "placeholder");
        verify_value_matches(actualBankAccountType, bankAccountType);
        return me();
    }

    @Step("Verifying Account Holder Last Four Digits")
    public WalletPage verify_account_number_last_four(String accountNumberLastFour) {
        logger.info("Verifying Account Holder Last Four Digits");
        String actualLastFour = find_element_attribute_value(this.accountNumberLastFour, "value");
        verify_value_matches(actualLastFour, accountNumberLastFour);
        return me();
    }

    @Step("Verifying Credit Card Number Last Four Digits")
    public WalletPage verify_credit_card_last_four(String creditCardNumberLastFour) {
        logger.info("Verifying Credit Card Number Last Four Digits");
        verify_element_by_value(this.creditCardNumberLastFour, creditCardNumberLastFour);
        return me();
    }

    @Step("Verifying Expiration Year")
    public WalletPage verify_expiration_year(String expirationYear) {
        logger.info("Verifying Expiration Year");
        String actualExpirationYear = find_element_attribute_value(this.expirationYear, "placeholder");
        verify_value_matches(actualExpirationYear, expirationYear);
        return me();
    }

    @Step("Verifying Expiration Month")
    public WalletPage verify_expiration_month(String expirationMonth) {
        logger.info("Verifying Expiration Month");
        String actualExpirationMonth = find_element_attribute_value(this.expirationMonth, "placeholder").toLowerCase();
        verify_value_matches(actualExpirationMonth, expirationMonth);
        return me();
    }

    @Step("Verifying Payment Method Type")
    public WalletPage verify_payment_method_type(String paymentMethodType) {
        logger.info("Verifying Payment Method Type");
        String actualPaymentMethodType = find_element_attribute_value(this.paymentMethodType, "placeholder");
        verify_value_matches(actualPaymentMethodType, paymentMethodType);
        return me();
    }

    @Step("Verifying Zip code")
    public WalletPage verify_zip(String zip) {
        logger.info("Verifying Zip");
        verify_element_by_value(this.zip, zip);
        return me();
    }

    @Step("Verifying Zip code")
    public WalletPage verify_payment_method_zip_code(StoreMemberDetails storeMemberDetails) {
        logger.info("Verifying Zip code");
        if (storeMemberDetails.getMemberAddress().equalsIgnoreCase(BAD_ADDRESS)) {
            logger.info("Bad Address");
        } else
            verify_value_matches(find_element_value(paymentMethodZipCode), storeMemberDetails.getMemberZipCode().substring(0, 5));
        return me();
    }

    @Step("Verifying Credit Card Type")
    public WalletPage verify_credit_card_type(String creditCardType) {
        logger.info("Verifying Credit Card Type");
        String actualCreditCardType = find_element_attribute_value(this.creditCardType, "placeholder");
        verify_value_matches(actualCreditCardType, creditCardType);
        return me();
    }

    @Step("Verifying Credit Type")
    public WalletPage verify_credit_type(String creditType) {
        logger.info("Verifying Credit Type");
        is_text_present_on_page(creditType.toLowerCase());
        return me();
    }

    @Step("Verifying Credit Type with last four digit")
    public WalletPage verify_credit_type_with_last_four_digit(String creditType) {
        logger.info("Verifying Credit Type with last four digit");

        is_text_present_on_page(creditType.toLowerCase());
        return me();
    }

    @Step("Verifying Credit Type with last four digit")
    public WalletPage verify_secondary_credit_type_with_last_four_digit(String expectedTypeWithLastFour) {
        logger.info("Verifying Credit Type with last four digit");
        verify_value_matches(find_element_text(secondaryPaymentMethod), expectedTypeWithLastFour);
        return me();
    }

    @Step("Verifying Expiration Details")
    public WalletPage verify_expiration_details(String expiration) {
        logger.info("Verifying Expiration Details");
        wait_until(2);
        String expiryDate = find_element_text(this.expiryDate).substring(0, 2) + "/" + find_element_text(this.expiryDate).substring(5, 7);
        verify_value_matches(expiryDate, expiration);
        return me();
    }

    @Step("Verifying Secondary Payment method expiration date")
    public WalletPage verify_secondary_payment_method_expiration_date(String expectedExpirationDate) {
        logger.info("Verifying Secondary Payment method expiration date");
        wait_until(2);
        verify_value_matches(find_element_text(this.secondaryExpirationDate).substring(0, 2) + "/" + find_element_text(this.secondaryExpirationDate).substring(5, 7), expectedExpirationDate);
        return me();
    }

    @Step("Clicking on Wallet Close Button")
    public WalletPage click_close_button() {
        logger.info("Clicking on Wallet Close Button");
        verify(elementToBeClickable(closeButton), 60, 2000);
        wait_until(2);
        return click(closeButton);
    }

    @Step("Verifying Member Payment Method Statement in the UNO Application")
    public WalletPage verify_statement_payment_method() {
        logger.info("Verifying Member Payment Method Statement in the UNO Application");
        // click_wallet_Tab();
        verify_element_by_text(statementPM, "Statement");
        return me();
    }

    @Step("Verifying Secondary Payment Method  in the UNO Application")
    public WalletPage verify_secondary_payment_method(StoreSecondaryPaymentMethodDetails storeSecondaryPaymentMethodDetails) {
        logger.info("Verifying Secondary Payment Method  in the UNO Application");
        if (is_element_exist(secondaryPM)) {
            click(secondaryPM);
            wait_until(1);
            if (is_element_exist(bankAccount)) {
                verify_all(
                        () -> verify_account_number_last_four(storeSecondaryPaymentMethodDetails.getSecondaryBankAccountNumber()),
                        () -> verify_account_holder_name(storeSecondaryPaymentMethodDetails.getSecondaryAccountHolder()),
                        () -> verify_routing_number(storeSecondaryPaymentMethodDetails.getSecondaryRoutingNumber())
                );
                click_close_button();
                verify_all(
                        () -> verify_secondary_credit_type_with_last_four_digit(BANK_ACCOUNT_PAYMENT_METHOD + " " + "(" + storeSecondaryPaymentMethodDetails.getSecondaryBankAccountNumber() + ")"),
                        () -> verify_secondary_account_holder_name(storeSecondaryPaymentMethodDetails.getSecondaryAccountHolder())
                );

            } else {
                String[] expirationDateWithYear = storeSecondaryPaymentMethodDetails.getSecondaryCreditCardExpirationDate().split("/");
                String expYear = EXPIRATION_YEAR_PREFIX + expirationDateWithYear[1];
                int expMonth = Integer.parseInt(expirationDateWithYear[0]);

                verify_all(
                        () -> verify_credit_card_last_four(storeSecondaryPaymentMethodDetails.getSecondaryCreditCardNumber()),
                        () -> verify_account_holder_name(storeSecondaryPaymentMethodDetails.getSecondaryAccountHolder()),
                        () -> verify_expiration_year(expYear),
                        () -> verify_credit_card_type(CREDIT_CARD_TYPE),
                        () -> verify_expiration_month(Month.of(expMonth).name().toLowerCase())
                );
                click_close_button();
                String creditCardType = storeSecondaryPaymentMethodDetails.getSecondaryCreditCardType();
                switch (creditCardType) {
                    case "V":
                        creditCardType = VISA;
                        break;
                    case "A":
                        creditCardType = AMEX;
                        break;
                    case "D":
                        creditCardType = DISCOVER;
                        break;
                    case "M":
                        creditCardType = MASTER_CARD;
                }
                String finalCreditCardType = creditCardType;
                verify_all(
                        () -> verify_secondary_credit_type_with_last_four_digit(finalCreditCardType + " " + "(" + storeSecondaryPaymentMethodDetails.getSecondaryCreditCardNumber() + ")"),
                        () -> verify_secondary_payment_method_expiration_date(storeSecondaryPaymentMethodDetails.getSecondaryCreditCardExpirationDate()),
                        () -> verify_secondary_account_holder_name(storeSecondaryPaymentMethodDetails.getSecondaryAccountHolder())
                );
                //verify_secondary_pay_row_value(finalCreditCardType+ " "+"("+storeSecondaryPaymentMethodDetails.getSecondaryCreditCardNumber()+")",storeSecondaryPaymentMethodDetails.getSecondaryAccountHolder(),expirationDateWithYear[0] + "/" + expYear);
            }

        }
        return me();
    }

    @Step("Verifying Wallet Details from OBC UI to Uno APP")
    public WalletPage verify_wallet_details_obc(StorePaymentMethodDetails storePaymentMethodDetails, StoreMemberDetails storeMemberDetails) {
        logger.info("Verifying Wallet Details from OBC UI to Uno APP");
        if (storePaymentMethodDetails.getPaymentType().equalsIgnoreCase("No Billing Information")) {
            logger.info("Biling information is not available");
            click_wallet_Tab();
        } else {
            click_wallet_Tab();
            String payment_method = storePaymentMethodDetails.getPaymentType();
            if (payment_method.equals("EF")) {
                click_payment_method_list();

                try {
                    verify_all(
                            () -> verify_routing_number(storePaymentMethodDetails.getRoutingNumber()),
                            () -> verify_account_number_last_four(storePaymentMethodDetails.getLastFour()),
                            () -> verify_bank_account_type(storePaymentMethodDetails.getBankAccountType().trim()),
                            () -> verify_account_holder_name(storePaymentMethodDetails.getPayorName())
                    );
                }
                finally {
                    storePaymentMethodDetails.setPayorPGName(find_element_value(accountHolder));
                    logger.info("Payor PG Name: " + storePaymentMethodDetails.getPayorPGName());
                    click_close_button();
                }

            } else if (payment_method.equals(CREDIT_CARD)) {

                String[] expirationDateWithYear = storePaymentMethodDetails.getExpirationDate().split("/");
                String expYear = EXPIRATION_YEAR_PREFIX + expirationDateWithYear[1];
                int expMonth = Integer.parseInt(expirationDateWithYear[0]);

                try {
                    verify_all(
                            () -> verify_credit_type_with_last_four_digit(storePaymentMethodDetails.getCreditCardType() + "(" + storePaymentMethodDetails.getLastFour() + ")"),
                            () -> verify_expiration_details(storePaymentMethodDetails.getExpirationDate()),
                            () -> verify_account_holder_name(storePaymentMethodDetails.getPayorName())
                    );
                }
                finally {
                    click_payment_method_list();
                    try {
                        verify_all(
                                () -> verify_credit_card_last_four(storePaymentMethodDetails.getLastFour()),
                                () -> verify_account_holder_name(storePaymentMethodDetails.getPayorName()),
                                () -> verify_expiration_year(expYear),
                                () -> verify_payment_method_type(storePaymentMethodDetails.getPaymentType()),
                                () -> verify_credit_card_type(CREDIT_CARD_TYPE),
                                () -> verify_expiration_month(Month.of(expMonth).name().toLowerCase()),
                                () -> verify_payment_method_zip_code(storeMemberDetails)
                        );
                    }
                    finally {
                        storePaymentMethodDetails.setPayorPGName(find_element_value(accountHolder));
                        logger.info("Payor PG Name: " + storePaymentMethodDetails.getPayorPGName());
                        click_close_button();
                    }
                }
            }
        }
        return me();
    }

    @Step("Verifying Wallet Details")
    public WalletPage verify_wallet_details(Map payorProfileInfoDbMap) {
        logger.info("Verifying Wallet Details");
        click_wallet_Tab();
        String payment_method = get_payment_method();
        if (payment_method.equals(BANK_ACCOUNT_PAYMENT_METHOD)) {
            click_payment_method_list();
            click_wallet_edit_btn();

            verify_all(
                    () -> verify_routing_number(convertObjectIntoString(payorProfileInfoDbMap.get("routingNumber"))),
                    () -> verify_account_number_last_four(convertObjectIntoString(payorProfileInfoDbMap.get("lastFour"))),
                    () -> verify_bank_account_type(convertObjectIntoString(payorProfileInfoDbMap.get("type"))),
                    () -> verify_account_holder_name(convertObjectIntoString(payorProfileInfoDbMap.get("name")))
            );
        } else if (payment_method.equals(CREDIT_CARD)) {
            click_payment_method_list();
            click_wallet_edit_btn();

            verify_all(
                    () -> verify_credit_card_last_four(convertObjectIntoString(payorProfileInfoDbMap.get("lastFour"))),
                    () -> verify_expiration_year(convertObjectIntoString(payorProfileInfoDbMap.get("expiryYear"))),
                    () -> verify_zip(convertObjectIntoString(payorProfileInfoDbMap.get("routingNumber"))),
                    () -> verify_credit_card_type(convertObjectIntoString(payorProfileInfoDbMap.get("type"))),
                    () -> verify_account_holder_name(convertObjectIntoString(payorProfileInfoDbMap.get("name")))
            );
        }
        return me();
    }

    @Step("Verify late fee details")
    public WalletPage verify_late_fee_details(StoreLateFeeDetails storeLateFeeDetails) {
        verify_all(
                () -> verify_late_fee_count(storeLateFeeDetails),
                () -> verify_late_fee_amount(storeLateFeeDetails)
        );
        return me();
    }

    @Step("Verify service fee details")
    public WalletPage verify_service_fee_details(StoreServiceFeeDetails storeServiceFeeDetails) {
        logger.info("Verifying service fee details");
        verify_all(
                () -> verify_service_fee_count(storeServiceFeeDetails),
                () -> verify_service_fee_amount(storeServiceFeeDetails)
        );
        return me();
    }

    @Step("Verifying late fee count from OBC to Uno APP")
    public WalletPage verify_late_fee_count(StoreLateFeeDetails storeLateFeeDetails) {
        logger.info("Verifying late fee count from OBC to Uno APP");
        click(paymentDetailsButton);
        if (storeLateFeeDetails.getLateFeeCount().equalsIgnoreCase("Late Fee Not Present")) {
            wait_until(3);
            verify(invisibilityOfElementLocated(lateFeeTransaction));
            logger.info("Late fee not present");
        } else {
            wait_until(3);
            verify_value_matches(String.valueOf(find_element_count(lateFeeTransaction)), storeLateFeeDetails.getLateFeeCount());
        }
        return me();
    }

    @Step("Verifying service fee count from OBC to Uno APP")
    public WalletPage verify_service_fee_count(StoreServiceFeeDetails storeServiceFeeDetails) {
        logger.info("Verifying service fee count from OBC to Uno APP");
        if (storeServiceFeeDetails.getServiceFeeCount().equalsIgnoreCase("Service Fee Not Present")) {
            verify(invisibilityOfElementLocated(serviceFeeTransaction));
            logger.info("Service fee not present");
        } else {
            verify_value_matches(String.valueOf(find_element_count(serviceFeeTransaction)), storeServiceFeeDetails.getServiceFeeCount());
        }
        return me();
    }

    @Step("Verifying late fee amount from OBC to Uno APP")
    public WalletPage verify_late_fee_amount(StoreLateFeeDetails storeLateFeeDetails) {
        logger.info("Verifying late fee amount from OBC to Uno APP");
        if (storeLateFeeDetails.getLateFeeCount().equalsIgnoreCase("Late Fee Not Present")) {
            verify(invisibilityOfElementLocated(lateFeeTransaction));
            logger.info("Late fee not present");
        } else {
            verify_value_matches(find_element_text(lateFeeTransactionAmount), storeLateFeeDetails.getLateFeeAmount());
        }
        return me();
    }

    @Step("Verifying service fee amount from OBC to Uno APP")
    public WalletPage verify_service_fee_amount(StoreServiceFeeDetails storeServiceFeeDetails) {
        logger.info("Verifying service fee amount from OBC to Uno APP");
        if (storeServiceFeeDetails.getServiceFeeCount().equalsIgnoreCase("Service Fee Not Present")) {
            verify(invisibilityOfElementLocated(serviceFeeTransaction));
            logger.info("Service fee not present");
        } else {
            verify_value_matches(find_element_text(serviceFeeTransactionAmount), storeServiceFeeDetails.getServiceFeeAmount());
        }
        return me();
    }

    @Step("Verifying late fee type from OBC to Uno APP")
    public WalletPage verify_late_fee_type(StoreLateFeeDetails storeLateFeeDetails) {
        logger.info("Verifying late fee type from OBC to Uno APP");
        if (storeLateFeeDetails.getLateFeeCount().equalsIgnoreCase("Late Fee Not Present")) {
            verify(invisibilityOfElementLocated(lateFeeTransaction));
            logger.info("Late fee not present");
        } else {
            verify_value_matches(find_element_text(lateFeeDebitType), DEBIT);
        }
        return me();
    }

    @Step("Verifying service fee type from OBC to Uno APP")
    public WalletPage verify_service_fee_type(StoreServiceFeeDetails storeServiceFeeDetails) {
        logger.info("Verifying service fee type from OBC to Uno APP");
        if (storeServiceFeeDetails.getServiceFeeCount().equalsIgnoreCase("Late Fee Not Present")) {
            verify(invisibilityOfElementLocated(serviceFeeTransaction));
            logger.info("Service fee not present");
        } else {
            verify_value_matches(find_element_text(serviceFeeDebitType), DEBIT);
        }
        return me();
    }

    public WalletPage verify_payor_payment_method_details(List memberId, List paymentmethodId, DataBaseHandler dataBaseHandler) {

        for (int i = 0; i < paymentmethodId.size(); i++) {
            Map<String, String> memberInfo = (Map<String, String>) memberId.get(i);
            Map<String, String> payorProfileInfoDbMap = (Map<String, String>) paymentmethodId.get(i);
            click(memberLink);
            search_member(memberInfo.get("phoneNumber")).
                    select_member(memberInfo.get("email"));
            verify_wallet_details(payorProfileInfoDbMap);
            //   .click(closeButton);
        }
        return me();
    }
}
