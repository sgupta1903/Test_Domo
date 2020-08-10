package pagetest.datamigrationpage;

import config.EnvProperty;
import helper.DataBaseHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static util.UtilityGeneric.getFormatedDate;
import static util.UtilityGeneric.get_converted_date;

//Created By Monika Phoughat
//Date : 30-10-2019


public class ABCRatePage extends AbcCommonAbstractPage<ABCRatePage> {
    EnvProperty envProperty = EnvProperty.getInstance(CASH_STORAGE_INI);

    static String rateType;
    private By abcRateTab = By.xpath("//i[@data-abc-id='ratesTitleIcon']");
    private By editButton = By.xpath("//*[@data-abc-id='editButton']");
    private By rateDropdown = By.xpath("//div[@data-abc-id='ABCRatesOptionInput']");
    private By closeButton=By.xpath("//button[@data-abc-id='closeDrawerButton']");
    //Abc Fee
    private By abcFeeBasePercentage = By.xpath("//input[@id='rates[0].BasePercentage']");
    private By abcFeePerTransaction = By.xpath("//input[@id='rates[0].PerTransaction']");
    private By abcFeeStartDate = By.xpath("//input[@id='rates[0].StartDateInput']");
    private By abcFeeEndDate = By.xpath("//input[@id='rates[0].EndDateInput']");
    //Reversal Fee
    private By reversalFeePerTransaction = By.xpath("//input[@id='rates[1].PerTransaction']");
    private By reversalFeeStartDate = By.xpath("//input[@id='rates[1].StartDateInput']");
    private By reversalEndDate = By.xpath("//input[@id='rates[1].EndDateInput']");
    //ACH
    private By achBasePercentage = By.xpath("//input[@id='rates[2].BasePercentage']");
    private By achPerTransaction = By.xpath("//input[@id='rates[2].PerTransaction']");
    private By achStartDate = By.xpath("//input[@id='rates[2].StartDateInput']");
    private By achEndDate = By.xpath("//input[@id='rates[2].EndDateInput']");
    //Statement Generation
    private By statementBasePercentage = By.xpath("//input[@id='rates[3].BasePercentage']");
    private By statementPerTransaction = By.xpath("//input[@id='rates[3].PerTransaction']");
    private By statementStartDate = By.xpath("//input[@id='rates[3].StartDateInput']");
    private By statementEndDate = By.xpath("//input[@id='rates[3].EndDateInput']");
    //American Express
    private By americanExpBasePercentage = By.xpath("//input[@id='rates[4].BasePercentage']");
    private By americanExpPerTransaction = By.xpath("//input[@id='rates[4].PerTransaction']");
    private By americanExpStartDate = By.xpath("//input[@id='rates[4].StartDateInput']");
    private By americanExpEndDate = By.xpath("//input[@id='rates[4].EndDateInput']");
    //Master Card
    private By masterCardBasePercentage = By.xpath("//input[@id='rates[5].BasePercentage']");
    private By masterCardPerTransaction = By.xpath("//input[@id='rates[5].PerTransaction']");
    private By masterCardStartDate = By.xpath("//input[@id='rates[5].StartDateInput']");
    private By masterCardEndDate = By.xpath("//input[@id='rates[5].EndDateInput']");
    //Visa
    private By visaBasePercentage = By.xpath("//input[@id='rates[6].BasePercentage']");
    private By visaPerTransaction = By.xpath("//input[@id='rates[6].PerTransaction']");
    private By visaStartDate = By.xpath("//input[@id='rates[6].StartDateInput']");
    private By visaEndDate = By.xpath("//input[@id='rates[6].EndDateInput']");
    //Discover
    private By discoverBasePercentage = By.xpath("//input[@id='rates[7].BasePercentage']");
    private By discoverPerTransaction = By.xpath("//input[@id='rates[7].PerTransaction']");
    private By discoverStartDate = By.xpath("//input[@id='rates[7].StartDateInput']");
    private By discoverEndDate = By.xpath("//input[@id='rates[7].EndDateInput']");
    //Payment At Club
    private By payAtClubBasePercentage = By.xpath("//input[@id='rates[8].BasePercentage']");
    private By payAtClubPerTransaction = By.xpath("//input[@id='rates[8].PerTransaction']");
    private By payAtClubStartDate = By.xpath("//input[@id='rates[8].StartDateInput']");
    private By payAtClubEndDate = By.xpath("//input[@id='rates[8].EndDateInput']");
    //Club Level
    private By clubLevelBasePercentage = By.xpath("//input[@id='rates[9].BasePercentage']");
    private By clubLevelPerTransaction = By.xpath("//input[@id='rates[9].PerTransaction']");
    private By clubLevelStartDate = By.xpath("//input[@id='rates[9].StartDateInput']");
    private By clubLevelEndDate = By.xpath("//input[@id='rates[9].EndDateInput']");
    //Pre Note Fee
    private By preNoteFeePerTransaction = By.xpath("//input[@id='rates[10].PerTransaction']");
    private By preNoteFeeStartDate = By.xpath("//input[@id='rates[10].StartDateInput']");
    private By preNoteFeeEndDate = By.xpath("//input[@id='rates[10].EndDateInput']");
    //CC Charge Back
    private By chargeBackFeePerTransaction = By.xpath("//input[@id='rates[11].PerTransaction']");
    private By chargeBackFeeStartDate = By.xpath("//input[@id='rates[11].StartDateInput']");
    private By chargeBackFeeEndDate = By.xpath("//input[@id='rates[11].EndDateInput']");
    //Unauthorized Fee
    private By unauthorizedFeePerTransaction = By.xpath("//input[@id='rates[12].PerTransaction']");
    private By unauthorizedFeeStartDate = By.xpath("//input[@id='rates[12].StartDateInput']");
    private By unauthorizedFeeEndDate = By.xpath("//input[@id='rates[12].EndDateInput']");
    //Statement Payment
    private By statementPaymentBasePercentage = By.xpath("//input[@id='rates[13].BasePercentage']");
    private By statementPaymentPerTransaction = By.xpath("//input[@id='rates[13].PerTransaction']");
    private By statementPaymentStartDate = By.xpath("//input[@id='rates[13].StartDateInput']");
    private By statementPaymentEndDate = By.xpath("//input[@id='rates[13].EndDateInput']");
    //Statement Postage
    private By statementPostageBasePercentage = By.xpath("//input[@id='rates[14].BasePercentage']");
    private By statementPostagePerTransaction = By.xpath("//input[@id='rates[14].PerTransaction']");
    private By statementPostageStartDate = By.xpath("//input[@id='rates[14].StartDateInput']");
    private By statementPostageEndDate = By.xpath("//input[@id='rates[14].EndDateInput']");

    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private String rateType1 = "//td[contains(text(),'";
    private String rateType2 = "')]";
    private String startDate1 = "//td[contains(text(),'";
    private String startDate2 = "')]/following-sibling::td[1]";

