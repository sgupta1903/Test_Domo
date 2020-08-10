package pagetest.datamigrationpage;

import config.EnvProperty;
import helper.DataBaseHandler;
import io.qameta.allure.Step;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import org.testng.Assert;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.io.IOException;
import java.util.*;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CatalogPage extends AbcCommonAbstractPage<CatalogPage> {
    EnvProperty envProperty = EnvProperty.getInstance(CASH_STORAGE_INI);
    List<Map<String, String>> categoriesMap = new ArrayList<>();

    private By businessLink = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By edit = By.xpath("//i[contains(@data-abc-id,'ectionHeaderButtonIcon')]");
    private By itemName = By.xpath("//input[@id='itemName']");
    private By itemCategoryName = By.xpath("//input[@id='itemCategoryName']");
    private By itemCategory = By.xpath("//input[@id='itemCategoryInput']");
    //private By itemCategoryDescription = By.xpath("//div[@data-abc-id='itemCategory-description']");
    private By itemCategoryDescription = By.xpath("//input[@id='itemCategoryName']//following::textarea[1]");
    private By itemType = By.xpath("//input[@id='itemTypeInput']");
    private By itemCategoryNameInItem = By.xpath("//div[@id='item-category']");
    private By catalogTab = By.xpath("//li[@data-abc-id='catalogTitle']");
    private By homeLink = By.xpath("//a[@href='/uno-app/app/home']/i");
    private By orgRadioBtn = By.xpath("//*[text()='Organizations']");
    private By orgList = By.xpath("//tr[@data-abc-id='landingPageRow']");
    private By categoriesRadioBtn = By.xpath("//*[text()='Categories']");
    private By closeIcon = By.xpath("//i[contains(@class,'icon-x-thin ui-icon styles--plusIcon')]");
    private By itemCount = By.xpath("//table[contains(@class,'ui-infinite-table')]//tbody//tr");
    private By description = By.xpath("//input[@id='itemAutoPay']//following::textarea[1]");
    protected String itemsName = "//table[contains(@class,'ui-infinite-table')]//tbody//tr[%s]//td[1]//span";
    protected String itemsClick = "//table[contains(@class,'ui-infinite-table')]//tbody//tr[%s]//td[1]";
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By closeButton = By.xpath("//button[@data-abc-id='closeDrawerButton']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private By itemAutoPay = By.xpath("//input[@id='itemAutoPay']");
    private By itemPrice = By.xpath("//input[@id='itemPrice']");
    private By itemCategoryTaxRate = By.xpath("//input[@id='itemCategoryTaxRate']");
    private By taxRateOverride = By.xpath("//input[@id='itemCategoryTaxRateOverride']");
    private By taxCode = By.xpath("//input[@id='itemCategoryTaxCodeInput']");

    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    String dataAbcId = "itemListRow";
    String dataAbcId2 = "itemCategoriesListRow";

    private String itemRowXpath = "//tr[@data-abc-id='%s']/td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']";
    private String CategoriesRowXpath = "//tr[@data-abc-id='%s']/td/span[text()='%s']/../../td/span[text()='%s']";

    Ini ini;

    {
        try {
            ini = new Wini(this.getClass().getResourceAsStream('/' + "cashstorage.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public By get_categories_row_xpath(String dataAbcId2, String itemCategoryName, String itemCategoryDescription) {
        return By.xpath(String.format(CategoriesRowXpath, dataAbcId2, itemCategoryName, itemCategoryDescription));
    }

    @Step("Verifying Categories Details")
    public CatalogPage verify_categories(String itemCategoryName, String itemCategoryDescription) {
        verify(visibilityOfAllElementsLocatedBy(get_categories_row_xpath(dataAbcId2, itemCategoryName, itemCategoryDescription)));
        return me();
    }


    public By get_item_row_xpath(String dataAbcId, String itemName, String itemType, String itemDescription, String itemCategory) {
        return By.xpath(String.format(itemRowXpath, dataAbcId, itemName, itemType, itemDescription, itemCategory));
    }

    @Step("Verifying item")
    public CatalogPage verify_catalog_item(String itemName, String itemType, String itemDescription, String itemCategory) {
        verify(visibilityOfAllElementsLocatedBy(get_item_row_xpath(dataAbcId, itemName, itemType, itemDescription, itemCategory)));
        return me();
    }

    @Step("Click on Location")
    private CatalogPage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    public int get_item_count() {
        logger.info("Getting Total No of Items");
        int itemCount = find_element_count(this.itemCount);
        return itemCount;
    }

    public By get_Item_Name(String no) {
        logger.info("Getting xpath for Item ");
        return By.xpath(String.format(itemsName, no));
    }

    public By click_Item(String no) {
        logger.info("Click on Item");
        return By.xpath(String.format(itemsClick, no));
    }

    public CatalogPage click_item_list(String data) {
        int count = get_item_count();
        Boolean itemFind = true;
        for (int i = 1; i <= count; i++) {
            String actualName = find_element_text(get_Item_Name(String.valueOf(i)));
            if (actualName.equals(data)) {
                wait_until(elementToBeClickable(get_Item_Name(String.valueOf(i))));
                wait_until(4);
                click(click_Item(String.valueOf(i)));
                if (!itemFind) {
                    itemFind = true;
                }
                break;
            } else {
                itemFind = false;
            }
        }
        if (!itemFind) {
            Assert.assertTrue(false);
        }
        return me();
    }

    public CatalogPage click_home_link() {
        logger.info("Click on Home Link");
        verify(elementToBeClickable(homeLink), 60, 2000);
        wait_until(2);
        return click(homeLink);
    }

    public CatalogPage click_on_catalog_tab() {
        logger.info("Click on Catalog Tab");
        verify(presenceOfElementLocated(catalogTab), 60, 2000);
        wait_until(2);
        return click(catalogTab);
    }

    public CatalogPage click_categories_radio_btn() {
        logger.info("Click on Item Categories Radio Button");
        verify(elementToBeClickable(categoriesRadioBtn), 60, 2000);
        return click(categoriesRadioBtn);
    }

    public CatalogPage select_org_radio_btn() {
        logger.info("Select Organization Radio Button");
        verify(elementToBeClickable(orgRadioBtn), 60, 2000);
        return click(orgRadioBtn);
    }

    public CatalogPage click_org_list() {
        logger.info("Click on Organisation List ");
        verify(elementToBeClickable(orgList), 60, 2000);
        wait_until(2);
        return click(orgList);
    }

    public CatalogPage click_business_link() {
        logger.info("Click on Business Link");
        verify(presenceOfElementLocated(businessLink), 60, 2000);
        return click(businessLink);
    }

    public CatalogPage click_edit_btn() {
        logger.info("Clicking on Edit Section ");
        verify(elementToBeClickable(edit), 60, 2000);
        return click(edit);
    }

    public CatalogPage verify_item_name(String itemname) {
        logger.info("Verifying Item Name");
        verify_element_by_value(itemName, itemname);
        return me();
    }

    public CatalogPage verify_item_Category_Name(String itemcategoryname) {
        logger.info("Verifying Item Category Name");
        verify_element_by_value(itemCategoryName, itemcategoryname);
        return me();
    }

    public CatalogPage verify_Category_Name(String expectedItemCategoryName) {
        logger.info("Verifying Item Category Name");
        verify_element_attribute_value(itemCategory, "placeholder", expectedItemCategoryName);
        return me();
    }

    public CatalogPage verify_item_category_name_in_item_page(String itemcategoryname) {
        logger.info("Verifying Item Category Name");
        verify_element_by_text(itemCategoryNameInItem, itemcategoryname);
        return me();
    }

    public CatalogPage verify_item_category_description(String itemcategorydescription) {
        logger.info("Getting Item Category Description");
        verify_element_by_value(itemCategoryDescription, itemcategorydescription);
        return me();
    }

    public CatalogPage verify_item_description(String expectedItemDescription) {
        logger.info("Getting Item Description");
        verify_element_by_value(description, expectedItemDescription);
        return me();
    }

    public CatalogPage verify_item_type(String itemtype) {
        logger.info("Verifying Item Type");
        verify_element_attribute_value(itemType, "placeholder", itemtype);
        //verify_element_by_value(itemType, itemtype);
        return me();
    }

    public CatalogPage click_close_edit_PopUp() {
        logger.info("Closing the Edit Pop-Up");
        return click(closeIcon);
    }

    public CatalogPage click_catalog_tab() {
        logger.info("Click on Catalog Tab");
        click_home_link();
        click_business_link();
        select_org_radio_btn();
        click_org_list();
        click_on_catalog_tab();
        return me();
    }

    public CatalogPage verify_item_auto_pay(String expectedAutoPay) {
        logger.info("Verifying item Auto pay amount");
        verify_element_by_value(itemAutoPay, expectedAutoPay);
        return me();
    }

    public CatalogPage verify_item_price(String expectedItemPrice) {
        logger.info("Verifying item Price");
        verify_element_by_value(itemPrice, expectedItemPrice);
        return me();
    }

    public CatalogPage verify_item_category_tax_Rate(String expectedTaxRate) {
        logger.info("Verifying item Categroy Tax Rate");
        verify_element_by_value(itemCategoryTaxRate, expectedTaxRate);
        return me();
    }

    public CatalogPage verify_item_category_tax_Rate_override(String expectedTaxRateOverride) {
        logger.info("Verifying item Categroy Tax Rate Override");
        verify_element_by_value(taxRateOverride, expectedTaxRateOverride);
        return me();
    }

    public CatalogPage verify_item_category_tax_code(String expectedTaxCode) {
        logger.info("Verifying item Categroy Tax Code");
        verify_element_by_value(taxCode, expectedTaxCode);
        return me();
    }

    public CatalogPage verify_item_details(Map itemInfo) {
        logger.info("Verifying Item Details");
        click_item_list(itemInfo.get("name").toString());
        click_edit_btn();
        verify_item_name(itemInfo.get("name").toString());
        verify_item_type(itemInfo.get("type").toString());
        verify_item_category_name_in_item_page(itemInfo.get("categoryName").toString());
        click_close_edit_PopUp();
        return me();
    }

    public CatalogPage click_category_tab() {
        logger.info("Clicking on Item Category Radio Button");
        click_categories_radio_btn();
        return me();
    }

    public CatalogPage verify_item_category_details(Map itemCategoryInfo) {
        logger.info("Verifying Item Category Details");
        click_item_list(itemCategoryInfo.get("name").toString());
        click_edit_btn();
        verify_item_Category_Name(itemCategoryInfo.get("name").toString());
        verify_item_category_description(itemCategoryInfo.get("description").toString());
        click_close_edit_PopUp();
        return me();
    }

    public CatalogPage verify_item_details(String orgName, DataBaseHandler dataBaseHandler, List itemId) {
        logger.info("Verifying Item Details");
        for (int i = 0; i < itemId.size(); i++) {
            CentralDBPage centralDBPage = new CentralDBPage();
            Map<String, String> itemInfo = centralDBPage.getItemData(itemId.get(i).toString(), dataBaseHandler);
            Map<String, String> itemCategoryInfo = centralDBPage.getItemCategoryData(itemInfo.get("itemCategoryId"), dataBaseHandler);
            itemInfo.put("categoryName", itemCategoryInfo.get("name"));
            verify_item_details(itemInfo);
        }
        return me();
    }

    public CatalogPage verify_item_category_details(String orgName, DataBaseHandler dataBaseHandler, List itemCategoryId) {
        for (int i = 0; i < itemCategoryId.size(); i++) {
            CentralDBPage centralDBPage = new CentralDBPage();
            Map<String, String> itemCategoryInfo = centralDBPage.getItemCategoryData(itemCategoryId.get(i).toString(), dataBaseHandler);
            verify_item_category_details(itemCategoryInfo);
        }
        return me();
    }

    public void parse_my_data(String str) {
        Map<String, String> newMap = new HashMap<>();
        logger.info(str);
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));

        newMap.put("clubNumbers", myList.get(0));
        newMap.put("itemCategoryName", myList.get(1));
        newMap.put("itemCategoryDescription", myList.get(2));

        categoriesMap.add(newMap);
    }

    public void get_categories_details(String clubNumber) {
        String finalcategoriesDetails = null;
        Map<String, String> categoriesMap = ini.get(clubNumber);
        for (Map.Entry<String, String> entry : categoriesMap.entrySet()) {
            String categoriesData[] = entry.getValue().split("\t");
            if ("Pass".equalsIgnoreCase(categoriesData[0])) {
                System.out.println("This is a status");
            } else {
                finalcategoriesDetails = parse_raw_data(entry.getValue().trim());
                parse_my_data(finalcategoriesDetails);
            }
        }
    }

    public String parse_raw_data(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(" ")));
        for (int i = 0; i < myList.size(); i++) {
            if (i < 1) {
                stringBuffer.append(myList.get(i) + ",");
            }
        }
        myList.subList(0, 1).clear();
        StringBuffer myFinalString = new StringBuffer();
        for (int j = 0; j < myList.size(); ++j) {
            myFinalString.append(myList.get(j) + ",");

        }
        StringBuffer categories = stringBuffer.append(myFinalString);
        return String.valueOf(categories).trim();
    }


    public CatalogPage verify_catalog_item_detais(String clubNumber) {
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);

        click(catalogTab);

        verify_catalog_item(envProperty.getConfigPropertyValue(clubNumber, "itemName"), envProperty.getConfigPropertyValue(clubNumber, "itemType"),
                envProperty.getConfigPropertyValue(clubNumber, "itemDescription"), envProperty.getConfigPropertyValue(clubNumber, "itemCategory"));

        click(get_item_row_xpath(dataAbcId, envProperty.getConfigPropertyValue(clubNumber, "itemName"), envProperty.getConfigPropertyValue(clubNumber, "itemType"),
                envProperty.getConfigPropertyValue(clubNumber, "itemDescription"), envProperty.getConfigPropertyValue(clubNumber, "itemCategory")));

        try {
            verify_all(
                    () -> verify_item_name(envProperty.getConfigPropertyValue(clubNumber, "itemName")),
                    () -> verify_item_type(envProperty.getConfigPropertyValue(clubNumber, "itemType")),
                    () -> verify_Category_Name(envProperty.getConfigPropertyValue(clubNumber, "itemCategory")),
                    () -> verify_item_description(envProperty.getConfigPropertyValue(clubNumber, "itemDescription"))
            );
        } finally {
            click(closeButton);
        }

        return me();
    }

    public CatalogPage verify_categories_details(String clubNumber) {
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);

        click(catalogTab);
        click_categories_radio_btn();
        get_categories_details(clubNumber);

        for (int i = 0; i < categoriesMap.size(); ++i) {
            verify_categories(categoriesMap.get(i).get("itemCategoryName"), categoriesMap.get(i).get("itemCategoryDescription"));
            click(get_categories_row_xpath(dataAbcId2, categoriesMap.get(i).get("itemCategoryName"), categoriesMap.get(i).get("itemCategoryDescription")));
            int finalI = i;
            try {
                verify_all(
                        () -> verify_item_Category_Name(categoriesMap.get(finalI).get("itemCategoryName")),
                        () -> verify_item_category_description(categoriesMap.get(finalI).get("itemCategoryDescription"))
                );
            } finally {
                click(closeButton);
            }
        }
        return me();
    }
}



