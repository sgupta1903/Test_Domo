package pagetest.obcUIPage;

import config.EnvProperty;
import datastore.*;
import helper.AppConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helper.AppConstants.*;
import static util.UtilityGeneric.convertObjectIntoJsonString;

public class AccountSummaryPage extends AbcCommonAbstractPage<AccountSummaryPage> {

    public StoreClubDetails storeClubDetails = new StoreClubDetails();
    public StoreMemberDetails storeMemberDetails = new StoreMemberDetails();
    public StoreAgreementDetails storeAgreementDetails = new StoreAgreementDetails();
    public StoreLateFeeDetails storeLateFeeDetails = new StoreLateFeeDetails();
    public StoreServiceFeeDetails storeServiceFeeDetails = new StoreServiceFeeDetails();
    public StoreMemberStatusDetails storeMemberStatusDetails = new StoreMemberStatusDetails();
    public StoreSubscriptionDetails storeSubscriptionDetails = new StoreSubscriptionDetails();
    public StorePaymentMethodDetails storePaymentMethodDetails = new StorePaymentMethodDetails();
    public StoreSecondaryMemberDetails storeSecondaryMemberDetails = new StoreSecondaryMemberDetails();
    public StorePcsDetails storePcsDetails = new StorePcsDetails();
    public StoreFreezeStatus storeFreezeStatus = new StoreFreezeStatus();
    public StoreMemberNoteDetails storeMemberNoteDetails = new StoreMemberNoteDetails();

    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    EnvProperty env = EnvProperty.getInstance(AppConstants.DATA_MIGRATION_INI);

    private By accountSummaryLink = By.xpath("//a[@href='https://client2.abcfinancial.com/summary/account_summary.asp']");
    //club details xpaths
    private By clubName = By.xpath("//td[contains(text(),'Club Name:')]/following-sibling::td");
    private By clubDetailsLink = By.xpath("//a[@id='A1']");
    private By clubDetailsUI = By.xpath("//div[@id='popDivHTML']");
    private By clubCloseBtn = By.xpath("//div[@id='popDivBot']//a[@id='closeLink']");
    //member details xpaths
    private By memberName = By.xpath("//td[@style='width:250px']");
    private By memberStatus = By.xpath("//td[contains(text(),'Primary Status:')]/following-sibling::td");
    private By memberAddress = By.xpath("//td[contains(text(),'Address:')]/following-sibling::td");
    private By memberAddress2 = By.xpath("//td[contains(text(),'Address:')]//following::tr[1]");
    private By memberCountry = By.xpath("//td[contains(text(),'Country:')]/following-sibling::td");
    private By memberEmail = By.xpath("//td[contains(text(),'Email:')]/following-sibling::td");
    private By memberHomePhone = By.xpath("//td[contains(text(),'Home Phone:')]/following-sibling::td");
    private By memberWorkPhone = By.xpath("//td[contains(text(),'Work Phone:')]/following-sibling::td");
    private By memberCellPhone = By.xpath("//td[contains(text(),'Cell Phone:')]/following-sibling::td");
    private By memberEmerPhone = By.xpath("//td[contains(text(),'Emer Phone:')]/following-sibling::td");
    private By memberSinceDate = By.xpath("//td[contains(text(),'Member Since:')]/following-sibling::td");
    private By membershipType = By.xpath("//td[contains(text(),'Membership Type:')]/following-sibling::td");
    private By badAddress = By.xpath("//td[contains(text(),'Bad Address:')]");
    private By noInvoices = By.xpath("//td[contains(text(),'No invoices to display.')]");
    private By secondaryMemberLink = By.xpath("//a[@id='linkSecMbrs']");
    private By secondaryMemberNameOBC = By.xpath("//div[@id='popDivHTML']");
    private By noSecondaryMember = By.xpath("//td[contains(text(),'Secondary Members:')]/following-sibling::td");
    //agreement details xpaths
    private By contractBeganDate = By.xpath("//td[contains(text(),'Contract Began:')]/following-sibling::td");
    private By nextDueDate = By.xpath("//td[contains(text(),'Next Scheduled Payment Due:')]/following-sibling::td[2]");
    private By pastDueAmount = By.xpath("//td[@class='summBoth']");
    private By dueAmount = By.xpath("//td[contains(text(),'Next Scheduled Payment Due:')]/following-sibling::td[1]");
    private By clubNo = By.xpath("//td[contains(text(),'Agreement #:')]/following-sibling::td");
    //subscription details xpaths
    private By invoiceLink = By.xpath("//a[contains(text(),'Invoices')]");
    private By subItem = By.xpath("//select[@id='f_profit']/following::td[12]");
    private By subscriptionType = By.xpath("//td[contains(text(),'Agreement Type:')]/following-sibling::td");
    private By subscriptionExpirationDate = By.xpath("//td[contains(text(),'Renewal Date:')]/preceding::td[1]");
    private By pcsStatus = By.xpath("//td[contains(text(),'Primary Status:')]/following::td[3]");
    private By freezeStatus = By.xpath("//td[contains(text(),'Primary Status:')]/following::td[3]");
    //payment method xpaths
    private By payorName = By.xpath("//td[contains(text(),'Name: ')]");
    private By paymentMethodDetail1 = By.xpath("//td[contains(text(),'Name: ')]/following-sibling::td");
    private By paymentMethodDetail2 = By.xpath("//td[contains(text(),'Due:')]//preceding::tr[6]/td[2]");
    private By paymentMethodType = By.xpath("//td[contains(text(),'Over')]//preceding::tr[5]//td[1]");
    private By noBillingInfo = By.xpath("//td[contains(text(),'Your current auto-payment information needs to be')]");
    private By couponType = By.xpath("//td[contains(text(),'Coupon')]");
    private By logOut = By.xpath("//b[contains(text(),'Logout')]");

