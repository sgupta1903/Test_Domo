package testcase.dbpackagetest;


import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.dbpackagepage.PackagePage;
import ui.AbstractAutoUITest;
import java.util.List;
import java.util.Map;

@Slf4j
public class OrganisationDBPackageTests extends AbstractAutoUITest {
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
    public void verifyTestPassFlagOrganizationTimeZoneLookUp() {
        log.info("Verifying Test Pass Flag for Organization Time Zone Look up");
        setTestResultOnXRay("A30TP-12011");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.timezone_lookup source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagOrganizationTimeZoneLookUp() {
        log.info("Verifying Test Critical Pass Flag for Organization Time Zone Look up");
        setTestResultOnXRay("A30TP-12021");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.timezone_lookup source to target");
    }

    @Test
    public void verifyTestPassFlagOrganizationStateName() {
        log.info(".......Verifying Test Pass Flag for Organisation State Name..........");
        setTestResultOnXRay("A30TP-12010");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.state_name source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagOrganizationStateName() {
        log.info(".......Verifying Test Critical Pass Flag for Organisation State Name..........");
        setTestResultOnXRay("A30TP-12018");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.state_name source to target");
    }

    @Test
    public void verifyTestPassFlagOrganizationStateLookup() {
        log.info(".......Verifying Test Pass Flag for Organisation State Lookup..........");
        setTestResultOnXRay("A30TP-12008");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.state_lookup source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagOrganizationStateLookup() {
        log.info(".......Verifying Test Critical Pass Flag for Organisation State Lookup..........");
        setTestResultOnXRay("A30TP-12016");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.state_lookup source to target");
    }


    @Test
    public void verifyPassFlagCountOrgMemType() {
        log.info(".......Verify Test Pass Flag of Organization Membership Type..........");
        setTestResultOnXRay("A30TP-12138");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.membership_type source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountOrgMemType() {
        log.info(".......Verify Test Critical Pass Flag of Organization Membership Type..........");
        setTestResultOnXRay("A30TP-19917");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "organization.membership_type source to target");
    }

    @Test
    public void verifyPassFlagCountOrgConLookUp() {
        log.info(".......Verify Test Pass Flag of Organization Country Lookup..........");
        setTestResultOnXRay("A30TP-12300");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.country_lookup source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountOrgConLookUp() {
        log.info(".......Verify Test Critical Pass Flag of Organization Country Lookup..........");
        setTestResultOnXRay("A30TP-19919");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "organization.country_lookup source to target");
    }

    @Test
    public void verifyPassFlagCountOrgConName() {
        log.info(".......Verify Test Pass Flag of Organization Country Name..........");
        setTestResultOnXRay("A30TP-12303");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.country_name source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountOrgConName() {
        log.info(".......Verify Test Critical Pass Flag of Organization Country Name..........");
        setTestResultOnXRay("A30TP-19935");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "organization.country_name source to target");
    }

    @Test
    public void verifyPassFlagCountMember() {
        log.info(".......Verify Test Pass Flag of Member..........");
        setTestResultOnXRay("A30TP-12342");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "organization.member source to target");
    }

    @Test
    public void verifyCriticalPassFlagCountCountMember() {
        log.info(".......Verify Test Critical Pass Flag of Member..........");
        setTestResultOnXRay("A30TP-12343");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "organization.member source to target");
    }


}
