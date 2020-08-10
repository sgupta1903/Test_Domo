package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Jira Ticket:
Date: 09/06/2018
*/

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static helper.AppConstants.SUB_PAY_AMOUNT;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.getDate;
import static util.UtilityGeneric.get_section_name;

@Slf4j
public class UnoAppMemberDetailPage extends AbcCommonAbstractPage<UnoAppMemberDetailPage> {

    protected By agreementTab = By.xpath("//div[text()= 'Agreement']");
    protected String agreementInformation = "//div/h3[text() = 'Agreement Information']";
    protected String agreementStatus = "//div[@data-abc-id = 'agreementStatus'][contains(.,'Status: Ok')]";
    protected By subscriptionTab = By.xpath("//div[contains(text(),'Subscriptions')]");
    protected By walletTab = By.xpath("//div[text()='Payments']");
    protected By notesTab = By.xpath("//div[contains(text(),'Notes')]");
    protected By profileTab = By.xpath("//div[contains(text(),'Profile')]");
    protected String personalInformationSection = "//h3[@data-abc-id='personalInfoSectionHeaderText']";
    protected By transactionTypeInvoice = By.xpath("//td[@data-abc-id = 'transactionType'][text() = 'Invoice']");
    protected String contactInformationSection = "//h3[@data-abc-id='contactInfoSectionHeaderText']";
    protected String blankNotesTab = "//div[contains(text(),'An Item ')]";
    protected By payMethodTablePlusIcon = By.xpath("//i[@data-abc-id = 'paymentMethodTablePlusIcon']");
    protected String walletPaymentMethod = "//span[contains (text(), '%s')][@data-abc-id = 'lastFour']";
    protected String walletAccountHolderName = "//td[@data-abc-id= 'accountHolder'][text() = '%s']";
    protected String paymentMethodStatus = "//span[text() ='%s']";
    protected String subscriptionFrequency = "//td[@data-abc-id = 'frequency'][text() = '%s']";
    protected String subscriptionAmenity = "//td[@data-abc-id = 'subscriptionItem.catalogItemName']";
    protected String subscriptionType = "//td[@data-abc-id = 'subType'][contains(text() ,'%s')]";
    protected String createdDate = "//td[@data-abc-id = 'date'][text() = '%s']";
    protected By addSubscriptionBtn = By.xpath("//span[contains(text(),'Add Subscription')]");
    protected By subsType = By.xpath("//div[@data-abc-id='type']//div[contains(@class,'fieldWrapper')]//input[@name='type']");
    protected By beginDate = By.xpath("//div[@data-abc-id='startDatePickerInput']//input[@name='startDatePicker']");
    protected By expirationDate = By.xpath("//div[@data-abc-id='expirationDateInput']//div[contains(@class,'fieldWrapper')]//input[@name='expirationDate']");
    protected By noOfPayments = By.xpath("//div[@data-abc-id='duration']//div[contains(@class,'fieldWrapper')]//input[@name='duration']");
    protected By profitCenterType = By.xpath("//div[@data-abc-id='catalogItem']//div[contains(@class,'fieldWrapper')]//input[@name='catalogItem']");
    protected By paymentFrequency = By.xpath("//div[@data-abc-id='frequencyInput']//div[@class='fieldWrapper--1gIap']//input");
    protected By firstDueDate = By.xpath("//div[@data-abc-id='firstDueDateInput']//div[contains(@class,'fieldWrapper')]//input[@name='firstDueDate']");
    protected By payAmount = By.xpath("//div[@data-abc-id='autoPayAmount']//div[contains(@class,'fieldWrapper')]//input[@name='autoPayAmount']");
    protected By stmtAmount = By.xpath("//div[@data-abc-id='statementAmount']//div[contains(@class,'fieldWrapper')]//input[@name='statementAmount']");
    protected By submitBtn = By.xpath("//span[text()='Save']");
    protected By addBankAccountButton = By.xpath("//span[text()='Add Bank Account']");
    protected By addCreditCardButton = By.xpath("//span[text()='Add Credit Card']");
    protected By paymentDetails = By.xpath("//strong[text() = 'Default']/../following-sibling::td//button[@data-abc-id = 'paymentDetailsButton']");
    protected String paymentStatus= "//td[text()='Transfer Transaction']";
    protected By paymentMethodList = By.xpath("//td[@data-abc-id='status']//span[text()='Active']");
    protected By stopPaymentButton = By.xpath("//button[@data-abc-id='stopPayments']");
    protected By confirmBtn = By.xpath("//button[@data-abc-id='confirmButton']");
    protected By closeDialogBox = By.xpath("//i[@data-abc-id='closeDrawerButtonIcon']");
    protected By ufoMessage = By.xpath("//div[contains(@data-abc-id,'ufoContent')]");
    protected By profileEditButton = By.xpath("//button[@data-abc-id='editButton']//i[@data-abc-id='editButtonIcon']");
    protected By memberFirstName = By.xpath("//div[@data-abc-id='memberFirstNameInput']//div[contains(@class,'fieldWrapper')]//input[@name='firstName']");
    protected By memberLastName = By.xpath("//div[@data-abc-id='memberLastNameInput']//div[contains(@class,'fieldWrapper')]//input[@name='lastName']");
    protected By memberSinceDate = By.xpath("//div[@data-abc-id='memberSinceDatePickerInput']//div[contains(@class,'fieldWrapper')]//input[@name='memberSince']");
    protected By memberAddress = By.xpath("//div[@data-abc-id='addressInput']//div[contains(@class,'fieldWrapper')]//input[@name='address']");
    protected By memberCity = By.xpath("//div[@data-abc-id='cityInput']//div[contains(@class,'fieldWrapper')]//input[@name='city']");
    protected By memberZipCode = By.xpath("//div[@data-abc-id='zipInput']//div[contains(@class,'fieldWrapper')]//input[@name='zip']");
    protected By state = By.xpath("//div[@data-abc-id='stateInput']//div[contains(@class,'fieldWrapper')]//input[@name='state']");
    protected By saveProfileBtn = By.xpath("//span[text()='Save']");
    protected By clickSubscriptionList = By.xpath("//tr//td[@data-abc-id='price']");
    protected By freezeBtn = By.xpath("//button[@data-abc-id='freezeSubscriptionButton']");
    protected String freezeType = "//div[@data-abc-id='freezeType']//div[contains(@class,'fieldWrapper')]//input[@name='freezeType']";
    protected By freezeStartDate = By.xpath("//div[@data-abc-id='startDateInput']//div[contains(@class,'fieldWrapper')]//input[@name='startDate']");
    protected By freezeEndDate = By.xpath("//div[@data-abc-id='expirationDateInput']//div[contains(@class,'fieldWrapper')]//input[@name='expirationDate']");
    protected By saveFreeze = By.xpath("//button[@data-abc-id='freezeSubscriptionFormSubmit']");
    protected By closeDrawerDialog = By.xpath("//div[@data-abc-id='subscriptionDrawer']//i[@data-abc-id='closeDrawerButtonIcon']");
    protected String freezeStatus = "//td[text()='%s']";
    static String firstName = null;
    static String lastName = null;
    static String address = null;
    static String city = null;

