package pagetest.unoapppage;

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.get_section_name;
@Slf4j
public class UnoAppOrganizationPage extends AbcCommonAbstractPage<UnoAppOrganizationPage> {

    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    EnvProperty envMigratedIni = EnvProperty.getInstance(MIGRATED_INI);
    EnvProperty envSubscriptionIni = EnvProperty.getInstance(SUBSCRIPTION_INI);
    EnvProperty migratedOrgCreated = EnvProperty.getInstance(MIGRATEDORGCREATED_INI);
    private By businessLink = By.xpath("//i[@data-abc-id='clientManagementUiModuleIcon']");
    private By organizationLabel = By.xpath("//*[text()='Organizations']");
    private By addOrganizationButton = By.xpath("//i[@data-abc-id='showAddOrganizationFormIcon']");
    private By texName = By.xpath("//input[@name='name']");
    private By orgFirstName = By.xpath("//div[@data-abc-id='firstName']//input[@name='firstName']");
    private By orgLastName = By.xpath("//div[@data-abc-id='lastName']//input[@name='lastName']");
    private By textPhone = By.xpath("//div[@data-abc-id='phoneNumber']//div[contains(@class,'fieldWrapper')]//input[@id='phone.number']");
    private By textEmail = By.xpath("//input[@name='email']");
    private By textUrl = By.xpath("//input[@name='subDomainAlias']");
    private By textAddress1 = By.xpath("//div[@data-abc-id='address1']//div[contains(@class,'fieldWrapper')]//input[@id='address1']");
    private By textAddress2 = By.xpath("//div[@data-abc-id='address2']//div[contains(@class,'fieldWrapper')]//input[@id='address2']");
    private By textCity = By.xpath("//div[@data-abc-id='city']//div[contains(@class,'fieldWrapper')]//input[@id='city']");
    private By textZipCode = By.xpath("//div[@data-abc-id='postalCode']//input[@id='postalCode']");
    private By textWebsite = By.xpath("//div[@data-abc-id='websiteUrl']//input[@id='websiteUrl']");
    private By textState = By.xpath("//div[@data-abc-id='provinceCode']//input[@id='provinceCodeInput']");
    private By textTimezone = By.xpath("//div[@data-abc-id='timezoneInput']//input[@id='timezoneInput']");
    private By orgsaveBtn = By.xpath("//button[@type='submit']");
    private By organizationInfoName = By.xpath("//label[contains(.,'Organization Name')]/../input[@id='name']");
    private By organizationProfileTabEditButton = By.xpath("//button[@data-abc-id='organizationInfoHeaderButton']//i");
    String organizationName = "//td[@data-abc-id = 'name']//span[text() = '%s']";
    String organizationData = "//div[@data-abc-id = '%s']//input";


    static String orgName = null;
    String accountNumber = null;

    public By get_org_detail(String fieldName) {
        log.info("Fetching xpath for " + fieldName);
        return By.xpath(String.format(organizationData, fieldName));
    }

    public By get_organization(String orgName) {
        wait_until(2);
        log.info("Getting the organization which is craeted by user");
        return By.xpath(String.format(organizationName, orgName));
    }

    public UnoAppOrganizationPage enter_org_name(String name) {
        wait_until(2);
        log.info("Entering the Org Name");
        return enter(this.texName, name);
    }

    public UnoAppOrganizationPage enter_org_first_name(String name) {
        wait_until(2);
        log.info("Entering the Org Name");
        return enter(this.orgFirstName, name);
    }

    public UnoAppOrganizationPage enter_org_last_name(String name) {
        wait_until(2);
        log.info("Entering the Org Name");
        return enter(this.orgLastName, name);
    }

    public UnoAppOrganizationPage enter_org_phone(String phone) {
        log.info("Entering the Org phone");
        return enter(this.textPhone, phone);
    }

    public UnoAppOrganizationPage enter_org_url(String url) {
        log.info("Entering the Org url");
        return enter(this.textUrl, url);
    }

    public UnoAppOrganizationPage enter_org_email(String email) {
        log.info("Entering the Org email");
        return enter(this.textEmail, email);
    }

