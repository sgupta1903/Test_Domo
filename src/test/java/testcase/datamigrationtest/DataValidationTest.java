//Created By Monika Phoughat
//Date : 11-09-2019
package testcase.datamigrationtest;

import helper.DataBaseHandler;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.datamigrationpage.*;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;

@Slf4j
public class DataValidationTest extends AbstractAutoUITest {
    private static DataBaseHandler dataBaseHandler;
    private String orgName;

    public DataValidationTest(String orgName) {
        this.orgName = orgName;
    }

    @Test
    @Issue("A30TP-13920")
    public void login() {
        log.info("Login into the Application");
        setTestResultOnXRay("A30TP-13920");
        dataBaseHandler = getDBClient("RegShiftQADB");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Issue("A30TP-13921")
    public void searchOrganizationByName() {
        log.info("Searching for an Organization and verifying org correctly serached");
        setTestResultOnXRay("A30TP-13921");
        getPage(SearchOrgPage.class)
                .search_organization(orgName)
                .verify_org_home_text(orgName);
    }

    @Test
    @Issue("A30TP-13922")
    public void verifyOrgnizationList() {
        log.info("Visibility of Organisation List on Organization Page");
        setTestResultOnXRay("A30TP-13922");
        getPage(SearchOrgPage.class)
                .click_on_org_radio_btn()
                .verify_org_list_exist();
    }

    @Test
    @Issue("A30TP-19529")
    public void storingOrgIdIntoContextAttribute() {
        log.info("Getting Organisation Id From Central Database and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19529");
        store_test_data("orgId", getPage(CentralDBPage.class).get_org_id(orgName, getDBClient("RegShiftQADB")));
    }

    @Test
    @Issue("A30TP-14668")
    public void verifyOrgDetails() {
        log.info("Verifying Organisation Details ");
        setTestResultOnXRay("A30TP-14668");
        getPage(SearchOrgPage.class)
                .verify_org_deatils(getPage(CentralDBPage.class).get_org_data(retrieve_test_data("orgId"), getDBClient("RegShiftQADB")));
    }

    @Test
    @Issue("A30TP-19531")
    public void storingLocationIdIntoContextAttribute() {
        log.info("Getting Location Id From Central Database and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19531");
        store_test_data("locId", getPage(CentralDBPage.class).get_location_id(orgName, getDBClient("RegShiftQADB")));
    }

   /* @Test
    @Issue("A30TP-14669")
    public void verifyLocationDetails() {
        log.info("Verifying Location Details ");
        setTestResultOnXRay("A30TP-14669");
        getPage(SearchLocationPage.class)
                .verify_loc_details(getPage(CentralDBPage.class).get_location_data(retrieve_test_data("locId"), getDBClient("RegShiftQADB")));

    }*/


    @Test
    @Issue("A30TP-19532")
    public void storingClientProfileIdIntoContextAttribute() {
        log.info("Getting Client Profile Id From Central DataBase and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19532");
        store_test_data("clientProfileId", getPage(CentralDBPage.class).get_client_profile_id(orgName, getDBClient("RegShiftQADB")).toString());
    }

    @Test
    @Issue("A30TP-10816")
    public void verifyClientProfileDetails() {
        log.info("Verifying Client Profile Details ");
        setTestResultOnXRay("A30TP-10816");
        getPage(SearchClientProfilePage.class)
                .verify_client_profile_details(getPage(CentralDBPage.class).get_client_profile_data(retrieve_test_data("clientProfileId"), getDBClient("RegShiftQADB")));
    }


    @Test
    @Issue("A30TP-17438")
    public void verifyABCRateDetails() {
        log.info("Verifying ABC Rate Details");
        setTestResultOnXRay("A30TP-10816");
        getPage(ABCRatePage.class).click_abc_rate_tab();
        store_test_list("rateIds", getPage(CentralDBPage.class).get_sample_client_configure_fee_ids(orgName, getDBClient("RegShiftQADB")));
        getPage(ABCRatePage.class).
                verify_abc_rate_details(orgName, dataBaseHandler, retrieve_test_list("rateIds"));
    }

    @Test
    @Issue("A30TP-28282")
    public void verifyABCFeeDetails() {
        log.info("Verifying ABC Fee Details");
        setTestResultOnXRay("A30TP-28282");
        getPage(ABCFeePage.class).click_abc_fee_tab();
        store_test_list("feeIds", getPage(CentralDBPage.class).get_sample_member_configure_fee_ids(orgName, getDBClient("RegShiftQADB")));
        getPage(ABCFeePage.class).
                verify_fee_details(orgName, dataBaseHandler, retrieve_test_list("feeIds"));
    }

    @Test
    @Issue("A30TP-19533")
    public void storingSampleItemIdsIntoContextAttribute() {
        log.info("Getting Sample Item Ids From Central DataBase and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19533");
        store_test_data("itemId", String.valueOf(getPage(CentralDBPage.class).get_sample_item_ids(orgName, getDBClient("RegShiftQADB"))));
    }

    @Test
    @Issue("A30TP-11845")
    public void verifyItemDetails() {
        log.info("Verifying Item Details ");
        setTestResultOnXRay("A30TP-11845");
        getPage(CatalogPage.class).click_catalog_tab();
        store_test_list("itemIds", getPage(CentralDBPage.class).get_sample_item_ids(orgName, getDBClient("RegShiftQADB")));
        getPage(CatalogPage.class).verify_item_details(orgName, dataBaseHandler, retrieve_test_list("itemIds"));
    }


    @Test
    @Issue("A30TP-19534")
    public void storingSampleItemCategoryIdsIntoContextAttribute() {
        log.info("Getting Sample Item Category Ids From Central DataBase and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19534");
        store_test_data("itemCategoryId", String.valueOf(getPage(CentralDBPage.class).get_sample_item_category_ids(orgName, getDBClient("RegShiftQADB"))));
    }

    @Test
    @Issue("A30TP-10599")
    public void verifyItemCategory() {
        log.info("Verifying Item Category Details ");
        setTestResultOnXRay("A30TP-10599");
        store_test_list("itemCategoryIds", getPage(CentralDBPage.class).get_sample_item_category_ids(orgName, getDBClient("RegShiftQADB")));
        getPage(CatalogPage.class).click_category_tab();
        getPage(CatalogPage.class).verify_item_category_details(orgName, dataBaseHandler, retrieve_test_list("itemCategoryIds"));
    }

    @Test
    @Issue("A30TP-19557")
    public void storingSampleMemberIdsIntoContextAttribute() {
        log.info("Getting Sample Member Ids From Central DataBase  and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19557");
        store_test_data("memberId", String.valueOf(getPage(CentralDBPage.class).get_sample_member_ids(orgName, getDBClient("RegShiftQADB"))));
    }

    @Test
    @Issue("A30TP-10689")
    public void verifyMemberProfile() {
        log.info("Verifying The Member Profile Details ");
        setTestResultOnXRay("A30TP-10689");
        store_test_list("memberIds", getPage(CentralDBPage.class).get_sample_member_ids(orgName, getDBClient("RegShiftQADB")));
        getPage(MemberPage.class)
                .verify_member_profile(dataBaseHandler, getPage(CentralDBPage.class).getMemberData(retrieve_test_list("memberIds"), dataBaseHandler));
    }

    @Test
    @Issue("A30TP-19609")
    public void storingSamplePayorPaymentMethodIdsIntoContextAttribute() {
        log.info("Getting Sample Payor Profile Ids From Central DataBase and storing into a Context attribute");
        setTestResultOnXRay("A30TP-19609");
        store_test_data("paymentMethodIds", String.valueOf(getPage(CentralDBPage.class).get_sample_payor_payment_method_details(orgName, getDBClient("RegShiftQADB"))));
    }

    @Test
    @Issue("A30TP-10706")
    public void verifyPayorPaymentMethodDetails() {
        log.info("Verifying Payor Payment Method Details ");
        setTestResultOnXRay("A30TP-10706");
        store_test_list("paymentMethodIds", getPage(CentralDBPage.class).get_sample_payor_payment_method_details(orgName, getDBClient("RegShiftQADB")));
        getPage(WalletPage.class)
                .verify_payor_payment_method_details(getPage(CentralDBPage.class).getMemberData(retrieve_test_list("memberIds"), dataBaseHandler), getPage(CentralDBPage.class).getPayorPaymentMethodData(retrieve_test_list("paymentMethodIds"), dataBaseHandler), dataBaseHandler);
    }

    @Test
    @Issue("A30TP-11883")
    public void verifyAgreementDetails() {
        log.info("Verifying The Agreement Details ");
        setTestResultOnXRay("A30TP-11883");
        store_test_list("agreementIds", getPage(CentralDBPage.class).get_sample_agreement_details(orgName, getDBClient("RegShiftQADB")));
        getPage(AgreementPage.class).
                verify_agreement_details(getPage(CentralDBPage.class).getMemberData(retrieve_test_list("memberIds"), dataBaseHandler), getPage(CentralDBPage.class).getAgreementData(retrieve_test_list("agreementIds"), dataBaseHandler), dataBaseHandler);

    }

    @Test
    @Issue("A30TP-11923")
    public void verifySubscriptionDetails() {
        log.info("Verifying The Subscription Details ");
        setTestResultOnXRay("A30TP-11923");
        store_test_list("subscriptionIds", getPage(CentralDBPage.class).get_sample_subscription_details(orgName, getDBClient("RegShiftQADB")));
        getPage(SubscriptionPage.class)
                .verify_subscription_details(getPage(CentralDBPage.class).getMemberData(retrieve_test_list("memberIds"), dataBaseHandler), getPage(CentralDBPage.class).getSubscriptionData(retrieve_test_list("subscriptionIds"), dataBaseHandler), dataBaseHandler);

    }


    @Test
    @Issue("A30TP-8219")
    @Description("Logout from the Uno Application")
    public void unoAppLogout() {
        log.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown()
                .wait_until(2);
    }
}

