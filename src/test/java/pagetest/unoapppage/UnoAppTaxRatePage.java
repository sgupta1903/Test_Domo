package pagetest.unoapppage;
/*
Created By: Sharwan Jha
Updated By:
Jira Ticket:
Date: 10/03/2018
*/
import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static util.UtilityGeneric.getDate;

@Slf4j
public class UnoAppTaxRatePage extends AbcCommonAbstractPage<UnoAppTaxRatePage> {
    EnvProperty env = EnvProperty.getInstance( AppConstants.UNOAPP_INI );
    private By taxRateTab = By.xpath( "//i[@data-abc-id=\"ratesTitleIcon\"]" );
    private By addTaxRateBtn = By.xpath("//i[@data-abc-id='editButtonIcon']");
    private By abcBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[0].percentage')]");
    private By abcPerTransaction = By.xpath("//h5[text() = 'ABC Fee']/..//input[@id = 'abcRatesFormPerTransaction']");
    private By abcStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[0].startDate')]");
    private By abcEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[0].endDate')]");
    private By abcReversalPercentage = By.xpath("//div[contains(@class , 'fieldWrapper--1gIap')]//input[@name='rates[1].perTransaction']");
    private By abcReversalStartDate = By.xpath("//div[contains(@class , 'fieldWrapper--1gIap')]//input[@name='rates[1].startDate']");
    private By abcReversalEndDate = By.xpath("//div[contains(@class , 'fieldWrapper--1gIap')]//input[@name='rates[1].endDate']");
    private By achBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[2].percentage')]");
    private By achStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[2].startDate')]");
    private By achEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[2].endDate')]");
    private By statementBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[3].percentage')]");
    private By statementStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[3].startDate')]");
    private By statementEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[3].endDate')]");
    private By aeBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[4].percentage')]");
    private By americanExStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[4].startDate')]");
    private By americanExEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[4].endDate')]");
    private By masterCardBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[5].percentage')]");
    private By masterCardStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[5].startDate')]");
    private By masterCardEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[5].endDate')]");
    private By visaBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[6].percentage')]");
    private By visaStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[6].startDate')]");
    private By visaEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[6].endDate')]");
    private By discoverBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[7].percentage')]");
    private By discoverStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[7].startDate')]");
    private By discoverEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[7].endDate')]");
    private By paymentClubBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[8].percentage')]");
    private By paymentClubStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[8].startDate')]");
    private By paymentClubEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[8].endDate')]");
    private By clubLevelBasePercentage = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[9].percentage')]");
    private By clubLevelStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[9].startDate')]");
    private By clubLevelEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[9].endDate')]");
    private By preNotePerTransaction = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[10].perTransaction')]");
    private By preNoteStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[10].startDate')]");
    private By preNoteEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[10].endDate')]");
    private By ccChargePerTransaction = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[11].perTransaction')]");
    private By ccChargeStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[11].startDate')]");
    private By ccChargeEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[11].endDate')]");
    private By unAuthorisedPerTransaction = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[12].perTransaction')]");
    private By unAuthorisedStartDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[12].startDate')]");
    private By unAuthorisedEndDate = By.xpath("//div[contains(@class, 'fieldWrapper--1gIap')]//input[contains(@name, 'rates[12].endDate')]");
    private By saveBtn = By.xpath("//span[text()='Save']");

    public UnoAppTaxRatePage click_tax_rate_tab() {
        log.info("Clicking on tax rate tab.");
        wait_until(2);
        return click(taxRateTab);
    }

    public UnoAppTaxRatePage click_on_add_tax() {
        log.info("Clicking on add tax rate button.");
        return click(addTaxRateBtn);
    }

    public UnoAppTaxRatePage enter_abc_base_percentage(String percentage) {
        log.info("Entering abc base percentage.");
        wait_until(2);
        return enter(this.abcBasePercentage, percentage);
    }
    public UnoAppTaxRatePage enter_abc_rate_per_transaction(String perTransactionAmount ) {
        log.info("Entering abc per transaction.");
        wait_until(2);
        return enter(this.abcPerTransaction, perTransactionAmount);
    }

    public UnoAppTaxRatePage enter_abc_reversal_percentage(String percentage) {
        log.info("Entering abc reversal percentage.");
        click(abcReversalPercentage);
        wait_until(2);
        return enter(this.abcReversalPercentage, percentage);
    }

