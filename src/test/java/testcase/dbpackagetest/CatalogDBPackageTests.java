package testcase.dbpackagetest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagetest.dbpackagepage.PackagePage;
import ui.AbstractAutoUITest;
import java.util.List;
import java.util.Map;

@Slf4j
public class CatalogDBPackageTests extends AbstractAutoUITest {
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
    public void verifyTestPassFlagItemCategory() {
        log.info(".......Verifying Test Pass Flag for Item Category..........");
        setTestResultOnXRay("A30TP-17565");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "catalog.item_category source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagItemCategory() {
        log.info(".......Verifying Test Pass Critical Flag for Item Category..........");
        setTestResultOnXRay("A30TP-17566");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "catalog.item_category source to target");
    }

    @Test
    public void verifyTestPassFlagCatalogItem() {
        log.info(".......Verifying Test Pass Flag for Catalog Item ..........");
        setTestResultOnXRay("A30TP-13773");
        getPageWithoutUI(PackagePage.class)
                .verify_test_pass_flag(automationTestResult, "catalog.item source to target");
    }

    @Test
    public void verifyTestPassCriticalFlagCatalogItem() {
        log.info(".......Verifying Test Pass Critical Flag for Catalog Item ..........");
        setTestResultOnXRay("A30TP-17566");
        getPageWithoutUI(PackagePage.class)
                .verify_test_critical_pass_flag(automationTestResult, "catalog.item source to target");
    }

}
