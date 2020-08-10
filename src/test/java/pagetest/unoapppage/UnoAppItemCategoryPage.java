package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Date: 12/27/2019
*/

import config.EnvProperty;
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
public class UnoAppItemCategoryPage extends AbcCommonAbstractPage<UnoAppItemCategoryPage> {

    EnvProperty env = EnvProperty.getInstance(UNOAPP_INI);
    EnvProperty envSubscriptionIni = EnvProperty.getInstance(SUBSCRIPTION_INI);

    protected By itemCategoryEditButton = By.xpath("//button[@data-abc-id = 'itemCategoryForm-sectionHeaderButton']");
    protected String itemCategory = "//span[text() = '%s']";
    private By itemCategorybtn = By.xpath("//*[text() = 'Add Item Category']");
    protected By itemCategoryName = By.xpath("//div[@data-abc-id='itemCategoryName']//input[@id='itemCategoryName']");
    private By catalogTab = By.xpath("//li[@data-abc-id='catalogTitle']");
    protected By itemCategoryRadioButton = By.xpath("//div[@data-abc-id = 'categoriesRadio']//input[@name='group1']");
    protected By itemCategoryTaxCode = By.xpath("//div[@data-abc-id='itemCategoryTaxCode']//input[@name='taxCode']");
    protected By itemCategoryTaxRateOverride =By.xpath("//div[@data-abc-id='itemCategoryTaxRateOverride']//input[@name='taxRate']");
    protected By itemCategoryDescription = By.xpath("//div[@data-abc-id = 'itemCategoryDescription']");
    protected String saveButton = "//button[@data-abc-id='submitButton']";


    public By get_item_category_by_name(String itemCategoryName) {

        return By.xpath(String.format(itemCategory, itemCategoryName));
    }

    public String get_item_category_name() {
        log.info("Getting Category name");
        return env.getConfigPropertyValue("item", "categoryName");
    }

    public UnoAppItemCategoryPage click_item_category() {
        log.info("Click On Item Category");
        return click(itemCategorybtn);
    }

    public UnoAppItemCategoryPage select_item_category_radio_button() {
        verify(elementToBeClickable(itemCategoryRadioButton));
        click(itemCategoryRadioButton);
        return me();
    }

    public UnoAppItemCategoryPage select_item_category_by_name() {
        click(get_item_category_by_name(get_item_category_name()));
        return me();
    }

    public UnoAppItemCategoryPage click_edit_button() {
        click(itemCategoryEditButton);
        return me();
    }

    public UnoAppItemCategoryPage write_item_category_name() {
        log.info("Writing Category name");
        env.writeIniFile("item", "categoryName", "Dues " + get_alphabetical_data(5));
        return me();
    }

    public UnoAppItemCategoryPage enter_item_category_name(String name) {
        log.info("Entering the Item Category Name");
        return enter(this.itemCategoryName, name);
    }

    public UnoAppItemCategoryPage enter_item_tax_code(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        wait_until(5);
        verify(presenceOfElementLocated(itemCategoryTaxCode), 60, 2000);
        wait_until(2);
        click(itemCategoryTaxCode);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppItemCategoryPage enter_item_tax_rate_override(String name) {
        log.info("Entering the Item Category Name");
        return enter(this.itemCategoryTaxRateOverride, name);
    }

    public By click_save_button() {
        log.info("Click on Save Item Category");
        ;
        return By.xpath(saveButton);
    }

    public UnoAppItemCategoryPage update_item_category_name() {
        log.info("Create Item Category");
        write_item_category_name();
        enter_item_category_name(get_item_category_name());
        return me();
    }

    public UnoAppItemCategoryPage update_item_category_tax_details() {
        log.info("Create Item Category");
        enter_item_tax_code(env.getConfigPropertyValue("ITEM", "taxCode"));
        enter_item_tax_rate_override(env.getConfigPropertyValue("ITEM", "taxRate"));
        return me();
    }

    public UnoAppItemCategoryPage update_item_category_description() {
        log.info("Create Item Category");
        enter(itemCategoryDescription, generate_random_string());
        return me();
    }

    public UnoAppItemCategoryPage click_catalog_tab() {
        log.info("Click On Catalog");
        return click(catalogTab);
    }

    public By category_save_button() {
        log.info("Click on Save Item Category");
        wait_until(1);

        return By.xpath(saveButton);
    }
    public UnoAppItemCategoryPage generate_category_name2(Map<String, String> categoryName) {
        log.info("Generating dynamic category name :" + categoryName);
        String dynamicCategoryName = categoryName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        if(categoryName.containsKey(SECOND_ITEM_CATEGORY_NAME)){
            env.writeIniFile(ITEM_SECTION, SECOND_ITEM_CATEGORY_NAME, dynamicCategoryName);
        }
        env.writeIniFile(ITEM_SECTION, ITEM_CATEGORY_NAME, dynamicCategoryName);
        envSubscriptionIni.writeIniFile(get_section_name(), LOCATION_SECTION, dynamicCategoryName);
        return me();
    }
    public UnoAppItemCategoryPage generate_category_name(String categoryName) {
        log.info("Generating dynamic category name :" + categoryName);
        String dynamicCategoryName = categoryName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        env.writeIniFile(ITEM_SECTION, ITEM_CATEGORY_NAME, dynamicCategoryName);
        envSubscriptionIni.writeIniFile(get_section_name(), LOCATION_SECTION, dynamicCategoryName);
        return me();
    }

    public UnoAppItemCategoryPage generate_category_name_for_second_location(String categoryName) {
        log.info("Generating dynamic category name :" + categoryName);
        String dynamicCategoryName = categoryName + RandomStringUtils.randomAlphabetic(Integer.parseInt(SEVEN));
        env.writeIniFile(ITEM_SECTION, SECOND_ITEM_CATEGORY_NAME, dynamicCategoryName);
        return me();
    }

    public UnoAppItemCategoryPage create_item_category_for_second_location(Map<String, String> itemCategoryData) {
        log.info("Create Item Category");
        click_catalog_tab();
        click_item_category();
        enter_item_category_name(itemCategoryData.get(SECOND_ITEM_CATEGORY_NAME));
        enter_item_tax_code(itemCategoryData.get(ITEM_TAX_CODE));
        enter_item_tax_rate_override(itemCategoryData.get(ITEM_TAX_RATE));
        category_save_button();
        return me();
    }
    public UnoAppItemCategoryPage create_item_category(Map<String, String> itemCategoryData) {
        log.info("Create Item Category");
        click_catalog_tab();
        click_item_category();
        enter_item_category_name(itemCategoryData.get(ITEM_CATEGORY_NAME));
        wait_until(2);
        enter_item_tax_code(itemCategoryData.get(ITEM_TAX_CODE));
        enter_item_tax_rate_override(itemCategoryData.get(ITEM_TAX_RATE));
        category_save_button();
        return me();
    }


}
