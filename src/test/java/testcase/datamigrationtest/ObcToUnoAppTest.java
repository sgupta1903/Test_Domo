package testcase.datamigrationtest;


import datastore.*;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pagetest.datamigrationpage.*;
import pagetest.obcUIPage.*;
import pagetest.paymentgatewaypage.CompanyTabPage;
import pagetest.paymentgatewaypage.PaymentGatewayLoginPage;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;

import java.util.LinkedHashMap;
import java.util.Map;

import static helper.AppConstants.ABC_FINANCIAL_BILLING_SERVICES;

@Slf4j
public class ObcToUnoAppTest extends AbstractAutoUITest {
    private String clubNumber, memberNumber;
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    StoreClubDetails storeClubDetails;
    StoreLateFeeDetails storeLateFeeDetails;
    StoreServiceFeeDetails storeServiceFeeDetails;
    StoreMemberDetails storeMemberDetails;
    StoreSubscriptionDetails storeSubscriptionDetails;
    StorePaymentMethodDetails storePaymentMethodDetails;
    StoreSecondaryMemberDetails storeSecondaryMemberDetails;
    StoreAgreementDetails storeAgreementDetails;
    StoreAdditionalMemberDetails storeAdditionalMemberDetails;
    StoreRenewalDetails storeRenewalDetails;
    StoreSecondaryPaymentMethodDetails storeSecondaryPaymentMethodDetails;
    StorePcsDetails storePcsDetails;
    StoreFreezeStatus storeFreezeStatus;
    StoreMemberNoteDetails storeMemberNoteDetails;

    Map<String, Object> getData = new LinkedHashMap<>();


    public ObcToUnoAppTest(String clubNumber, String memberNumber) {
        this.clubNumber = clubNumber;
        this.memberNumber = memberNumber;
    }


    @Test(description = "Login 'OBC' Application " )
    @Step("Login with {clubNumber} and {memberNumber}")
    @TmsLink("A30TP-11053")
    public void loginObcUiApplication() {
        log.info("Login into the OBC Application");
        setTestResultOnXRay("A30TP-11053");
        getPage(ObcAppLoginPage.class)
                .login_obc_application()
                .verify_successful_login();
    }

    @Test(description = "Store Club Details from OBC Application")
    @TmsLink("A30TP-10824")
    public void storeClubDetails() {
        log.info("Storing Club Details from OBC UI Application ");
        setTestResultOnXRay("A30TP-10824");
        getPage(EnterClubPage.class)
                .enter_club_no(clubNumber);
        getPage(SelectMemberAccountPage.class)
                .enter_member_number(memberNumber);
        storeClubDetails = getPage(AccountSummaryPage.class)
                .store_club_details(getData);
    }

    @Test(description = "Verify Member Status from OBC Application")
    @TmsLink("A30TP-35732")
    public void verifyMemberStatus() {
        log.info("Verify Member Status from OBC Application");
        setTestResultOnXRay("A30TP-35732");
        getPage(AccountSummaryPage.class)
                .verify_member_status();
    }