    public UnoAppOrganizationPage enter_org_add1(String add1) {
        log.info("Entering the Org add1");
        return enter(this.textAddress1, add1);
    }

    public UnoAppOrganizationPage enter_org_add2(String add2) {
        log.info("Entering the Org add2");
        return enter(this.textAddress2, add2);
    }

    public UnoAppOrganizationPage enter_org_city(String city) {
        log.info("Entering the Org city");
        return enter(this.textCity, city);
    }

    public UnoAppOrganizationPage enter_org_zip(String zip) {
        log.info("Entering the Org zip");
        return enter(this.textZipCode, zip);
    }

    public UnoAppOrganizationPage enter_org_website(String website) {
        log.info("Entering the Org website");
        return enter(this.textWebsite, website);
    }

    public UnoAppOrganizationPage business_link() {
        log.info("Click on Business Link");
        return click(businessLink);
    }

    public UnoAppOrganizationPage org_radio_btn() {
        log.info("Select Organization Radio Button");
        verify(elementToBeClickable(organizationLabel), 60, 2000);
        return click(organizationLabel);
    }

    public UnoAppOrganizationPage add_org_btn() {
        log.info("Click on Add Organization Button");
        return click(addOrganizationButton);
    }

    public UnoAppOrganizationPage org_save_btn() {
        log.info("Click on Save Organization Button");
        verify(elementToBeClickable(orgsaveBtn), 60, 2000);
        click(orgsaveBtn);
        return me();

    }