    public By get_payment_method(String type) {
        log.info("Getting payment method");
        switch (type) {
            case "Visa":
                return By.xpath(String.format(walletPaymentMethod, "Visa"));
            case "MasterCard":
                return By.xpath(String.format(walletPaymentMethod, "MasterCard"));
            case "American Express":
                return By.xpath(String.format(walletPaymentMethod, "American Express"));
            default:
                return By.xpath(String.format(walletPaymentMethod, type));
        }
    }

    public By get_payment_method_status(String type) {
        log.info("Getting payment method status");
        wait_until(2);
        return By.xpath(String.format(paymentMethodStatus, type));
    }
    public By get_payment_status_after_transferred(String status){
        log.info("Getting the payment method status after payment status changes to transferred");
        wait_until(2);
        return By.xpath(String.format(paymentStatus, status));
    }
    public By get_account_holder_name(String name) {
        log.info("Getting account holder name");
        if (name.equals("card")) {
            return By.xpath(String.format(walletAccountHolderName, name));
        } else {
            return By.xpath(String.format(walletAccountHolderName, name));
        }
    }

    public By get_freeze_subscription_status(String statusInfo) {
        log.info("Verifying the Freeze subscription status");
        return By.xpath(String.format(freezeStatus, statusInfo));
    }

    public By get_subscription_frequency(String frequency) {
        log.info("Getting payment frequency");
        return By.xpath(String.format(subscriptionFrequency, frequency));
    }

