package pagetest.unoapppage;

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.getDate;
import static util.UtilityGeneric.get_section_name;

/*
Created By: Sapna Batan
Updated By: Shilpi Gupta
Jira Ticket:
Test Rail Test Case Number:
Date: 02/15/2018
*/
@Slf4j
public class UnoAppSubscriptionPage extends AbcCommonAbstractPage<UnoAppSubscriptionPage> {

    private EnvProperty env = EnvProperty.getInstance(UNOAPP_INI);
    EnvProperty envMigratedIni = EnvProperty.getInstance(AppConstants.MIGRATED_INI);
    private By continueBtn = By.xpath("//button[@type='submit']");
    private By submitBtn = By.xpath("//span[text()='Submit']");
    private By textRoutingNumber = By.xpath("//div[@data-abc-id='routingNumber']//input[@name='routingNumber']");
    private By textAccountNumber = By.xpath("//div[@data-abc-id='bankAccountNumber']//input[@name='bankAccountNumber']");
    private By textAccountType = By.xpath("//div[contains(@data-abc-id,'savings')]");
    private By textBeginDate = By.xpath("//div[@data-abc-id='startDatePickerInput']//input[@name='startDatePicker']");
    private By textExpiryDate = By.xpath("//div[@data-abc-id='expirationDateInput']//input[@id ='expirationDateInput']");
    private By textDownPayment = By.xpath("//div[@data-abc-id='downpayment']//input[@id='downpayment']");
    private By textDueDate = By.xpath("//div[@data-abc-id='firstDueDateInput']//input[@name ='firstDueDate']");
    private By Duration = By.xpath("//div[@data-abc-id='duration']//input[@id='duration']");
    private By textTotalAmt = By.xpath("//div[@data-abc-id='statementAmount']//input[@name='statementAmount']");
    private By selectRenewalType = By.xpath("//div[@data-abc-id='renewTypeInput']//input[@id='renewTypeInput']");
    private By textRenewalDate = By.xpath("//div[@data-abc-id='renewDateInput']//input[@name ='renewDate']");
    private By textRenewalFreq = By.xpath("//div[@data-abc-id='renewFrequencyInput']//input[@id='renewFrequencyInput']");
    private By NumberOfPayments = By.xpath("//div[@data-abc-id='duration']//input[@id='duration']");
    private By memberData = By.xpath("//span[@data-abc-id = 'memberNameText']");

    private By statementAmt = By.xpath(" //div[@data-abc-id='statementAmount']//input[@name='statementAmount']");
    private By autoPayAmt = By.xpath(" //div[@data-abc-id='autoPayAmount']//input[@name='autoPayAmount']");
    //subscription
    private By bankBtn = By.xpath("//span[text()='Add Payment Method']");
    private By accountHolderName = By.xpath("//div[@data-abc-id='accountHolderName']//input[@name='accountHolderName']");
    private By cardNumber = By.xpath("//div[@data-abc-id='creditCardNumber']//input[@id='creditCardNumber']");
    private By cardHolderName = By.xpath("//div[@data-abc-id='accountHolderName']//input[@id='accountHolderName']");
    private By zipCode = By.xpath("//div[@data-abc-id='postalCode']//input[@id='postalCode']");
    private By creditCardBtn = By.xpath("//span[text()='Credit Card']");
    private By cardSaveBtn = By.xpath("//span[text()='Save']");
    private By expirationDate = By.xpath("//div[contains(@data-abc-id,'expirationDate')]//input");
    private String selectInputData = "//div[contains(@id,'%s')]//div[@class = 'fieldWrapper--1gIap']//input";
    String errorMessage = "//div[@data-abc-id = '%s']/span";
    private By cancelButton = By.xpath("//button[@data-abc-id='enrollmentCancelButton']");


    public UnoAppSubscriptionPage enter_subscription_with_migrated_data() {
        log.info("Entering data in subscription details section");
        verify(presenceOfElementLocated(textDownPayment), 60, 2000);
        enter(textDownPayment, envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "downPayment"));


