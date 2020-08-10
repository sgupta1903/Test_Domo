package pagetest.unoapppage;

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.getDate;

@Slf4j
public class UnoAppFeePage extends AbcCommonAbstractPage<UnoAppFeePage> {
    EnvProperty env = EnvProperty.getInstance( AppConstants.UNOAPP_INI );
    private By feeTab = By.xpath( "//i[@data-abc-id=\"feeTitleIcon\"]" );
    private By addFeeButton = By.xpath("//button[@data-abc-id='showAddFeeForm']");
    private By selectLateFee = By.xpath("//label[text() ='Late Fee']");
    private By selectStartDate = By.xpath("//div[@data-abc-id='startDateInput']//input[@id='startDateInput']");
    private By selectStartDelay = By.xpath("//div[@data-abc-id='assessmentStartDateInput']//input[@id='assessmentStartDateInput']");
    private By selectAccessFee = By.xpath("//div[@data-abc-id='assessFeeInput']//input[@id='assessFeeInput']");
    private By draftFee = By.xpath("//div[@data-abc-id='chargeFeeInput']//input[@id='chargeFeeInput']");
    private By graceDay = By.xpath("//div[@data-abc-id='feeGraceDays']//input[@id='feeGraceDays']");
    private By feeAmount = By.xpath("//div[@data-abc-id='flat.amount']//input[@id='flat.amount']");
    private By feePercentage = By.xpath("//div[@data-abc-id='feePercentage']//input[@id='feePercentage']");
    private By locationPercentage = By.xpath("//div[@data-abc-id='locationPercentage']//input[@id='locationPercentage']");
    private By locationPercentageType = By.xpath("//div[@data-abc-id='locationPercentagePaymentTypeInput']//input[@id='locationPercentagePaymentTypeInput']");
    private String saveBtn = "//span[text() ='Save']";
    private String lateFeeList = "//tr[@data-abc-id='feesListRow'] //td[contains(text(),'Late')] ";

    public UnoAppFeePage click_fee_tab() {
        log.info("Clicking on Fee tab.");
        wait_until(4);
        verify(presenceOfElementLocated(feeTab));
        return click(feeTab);
    }

    public UnoAppFeePage click_add_fee_btn() {
        log.info("Clicking on Add Fee button");
        wait_until(1);
        return click(addFeeButton);
    }

    public UnoAppFeePage select_late_fee_btn() {
        log.info("Clicking late fee button");
        wait_until(1);
        return click(selectLateFee);
    }

    public UnoAppFeePage enter_start_date(String startDate) {
        log.info("Entering start date");
        wait_until(1);
        enter(selectStartDate, startDate);
        return me();
    }

    public UnoAppFeePage enter_delay_start_date(String delayDate) {
        log.info("Entering delay start date");
        wait_until(1);
        enter(selectStartDelay, delayDate);
        return me();
    }

    public UnoAppFeePage select_access_type(String accessType) {
        log.info("Entering access Type");
        wait_until(1);
        enter(selectAccessFee, accessType);
        enter_by_key();
        return me();
    }

    public UnoAppFeePage select_draft_fee(String selectFee) {
        log.info("Select Draft Fee");
        wait_until(1);
        enter(draftFee, selectFee);
        enter_by_key();
        return me();
    }

    public UnoAppFeePage fee_grace_days(String graceDays) {
        log.info("Select fee grace days");
        wait_until(1);
        enter(graceDay, graceDays);
        return me();
    }

    public UnoAppFeePage enter_fee_amount(String amount) {
        log.info("Select fee amount");
        wait_until(1);
        enter(feeAmount, amount);
        return me();
    }

    public UnoAppFeePage enter_fee_percentage(String percentage) {
        log.info("Select fee percentage");
        wait_until(1);
        enter(feePercentage, percentage);
        return me();
    }

    public UnoAppFeePage location_percentage(String percentage) {
        log.info("Select Location percentage");
        wait_until(1);
        enter(locationPercentage, percentage);
        return me();
    }

    public UnoAppFeePage location_percentage_type(String percentageType) {
        log.info("Select Location percentage type");
        wait_until(1);
        enter(locationPercentageType, percentageType);
        press_key_down();
        enter_by_key();
        return me();
    }

    public By save_btn() {
        log.info("Select fee percentage");
        wait_until(1);
        return By.xpath(saveBtn);
    }

    public UnoAppFeePage add_fee() {
        select_late_fee_btn();
        select_access_type(env.getConfigPropertyValue("fees", "accessType"));
        select_draft_fee(env.getConfigPropertyValue("fees", "draftFee"));
        fee_grace_days(env.getConfigPropertyValue("fees", "feeGraceDays"));
        enter_fee_amount(env.getConfigPropertyValue("fees", "feeAmount"));
        enter_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("fees", "startDate"))));
        enter_delay_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("fees", "delayDate"))));
        enter_fee_percentage(env.getConfigPropertyValue("fees", "feePercentage"));
        location_percentage(env.getConfigPropertyValue("fees", "locationPercentage"));
        location_percentage_type(env.getConfigPropertyValue("fees", "locationPercentageType"));
        save_btn();
        return me();
    }
}
