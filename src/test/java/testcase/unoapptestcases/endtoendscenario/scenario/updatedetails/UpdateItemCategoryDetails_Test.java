package testcase.unoapptestcases.endtoendscenario.scenario.updatedetails;

/*
Created By: Shilpi Gupta
Date: 12/27/2019
*/

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppItemCategoryPage;
import pagetest.unoapppage.UnoAppLocationPage;
import pagetest.unoapppage.UnoAppLoginPage;
import pagetest.unoapppage.UnoAppOrganizationPage;
import ui.AbstractAutoUITest;
import static helper.AppConstants.*;

@Issue("A30TP-32380")
@Slf4j
public class UpdateItemCategoryDetails_Test extends AbstractAutoUITest {


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
    @Description("Select a Catalog-ItemCategory")
    @TmsLink("A30TP-8229")
    public void editItemCategory() {
        log.info("Selecting the details for already existing item category ");
        setTestResultOnXRay("A30TP-8229");
        getPage(UnoAppItemCategoryPage.class)
                .select_item_category_radio_button()
                .select_item_category_by_name();
    }

    @Test
    @Description("click on edit button for Catalog-ItemCategory")
    @TmsLink("A30TP-32485")
    public void clickOnEditButton() {
        log.info("Clicking the details for already existing item category ");
        setTestResultOnXRay("A30TP-32485");
        getPage(UnoAppItemCategoryPage.class)
                .click_edit_button();
    }

    @Test
    @Description("update item category name")
    @TmsLink("A30TP-32483")
    public void updateItemCategoryName() {
        log.info("update item category name");
        setTestResultOnXRay("A30TP-32483");
        getPage(UnoAppItemCategoryPage.class)
                .update_item_category_name();
    }

    @Test
    @Description("update item category tax details")
    @TmsLink("A30TP-32484")
    public void updateItemCategoryTaxInformation() {
        log.info("update item category tax details ");
        setTestResultOnXRay("A30TP-32484");
        getPage(UnoAppItemCategoryPage.class)
                .update_item_category_tax_details();
    }

    @Test
    @Description("update item category description")
    @TmsLink("A30TP-32487")
    public void updateItemCategoryDescription() {
        log.info("update item category description");
        setTestResultOnXRay("A30TP-32487");
        getPage(UnoAppItemCategoryPage.class)
                .update_item_category_description();
    }

    @Test
    @Description("Save the updated details for item category name")
    @TmsLink("A30TP-32486")
    public void saveItemCategoryDetailsAfterEditing() {
        log.info("Saving the details which are updated");
        setTestResultOnXRay("A30TP-32486");
        getPage(UnoAppItemCategoryPage.class)
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
