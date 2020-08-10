package pagetest.datamigrationpage;

import datastore.StoreFreezeStatus;
import datastore.StorePcsDetails;
import datastore.StoreRenewalDetails;
import datastore.StoreSubscriptionDetails;
import helper.DataBaseHandler;
import org.openqa.selenium.By;
import org.testng.Assert;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.List;
import java.util.Map;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static util.UtilityGeneric.convertObjectIntoString;
import static util.UtilityGeneric.getFormatedDate;

//Created By Monika Phoughat
//Date : 22-09-2019

public class SubscriptionPage extends AbcCommonAbstractPage<SubscriptionPage> {

    private By memberLink = By.xpath("//a[@href='/uno-app/app/member-management']/i");
    private By searchIcon = By.xpath("//div[@id='searchBy']//div[contains(@class,'FormSelect__control css')]");
    private By phoneNumber = By.xpath("//div[contains(text(),'Phone Number')]");
    private By search = By.xpath("//input[@id='searchInput']");
    protected String member = "//ul[contains(@class,'ui-infinite-list list')]/li[%s]";
    private By profileTab = By.xpath("//div[contains(text(),'Profile')]");
    private By email = By.xpath("//input[@id='email']");
    private By memberCount = By.xpath("//ul[contains(@class,'ui-infinite-list list')]/li");
    private By backIcon = By.xpath("//i[contains(@class,'icon-arrow-left-thin ui-icon')]");
    private By subscriptionTab = By.xpath("//i[@data-abc-id='subscriptionsTitleIcon']");
    private By type = By.xpath("//p[@data-abc-id='type']");
    private By totalAmount = By.xpath("//input[@id='totalAmount']");
    private By nextDueDate = By.xpath("//input[@id='nextInvoiceDateInput']");
    private By expiryDate = By.xpath("//input[@name='expirationDate']");
    private By subscriptionList = By.xpath("//td[contains(text(),'DUES')]");
    private String subscriptionList1 = "//td[contains(text(),'";
    private String subscriptionList2 = "')]";
    private By subscriptionType = By.xpath("//div[@data-abc-id='typeInput']");
    private By beginDate = By.xpath("//input[@id='startDatePickerInput']");
    private By amenity = By.xpath("//div[@id='subscriptions[0].catalogItem']");
    private By frequency = By.xpath("//div[@id='subscriptions[0].frequency']");
    private By firstDueDate = By.xpath("//input[@id='subscriptions[0].firstDueDate']");
    private By subStatus = By.xpath("//div[@data-abc-id='subscriptionStatus']");
    //renewal
    private By noOfPayments = By.xpath("//input[@id='renewDuration']");
    private By renewalType = By.xpath("//div//input[@id='renewTypeInput']");
    private By renewDate = By.xpath("//input[@id='renewDateInput']");
    private By renewAmount = By.xpath("//input[@id='renewStatementAmount']");
    private By paymentFrequency = By.xpath("//div[@id='subscriptions[0].renewFrequency']");
    private By closeButton = By.xpath("//button[@data-abc-id='closeDrawerButton']");

    //String subItem = storeSubscriptionDetails.getSubscriptionItem();

    public SubscriptionPage click_sub_list(String subItem) {
        logger.info("Clicking Subscription....");
        String subItemUno = subscriptionList1 + subItem + subscriptionList2;
        wait_until(1);
        click(By.xpath(subItemUno));
        return me();
    }

    public SubscriptionPage search_member(String name) {
        logger.info("Searching Member");
        click(searchIcon);
        click(phoneNumber);
        enter(search, name);
        wait_until(2);
        return me();
    }

    public int get_member_count() {
        int memberCount = find_element_count(this.memberCount);
        return memberCount;
    }

    public By get_member(String no) {
        return By.xpath(String.format(member, no));
    }

