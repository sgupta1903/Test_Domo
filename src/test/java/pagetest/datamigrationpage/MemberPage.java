package pagetest.datamigrationpage;

import config.EnvProperty;
import datastore.StoreAdditionalMemberDetails;
import datastore.StoreClubDetails;
import datastore.StoreMemberDetails;
import datastore.StoreSecondaryMemberDetails;
import helper.DataBaseHandler;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import pagetest.businessapppage.AbcCommonAbstractPage;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static util.UtilityGeneric.convertObjectIntoString;
import static util.UtilityGeneric.getFormatedDate;

//Created By Monika Phoughat
//Date : 16-09-2019

public class MemberPage extends AbcCommonAbstractPage<MemberPage> {

    EnvProperty env=EnvProperty.getInstance("datamigration.ini");
    private By memberLink = By.xpath("//i[@data-abc-id='userCheckIcon']");
    private By profileTab = By.xpath("//i[@data-abc-id='profileTitleIcon']");
    private String clubNumber = "//td[contains(text(),'";
    private String memberNumber = "')]";
    private By loadAllMembersList = By.xpath("//td[@data-abc-id='loadingCell']");
    private By firstName = By.xpath("//input[@id='firstName-Member']");
    //private By middleName = By.xpath("//input[@id='middleInitial-Member']");
    private By middleName = By.xpath("//input[@id='middleName-Member']");
    private By lastName = By.xpath("//input[@id='lastName-Member']");
    private By search = By.xpath("//input[@id='memberSearchInput']");
    private By email = By.xpath("//input[@id='email-Member']");
    private By address = By.xpath("//input[@id='address-Member']");
    private By city = By.xpath("//input[@id='city-Member']");
    private By zip = By.xpath("//input[@id='zip-Member']");
    private By birthDate = By.xpath("//input[@id='birthDate-Member']");
    private By gender = By.xpath("//div[@id='genderIdentity']");
    private By allMembers = By.xpath("//input[@id='membersFilterInput']");
    private By primaryMember = By.xpath("//h3[contains(text(),'Primary')]");
    private By searchIcon = By.xpath("//i[@data-abc-id='memberSearchSelectInputIconAfter']");
    private By phoneNumber = By.xpath("//span[contains(text(),'Phone Number')]");

    private By memberCount = By.xpath("//ul[contains(@class,'ui-infinite-list list')]/li");
    private By backIcon = By.xpath("//i[contains(@class,'icon-arrow-left-thin ui-icon')]");
    protected String member = "//ul[contains(@class,'ui-infinite-list list')]/li[%s]";
    private By editBtn = By.xpath("//i[@data-abc-id='editButtonIcon']");
    private By memberSince = By.xpath("//input[@id='memberSince-MemberInput']");
    private By memberCountry = By.xpath("//div//input[@id='country-MemberInput']");
    private By membershipType = By.xpath("//div//input[@id='membershipTypeId-MemberInput']");
    private By drivingLicense = By.xpath("//input[@id='dln-Member']");
    private By ssn = By.xpath("//input[@id='ssn-Member']");
    private By employer = By.xpath("//input[@id='employmentData[employer]-Member']");
    private By occupation = By.xpath("//input[@id='employmentData[occupation]-Member']");
    private By badAddress = By.xpath("//input[@id='invalidAddress-Member']");
    private By selectMember = By.xpath("//div[@id='memberSelector']");
    private By memberNoteTab=By.xpath("//i[@data-abc-id='notesTitleIcon']");

    private String memberNoteRowXpath = "//tr[@data-abc-id='%s']/td//div[text()='%s']";
    private String dataAbcId = "notesListRow";

    public By get_member_note_xpath(String dataAbcId, String note) {
        return By.xpath(String.format(memberNoteRowXpath, dataAbcId, note));
    }

    @Step("Verifying Deduction/Reimbursement item")
    public MemberPage verify_member_note_row(String note) {
        verify(visibilityOfAllElementsLocatedBy(get_member_note_xpath(dataAbcId, note)));
        return me();
    }