    public By get_member_agreement_info(String agreementInfo) {
        log.info("Getting payment frequency");
        return By.xpath(String.format(agreementInformation, agreementInfo));
    }

    public By get_agreement_status_ok(String okay) {
        log.info("Verifying that agreement status as ok");
        return By.xpath(String.format(agreementStatus, okay));
    }

    public By get_created_date() {
        log.info("Getting payment frequency");
        return By.xpath(String.format(createdDate, getDate(0)));
    }

    /*public By freeze_start_date(String date){
        log.info("Enter the freeze start date.");
        return By.xpath(String.format(freezeStartDate,getDate(Integer.parseInt(date))));
    }*/
    /*public By freeze_end_date(String endEate){
        log.info("Enter the freeze end date.");
        return By.xpath(String.format(freezeEndDate,getDate(Integer.parseInt(endEate))));
    }*/
    public By freeze_type(String type) {
        log.info("Enter the freeze type .");
        return By.xpath(String.format(freezeType, type));
    }

    public By get_subscription_amenity(String amenity) {
        log.info("Getting subscription amenity");
        return By.xpath(String.format(subscriptionAmenity, amenity));
    }

    public By get_subscription_type(String subsType) {
        log.info("Getting subscription type");
        return By.xpath(String.format(subscriptionType, subsType));
    }

    public UnoAppMemberDetailPage select_agreement_tab() {
        log.info("Selecting agreement tab");
        wait_until(2);
        verify(presenceOfElementLocated(agreementTab), 60, 2000);
        wait_until(2);
        click(agreementTab);
        return me();
    }

    public UnoAppMemberDetailPage enter_freeze_start_date(Map<String, String> startDate) {
        log.info("Enter the freeze start date.");
        wait_until(1);
        click(freezeStartDate);
        enter(freezeStartDate, getDate(Integer.parseInt(startDate.get(FREEZE_START_DATE))));
        return me();
    }

    public UnoAppMemberDetailPage enter_freeze_end_date(Map<String, String> endDate) {
        log.info("Enter the freeze end date.");
        wait_until(1);
        enter(freezeEndDate, getDate(Integer.parseInt(endDate.get(FREEZE_END_DATE))));
        return me();
    }

    public UnoAppMemberDetailPage enter_freeze_type(Map<String, String> freezeTypeInfo) {
        log.info("Enter the freeze type.");
        verify(presenceOfElementLocated(freeze_type(freezeTypeInfo.get(FREEZE_TYPE))));
        return me();
    }

    public UnoAppMemberDetailPage verify_freeze_subscription_status(Map<String, String> freezeStatus) {
        log.info("Verify the freeze subscription status.");
        verify(presenceOfElementLocated(get_freeze_subscription_status(freezeStatus.get(FREEZE_STATUS))));
        return me();
    }

    public UnoAppMemberDetailPage verify_agreement_information_presence(Map<String, String> agreementInfo) {
        log.info("Verifying that agreement information is present");
        verify(presenceOfElementLocated(get_member_agreement_info(agreementInfo.get(AGREEMENT_INFO))));
        return me();
    }

    public UnoAppMemberDetailPage verify_agreement_status_ok(Map<String, String> agreementStatus) {
        log.info("Verifying that agreement status should be OK");
        verify(presenceOfElementLocated(get_agreement_status_ok(agreementStatus.get(AGREEMENT_STATUS))));
        return me();
    }

    public UnoAppMemberDetailPage select_subscription_tab() {
        log.info("Selecting subscription tab");
        verify(presenceOfElementLocated(subscriptionTab), 60, 2000);
        click(subscriptionTab);
        return me();
    }

