package testcase.unoapptestcases.endtoendscenario.scenario.updatedetails;

/*
Created By: Shilpi Gupta
Date: 12/30/2019
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.*;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;

@Issue("A30TP-32379")
@Slf4j
public class UpdateItemDetails_Test extends AbstractAutoUITest {
    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    @TmsLink("A30TP-8217")
    public void unoAppLogin() {
        log.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }

    @Test
    @Description("Create a new Organization")
    @TmsLink("A30TP-8221")
    public void createOrganization() {
        log.info("Entering the details to create a new organization ");
        setTestResultOnXRay("A30TP-8221");
        getPage(UnoAppOrganizationPage.class)
                .generate_org_name(ORGANIZATION_NAME)
                .generate_org_url(ORGANIZATION_URL)
                .generate_org_email(ORGANIZATION_EMAIL)
                .generate_org_email(ORGANIZATION_EMAIL)
                .create_organization(getTestData(UNOAPP_INI, ORGANIZATION_SECTION, ORGANIZATION_PHONE_NUMBER,
                        ORGANIZATION_NAME, ORGANIZATION_URL, ORGANIZATION_FIRST_NAME, ORGANIZATION_LAST_NAME,
                        ORGANIZATION_EMAIL, ORGANIZATION_ADD1, ORGANIZATION_ADD2,
                        ORGANIZATION_CITY, ORGANIZATION_STATE, ORGANIZATION_EMAIL,
                        ORGANIZATION_WEBSITE, ORGANIZATION_TIMEZONE, ORGANIZATION_ZIP));
    }

    @Test
    @Description("Create a Location for the organization")
    @TmsLink("A30TP-8212")
    public void createLocation() {
        log.info("Creating the location for the organization and adding bank account details for location ");
        setTestResultOnXRay("A30TP-8212");
        getPage(UnoAppLocationPage.class)
                .generate_location_name(LOCATION_NAME)
                .create_location(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_NAME,
                        LOCATION_PHONE_NUMBER, LOCATION_EMAIL, LOCATION_ADD1,
                        LOCATION_ADD2, LOCATION_CITY, LOCATION_ZIP, LOCATION_STATE,
                        LOCATION_TIMEZONE, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER))
                .add_location_bank_details(getTestData(UNOAPP_INI, LOCATION_SECTION, LOCATION_ACCOUNT_HOLDER_NAME,
                        LOCATION_BANK_ROUTING_NUMBER));
    }

    @Test
    @Description("Create a Catalog-ItemCategory")
    @TmsLink("A30TP-8229")
    public void createItemCategory() {
        log.info("Entering the details for creating item category ");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Create Catalog-Item")
    @TmsLink("A30TP-8241")
    public void createItem() {
        log.info("Entering the details of item ");
        setTestResultOnXRay("A30TP-8241");
        getPage(UnoAppItemPage.class)
                .generate_item_name(ITEM_NAME)
                .create_item(getTestData(UNOAPP_INI, ITEM_SECTION, ITEM_TYPE,
                        AUTO_PAY, ITEM_CATEGORY_NAME, ITEM_NAME));
    }

    @Test
    @Description("Create a Catalog-ItemCategory")
    @TmsLink("A30TP-8229")
    public void createSecondItemCategory() {
        log.info("Entering the details for creating item category ");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .generate_category_name(ITEM_CATEGORY_NAME)
                .create_item_category(getTestData(UNOAPP_INI, ITEM_SECTION,
                        ITEM_CATEGORY_NAME, ITEM_TAX_CODE, ITEM_TAX_RATE));
    }

    @Test
    @Description("Select item from list")
    @TmsLink("30TP-32560")
    public void selectItemFromItemList() {
        log.info("Select item from list ");
        setTestResultOnXRay("30TP-32560");
        getPage(UnoAppItemPage.class)
                .select_item_from_list();
    }

    @Test
    @Description("Click on edit button")
    @TmsLink("A30TP-32533")
    public void clickEditButton() {
        log.info("Click on edit button");
        setTestResultOnXRay("A30TP-32533");
        getPage(UnoAppItemPage.class)
                .click_edit_button();
    }

    @Test
    @Description("Update item name")
    @TmsLink("A30TP-32534")
    public void updateItemName() {
        log.info("Update item name");
        setTestResultOnXRay("A30TP-32534");
        getPage(UnoAppItemPage.class)
                .update_item_name();
    }

    @Test
    @Description("Update item type")
    @TmsLink("A30TP-32535")
    public void updateItemtype() {
        log.info("Update item type");
        setTestResultOnXRay("A30TP-32535");
        getPage(UnoAppItemPage.class)
                .update_item_type();
    }

    @Test
    @Description("Update item category")
    @TmsLink("A30TP-32536")
    public void updateItemCategory() {
        log.info("Update item category");
        setTestResultOnXRay("A30TP-32536");
        getPage(UnoAppItemPage.class)
                .update_item_category();
    }

    @Test
    @Description("Update item default price")
    @TmsLink("A30TP-32537")
    public void updateItemDefaultPrice() {
        log.info("Update item default price");
        setTestResultOnXRay("A30TP-32537");
        getPage(UnoAppItemPage.class)
                .update_item_default_price();
    }

    @Test
    @Description("Update item description")
    @TmsLink("A30TP-32538")
    public void updateItemDescription() {
        log.info("Update item description");
        setTestResultOnXRay("A30TP-32538");
        getPage(UnoAppItemPage.class)
                .update_item_description();
    }

    @Test
    @Description("Click on save button")
    @TmsLink("A30TP-32540")
    public void ClickSaveButton() {
        log.info("Click on save button");
        setTestResultOnXRay("A30TP-32540");
        getPage(UnoAppItemPage.class)
                .click_save_button();
    }
    @Test
    @Description("Logout from the Uno Application")
    @TmsLink("A30TP-8219")
    public void unoAppLogout() {
        log.info("Logout from the Uno Application");
        setTestResultOnXRay("A30TP-8219");
        getPage(UnoAppLoginPage.class)
                .unoApp_logout_with_teardown();
    }
}