    @Test(description = "Store the late fee details from OBC", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-44388")
    public void storeLateFeeDetails() {
        log.info("Store the late fee details from OBC");
        setTestResultOnXRay("A30TP-44388");
        storeLateFeeDetails = getPage(AccountSummaryPage.class)
                .store_late_fee_details();
    }

    @Test(description = "Store the service fee details from OBC", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-44504")
    public void storeServiceFeeDetails() {
        log.info("Store the service fee details from OBC");
        setTestResultOnXRay("A30TP-44504");
        storeServiceFeeDetails = getPage(AccountSummaryPage.class)
                .store_service_fee_details();
    }

    @Test(description = "Store Member Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-11071")
    public void storeMemberDetails() {
        log.info("Storing Member Details from OBC Application");
        setTestResultOnXRay("A30TP-11071");
        storeMemberDetails = getPage(AccountSummaryPage.class)
                //.store_member_name(getData)
                .store_member_details(getData);
    }

    @Test(description = "Store Subscription Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-11905")
    public void storeSubscriptionDetails() {
        log.info("Storing  Subscription Details from OBC Application");
        setTestResultOnXRay("A30TP-11905");
        storeSubscriptionDetails = getPage(AccountSummaryPage.class)
                .store_subscription_details(getData);
    }

    @Test(description = "Store Agreement Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-11859")
    public void storeAgreementDetails() {
        log.info("Storing Agreement Details from OBC UI Application");
        setTestResultOnXRay("A30TP-11859");
        storeAgreementDetails = getPage(AccountSummaryPage.class)
                .store_agreement_details(getData);
    }

    @Test(description = "Store Payment Method Details from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-13893")
    public void storePaymentMethodDetails() {
        log.info("Storing Payment Method Details from OBC UI Application");
        setTestResultOnXRay("A30TP-13893");
        storePaymentMethodDetails = getPage(AccountSummaryPage.class)
                .store_payment_method_details(getData);
    }

    @Test(description = "Store Secondary Member's name from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38807")
    public void storeSecondaryMemberName() {
        log.info("Storing Secondary Member's name from OBC Application");
        setTestResultOnXRay("A30TP-38807");
        storeSecondaryMemberDetails = getPage(AccountSummaryPage.class)
                .store_secondary_member_name();
    }

    @Test(description = "Store Pending Cancel Status from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38808")
    public void storePcsStatus() {
        log.info("Storing Pending Cancel Status from OBC UI Application");
        setTestResultOnXRay("A30TP-38808");
        storePcsDetails = getPage(AccountSummaryPage.class)
                .store_pcs();
    }

    @Test(description = "Store Freeze Status from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38809")
    public void storeFreezeStatus() {
        log.info("Storing Freeze Status from OBC UI Application");
        setTestResultOnXRay("A30TP-38809");
        storeFreezeStatus = getPage(AccountSummaryPage.class)
                .store_freeze();
    }

    @Test(description = "Store Additional Information of Member from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-14127")
    public void storeAdditionalInformationDetails() {
        log.info("Store Additional Information of Member from OBC Application");
        setTestResultOnXRay("A30TP-14127");
        storeAdditionalMemberDetails = getPage(AccountSummaryUpdatePage.class)
                .store_additional_information();
    }

    @Test(description = "Store Renewal Flag and Information of Member from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36523")
    public void storeRenewalFlagAndInformation() {
        log.info("Store Renewal Flag and Information of Member from OBC Application");
        setTestResultOnXRay("A30TP-36523");
        storeRenewalDetails = getPage(RenewalInformationPage.class)
                .store_renew_flag_and_information();
    }

    @Test(description = "Store Secondary Payment Method Details of Member from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-41598")
    public void storeSecondaryPaymentMethodDetails() {
        log.info("Store Secondary Payment Method Details of Member from OBC Application");
        setTestResultOnXRay("A30TP-41598");
        storeSecondaryPaymentMethodDetails = getPage(SecondaryPaymentMethodPage.class)
                .store_secondary_payment_method();


    }

   /* @Test(description = "Store Member notes from OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-11071")
    public void storeMemberNotesDetails() {
        log.info("Storing Member notes from OBC Application");
        setTestResultOnXRay("A30TP-11071");
        storeMemberNoteDetails=getPage(AccountSummaryPage.class)
                .store_member_note_details();
    }*/

    @Test(description = "Logout from the OBC Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-14057")
    public void obcUiLogout() {
        log.info("Logout from the OBC UI Application");
        setTestResultOnXRay("A30TP-14057");
        getPage(AccountSummaryPage.class)
                .logout_obcui();

    }

    @Test(description = "Login 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-13920")
    public void loginUnoApplication() {
        log.info("Login 'UNO' Application");
        setTestResultOnXRay("A30TP-13920");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test(description = "Verifying Club Details from OBC UI to the UNO Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32429")
    public void verifyClubDetails() {
        log.info("Verifying Club Details from OBC UI to the UNO Application");
        setTestResultOnXRay("A30TP-32429");
        setTestResultOnXRay("A30TP-32429");
        getPage(SearchLocationPage.class)
                .verify_club_details_obc(storeClubDetails, clubNumber);
    }

    @Test(description = "Verifying Organisation Details from OBC UI to the 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38811")
    public void verifyOrgDetails() {
        log.info("Verifying Organisation Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-38811");
        getPage(SearchOrgPage.class)
                .verify_org_details_obc(storeClubDetails);
    }

    @Test(description = "Verifying Member Details from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32428")
    public void verifyMemberDetails() {
        log.info("Verifying Member Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-32428");
        getPage(MemberPage.class)
                .verify_member_details_obc(storeMemberDetails, storeClubDetails);
    }

    @Test(description = "Verifying Member's Additional Information from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32432")
    public void verifyMemberAdditionalInformation() {
        log.info("Verifying Member's Additional Information from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-32432");
        getPage(MemberPage.class)
                .verify_additional_information_obc(storeMemberDetails, storeAdditionalMemberDetails);
    }

 /*   @Test(description = "Verifying Member Notes from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32428")
    public void verifyMemberNotes() {
        log.info("Verifying Member Notes from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-32428");
        getPage(MemberPage.class)
                .verify_member_notes_obc(storeMemberNoteDetails);
    }*/

    @Test(description = "Verifying Member Agreement Details from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-31325")
    public void verifyAgreementDetails() {
        log.info("Verifying Member Agreement Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-31325");
        getPage(AgreementPage.class)
                .verify_agreement_details_obc(storeAgreementDetails, storeRenewalDetails);
    }

    @Test(description = "Verifying Member Subscription Details from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-31326")
    public void verifySubscriptionDetails() {
        log.info("Verifying Member Subscription Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-31326");
        getPage(SubscriptionPage.class)
                .verify_subscription_details_obc(storeSubscriptionDetails);
    }

    @Test(description = "Verifying Member Renewal Subscription Details from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32431")
    public void verifyRenewalSubscriptionDetails() {
        log.info("Verifying Member Renewal Subscription Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-32431");
        getPage(SubscriptionPage.class)
                .verify_renewal_subscription_details_obc(storeRenewalDetails);
    }


    @Test(description = "Verifying Pending Cancel Status from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38819")
    public void verifyPcsStatus() {
        log.info("Verifying Pending Cancel Status from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-38819");
        getPage(SubscriptionPage.class)
                .verify_subscription_pcs_obc(storePcsDetails);
    }

    @Test(description = "Verifying  Subscription freeze Status from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38820")
    public void verifyFreezeStatus() {
        log.info("Verifying Subscription freeze Status from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-38820");
        getPage(SubscriptionPage.class)
                .verify_subscription_freeze_obc(storeFreezeStatus);
    }


    @Test(description = "Verifying Member's Payment Method Details from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-32430")
    public void verifyPaymentMethodDetails() {
        log.info("Verifying Member's Payment Method Details from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-32430");
        getPage(WalletPage.class)
                .verify_wallet_details_obc(storePaymentMethodDetails, storeMemberDetails);
    }

    @Test(description = "Verifying Member Payment Method Statement to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38459")
    public void verifyStatementPaymentMethod() {
        log.info("Verifying Member Payment Method Statement to 'UNO' Application");
        setTestResultOnXRay("A30TP-38459");
        getPage(WalletPage.class)
                .verify_statement_payment_method();
    }


    @Test(description = "Verifying Secondary  Payment Method  to UNO Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-36520")
    public void verifySecondaryPaymentMethod() {
        log.info("Verifying Secondary  Payment Method  to UNO Application");
        setTestResultOnXRay("A30TP-36520");
        getPage(WalletPage.class)
                .verify_secondary_payment_method(storeSecondaryPaymentMethodDetails);
    }


    @Test(description = "Verifying Late fee details to UNO Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-44199")
    public void verifyLateFeeDetails() {
        log.info("Verifying Late fee details to UNO Application");
        setTestResultOnXRay("A30TP-44199");
        getPage(WalletPage.class)
                .verify_late_fee_details(storeLateFeeDetails);
    }

    @Test(description = "Verifying service fee details to UNO Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-44536")
    public void verifyServiceFeeDetails() {
        log.info("Verifying Service fee details to UNO Application");
        setTestResultOnXRay("A30TP-44536");
        getPage(WalletPage.class)
                .verify_service_fee_details(storeServiceFeeDetails);
    }

    @Test(description = "Verifying Payor Profile Details to UNO Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-45039")
    public void verifyPayorProfileDetails() {
        log.info("Verifying Payor Profile Details to UNO Application");
        setTestResultOnXRay("A30TP-45039");
        getPage(MemberAccountPage.class)
                .verify_payor_profile_details_obc(storeMemberDetails, storePaymentMethodDetails, storeAdditionalMemberDetails, storeSubscriptionDetails);
    }

    @Test(description = "Verifying Secondary Member from OBC UI to 'UNO' Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-38824")
    public void verifySecondaryMember() {
        log.info("Verifying Secondary Member from OBC UI to 'UNO' Application");
        setTestResultOnXRay("A30TP-38824");
        getPage(MemberPage.class)
                .verify_secondary_member_obc(storeSecondaryMemberDetails);
    }

    @Test(description = "Login into Payment Gateway", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-12686")
    public void pgLogin() {
        log.info("Login into Payment Gateway");
        setTestResultOnXRay("A30TP-12686");
        getPage(PaymentGatewayLoginPage.class)
                .login_pg()
                .verify_successful_login();
    }

    @Test(description = "Verifying Payment Details in Payment Gateway ", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-43682")
    public void verifyPaymentDetailsPG() {
        log.info("Verifying Payment Details in Payment Gateway");
        setTestResultOnXRay("A30TP-43682");
        getPage(CompanyTabPage.class)
                .search_company(ABC_FINANCIAL_BILLING_SERVICES)
                .verify_payment_details_obc(storePaymentMethodDetails, storeMemberDetails);
    }

    @Test(description = "Logout from Payment Gateway", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-12693")
    public void logoutPaymentGateway() {
        setTestResultOnXRay("A30TP-12693");
        getPage(PaymentGatewayLoginPage.class)
                .payment_gateway_logout();
    }

    @Test(description = "Logout from Uno Application", dependsOnMethods = {"verifyMemberStatus"})
    @TmsLink("A30TP-8219")
    public void unoAppLogout() {
        log.info("Logout from Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout();
    }
}