    public UnoAppMemberDetailPage select_subscription_list() {
        log.info("Selecting subscriptions List");
        verify(presenceOfElementLocated(clickSubscriptionList), 60, 2000);
        click(clickSubscriptionList);
        return me();
    }

    public UnoAppMemberDetailPage click_freeze_button() {
        log.info("Selecting subscriptions List");
        wait_until(2);
        verify(presenceOfElementLocated(freezeBtn), 60, 2000);
        click(freezeBtn);
        return me();
    }

    public UnoAppMemberDetailPage click_freeze_save_button() {
        log.info("Saving the freeze the details.");
        wait_until(2);
        verify(presenceOfElementLocated(saveFreeze), 60, 2000);
        return click(saveFreeze);
    }

    public UnoAppMemberDetailPage click_close_drawer_dialog() {
        log.info("Closing Drawer Dialog box");
        verify(presenceOfElementLocated(closeDrawerDialog), 60, 2000);
        wait_until(1);
        click(closeDrawerDialog);
        return me();
    }

    public UnoAppMemberDetailPage add_subscription() {
        log.info("Click to add another subscription");
        verify(presenceOfElementLocated(addSubscriptionBtn), 60, 2000);
        click(addSubscriptionBtn);
        wait_until(2);
        return me();
    }

    public UnoAppMemberDetailPage enter_open_ended_another_subscription_details(Map<String, String> subData) {
        add_subscription();
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(SUB_TYPE));
        select_payment_freq(subData.get(SUB_PAY_FREQUENCY));
        enter(firstDueDate, getDate(Integer.parseInt(subData.get(SUB_DUE_DATE))));
        wait_until(2);
        select_profit_center(subData.get(ITEM_NAME));
        enter_stmt_amount(subData.get(SUB_STMT_AMOUNT));
        enter_autopay_payment_amt(subData.get(SUB_PAY_AMOUNT));
        enter(beginDate, getDate(Integer.parseInt(subData.get(SUB_BEGIN_DATE))));
        wait_until(2);

