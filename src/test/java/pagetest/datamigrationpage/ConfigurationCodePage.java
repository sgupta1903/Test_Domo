package pagetest.datamigrationpage;

import config.EnvProperty;
import helper.DriverInitilization;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static helper.AppConstants.CASH_STORAGE_INI;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class ConfigurationCodePage extends AbcCommonAbstractPage<ConfigurationCodePage> {

    EnvProperty envProperty = EnvProperty.getInstance(CASH_STORAGE_INI);

    private By loadingCell=By.xpath("//td[@data-abc-id='loadingCell']");
    private By locationNotesRow=By.xpath("//tr[@data-abc-id='notesListRow']");

    private By configurationIcon = By.xpath("//i[@data-abc-id='slidersHIcon']");
    private By codesTab = By.xpath("//i[@data-abc-id='codesListPageLinkIcon']");
    private By codesDropdown = By.xpath("//div[@data-abc-id='codesTypeFilterInput']");
    private By codesStatus = By.xpath("//div[@data-abc-id='codesStatusFilterInput']");
    private By cancelCode = By.xpath("//input[@id='numericCode']");
    private By cancelCodeDescription = By.xpath("//input[@id='numericCode']//following::textarea[1]");
    private By locationRecordingCode = By.xpath("//input[@id='code']");
    private By locationRecordingCodeType = By.xpath("//input[@id='codeTypeInput']");
    private By locationRecordingCodeDescription = By.xpath("//input[@id='code']//following::textarea[1]");
    private By memberNoteCode = By.xpath("//input[@id='code']");
    private By memberNoteCodeDescription = By.xpath("//input[@id='code']//following::textarea[1]");
    private By reimbursementDeductionCode = By.xpath("//input[@id='code']");
    private By generalLedgerDepartment = By.xpath("//input[@id='generalLedgerDepartment']");
    private By generalLedgerAccount = By.xpath("//input[@id='generalLedgerAccount']");
    private By upperLimit = By.xpath("//input[@id='upperLimit']");
    private By reimbursementDeductionCodeDescription = By.xpath("//input[@id='upperLimit']//following::textarea[1]");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By closeButton = By.xpath("//button[@data-abc-id='closeDrawerButton']");

    private String dataAbcId = "codesListRow";

    private String codesListRowXpath = "//tr[@data-abc-id='%s']/../..//td[text()='%s']/../..//td[text()='%s']";

    public By get_codes_row_xpath(String dataAbcId, String code, String description) {
        return By.xpath(String.format(codesListRowXpath, dataAbcId, code, description));
    }

    @Step("Verifying codes details")
    public ConfigurationCodePage verify_code_details(String code, String description) {
        verify(visibilityOfAllElementsLocatedBy(get_codes_row_xpath(dataAbcId, code, description)));
        return me();
    }

    @Step("Click on Location")
    private ConfigurationCodePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    private ConfigurationCodePage click_configuration_icon() {
        logger.info("Clicking on Configuration Icon");
        click(configurationIcon);
        return me();
    }

    private ConfigurationCodePage click_code_tab() {
        logger.info("Clicking on code tab");
        click(codesTab);
        return me();
    }

    private ConfigurationCodePage click_close_button() {
        logger.info("Clicking on close Button");
        click(closeButton);
        return me();
    }

    private ConfigurationCodePage verify_cancel_code(String expectedCancelCode) {
        logger.info("Verifying Cancel Code on Side Bar");
        verify_element_by_value(cancelCode, expectedCancelCode);
        return me();
    }

    private ConfigurationCodePage verify_cancel_code_description(String expectedDescription) {
        logger.info("Verifying Cancel Code Description on Side Bar");
        verify_element_by_text(cancelCodeDescription, expectedDescription);
        return me();
    }

    private ConfigurationCodePage verify_location_recording_code(String expectedCode) {
        logger.info("Verifying Location Recording Code Description on Side Bar");
        verify_element_by_value(locationRecordingCode, expectedCode);
        return me();
    }

    private ConfigurationCodePage verify_location_recording_code_type(String expectedCodeType) {
        logger.info("Verifying Location Recording Code Type on Side Bar");
        verify_element_by_value(locationRecordingCodeType, expectedCodeType);
        return me();
    }

    private ConfigurationCodePage verify_location_recording_code_description(String expectedCodeDescription) {
        logger.info("Verifying Location Recording Code Description on Side Bar");
        verify_element_by_text(locationRecordingCodeDescription, expectedCodeDescription);
        return me();
    }

    private ConfigurationCodePage verify_member_note_code(String expectedNoteCode) {
        logger.info("Verifying Member Note Code on Side Bar");
        verify_element_by_value(memberNoteCode, expectedNoteCode);
        return me();
    }

    private ConfigurationCodePage verify_member_note_code_description(String expectedNoteCodeDescription) {
        logger.info("Verifying Member Note Code Description on Side Bar");
        verify_element_by_text(memberNoteCodeDescription, expectedNoteCodeDescription);
        return me();
    }

    private ConfigurationCodePage verify_rembursement_deduction_code(String expectedRemDedCode) {
        logger.info("Verifying Reimbursement/Deduction Code on Side Bar");
        verify_element_by_value(reimbursementDeductionCode, expectedRemDedCode);
        return me();
    }

    private ConfigurationCodePage verify_general_ledger_department(String expectedGlDepartment) {
        logger.info("Verifying General Ledger Department on Side Bar");
        verify_element_by_value(generalLedgerDepartment, expectedGlDepartment);
        return me();
    }

    private ConfigurationCodePage verify_general_ledger_account(String expectedGlAccount) {
        logger.info("Verifying General Ledger Account on Side Bar");
        verify_element_by_value(generalLedgerAccount, expectedGlAccount);
        return me();
    }

    private ConfigurationCodePage verify_upper_limit(String expectedUpperLimit) {
        logger.info("Verifying Upper Limit on Side Bar");
        verify_element_by_value(upperLimit, expectedUpperLimit);
        return me();
    }

    private ConfigurationCodePage verify_reimbursement_deduction_code_description(String expectedRemDedDescription) {
        logger.info("Verifying Upper Limit on Side Bar");
        verify_element_by_text(reimbursementDeductionCodeDescription, expectedRemDedDescription);
        return me();
    }

    public ConfigurationCodePage verify_cancel_code_details() {
        click_configuration_icon();
        click_code_tab();
        enter_by_action(codesDropdown, "Cancel Codes");
        enter_by_key();
        wait_until(3);

        verify_code_details(envProperty.getConfigPropertyValue("CODE", "code"), envProperty.getConfigPropertyValue("CODE", "codeDescription"));

        click(get_codes_row_xpath(dataAbcId, envProperty.getConfigPropertyValue("CODE", "code"), envProperty.getConfigPropertyValue("CODE", "codeDescription")));

        try {
            verify_all(
                    () -> verify_cancel_code(envProperty.getConfigPropertyValue("CODE", "code")),
                    () -> verify_cancel_code_description(envProperty.getConfigPropertyValue("CODE", "codeDescription"))
            );
        } finally {
            click_close_button();
        }
        return me();
    }
}