    @Step("Click on Location")
    private ABCRatePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    @Step("Click Close Button")
    private ABCRatePage click_close_button(){
        logger.info("Clicking on Close Button");
        click(closeButton);
        return me();
    }

    public ABCRatePage click_rate_tab() {
        logger.info("Click on ABC Rate Tab");
        verify(elementToBeClickable(abcRateTab), 60, 2000);
        wait_until(2);
        return click(abcRateTab);
    }

    public By get_rate_type() {
        String feeRateType = rateType1 + rateType + rateType2;
        return new By.ByXPath(feeRateType);
    }

    public By get_start_date() {
        String startDate = startDate1 + rateType + startDate2;
        return new By.ByXPath(startDate);
    }

    public ABCRatePage verify_rate_type(String rateType, String uiRateType) {
        logger.info("Verifying Rate Type");
        for (int i = 0; i < get_fees_list().size(); i++) {
            if (uiRateType.equals(get_fees_list().get(i).toString())) {
                verify_value_matches(find_element_text(get_rate_type()), get_fees_list().get(i).toString());
            }
        }
        return me();
    }

    public ABCRatePage verify_start_date(String startDate) {
        logger.info("Verifying Start Date");
        startDate = startDate.substring(0, 10);
        startDate = getFormatedDate(startDate);
        startDate = startDate.replace("-", "/");
        verify_value_matches(find_element_text(get_start_date()), startDate);
        return me();
    }

