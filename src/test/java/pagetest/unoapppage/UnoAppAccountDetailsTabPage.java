package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Date: 01/03/2020
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static util.UtilityGeneric.get_section_name;
@Slf4j
public class UnoAppAccountDetailsTabPage extends AbcCommonAbstractPage<UnoAppAccountDetailsTabPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);

    protected By accountDetailsTab = By.xpath("//i[@data-abc-id = 'accountDetailsTitleIcon']");
    protected By beginDate = By.xpath("//input[@name = 'beginDate']");
    protected By endDate = By.xpath("//input[@name = 'endDate']");
    protected By plusIcon = By.xpath("//i[@data-abc-id= 'AccountDetailsListPlusIcon']");
    protected By tableRows = By.xpath("//tr[@data-abc-id = 'AccountDetailsListRowRow']");
    protected String rowData = "//tr[@data-abc-id = 'AccountDetailsListRowRow']//td[@data-abc-id= '%s']";
    protected String paymentAsPerDate = "//td[text() =  '%s']/following-sibling::td[@data-abc-id = '%s']";


    public By get_row_by_column_name(String columnName) {
        log.info("Getting row data");
        return By.xpath(String.format(rowData, columnName));
    }

    public By get_row_by_column_name_and_date(String columnName ,  String dateFormat) {
        log.info("Getting row data as per column and date");
        return By.xpath(String.format(paymentAsPerDate, fetch_current_date(dateFormat), columnName));
    }

    public UnoAppAccountDetailsTabPage select_account_details_tab() {
        log.info("Select account details tab");
        click(accountDetailsTab);
        return me();
    }

    public UnoAppAccountDetailsTabPage click_plus_icon() {
        log.info("Clicking plus icon");
        click(plusIcon);
        return me();
    }

    public String fetch_current_date(String dateFormat) {
        log.info("Fetching current date");
        return get_current_date(dateFormat);
    }

    public UnoAppAccountDetailsTabPage verify_date(String expectedDate) {
        log.info("Verify the date");
        wait_until(2);
        verify_element_by_text(get_row_by_column_name("evalDate"), expectedDate);
        return me();
    }

    public String get_billing_amount() {
        log.info("Getting billed amount");
        wait_until(2);
        return "$" + get_two_decimal_point_value(get_subscription_test_data(get_section_name(), "payAmount"));
    }

    public String get_payment_amount() {
        log.info("Getting deduction and reimbursement amount when start date is in future");
        return "$" + get_two_decimal_point_value("0");
    }

    public UnoAppAccountDetailsTabPage verify_billed_amount(String expectedBilledAmount) {
        log.info("Verify the Billed  Amount");
        verify_element_by_text(get_row_by_column_name("billedAmount"), expectedBilledAmount);
        return me();
    }

    public UnoAppAccountDetailsTabPage verify_payments_received(String expectedPaymentsReceived) {
        log.info("Verify the Payments Received");
        verify_element_by_text(get_row_by_column_name("paymentsReceived"), expectedPaymentsReceived);
        return me();
    }

    public UnoAppAccountDetailsTabPage verify_charge_backs(String expectedChargeBacks) {
        log.info("Verify the Charge Backs");
        verify_element_by_text(get_row_by_column_name("chargeBacks"), expectedChargeBacks);
        return me();
    }

    public UnoAppAccountDetailsTabPage verify_deductions(String expectedDeductions) {
        log.info("Verify the Deductions plus tax rate");
        verify_element_by_text(get_row_by_column_name_and_date("deductions" , "MM/dd/YYYY"), expectedDeductions);
        return me();
    }

    public Double get_abc_rate_base_percentage() {
        log.info("Getting abc base rate percentage");
        return Double.parseDouble(env.getConfigPropertyValue("TaxRate", "abcBasePercentage"));
    }

    public Double get_abc_rate_per_transaction() {
        log.info("Getting abc rate per transaction");
        return Double.parseDouble(env.getConfigPropertyValue("TaxRate", "abcPerTransaction"));
    }

    public Double get_one_time_deduction_amount() {
        log.info("Getting one time Deduction amount");
        return Double.parseDouble(get_subscription_test_data(get_section_name(), "deductionOneTimeAmount"));
    }

    public Double get_recurring_deduction_amount() {
        log.info("Verify the Deductions plus tax rate");
        return Double.parseDouble(get_subscription_test_data(get_section_name(), "deductionRecurringAmount"));
    }

    public String calculate_deductions_with_abc_rates() {
        log.info("Calculate the Deductions plus tax rate");
        double basePercentageOnPayAmount = (Double.parseDouble(get_subscription_test_data(get_section_name(), "payAmount")) * get_abc_rate_base_percentage()) / 100;
        return String.valueOf(basePercentageOnPayAmount + get_abc_rate_per_transaction() + get_one_time_deduction_amount() + get_recurring_deduction_amount());
    }

    public String calculate_deductions_without_abc_rates() {
        log.info("Calculate the Deductions without tax rate");
        return String.valueOf(get_one_time_deduction_amount() + get_recurring_deduction_amount());
    }

    public UnoAppAccountDetailsTabPage verify_reimbursements(String expectedReimbursements) {
        log.info("Verify the Reimbursements");
        verify_element_by_text(get_row_by_column_name("reimbursements"), expectedReimbursements);
        return me();
    }

    public UnoAppAccountDetailsTabPage verify_deposits(String expectedDeposits) {
        log.info("Verify the Deposits");
        verify_element_by_text(get_row_by_column_name("deposits"), expectedDeposits);
        return me();
    }

    public UnoAppAccountDetailsTabPage verify_available_balance(String expectedAvailableBalance) {
        log.info("Verify the available balance");
        verify_element_by_text(get_row_by_column_name("balance"), expectedAvailableBalance);
        return me();
    }
}