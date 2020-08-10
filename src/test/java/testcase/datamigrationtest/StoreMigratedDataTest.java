package testcase.datamigrationtest;
/*
Created By: Praveen
Updated By: Sapna
Date: 02/12/2020
*/

import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.obcUIPage.*;
import ui.AbstractAutoUITest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class StoreMigratedDataTest extends AbstractAutoUITest {
    private String clubNumber, memberNumber, filePath, sheetName;
    Map<String, Object> getData = new LinkedHashMap<>();

    public StoreMigratedDataTest(String clubNumber, String memberNumber, String filePath, String sheetName) {
        this.clubNumber = clubNumber;
        this.memberNumber = memberNumber;
        this.filePath = filePath;
        this.sheetName = sheetName;
    }

    @Test(description = "Login into the OBC Application")
    @TmsLink("A30TP-11053")
    public void loginObcUiApplication() {
        log.info("Login 'OBC' Application");
        setTestResultOnXRay("A30TP-11053");
        getPage(ObcAppLoginPage.class)
                .login_obc_application()
                .verify_successful_login();
    }

    @Test(description = "Store Location Details from OBC Application")
    @TmsLink("A30TP-36239")
    public void storeClubDetails() {
        log.info("Storing  Club Details from 'OBC' Application");
        setTestResultOnXRay("A30TP-36239");
        getPage(EnterClubPage.class)
                .enter_club_no(clubNumber);
        getPage(SelectMemberAccountPage.class)
                .enter_member_number(memberNumber);
        getPage(AccountSummaryPage.class)
                .store_club_details(getData);
    }

    @Test(description = "Verify Member Status from OBC Application")
    @TmsLink("A30TP-36237")
    public void verifyMemberStatus() {
        log.info("Verify  Member Status from 'OBC' Application");
        setTestResultOnXRay("A30TP-36237");
        getPage(AccountSummaryPage.class)
                .verify_member_status();
    }

    @Test(description = "Store Member Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36238")
    public void storeMemberDetails() {
        log.info("Storing Member Details from OBC Application");
        setTestResultOnXRay("A30TP-36238");
        getPage(AccountSummaryPage.class)
                .store_member_name(getData)
                .store_member_details(getData);
    }

    @Test(description = "Store Agreement Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36244")
    public void storeAgreementDetails() {
        log.info("Storing Agreement Details from OBC Application");
        setTestResultOnXRay("A30TP-36244");
        getPage(AccountSummaryPage.class)
                .store_agreement_details(getData);
    }

    @Test(description = "Store Subscription Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36247")
    public void storeSubscriptionDetails() {
        log.info("Storing  Subscription Details from OBC Application");
        setTestResultOnXRay("A30TP-36247");
        getPage(AccountSummaryPage.class)
                .store_subscription_details(getData);
    }

    @Test(description = "Store Payment Method Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36253")
    public void storePaymentMethodDetails() {
        log.info("Storing Payment Method Details from OBC Application");
        setTestResultOnXRay("A30TP-36253");
        getPage(AccountSummaryPage.class)
                .store_payment_method_details(getData);
    }

    @Test(description = "Store Additional Information of Member from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36215")
    public void storeAdditionalInformationDetails() {
        log.info("Store Additional Information of Member from OBC Application");
        setTestResultOnXRay("A30TP-36215");
        getPage(AccountSummaryUpdatePage.class)
                .store_additional_information();
    }

    @Test(description = "Write Migrated data into Excel", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36254")
    public void writeMigratedDataInExcel() throws IOException {
        log.info("Write Migrated data into Excel");
        setTestResultOnXRay("A30TP-36254");
        getExcelClientWithoutSection(filePath, sheetName)
                .write_excel(getData, filePath);
    }

    @Test(description = "Logout OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-14057")
    public void obcUILogout() {
        log.info("Logout OBC Application");
        setTestResultOnXRay("A30TP-14057");
        getPage(AccountSummaryPage.class)
                .logout_obcui();
    }
}