    public SubscriptionPage click_profile_tab() {
        logger.info("Clicking on Member Profile Tab");
        verify(elementToBeClickable(profileTab), 60, 2000);
        return click(profileTab);
    }

    public SubscriptionPage select_member(String email) {
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

    public SubscriptionPage click_subscriptionTab() {
        logger.info("Click on Subscription Tab");
        verify(elementToBeClickable(subscriptionTab), 60, 2000);
        wait_until(2);
        return click(subscriptionTab);
    }

    public String get_subscription_type() {
        return find_element_text(this.type);
    }

    public SubscriptionPage click_subscription_list() {
        logger.info("Click on Subscription List");
        verify(elementToBeClickable(subscriptionList), 60, 2000);
        wait_until(2);
        return click(subscriptionList);
    }

    public SubscriptionPage verify_subscription_type(String subscriptionType) {
        logger.info("Verifying Subscription Type");
        subscriptionType = subscriptionType.replace("_", "-");
        verify_element_by_text(this.subscriptionType, subscriptionType);
        return me();
    }

    public SubscriptionPage verify_begin_date(String beginDate) {
        logger.info("Verifying Subscription Begin date");
        beginDate = beginDate.substring(0, 10);
        System.out.println(beginDate);
        beginDate = getFormatedDate(beginDate);
        beginDate = beginDate.replace("-", "/");
        verify_element_by_value(this.beginDate, beginDate);
        return me();
    }

    public SubscriptionPage verify_amenity(String amenity) {
        logger.info("Verifying Subscription Amenity");
        verify_element_by_text(this.amenity, amenity);
        return me();
    }

    public SubscriptionPage verify_frequency(String frequency) {
        logger.info("Verifying Subscription Frequency");
        verify_element_by_text(this.frequency, frequency);
        return me();
    }

    public SubscriptionPage verify_first_due_date(String firstDueDate) {
        logger.info("Verifying Subscription First Due Date");
        firstDueDate = firstDueDate.substring(0, 10);
        System.out.println(firstDueDate);
        firstDueDate = getFormatedDate(firstDueDate);
        firstDueDate = firstDueDate.replace("-", "/");
        verify_element_by_value(this.firstDueDate, firstDueDate);
        return me();
    }

    public SubscriptionPage verify_expiry_date(String expiryDate) {
        logger.info("Verifying Subscription Expiry Date");
        expiryDate = expiryDate.substring(0, 10);
        expiryDate = getFormatedDate(expiryDate);
        expiryDate = expiryDate.replace("-", "/");
        verify_element_by_value(this.expiryDate, expiryDate);
        return me();
    }

    public SubscriptionPage verify_no_of_payments(String noOfPayments) {
        logger.info("Verifying Subscription No Of Payments");
        verify_element_by_value(this.noOfPayments, noOfPayments);
        return me();
    }

    public SubscriptionPage verify_renewal_type(String expectedRenewalType) {
        logger.info("Verifying Subscription Renewal Type");
        verify_element_attribute_value(this.renewalType, "placeholder", expectedRenewalType);
        return me();
    }

    public SubscriptionPage verify_renewal_date(String renewDate) {
        logger.info("Verifying Subscription Renewal Date");
        renewDate = renewDate.substring(0, 10);
        renewDate = getFormatedDate(renewDate);
        renewDate = renewDate.replace("-", "/");
        verify_element_by_value(this.renewDate, renewDate);
        return me();
    }

    public SubscriptionPage verify_renewal_date_obc(String renewDate) {
        logger.info("Verifying Subscription Renewal Date");
        String renewDateUno = find_element_value(this.renewDate);
        verify_value_matches(renewDateUno.substring(0, 5), renewDate.substring(0, 5));
        return me();
    }

    public SubscriptionPage verify_renewal_amount(String renewAmount) {
        logger.info("Verifying Subscription Renewal Amount");
        String renewAmountUno = find_element_value(this.renewAmount).replaceAll(",", "");
        verify_value_matches(renewAmountUno, renewAmount);
        return me();
    }

    public SubscriptionPage verify_renewal_no_of_payments(String noOfPayments) {
        logger.info("Verifying Renewal Subscription No Of Payments");
        verify_element_by_value(this.noOfPayments, noOfPayments);
        return me();
    }

    public SubscriptionPage verify_pcs_status(String pcsStatus) {
        logger.info("Verifying Pending Cancel Status");
        verify_element_by_text(this.subStatus, pcsStatus.replaceAll("PENDING CANCEL STATUS", "PENDING CANCELLATION").trim());
        return me();
    }

    public SubscriptionPage verify_freeze_status(String freezeStatus) {
        logger.info("Verifying  Subscription freeze Status");
        verify_element_by_text(this.subStatus, freezeStatus.replaceAll("FROZEN ACCOUNT", "FROZEN"));
        return me();
    }

    public SubscriptionPage verify_payment_frequency(String paymentFrequency) {
        logger.info("Verifying Subscription Payment Frequency");
        verify_element_by_text(this.paymentFrequency, paymentFrequency);
        return me();
    }

    public SubscriptionPage click_sub_close_button() {
        logger.info("Clicking on Subscription Close Button");
        verify(elementToBeClickable(closeButton), 60, 2000);
        wait_until(2);
        return click(closeButton);
    }

    public SubscriptionPage verify_sub_type(String subType) {
        logger.info("Verifying Subscription Type");
        is_text_present_on_page(subType);
        // verify_element_by_text_partial_match(subscriptionType, subType);
        return me();
    }

    public SubscriptionPage verify_sub_began_date(String subBegandate) {
        logger.info("Verifying Subscription Began Date");
        if (!(find_element_value(beginDate).equals(subBegandate))) {
            String[] subBeginDate = subBegandate.split("/");
            String dd = null;
            int day = Integer.parseInt(subBeginDate[1]);
            if ((day < 10) && (subBeginDate[1].length() < 2)) {
                dd = "0" + subBeginDate[1];
                subBegandate = subBeginDate[0] + "/" + dd + "/" + subBeginDate[2];
            }
        }
        verify_element_by_value(beginDate, subBegandate);
        return me();
    }

    public SubscriptionPage verify_sub_due_amount(String subDueAmount) {
        logger.info("Verifying Subscription Due Amount");
        verify_value_matches(find_element_value(this.totalAmount).replaceAll(",", ""), subDueAmount);
        return me();
    }

    public SubscriptionPage verify_next_due_date(String nextDueDate) {
        logger.info("Verifying Next Due Date");
        if (find_element_value(this.nextDueDate).equalsIgnoreCase(nextDueDate)) {
            verify_element_by_value(this.nextDueDate, nextDueDate);
        } else {
            int finalNextDueDate = Integer.parseInt(nextDueDate.substring(9, 10)) + 1;
            verify_element_by_value(this.nextDueDate, nextDueDate.substring(0, 9) + finalNextDueDate);
        }
        return me();
    }

    public SubscriptionPage verify_sub_expiry_date(String expirationDate) {
        logger.info("Verifying Subscription Expiry Date");
        if (!(find_element_value(expiryDate).equals(expirationDate))) {
            String[] subExpiryDate = expirationDate.split("/");
            String dd = null;
            int day = Integer.parseInt(subExpiryDate[1]);
            if ((day < 10) && (subExpiryDate[1].length() < 2)) {
                dd = "0" + subExpiryDate[1];
                expirationDate = subExpiryDate[0] + "/" + dd + "/" + subExpiryDate[2];
            }
        }
        verify_element_by_value(expiryDate, expirationDate);
        return me();
    }

    public SubscriptionPage verify_subscription_details_obc(StoreSubscriptionDetails storeSubscriptionDetails) {
        logger.info("Verifying Subscription Details");
        click_subscriptionTab();
        String subType = storeSubscriptionDetails.getSubscriptionType();
        switch (subType) {
            case PAID_UP_FRONT:
                click_sub_list(storeSubscriptionDetails.getSubscriptionType());
                verify_sub_type(storeSubscriptionDetails.getSubscriptionType());
                break;
            case OPEN:
                click_sub_list(storeSubscriptionDetails.getSubscriptionItem());

                verify_all(
                        () -> verify_sub_type(storeSubscriptionDetails.getSubscriptionType()),
                        () -> verify_sub_began_date(storeSubscriptionDetails.getSubscriptionBeganDate()),
                        () -> verify_sub_due_amount(storeSubscriptionDetails.getSubscriptionDueAmount()),
                        () -> verify_next_due_date(storeSubscriptionDetails.getSubscriptionNextDueDate())
                );
                break;
            case INSTALLMENT:
                click_sub_list(storeSubscriptionDetails.getSubscriptionItem());

                verify_all(
                        () -> verify_sub_type(storeSubscriptionDetails.getSubscriptionType()),
                        () -> verify_sub_began_date(storeSubscriptionDetails.getSubscriptionBeganDate()),
                        () -> verify_sub_due_amount(storeSubscriptionDetails.getSubscriptionDueAmount()),
                        () -> verify_next_due_date(storeSubscriptionDetails.getSubscriptionNextDueDate()),
                        () -> verify_sub_expiry_date(storeSubscriptionDetails.getSubscriptionExpirationDate())
                );
                break;

            case INSTALLMENT_COUPON:
                click_sub_list(storeSubscriptionDetails.getSubscriptionItem());

                verify_all(
                        () -> verify_sub_type(storeSubscriptionDetails.getSubscriptionType()),
                        () -> verify_sub_began_date(storeSubscriptionDetails.getSubscriptionBeganDate()),
                        () -> verify_sub_expiry_date(storeSubscriptionDetails.getSubscriptionExpirationDate())
                );
        }
        return me();
    }

    public SubscriptionPage verify_renewal_subscription_details_obc(StoreRenewalDetails storeRenewalDetails) {
        logger.info("Verifying Renewal Subscription Details");
        String renewalPresence = storeRenewalDetails.getRenewalFlag();
        if (renewalPresence.equals("Y")) {
            String renewalType = storeRenewalDetails.getRenewalType();
            switch (renewalType) {
                case AUTO_RENEW_TO_OPEN:
                    verify_all(
                            () -> verify_renewal_type(storeRenewalDetails.getRenewalType()),
                            () -> verify_renewal_date_obc(storeRenewalDetails.getRenewalDate()),
                            () -> verify_renewal_amount(storeRenewalDetails.getRenewalAmount())
                    );
                    break;
                case NON_RENEWAL_AGREEMENT:
                    verify_renewal_type(storeRenewalDetails.getRenewalType());
                    break;
                case AUTO_RENEW_TO_TERM:
                    verify_all(
                            () -> verify_renewal_type(storeRenewalDetails.getRenewalType()),
                            () -> verify_renewal_date_obc(storeRenewalDetails.getRenewalDate()),
                            () -> verify_renewal_amount(storeRenewalDetails.getRenewalAmount()),
                            () -> verify_renewal_no_of_payments(storeRenewalDetails.getNoOfPayments())
                    );
                    break;
            }
        } else {
            logger.info("Renewal Information Not Present on this member");
        }
        return me();
    }

    public SubscriptionPage verify_subscription_pcs_obc(StorePcsDetails storePcsDetails) {
        String pcsPresence = storePcsDetails.getSubscriptionCancelStatus();
        if (pcsPresence.equals("N")) {
            logger.info(" Pending Cancel status Not Present");
        } else {
            logger.info("Verifying Pending Cancel status");
            verify_pcs_status(storePcsDetails.getSubscriptionCancelStatus());
        }
        return me();
    }

    public SubscriptionPage verify_subscription_freeze_obc(StoreFreezeStatus storeFreezeStatus) {
        String freezePresence = storeFreezeStatus.getSubscriptionFreezeStatus();

        if (freezePresence.equals("N")) {
            logger.info("Freeze Not Present");
        } else {
            logger.info("Verifying Freeze status of subscription");
            verify_freeze_status(storeFreezeStatus.getSubscriptionFreezeStatus());
        }
        click_sub_close_button();
        return me();
    }

    public SubscriptionPage verify_subscription_details(Map subscriptionInfo) {
        logger.info("Verifying Subscription Details");
        click_subscriptionTab();
        String subType = get_subscription_type();
        switch (subType) {
            case OPEN_END:
                click_subscription_list();

                verify_all(
                        () -> verify_subscription_type(convertObjectIntoString(subscriptionInfo.get("subscriptionType"))),
                        () -> verify_begin_date(convertObjectIntoString(subscriptionInfo.get("beginDate"))),
                        () -> verify_amenity(convertObjectIntoString(subscriptionInfo.get("amenity"))),
                        () -> verify_frequency(convertObjectIntoString(subscriptionInfo.get("frequency"))),
                        () -> verify_first_due_date(convertObjectIntoString(subscriptionInfo.get("firstDueDate")))
                );
                break;
            case INSTALLMENT:
                click_subscription_list();

                verify_all(
                        () -> verify_subscription_type(convertObjectIntoString(subscriptionInfo.get("subscriptionType"))),
                        () -> verify_begin_date(convertObjectIntoString(subscriptionInfo.get("beginDate"))),
                        () -> verify_amenity(convertObjectIntoString(subscriptionInfo.get("amenity"))),
                        () -> verify_frequency(convertObjectIntoString(subscriptionInfo.get("frequency"))),
                        () -> verify_first_due_date(convertObjectIntoString(subscriptionInfo.get("firstDueDate"))),
                        () -> verify_expiry_date(convertObjectIntoString(subscriptionInfo.get("expiryDate"))),
                        () -> verify_no_of_payments(convertObjectIntoString(subscriptionInfo.get("noOfPayments"))),
                        () -> verify_renewal_type(convertObjectIntoString(subscriptionInfo.get("renewalType"))),
                        () -> verify_renewal_date(convertObjectIntoString(subscriptionInfo.get("renewDate"))),
                        () -> verify_payment_frequency(convertObjectIntoString(subscriptionInfo.get("paymentFrequency")))
                );
                break;
            case PAID_UP_FRONT:
                click_subscription_list();

                verify_all(
                        () -> verify_subscription_type(convertObjectIntoString(subscriptionInfo.get("subscriptionType"))),
                        () -> verify_begin_date(convertObjectIntoString(subscriptionInfo.get("beginDate"))),
                        () -> verify_amenity(convertObjectIntoString(subscriptionInfo.get("amenity"))),
                        () -> verify_frequency(convertObjectIntoString(subscriptionInfo.get("frequency"))),
                        () -> verify_first_due_date(convertObjectIntoString(subscriptionInfo.get("firstDueDate"))),
                        () -> verify_expiry_date(convertObjectIntoString(subscriptionInfo.get("expiryDate")))
                );
                break;
        }
        click_sub_close_button();
        return me();
    }

    public SubscriptionPage verify_subscription_details(List memberId, List subscriptionId, DataBaseHandler dataBaseHandler) {
        for (int i = 0; i < subscriptionId.size(); i++) {
            Map<String, String> memberInfo = (Map<String, String>) memberId.get(i);
            Map<String, String> subscriptionInfo = (Map<String, String>) subscriptionId.get(i);
            click(memberLink);
            search_member(memberInfo.get("phoneNumber")).
                    select_member(memberInfo.get("email"));
            verify_subscription_details(subscriptionInfo);
        }
        return me();
    }
}