        log.info("Entering data in schedule information section");
        select_subs_type(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "subType"));

        enter(textDueDate, getDate(Integer.parseInt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "dueDate"))));
        wait_until(1);
        select_profit_center(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "profitCenter"));
        enter(textBeginDate, getDate(Integer.parseInt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "beginDate"))));
        select_payment_freq(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "payFrequency"));
        enter_payment_statement_amt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "payAmount"));
        enter_payment_auto_pay_amt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "payAmount"));
        wait_until(1);

        if (envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "subType").equals("Installment")) {
            log.info("Entering data in schedule information section");
            enter(textExpiryDate, (getDate(Integer.parseInt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "expiryDate")))));
            enter(Duration, envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "textDuration"));
        }

        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_type_data() {
        log.info("Entering data in subscription details section");
        select_subs_type(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "subType"));
        wait_until(1);
        enter(textBeginDate, getDate(Integer.parseInt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "beginDate"))));
        enter(textExpiryDate, (getDate(Integer.parseInt(envMigratedIni.getConfigPropertyValue("SUBSCRIPTION", "expiryDate")))));
        return me();
    }


    public UnoAppSubscriptionPage enter_payment_information_for_migrated_data(Map<String, String> subData) {

        if (subData.get(PAYMENT_METHOD_CARD).equals("EF")) {
            log.info("Entering bank account payment information");
            click(bankBtn);
            wait_until(3);
            switch_to_window();
            enter(this.accountHolderName, (subData.get(SUB_ACCOUNT_HOLDER_NAME)));
            enter_bank_routing_number(subData.get(SUB_BANK_ROUTING_NUMBER));
            enter_bank_account_number(subData.get(SUB_BANK_ACCOUNT_NUMBER) + subData.get(SUB_LAST_FOUR));
            select_bank_account_type();
            verify(elementToBeClickable(continueBtn), 60, 2000);
            click(continueBtn);
        } else {
            log.info("Entering data Payment information section via Credit card");
            click_credit_card_btn();
            wait_until(2);
            switch_to_window();
            if (subData.get(SUB_CARD_TYPE).equals("Visa")) {
                enter_card_number(subData.get(SUB_CARD_NUMBER));

            } else if (subData.get(SUB_CARD_TYPE).equals("MasterCard")) {
                enter_card_number(subData.get(SUB_MASTERCARD_NUMBER));
            }
            enter_card_holder_name(subData.get(SUB_CARD_HOLDER_NAME));
            enter_card_expiration_year(subData.get(SUB_CARD_EXPIRY_YEAR));
            enter(this.zipCode, subData.get(SUB_CARD_POSTAL_CODE));
            verify(elementToBeClickable(cardSaveBtn));
            click(cardSaveBtn);


        }
        wait_until(2);
        switch_to_parent_window();
        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_downpayment(Map<String, String> subData) {
        log.info("Entering data in subscription details section");
        verify(presenceOfElementLocated(textDownPayment), 60, 2000);
        enter(textDownPayment, subData.get(SUB_DOWNPAYMENT));
        return me();
    }

    public UnoAppSubscriptionPage enter_payment_information_bank_account(Map<String, String> subData) {
        log.info("Entering bank account payment information");
        click(bankBtn);
        wait_until(3);
        switch_to_window();
        enter(this.accountHolderName, (subData.get(SUB_ACCOUNT_HOLDER_NAME)));
        enter_bank_routing_number(subData.get(SUB_BANK_ROUTING_NUMBER));
        enter_bank_account_number(subData.get(SUB_BANK_ACCOUNT_NUMBER));
        select_bank_account_type();
        verify(elementToBeClickable(continueBtn), 60, 2000);
        click(continueBtn);
        wait_until(3);
        switch_to_parent_window();
        return me();
    }

    public UnoAppSubscriptionPage add_another_payment_information_with_bank_account() {
        log.info("Add Payment information  with another payment method with   bank account");
        wait_until(3);
        switch_to_window();
        enter(this.accountHolderName, (get_subscription_test_data(get_section_name(), "accountHolderName")));
        enter_bank_routing_number(get_subscription_test_data(get_section_name(), "bankRouting"));
        enter_bank_account_number(get_subscription_test_data(get_section_name(), "accountNo"));
        select_bank_account_type();
        verify(elementToBeClickable(continueBtn), 60, 2000);
        click(continueBtn);
        wait_until(3);
        switch_to_parent_window();
        return me();
    }
    public UnoAppSubscriptionPage enter_add_another_payment_credit_card(Map<String, String> cardData) {
        log.info("Entering data Payment information section via Credit card");
        wait_until(2);
        switch_to_window();
        enter_card_number(cardData.get(SUB_CARD_NUMBER));
        enter_card_holder_name(cardData.get(SUB_CARD_HOLDER_NAME));
        enter_card_expiration_year(cardData.get(SUB_CARD_EXPIRY_YEAR));
        enter(this.zipCode, cardData.get(SUB_CARD_POSTAL_CODE));
        verify(elementToBeClickable(cardSaveBtn), 60, 2000);
        click(cardSaveBtn);
        wait_until(3);
        switch_to_parent_window();
        return me();
    }

    public UnoAppSubscriptionPage enter_bank_routing_number(String name) {
        log.info("Entering the Bank Routing Number");
        return enter(this.textRoutingNumber, name);

    }

    public UnoAppSubscriptionPage enter_bank_account_number(String name) {
        log.info("Entering the Bank Account Number");
        verify(presenceOfElementLocated(textAccountNumber), 60, 2000);
        return enter(this.textAccountNumber, name);
    }

    public UnoAppSubscriptionPage select_bank_account_type() {
        log.info(" Select Bank Account Type ");
        return click(textAccountType);
    }

    public UnoAppSubscriptionPage enter_payment_information_credit_card_not_use() {

        log.info("Entering data Payment information section via Credit card");
        click_credit_card_btn();
        wait_until(2);
        switch_to_window();
        enter_card_number(get_subscription_test_data(get_section_name(), "creditCardNumber"));
        enter_card_holder_name(get_subscription_test_data(get_section_name(), "cardHolderName"));
        enter_card_expiration_year(get_subscription_test_data(get_section_name(), "expYear"));
        enter(this.zipCode, get_subscription_test_data(get_section_name(), "postalCode"));
        verify(elementToBeClickable(cardSaveBtn), 60, 2000);
        click(cardSaveBtn);
        wait_until(2);
        switch_to_parent_window();
        return me();
    }

    public UnoAppSubscriptionPage enter_payment_information_credit_card(Map<String, String> cardData) {

        log.info("Entering data Payment information section via Credit card");
        click_credit_card_btn();
        wait_until(2);
        switch_to_window();
        enter_card_number(cardData.get(SUB_CARD_NUMBER));
        enter_card_holder_name(cardData.get(SUB_CARD_HOLDER_NAME));
        enter_card_expiration_year(cardData.get(SUB_CARD_EXPIRY_YEAR));
        enter(this.zipCode, cardData.get(SUB_CARD_POSTAL_CODE));
        verify(elementToBeClickable(cardSaveBtn));
        click(cardSaveBtn);
        wait_until(2);
        switch_to_parent_window();
        return me();
    }

    public UnoAppSubscriptionPage click_credit_card_btn() {
        log.info("Click On Credit Card Button");
        return click(creditCardBtn);

    }

    public UnoAppSubscriptionPage enter_card_number(String num) {
        log.info("Entering the card number");
        wait_until(2);
        return enter(this.cardNumber, num);
    }

    public UnoAppSubscriptionPage enter_card_holder_name(String name) {
        log.info("Enter Card Holder Name");
        return enter(this.cardHolderName, name);
    }

    public UnoAppSubscriptionPage enter_card_expiration_year(String expirationDate) {
        log.info("Enter Card Expiration Year");
        return enter(this.expirationDate, expirationDate);

    }

    public By get_input_field(String inputField) {
        log.info("Getting the input field");
        return By.xpath(String.format(selectInputData, inputField));

    }

    public UnoAppSubscriptionPage enter_subscription_schedule_information(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        select_profit_center(subData.get(ITEM_NAME));
        enter(textBeginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);

        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_for_second_location(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        select_profit_center(subData.get(SECOND_ITEM_NAME));
        enter(textBeginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);

        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_schedule_information_for_second_location(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        select_profit_center(subData.get(SECOND_ITEM_NAME));
        enter(textBeginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);

        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_schedule_information_without_frequency(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        select_profit_center(subData.get(ITEM_NAME));
        enter(textBeginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        click_freq(get_subscription_test_data(get_section_name(), "payFrequency"));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);
        return me();
      }

    public UnoAppSubscriptionPage enter_subscription_schedule_information_without_amenity(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        click_amenity(get_subscription_test_data(get_section_name(), "itemName"));
        enter(textBeginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_schedule_information_without_begin_date(Map<String, String> subData) {
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        enter(textDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(1);
        select_profit_center(subData.get(ITEM_NAME));
        click(textBeginDate);
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter_payment_amt(subData.get(SUB_PAY_AMOUNT));
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage select_subs_type(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        wait_until(2);
        verify(presenceOfElementLocated(get_input_field("type")), 60, 2000);
        enter(get_input_field("type"), dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppSubscriptionPage click_amenity(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(get_input_field("catalogItem")), 60, 2000);
        click(get_input_field("catalogItem"));
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage select_profit_center(String dataSetName) {

        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(get_input_field("catalogItem")), 60, 2000);
        enter(get_input_field("catalogItem"), dataSetName);
        wait_until(2);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppSubscriptionPage enter_payment_amt(String num) {
        log.info("Entering the Payment Amount");
        enter(this.textTotalAmt, num);
        click(this.textTotalAmt);
        wait_until(2);
        return me();
    }

    public UnoAppSubscriptionPage enter_payment_statement_amt(String num) {
        log.info("Entering the Statement Amount");
        enter(this.statementAmt, num);
        click(this.statementAmt);
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage enter_payment_auto_pay_amt(String num) {
        log.info("Entering the Auto Pay Amount");
        enter(this.autoPayAmt, num);
        click(this.autoPayAmt);
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage select_payment_freq(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(get_input_field("frequency")), 60, 2000);
        click(get_input_field("frequency"));
        wait_until(1);
        enter(get_input_field("frequency"), dataSetName);
        wait_until(1);
        enter_by_key();
        return me();
    }

    public UnoAppSubscriptionPage
    click_freq(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(get_input_field("frequency")), 60, 2000);
        click(get_input_field("frequency"));
        wait_until(1);
        return me();
    }

    public UnoAppSubscriptionPage enter_subscription_schedule_information_installment(Map<String, String> subData) {

        log.info("Entering data in schedule information section");
        enter(textExpiryDate, (getDate(Integer.parseInt(subData.get(SUB_EXPIRY_DATE)))));
        enter(Duration, subData.get(SUB_DURATION));
        return me();
    }

    public UnoAppSubscriptionPage enter_renewal_auto_to_open(Map<String, String> subData) {
        log.info("Entering data when type is auto renew to open.");
        select_renewal_type(subData.get(SUB_RENEWAL_TYPE));
        enter(textRenewalDate, getDate(Integer.parseInt(subData.get(SUB_RENEWAL_DATE))));
        select_renewal_freq(subData.get(SUB_RENEWAL_FREQUENCY));
        return me();
    }

    public UnoAppSubscriptionPage
    select_renewal_type(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        wait_until(2);
        verify(presenceOfElementLocated(selectRenewalType), 60, 2000);
        enter(selectRenewalType, dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppSubscriptionPage
    select_renewal_freq(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textRenewalFreq), 60, 2000);
        enter(textRenewalFreq, dataSetName);
        wait_until(2);
        return me();
    }

    public UnoAppSubscriptionPage enter_renewal_auto_to_term() {
        log.info("Entering data when type is auto renew to term.");
        select_renewal_type(get_subscription_test_data(get_section_name(), "renewalType"));
        enter(textRenewalDate, getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "textRenewalDate"))));
        select_number_of_payments(get_subscription_test_data(get_section_name(), "NumberOfPayments"));
        select_renewal_freq(get_subscription_test_data(get_section_name(), "renewalFreq"));
        return me();
    }

    public UnoAppSubscriptionPage select_number_of_payments(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(NumberOfPayments), 60, 2000);
        enter(NumberOfPayments, dataSetName);
        wait_until(2);
        return me();
    }

    public UnoAppSubscriptionPage click_on_submit_btn() {
        log.info("Clicking on submit button to create the subscription");
        scroll_down_to_page("0", "20");
        verify(presenceOfElementLocated(submitBtn), 60, 2000);
        wait_until(2);
        click(submitBtn);
        return me();
    }

    public UnoAppSubscriptionPage verify_disabled_submit_btn() {
        log.info("Verifying submit button to create the subscription");
        wait_until(1);
        scroll_down_to_page("0", "20");
        verify_element_disabled(continueBtn);
        log.info("submit button is disabled");
        return me();
    }
    public UnoAppSubscriptionPage click_cancel_subscription(){
        log.info("Clicking on cancel subscription button");
        wait_until(1);
        return click(cancelButton);
    }

    public UnoAppSubscriptionPage verify_subscription_created() {
        log.info("Verifying that subscription creation is done successfully");
        verify(presenceOfElementLocated(memberData), 5, 2000);
        return me();
    }

    public UnoAppSubscriptionPage verify_frequency_error() {
        log.info("Verifying error message for missing frequency");
        verify_element_by_text_continue(get_error_message("frequency"), "Please select Payment Frequency");
        return me();
    }

    public UnoAppSubscriptionPage verify_amenity_error() {
        log.info("Verifying error message for missing amenity");
        verify_element_by_text_continue(get_error_message("catalogItem"), "Please select an Amenity");
        return me();
    }

    public UnoAppSubscriptionPage verify_begin_date_error() {
        log.info("Verifying error message for missing begin date");
        verify_element_by_text_continue(get_error_message("startDatePickerInput"), "Please enter a valid Begin Date");
        return me();
    }

    public By get_error_message(String fieldName) {
        log.info("Fetching xpath for " + fieldName);
        return By.xpath(String.format(errorMessage, fieldName));
    }

}