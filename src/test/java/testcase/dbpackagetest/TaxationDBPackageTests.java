package testcase.dbpackagetest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.dbpackagepage.PackagePage;
import ui.AbstractAutoUITest;
import java.util.List;
import java.util.Map;

@Slf4j
public class TaxationDBPackageTests extends AbstractAutoUITest {
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
    public void verifyTestPassFlagTaxLocation() {
        log.info(".......Verifying Test Pass Flag for Tax Location..........");
        setTestResultOnXRay("A30TP-14121");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "taxation.tax_location source to target");

    }

    @Test
    public void verifyTestPassCriticalFlagTaxLocation() {
        log.info(".......Verifying Test Pass Critical Flag for Tax Location..........");
        setTestResultOnXRay("A30TP-14123");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "taxation.tax_location source to target");
    }

}