        return click_on_submit_btn();
    }

    public UnoAppMemberDetailPage enter_installment_another_subscription_details(Map<String, String> subData) {
        add_subscription();
        log.info("Entering data in schedule information section");
        select_subs_type(subData.get(MULTIPLE_SUBS_TYPE));
        select_payment_freq(subData.get(MULTIPLE_PAY_FREQUENCY));
        enter(firstDueDate, getDate(Integer.parseInt(subData.get(MULTIPLE_FIRST_DUE_DATE))));
        enter(expirationDate, getDate(Integer.parseInt(subData.get(MULTIPLE_EXPIRATION_DATE))));
        wait_until(2);
        enter_stmt_amount_for_installment(subData.get(SUB_STMT_AMOUNT));
        select_profit_center(subData.get(ITEM_NAME));
        enter_autopay_payment_amt(subData.get(SUB_PAY_AMOUNT));
        enter(noOfPayments, getDate(Integer.parseInt(subData.get(MULTIPLE_NO_OF_PAYMENTS))));
        enter(beginDate, getDate(Integer.parseInt(subData.get(MULTIPLE_BEGIN_DATE))));
        wait_until(2);
        return click_on_submit_btn();
    }

    public UnoAppMemberDetailPage click_on_submit_btn() {
        log.info("Clicking on submit button to create the subscription");
        verify(presenceOfElementLocated(submitBtn), 60, 2000);
        click(submitBtn);
        return me();
    }

    public UnoAppMemberDetailPage select_subs_type(String dataSetName) {
        log.info("Select Subscription type.");
        wait_until(2);
        verify(presenceOfElementLocated(subsType), 60, 2000);
        enter(subsType, dataSetName);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(1);
        return me();
    }

    public UnoAppMemberDetailPage select_profit_center(String dataSetName) {

        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(profitCenterType), 60, 2000);
        enter(profitCenterType, dataSetName);
        wait_until(2);
        enter_by_key();
        wait_until(3);
        return me();
    }

    public UnoAppMemberDetailPage select_payment_freq(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(paymentFrequency), 60, 2000);
        enter(paymentFrequency, dataSetName);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public UnoAppMemberDetailPage enter_autopay_payment_amt(String num) {
        log.info("Entering the Payment Amount");
        wait_until(1);
        enter(this.payAmount, num);
        return me();
    }

    public UnoAppMemberDetailPage enter_stmt_amount(String num) {
        log.info("Entering the Statement Amount");
        wait_until(1);
        click(stmtAmount);
        enter(this.stmtAmount, num);
        return me();
    }
    public UnoAppMemberDetailPage enter_stmt_amount_for_installment(String num) {
        log.info("Entering the Statement Amount");
        wait_until(2);
        enter(this.stmtAmount, num);
        return me();
    }

    public UnoAppMemberDetailPage verify_subscription_frequency(Map<String, String> frequencyData) {
        log.info("Verifying subscription frequency");
        wait_until(2);
        verify(presenceOfElementLocated(get_subscription_frequency(frequencyData.get(SUB_PAY_FREQUENCY))));
        return me();
    }

    public UnoAppMemberDetailPage verify_subscription_amenity(Map<String, String> amenityData) {
        log.info("Verifying subscription amenity");
        verify(presenceOfElementLocated(get_subscription_amenity(amenityData.get(ITEM_NAME))));
        return me();
    }

    public UnoAppMemberDetailPage verify_subscription_amenity_second_location_item(Map<String, String> amenityData) {
        log.info("Verifying subscription amenity");
        verify(presenceOfElementLocated(get_subscription_amenity(amenityData.get(SECOND_ITEM_NAME))));
        return me();
    }


    public UnoAppMemberDetailPage verify_subscription_type(Map<String, String> subsTypeData) {
        log.info("Verifying subscription type");
        verify(presenceOfElementLocated(get_subscription_type(subsTypeData.get(SUB_TYPE))));
        return me();
    }

    public UnoAppMemberDetailPage click_profile_tab() {
        log.info("Click on profile tab");
        verify(presenceOfElementLocated(profileTab), 60, 2000);
        click(profileTab);
        return me();
    }

    public UnoAppMemberDetailPage click_on_edit_btn() {
        log.info("Clicking on profile edit button");
        verify(presenceOfElementLocated(profileEditButton), 60, 2000);
        return click(profileEditButton);
    }

    public UnoAppMemberDetailPage enter_member_first_name(String firstName) {
        log.info("Enter first name");
        verify(presenceOfElementLocated(memberFirstName), 60, 2000);
        enter(memberFirstName, firstName);
        return me();
    }

    public UnoAppMemberDetailPage enter_member_last_name(String lastName) {
        log.info("Enter last name");
        verify(presenceOfElementLocated(memberLastName), 60, 2000);
        enter(memberLastName, lastName);
        return me();
    }

    public UnoAppMemberDetailPage enter_member_address(String address) {
        log.info("Enter address");
        verify(presenceOfElementLocated(memberAddress), 60, 2000);
        enter(memberAddress, address);
        return me();
    }

    public UnoAppMemberDetailPage enter_member_city(String city) {
        log.info("Enter city");
        verify(presenceOfElementLocated(memberCity), 60, 2000);
        enter(memberCity, city);
        return me();
    }

    public UnoAppMemberDetailPage enter_member_since(String memerSince) {
        log.info("Enter city");
        verify(presenceOfElementLocated(memberSinceDate), 60, 2000);
        enter(memberSinceDate, memerSince);
        return me();
    }

    public UnoAppMemberDetailPage enter_member_zipcode(String zip) {
        log.info("Enter zip code");
        verify(presenceOfElementLocated(memberZipCode), 60, 2000);
        enter(memberZipCode, zip);
        return me();
    }

    public UnoAppMemberDetailPage save_profile_details() {
        log.info("Saving the profile details");
        verify(presenceOfElementLocated(saveProfileBtn), 60, 2000);
        wait_until(1);
        click(saveProfileBtn);
        return me();
    }

    public UnoAppMemberDetailPage update_profile_details(String zipCode) {
        log.info("Updating profile details");
        firstName = RandomStringUtils.randomAlphabetic(4);
        lastName = RandomStringUtils.randomAlphabetic(4);
        address = RandomStringUtils.randomAlphabetic(10);
        city = RandomStringUtils.randomAlphabetic(4);
        enter_member_first_name(firstName);
        enter_member_last_name(lastName);
        enter_member_since(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "beginDate"))));
        enter_member_address(address);
        enter_member_city(city);
        enter_member_zipcode(zipCode);
        enter(state,"Alaska");
        save_profile_details();
        wait_until(3);
        return me();
    }

    public By get_personal_info_section_present(String personalInfo) {
        log.info("Verify personal information section is present");
        return By.xpath(String.format(personalInformationSection, personalInfo));
    }

    public By get_contact_info_section_present(String contactInfo) {
        log.info("Verify contact information section present");
        return By.xpath(String.format(contactInformationSection, contactInfo));
    }

    public UnoAppMemberDetailPage verify_contact_info_section_present(Map<String, String> contactInfo) {
        log.info("Verify contact information section present");
        verify(presenceOfElementLocated(get_contact_info_section_present(contactInfo.get(PROFILE_CONTACT_INFO))));
        return me();
    }

    public UnoAppMemberDetailPage click_notes_tab() {
        log.info("clicking on notes tab");
        verify(presenceOfElementLocated(notesTab), 60, 2000);
        click(notesTab);
        return me();
    }

    public By get_notes_tab_info(String notesInfo) {
        log.info("Verify notes tab information");
        return By.xpath(String.format(blankNotesTab, notesInfo));
    }

    public UnoAppMemberDetailPage verify_notes_tab_info(Map<String, String> notesInfo) {
        log.info("Verify notes tab when no notes are present");
        verify(presenceOfElementLocated(get_notes_tab_info(notesInfo.get(NOTES_INFO))));
        return me();
    }

    public UnoAppMemberDetailPage click_payments_tab() {
        log.info("clicking on Payments tab");
        verify(presenceOfElementLocated(walletTab), 60, 2000);
        wait_until(2);
        click(walletTab);
        return me();
    }

    public UnoAppMemberDetailPage select_payment_details() {
        log.info("Selecting the Payment Method");
        verify(presenceOfElementLocated(paymentDetails), 60, 2000);
        wait_until(10);
        click(paymentDetails);
        wait_until(2);
        return me();
    }
    public UnoAppMemberDetailPage verify_the_payment_status(Map<String, String> paymentMethod){
        log.info("Verifying the payment status.");
        wait_until(2);
        verify(presenceOfElementLocated(get_payment_status_after_transferred(paymentMethod.get(PAYMENT_METHOD_STATUS))));
        return me();
    }
    public UnoAppMemberDetailPage wait_until_payment_status_changed() {
        log.info("Wait for the scheduler changed payment details");
        wait_until(1500);
        return me();
    }

    public UnoAppMemberDetailPage click_on_add_bank_account() {
        log.info("Clicking on bank account");
        verify(presenceOfElementLocated(addBankAccountButton), 60, 2000);
        wait_until(2);
        click(addBankAccountButton);
        return me();
    }

    public UnoAppMemberDetailPage click_on_add_credit_card_button() {
        log.info("Clicking on credit card button");
        verify(presenceOfElementLocated(addCreditCardButton), 60, 2000);
        wait_until(2);
        click(addCreditCardButton);
        return me();
    }

    public UnoAppMemberDetailPage click_on_payment_method_list() {
        log.info("Clicking on Payment Method list");
        verify(presenceOfElementLocated(paymentMethodList), 60, 2000);
        wait_until(2);
        click(paymentMethodList);
        return me();
    }

    public UnoAppMemberDetailPage click_on_stop_payments_btn() {
        log.info("Clicking on stop Payment button");
        verify(presenceOfElementLocated(stopPaymentButton), 60, 2000);
        wait_until(2);
        click(stopPaymentButton);
        return me();
    }

    public UnoAppMemberDetailPage confirm_to_stop_payment() {
        log.info("Confirm to stopping the payment");
        wait_until(1);
        click(confirmBtn);
        return me();
    }

    public UnoAppMemberDetailPage close_payment_dialog_box() {
        log.info("Closing the payment Drawer box");
        wait_until(4);
        click(closeDialogBox);
        return me();
    }

    public UnoAppMemberDetailPage verify_payment_method_status() {
        log.info(" Verifying wallet payment method status after stopped payment");
        verify(presenceOfElementLocated(get_payment_method_status("Payor revoked")), 60, 2000);
        return me();
    }

    public UnoAppMemberDetailPage verify_wallet_payment_method_bank(Map<String, String> walletType) {
        log.info("Verify payment method as bank");
        verify(presenceOfElementLocated(get_payment_method(walletType.get(PAYMENT_METHOD_BANK))));
        return me();
    }

    public UnoAppMemberDetailPage verify_wallet_payment_method_card(Map<String, String> paymentMethodType) {
        log.info(" Verifying wallet payment method");
        verify(presenceOfElementLocated(get_payment_method(paymentMethodType.get(PAYMENT_METHOD_CARD))));
        return me();
    }
    public UnoAppMemberDetailPage verify_wallet_payment_method_amex(Map<String, String> paymentMethodType) {
        log.info(" Verifying wallet payment method");
        verify(presenceOfElementLocated(get_payment_method(paymentMethodType.get(PAYMENT_METHOD_AMEX))));
        return me();
    }

    public UnoAppMemberDetailPage verify_payment_method_migrated_data(Map<String, String> paymentMethodType) {
        log.info(" Verifying wallet payment method");
        if (paymentMethodType.get(PAYMENT_METHOD_CARD).equals("EF")) {
            verify(presenceOfElementLocated(get_payment_method(paymentMethodType.get(PAYMENT_METHOD_BANK))));
        } else {
            verify(presenceOfElementLocated(get_payment_method(paymentMethodType.get(SUB_CARD_TYPE))));
        }
        return me();
    }

    public UnoAppMemberDetailPage verify_wallet_bank_account_holder_name(Map<String, String> accountHolerName) {
        log.info("Verifying wallet account holder name");
        verify(presenceOfElementLocated(get_account_holder_name(accountHolerName.get(SUB_ACCOUNT_HOLDER_NAME))));
        return me();
    }
    public UnoAppMemberDetailPage verify_holder_name_migrated_data(Map<String, String> accountHolerName) {
        log.info("Verifying wallet account holder name");
        if (accountHolerName.get(PAYMENT_METHOD_CARD).equals("EF")){
            verify(presenceOfElementLocated(get_account_holder_name(accountHolerName.get(SUB_ACCOUNT_HOLDER_NAME))));
        }
        else
            verify(presenceOfElementLocated(get_account_holder_name(accountHolerName.get(SUB_CARD_HOLDER_NAME))));


        return me();
    }

    public UnoAppMemberDetailPage verify_personal_info_section_present(Map<String, String> personalInfo) {
        log.info("Verify personal information section is present");
        verify(presenceOfElementLocated(get_personal_info_section_present(personalInfo.get(PROFILE_PERSONAL_INFO))));
        return me();
    }

    public UnoAppMemberDetailPage verify_wallet_card_account_holder_name(Map<String, String> cardHolderName) {
        log.info("Verifying wallet account holder name");
        verify(presenceOfElementLocated(get_account_holder_name(cardHolderName.get(SUB_CARD_HOLDER_NAME))));
        return me();
    }

    public UnoAppMemberDetailPage verify_invoice_generated() {
        log.info("Verifying wallet account invoice generation");
        verify(presenceOfElementLocated(payMethodTablePlusIcon), 60, 2000);
        click(payMethodTablePlusIcon);
        wait_until(5);
        verify(presenceOfElementLocated(transactionTypeInvoice), 60, 2000);
        verify(presenceOfElementLocated(get_created_date()), 60, 2000);
        return me();
    }
}