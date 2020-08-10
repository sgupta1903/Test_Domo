package pagetest.unoapppage;
/*
Created By: Shilpi Gupta
Date: 11/13/2019
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppLocationProfileTabPage extends AbcCommonAbstractPage<UnoAppLocationProfileTabPage>
{
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);

    private By texName = By.xpath("//div[@data-abc-id='name']//input[@name='name']");
    private By textPhone = By.xpath("//div[@data-abc-id='phoneNumber']//input[@name='phone.number']");
    private By textEmail = By.xpath("//div[@data-abc-id='email']//input[@name='email']");
    private By textLocationName = By.xpath("//div[@data-abc-id='name']//input[@name='name']");
    private By textPhoneNumber = By.xpath("//div[@data-abc-id='phoneNumber']//input[@name='phone.number']");
    private By textUpdateEmail = By.xpath("//div[@data-abc-id='email']//input[@name='email']");
    private By textLocTimezone = By.xpath("//div[@data-abc-id='timezoneInput']//input[@name='timezone']");
    private By saveBtn = By.xpath("//button[@data-abc-id = 'submitButton']");
    private By locationProfileTab = By.xpath("//i[@data-abc-id = 'profileTitleIcon']");
    private By locationProfileTabEditButton = By.xpath("//div[contains(@class , 'locationInfo')]//i[contains(@class ,'icon-pencil')]");
    String locationData = "//div[@data-abc-id = '%s']//input";
    String locationArea = "//div[@data-abc-id = '%s']//div[contains(@class,'FormSelect__single')]";

    public By get_location_detail(String fieldName) {
        log.info("Fetching xpath for " + fieldName);
        return By.xpath(String.format(locationData, fieldName));
    }

    public By get_location_area(String fieldName) {
        log.info("Fetching xpath for " + fieldName);
        return By.xpath(String.format(locationArea, fieldName));
    }

    public UnoAppLocationProfileTabPage verify_loc_name() {
        log.info("Verifying location name ");
        verify_element_by_value(
                get_location_detail("name"),
                env.getConfigPropertyValue("LOCATION", "locationName"));
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_phone_number(String expectedPhoneNumber) {
        log.info("Verifying location phone number ");
        verify_element_by_value(get_location_detail("phoneNumber"), expectedPhoneNumber);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_email(String expectedEmailAddress) {
        log.info("Verifying location email address ");
        verify_element_by_value(get_location_detail("email"), expectedEmailAddress);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_address_line(String expectedAdd1, String expectedAdd2) {
        log.info("Verifying location address lines ");
        verify_element_by_value(get_location_detail("address1"), expectedAdd1);
        verify_element_by_value(get_location_detail("address2"), expectedAdd2);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_state(String expectedState) {
        log.info("Verifying location state ");
        verify_element_by_text(get_location_area("stateId"), expectedState);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_city(String expectedCity) {
        log.info("Verifying location city ");
        verify_element_by_value(get_location_detail("city"), expectedCity);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_zip(String expectedZip) {
        log.info("Verifying location zip code ");
        verify_element_by_value(get_location_detail("zipCode"), expectedZip);
        return me();
    }

    public UnoAppLocationProfileTabPage verify_loc_time_zone(String expectedTimeZone) {
        log.info("Verifying location time zone ");
        verify_element_by_text(get_location_area("timezone"), expectedTimeZone);
        return me();
    }

    public UnoAppLocationProfileTabPage update_loc_name() {
        log.info("Entering the Location Name");
        write_location_name("locationName");
        wait_until(2);
        return enter(this.texName, env.getConfigPropertyValue("LOCATION", "locationName"));
    }

    public UnoAppLocationProfileTabPage update_loc_phone(String phone) {
        log.info("Entering the Org phone");
        return enter(this.textPhone, phone);
    }

    public UnoAppLocationProfileTabPage update_loc_email(String email) {
        log.info("Entering the Org email");
        return enter(this.textEmail, email);
    }

    public UnoAppLocationProfileTabPage update_loc_name(String city) {
        log.info("Entering the Org city");
        return enter(this.textLocationName, city);
    }

    public UnoAppLocationProfileTabPage update_loc_phone_number(String textPhoneNumber) {
        log.info("Entering the Org zip");
        return enter(this.textPhoneNumber, textPhoneNumber);
    }

    public UnoAppLocationProfileTabPage clickSaveButton() {
        log.info("Click on Save Location Button");
        wait_until(2);
        click(saveBtn);
        wait_until(2);
        return me();
    }

    public UnoAppLocationProfileTabPage update_email(String dataSetName) {
        log.info(" Select state " + dataSetName + " data set");
        verify(presenceOfElementLocated(textUpdateEmail), 60, 2000);
        enter(textUpdateEmail, dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppLocationProfileTabPage update_timezone(String dataSetName) {
        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textLocTimezone), 60, 1000);
        enter(textLocTimezone, dataSetName);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppLocationProfileTabPage write_location_name(String locationKey) {
        log.info("Enter location name");
        env.writeIniFile("LOCATION", locationKey, "Location" + get_alphabetical_data(5));
        return me();
    }

    public UnoAppLocationProfileTabPage select_location_profile_tab() {
        log.info("selecting location profile tab");
        click(locationProfileTab);
        return me();
    }

    public UnoAppLocationProfileTabPage select_edit_icon() {
        log.info("selecting edit button in profile tab");
        click(locationProfileTabEditButton);
        return me();
    }

    public UnoAppLocationProfileTabPage update_location_name_email(String email) {
        log.info("Updating location information : name and email");
        update_loc_name();
        update_loc_email(email);
        return me();
    }

    public UnoAppLocationProfileTabPage update_location_phone_number(String phone) {
        log.info("Updating location phone number");
        update_loc_phone(phone);
        return me();
    }

    public UnoAppLocationProfileTabPage update_location_address(String LocName, String state, String zip, String timeZone) {
        log.info("Updating location address details : address line one, two, city, state zip code and timezone");
        update_loc_name(LocName);
        update_loc_phone_number(zip);
        update_email(state);
        update_timezone(timeZone);
        return me();
    }
}