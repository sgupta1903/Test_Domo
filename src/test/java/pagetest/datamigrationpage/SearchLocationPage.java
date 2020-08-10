package pagetest.datamigrationpage;

import config.EnvProperty;
import datastore.StoreClubDetails;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
//import static pagetest.obcUIPage.AccountSummaryPage.storeClubDetails;
//import static util.UtilityGeneric.convertObjectIntoString;

//Created By Monika Phoughat
//Date : 13-09-2019
public class SearchLocationPage extends AbcCommonAbstractPage<SearchLocationPage> {

    EnvProperty env = EnvProperty.getInstance(DATA_MIGRATION_INI);
    //private StoreClubDetails storeClubDetails = DataBuilder.getClubDetails();

   // String locationNo = storeClubDetails.getClubNumber();

    private By edit = By.xpath("//*[@data-abc-id='locationInfoHeaderButtonIcon']");
    private By name = By.xpath("//*[@id='name']");
    private By phoneNumber = By.xpath("//input[@id='phone.number']");
    private By emailAddress = By.xpath("//*[@id='email']");
    private By address = By.xpath("//*[@id='address1']");
    private By address2 = By.xpath("//*[@id='address2']");
    private By cityName = By.xpath("//*[@id='city']");
    private By zipCode = By.xpath("//*[@id='zipCode']");
    private By locationTab = By.xpath("//*[@id=\"react-tabs-2\"]");
    private By locationNameLink = By.xpath("//table/tbody/tr[1]");
    private By locationBtn = By.xpath("//i[@data-abc-id='buildingIcon']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By backButton = By.xpath("//i[@data-abc-id='authorizedBoxActionIcon']");

    public SearchLocationPage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        enter(searchLocation, clubNumber);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + clubNumber + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    public SearchLocationPage click_location_tab() {
        logger.info("Click on Location Tab");
        verify(presenceOfElementLocated(locationTab), 60, 2000);
        wait_until(2);
        return click(locationTab);
    }

    public SearchLocationPage click_location_link() {
        logger.info("Click on Location  Link");
        verify(presenceOfElementLocated(locationNameLink), 60, 2000);
        wait_until(2);
        return click(locationNameLink);
    }

    public SearchLocationPage click_location_edit_btn() {
        logger.info("Clicking on Edit Button for Location ");
        verify(elementToBeClickable(edit), 60, 2000);
        wait_until(2);
        return click(edit);
    }

    public SearchLocationPage verify_loc_name(String orgname) {
        logger.info("Verifying Location Name");
        verify_element_by_value(name, orgname);
        return me();
    }

    public SearchLocationPage verify_loc_email(String email) {
        logger.info("Verifying Location Email");
        verify_element_by_value(emailAddress, email);
        return me();
    }

    public SearchLocationPage verify_loc_address(String add) {
        logger.info("Verifying Location Address");
        verify_element_by_value(address, add);
        return me();
    }

    public SearchLocationPage verify_loc_address2(String add2) {
        logger.info("Verifying Location Address 2");
        verify_element_by_value(address2, add2);
        return me();
    }

    public SearchLocationPage verify_loc_city(String city) {
        logger.info("Verifying Location City");
        verify_element_by_value(cityName, city);
        return me();
    }

    public SearchLocationPage verify_loc_zip(String zip) {
        logger.info("Verifying Location Zip");
        verify_element_by_value(zipCode, zip);
        return me();
    }

  /*  public SearchLocationPage verify_loc_details(Map locInfoDbMap) {
        logger.info("Verifying Location Details");
        click_location_tab();
        click_location_link();
        click_location_edit_btn();

        verify_all(
                () -> verify_loc_name(convertObjectIntoString(locInfoDbMap.get("name"))),
                () -> verify_loc_email(convertObjectIntoString(locInfoDbMap.get("email"))),
                () -> verify_loc_address(convertObjectIntoString(locInfoDbMap.get("address.address1"))),
                () -> verify_loc_address2(convertObjectIntoString(locInfoDbMap.get("address.address2"))),
                () -> verify_loc_city(convertObjectIntoString(locInfoDbMap.get("address.city"))),
                () -> verify_loc_zip(convertObjectIntoString(locInfoDbMap.get("zipCode")))
        );
        return me();
    }
*/
    public SearchLocationPage verify_club_phone(String phone) {
        logger.info("Verifying Club Phone Number");
        verify_element_by_value(phoneNumber, phone);
        return me();
    }

    public SearchLocationPage verify_club_details_obc(StoreClubDetails storeClubDetails,String clubNumber) {
        logger.info("Verifying Club Details from OBC UI to the UNO Application");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);

        verify_all(
                () -> verify_loc_name(storeClubDetails.getClubName()),
                () -> verify_club_phone(storeClubDetails.getPhone()),
                () -> verify_loc_address(storeClubDetails.getAddress()),
                () -> verify_loc_city(storeClubDetails.getCity()),
                () -> verify_loc_zip(storeClubDetails.getZipCode())
        );
        click(backButton);
        return me();
    }
}

