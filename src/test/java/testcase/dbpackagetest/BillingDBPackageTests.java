package testcase.dbpackagetest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.dbpackagepage.PackagePage;
import ui.AbstractAutoUITest;
import java.util.List;
import java.util.Map;

@Slf4j
public class BillingDBPackageTests extends AbstractAutoUITest {
    List<Map<String, Object>> automationTestResult = null;


    @BeforeClass
    public void getTestDataFromAutomationResults() {
        log.info("..........Getting Data from Automation Result Data Set from DB...........");
        setTestResultOnXRay("A30TP-18547");
        automationTestResult = getPageWithoutUI(PackagePage.class)
                .get_db_automation_test_data(getDBClient("RedShiftQADB"));
    }

    @Test
    public void verifyAutomationTestResultCount() {
        log.info("................Verifying Automation Test Data count is greater than zero............");
        setTestResultOnXRay("A30TP-18551");
        getPageWithoutUI(PackagePage.class)
                .verify_automation_test_data_count_greater_than_zero(automationTestResult);
    }

    @Test
    public void verifyTestPassFlagAgreementPayorProfile() {
        log.info(".......Verifying Test Pass Flag for Agreement Payor Profile..........");
        setTestResultOnXRay("A30TP-12333");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.agreement_payor_profile source to target");

    }