    public UnoAppTaxRatePage enter_abc_start_date(String startDate) {
        log.info("Enter abc start date.");
        enter(abcStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_abc_reversal_start_date(String startDate) {
        log.info("Enter reversal start date.");
        enter(abcReversalStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_abc_reversal_end_date(String endDate) {
        log.info("Enter reversal end date.");
        enter(abcReversalEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_abc_end_date(String endDate) {
        log.info("Enter abc end date.");
        enter(abcEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_ach_base_percentage(String achPercentage) {
        log.info("Entering ach base percentage.");
        click(achBasePercentage);
        wait_until(1);
        return enter(this.achBasePercentage, achPercentage);
    }

    public UnoAppTaxRatePage enter_ach_start_date(String startDate) {
        log.info("Enter ach start date.");
        enter(achStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_ach_end_date(String endDate) {
        log.info("Enter ach start date.");
        enter(achEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_statement_base_percentage(String stmtPercentage) {
        log.info("Entering statement base percentage.");
        click(statementBasePercentage);
        wait_until(2);
        return enter(this.statementBasePercentage, stmtPercentage);
    }

    public UnoAppTaxRatePage enter_statement_start_date(String startDate) {
        log.info("Enter statement start date.");
        enter(statementStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_statement_end_date(String endDate) {
        log.info("Enter statement start date.");
        enter(statementEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_american_base_percentage(String percentage) {
        log.info("Entering american express base percentage.");
        click(aeBasePercentage);
        wait_until(1);
        return enter(this.aeBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_american_exp_start_date(String startDate) {
        log.info("Enter american express start date.");
        enter(americanExStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_american_exp_end_date(String endDate) {
        log.info("Enter american express start date.");
        enter(americanExEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_master_card_base_percentage(String percentage) {
        log.info("Entering master card base percentage.");
        click(masterCardBasePercentage);
        wait_until(1);
        return enter(this.masterCardBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_master_card_start_date(String startDate) {
        log.info("Enter master card start date.");
        enter(masterCardStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_master_card_end_date(String endDate) {
        log.info("Enter master card start date.");
        enter(masterCardEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_visa_base_percentage(String percentage) {
        log.info("Entering VISA base percentage.");
        click(visaBasePercentage);
        wait_until(1);
        return enter(this.visaBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_visa_start_date(String startDate) {
        log.info("Enter VISA start date.");
        enter(visaStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_visa_end_date(String endDate) {
        log.info("Enter VISA end date.");
        enter(visaEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_discover_base_percentage(String percentage) {
        log.info("Entering Discover base percentage.");
        click(discoverBasePercentage);
        wait_until(1);
        return enter(this.discoverBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_discover_start_date(String startDate) {
        log.info("Enter Discover start date.");
        enter(discoverStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_discover_end_date(String endDate) {
        log.info("Enter Discover start date.");
        enter(discoverEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_payments_base_percentage(String percentage) {
        log.info("Entering Payments at club base percentage.");
        click(paymentClubBasePercentage);
        wait_until(1);
        return enter(this.paymentClubBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_payments_start_date(String startDate) {
        log.info("Enter Payments at club start date.");
        enter(paymentClubStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_payments_end_date(String endDate) {
        log.info("Enter Payments at club start date.");
        enter(paymentClubEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_club_level_base_percentage(String percentage) {
        log.info("Entering Club Level Fee base percentage.");
        click(clubLevelBasePercentage);
        wait_until(1);
        return enter(this.clubLevelBasePercentage, percentage);
    }

    public UnoAppTaxRatePage enter_club_level_start_date(String startDate) {
        log.info("Enter Club Level Fee start date.");
        enter(clubLevelStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_club_level_end_date(String endDate) {
        log.info("Enter Club Level Fee start date.");
        enter(clubLevelEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_pre_note_per_transaction(String transaction) {
        log.info("Entering Pre Note Fee per transaction.");
        click(preNotePerTransaction);
        wait_until(1);
        return enter(this.preNotePerTransaction, transaction);
    }

    public UnoAppTaxRatePage enter_pre_note_start_date(String startDate) {
        log.info("Enter Pre Note start date.");
        enter(preNoteStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_pre_note_end_date(String endDate) {
        log.info("Enter Pre Note end date.");
        enter(preNoteEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_cc_charge_back_per_transaction(String transaction) {
        log.info("Entering CC charge back transaction.");
        click(ccChargePerTransaction);
        wait_until(1);
        return enter(this.ccChargePerTransaction, transaction);
    }

    public UnoAppTaxRatePage enter_cc_charge_back_start_date(String startDate) {
        log.info("Enter CC charge back start date.");
        enter(ccChargeStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_cc_charge_back_end_date(String endDate) {
        log.info("Enter CC charge back end date.");
        enter(ccChargeEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_unauthorized_per_transaction(String transaction) {
        log.info("Entering unauthorized transaction.");
        click(unAuthorisedPerTransaction);
        wait_until(1);
        return enter(this.unAuthorisedPerTransaction, transaction);
    }

    public UnoAppTaxRatePage enter_unauthorized_start_date(String startDate) {
        log.info("Enter unauthorized start date.");
        enter(unAuthorisedStartDate, startDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage enter_unauthorized_end_date(String endDate) {
        log.info("Enter unauthorized end date.");
        enter(unAuthorisedEndDate, endDate);
        wait_until(2);

        return me();
    }

    public UnoAppTaxRatePage click_on_save_btn() {
        log.info("Clicking on save button.");
        wait_until(2);
        return click(saveBtn);
    }

    public UnoAppTaxRatePage add_abc_tax_rate() {
        log.info("Adding tax rate for ABC ");
        wait_until(2);
        enter_abc_base_percentage(env.getConfigPropertyValue("TaxRate", "abcBasePercentage"));
        enter_abc_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "abcStartDate"))));
        enter_abc_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "abcEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_reversal_tax_rate() {
        log.info("Adding tax reversal rate for ABC ");
        wait_until(2);
        enter_abc_reversal_percentage(env.getConfigPropertyValue("TaxRate", "abcReversalPercentage"));
        enter_abc_reversal_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "reversalStartDate"))));
        enter_abc_reversal_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "reversalEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_ach_tax_rate() {
        log.info("Adding tax rate for ACH ");
        wait_until(2);
        enter_ach_base_percentage(env.getConfigPropertyValue("TaxRate", "achBasePercentage"));
        enter_ach_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "achStartDate"))));
        enter_ach_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "achEndDate"))));

        return me();
    }

    public UnoAppTaxRatePage add_statement_tax_rate() {
        log.info("Adding tax rate for Statement ");
        enter_statement_base_percentage(env.getConfigPropertyValue("TaxRate", "stmtBasePercentage"));
        enter_statement_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "stmtStartDate"))));
        enter_statement_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "stmtEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_american_express_tax_rate() {
        log.info("Adding tax rate for American Express ");
        wait_until(2);
        enter_american_base_percentage(env.getConfigPropertyValue("TaxRate", "americanBasePercentage"));
        enter_american_exp_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "americanStartDate"))));
        enter_american_exp_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "americanEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_master_card_tax_rate() {
        log.info("Adding tax rate for Master Card ");
        wait_until(2);
        enter_master_card_base_percentage(env.getConfigPropertyValue("TaxRate", "masterBasePercentage"));
        enter_master_card_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "masterStartDate"))));
        enter_master_card_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "masterEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_visa_tax_rate() {
        log.info("Adding tax rate for VISA ");
        wait_until(2);
        enter_visa_base_percentage(env.getConfigPropertyValue("TaxRate", "visaBasePercentage"));
        enter_visa_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "visaStartDate"))));
        enter_visa_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "visaEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_discover_tax_rate() {
        log.info("Adding tax rate for Discover ");
        wait_until(2);
        enter_discover_base_percentage(env.getConfigPropertyValue("TaxRate", "discoverBasePercentage"));
        enter_discover_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "discoverStartDate"))));
        enter_discover_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "discoverEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_payments_add_tax_rate() {
        log.info("Adding tax rate for Payments ");
        wait_until(2);
        enter_payments_base_percentage(env.getConfigPropertyValue("TaxRate", "paymentsBasePercentage"));
        enter_payments_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "paymentsStartDate"))));
        enter_payments_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "paymentsEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_club_level_tax_rate() {
        log.info("Adding tax rate for Club Level ");
        enter_club_level_base_percentage(env.getConfigPropertyValue("TaxRate", "clubBasePercentage"));
        enter_club_level_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "clubStartDate"))));
        enter_club_level_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "clubEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_pre_note_tax_rate() {
        log.info("Adding tax rate for Pre Note ");
        enter_pre_note_per_transaction(env.getConfigPropertyValue("TaxRate", "prePerTransaction"));
        enter_pre_note_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "preStartDate"))));
        enter_pre_note_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "preEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_cc_charge_tax_rate() {
        log.info("Adding tax rate for Credit Card ");
        enter_cc_charge_back_per_transaction(env.getConfigPropertyValue("TaxRate", "ccPerTransaction"));
        enter_cc_charge_back_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "ccStartDate"))));
        enter_cc_charge_back_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "ccEndDate"))));
        return me();
    }

    public UnoAppTaxRatePage add_unauthorized_tax_rate() {
        log.info("Adding tax rate for Unauthorized....");
        enter_unauthorized_per_transaction(env.getConfigPropertyValue("TaxRate", "unauthorizedPerTransaction"));
        enter_unauthorized_start_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "unauthorizedStartDate"))));
        enter_unauthorized_end_date(getDate(Integer.parseInt(env.getConfigPropertyValue("TaxRate", "unauthorizedEndDate"))));
        return me();
    }
}
