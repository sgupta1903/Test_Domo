package testcase.unoapptestcases.endtoendscenario.scenario.invoiceverification;


/*
Created By: Shilpi Gupta
Date: 10/04/2019
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppDashBoardPage;
import pagetest.unoapppage.UnoAppLoginPage;
import pagetest.unoapppage.UnoAppMemberPage;
import pagetest.unoapppage.UnoAppWalletTabPage;
import ui.AbstractAutoUITest;

/*
Note:
1. After scheduler run, invoice verification will work. This is test for dummy data to verify invoice generation
 */

@Issue("A30TP-17394")
@Slf4j
public class VerifyInvoiceCreated_Test extends AbstractAutoUITest {

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
    @Description("Verify invoice generation")
    @TmsLink("A30TP-17391")
    public void searchOrganization() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-17391");
        getPage(UnoAppDashBoardPage.class)
                .search_org_name_for_invoice_verification()
                .verify_organization_search_success();
    }

    @Test
    @Description("Select member")
    @TmsLink("A30TP-17392")
    public void selectOrganizationMember() {
        log.info("Selecting member");
        setTestResultOnXRay("A30TP-17392");
        getPage(UnoAppDashBoardPage.class)
                .select_member_icon();
        getPage(UnoAppMemberPage.class)
                .verify_member_page_displayed()
                .select_member_by_agreement_id();

    }

    @Test
    @Description("Verify invoices are generated")
    @TmsLink("A30TP-17394")
    public void verifyInvoiceDetails() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-17394");
        getPage(UnoAppWalletTabPage.class)
                .select_wallet_tab()
                .click_plus_icon()
                .verify_invoice_displayed();
    }

    @Test
    @Description("Verify statements are generated")
    @TmsLink("A30TP-19014")
    public void verifyStatementDetails() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-19014");
        getPage(UnoAppWalletTabPage.class)
                .verify_statement_displayed();
    }

    @Test
    @Description("Verify reference number are generated")
    @TmsLink("A30TP-19020")
    public void verifyReferenceNumberDisplayed() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-19020");
        getPage(UnoAppWalletTabPage.class)
                .verify_reference_number_displayed();

    }

    @Test
    @Description("Verify reference number are generated")
    @TmsLink("A30TP-19021")
    public void verifyReferenceNumberDetails() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-19021");
        getPage(UnoAppWalletTabPage.class)
                .verify_reference_number_not_null();

    }

    @Test
    @Description("Verify reference number are generated")
    @TmsLink("A30TP-19022")
    public void verifyCreatedDate() {
        log.info("Verifying invoice generation");
        setTestResultOnXRay("A30TP-19022");
        getPage(UnoAppWalletTabPage.class)
                .verify_created_date();
    }
}