    public ABCRatePage click_abc_rate_tab() {
        logger.info("Clicking on ABC Rate Tab");
        click_rate_tab();
        return me();
    }

    public ABCRatePage verify_rate_details(Map rateInfo) {
        for (int i = 0; i < get_fees_list().size(); i++) {
            if (rateInfo.get("rate_type").toString().replaceAll("_", " ").equals(get_fees_list().get(i).toString().toUpperCase())) {
                rateType = get_fees_list().get(i).toString();
            }
        }
        verify_rate_type(rateType, rateInfo.get("rate_type").toString());
        verify_start_date(rateInfo.get("start_date").toString());
        return me();
    }

    public ABCRatePage verify_abc_rate_details(String orgName, DataBaseHandler dataBaseHandler, List client_config_fee_id) {
        logger.info("Verifying ABC Rate Details");
        for (int i = 0; i < client_config_fee_id.size(); i++) {
            CentralDBPage centralDBPage = new CentralDBPage();
            Map<String, String> rateInfo = centralDBPage.getRateData(client_config_fee_id.get(i).toString(), dataBaseHandler);
            verify_rate_details(rateInfo);
        }
        return me();
    }

    public List get_fees_list() {
        List feeList = new ArrayList();
        feeList.add(ABC);
        feeList.add(STATEMENT);
        feeList.add(AMEX);
        feeList.add(MASTERCARD);
        feeList.add(VISA);
        feeList.add(DISCOVER);
        feeList.add(CC_CARGE_BACK_FEE);
        feeList.add(UNAUTHORIZED_FEE);
        return feeList;
    }

