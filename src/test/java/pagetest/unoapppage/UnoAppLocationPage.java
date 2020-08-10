package pagetest.unoapppage;
/*
Created By: Sapna Batan
Updated By: Shilpi Gupta
Jira Ticket:
Test Rail Test Case Number:
Date: 02/15/2018
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;
import java.util.Map;
import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.get_section_name;

@Slf4j
public class UnoAppLocationPage extends AbcCommonAbstractPage<UnoAppLocationPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    EnvProperty envSubscriptionIni = EnvProperty.getInstance(AppConstants.SUBSCRIPTION_INI);
    EnvProperty envMigartedIni = EnvProperty.getInstance(AppConstants.MIGRATED_INI);
    EnvProperty migratedOrgCreated = EnvProperty.getInstance(MIGRATEDORGCREATED_INI);
    EnvProperty envOrg = EnvProperty.getInstance(AppConstants.ORGID_INI);
    private By businessLink = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By orgRadioBtn = By.xpath("//*[text()='Organizations']");
    private By texName = By.xpath("//input[@name='name']");
    private By textAccount = By.xpath("//input[@name='number']");
    private By textPhone = By.xpath("//input[contains (@name , 'phone')]");
    private By textEmail = By.xpath("//input[contains (@name , 'email')]");
    private By textAddress1 = By.xpath("//input[contains (@name , 'address1')]");
    private By textAddress2 = By.xpath("//input[contains(@name ,'address2')]");
    private By textCity = By.xpath("//input[contains(@name , 'city')]");
    private By textZipCode = By.xpath("//div[@data-abc-id='postalCode']//input[@id='postalCode']");
    private By textState = By.xpath("//div[@data-abc-id='provinceCodeInput']//input[@id='provinceCodeInput']");
    private By viewOrgLink = By.xpath("//table[@data-abc-id=\"landingPageTable\"]//tr//td");
    private By textLocTimezone = By.xpath("//div[@data-abc-id='timezoneInput']//input[@name='timezone']");
    private By backButton = By.xpath("//i[@data-abc-id = 'authorizedBoxActionIcon']");
    private String saveBtn = "//button[@type='submit']";
    private By submitButton = By.xpath("//button[@type='submit']");
    private By locationTab = By.xpath("//div[text() = 'Locations']");
    private By bankingTab = By.xpath("//i[@data-abc-id='bankingTitleIcon']");
    private By addLocationBtn = By.xpath("//*[text() = 'Add Location']");
    private By textRoutingNumber = By.xpath("//input[@name='routingNumber']");
    private By textAccountNumber = By.xpath("//input[@name='bankAccountNumber']");
    private By textAccountType = By.xpath("//div[contains(@data-abc-id,'savings')]");
    private String locationByValue = "//tr[@data-abc-id ='landingPageRow']//span[text() = '%s']";


    //Catalog tab
    private By catalogTab = By.xpath("//*[text() = 'Catalog']");
    private By itemCategory = By.xpath("//*[text() = 'Add Item Category']");
    private By itemCategoryName = By.xpath(" //input[@id='itemCategory-name']");
    private By itemTaxCode = By.xpath("//div[@id='itemCategory-taxCode']/div[@class='FormSelect__control css-yk16xz-control']");
    private By itemTaxRate = By.xpath("//input[@name='taxRate']");
    private By saveButton = By.xpath(" //*[@data-abc-id='submitButton']");
    private By itemButton = By.xpath("//button[@data-abc-id='showItemForm']");
    private By itemName = By.xpath("//input[@id='item-name']");
    private By itemType = By.xpath("//*[@id='item-type']");
    private By selectItemCategory = By.xpath("//div[@id='item-category']");
    private By defaultPrice = By.xpath(" //input[@name='itemPrice']");
    private By homeIcon = By.xpath("//a[@href='/uno-app/app/home']/i");
    //Bank tab
    private By bankPaymentInfoBtn = By.xpath("//span[text()='Enter Payment Information']");
    private By accountHolderName = By.xpath(" //input[@name='accountHolderName']");

    public By get_location_by_key(String locationKey) {
        log.info("Fetching location by " + locationKey);
        wait_until(2);
        return By.xpath(String.format(locationByValue, locationKey));
    }

    public UnoAppLocationPage click_submit_button() {
        log.info("Click On Continue Button");
        wait_until(2);
        return click(submitButton);

    }

    public UnoAppLocationPage click_back_button() {
        log.info("Click On back Button");
        wait_until(2);
        return click(backButton);

    }

    public UnoAppLocationPage click_add_location_button() {
        log.info("Click On Add Locations");
        return click(addLocationBtn);
    }

    public UnoAppLocationPage click_payment_info_btn() {
        log.info("Click On Payment Info Button");
        return click(bankPaymentInfoBtn);

    }

    public UnoAppLocationPage click_location_tab() {
        log.info("Click On Locations");
        return click(locationTab);
    }

    public UnoAppLocationPage store_organization_id() {
        log.info("Coping the organization url");

        env.writeIniFile("ORGANIZATION", "organizationId", copy_url());
        //save Org Id in OrgId ini file
        String OrgName = env.getConfigPropertyValue("ORGANIZATION", "organizationName");
        envOrg.writeIniFile("ORGANIZATION", OrgName, copy_url());
        env.writeIniFile("ORGANIZATION", "organizationId", copy_url());
        return me();
    }

    public UnoAppLocationPage click_banking_tab() {
        log.info("Click On Locations");
        return click(bankingTab);
    }

    public UnoAppLocationPage enter_bank_routing_number(String name) {
        log.info("Entering the Bank Routing Number");
        return enter(this.textRoutingNumber, name);
    }

    public UnoAppLocationPage enter_bank_account_number(String name) {
        log.info("Entering the Bank Account Number");
        verify(presenceOfElementLocated(textAccountNumber), 60, 2000);
        return enter(this.textAccountNumber, name);
    }

    public UnoAppLocationPage enter_loc_name(String name) {
        log.info("Entering the Location Name");
        wait_until(2);
        return enter(this.texName, name);
    }

    public UnoAppLocationPage enter_loc_account(String account) {
        log.info("Entering the Org Account");
        return enter(this.textAccount, account);
    }

    public UnoAppLocationPage enter_loc_phone(String phone) {
        log.info("Entering the Org phone");
        return enter(this.textPhone, phone);
    }

    public UnoAppLocationPage enter_loc_email(String email) {
        log.info("Entering the Org email");
        return enter(this.textEmail, email);
    }

    public UnoAppLocationPage enter_loc_add1(String add1) {
        log.info("Entering the Org add1");
        return enter(this.textAddress1, add1);
    }

    public UnoAppLocationPage enter_loc_add2(String add2) {
        log.info("Entering the Org add2");
        return enter(this.textAddress2, add2);
    }

    public UnoAppLocationPage enter_loc_city(String city) {
        log.info("Entering the Org city");
        return enter(this.textCity, city);
    }

    public UnoAppLocationPage enter_loc_zip(String zip) {
        log.info("Entering the Org zip");
        return enter(this.textZipCode, zip);
    }

    public By click_location_save_button() {
        log.info("Click on Save Organization Button");
        wait_until(2);

        wait_until(2);
        return By.xpath(saveBtn);

    }

    public UnoAppLocationPage select_org_state(String dataSetName) {
        log.info(" Select state " + dataSetName + " data set");
        verify(presenceOfElementLocated(textState), 60, 2000);
        click(textState);
        enter(textState, dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppLocationPage select_bankaccount_type() {
        log.info(" Select Account type ");
        verify(presenceOfElementLocated(textAccountType), 60, 2000);
        wait_until(2);

        return click(textAccountType);
    }

    public UnoAppLocationPage select_loc_timezone(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textLocTimezone), 60, 2000);
        enter(textLocTimezone,dataSetName);
        press_key_down();
        enter_by_key();
        return me();
    }

    public UnoAppLocationPage click_item_category() {
        log.info("Click On Item Category");
        return click(itemCategory);
    }

    public UnoAppLocationPage enter_item_category_name(String name) {
        log.info("Entering the Item Category Name");
        return enter(this.itemCategoryName, name);
    }

    public UnoAppLocationPage enter_item_tax_code(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(itemTaxCode), 60, 2000);
        wait_until(2);
        click(itemTaxCode);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppLocationPage enter_item_tax_rate(String name) {
        log.info("Entering the Item Category Name");
        return enter(this.itemTaxRate, name);
    }

    public UnoAppLocationPage category_save_button() {
        log.info("Click on Save Item Category");
        click(saveButton);
        return me();
    }

    public UnoAppLocationPage click_item() {
        log.info("Click On Item ");
        wait_until(5);
        click(itemButton);
        return me();
    }

    public UnoAppLocationPage enter_item_name(String name) {
        log.info("Entering the Item Name");
        return enter(this.itemName, name);
    }

    public UnoAppLocationPage select_item_type(String dataSetName) {
        log.info(" Select item type : " + dataSetName);
        verify(presenceOfElementLocated(itemType), 60, 2000);
        click(itemType);
        wait_until(2);
        enter_by_key();
        press_key_down();
        wait_until(3);
        return me();
    }

    public UnoAppLocationPage select_item_category(String dataSetName) {
        log.info(" Select item category : " + dataSetName);
        verify(presenceOfElementLocated(selectItemCategory), 60, 2000);
        click(selectItemCategory);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public UnoAppLocationPage enter_price(String price) {
        log.info("Entering the default price");
        return enter(this.defaultPrice, price);
    }

    public UnoAppLocationPage item_save_button() {
        log.info("Click on Save Item ");
        click(saveButton);
        return me();
    }

    public UnoAppLocationPage click_business_link() {
        log.info("Click on Business Link");
        verify(presenceOfElementLocated(businessLink), 60, 2000);
        return click(businessLink);
    }

    public UnoAppLocationPage click_org_radio_btn() {
        log.info("Select Organization Radio Button");
        verify(elementToBeClickable(orgRadioBtn), 60, 2000);
        return click(orgRadioBtn);
    }

    public UnoAppLocationPage click_view() {
        log.info("Click On View");
        wait_until(2);
        return click(viewOrgLink);
    }

    public UnoAppLocationPage click_home_icon() {
        log.info("Click On Home Icon");
        wait_until(5);
        verify(presenceOfElementLocated(homeIcon), 60, 2000);
        return click(homeIcon);
    }

    //catalog tab
    public UnoAppLocationPage click_catalog_tab() {
        log.info("Click On Catalog");
        return click(catalogTab);
    }

    public UnoAppLocationPage switch_window() {
        log.info("Switch to New Window");
        wait_until(5);
        return switch_to_window();

    }

    public UnoAppLocationPage switch_parent_window() {
        log.info("Switch to Parent Window");
        wait_until(5);
        return switch_to_parent_window();

    }

    public UnoAppLocationPage enter_account_holder_name(String name) {
        log.info("Enter Account Holder Name");
        return enter(this.accountHolderName, name);
    }

    public UnoAppLocationPage write_location_name(String locationKey) {
        log.info("Enter location Name");
        String locationName = "Location " + get_alphabetical_data(5);
        env.writeIniFile("LOCATION", locationKey, locationName);
        envSubscriptionIni.writeIniFile(get_section_name(), "locationName", locationName);
        return me();
    }

    public UnoAppLocationPage write_second_location_name(String secondLocation) {
        log.info("Enter location Name");
        String locationName = "Location " + get_alphabetical_data(5);
        env.writeIniFile("LOCATION", secondLocation, locationName);
        envSubscriptionIni.writeIniFile(get_section_name(), "secondLocation", locationName);
        return me();
    }

    public String get_location_name(String locationKey) {
        log.info("Enter location Name");
        return env.getConfigPropertyValue("LOCATION", locationKey);

    }

    public UnoAppLocationPage write_item_category_name() {
        log.info("Writing Category name");

        env.writeIniFile("item", "categoryName", "Dues " + get_alphabetical_data(5));
        return me();
    }

    public String get_item_category_name() {
        log.info("Getting Category name");
        return env.getConfigPropertyValue("item", "categoryName");

    }

    public UnoAppLocationPage write_item_name() {
        log.info("Writing item name");

        env.writeIniFile("item", "itemName", generate_random_string() + "Dues");
        return me();
    }

    public String get_item_name() {
        log.info("Getting item name");
        return env.getConfigPropertyValue("item", "itemName");

    }

    public UnoAppLocationPage create_another_location(String locationKey) {
        log.info("Creating another location");
        write_second_location_name(locationKey);
        click_location_tab();
        click_add_location_button();
        enter_loc_name(get_location_name(locationKey));
        enter_loc_phone(env.getConfigPropertyValue("LOCATION", "phoneNumber"));
        enter_loc_email(env.getConfigPropertyValue("LOCATION", "email"));
        enter_loc_account(get_alphanumeric_data(6));
        enter_loc_add1(env.getConfigPropertyValue("LOCATION", "locAdd1"));
        enter_loc_add2(env.getConfigPropertyValue("LOCATION", "locAdd2"));
        enter_loc_city(env.getConfigPropertyValue("LOCATION", "city"));
        enter_loc_zip(env.getConfigPropertyValue("LOCATION", "zip"));
        select_org_state(env.getConfigPropertyValue("LOCATION", "state"));
        select_loc_timezone(env.getConfigPropertyValue("LOCATION", "timezone"));
        click_location_save_button();
        return me();
    }

    public UnoAppLocationPage add_banking_details() {
        log.info("Adding banking details on the location");
        click_banking_tab();
        click_payment_info_btn();
        switch_window();
        enter_account_holder_name(env.getConfigPropertyValue("LOCATION", "accountHolderName"));
        enter_bank_routing_number(env.getConfigPropertyValue("LOCATION", "bankRoutingNumber"));
        enter_bank_account_number(get_numeric_data(4));
        select_bankaccount_type();
        click_submit_button();
        switch_parent_window();
        return me();
    }

    public UnoAppLocationPage add_location_bank_details(String locAccountHolderName, String locBankRoutingNumber) {
        log.info("Adding banking details on the location");
        click_banking_tab();
        click_payment_info_btn();
        switch_window();
        enter_account_holder_name(locAccountHolderName);
        enter_bank_routing_number(locBankRoutingNumber);
        enter_bank_account_number(get_numeric_data(Integer.parseInt(FOUR)));
        select_bankaccount_type();
        click_submit_button();
        switch_parent_window();
        return me();
    }

    public UnoAppLocationPage generate_location_name(String locName) {
        log.info("Generating dynamic location name :" + locName);
        String locationName = locName + RandomStringUtils.randomAlphabetic(7);
        env.writeIniFile(LOCATION_SECTION, locName, locationName);
        envSubscriptionIni.writeIniFile(get_section_name(), LOCATION_SECTION, locationName);
        envMigartedIni.writeIniFile("LOCATION", "locationName", locationName);
        return me();
    }

    public UnoAppLocationPage create_location(Map<String, String> locationData) {
        log.info("Create Location");
        click_location_tab();
        //Storing the organization id from URL
        store_organization_id();

        click_add_location_button();
        if (locationData.containsKey(SECONDLOCATION)) {
            enter_loc_name(locationData.get(SECONDLOCATION));
        } else {
            enter_loc_name(locationData.get(LOCATION_NAME));
        }
        enter_loc_phone(locationData.get(LOCATION_PHONE_NUMBER));
        enter_loc_email(locationData.get(LOCATION_EMAIL));
        enter_loc_account(get_alphanumeric_data(Integer.parseInt(SIX)));
        enter_loc_add1(locationData.get(LOCATION_ADD1));
        enter_loc_add2(locationData.get(LOCATION_ADD2));
        enter_loc_city(locationData.get(LOCATION_CITY));
        enter_loc_zip(locationData.get(LOCATION_ZIP));
        select_org_state(locationData.get(LOCATION_STATE));
        select_loc_timezone(locationData.get(LOCATION_TIMEZONE));
        click_location_save_button();
        return me();
    }

    public UnoAppLocationPage add_location_bank_details(Map<String, String> locationBankAccData) {
        log.info("Adding banking details on the location");
        wait_until(1);
        click_banking_tab();
        click_payment_info_btn();
        switch_window();
        enter_account_holder_name(locationBankAccData.get(LOCATION_ACCOUNT_HOLDER_NAME));
        enter_bank_routing_number(locationBankAccData.get(LOCATION_BANK_ROUTING_NUMBER));
        enter_bank_account_number(get_numeric_data(Integer.parseInt(FOUR)));
        select_bankaccount_type();
        click_submit_button();
        switch_parent_window();
        return me();
    }

    public UnoAppLocationPage select_location(Map<String, String> locationData) {
        log.info("Select Location");
        wait_until(3);
        click(get_location_by_key(locationData.get(LOCATION_NAME)));
        return me();
    }

        public UnoAppLocationPage store_migrated_location_created(String clubNumber , String locName ) {
            migratedOrgCreated.writeIniFile(clubNumber, LOCATION_NAME, locName );
            return me();
        }

}