    @Test
    public void verifyTestPassCriticalFlagAgreementPayorProfile() {
        log.info(".......Verifying Test Pass Critical Flag for Agreement Payor Profile..........");
        setTestResultOnXRay("A30TP-12334");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.agreement_payor_profile source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagClientAccountEntry() {
        log.info(".......Verifying Test Pass Flag for Client Account Entry..........");
        setTestResultOnXRay("A30TP-11808");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_account_entry source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagClientAccountEntry() {
        log.info(".......Verifying Test Pass Critical Flag for Client Account Entry..........");
        setTestResultOnXRay("A30TP-11809");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_account_entry source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagClientAccount() {
        log.info(".......Verifying Test Pass Flag for Client Account..........");
        setTestResultOnXRay("A30TP-11764");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_account source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagClientAccount() {
        log.info(".......Verifying Test Pass Critical Flag for Client Account..........");
        setTestResultOnXRay("A30TP-11807");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_account source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagSettlementEvaluationSchedule() {
        log.info(".......Verifying Test Pass Flag for Settlement Evaluation Schedule..........");
        setTestResultOnXRay("A30TP-11816");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.settlement_evaluation_schedule source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagSettlementEvaluationSchedule() {
        log.info(".......Verifying Test Pass Critical Flag for Settlement Evaluation Schedule..........");
        setTestResultOnXRay("A30TP-11817");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.settlement_evaluation_schedule source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagCategoryTaxCode() {
        log.info(".......Verifying Test Pass Flag for Category Tax Code..........");
        setTestResultOnXRay("A30TP-12336");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.category_tax_code source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagCategoryTaxCode() {
        log.info(".......Verifying Test Pass Critical Flag for Category Tax Code..........");
        setTestResultOnXRay("A30TP-12338");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.category_tax_code source to target");
    }

    @Test
    public void verifyTestPassFlagDepositEvent() {
        log.info(".......Verifying Test Pass Flag for Deposit Event..........");
        setTestResultOnXRay("A30TP-12337");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.deposit_event source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagDepositEvent() {
        log.info(".......Verifying Test Pass Critical Flag for Deposit Event..........");
        setTestResultOnXRay("A30TP-12339");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.deposit_event source to target");
    }

    @Test
    public void verifyTestPassFlagDeposit() {
        log.info(".......Verifying Test Pass Flag for Deposit..........");
        setTestResultOnXRay("A30TP-14173");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.deposit source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagDeposit() {
        log.info(".......Verifying Test Pass Critical Flag for Deposit..........");
        setTestResultOnXRay("A30TP-14174");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.deposit source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagStatementEvaluationSchedule() {
        log.info(".......Verifying Test Pass Flag for Statement Evaluation Schedule..........");
        setTestResultOnXRay("A30TP-14412");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.statement_evaluation_schedule source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagStatementEvaluationSchedule() {
        log.info(".......Verifying Test Pass Critical Flag for Statement Evaluation Schedule..........");
        setTestResultOnXRay("A30TP-14413");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.statement_evaluation_schedule source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagFeeSchedule() {
        log.info(".......Verifying Test Pass Flag for Fee Schedule..........");
        setTestResultOnXRay("A30TP-14415");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.fee_schedule source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagFeeSchedule() {
        log.info(".......Verifying Test Pass Critical Flag for Fee Schedule..........");
        setTestResultOnXRay("A30TP-14416");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.fee_schedule source to target");
    }

    @Test
    public void verifyTestPassFlagStatement() {
        log.info(".......Verifying Test Pass Flag for Statement..........");
        setTestResultOnXRay("A30TP-14290");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.statement source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagStatement() {
        log.info(".......Verifying Test Pass Critical Flag for Statement..........");
        setTestResultOnXRay("A30TP-14296");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.statement source to target");
    }

    @Test
    public void verifyTestPassFlagSubscription() {
        log.info(".......Verifying Test Pass Flag for Subscription..........");
        setTestResultOnXRay("A30TP-12327");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.subscription source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagSubscription() {
        log.info(".......Verifying Test Pass Critical Flag for Subscription..........");
        setTestResultOnXRay("A30TP-12328");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.subscription source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagMemberTransactionFeeConfigClientAccount() {
        log.info(".......Verifying Test Pass Flag for Member Transaction Fee Config Client Account..........");
        setTestResultOnXRay("A30TP-16416");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.member_transaction_fee_config_client_account source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagMemberTransactionFeeConfigClientAccount() {
        log.info(".......Verifying Test Pass Critical Flag for Member Transaction Fee Config Client Account..........");
        setTestResultOnXRay("A30TP-16411");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.member_transaction_fee_config_client_account source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassFlagScheduledFeeConfigClientAccount() {
        log.info(".......Verifying Test Pass Flag for Member Transaction Fee Config Client Account..........");
        setTestResultOnXRay("A30TP-16839");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config_client_account source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagScheduledFeeConfigClientAccount() {
        log.info(".......Verifying Test Pass Critical Flag for Member Transaction Fee Config Client Account..........");
        setTestResultOnXRay("A30TP-16840");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config_client_account source to target");
    }

    @Test
    public void verifyTestPassFlagScheduledFee() {
        log.info(".......Verifying Test Pass Flag for Scheduled Fee..........");
        setTestResultOnXRay("A30TP-17555");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.scheduled_fee source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagScheduledFee() {
        log.info(".......Verifying Test Pass Critical Flag for Scheduled Fee..........");
        setTestResultOnXRay("A30TP-17557");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.scheduled_fee source to target");
    }


    @Test(enabled = false)
    public void verifyTestPassFlagReattemptPolicy() {
        log.info(".......Verifying Test Pass Flag for Scheduled Fee..........");
        setTestResultOnXRay("A30TP-17561");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.reattempt_policy source to target");
    }

    @Test(enabled = false)
    public void verifyTestPassCriticalFlagReattemptPolicy() {
        log.info(".......Verifying Test Pass Critical Flag for Scheduled Fee..........");
        setTestResultOnXRay("A30TP-17562");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.reattempt_policy source to target");
    }


    @Test
    public void verifyTestPassFlagClientTransactionFee() {
        log.info(".......Verifying Test Pass Flag for Client Transaction Fee ..........");
        setTestResultOnXRay("A30TP-17565");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_transaction_fee source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagClientTransactionFee() {
        log.info(".......Verifying Test Pass Critical Flag for Client Transaction Fee ..........");
        setTestResultOnXRay("A30TP-17566");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_transaction_fee source to target");
    }


    @Test
    public void verifyTestPassFlagSubscriptionItem() {
        log.info(".......Verifying Test Pass Flag for Subsription Item..........");
        setTestResultOnXRay("A30TP-17565");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.subscription_item source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagSubscriptionItem() {
        log.info(".......Verifying Test Pass Critical Flag for Subsription Item..........");
        setTestResultOnXRay("A30TP-17566");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.subscription_item source to target");
    }

    @Test
    public void verifyTestPassFlagScheduledFeeConfig() {
        log.info(".......Verifying Test Pass Flag for Subsription Item..........");
        setTestResultOnXRay("A30TP-17565");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagScheduledFeeConfig() {
        log.info(".......Verifying Test Pass Critical Flag for Subsription Item..........");
        setTestResultOnXRay("A30TP-17566");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountBankHoliday() {
        log.info(".......Verify Test Pass Flag of Bank Holiday..........");
        setTestResultOnXRay("A30TP-11720");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.bank_holiday source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountBankHoliday() {
        log.info(".......Verify Test Critical Pass Flag of Bank Holiday..........");
        setTestResultOnXRay("A30TP-19748");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.bank_holiday source to target");
    }

    @Test
    public void verifyPassFlagCountClientProfile() {
        log.info(".......Verify Test Pass Flag of Client Profile..........");
        setTestResultOnXRay("A30TP-11792");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_profile source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountClientProfile() {
        log.info(".......Verify Test Critical Pass Flag of Client Profile..........");
        setTestResultOnXRay("A30TP-19750");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_profile source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountSubPayMethod() {
        log.info(".......Verify Test Pass Flag of Subscription Payment Method..........");
        setTestResultOnXRay("A30TP-11794");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.subscription_payment_method source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountSubPayMethod() {
        log.info(".......Verify Test Critical Pass Flag of Subscription Payment Method..........");
        setTestResultOnXRay("A30TP-19903");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.subscription_payment_method source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountCountryLookUp() {
        log.info(".......Verify Test Pass Flag of Country Lookup..........");
        setTestResultOnXRay("A30TP-11960");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.country_lookup source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountCountryLookUp() {
        log.info(".......Verify Test Critical Pass Flag of Country Lookup..........");
        setTestResultOnXRay("A30TP-19905");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.country_lookup source to target");
    }

    @Test
    public void verifyPassFlagCountDeposit() {
        log.info(".......Verify Test Pass Flag of Deposit..........");
        setTestResultOnXRay("A30TP-12054");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.deposit source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountDeposit() {
        log.info(".......Verify Test Critical Pass Flag of Deposit..........");
        setTestResultOnXRay("A30TP-19907");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.deposit source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountRenewOption() {
        log.info(".......Verify Test Pass Flag of Renewal Option..........");
        setTestResultOnXRay("A30TP-12084");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.renewal_options source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountRenewOption() {
        log.info(".......Verify Test Critical Pass Flag of Renewal Option..........");
        setTestResultOnXRay("A30TP-19910");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.renewal_options source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountStateLookUp() {
        log.info(".......Verify Test Pass Flag of State Lookup..........");
        setTestResultOnXRay("A30TP-12094");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.state_lookup source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountStateLookUp() {
        log.info(".......Verify Test Critical Pass Flag of State Lookup..........");
        setTestResultOnXRay("A30TP-19911");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.state_lookup source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountTaxCodeLookUp() {
        log.info(".......Verify Test Pass Flag of Tax Code Lookup..........");
        setTestResultOnXRay("A30TP-12100");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.tax_code_lookup source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountTaxCodeLookUp() {
        log.info(".......Verify Test Critical Pass Flag of Tax Code Lookup..........");
        setTestResultOnXRay("A30TP-19912");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.tax_code_lookup source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountCanActionCode() {
        log.info(".......Verify Test Pass Flag of Cancel Action Code..........");
        setTestResultOnXRay("A30TP-12320");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.cancel_action_code source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountCanActionCode() {
        log.info(".......Verify Test Critical Pass Flag of Cancel Action Code..........");
        setTestResultOnXRay("A30TP-19940");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.cancel_action_code source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountPayEvent() {
        log.info(".......Verify Test Pass Flag of Payment Event..........");
        setTestResultOnXRay("A30TP-14023");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.payment_event source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountPayEvent() {
        log.info(".......Verify Test Critical Pass Flag of Payment Event..........");
        setTestResultOnXRay("A30TP-19952");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.payment_event source to target");
    }

    @Test
    public void verifyPassFlagCountPayMethod() {
        log.info(".......Verify Test Pass Flag of Payment Method..........");
        setTestResultOnXRay("A30TP-14212");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.payment_method source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountPayMehod() {
        log.info(".......Verify Test Critical Pass Flag of Payment Method..........");
        setTestResultOnXRay("A30TP-19954");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.payment_method source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountPayMethodAccount() {
        log.info(".......Verify Test Pass Flag of Payment Method Account..........");
        setTestResultOnXRay("A30TP-14382");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.payment_method_account source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountPayMehodAccount() {
        log.info(".......Verify Test Critical Pass Flag of Payment Method Account..........");
        setTestResultOnXRay("A30TP-19956");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.payment_method_account source to target");
    }

    @Test
    public void verifyPassFlagCountClientTranFeeConfig() {
        log.info(".......Verify Test Pass Flag of Client Transaction Fee Config..........");
        setTestResultOnXRay("A30TP-14384");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_transaction_fee_config source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountClientTranFeeConfig() {
        log.info(".......Verify Test Critical Pass Flag of Client Transaction Fee Config..........");
        setTestResultOnXRay("A30TP-19958");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_transaction_fee_config source to target");
    }

    @Test
    public void verifyPassFlagCountReasonCode() {
        log.info(".......Verify Test Pass Flag of Reason Code..........");
        setTestResultOnXRay("A30TP-14387");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.reason_code source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountReasonCode() {
        log.info(".......Verify Test Critical Pass Flag of Reason Code..........");
        setTestResultOnXRay("A30TP-19959");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.reason_code source to target");
    }

    @Test
    public void verifyPassFlagCountSchFeeConfig() {
        log.info(".......Verify Test Pass Flag of Scheduled Fee Config..........");
        setTestResultOnXRay("A30TP-14391");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountSchFeeConfig() {
        log.info(".......Verify Test Critical Pass Flag of Scheduled Fee Config..........");
        setTestResultOnXRay("A30TP-19959");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountClientPercentage() {
        log.info(".......Verify Test Pass Flag of Client Percentage..........");
        setTestResultOnXRay("A30TP-16034");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_percentage source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountClientPercentage() {
        log.info(".......Verify Test Critical Pass Flag of Client Percentage..........");
        setTestResultOnXRay("A30TP-19964");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_percentage source to target");
    }

    @Test(enabled = false)
    public void verifyPassFlagCountClientTranFeeConfigClientAcc() {
        log.info(".......Verify Test Pass Flag of Client Transaction Fee Config Client Account ..........");
        setTestResultOnXRay("A30TP-16552");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.client_transaction_fee_config_client_account source to target");
    }

    @Test(enabled = false)
    public void verifyCriticalPassFlagCountClientTranFeeConfigClientAcc() {
        log.info(".......Verify Test Critical Pass Flag of Client Transaction Fee Config Client Account..........");
        setTestResultOnXRay("A30TP-19965");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.client_transaction_fee_config_client_account source to target");
    }

    @Test
    public void verifyPassFlagCountPayment() {
        log.info(".......Verify Test Pass Flag of Payment ..........");
        setTestResultOnXRay("A30TP-18606");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.payment source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountPayment() {
        log.info(".......Verify Test Critical Pass Flag of Payment..........");
        setTestResultOnXRay("A30TP-18605");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.payment source to target");
    }

    @Test
    public void verifyPassFlagCountabcbillingScheduledFeeConfig() {
        log.info(".......Verify Test Pass Flag of Scheduled Fee Config..........");
        setTestResultOnXRay("A30TP-18600");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountScheduledFeeConfig() {
        log.info(".......Verify Test Critical Pass Flag of Scheduled Fee Config..........");
        setTestResultOnXRay("A30TP-18599");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.scheduled_fee_config source to target");
    }

    @Test
    public void verifyPassFlagCountabcbillingSettlement() {
        log.info(".......Verify Test Pass Flag of Settlement..........");
        setTestResultOnXRay("A30TP-18581");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.settlement source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountAbcbillingSettlement() {
        log.info(".......Verify Test Critical Pass Flag of Settlement..........");
        setTestResultOnXRay("A30TP-18580");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.settlement source to target");
    }


    @Test
    public void verifyPassFlagCountabcbillingAgreement() {
        log.info(".......Verify Test Pass Flag of Agreement..........");
        setTestResultOnXRay("A30TP-12330");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.agreement source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountAbcbillingAgreement() {
        log.info(".......Verify Test Critical Pass Flag of Agreement..........");
        setTestResultOnXRay("A30TP-12331");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.agreement source to target");
    }

    @Test
    public void verifyPassFlagPayorProfile() {
        log.info(".......Verify Test Pass Flag of Payor Profile..........");
        setTestResultOnXRay("A30TP-11814");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "abcbilling.payor_profile source to target");
    }

    @Test
    public void verifyCriticalPassFlagPayorProfile() {
        log.info(".......Verify Test Critical Pass Flag of Payor Profile..........");
        setTestResultOnXRay("A30TP-11815");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "abcbilling.payor_profile source to target");
    }

}

