package testcase.unoapptestcases.endtoendscenario.scenario.AccountDetailsTab.AfterScheduler;
/*
Created By: Shilpi Gupta
Date: 01/08/2020
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppAccountDetailsTabPage;
import pagetest.unoapppage.UnoAppDashBoardPage;
import pagetest.unoapppage.UnoAppLocationPage;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;
import static helper.DataProviderSection.ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK;
import static util.UtilityGeneric.set_section_name;


@Issue("A30TP-32829")
@Slf4j
public class VerifyAccountDetailsTabForPastMonthlyAutoRenewToOpen extends AbstractAutoUITest {

    @BeforeClass
    public void setSectionData() {
        log.info("Setting data for the test execution");
        setTestResultOnXRay(" A30TP-14514");
        set_section_name(ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK);
    }

    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    public void unoAppLogin() {
        log.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Search organization by name")
    public void searchOrganization() {
        log.info("Search organization has started");
        setTestResultOnXRay("A30TP-17391");
        getPage(UnoAppDashBoardPage.class)
                .search_organization_by_name(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK, ORGANIZATION_NAME))
                .click_business_link();
    }

    @Test
    @Description("Select location by name")
    public void selectLocation() {
        log.info("Select location has started");
        setTestResultOnXRay("A30TP-33692");
        getPage(UnoAppLocationPage.class)
                .select_location(getTestData(SUBSCRIPTION_INI, ACCOUNT_DETAIL_PAST_WITHOUT_DWNPAY_AUTORENEWTOOPEN_MONTHLY_BANK,LOCATION_NAME));

    }

    @Test
    @Description("Select account details tab")
    public void selectAccountDetailsTab() {
        log.info("Select account detail has started");
        setTestResultOnXRay("A30TP-33695");
        getPage(UnoAppAccountDetailsTabPage.class)
                .select_account_details_tab();
    }


    @Test
    @Description("Select plus icon tab")
    public void selectPlusIcon() {
        log.info("Selecting plus icon to expand the account details for the location account");
        setTestResultOnXRay("A30TP-33697");
        getPage(UnoAppAccountDetailsTabPage.class)
                .click_plus_icon();
    }

    @Test
    @Description("Verify Date")
    public void verifyDate() {
        log.info("Verification of date has started");
        setTestResultOnXRay("A30TP-33699");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_date(getPage(UnoAppAccountDetailsTabPage.class).fetch_current_date("MM/dd/YYYY"));
    }

    @Test
    @Description("Verify Billed Amount")
    public void verifyBilledAmount() {
        log.info("Verification of billed amount has started");
        setTestResultOnXRay("A30TP-33703");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_billed_amount(getPage(UnoAppAccountDetailsTabPage.class).get_billing_amount());
    }

    @Test
    @Description("Verify Payments Received Amount")
    public void verifyReceivedAmount() {
        log.info("Verification of payment details amount has started");
        setTestResultOnXRay("A30TP-33708");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_payments_received(getPage(UnoAppAccountDetailsTabPage.class).get_billing_amount());
    }

    @Test
    @Description("Verify available balance when deduction and reimbursement is not applied ")
    public void verifyAvailableBalance() {
        log.info("Verification of available balance has started");
        setTestResultOnXRay("A30TP-33712");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_available_balance(getPage(UnoAppAccountDetailsTabPage.class).get_billing_amount());
    }

    @Test
    @Description("Verify deduction amount ")
    public void verifyDeduction() {
        log.info("Verification of deduction amount has started");
        setTestResultOnXRay("A30TP-33718");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_deductions(DEDUCTION_AMOUNT_ZERO);
    }

    @Test
    @Description("Verify reimbursement amount")
    public void verifyReimbursement() {
        log.info("Verification of reimbursement amount has started");
        setTestResultOnXRay("A30TP-33720");
        getPage(UnoAppAccountDetailsTabPage.class)
                .verify_reimbursements(REIMBURSEMENT_AMOUNT_ZERO);
    }
}