    private By cancelFreezePolicy = By.xpath("//a[contains(text(),'Cancel/Freeze')]");
    private By lateFeeLink = By.xpath("//a[@id='lateFeeLink']");
    private By serviceFeeLink = By.xpath("//a[@id='serviceFeeLink']");
    private By wavesRadioButton = By.xpath("//input[@type='radio']");
    private By closeButton = By.xpath("//input[@id='f_action']//following::input[2]");
    private By totalLateFeeAmount = By.xpath("//td[contains(text(),'Late Fee:')]//following-sibling::td[1]");
    private By totalServiceFeeAmount = By.xpath("//td[contains(text(),'Service Fee:')]/following-sibling::td[1]");
    private By singleFeeAmount = By.xpath("//input[@type='radio']//following::td[4]");
    private By memberNoteTab = By.xpath("//i[@data-abc-id='notesTitleIcon']");
    private By memberNotes = By.xpath("//tr[]//td[]");
    private By accountNote = By.xpath("//a[@href='https://client2.abcfinancial.com/notes/account_notes.asp']");
    List<By> rows = Collections.singletonList(By.xpath("//tr[@class='dataRow0']"));


    @Step("Store club details")
    public StoreClubDetails store_club_details(Map getData) {

        logger.info("Getting the  Club Details from OBC App");
        click(clubDetailsLink);

        String clubDetails[] = find_element_text(clubDetailsUI).split("\\r?\\n");

        storeClubDetails.setAddress(clubDetails[1]);
        storeClubDetails.setCity(clubDetails[2]);
        storeClubDetails.setZipCode(clubDetails[3].substring(3, 8));
        storeClubDetails.setPhone(clubDetails[4]);
        storeClubDetails.setClubNumber(find_element_text(clubNo).substring(0, 4));
        storeClubDetails.setClubName(find_element_text(clubName).replaceAll("Details", "").trim());
        storeClubDetails.setAgreementNumber(find_element_text(clubNo).replace(" ", ""));

        logger.info(BLUE_UNDERLINED + "*******Club details are listed below************" + ANSI_RESET);
        String jsonString = convertObjectIntoJsonString(storeClubDetails);
        logger.info(jsonString);
        click(clubCloseBtn);
        return storeClubDetails;
    }

    @Step("Store member name")
    public By get_member_note_xpath(int i) {
        return By.xpath("//tr[" + i + "]//td[7]");
    }

