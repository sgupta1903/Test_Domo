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
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppMemberPage extends AbcCommonAbstractPage<UnoAppMemberPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    EnvProperty migratedEnv = EnvProperty.getInstance(AppConstants.MIGRATED_INI);
    private By textCity = By.xpath("//input[@name='city']");
    private By textmemZip = By.xpath("//div[@data-abc-id='postalCodeInput']//input[@name='postalCode']");
    private By continueBtn = By.xpath("//button[@data-abc-id='memberContinueButton']");
    private By homeIcon = By.xpath("//a[@href='/app/home']/i");
    private By textFirstName = By.xpath("//input[@name='firstName']");
    private By textLastName = By.xpath("//input[@name='lastName']");
    private By textAddress = By.xpath("//input[@name='address']");
    private By textMemState = By.xpath("//div[@data-abc-id='stateInput']");
    private By textMemCountry = By.xpath("//div[@data-abc-id='countryInput']");
    private By memberPage = By.xpath("//h2[@data-abc-id = 'memberListPageHeader']");
    private String addSubscriptionButton = "//td[text() = '%s']/..//button[@data-abc-id = 'createSubscription']";

    //secondary member details
    String secondarySection = "//h5[contains(.,'Secondary')]/../following-sibling::div//div";
    private By secMemberFirstName = By.xpath(secondarySection + "//input[@name='firstName']");
    private By secMemberLastName = By.xpath(secondarySection + "//input[@name='lastName']");
    private By secMemberPrimaryPhNum = By.xpath(secondarySection + "//input[@name='primaryPhone']");

    //member tab
    private By memberLink = By.xpath("//a[@href='/app/member-management']//i[@data-abc-id='userCheckIcon']");
    private By memberSearchBox = By.xpath("//input[@id='searchInput']");
    private String memberName = "//span[@data-abc-id = 'memberNameText']";
    private String memberFromList = "//span[@data-abc-id = 'memberNameText'][contains(.,'%s')]";
    private By memberNumber = By.xpath("//input[@name='number']");
    String memberAgreementNumber = "//p[text() = '%s']";


    private By memberPrimaryPhone = By.xpath("//input[@name='primaryPhone']");
    private By addMemberButton = By.xpath("//i[@data-abc-id='createMemberIcon']");
    private By secondaryMemberSection = By.xpath("//h5[contains(.,'Secondary')]");

    //update member
    private By updateBtn = By.xpath("//button[@data-abc-id='editButton']//i[@data-abc-id='editButtonIcon']");
    private By submitUpdateBtn = By.xpath("//button[@data-abc-id='editMemberSubmit']");
    private By createPayor= By.xpath("//div/button[text()='Continue']");

    public By get_member_by_name(String memberFirstName) {
        return By.xpath(String.format(memberName, memberFirstName));
    }

    public By get_migrated_member(String memberLastName) {
        return By.xpath(String.format(memberFromList, memberLastName));
    }

    public By get_member_by_agreement_id(String agreementNumber) {
        return By.xpath(String.format(memberAgreementNumber, agreementNumber));

    }

    public By get_location_to_add_subscription(String locationName) {
        return By.xpath(String.format(addSubscriptionButton, locationName));
    }

    public UnoAppMemberPage click_continue_button() {
        log.info("Click On continue button");
        wait_until(2);
        click(continueBtn);
        return me();

    }

    public UnoAppMemberPage click_home_icon() {
        wait_until(5);
        log.info("Click On Home Icon");
        verify(presenceOfElementLocated(homeIcon), 60, 2000);
        return click(homeIcon);
    }

    public UnoAppMemberPage click_add_member_button() {
        click(addMemberButton);
        return me();
    }

    public UnoAppMemberPage enter_member_add(String add) {
        log.info("Entering the Member Address");
        return enter(this.textAddress, add);
    }

    public UnoAppMemberPage enter_first_name(String firstName) {
        log.info("Entering the First Name");
        return enter(this.textFirstName, firstName);
    }

    public UnoAppMemberPage enter_last_name(String name) {
        log.info("Entering the Last Name");
        return enter(this.textLastName, name);
    }

    public UnoAppMemberPage enter_mem_city(String city) {
        log.info("Entering the Org city");
        return enter(this.textCity, city);
    }

    public UnoAppMemberPage enter_mem_zip(String zip) {
        log.info("Entering the Mem zip");
        wait_until(2);
        scroll_to_element(textmemZip);
        return enter(this.textmemZip, zip);
    }

    public UnoAppMemberPage enter_mem_number(String number) {
        log.info("Entering the Mem zip");
        return enter(this.memberNumber, number);
    }

    public UnoAppMemberPage enter_mem_primary_phone(String primaryPhone) {
        log.info("Entering the Mem zip");
        return enter(this.memberPrimaryPhone, primaryPhone);
    }

    public UnoAppMemberPage memberLink() {
        log.info("Click on Member Link");
        verify(presenceOfElementLocated(memberLink), 5, 2000);
        return click(memberLink);
    }

    public UnoAppMemberPage click_add_member_btn() {
        log.info("Click On add member button");
        return click(addMemberButton);
    }

    public UnoAppMemberPage select_state(String dataSetName) {
        log.info(" Select state " + dataSetName);
        verify(presenceOfElementLocated(textMemState), 60, 2000);
        click(textMemState);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();

    }

    public UnoAppMemberPage select_country(String dataSetName) {
        log.info(" Select country " + dataSetName);
        verify(presenceOfElementLocated(textMemCountry), 60, 2000);
        click(textMemCountry);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();

    }

    public UnoAppMemberPage generate_member_first_name(String memberName) {
        log.info("Generating dynamic member first name");
        String memberFirstName = memberName + randomAlphabetic(2);
        env.writeIniFile(MEMBER_SECTION, MEMBER_FIRST_NAME, memberFirstName);
        return me();
    }

    public UnoAppMemberPage generate_second_member_first_name(String memberName) {
        log.info("Generating dynamic member first name");
        String memberFirstName = memberName + randomAlphabetic(2);
        env.writeIniFile(MEMBER_SECTION, SECOND_MEMBER, memberFirstName);
        return me();
    }

    public void create_member(Map<String, String> memberData) {
        log.info("Create Member");
        String lastName;
        click_home_icon();
        memberLink();
        click_add_member_btn();
        enter_first_name(memberData.get(MEMBER_FIRST_NAME));
        lastName = memberData.get(MEMBER_LAST_NAME) + randomAlphabetic(7);
        enter_last_name(lastName);
        migratedEnv.writeIniFile(MEMBER_SECTION, MEMBER_LAST_NAME, lastName);
        enter_member_add(memberData.get(MEMBER_ADDRESS));
        enter_mem_city(memberData.get(MEMBER_CITY));
        select_state(memberData.get(MEMBER_STATE));
        select_country(memberData.get(MEMBER_COUNTRY));
        enter_mem_zip(memberData.get(MEMBER_ZIP_CODE));
        enter_mem_primary_phone(memberData.get(MEMBER_PHONE_NUMBER));
        click_continue_button();
        create_payor();
    }
    public UnoAppMemberPage create_payor(){
        log.info("Click Continue to create Payor");
        wait_until(2);
        return click(createPayor);
    }

    public UnoAppMemberPage enter_member_details(Map<String, String> memberData) {
        log.info("Entering member details");
        String firstName = memberData.get(MEMBER_FIRST_NAME) + generate_random_string();
        env.writeIniFile(MEMBER_SECTION, MEMBER_FIRST_NAME, firstName);
        enter_first_name(firstName);
        enter_last_name(memberData.get(MEMBER_LAST_NAME) + generate_random_string());
        enter_member_add(memberData.get(MEMBER_ADDRESS));
        enter_mem_city(memberData.get(MEMBER_CITY));
        select_state(memberData.get(MEMBER_STATE));
        select_country(memberData.get(MEMBER_COUNTRY));
        enter_mem_zip(memberData.get(MEMBER_ZIP_CODE));
        enter_mem_primary_phone(memberData.get(MEMBER_PHONE_NUMBER));
        return me();
    }

    public UnoAppMemberPage enter_member_for_second_location(Map<String, String> memberData) {
        log.info("Entering member details");
        enter_first_name((memberData.get(SECOND_MEMBER)));
        enter_last_name(memberData.get(MEMBER_LAST_NAME) + generate_random_string());
        enter_member_add(memberData.get(MEMBER_ADDRESS));
        enter_mem_city(memberData.get(MEMBER_CITY));
        select_state(memberData.get(MEMBER_STATE));
        select_country(memberData.get(MEMBER_COUNTRY));
        enter_mem_zip(memberData.get(MEMBER_ZIP_CODE));
        enter_mem_primary_phone(memberData.get(MEMBER_PHONE_NUMBER));
        return me();
    }

    public UnoAppMemberPage select_member_created(Map<String, String> memberData) {
        log.info("Selecting member for which subscription is created");
        verify(presenceOfElementLocated(get_member_by_name(memberData.get(MEMBER_FIRST_NAME))));
        click(get_member_by_name(memberData.get(MEMBER_FIRST_NAME)));
        return me();
    }

    public UnoAppMemberPage select_member_migrated_created(Map<String, String> memberData) {
        log.info("Selecting migrated member for which subscription is created");
        verify(presenceOfElementLocated(get_migrated_member(memberData.get(MEMBER_LAST_NAME))));
        click(get_migrated_member(memberData.get(MEMBER_LAST_NAME)));
        return me();
    }

    public UnoAppMemberPage select_second_member_created(Map<String, String> memberData) {
        log.info("Selecting member for which subscription is created");
        verify(presenceOfElementLocated(get_member_by_name(memberData.get(SECOND_MEMBER))));
        click(get_member_by_name(memberData.get(SECOND_MEMBER)));
        return me();
    }

    public UnoAppMemberPage select_member_by_agreement_id() {
        log.info("Selecting member by agreement id");
        click(get_member_by_agreement_id(env.getConfigPropertyValue("INVOICEVERIFICATION", "agreementId")));
        wait_until(1);
        return me();
    }

    public UnoAppMemberPage navigate_and_click_add_member_btn() {
        log.info("Selecting add member page by navigating from home link and then clicking on add member button");
        click_home_icon();
        wait_until(1);
        memberLink();
        click_add_member_btn();
        return me();
    }

    public String get_location_name(String locationKey) {
        log.info("Enter Account Holder Name");
        return env.getConfigPropertyValue("LOCATION", locationKey);

    }

    public UnoAppMemberPage click_add_subscription_for_location(Map<String, String> locationData) {
        log.info("Selecting add member");
        if (locationData.containsKey(LOCATION_NAME))
            click(get_location_to_add_subscription(locationData.get(LOCATION_NAME)));
        else
            click(get_location_to_add_subscription(locationData.get(SECONDLOCATION)));
        return me();
    }

    public UnoAppMemberPage verify_secondary_member_section_displayed() {
        log.info("Verify that secondary member section is displayed");
        wait_until(2);
        verify(presenceOfElementLocated(secondaryMemberSection));
        return me();
    }

    public UnoAppMemberPage enter_secondary_member_details() {
        log.info("Entering secondary members details");
        wait_until(2);
        enter(secMemberFirstName, generate_random_string());
        enter(secMemberLastName, generate_random_string());
        enter(secMemberPrimaryPhNum, "3123127823");
        return me();
    }

    public UnoAppMemberPage verify_member_page_displayed() {
        log.info("Verify that member page is displayed");
        wait_until(2);
        verify(presenceOfElementLocated(memberPage));
        return me();
    }

    public UnoAppMemberPage click_update_member() {
        log.info("Click on update Member");
        verify(presenceOfElementLocated(updateBtn), 5, 2000);
        return click(updateBtn);
    }

    public UnoAppMemberPage click_submit_update() {
        log.info("Click on update submit button");
        verify(presenceOfElementLocated(submitUpdateBtn), 5, 2000);
        return click(submitUpdateBtn);
    }

    public UnoAppMemberPage search_member(Map<String, String> memberData) {
        log.info("Searching member by last name : " + memberData.get(MEMBER_LAST_NAME));
        enter(memberSearchBox, memberData.get(MEMBER_LAST_NAME));
        wait_until(3);
        return me();
    }


}