    public MemberPage verify_member_since_date(String memberSinceDate) {
        logger.info("Verifying Member Since Date");
        if (!(find_element_value(memberSince).equals(memberSinceDate))) {
            String[] memberSinceDateUI = memberSinceDate.split("/");
            String dd = null;
            int day = Integer.parseInt(memberSinceDateUI[1]);
            if ((day < 10) && (memberSinceDateUI[1].length() < 2)) {
                dd = "0" + memberSinceDateUI[1];
                memberSinceDate = memberSinceDateUI[0] + "/" + dd + "/" + memberSinceDateUI[2];
            }

        }
        verify_element_by_value(memberSince, memberSinceDate);
        return me();
    }

    public MemberPage verify_member_country(String memberCntry) {
        logger.info("Verifying Member Country ");
        verify_value_matches(find_element_attribute_value(this.memberCountry, "placeholder").toLowerCase(), memberCntry);
        return me();
    }

    public MemberPage click_Member(StoreClubDetails storeClubDetails) {
        logger.info("Clicking on Migrated Member");
        wait_until(2);
        logger.info("Selecting Member ....");
        String[] agreementOrg = storeClubDetails.getAgreementNumber().split("-");
        if (wait_until(elementToBeClickable(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber))) != null) {
            click(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber));
        } else {
            click(loadAllMembersList);
            wait_until(2);
            if (wait_until(elementToBeClickable(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber))) != null) {
                click(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber));
            } else {
                click(loadAllMembersList);
                wait_until(2);
                if (wait_until(elementToBeClickable(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber))) != null) {
                    click(By.xpath(clubNumber + StringUtils.leftPad(agreementOrg[0], 6, "0") + "-B" + StringUtils.leftPad(agreementOrg[1], 5, "0") + memberNumber));
                }
            }
        }
        return me();
    }


    public MemberPage click_member_link() {
        logger.info("Clicking on Member Link");
        verify(elementToBeClickable(memberLink), 3, 1);
        return click(memberLink);
    }

    public MemberPage click_member_note_tab() {
        logger.info("Clicking on Member Note Tab");
        verify(elementToBeClickable(memberNoteTab), 3, 1);
        click(memberNoteTab);
        return me();
    }

    public int get_member_count() {
        int memberCount = find_element_count(this.memberCount);
        return memberCount;
    }

    public MemberPage search_member(String phoneNo) {
        logger.info("Searching for Member");
        //click(allMembers);
        //wait_until(2);
        //enter_by_key();
        click(primaryMember);
        click(searchIcon);
        click(phoneNumber);
        enter(search, phoneNo);
        wait_until(2);
        return me();
    }


    public By get_member(String no) {
        return By.xpath(String.format(member, no));
    }

    public MemberPage select_member(String email) {
        logger.info("Clicking on Searched Member");
        Boolean memberFind = true;
        int memberNo = get_member_count() - 1;
        for (int i = 1; i <= memberNo; i++) {
            if (is_element_exist(backIcon)) {
                click(backIcon);
            }
            verify(elementToBeClickable(get_member(String.valueOf(i))), 60, 2000);
            wait_until(3);
            click(get_member(String.valueOf(i)));
            wait_until(1);
            click_profile_tab();
            wait_until(1);
            if (email.equals(find_element_value(this.email))) {
                if (!memberFind) {
                    memberFind = true;
                }
                break;
            } else {
                memberFind = false;
            }
        }
        if (!memberFind) {
            Assert.assertTrue(false);

        }
        return me();
    }

    public MemberPage click_profile_tab() {
        logger.info("Clicking on Member's Profile Tab");
        verify(elementToBeClickable(profileTab), 60, 2000);
        return click(profileTab);
    }

    public MemberPage verify_first_name(String firstName) {
        logger.info("verifying the First Name");
        verify_element_by_value(this.firstName, firstName);
        return me();
    }

    public MemberPage verify_name(String name) {
        logger.info("verifying the Full Member Name");
        String nameUI = (find_element_value(this.firstName) + find_element_value(this.middleName) + find_element_value(this.lastName)).replace(" ", "");
        verify_value_matches(nameUI, name);
        return me();
    }

    public MemberPage verify_last_name(String lastName) {
        logger.info("verifying the Last Name");
        verify_element_by_value(this.lastName, lastName);
        return me();
    }

    public MemberPage verify_email(String email) {
        logger.info("Verifying the Member Email");
        verify_element_by_value(this.email, email);
        return me();
    }

    public MemberPage verify_address(String address) {
        logger.info("Verifying the Member Address");
        verify_element_by_value(this.address, address);
        return me();
    }

    public MemberPage verify_city(String city) {
        logger.info("Verifying the Member City");
        verify_element_by_value(this.city, city.trim());
        return me();
    }

    public MemberPage verify_zip(String zip) {
        logger.info("Verifying the Member Zip");
        String zipCode = find_element_value(this.zip);
        zipCode = zipCode.replace("-", "");
        Assert.assertEquals(zipCode, zip);
        return me();
    }

    public MemberPage verify_zip_obc(String zip) {
        logger.info("Verifying the Member Zip");
        verify_element_by_value(this.zip, zip.trim());
        return me();
    }

    public MemberPage verify_birth_date(String birthDate) {
        logger.info("Verifying the Member BirthDate");
        birthDate = getFormatedDate(birthDate);
        birthDate = birthDate.replace("-", "/");
        verify_element_by_value(this.birthDate, birthDate);
        return me();
    }

    public MemberPage verify_gender(String gender) {
        logger.info("Verifying the Member Gender");
        verify_element_by_text(this.gender, gender);
        return me();
    }


    public MemberPage verify_membership_type(String membershipType) {
        logger.info("Verifying the Membership Type");
        String membershipTypeUI = find_element_attribute_value(this.membershipType,"placeholder");
        verify_value_matches(membershipTypeUI, membershipType);
        return me();
    }

    public MemberPage verify_driving_license(String drivingLicense) {
        logger.info("Verifying the Driving License");
        String drivingLicenseUI = find_element_attribute_value(this.drivingLicense, "placeholder").replaceAll("[^0-9]", "");
        verify_value_matches(drivingLicenseUI, drivingLicense);
        return me();
    }

    public MemberPage verify_ssn(String ssn) {
        logger.info("Verifying the  SSN");
        String ssnUI = find_element_attribute_value(this.ssn, "placeholder").replaceAll("[^0-9]", "");
        verify_value_matches(ssnUI, ssn);
        return me();
    }

    public MemberPage verify_birth_date_obc(String birthDate) {
        logger.info("Verifying the Birth Date");
        if (birthDate.equals("")) {
            logger.info("Birth Date is not present for Member");
        } else {
            String birthDateUI = find_element_value(this.birthDate).substring(0, 6) + find_element_value(this.birthDate).substring(8, 10);
            verify_value_matches(birthDateUI, birthDate);
        }
        return me();
    }

    public MemberPage verify_employer(String employer) {
        logger.info("Verifying the Employer");
        String employerUI = find_element_value(this.employer);
        verify_value_matches(employerUI, employer);
        return me();
    }

    public MemberPage verify_occupation(String occupation) {
        logger.info("Verifying the Occupation");
        String occupationUI = find_element_value(this.occupation);
        verify_value_matches(occupationUI, occupation);
        return me();
    }

    public MemberPage verify_secondary_member_name(String memberName) {
        logger.info("verifying the Full Secondary Member Name");
        String nameUI = (find_element_value(this.firstName) + find_element_value(this.middleName) + find_element_value(this.lastName)).replace(" ", "");
        verify_value_matches(nameUI, memberName);
        return me();
    }

    public MemberPage verify_member_profile(Map memberInfo) {
        logger.info("Verifying the Member Profile");
        click_member_link();

        verify_all(
                () -> search_member(convertObjectIntoString(memberInfo.get("phoneNumber"))),
                () -> select_member(convertObjectIntoString(memberInfo.get("email"))),
                () -> verify_first_name(convertObjectIntoString(memberInfo.get("firstName"))),
                () -> verify_last_name(convertObjectIntoString(memberInfo.get("lastName"))),
                () -> verify_email(convertObjectIntoString(memberInfo.get("email"))),
                () -> verify_address(convertObjectIntoString(memberInfo.get("address"))),
                () -> verify_city(convertObjectIntoString(memberInfo.get("city"))),
                () -> verify_zip(convertObjectIntoString(memberInfo.get("zipCode"))),
                () -> verify_birth_date(convertObjectIntoString(memberInfo.get("birthDate"))),
                () -> verify_gender(convertObjectIntoString(memberInfo.get("genderIdentity")))
        );
        return me();
    }

    public MemberPage verify_member_details_obc(StoreMemberDetails storeMemberDetails,StoreClubDetails storeClubDetails) {
        logger.info("Verifying the Member Details");
        click_member_link();
        search_member(storeMemberDetails.getMemberHomePhone());
        click_Member(storeClubDetails);
        click_profile_tab();
        click(editBtn);
        if (is_element_selected(badAddress)) {
            storeMemberDetails.setMemberAddress("Bad Address");
        } else {
            verify_all(
                    () -> verify_address(storeMemberDetails.getMemberAddress()),
                    () -> verify_city(storeMemberDetails.getMemberCity()),
                    () -> verify_zip_obc(storeMemberDetails.getMemberZipCode()),
                    () -> verify_member_country(storeMemberDetails.getMemberCountry().toLowerCase())
            );

           // env.writeIniFile(MEMBER_DETAILS, MEMBER_ADDRESS, BAD_ADDRESS);
        }
        verify_all(
                () -> verify_name(storeMemberDetails.getMemberName()),
                () -> verify_email(storeMemberDetails.getMemberEmail()),
                () -> verify_member_since_date(storeMemberDetails.getMemberSinceDate())
        );
        return me();
    }

    public MemberPage verify_additional_information_obc(StoreMemberDetails storeMemberDetails,StoreAdditionalMemberDetails storeAdditionalMemberDetails) {

        verify_all(
                () -> verify_membership_type(storeMemberDetails.getMembershipType()),
                () -> verify_driving_license(storeAdditionalMemberDetails.getDrivingLicense()),
                () -> verify_ssn(storeAdditionalMemberDetails.getSsn()),
                () -> verify_birth_date_obc(storeAdditionalMemberDetails.getDateOfBirth()),
                () -> verify_employer(storeAdditionalMemberDetails.getEmployer()),
                () -> verify_occupation(storeAdditionalMemberDetails.getOccupation())
        );
        return me();
    }

    public MemberPage verify_secondary_member_obc(StoreSecondaryMemberDetails storeSecondaryMemberDetails) {
        String secondaryMemberPresence = storeSecondaryMemberDetails.getSecondoryMemberName();
        if (secondaryMemberPresence.equals("N")) {
            logger.info("Secondary member Not Present");
        } else {
            logger.info("Verifying Secondary Member Name");
            click(selectMember);
            is_text_present_on_page(storeSecondaryMemberDetails.getSecondoryMemberName());
        }
        return me();
    }

    public MemberPage verify_member_notes_obc() {
        logger.info("Verifying the Member Notes");
        click_member_note_tab();
        Map<String, String>memberNoteMap=env.get("MEMBER_NOTE");
        for(Map.Entry<String, String> entry : memberNoteMap.entrySet()){
            verify_member_note_row(entry.getValue());
        }
        return me();
    }

    public MemberPage verify_member_profile(DataBaseHandler dataBaseHandler, List memberId) {
        for (int i = 0; i < memberId.size(); i++) {
            Map<String, String> memberInfo = (Map<String, String>) memberId.get(i);
            verify_member_profile(memberInfo);
        }
        return me();
    }
}