    public AccountSummaryPage store_member_name(Map getData) {
        logger.info("Getting the  Member Name  from OBC Application");
        env.writeIniFile(MEMBER_DETAILS, MEMBER_NAME, find_element_text(memberName).replace(" ", ""));
        getData.put(MEMBER_NAME, find_element_text(memberName));
        return me();
    }

    @Step("Store secondary member name")
    public StoreSecondaryMemberDetails store_secondary_member_name() {
        logger.info("Getting the Secondary Member Name  from OBC Application");
        String secondaryMemberPresence = find_element_text(noSecondaryMember);
        if (secondaryMemberPresence.equals("* No secondary members at this time.")) {
            logger.info("No Secondary Member Present on this Member ");
            storeSecondaryMemberDetails.setSecondoryMemberName("N");
        } else {
            click(secondaryMemberLink);
            String secondaryMemberName[] = find_element_text(secondaryMemberNameOBC).split("\\r?\\n");
            storeSecondaryMemberDetails.setSecondoryMemberName(secondaryMemberName[1]);
        }
        logger.info(BLUE_UNDERLINED + "******************Details of Secondary Member Details*************************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeSecondaryMemberDetails));
        return storeSecondaryMemberDetails;
    }

    @Step("Store pcs details")
    public StorePcsDetails store_pcs() {
        logger.info("Getting the PCS of Subscription from OBC UI");
        String pcsPresence = find_element_text(pcsStatus);
        if (pcsPresence.equals("PENDING CANCEL STATUS  Print Ltr")) {
            storePcsDetails.setSubscriptionCancelStatus(find_element_text(pcsStatus).replaceAll("Print Ltr", ""));
        } else {
            storePcsDetails.setSubscriptionCancelStatus("N");
        }
        logger.info(BLUE_UNDERLINED + "*******Subscription cancel status********" + ANSI_RESET);
        logger.info("Subscription Cancel Status:--->" + storePcsDetails.getSubscriptionCancelStatus());
        return storePcsDetails;
    }

    @Step("Store freeze details")
    public StoreFreezeStatus store_freeze() {
        logger.info("Getting the Freeze Status of Subscription from OBC UI");
        String freezePresence = find_element_text(freezeStatus).trim();
        if (freezePresence.equals(FROZEN_ACCOUNT)) {
            storeFreezeStatus.setSubscriptionFreezeStatus(find_element_text(freezeStatus).trim());
        } else {
            storeFreezeStatus.setSubscriptionFreezeStatus("N");
        }
        logger.info(BLUE_UNDERLINED + "**************Subscription Freeze status******************" + ANSI_RESET);
        logger.info("Subscription Freeze status:--->" + storeFreezeStatus.getSubscriptionFreezeStatus());
        return storeFreezeStatus;
    }

    @Step("Store member status")
    public AccountSummaryPage verify_member_status() {
        storeMemberStatusDetails.setMemberStatus(find_element_text(memberStatus));
        verify_value_matches(storeMemberStatusDetails.getMemberStatus(), ACTIVE);
        logger.info(BLUE_UNDERLINED + "********Member status details are mentioned below*************" + ANSI_RESET);
        logger.info("Member status :--->" + storeMemberStatusDetails.getMemberStatus());
        return me();
    }

    @Step("Store member details")
    public StoreMemberDetails store_member_details(Map getData) {
        int memerSinceLength=find_element_text(memberSinceDate).length();
        logger.info("Getting Member Details from OBC Application");
        if (is_element_exist(badAddress)) {
            storeMemberDetails.setMemberName(find_element_text(memberName).replace(" ", ""));
            storeMemberDetails.setMemberAddress(BAD_ADDRESS);
        } else {
            String[] memberAddresses2 = find_element_text(memberAddress2).split(",");
            storeMemberDetails.setMemberAddress(find_element_text(memberAddress));
            storeMemberDetails.setMemberCity(memberAddresses2[0].trim());
            storeMemberDetails.setMemberZipCode(memberAddresses2[1].replaceAll("[A-Z]", "").trim());
            storeMemberDetails.setMemberCountry(find_element_text(memberCountry));
            storeMemberDetails.setMemberName(find_element_text(memberName).replace(" ", ""));
        }
        storeMemberDetails.setMemberEmail(find_element_text(memberEmail).trim());
        if (find_element_text(memberHomePhone).equals(" ") && find_element_text(memberWorkPhone).equals(" ")) {
            storeMemberDetails.setMemberHomePhone(find_element_text(memberWorkPhone).trim());
            if (find_element_text(memberWorkPhone).equals(" ")) {
                storeMemberDetails.setMemberHomePhone(find_element_text(memberCellPhone).trim());
            }
        } else {
            storeMemberDetails.setMemberHomePhone(find_element_text(memberHomePhone).trim());
        }
        storeMemberDetails.setMemberWorkPhone(find_element_text(memberWorkPhone));
        storeMemberDetails.setMemberCellPhone(find_element_text(memberCellPhone));
        storeMemberDetails.setMemberEmergencyPhone(find_element_text(memberEmerPhone));
        storeMemberDetails.setMembershipType(find_element_text(membershipType).trim());
        if(find_element_text(memberSinceDate).substring((memerSinceLength-4),memerSinceLength).equalsIgnoreCase("2098")){
            storeMemberDetails.setMemberSinceDate(find_element_text(memberSinceDate).substring(0,memerSinceLength-4)+"1998");
        }
        else if(find_element_text(memberSinceDate).substring((memerSinceLength-4),memerSinceLength).equalsIgnoreCase("2099")){
            storeMemberDetails.setMemberSinceDate(find_element_text(memberSinceDate).substring(0,memerSinceLength-4)+"1999");
        }
        else
        storeMemberDetails.setMemberSinceDate(find_element_text(memberSinceDate));

        logger.info(BLUE_UNDERLINED + "**************Member Details are listed below************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeMemberDetails));

        return storeMemberDetails;
    }

    @Step("Store agreement details")
    public StoreAgreementDetails store_agreement_details(Map getData) {
        int contractBeginLength=find_element_text(contractBeganDate).length();

        logger.info("Getting  Agreement Details from OBC UI");

        storeAgreementDetails.setPastDueAmount("$" + find_element_text(pastDueAmount));
        storeAgreementDetails.setDueAmount("$" + find_element_text(dueAmount).replaceAll(",", ""));
        storeAgreementDetails.setNextDueDate(find_element_text(nextDueDate));

        if(find_element_text(contractBeganDate).substring((contractBeginLength-4),contractBeginLength).equalsIgnoreCase("2098")){
            storeAgreementDetails.setContactBeganDate(find_element_text(contractBeganDate).substring(0,contractBeginLength-4)+"1998");
        }
        else if(find_element_text(contractBeganDate).substring((contractBeginLength-4),contractBeginLength).equalsIgnoreCase("2099")){
            storeAgreementDetails.setContactBeganDate(find_element_text(contractBeganDate).substring(0,contractBeginLength-4)+"1999");
        }
        else
        storeAgreementDetails.setContactBeganDate(find_element_text(contractBeganDate));

        logger.info(BLUE_UNDERLINED + "**************Agreement details are mentioned below****************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeAgreementDetails));

        return storeAgreementDetails;
    }

    @Step("Store subscription details")
    public StoreSubscriptionDetails
    store_subscription_details(Map getData) {
        String subItemOBC=find_element_text(subscriptionType);
        logger.info("Getting  Subscription Details from OBC UI");
        if (is_element_exist(couponType)) {
             //subItemOBC = find_element_text(subscriptionType);
            switch (subItemOBC) {
                case "Cash":
                    storeSubscriptionDetails.setSubscriptionType("Paid Up Front");
                    break;
                case "Installment":
                    storeSubscriptionDetails.setSubscriptionItem("DUES");
                    storeSubscriptionDetails.setSubscriptionExpirationDate(find_element_text(subscriptionExpirationDate));
                    storeSubscriptionDetails.setSubscriptionBeganDate(find_element_text(contractBeganDate));
                    storeSubscriptionDetails.setSubscriptionType(INSTALLMENT_COUPON);
                    break;
                case "Open end":
                    storeSubscriptionDetails.setSubscriptionType("Paid Up Front");
                    break;
            }

            // storeSubscriptionDetails.setSubscriptionType("Dues");
            logger.info(convertObjectIntoJsonString(storeSubscriptionDetails));
        } else {
            if(subItemOBC.equalsIgnoreCase("Cash")){
                storeSubscriptionDetails.setSubscriptionType("Paid Up Front");
                storeSubscriptionDetails.setSubscriptionBeganDate(find_element_text(contractBeganDate));
                storeSubscriptionDetails.setSubscriptionExpirationDate(find_element_text(subscriptionExpirationDate));
            }
            else
            storeSubscriptionDetails.setSubscriptionType(find_element_text(subscriptionType).replace(" end", ""));
            storeSubscriptionDetails.setSubscriptionBeganDate(find_element_text(contractBeganDate));
            storeSubscriptionDetails.setSubscriptionDueAmount("$" + find_element_text(dueAmount).replaceAll(",", ""));
            storeSubscriptionDetails.setSubscriptionNextDueDate(find_element_text(nextDueDate));
            storeSubscriptionDetails.setSubscriptionExpirationDate(find_element_text(subscriptionExpirationDate));

            click(invoiceLink);
            if (is_element_exist(noInvoices)) {
                storeSubscriptionDetails.setSubscriptionItem(DUES);
            } else {
                 subItemOBC = find_element_text(subItem);
                switch (subItemOBC) {
                    case DUES:
                        storeSubscriptionDetails.setSubscriptionItem(DUES);
                        break;
                    case ENHANCE:
                        storeSubscriptionDetails.setSubscriptionItem(ENHANCE_FEE);
                        break;
                    case MAINTENANCE_FEE:
                        storeSubscriptionDetails.setSubscriptionItem(MAINTENANCE_FEE);
                        break;
                    case MONTHLY_RENTAL_REAL_ESTATE:
                        storeSubscriptionDetails.setSubscriptionItem(RENT);
                        break;
                    case CHARGE_BACK:
                        storeSubscriptionDetails.setSubscriptionItem(CHARGEBACK);
                        break;
                    case UNASSIGNED:
                        storeSubscriptionDetails.setSubscriptionItem(PROFITCTR1);
                        break;
                    case INSTALLATION_FEE:
                        storeSubscriptionDetails.setSubscriptionItem(INSTALLATION_FEE);
                        break;
                }
            }
            logger.info(BLUE_UNDERLINED + "****************Subscription details are mentioned below************************" + ANSI_RESET);
            logger.info(convertObjectIntoJsonString(storeSubscriptionDetails));
            click(accountSummaryLink);
        }
        return storeSubscriptionDetails;
    }

    @Step("Store payment method details")
    public StorePaymentMethodDetails store_payment_method_details(Map getData) {
        logger.info("Getting  Payor Payment Method Details from OBC UI");
        if (is_element_exist(noBillingInfo) || is_element_exist(couponType)) {
            storePaymentMethodDetails.setPaymentType("No Billing Information");
        } else {
            String obcPaymentMethodType = find_element_text(paymentMethodType).substring(0, 2);
            if (obcPaymentMethodType.equalsIgnoreCase("EF")) {

                storePaymentMethodDetails.setPayorName(find_element_text(payorName).replaceAll("Name: ", ""));
                storePaymentMethodDetails.setRoutingNumber(find_element_text(paymentMethodDetail1).replaceAll("Routing Number: ", ""));
                storePaymentMethodDetails.setLastFour(find_element_text(paymentMethodDetail2).replaceAll("[^0-9]", ""));
                storePaymentMethodDetails.setBankAccountType(find_element_text(paymentMethodType).replaceAll("EFT: ", "").replace("Account", ""));
                storePaymentMethodDetails.setPaymentType(obcPaymentMethodType);

            } else if (obcPaymentMethodType.equalsIgnoreCase("Cr")) {

                storePaymentMethodDetails.setPayorName(find_element_text(payorName).replaceAll("Name: ", ""));
                storePaymentMethodDetails.setLastFour(find_element_text(paymentMethodDetail1).replaceAll("[^0-9]", ""));
                storePaymentMethodDetails.setExpirationDate(find_element_text(paymentMethodDetail2).replaceAll("Expiration Date: ", ""));
                storePaymentMethodDetails.setCreditCardType(find_element_text(paymentMethodType).replaceAll("Credit Card: ", ""));
                storePaymentMethodDetails.setPaymentType(obcPaymentMethodType.replaceAll("Cr", "Credit Card"));
            }
        }
        logger.info(BLUE_UNDERLINED + "******************Payment method details are mentioned below**********************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storePaymentMethodDetails));
        return storePaymentMethodDetails;
    }

    @Step("Store late fee details")
    public StoreLateFeeDetails store_late_fee_details() {
        if (is_element_exist(lateFeeLink)) {
            click(lateFeeLink);
            wait_until(2);
            switch_to_window();
            logger.info("Count of late fee in OBC is  :-->" + find_element_count(wavesRadioButton));
            storeLateFeeDetails.setLateFeeCount(String.valueOf(find_element_count(wavesRadioButton)));
            storeLateFeeDetails.setLateFeeAmount(String.valueOf(find_element_text(singleFeeAmount)));

            click(closeButton);
            switch_to_parent_window();
        } else {
            logger.info("Late Fee Not Present");
            storeLateFeeDetails.setLateFeeCount("Late Fee Not Present");
        }
        logger.info(BLUE_UNDERLINED + "***************Store late fee details are mentioned below**********" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeLateFeeDetails));
        return storeLateFeeDetails;
    }

    @Step("Store service fee details")
    public StoreServiceFeeDetails store_service_fee_details() {
        logger.info("Storing Service Fee Details");
        if (is_element_exist(serviceFeeLink)) {
            click(serviceFeeLink);
            switch_to_window();
            logger.info("Count of Service Fee in OBC is  :-->" + find_element_count(wavesRadioButton));
            storeServiceFeeDetails.setServiceFeeCount(String.valueOf(find_element_count(wavesRadioButton)));
            storeServiceFeeDetails.setServiceFeeAmount(String.valueOf(find_element_text(singleFeeAmount)));
            click(closeButton);
            switch_to_parent_window();
        } else {
            logger.info("Service Fee Not Present");
            storeServiceFeeDetails.setServiceFeeCount("Service Fee Not Present");
        }
        logger.info(BLUE_UNDERLINED + "****************Service fee details are mentioned below*********************************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeServiceFeeDetails));
        return storeServiceFeeDetails;
    }

    @Step("Log out OBC")
    public AccountSummaryPage logout_obcui() {
        logger.info("LogOut from OBC Application");
        click(logOut);
        wait_until(2);
        return me();
    }

    public StoreMemberNoteDetails store_member_note_details() {
        click(accountNote);
        Map<String, String>map= new HashMap<>();
        logger.info("Storing member note details");
        for (int i = 5; i <= 23; i++) {
            String memberNote = find_element_text(get_member_note_xpath(i));
            map.put("memberNote"+i,memberNote);
            i = i + 1;
        }
        storeMemberNoteDetails.setMemberNote1(map.get("memberNote5"));
        storeMemberNoteDetails.setMemberNote2(map.get("memberNote7"));
        storeMemberNoteDetails.setMemberNote3(map.get("memberNote9"));
        storeMemberNoteDetails.setMemberNote4(map.get("memberNote11"));
        storeMemberNoteDetails.setMemberNote5(map.get("memberNote13"));
        storeMemberNoteDetails.setMemberNote6(map.get("memberNote15"));
        storeMemberNoteDetails.setMemberNote7(map.get("memberNote17"));
        storeMemberNoteDetails.setMemberNote8(map.get("memberNote19"));
        storeMemberNoteDetails.setMemberNote9(map.get("memberNote21"));
        storeMemberNoteDetails.setMemberNote10(map.get("memberNote23"));

        logger.info(BLUE_UNDERLINED + "****************Member Notes Details are mentioned below*********************************" + ANSI_RESET);
        logger.info(convertObjectIntoJsonString(storeMemberNoteDetails));
        return storeMemberNoteDetails;
    }
}
