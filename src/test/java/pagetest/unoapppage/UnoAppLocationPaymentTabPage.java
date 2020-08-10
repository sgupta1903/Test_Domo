package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Jira Ticket:
Date: 10/1162018
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.getDate;
import static util.UtilityGeneric.get_section_name;
@Slf4j
public class UnoAppLocationPaymentTabPage extends AbcCommonAbstractPage<UnoAppLocationPaymentTabPage> {
    protected By paymentTab = By.xpath("//i[contains(@data-abc-id,'paymentTitleIcon')]");
    protected By addDeductionPage = By.xpath("//h3[text() = 'Add Deduction']");
    protected By deductionType = By.xpath("//div[@data-abc-id='paymentTypeInput']//input[@name='paymentType']");
    protected By startDate = By.xpath("//div[@data-abc-id='startDateInput']//input[@id='startDateInput']");
    protected By numberOfPayments = By.xpath("//input[@id = 'numberOfPayments']");
    protected By deductionFrequency = By.xpath("//div[@data-abc-id='frequencyInput']//input[@id='frequencyInput']");
    protected By deductionCode = By.xpath("//div[@data-abc-id='reimbursementDeductionCodeInput']//input[@name='reasonCodeId']");
    protected By deductionAmount = By.xpath("//div[@data-abc-id='amount']//input[@id='amount']");
    protected By deductionReason = By.xpath("//div[@data-abc-id = 'paymentReason']//textarea");
    protected By deductionReimbursementAdded = By.xpath("//td[@data-abc-id = 'type']");
    protected By saveButton = By.xpath("//span[text()='Save']");
    protected By addReimbursementPage = By.xpath("//h3[text() = 'Add Reimbursement']");
    private String paymentOption = "//label[contains(text(),'%s')]";
    private By addPaymentButton = By.xpath("//i[@data-abc-id='togglePaymentDrawerIcon']");

    public By getPaymentOption(String option) {
        log.info("Getting payment option which needs to be selected");
        return By.xpath(String.format(paymentOption, option));
    }

    public UnoAppLocationPaymentTabPage select_payment_tab() {
        log.info("Selecting payment tab");
        wait_until(4);
        verify(presenceOfElementLocated(paymentTab));
        click(paymentTab);
        return me();
    }

    /*
      Selecting payment option using either  Reimbursements or Deductions
     */
    public UnoAppLocationPaymentTabPage select_location_payment_option(String paymentOption) {
        log.info("Selecting" + paymentOption + "tab");
        verify(presenceOfElementLocated(getPaymentOption(paymentOption)));
        click(getPaymentOption(paymentOption));
        return me();
    }

    /*
    Selecting add button using either  Add Deduction or Add Reimbursement
   */
    public UnoAppLocationPaymentTabPage click_add_button(String buttonName) {
        log.info("Selecting add button");
        verify(presenceOfElementLocated(addPaymentButton));
        click(addPaymentButton);
        return me();
    }

    public UnoAppLocationPaymentTabPage verify_add_deduction_page_displayed() {
        log.info("Verifying Add deduction page displayed");
        verify(presenceOfElementLocated(addDeductionPage));
        return me();
    }

