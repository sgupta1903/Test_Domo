package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Date: 12/30/2019
*/

import config.EnvProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static helper.AppConstants.ITEM_TYPE;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.get_section_name;

@Slf4j
public class UnoAppItemPage extends AbcCommonAbstractPage<UnoAppItemPage> {
    EnvProperty env = EnvProperty.getInstance(UNOAPP_INI);
    EnvProperty envSubscriptionIni = EnvProperty.getInstance(SUBSCRIPTION_INI);
    protected By itemEditButton = By.xpath("//button[@data-abc-id = 'itemInformationSectionHeaderButton']");
    private By saveButton = By.xpath("//button[@data-abc-id='submitButton']");
    private By itemAutoPay = By.xpath("//div[@data-abc-id='itemAutoPay']//input[@id='itemAutoPay']");
    private By itemName = By.xpath("//div[@data-abc-id='itemName']//input[@id='itemName']");
    private By itemType = By.xpath("//div[@data-abc-id='itemTypeInput']//input[@id='itemTypeInput']");
    private By itemDescription = By.xpath("//div[@data-abc-id = 'itemDescription']//textarea");
    private By selectItemCategory = By.xpath("//div[@data-abc-id='itemCategoryInput']//input[@name='itemCategory']");
    private By itemButton = By.xpath("//button[@data-abc-id='showItemForm']");
    String itemInList = "//span[text()= '%s']";

    public By get_item_ny_name(){
        log.info("Fetching item which is already present by name");
        return By.xpath(String.format(itemInList ,env.getConfigPropertyValue("item", "itemName") ));
    }

    public UnoAppItemPage write_item_name() {
        log.info("Writing item name");
        env.writeIniFile("item", "itemName", generate_random_string() + "Dues");
        return me();
    }
    public UnoAppItemPage enter_auto_pay(String price) {
        log.info("Entering the auto pay price");
        return enter(this.itemAutoPay, price);
    }

    public String get_item_name() {
        log.info("Getting item name");
        return env.getConfigPropertyValue("item", "itemName");

    }

    public UnoAppItemPage enter_item_name(String name) {
        log.info("Entering the Item Name");
        return enter(this.itemName, name);
    }
    public UnoAppItemPage click_save_item_button() {
        log.info("Click on Save Item ");
        click(saveButton);
        return me();
    }

    public UnoAppItemPage select_item_from_list() {
        log.info("Select item from list of items ");
        wait_until(3);
        click(get_item_ny_name());
        return me();
    }
    public UnoAppItemPage select_item_category(String dataSetName) {
        log.info(" Select item category : " + dataSetName);
        verify(presenceOfElementLocated(selectItemCategory), 60, 2000);
        enter(selectItemCategory,dataSetName);
        wait_until(1);
        press_key_down();
        enter_by_key();
        return me();
    }

    public UnoAppItemPage select_item_type(String dataSetName) {
        log.info(" Select item type : " + dataSetName);
        verify(presenceOfElementLocated(itemType), 60, 2000);
        click(itemType);
        wait_until(2);
        enter_by_key();
        press_key_down();
        wait_until(2);
        return me();
    }


    public String get_item_category_name() {
        log.info("Getting Category name");
        return env.getConfigPropertyValue("item", "categoryName");

    }

    public UnoAppItemPage click_edit_button() {
        log.info("click on edit button");
        verify(presenceOfElementLocated(itemEditButton), 60, 2000);
        click(itemEditButton);
        return me();
    }

    public UnoAppItemPage update_item_name() {
        log.info("update item name");
        write_item_name();
        enter_item_name(get_item_name());
        return me();
    }

    public UnoAppItemPage update_item_category() {
        log.info("update item category");
        select_item_category(get_item_category_name());
        return me();
    }

    public UnoAppItemPage update_item_type() {
        log.info("update item type");
        select_item_type(env.getConfigPropertyValue("ITEM", "itemType"));
        return me();
    }
    public UnoAppItemPage update_item_default_price() {
        log.info("update item rate");
        enter_auto_pay(env.getConfigPropertyValue("ITEM", "itemRate"));
        return me();
    }
    public UnoAppItemPage update_item_description() {
        log.info("update item description");
        scroll_down_to_page("0","10");
        enter(itemDescription, generate_random_string());
        return me();
    }

    public UnoAppItemPage click_save_button() {
        log.info("click on save button");
        click_save_item_button();
        return me();
    }
    public UnoAppItemPage click_item() {
        log.info("Click On Item ");
        wait_until(5);
        click(itemButton);
        return me();
    }
    public UnoAppItemPage generate_item_name2(Map<String, String> itemName) {
        log.info("Generating dynamic location name :"+ itemName);
        String generateItemName = itemName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        if(itemName.containsKey(SECOND_ITEM_NAME)){
            env.writeIniFile(ITEM_SECTION, SECOND_ITEM_NAME, generateItemName);
            envSubscriptionIni.writeIniFile(get_section_name(), SECOND_ITEM_NAME , generateItemName);
        }
        env.writeIniFile(ITEM_SECTION, ITEM_NAME, generateItemName);
        envSubscriptionIni.writeIniFile(get_section_name(), ITEM_NAME , generateItemName);
        return me();
    }
    public UnoAppItemPage generate_item_name(String itemName) {
        log.info("Generating dynamic location name :"+ itemName);
        String generateItemName = itemName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        env.writeIniFile(ITEM_SECTION, ITEM_NAME, generateItemName);
        envSubscriptionIni.writeIniFile(get_section_name(), ITEM_NAME , generateItemName);
        return me();
    }
    public UnoAppItemPage generate_item_name_for_second_location(String itemName) {
        log.info("Generating dynamic location name :"+ itemName);
        String generateItemName = itemName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        env.writeIniFile(ITEM_SECTION, SECOND_ITEM_NAME, generateItemName);
        envSubscriptionIni.writeIniFile(get_section_name(), SECOND_ITEM_NAME , generateItemName);
        return me();
    }

    public UnoAppItemPage create_item_for_second_location(Map<String, String> itemData)  {
        log.info("Create Item");
        click_item();
        enter_item_name(itemData.get(SECOND_ITEM_NAME));
        select_item_type(itemData.get(ITEM_TYPE));
        select_item_category(itemData.get(SECOND_ITEM_CATEGORY_NAME));
        enter_auto_pay(itemData.get(AUTO_PAY));
        click_save_item_button();
        return me();
    }
    public UnoAppItemPage create_item (Map<String, String> itemData)  {
        log.info("Create Item");
        click_item();
        enter_item_name(itemData.get(ITEM_NAME));
        select_item_type(itemData.get(ITEM_TYPE));
        select_item_category(itemData.get(ITEM_CATEGORY_NAME));
        enter_auto_pay(itemData.get(AUTO_PAY));
        click_save_item_button();
        return me();
    }

}