    public UnoAppOrganizationPage select_org_state(String dataSetName) {
        log.info(" Select state " + dataSetName + " data set");
        verify(presenceOfElementLocated(textState), 60, 2000);
        click(textState);
        enter(textState, dataSetName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public UnoAppOrganizationPage select_org_timezone(String dataSetName) {

        log.info(" Select " + dataSetName + " data set");
        verify(presenceOfElementLocated(textTimezone), 60, 2000);
        click(textTimezone);
        enter(textTimezone, dataSetName);
        enter_by_key();
        wait_until(2);
        return me();
    }

    public UnoAppOrganizationPage select_edit_icon() {
        wait_until(2);
        log.info("Select Edit Organization icon");
        click(organizationProfileTabEditButton);
        return me();
    }

    public UnoAppOrganizationPage update_org_name_email(Map<String, String> orgData) {
        log.info("Updating Organization information : name and email");
        update_org_name(orgData.get(ORGANIZATION_NAME));
        update_org_email(orgData.get(ORGANIZATION_EMAIL));
        return me();
    }

    public UnoAppOrganizationPage generate_org_name(String orgName) {
        log.info("Generating dynamic organization name");
        String organizationName = orgName + randomAlphabetic(7);
        env.writeIniFile("ORGANIZATION", "organizationName", organizationName);
        envMigratedIni.writeIniFile("ORGANIZATION", "organizationName", organizationName);
        envSubscriptionIni.writeIniFile(get_section_name(), "organizationName", organizationName);
        return me();
    }


    public UnoAppOrganizationPage generate_org_url(String orgURL) {
        log.info("Generating dynamic organization url");
        String organizationUrl = orgURL + randomAlphabetic(7);
        env.writeIniFile(ORGANIZATION_SECTION, ORGANIZATION_URL, organizationUrl);
        envMigratedIni.writeIniFile("ORGANIZATION", "organizationUrl", organizationUrl);
        return me();
    }

    public UnoAppOrganizationPage generate_org_email(String orgEmail) {
        log.info("Generating dynamic organization email");
        String organizationEmail = randomAlphabetic(Integer.parseInt(SEVEN)) + orgEmail + emailDomain;
        env.writeIniFile(ORGANIZATION_SECTION, ORGANIZATION_EMAIL, organizationEmail);
        envMigratedIni.writeIniFile("ORGANIZATION", "orgEmail", organizationEmail);
        return me();
    }

    public UnoAppOrganizationPage create_organization(Map<String, String> orgData) {
        log.info("Creation of organization has started");
        business_link();
        org_radio_btn();
        add_org_btn();
        enter_org_name(orgData.get(ORGANIZATION_NAME));
        enter_org_url(orgData.get(ORGANIZATION_URL));
        enter_org_first_name(orgData.get(ORGANIZATION_FIRST_NAME));
        enter_org_last_name(orgData.get(ORGANIZATION_LAST_NAME));
        enter_org_phone(orgData.get(ORGANIZATION_PHONE_NUMBER));
        enter_org_email(orgData.get(ORGANIZATION_EMAIL));
        enter_org_add1(orgData.get(ORGANIZATION_ADD1));
        enter_org_add2(orgData.get(ORGANIZATION_ADD2));
        enter_org_city(orgData.get(ORGANIZATION_CITY));
        select_org_state(orgData.get(ORGANIZATION_STATE));
        enter_org_website(orgData.get(ORGANIZATION_WEBSITE));
        select_org_timezone(orgData.get(ORGANIZATION_TIMEZONE));
        enter_by_key();
        enter_org_zip(orgData.get(ORGANIZATION_ZIP));
        org_save_btn();
        return me();
    }

    public UnoAppOrganizationPage store_migrated_org_created(String clubNumber, String orgName) {
        migratedOrgCreated.writeIniSectionName(clubNumber);
        migratedOrgCreated.writeIniFile(clubNumber, "organizationName", orgName);
        return me();
    }

    public String check_club_number_exist(String clubNumber) {
        log.info("Check that organization for the club number is already created in the UNO APP");
        return getValueWhenSectionExist(MIGRATEDORGCREATED_INI, clubNumber);
    }

    public UnoAppOrganizationPage createOrgAndLocationIfNotPresent(String clubNumber) {
        log.info("Checking club number already exist");
        orgName = getValueWhenSectionExist(MIGRATEDORGCREATED_INI, clubNumber);
        return me();
    }

    public UnoAppOrganizationPage save_organization_for_invoice_verification() {
        log.info("Save Organization name for invoice generation");
        env.writeIniFile("ORGANIZATION", "invoiceVerificationOrganizationName", orgName);
        return me();
    }

    public UnoAppOrganizationPage verify_organization_visible(Map<String, String> orgData) {
        log.info("Verifying that user created organization is visible");
        verify(presenceOfElementLocated(get_organization
                (orgData.get(ORGANIZATION_NAME))));
        return me();
    }


    public UnoAppOrganizationPage verify_organization_by_name() {
        log.info("Verifying organization is created successfully");
        verify_element_by_value(organizationInfoName,
                env.getConfigPropertyValue("ORGANIZATION", "organizationName"));

        return me();
    }

    public UnoAppOrganizationPage verify_organization_created_with_migrated_data() {
        log.info("Verifying organization is created successfully with migrated data");
        verify_element_by_value(organizationInfoName,
                envMigratedIni.getConfigPropertyValue("ORGANIZATION", "organizationName"));
        return me();
    }

    public UnoAppOrganizationPage update_org_name(String orgName) {
        log.info("Entering the Organization Name:" + orgName);
        wait_until(2);
        return enter(this.texName, orgName);
    }

    public UnoAppOrganizationPage update_org_email(String email) {
        log.info("Entering the Org email");
        return enter(this.textEmail, email);
    }

    public UnoAppOrganizationPage write_organization_name(String organizationKey) {
        log.info("Enter Organization name");
        orgName = "Organization " + randomAlphabetic(7);
        env.writeIniFile("ORGANIZATION", organizationKey, orgName);
        return me();
    }

    public UnoAppOrganizationPage select_organization(Map<String, String> orgData) {
        log.info("Selecting the organization");
        click(get_organization
                (orgData.get(ORGANIZATION_NAME)));
        return me();
    }

    public UnoAppOrganizationPage select_organization_option() {
        log.info("Selecting the organization");
        click(organizationLabel);
        return me();
    }

    public UnoAppOrganizationPage verify_org_phone_number(String expectedPhoneNumber) {
        log.info("Verifying organization phone number ");
        verify_element_by_value(get_org_detail("phoneNumber"), expectedPhoneNumber);
        return me();
    }

    public UnoAppOrganizationPage verify_org_email(Map<String, String> orgData) {
        log.info("Verifying organization email address ");
        verify_element_by_value(get_org_detail("email"), orgData.get(ORGANIZATION_EMAIL));
        return me();
    }

    public UnoAppOrganizationPage verify_org_address_line(String expectedAdd1, String expectedAdd2) {
        log.info("Verifying location address lines ");
        verify_element_by_value(get_org_detail("address1"), expectedAdd1);
        verify_element_by_value(get_org_detail("address2"), expectedAdd2);
        return me();
    }


    public UnoAppOrganizationPage verify_org_city(String expectedCity) {
        log.info("Verifying location city ");
        verify_element_by_value(get_org_detail("city"), expectedCity);
        return me();
    }

    public UnoAppOrganizationPage verify_org_zip(String expectedZip) {
        log.info("Verifying location zip code ");
        verify_element_by_value(get_org_detail("zipCode"), expectedZip);
        return me();
    }


    public UnoAppOrganizationPage store_migrated_data(String clubAddress, String clubCity, String clubZip, String clubPhone, String memberName, String memberAddress, String memberCity, String memberZip,
                                                      String memberCountry, String memberEmail, String memberHomePhone, String memberCellPhone,
                                                      String memberEmergencyNumber, String memberSinceDate, String subscriptionType, String subscriptionAmount,
                                                      String lastFour, String rountingNumber, String creditCardType, String paymentType) {
        log.info("Storing Migrated Data");
        String member[] = memberName.split("\\s+");
        if (member.length == 3) {
            String firstName = member[0];
            String middleName = member[1];
            String lastName = member[2];
            envMigratedIni.writeIniFile("MEMBER", "firstName", firstName);
            envMigratedIni.writeIniFile("MEMBER", "middleName", middleName);
            envMigratedIni.writeIniFile("MEMBER", "lastName", lastName);
        } else if (member.length == 2) {
            String firstName = member[0];
            String lastName = member[1];
            envMigratedIni.writeIniFile("MEMBER", "firstName", firstName);
            envMigratedIni.writeIniFile("MEMBER", "lastName", lastName);
        } else {
            envMigratedIni.writeIniFile("MEMBER", "firstName", memberName);
            envMigratedIni.writeIniFile("MEMBER", "lastName", MEMBER_LAST_NAME);
        }
        envMigratedIni.writeIniFile("ORGANIZATION", "orgAdd1", clubAddress);
        envMigratedIni.writeIniFile("ORGANIZATION", "orgCity", clubCity);
        envMigratedIni.writeIniFile("ORGANIZATION", "orgZip", clubZip);
        envMigratedIni.writeIniFile("ORGANIZATION", "orgPhoneNumber", clubPhone);
        envMigratedIni.writeIniFile("MEMBER", "memberAddress", memberAddress);
        envMigratedIni.writeIniFile("MEMBER", "city", memberCity);
        envMigratedIni.writeIniFile("MEMBER", "zipCode", memberZip);
        envMigratedIni.writeIniFile("MEMBER", "country", memberCountry);
        envMigratedIni.writeIniFile("MEMBER", "memberEmail", memberEmail);
        envMigratedIni.writeIniFile("MEMBER", "phoneNumber", memberHomePhone);
        envMigratedIni.writeIniFile("MEMBER", "memberCellPhone", memberCellPhone);
        envMigratedIni.writeIniFile("MEMBER", "memberEmergencyNumber", memberEmergencyNumber);
        envMigratedIni.writeIniFile("MEMBER", "memberSinceDate", memberSinceDate);
        envMigratedIni.writeIniFile("SUBSCRIPTION", "payAmount", subscriptionAmount);
        envMigratedIni.writeIniFile("SUBSCRIPTION", "subType", subscriptionType);
        envMigratedIni.writeIniFile("PAYMENTMETHOD", "bankRouting", rountingNumber);
        envMigratedIni.writeIniFile("PAYMENTMETHOD", "lastFour", lastFour);
        envMigratedIni.writeIniFile("PAYMENTMETHOD", "creditCardType", creditCardType);
        envMigratedIni.writeIniFile("PAYMENTMETHOD", "payMethod", paymentType);
        return me();
    }
}
