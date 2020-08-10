package pagetest.unoapppage;
/*
Created By: Sapna Batan
Updated By: Shilpi Gupta
Jira Ticket:
Date: 02/15/2018
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;
import java.util.Random;

import static helper.AppConstants.ORGANIZATION_NAME;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppDashBoardPage extends AbcCommonAbstractPage<UnoAppDashBoardPage> {

    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    EnvProperty envSubscriptionIni = EnvProperty.getInstance(AppConstants.SUBSCRIPTION_INI);
    private By businessLink = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By orgRadioBtn = By.xpath("//*[text()='Organizations']");
    private By addOrgBtn = By.xpath("//span[text() = 'Add Organization']");
    private By texName = By.xpath("//input[@name='name']");
    private By textAccount = By.xpath("//input[@name='number']");
    private By textPhone = By.xpath("//input[@name='phone']");
    private By textEmail = By.xpath("//input[@name='email']");
    private By textAddress1 = By.xpath("//input[@name='address1']");
    private By textAddress2 = By.xpath("//input[@name='address2']");
    private By textCity = By.xpath("//input[@name='city']");
    private By textZipCode = By.xpath("//input[@name='zipCode']");
    private By textWebsite = By.xpath("//input[@name='website']");
    private By textState = By.xpath("//input[@name='city']/following::input[1]");
    private By textTimezone = By.xpath("//input[@name='zipCode']/following::input[1]");
    private By textLocTimezone = By.xpath("//div[@id='form" +
            "" +
            "-timezone-JSSN7']");
    private By saveBtn = By.xpath("//button[@type='submit']");
    private By continueBtn = By.xpath("//button[@type='submit']");
    private By enterOrgName = By.xpath("//div[@class='FormSelect__input']/input");
    private By orgNameSearchBox = By.xpath("//div[@data-abc-id='orgSwitcher']");
    private By homeIcon = By.xpath("//a[@href='/uno-app/app/home']/i");

    private By viewOrgLink = By.xpath("//span[contains(text(),'Organization Name')]/following::span[4]");
    private By locationTab = By.xpath("//div[text() = 'Locations']");
    private By bankingTab = By.xpath("//div[text() = 'Banking']");
    private By editIcon = By.xpath("//i[@data-abc-id='editButton']");
    private By addLocationBtn = By.xpath("//*[text() = 'Add Location']");
    private By textRoutingNumber = By.xpath("//input[@name='routingNumber']");
    private By textAccountNumber = By.xpath("//input[@name='accountNumber']");
    private By textAccountType = By.xpath("//div[@id='account-type']");
    private By currentSelectedOrganization = By.xpath("//h1[@class='ui-text ui-elastic-text elasticText--rgVFl']");
    //Catalog tab
    private By catalogTab = By.xpath("//*[text() = 'Catalog']");
    private By itemCategory = By.xpath("//*[text() = 'Add Item Category']");
    private By itemCategoryName = By.xpath(" //input[@id='itemCategory-name']");
    private By saveButton = By.xpath(" //*[@data-abc-id='submitButton']");
    private By item = By.xpath("//span[text()='Add Item']");
    private By itemName = By.xpath("//input[@id='item-name']");
    private By itemType = By.xpath("//*[@id='item-type']");
    private By selectItemCategory = By.xpath("//div[@id='item-category']");
    private By defaultPrice = By.xpath(" //input[@name='itemPrice']");
    private By subscriptionCancelPopup = By.xpath("//div[@data-abc-id='leaveBlockerConfirm']");
    private By subscriptionCancelConfirmButton = By.xpath("//button[@data-abc-id='confirmButton']");
    //member tab
    private By memberIcon = By.xpath("//i[@data-abc-id = 'userCheckIcon']");

    String orgName = null;
    String memName = null;
    String locName = null;
    String accountNumber = null;
    String accounBankNumber = null;
    String categoryName = null;

    public UnoAppDashBoardPage click_continue_btn() {
        log.info("Click On Continue Button");
        wait_until(2);
        return click(continueBtn);

    }

    public UnoAppDashBoardPage click_add_location() {
        log.info("Click On Add Locations");
        return click(addLocationBtn);
    }

    public UnoAppDashBoardPage click_edit_icon() {
        log.info("Click On Edit Icon");
        return click(editIcon);
    }

    public UnoAppDashBoardPage click_location_tab() {
        log.info("Click On Locations");
        return click(locationTab);
    }

    public UnoAppDashBoardPage click_banking_tab() {
        log.info("Click On Locations");
        return click(bankingTab);
    }

    public UnoAppDashBoardPage click_view() {
        log.info("Click On View");
        return click(viewOrgLink);
    }

    public UnoAppDashBoardPage click_home_icon() {
        wait_until(5);
        log.info("Click On Home Icon");
        verify(presenceOfElementLocated(homeIcon), 60, 2000);
        return click(homeIcon);
    }

    public UnoAppDashBoardPage enter_bank_routing(String name) {
        log.info("Entering the Bank Routing Number");
        return enter(this.textRoutingNumber, name);
    }

    public UnoAppDashBoardPage enter_bank_number(String name) {
        log.info("Entering the Bank Account Number");
        verify(presenceOfElementLocated(textAccountNumber), 60, 2000);
        return enter(this.textAccountNumber, name);
    }


    public UnoAppDashBoardPage search_org_name(String sectionName, String orgKey) {
        log.info("Searching the Org Name");
        verify(presenceOfElementLocated(enterOrgName), 60, 2000);
        enter(this.enterOrgName, env.getConfigPropertyValue(sectionName, orgKey));
        wait_until(2);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppDashBoardPage search_org_name_for_invoice_verification() {
        log.info("Searching the Org Name");
        verify(presenceOfElementLocated(enterOrgName), 60, 2000);
        enter(this.enterOrgName, env.getConfigPropertyValue("INVOICEVERIFICATION", "invoiceVerificationOrganizationName"));
        wait_until(2);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppDashBoardPage verify_organization_search_success() {
        log.info(" Verifying searched organziation is selected");
        verify(presenceOfElementLocated(currentSelectedOrganization));
        verify_value_matches(find_element_text(currentSelectedOrganization),
                env.getConfigPropertyValue("INVOICEVERIFICATION", "invoiceVerificationOrganizationName"));
        return me();
    }

    public UnoAppDashBoardPage select_member_icon() {
        log.info("Selecting member icon");
        click(memberIcon);
        return me();
    }

    public UnoAppDashBoardPage enter_org_name(String name) {
        wait_until(2);
        log.info("Entering the Org Name");
        return enter(this.texName, name);
    }

    public UnoAppDashBoardPage enter_org_account(String account) {
        log.info("Entering the Org Account");
        return enter(this.textAccount, account);
    }

    public UnoAppDashBoardPage enter_org_phone(String phone) {
        log.info("Entering the Org phone");
        return enter(this.textPhone, phone);
    }

    public UnoAppDashBoardPage enter_org_email(String email) {
        log.info("Entering the Org email");
        return enter(this.textEmail, email);
    }

    public UnoAppDashBoardPage enter_org_add1(String add1) {
        log.info("Entering the Org add1");
        return enter(this.textAddress1, add1);
    }

    public UnoAppDashBoardPage enter_org_add2(String add2) {
        log.info("Entering the Org add2");
        return enter(this.textAddress2, add2);
    }

    public UnoAppDashBoardPage enter_org_city(String city) {
        log.info("Entering the Org city");
        return enter(this.textCity, city);
    }

    public UnoAppDashBoardPage enter_org_zip(String zip) {
        log.info("Entering the Org zip");
        return enter(this.textZipCode, zip);
    }

    public UnoAppDashBoardPage enter_org_website(String website) {
        log.info("Entering the Org website");
        return enter(this.textWebsite, website);
    }

    public UnoAppDashBoardPage click_business_link() {
        log.info("Click on Business Link");
        verify(presenceOfElementLocated(businessLink), 60, 2000);
        click(businessLink);
        return me();
    }

    public UnoAppDashBoardPage click_org_radio_btn() {
        log.info("Select Organization Radio Button");
        verify(elementToBeClickable(orgRadioBtn), 60, 2000);
        return click(orgRadioBtn);
    }

    public UnoAppDashBoardPage click_add_org_btn() {
        log.info("Click on Add Organization Button");
        return click(addOrgBtn);
    }

    public UnoAppDashBoardPage orgSaveBtn() {
        log.info("Click on Save Organization Button");
        wait_until(2);
        click(saveBtn);
        wait_until(2);
        return me();

    }

    public UnoAppDashBoardPage select_org_state(String dataSetName) {
        log.info(" Select state " + dataSetName + " data set");
        verify(presenceOfElementLocated(textState), 60, 2000);
        click(textState);
        enter(textState, dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }


    public UnoAppDashBoardPage select_bankaccount_type(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textAccountType), 60, 2000);
        click(textAccountType);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public UnoAppDashBoardPage
    select_org_timezone(String dataSetName) {

        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textTimezone), 60, 2000);
        click(textTimezone);
        enter(textTimezone, dataSetName);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppDashBoardPage
    select_loc_timezone(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textLocTimezone), 60, 2000);
        click(textLocTimezone);
        enter_by_key();
        wait_until(2);
        return me();
    }

    //catalog tab
    public UnoAppDashBoardPage click_catalog_tab() {
        log.info("Click On Catalog");
        return click(catalogTab);
    }

    public UnoAppDashBoardPage click_item_category() {
        log.info("Click On Item Category");
        return click(itemCategory);
    }

    public UnoAppDashBoardPage enter_item_category_name(String name) {
        log.info("Entering the Item Category Name");
        return enter(this.itemCategoryName, name);
    }

    public UnoAppDashBoardPage category_save_button() {
        log.info("Click on Save Item Category");
        click(saveButton);
        return me();
    }

    public UnoAppDashBoardPage click_item() {
        log.info("Click On Item ");
        wait_until(5);
        return click(item);
    }

    public UnoAppDashBoardPage enter_item_name(String name) {
        log.info("Entering the Item Name");
        return enter(this.itemName, name);
    }

    public UnoAppDashBoardPage select_item_type(String dataSetName) {
        log.info(" Select item type : " + dataSetName);
        verify(presenceOfElementLocated(itemType), 60, 2000);
        click(itemType);
        wait_until(2);
        enter_by_key();
        press_key_down();
        wait_until(3);
        return me();
    }

    public UnoAppDashBoardPage select_item_category(String dataSetName) {
        log.info(" Select item category : " + dataSetName);
        verify(presenceOfElementLocated(selectItemCategory), 60, 2000);
        click(selectItemCategory);
        wait_until(2);
        press_key_down();
        enter_by_key();
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public UnoAppDashBoardPage search_organization_by_name(Map<String, String> organizationData) {
        log.info("Searching the Org Name");
        verify(presenceOfElementLocated(enterOrgName));
        wait_until(2);
        click(orgNameSearchBox);
        enter(this.enterOrgName, organizationData.get(ORGANIZATION_NAME));
        wait_until(2);
        enter_by_key();
        if (is_element_exist(subscriptionCancelPopup)) {
            wait_until(2);
            click(subscriptionCancelConfirmButton);
            wait_until(2);
            click(orgNameSearchBox);
            enter(this.enterOrgName, organizationData.get(ORGANIZATION_NAME));
            wait_until(2);
            enter_by_key();
        }
        wait_until(2);
        return me();
    }

    public UnoAppDashBoardPage enter_price(String price) {
        log.info("Entering the default price");
        return enter(this.defaultPrice, price);
    }

    public UnoAppDashBoardPage item_save_button() {
        log.info("Click on Save Item ");
        click(saveButton);
        return me();
    }

    public UnoAppDashBoardPage create_organization() {

        orgName = "Organization " + RandomStringUtils.randomAlphabetic(5);
        accountNumber = RandomStringUtils.randomAlphanumeric(6);
        log.info("Create Organization");
        click_business_link();
        click_org_radio_btn();
        click_add_org_btn();
        enter_org_name(orgName);
        enter_org_phone("7878789789");
        enter_org_email("noreply@qa4life.com");
        enter_org_add1("AR-107");
        enter_org_add2("Sherwood");
        enter_org_city("Little Rock");
        select_org_state("Alaska");
        select_org_timezone("US/Central");
        enter_org_zip("72120");
        enter_org_website("https://test.url.qa4life.com");
        orgSaveBtn();
        return me();

    }

    public void
    create_location() {
        log.info("Create Location");
        memName = "Member " + RandomStringUtils.randomAlphabetic(5);
        locName = "Location " + RandomStringUtils.randomAlphabetic(5);
        accountNumber = RandomStringUtils.randomAlphanumeric(6);
        accounBankNumber = RandomStringUtils.randomNumeric(4);
        click_location_tab();
        click_add_location();
        enter_org_name(locName);
        enter_org_phone("7878789789");
        enter_org_email("noreply@qa4life.com");
        enter_org_account(accountNumber);
        enter_org_add1("AR-107");
        enter_org_add2("Sherwood");
        enter_org_city("Little Rock");
        enter_org_zip("72120");
        select_org_state("Alaska");
        select_loc_timezone("US/Alaska");
        click_banking_tab();
        click_edit_icon();
        enter_org_name(memName);
        enter_bank_routing("123103729");
        enter_bank_number(accounBankNumber);
        select_bankaccount_type("Checking");
        click_continue_btn();
    }

    public void create_item_category() {
        categoryName = "Dues " + RandomStringUtils.randomAlphabetic(4);
        log.info("Create Item Category");
        click_home_icon();
        click_business_link();
        click_org_radio_btn();
        click_view();
        click_catalog_tab();
        click_item_category();
        enter_item_category_name(categoryName);
        category_save_button();

    }

    public UnoAppDashBoardPage create_item() {
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        String itemName = x + "Dues";
        log.info("Create Item");
        click_item();
        enter_item_name(itemName);
        select_item_type("Amenity");
        select_item_category(categoryName);
        enter_price("15");
        item_save_button();
        return me();

    }
}