    public UnoAppLocationPaymentTabPage verify_add_reimbursement_page_displayed() {
        log.info("Verifying Add reimbursement page displayed");
        verify(presenceOfElementLocated(addReimbursementPage));
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_deduction_type(String type) {
        log.info("Selecting deduction type");
        enter(deductionType, type);
        enter_by_key();
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_start_date(String date) {
        log.info("Selecting start date");
        enter(startDate, date);
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_number_of_payments(String payments) {
        log.info("Selecting number of payments");
        enter(numberOfPayments, payments);
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_frequency(String frequency) {
        log.info("Entering frequency tab");
        enter(deductionFrequency, frequency);
        enter_by_key();
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_deduction_code(String code) {
        log.info("Entering deduction code");
        wait_until(4);
        enter(deductionCode, code);
        enter_by_key();

        return me();
    }

    public UnoAppLocationPaymentTabPage enter_amount(String amount) {
        log.info("Entering amount");
        enter(deductionAmount, amount);
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_reason(String reason) {
        log.info("Entering reason");
        enter(deductionReason, reason);
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_reimbursement_type(String type) {
        log.info("Selecting reimbursement type");
        enter(deductionType, type);
        enter_by_key();
        return me();
    }

    public UnoAppLocationPaymentTabPage enter_reimbursement_code(String code) {
        log.info("Entering reimbursement code");
        wait_until(4);
        enter(deductionCode, code);
        enter_by_key();
        return me();
    }

    public UnoAppLocationPaymentTabPage click_save_button() {
        log.info("Clicking save button");
        click(saveButton);
        return me();
    }

    /*
    Verifying for either Add Deduction or Add Reimbursement option
     */
    public UnoAppLocationPaymentTabPage verify_payment_option_added(String paymentOption) {
        log.info("Verify " + paymentOption + "is added successfully");
        verify(presenceOfElementLocated(addPaymentButton));
        verify(presenceOfElementLocated(deductionReimbursementAdded));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_details_for_one_time_deduction() {
        log.info("Adding details for one time deduction");
        enter_deduction_type(get_subscription_test_data(get_section_name(), "deductionType"));
        enter_deduction_code(get_subscription_test_data(get_section_name(), "deductionCode"));
        enter_amount(get_subscription_test_data(get_section_name(), "deductionAmount"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_one_time_deduction() {
        log.info("Adding details for one time deduction");
        enter(deductionType, get_subscription_test_data(get_section_name(), "deductionOneTimeType"));
        enter_by_key();
        enter_deduction_code(get_subscription_test_data(get_section_name(), "deductionCode"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter(deductionAmount, get_subscription_test_data(get_section_name(), "deductionOneTimeAmount"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_recurring_deduction() {
        log.info("Adding details for one time deduction");
        enter(deductionType, get_subscription_test_data(get_section_name(), "deductionRecurringType"));
        enter_by_key();
        enter_frequency(get_subscription_test_data(get_section_name(), "deductionFrequency"));
        enter_deduction_code(get_subscription_test_data(get_section_name(), "deductionCode"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter(deductionAmount, get_subscription_test_data(get_section_name(), "deductionRecurringAmount"));
        enter_reason(get_subscription_test_data(get_section_name(), "deductionReason"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_details_for_one_time_reimbursement() {
        log.info("Adding details for one time reimbursement");
        enter_reimbursement_type(get_subscription_test_data(get_section_name(), "reimbursementType"));
        enter_reimbursement_code(get_subscription_test_data(get_section_name(), "reimbursementCode"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter_amount(get_subscription_test_data(get_section_name(), "deductionAmount"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_one_time_reimbursement() {
        log.info("Adding details for one time reimbursement");
        enter(deductionType, get_subscription_test_data(get_section_name(), "reimbursementOneTime"));
        press_enter_key();
        enter_reimbursement_code(get_subscription_test_data(get_section_name(), "reimbursementCode"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter(deductionAmount, get_subscription_test_data(get_section_name(), "reimbursementOneTimeAmount"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_details_for_recurring_reimbursement() {
        log.info("Adding details for recurring reimbursement");
        enter_reimbursement_type(get_subscription_test_data(get_section_name(), "reimbursementType"));
        enter_frequency(get_subscription_test_data(get_section_name(), "deductionFrequency"));
        enter_reimbursement_code(get_subscription_test_data(get_section_name(), "reimbursementCode"));
        enter_amount(get_subscription_test_data(get_section_name(), "deductionAmount"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter_number_of_payments(get_subscription_test_data(get_section_name(), "deductionNumberOfPayments"));
        enter_reason(get_subscription_test_data(get_section_name(), "deductionReason"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_recurring_reimbursement() {
        log.info("Adding details for recurring reimbursement");
        enter(deductionType, get_subscription_test_data(get_section_name(), "reimbursementRecurringType"));
        press_enter_key();
        enter_frequency(get_subscription_test_data(get_section_name(), "deductionFrequency"));
        enter_reimbursement_code(get_subscription_test_data(get_section_name(), "reimbursementCode"));
        enter(deductionAmount, get_subscription_test_data(get_section_name(), "reimbursementRecurringAmount"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter_number_of_payments(get_subscription_test_data(get_section_name(), "deductionNumberOfPayments"));
        enter_reason(get_subscription_test_data(get_section_name(), "deductionReason"));
        return me();
    }

    public UnoAppLocationPaymentTabPage add_details_for_recurring_deduction() {
        log.info("Adding details for recurring type of deduction");
        enter_deduction_type(get_subscription_test_data(get_section_name(), "deductionType"));
        enter_frequency(get_subscription_test_data(get_section_name(), "deductionFrequency"));
        enter_deduction_code(get_subscription_test_data(get_section_name(), "deductionCode"));
        enter_number_of_payments(get_subscription_test_data(get_section_name(), "deductionNumberOfPayments"));
        enter_amount(get_subscription_test_data(get_section_name(), "deductionAmount"));
        enter_start_date(getDate(Integer.parseInt(get_subscription_test_data(get_section_name(), "deductionStartDate"))));
        enter_reason(get_subscription_test_data(get_section_name(), "deductionReason"));
        return me();
    }
}