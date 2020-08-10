package pagetest.datamigrationpage;


import datastore.StoreAdditionalMemberDetails;
import datastore.StoreMemberDetails;
import datastore.StorePaymentMethodDetails;
import config.EnvProperty;
import data.ManagePayorProfileData;
import data.StateCodeList;
import datastore.StoreSubscriptionDetails;
import helper.AppConstants;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.BAD_ADDRESS;

import java.io.IOException;
import java.time.Month;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

//Created By Monika Phoughat
//Date : 01-06-2020

public class MemberAccountPage extends AbcCommonAbstractPage<MemberAccountPage> {

    private By memberAccountTab = By.xpath("//i[@data-abc-id='memberAccountTitleIcon']");
    private By payorProfile = By.xpath("//tr[@data-abc-id='payerProfileTableRow']");
    private By firstName = By.xpath("//input[@id='firstName-PayorProfile']");

    EnvProperty env = EnvProperty.getInstance(AppConstants.DATA_MIGRATION_INI);
    EnvProperty envProperty = EnvProperty.getInstance(OUTPUT_INI);

    //private By middleName = By.xpath("//input[@id='middleInitial-PayorProfile']");
    private By middleName = By.xpath("//input[@id='middleName-PayorProfile']");
    private By lastName = By.xpath("//input[@id='lastName-PayorProfile']");
    private By email = By.xpath("//input[@id='email-PayorProfile']");
    private By address = By.xpath("//input[@id='address-PayorProfile']");
    private By city = By.xpath("//input[@id='city-PayorProfile']");
    private By zip = By.xpath("//input[@id='zip-PayorProfile']");
    private By birthDate = By.xpath("//input[@id='birthDate-PayorProfile']");
    private By drivingLicense = By.xpath("//input[@name='dln']");
    private By ssn = By.xpath("//input[@name='ssn']");
    private By closeButton = By.xpath("//button[@data-abc-id='closeDrawerButton']");