    private ABCRatePage verify_abc_fee_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Abc Fee Base percentage");
        verify_element_by_value(abcFeeBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_abc_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Abc Per Transaction");
        verify_element_by_value(abcFeePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_abc_fee_start_date(String expectedStartDate) {
        logger.info("Verifying Abc Fee Start Date");
        verify_element_by_value(abcFeeStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_abc_fee_end_date(String expectedEndDate) {
        logger.info("Verifying Abc Fee End Date");
        verify_element_by_value(abcFeeEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_reversal_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Reversal Per Transaction");
        verify_element_by_value(reversalFeePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_reversal_fee_start_date(String expectedStartDate) {
        logger.info("Verifying Reversal Fee Start Date");
        verify_element_by_value(reversalFeeStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_reversal_fee_end_date(String expectedEndDate) {
        logger.info("Verifying Reversal Fee End Date");
        verify_element_by_value(reversalEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_ach_fee_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying ACH Base percentage");
        verify_element_by_value(achBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_ach_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying ACH Per Transaction");
        verify_element_by_value(achPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_ach_fee_start_date(String expectedStartDate) {
        logger.info("Verifying ACH Start Date");
        verify_element_by_value(achStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_ach_fee_end_date(String expectedEndDate) {
        logger.info("Verifying ACH End Date");
        verify_element_by_value(achEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_statement_generation_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Statement Generation Base percentage");
        verify_element_by_value(statementBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_statement_generation_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Statement Generation Per Transaction");
        verify_element_by_value(statementPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_statement_generation_start_date(String expectedStartDate) {
        logger.info("Verifying Statement Generation Start Date");
        verify_element_by_value(statementStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_statement_generation_end_date(String expectedEndDate) {
        logger.info("Verifying Statement Generation End Date");
        verify_element_by_value(statementEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_american_express_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying American Express Base percentage");
        verify_element_by_value(americanExpBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_american_express_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying American Express Per Transaction");
        verify_element_by_value(americanExpPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_american_express_start_date(String expectedStartDate) {
        logger.info("Verifying American Express Start Date");
        verify_element_by_value(americanExpStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_american_express_end_date(String expectedEndDate) {
        logger.info("Verifying American Express End Date");
        verify_element_by_value(americanExpEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_master_card_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Master Card Base percentage");
        verify_element_by_value(masterCardBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_master_card_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Master Card Per Transaction");
        verify_element_by_value(masterCardPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_master_card_start_date(String expectedStartDate) {
        logger.info("Verifying Master Card Start Date");
        verify_element_by_value(masterCardStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_master_card_end_date(String expectedEndDate) {
        logger.info("Verifying Master Card End Date");
        verify_element_by_value(masterCardEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_visa_card_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Visa Card Base percentage");
        verify_element_by_value(visaBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_visa_card_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Visa Card Per Transaction");
        verify_element_by_value(visaPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_visa_card_start_date(String expectedStartDate) {
        logger.info("Verifying Visa Card Start Date");
        verify_element_by_value(visaStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_visa_card_end_date(String expectedEndDate) {
        logger.info("Verifying Visa Card End Date");
        verify_element_by_value(visaEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_discover_card_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Discover Card Base percentage");
        verify_element_by_value(discoverBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_discover_card_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Discover Card Per Transaction");
        verify_element_by_value(discoverPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_discover_card_start_date(String expectedStartDate) {
        logger.info("Verifying Discover Card Start Date");
        verify_element_by_value(discoverStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_discover_card_end_date(String expectedEndDate) {
        logger.info("Verifying Discover Card End Date");
        verify_element_by_value(discoverEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_pay_at_club_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Payment at club Base percentage");
        verify_element_by_value(payAtClubBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_pay_at_club_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Payment at club Per Transaction");
        verify_element_by_value(payAtClubPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_pay_at_club_start_date(String expectedStartDate) {
        logger.info("Verifying Payment at club Start Date");
        verify_element_by_value(payAtClubStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_pay_at_club_end_date(String expectedEndDate) {
        logger.info("Verifying Payment at club End Date");
        verify_element_by_value(payAtClubEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_club_level_fee_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Club level fee Base percentage");
        verify_element_by_value(clubLevelBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_club_level_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Club level fee Per Transaction");
        verify_element_by_value(clubLevelPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_club_level_fee_start_date(String expectedStartDate) {
        logger.info("Verifying Club level fee Start Date");
        verify_element_by_value(clubLevelStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_club_level_fee_end_date(String expectedEndDate) {
        logger.info("Verifying Club level fee End Date");
        verify_element_by_value(clubLevelEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_pre_note_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Pre note fee Per Transaction");
        verify_element_by_value(preNoteFeePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_pre_note_fee_start_date(String expectedStartDate) {
        logger.info("Verifying Pre note fee Start Date");
        verify_element_by_value(preNoteFeeStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_pre_note_fee_end_date(String expectedEndDate) {
        logger.info("Verifying Pre note fee End Date");
        verify_element_by_value(preNoteFeeEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_charge_back_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Charge Back Per Transaction");
        verify_element_by_value(chargeBackFeePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_charge_back_start_date(String expectedStartDate) {
        logger.info("Verifying Charge Back Start Date");
        verify_element_by_value(chargeBackFeeStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_charge_back_end_date(String expectedEndDate) {
        logger.info("Verifying Charge Back End Date");
        verify_element_by_value(chargeBackFeeEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_unauthorized_fee_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Unauthorized fee Per Transaction");
        verify_element_by_value(unauthorizedFeePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_unauthorized_fee_start_date(String expectedStartDate) {
        logger.info("Verifying Unauthorized fee Start Date");
        verify_element_by_value(unauthorizedFeeStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_unauthorized_fee_date(String expectedEndDate) {
        logger.info("Verifying Unauthorized fee End Date");
        verify_element_by_value(unauthorizedFeeEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_statement_payment_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Statement Payment Base percentage");
        verify_element_by_value(statementPaymentBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_statement_payment_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Statement Payment Per Transaction");
        verify_element_by_value(statementPaymentPerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_statement_payment_start_date(String expectedStartDate) {
        logger.info("Verifying Statement Payment Start Date");
        verify_element_by_value(statementPaymentStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_statement_payment_end_date(String expectedEndDate) {
        logger.info("Verifying Statement Payment End Date");
        verify_element_by_value(statementPaymentEndDate, expectedEndDate);
        return me();
    }

    private ABCRatePage verify_statement_postage_base_percentage(String expectedBasePercentage) {
        logger.info("Verifying Statement Postage Base percentage");
        verify_element_by_value(statementPostageBasePercentage, expectedBasePercentage);
        return me();
    }

    private ABCRatePage verify_statement_postage_per_transaction(String expectedPerTransaction) {
        logger.info("Verifying Statement Postage Per Transaction");
        verify_element_by_value(statementPostagePerTransaction, expectedPerTransaction);
        return me();
    }

    private ABCRatePage verify_statement_postage_start_date(String expectedStartDate) {
        logger.info("Verifying Statement Postage Start Date");
        verify_element_by_value(statementPostageStartDate, expectedStartDate);
        return me();
    }

    private ABCRatePage verify_statement_postage_end_date(String expectedEndDate) {
        logger.info("Verifying Statement Postage End Date");
        verify_element_by_value(statementPostageEndDate, expectedEndDate);
        return me();
    }

    public ABCRatePage verify_abc_rate_details(String clubNumber){
        logger.info("Verifying Abc Fee  Details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_abc_rate_tab();
        click(editButton);
        try {
            verify_all(
                    () -> verify_abc_fee_base_percentage(envProperty.getConfigPropertyValue(clubNumber, "ABC_FEE_BASE_PERCENTAGE")),
                    () -> verify_abc_fee_per_transaction(envProperty.getConfigPropertyValue(clubNumber, "ABC_FEE_PER_TRANSACTION")),
                    () -> verify_ach_fee_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"ACH_BASE_PERCENTAGE")),
                    () -> verify_ach_fee_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"ACH_PER_TRANSACTION")),
                    () -> verify_statement_payment_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"COUPON_BASE_PERCENTAGE")),
                    () -> verify_statement_payment_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"COUPON_PER_TRANSACTION")),
                    () -> verify_club_level_fee_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"CLUB_LEVEL_FEE_BASE_PERCENTAGE")),
                    () -> verify_club_level_fee_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"CLUB_LEVEL_FEE_PER_TRANSACTION")),
                    () -> verify_american_express_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"AMERICAN_EXPRESS_BASE_PERCENTAGE")),
                    () -> verify_american_express_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"AMERICAN_EXPRESS_PER_TRANSACTION")),
                    () -> verify_discover_card_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"DISCOVER_BASE_PERCENTAGE")),
                    () -> verify_discover_card_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"DISCOVER_PER_TRANSACTION")),
                    () -> verify_master_card_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"MASTER_CARD_BASE_PERCENTAGE")),
                    () -> verify_master_card_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"MASTER_CARD_PER_TRANSACTION")),
                    () -> verify_visa_card_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"VISA_BASE_PERCENTAGE")),
                    () -> verify_visa_card_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"VISA_PER_TRANSACTION")),
                    () -> verify_pay_at_club_base_percentage(envProperty.getConfigPropertyValue(clubNumber,"PAYMENTS_AT_CLUB_BASE_PERCENTAGE")),
                    () -> verify_pay_at_club_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"PAYMENTS_AT_CLUB_PER_TRANSACTION")),
                    () -> verify_charge_back_per_transaction("$" + envProperty.getConfigPropertyValue(clubNumber,"CC_CHARGE_BACK_PER_TRANSACTION").substring(0,5)),
                    ()->verify_pre_note_fee_per_transaction(envProperty.getConfigPropertyValue(clubNumber,"PRE_NOTE_PER_TRANSACTION"))
            );
        }
        finally {
            click_close_button();
        }
        return me();
    }
}
