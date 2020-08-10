package pagetest.datamigrationpage;

import config.EnvProperty;
import datastore.StoreClubDetails;
import helper.AppConstants;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.convertObjectIntoString;

//Created By Monika Phoughat
//Date : 12-09-2019

public class SearchOrgPage extends AbcCommonAbstractPage<SearchOrgPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.DATA_MIGRATION_INI);

    //Organisations
    private By searchTab = By.xpath("//input[@id='react-select-2-input']");
    private By businessLink = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By orgRadioBtn = By.xpath("//label[contains(text(),'Organizations')]");
    private By orgHomePlaceExists = By.xpath("//h1[contains(@class,'ui-elastic-text')]");
    private By orgList = By.xpath("//tr[@data-abc-id='landingPageRow']");
    private By edit = By.xpath("//*[@data-abc-id='organizationInfoHeaderButtonIcon']");
    private By orgName = By.xpath("//*[@id='name']");
    private By orgEmail = By.xpath("//*[@id='email']");
    private By orgAddress = By.xpath("//input[@name='address.address1']");
    private By orgAddress2 = By.xpath("//*[@id='address2']");
    private By orgCity = By.xpath("//input[@name='address.city']");
    private By orgZip = By.xpath("//input[@name='address.zipCode']");
    private By orgTimeZone = By.xpath("//descendant::div[contains(@class,'single-value')][3]");
    private By orgPhone = By.xpath("//input[@id='phone.number']");

    public SearchOrgPage enter_org_name(String enter_org_name) {
        logger.info("Entering the Organisation Name");
        enter(this.searchTab, enter_org_name);
        verify(presenceOfElementLocated(searchTab), 20, 2000);
        click(searchTab);
        wait_until(4);
        press_end_key();
        wait_until(2);
        press_end_key();
        wait_until(2);
        press_end_key();
        wait_until(2);
        press_end_key();
        press_enter_key();
        return me();
    }

    public SearchOrgPage click_on_business_link() {
        logger.info("Click on Business Link");
        verify(presenceOfElementLocated(businessLink), 60, 2000);
        return click(businessLink);
    }

    public SearchOrgPage org_radio_btn() {
        logger.info("Select Organization Radio Button");
        verify(elementToBeClickable(orgRadioBtn), 60, 2000);
        return click(orgRadioBtn);
    }

    public SearchOrgPage click_org_list() {
        logger.info("Click on  Organisation List ");
        verify(elementToBeClickable(orgList), 60, 2000);
        return click(orgList);
    }

    public SearchOrgPage verify_org_list_exist() {
        logger.info("Verifying Org List Displayed");
        verify(presenceOfElementLocated(orgList), 60, 2000);
        return me();
    }

    public SearchOrgPage click_org_edit_btn() {
        logger.info("Clicking on Edit Button for Organisation ");
        verify(elementToBeClickable(edit), 60, 2000);
        return click(edit);
    }

    public SearchOrgPage verify_org_name(String orgname) {
        logger.info("Verifying Name of  Organisation ");
        verify_element_by_value(orgName, orgname);
        return me();
    }

    public SearchOrgPage verify_org_email(String email) {
        logger.info("Verifying Organisation Email");
        verify_element_by_value(orgEmail, email);
        return me();
    }

    public SearchOrgPage verify_org_address(String address) {
        logger.info("Verifying Organisation address");
        verify_element_by_value(orgAddress, address);
        return me();
    }

    public SearchOrgPage verify_org_address2(String address2) {
        logger.info("Verifying Organisation Address 2");
        verify_element_by_value(orgAddress2, address2);
        return me();
    }

    public SearchOrgPage verify_org_city(String city) {
        logger.info("Verifying Organisation City");
        verify_element_by_value(orgCity, city);
        return me();
    }

    public SearchOrgPage verify_org_zip(String zip) {
        logger.info("Verifying Organisation Zip");
        verify_element_by_value(orgZip, zip);
        return me();
    }

    public SearchOrgPage verify_org_timezone(String timezone) {
        logger.info("Verifying Organisation Time Zone");
        verify_element_by_text(orgTimeZone, timezone);
        return me();
    }

    public SearchOrgPage search_organization(String orgName) {
        logger.info("Searching Organisation ");
        handle_browser_exceptions();
        enter_org_name(orgName);
        wait_until(4);
        return me();
    }

    public SearchOrgPage verify_org_home_text(String orgName) {
        logger.info("Verifying Organisation name exist after serach on Home place");
        verify_element_by_text(orgHomePlaceExists, orgName);
        return me();
    }

    public SearchOrgPage click_on_org_radio_btn() {
        logger.info("Click on Radio Button for Details");
        click_on_business_link();
        org_radio_btn();
        wait_until(2);
        return me();
    }

    public SearchOrgPage verify_org_phone(String phone) {
        logger.info("Verifying Organisation Phone Number");
        verify_element_by_value(orgPhone, phone);
        return me();
    }

    public SearchOrgPage verify_org_details_obc(StoreClubDetails storeClubDetails) {
        logger.info("Verifying Organisation Details from OBC UI to the UNO Application");
        org_radio_btn();
        wait_until(2);
        click(orgList);

        verify_all(
                () -> verify_org_phone(storeClubDetails.getPhone()),
                () -> verify_org_address(storeClubDetails.getAddress()),
                () -> verify_org_city(storeClubDetails.getCity()),
                () -> verify_org_zip(storeClubDetails.getZipCode())
        );
        return me();
    }

    public SearchOrgPage verify_org_deatils(Map orgInfoDbMap) {
        logger.info("Verifying Organisation Details");
        click_org_list();
        click_org_edit_btn();

        verify_all(
                () -> verify_org_name(convertObjectIntoString(orgInfoDbMap.get("name"))),
                () -> verify_org_email(convertObjectIntoString(orgInfoDbMap.get("email"))),
                () -> verify_org_address(convertObjectIntoString(orgInfoDbMap.get("address1"))),
                () -> verify_org_address2(convertObjectIntoString(orgInfoDbMap.get("address2"))),
                () -> verify_org_city(convertObjectIntoString(orgInfoDbMap.get("city"))),
                () -> verify_org_zip(convertObjectIntoString(orgInfoDbMap.get("zipCode"))),
                () -> verify_org_timezone(convertObjectIntoString(orgInfoDbMap.get("timezone")))
        );
        return me();
    }
}