    private By locationBtn = By.xpath("//i[@data-abc-id='buildingIcon']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By memberLink = By.xpath("//i[@data-abc-id='userCheckIcon']");
    private By primaryMember = By.xpath("//h3[contains(text(),'Primary')]");
    private By searchIcon = By.xpath("//i[@data-abc-id='memberSearchSelectInputIconAfter']");
    private By phoneNumber = By.xpath("//span[contains(text(),'Phone Number')]");
    private By search = By.xpath("//input[@id='memberSearchInput']");
    private By loadAllMembersList = By.xpath("//td[@data-abc-id='loadingCell']");
    private String memberNumber = "')]";
    private String clubNumber = "//td[contains(text(),'";
    private By textMessageCheckBox = By.xpath("//input[@id = 'communication-sms-PayorProfile']");
    private By emailCheckBox = By.xpath("//input[@id = 'communication-email-PayorProfile']");
    private By letterCheckBox = By.xpath("//input[@id = 'communication-letter-PayorProfile']");
    private By phoneCallCheckBox = By.xpath("//input[@id = 'communication-phone-PayorProfile']");
    private By paymentTab = By.xpath("//i[@data-abc-id='paymentsTitleIcon']");
    private By paymentMethodList = By.xpath("//strong[contains(text(),'Default')]");
    private By routingNumber = By.xpath("//input[@id='routingNumber']");
    private By accountNumberLastFour = By.xpath("//input[@id='accountNumberLastFour']");
    private By bankAccountType = By.xpath("//input[@id='bankAccountTypeInput']");
    private By expiryDate = By.xpath("//td[@data-abc-id='expirationDate']");
    private By creditCardNumberLastFour = By.xpath("//input[@id='creditCardNumberLastFour']");
    private By expirationYear = By.xpath("//div//input[@id='expirationYearInput']");
    private By creditCardType = By.xpath("//div//input[@id='creditCardTypeInput']");
    private By expirationMonth = By.xpath("//div//input[@id='expirationMonthInput']");
    private By accountHolderName = By.xpath("//*[@data-abc-id='cardLabelText']//following::td[1]");
    private By paymentMethodZipCode = By.xpath("//input[@id='postalCode']");
    private By state = By.xpath("//input[@id='state-PayorProfileInput']");
    private By editButton = By.xpath("//button[@data-abc-id='editPayorProfileButton']");
    private By orgSwitcherClearIcon = By.xpath("//i[@data-abc-id='organizationSwitcherInputIconAfter']");

    private By transactionFilter = By.xpath("//input[@id='payorTransactionsFilterInput']");
    private By feeCheckBox = By.xpath("/span[contains(text(),'Fee')]");

    public MemberAccountPage click_transaction_filter() {
        logger.info("Clicking on Transaction Filter");
        click(transactionFilter);
        return me();
    }

    public MemberAccountPage click_fee_check_box() {
        logger.info("Clicking on Fee Check Box");
        click(feeCheckBox);
        return me();
    }

    public MemberAccountPage click_Member(String agreementNumber) {
        logger.info("Clicking on Migrated Member");
        wait_until(2);
        logger.info("Selecting Member ....");
        String[] agreementOrg = agreementNumber.split("-");
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

    public MemberAccountPage click_member_link() {
        logger.info("Clicking on Member Link");
        verify(elementToBeClickable(memberLink), 3, 1);
        return click(memberLink);
    }

    public MemberAccountPage search_member(String phoneNo) {
        logger.info("Searching for Member");
        click(primaryMember);
        click(searchIcon);
        click(phoneNumber);
        enter(search, phoneNo);
        wait_until(2);
        return me();
    }

    @Step("Click on Location")
    private MemberAccountPage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    public MemberAccountPage verify_name(String name) {
        logger.info("verifying the Full Payor Name");
        String nameUI = (find_element_value(this.firstName) + find_element_value(this.lastName)).replace(" ", "");
        verify_value_matches(nameUI, name);
        return me();
    }

    public MemberAccountPage click_close_button() {
        logger.info("Clicking close button");
        click(closeButton);
        return me();
    }

    @Step("Click on Wallet Tab")
    public MemberAccountPage click_wallet_Tab() {
        logger.info("Click on Wallet Tab");
        verify(elementToBeClickable(paymentTab), 60, 2000);
        wait_until(2);
        return click(paymentTab);
    }

    @Step("Click on Payment Method List")
    public MemberAccountPage click_payment_method_list() {
        logger.info("Click on Payment Method List");
        verify(elementToBeClickable(paymentMethodList), 60, 2000);
        wait_until(2);
        return click(paymentMethodList);
    }

    public MemberAccountPage verify_first_name_green_screen(String expectedFirstName) {
        logger.info("verifying the First Name of Payor");
        verify_value_matches(find_element_value(this.firstName), expectedFirstName);
        return me();
    }

    @Step("Verifying Account Holder Name")
    public MemberAccountPage verify_account_holder_name(String accountHolderName) {
        logger.info("Verifying Account Holder Name");
        verify_value_matches(find_element_text(this.accountHolderName), accountHolderName);
        return me();
    }

    @Step("Verifying Zip code")
    public MemberAccountPage verify_payment_method_zip_code(String expectedZipCode) {
        verify_value_matches(find_element_value(paymentMethodZipCode), expectedZipCode.substring(0, 5));
        return me();
    }

    public MemberAccountPage verify_last_name_green_screen(String expectedLastName) {
        logger.info("verifying the Last Name of Payor");
        verify_value_matches(find_element_value(this.lastName), expectedLastName);
        return me();
    }

    public MemberAccountPage verify_middle_name_green_screen(String expectedMiddleName) {
        logger.info("verifying the Middle Name of Payor");
        verify_value_matches(find_element_value(this.middleName), expectedMiddleName);
        return me();
    }


    public MemberAccountPage verify_email(String email) {
        logger.info("Verifying the Payor Email");
        verify_element_by_value(this.email, email);
        return me();
    }

    public MemberAccountPage verify_address(String address) {
        logger.info("Verifying the Payor Address");
        verify_element_by_value(this.address, address);
        return me();
    }

    public MemberAccountPage verify_city(String city) {
        logger.info("Verifying the Payor City");
        verify_element_by_value(this.city, city.trim());
        return me();
    }

 /*   public MemberAccountPage verify_zip_obc(String zip) {
        logger.info("Verifying the Payor Zip");
        verify_element_by_value(this.zip, zip.trim().substring(0, 5));
        return me();
    }*/

    public MemberAccountPage verify_zip_obc(StoreMemberDetails storeMemberDetails) {
        logger.info("Verifying Zip Code");
        if (storeMemberDetails.getMemberAddress().equalsIgnoreCase(BAD_ADDRESS)) {
            logger.info("Bad Address");
        } else
            verify_value_matches(find_element_value(zip), storeMemberDetails.getMemberZipCode().substring(0, 5));
        return me();
    }

    public MemberAccountPage verify_state(String expectedState) {
        logger.info("Verifying the Payor State");
        verify_element_attribute_value(this.state, "placeholder", expectedState);
        return me();
    }

    @Step("Verifying Zip code")
    public MemberAccountPage verify_zip_obc(String expectedZipCode) {
        logger.info("Verifying Zip code");
        if (env.getConfigPropertyValue(MEMBER_DETAILS, MEMBER_ADDRESS).equalsIgnoreCase(BAD_ADDRESS)) {
            logger.info(BAD_ADDRESS);
        } else
            verify_value_matches(find_element_value(zip).substring(0, 5), expectedZipCode);
        return me();
    }

    @Step("Verifying Zip code from GreenScreen")
    public MemberAccountPage verify_zip_green_screen(String expectedZipCode) {
        logger.info("Verifying Zip code");
        verify_value_matches(find_element_value(zip), expectedZipCode.substring(0, 5));
        return me();
    }

    public MemberAccountPage verify_birth_date_obc(String birthDate) {
        logger.info("Verifying the Payor Birth Date");
        if (birthDate.equals("")) {
            logger.info("Birth Date is not present for Payor");
        } else {
            String birthDateUI = find_element_value(this.birthDate).substring(0, 6) + find_element_value(this.birthDate).substring(8, 10);
            verify_value_matches(birthDateUI, birthDate);
        }
        return me();
    }

    public MemberAccountPage verify_ssn(String ssn) {
        logger.info("Verifying the  Payor SSN");
        String ssnUI = find_element_attribute_value(this.ssn, "placeholder").replaceAll("[^0-9]", "");
        verify_value_matches(ssnUI, ssn);
        return me();
    }

    public MemberAccountPage verify_driving_license(String drivingLicense) {
        logger.info("Verifying the Payor Driving License");
        String drivingLicenseUI = find_element_attribute_value(this.drivingLicense, "placeholder").replaceAll("[^0-9]", "");
        verify_value_matches(drivingLicenseUI, drivingLicense);
        return me();
    }

    public MemberAccountPage verify_driving_license_green_screen(String drivingLicense) {
        logger.info("Verifying the Payor Driving License");
        int drivingLicenseLength=drivingLicense.length();
        drivingLicense=drivingLicense.isEmpty() ? drivingLicense : drivingLicense.substring(drivingLicenseLength-4,drivingLicenseLength);
        String drivingLicenseUI = find_element_attribute_value(this.drivingLicense, "placeholder");
        drivingLicenseUI=drivingLicenseUI.isEmpty() ? drivingLicenseUI : drivingLicenseUI.substring(6,10);
        verify_value_matches(drivingLicenseUI, drivingLicense);
        return me();
    }

    //public MemberAccountPage verify_payor_profile_details_obc(StoreMemberDetails storeMemberDetails, StorePaymentMethodDetails storePaymentMethodDetails, StoreAdditionalMemberDetails storeAdditionalMemberDetails) {

    @Step("Verifying Routing Number")
    public MemberAccountPage verify_routing_number(String routingNumber) {
        logger.info("Verifying Routing Number");
        String actualRoutingNumber = find_element_attribute_value(this.routingNumber, "value");
        verify_value_matches(actualRoutingNumber, routingNumber);
        return me();
    }

    @Step("Verifying Account Holder Last Four Digits")
    public MemberAccountPage verify_account_number_last_four(String accountNumberLastFour) {
        logger.info("Verifying Account Holder Last Four Digits");
        String actualLastFour = find_element_attribute_value(this.accountNumberLastFour, "value");
        verify_value_matches(actualLastFour, accountNumberLastFour);
        return me();
    }

    @Step("Verifying Bank Account Type")
    public MemberAccountPage verify_bank_account_type(String bankAccountType) {
        logger.info("Verifying Bank Account Type");
        String actualBankAccountType = find_element_attribute_value(this.bankAccountType, "placeholder");
        verify_value_matches(actualBankAccountType, bankAccountType);
        return me();
    }

    @Step("Verifying Credit Type with last four digit")
    public MemberAccountPage verify_credit_type_with_last_four_digit(String creditType) {
        logger.info("Verifying Credit Type with last four digit");
        is_text_present_on_page(creditType.toLowerCase());
        return me();
    }

    @Step("Verifying Expiration Details")
    public MemberAccountPage verify_expiration_details(String expiration) {
        logger.info("Verifying Expiration Details");
        wait_until(2);
        // String expiryDate = find_element_text(this.expiryDate).substring(0, 2) + "/" + find_element_text(this.expiryDate).substring(5, 7);
        String expiryDate = find_element_text(this.expiryDate);
        verify_value_matches(expiryDate, expiration);
        return me();
    }

    @Step("Verifying Expiration Year")
    public MemberAccountPage verify_expiration_year(String expirationYear) {
        logger.info("Verifying Expiration Year");
        String actualExpirationYear = find_element_attribute_value(this.expirationYear, "placeholder");
        verify_value_matches(actualExpirationYear, expirationYear);
        return me();
    }

    @Step("Verifying Credit Card Type")
    public MemberAccountPage verify_credit_card_type(String creditCardType) {
        logger.info("Verifying Credit Card Type");
        String actualCreditCardType = find_element_attribute_value(this.creditCardType, "placeholder");
        verify_value_matches(actualCreditCardType, creditCardType);
        return me();
    }

    @Step("Verifying Expiration Month")
    public MemberAccountPage verify_expiration_month(String expirationMonth) {
        logger.info("Verifying Expiration Month");
        String actualExpirationMonth = find_element_attribute_value(this.expirationMonth, "placeholder").toLowerCase();
        verify_value_matches(actualExpirationMonth, expirationMonth);
        return me();
    }

    @Step("Verifying Credit Card Number Last Four Digits")
    public MemberAccountPage verify_credit_card_last_four(String creditCardNumberLastFour) {
        logger.info("Verifying Credit Card Number Last Four Digits");
        verify_element_by_value(this.creditCardNumberLastFour, creditCardNumberLastFour);
        return me();
    }


    public MemberAccountPage verify_payment_details_from_green_screen(ManagePayorProfileData managePayorProfileData) {
        logger.info("Verifying Wallet Details from OBC UI to Uno APP");
        click_wallet_Tab();
        String creditCardType = managePayorProfileData.getCreditCardType();
        switch (creditCardType) {
            case "V":
                creditCardType = "Visa";
                break;
            case "A":
                creditCardType = "American Express";
                break;
            case "M":
                creditCardType = "MasterCard";
        }

        if (managePayorProfileData.getAccountType().equalsIgnoreCase("S") || managePayorProfileData.getAccountType().equalsIgnoreCase("C")) {
            String bankAccountType = managePayorProfileData.getAccountType();
            switch (bankAccountType) {
                case "C":
                    bankAccountType = "Checking";
                    break;
                case "S":
                    bankAccountType = "Savings";
                    break;
            }
            String finalBankAccountType=bankAccountType;
            click_payment_method_list();
            verify_all(
                    () -> verify_routing_number(managePayorProfileData.getRoutingNumber()),
                    () -> verify_account_number_last_four(managePayorProfileData.getAccountNumber().replaceAll("[*]", "")),
                    () -> verify_bank_account_type(finalBankAccountType)

            );
            click_close_button();
        } else {
            String expirationDateWithYear = managePayorProfileData.getExpiryDate();
            String expectedFormattedExpDate = expirationDateWithYear.substring(0, 2) + "/" + "20" + expirationDateWithYear.substring(2, 4);
            String expYear = "20" + expirationDateWithYear.substring(2, 4);
            int expMonth = Integer.parseInt(expirationDateWithYear.substring(0, 2));
            String finalCreditCardType = creditCardType;
            verify_all(
                    () -> verify_credit_type_with_last_four_digit(finalCreditCardType + "(" + managePayorProfileData.getCreditCardNumber().replaceAll("[*]", "") + ")"),
                    () -> verify_expiration_details(expectedFormattedExpDate),
                    () -> verify_account_holder_name(managePayorProfileData.getFirstName() + " " + managePayorProfileData.getLastName())
            );

            click_payment_method_list();

            verify_all(
                    () -> verify_account_holder_name(managePayorProfileData.getFirstName() + " " + managePayorProfileData.getLastName()),
                    () -> verify_credit_card_last_four(managePayorProfileData.getCreditCardNumber().replaceAll("[*]", "")),
                    () -> verify_expiration_year(expYear),
                    () -> verify_expiration_month(Month.of(expMonth).name().toLowerCase()),
                    () -> verify_payment_method_zip_code(managePayorProfileData.getZip())
            );

            click_close_button();
        }
        return me();
    }


    public MemberAccountPage verify_communication_details(ManagePayorProfileData managePayorProfileData) {
        String communicationText = managePayorProfileData.getCommunicationText();
        String communicationPhone = managePayorProfileData.getCommunicationPhone();
        String communicationLetter = managePayorProfileData.getCommunicationLetter();
        String communicationEmail = managePayorProfileData.getCommunicationEmail();
        scroll_to_element(textMessageCheckBox);
        SoftAssert softAssert = new SoftAssert();

        logger.info("Verifying Text Message Check Box");
        switch (communicationText) {
            case "Y":
                verify_element_selected(textMessageCheckBox);
                break;
            case "":
                Assert.assertEquals(find_element_attribute_value(textMessageCheckBox, "checked"), null,"Verification Of Text Message Check Box Failed");
                break;
            case "N":
                Assert.assertEquals(find_element_attribute_value(textMessageCheckBox, "checked"), null,"Verification Of Text Message Check Box Failed");
        }

        logger.info("Verifying Phone Calls Check Box");
        switch (communicationPhone) {
            case "Y":
                verify_element_selected(phoneCallCheckBox);
                break;
            case "N":
                Assert.assertEquals(find_element_attribute_value(phoneCallCheckBox, "checked"), null,"Verification Of Phone Calls Check Box Failed");
                break;
            case "":
                Assert.assertEquals(find_element_attribute_value(phoneCallCheckBox, "checked"), null,"Verification Of Phone Calls Check Box Failed");
        }

        logger.info("Verifying Letters Check Box");
        switch (communicationLetter) {
            case "Y":
                verify_element_selected(letterCheckBox);
                break;
            case "N":
                Assert.assertEquals(find_element_attribute_value(letterCheckBox, "checked"), null,"Verification Of Letter Check Box Failed");
                break;
            case "":
                Assert.assertEquals(find_element_attribute_value(letterCheckBox, "checked"), null,"Verification Of Letter Check Box Failed");

        }

        logger.info("Verifying Email Check Box");
        switch (communicationEmail) {
            case "Y":
                verify_element_selected(emailCheckBox);
                break;
            case "N":
                Assert.assertEquals(find_element_attribute_value(emailCheckBox, "checked"), null,"Verification Of Email Check Box Failed");
                break;
            case "":
                Assert.assertEquals(find_element_attribute_value(emailCheckBox, "checked"), null,"Verification Of Email Check Box Failed");
        }
        return me();
    }

    public MemberAccountPage verify_payor_profile_details_obc(StoreMemberDetails storeMemberDetails, StorePaymentMethodDetails storePaymentMethodDetails, StoreAdditionalMemberDetails storeAdditionalMemberDetails, StoreSubscriptionDetails storeSubscriptionDetails) {

        logger.info("Verifying Payor Profile Details");
        click(memberAccountTab);
        logger.info("Clicking on Payor Profile");
        click(payorProfile);

        try {
            if (storeSubscriptionDetails.getSubscriptionType().equalsIgnoreCase("Paid Up Front") || storeSubscriptionDetails.getSubscriptionType().equalsIgnoreCase("InstallmentCoupon")) {
                verify_all(
                        () -> verify_email(storeMemberDetails.getMemberEmail()),
                        () -> verify_driving_license(storeAdditionalMemberDetails.getDrivingLicense()),
                        () -> verify_ssn(storeAdditionalMemberDetails.getSsn()),
                        () -> verify_birth_date_obc(storeAdditionalMemberDetails.getDateOfBirth())
                );
            } else {
                if (storeMemberDetails.getMemberAddress().equalsIgnoreCase(BAD_ADDRESS)) {
                    logger.info(BAD_ADDRESS);

                    verify_all(
                            () -> verify_name(storePaymentMethodDetails.getPayorName().replace(" ", "").trim()),
                            () -> verify_email(storeMemberDetails.getMemberEmail()),
                            () -> verify_driving_license(storeAdditionalMemberDetails.getDrivingLicense()),
                            () -> verify_ssn(storeAdditionalMemberDetails.getSsn()),
                            () -> verify_birth_date_obc(storeAdditionalMemberDetails.getDateOfBirth())
                    );

                }
                else if(storePaymentMethodDetails.getPaymentType().equalsIgnoreCase("No Billing Information")){
                    logger.info("Biling information is not available");
                }

                else
                    verify_all(

                            () -> verify_name(storePaymentMethodDetails.getPayorName().replace(" ", "").trim()),
                            () -> verify_email(storeMemberDetails.getMemberEmail()),
                            () -> verify_address(storeMemberDetails.getMemberAddress()),
                            () -> verify_city(storeMemberDetails.getMemberCity()),
                            () -> verify_zip_obc(storeMemberDetails),
                            () -> verify_driving_license(storeAdditionalMemberDetails.getDrivingLicense()),
                            () -> verify_ssn(storeAdditionalMemberDetails.getSsn()),
                            () -> verify_birth_date_obc(storeAdditionalMemberDetails.getDateOfBirth())
                    );
            }
        } finally {
            click(closeButton);
        }
        return me();
    }

    public MemberAccountPage verify_payor_profile_from_green_screen(String clubNumber) {
        ManagePayorProfileData managePayorProfile = new ManagePayorProfileData(envProperty.get(clubNumber));
        String phone = managePayorProfile.getPrimaryPhoneNumber().replaceAll(" ", "");
        String finalClubNumber = clubNumber.substring(0, 4);
        StateCodeList stateCodeList = new StateCodeList();
        logger.info("Verifying Payor profile details");
        click(locationBtn);
        click_location(finalClubNumber);
        wait_until(2);
        click_member_link();
        search_member(phone);
        click_Member(managePayorProfile.getAgreementNumber());
        click(memberAccountTab);
        click(payorProfile);

        try {
            verify_all(
                    () -> verify_first_name_green_screen(managePayorProfile.getFirstName()),
                    () -> verify_last_name_green_screen(managePayorProfile.getLastName()),
                    () -> verify_middle_name_green_screen(managePayorProfile.getMiddleName()),
                    () -> verify_email(managePayorProfile.getEmail()),
                    () -> verify_address(managePayorProfile.getAddress1()),
                    () -> verify_city(managePayorProfile.getCity()),
                    () -> verify_zip_green_screen(managePayorProfile.getZip()),
                    () -> verify_driving_license_green_screen(managePayorProfile.getDrivingLicense()),
                    () -> verify_ssn(managePayorProfile.getSsn().replaceAll("[*]", "")),
                    () -> verify_birth_date_obc(managePayorProfile.getDateOfBirth()),
                    () -> verify_state(stateCodeList.getState(managePayorProfile.getState()))

            );
            click(editButton);
            verify_communication_details(managePayorProfile);
        } catch (AssertionError e) {
            Assert.assertTrue(false, e.getMessage());
        } finally {
            click_close_button();
            verify_payment_details_from_green_screen(managePayorProfile);
            clearing_organisation();
        }

        return me();
    }

    @Step("Clearing Last organisation tested from organisation search box")
    public MemberAccountPage clearing_organisation() {
        click_org_switcher_clear_icon();
        return me();
    }
    public MemberAccountPage click_org_switcher_clear_icon(){
        click(orgSwitcherClearIcon);
        return me();
    }
}
