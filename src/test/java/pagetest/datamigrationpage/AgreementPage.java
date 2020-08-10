
package pagetest.datamigrationpage;


import datastore.StoreAgreementDetails;
import datastore.StoreRenewalDetails;
import helper.DataBaseHandler;
import org.openqa.selenium.By;
import org.testng.Assert;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static util.UtilityGeneric.convertObjectIntoString;
import static util.UtilityGeneric.getFormatedDate;

//Created By Monika Phoughat
//Date : 18-09-2019

public class AgreementPage extends AbcCommonAbstractPage<AgreementPage> {
    private By memberLink = By.xpath("//a[@href='/uno-app/app/member-management']/i");
    private By searchIcon = By.xpath("//div[@id='searchBy']//div[contains(@class,'FormSelect__control css')]");
    private By phoneNumber = By.xpath("//div[contains(text(),'Phone Number')]");
    private By search = By.xpath("//input[@id='searchInput']");
    protected String member = "//ul[contains(@class,'ui-infinite-list list')]/li[%s]";
    private By profileTab = By.xpath("//div[contains(text(),'Profile')]");
    private By email = By.xpath("//input[@id='email']");
    private By memberCount = By.xpath("//ul[contains(@class,'ui-infinite-list list')]/li");
    private By backIcon = By.xpath("//i[contains(@class,'icon-arrow-left-thin ui-icon')]");
    private By agreementTab = By.xpath("//i[@data-abc-id='agreementTitleIcon']");
    private By beginDate = By.xpath("//input[@name='startDate']");
    private By nextDueDate = By.xpath("//input[@name='nextInvoiceDate']");
    private By pastDue = By.xpath("//input[@name='pastDue']");
    private By feesDue = By.xpath("//input[@name='feesDue']");
    private By totalPastDue = By.xpath("//input[@name='totalPastDue']");
    private By amountDue = By.xpath("//input[@name='amountDue']");
    private By totalAmountDue = By.xpath("//input[@name='totalAmountDue']");
    private By downPayment = By.xpath("//input[@name='downpayment']");
    private By closeButton = By.xpath("//i[contains(@class,'drawerControlIcon')]");


    public AgreementPage click_agreementTab() {
        logger.info("Click on Agreement Tab");
        verify(elementToBeClickable(agreementTab), 60, 2000);
        wait_until(2);
        click(agreementTab);
        return me();
    }

    public AgreementPage search_member(String name) {
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

    public AgreementPage click_profile_tab() {
        logger.info("Clicking on Member Profile Tab");
        verify(elementToBeClickable(profileTab), 60, 2000);
        return click(profileTab);
    }

    public AgreementPage select_member(String email) {
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

    public AgreementPage click_close_button() {
        logger.info("Click on Close Button");
        verify(elementToBeClickable(closeButton), 60, 2000);
        wait_until(2);
        return click(closeButton);
    }

    public AgreementPage verify_begin_date(String beginDate) {
        logger.info("Verifying Begin date");
        beginDate = beginDate.substring(0, 10);
        beginDate = getFormatedDate(beginDate);
        beginDate = beginDate.replace("-", "/");
        verify_element_by_value(this.beginDate, beginDate);
        return me();
    }

    public AgreementPage verify_next_due_date(String nextDueDate) {
        logger.info("Verifying Next Due Date");
        if (find_element_value(this.nextDueDate).equalsIgnoreCase(nextDueDate)) {
            verify_element_by_value(this.nextDueDate, nextDueDate);
        } else {
            int finalNextDueDate = Integer.parseInt(nextDueDate.substring(9, 10)) + 1;
            verify_element_by_value(this.nextDueDate, nextDueDate.substring(0, 9) + finalNextDueDate);
        }
        return me();
    }

    public AgreementPage verify_next_renewal_due_date(String nextDueDate) {
        logger.info("Verifying Next Due Date");
        String renewDateUno = find_element_value(this.nextDueDate);
        verify_value_matches(renewDateUno.substring(0, 5), nextDueDate.substring(0, 5));
        return me();
    }


    public AgreementPage verify_past_due(String pastDue) {
        logger.info("Verifying Past Due Amount");
        verify_element_by_value(this.pastDue, pastDue);
        return me();
    }

    public AgreementPage verify_fees_due(String feesDue) {
        logger.info("Verifying Fees Due Amount");
        verify_element_by_value(this.feesDue, feesDue);
        return me();
    }

    public AgreementPage verify_total_past_due(String totalPastDue) {
        logger.info("Verifying Total Past Due Amount");
        verify_element_by_value(this.totalPastDue, totalPastDue);
        return me();
    }

    public AgreementPage verify_amount_due(String amountDue) {
        logger.info("Verifying Amount Due ");
        verify_value_matches(find_element_value(this.amountDue).replaceAll(",", ""), amountDue);
        return me();
    }

    public AgreementPage verify_total_amount_due(String totalAmountDue) {
        logger.info("Verifying Total Amount Due ");
        verify_element_by_value(this.totalAmountDue, totalAmountDue);
        return me();
    }

    public AgreementPage verify_down_payment(String downPayment) {
        logger.info("Verifying Down Payment");
        verify_element_by_value(this.downPayment, downPayment);
        return me();
    }

    public AgreementPage verify_agreement_contract_began_date(String beganDate) {
        logger.info("Verifying Agreement Contract Began Date ");
        if (!(find_element_value(beginDate).equals(beganDate))) {
            String[] contractBeginDate = beganDate.split("/");
            String dd = null;
            int day = Integer.parseInt(contractBeginDate[1]);
            if ((day < 10) && (contractBeginDate[1].length() < 2)) {
                dd = "0" + contractBeginDate[1];
                beganDate = contractBeginDate[0] + "/" + dd + "/" + contractBeginDate[2];
            }

        }
        verify_element_by_value(beginDate, beganDate);
        return me();
    }

    public AgreementPage verify_agreement_details_obc(StoreAgreementDetails storeAgreementDetails, StoreRenewalDetails storeRenewalDetails) {
        logger.info("Verifying Agreement Details");
        click_agreementTab();
        if (storeAgreementDetails.getNextDueDate().equalsIgnoreCase("") && storeAgreementDetails.getDueAmount().equalsIgnoreCase("$0.00")) {
            if(storeRenewalDetails.getRenewalFlag().equalsIgnoreCase("")){
                verify_agreement_contract_began_date(storeAgreementDetails.getContactBeganDate());
            }
            else {
                verify_agreement_contract_began_date(storeAgreementDetails.getContactBeganDate());
                verify_next_renewal_due_date(storeRenewalDetails.getRenewalDate());
                verify_past_due(storeAgreementDetails.getPastDueAmount());
                verify_amount_due(storeRenewalDetails.getRenewalAmount());
            }

        }
        else {
            verify_all(
                    () -> verify_agreement_contract_began_date(storeAgreementDetails.getContactBeganDate()),
                    () -> verify_next_due_date(storeAgreementDetails.getNextDueDate()),
                    () -> verify_past_due(storeAgreementDetails.getPastDueAmount()),
                    () -> verify_amount_due(storeAgreementDetails.getDueAmount())
            );
        }
        return me();
    }

    public AgreementPage verify_agreement_details(Map agreementInfo) {
        logger.info("Verifying Agreement Details");
        click_agreementTab();

        verify_all(
                () -> verify_begin_date(convertObjectIntoString(agreementInfo.get("beginDate"))),
                () -> verify_next_due_date(convertObjectIntoString(agreementInfo.get("nextDueDate"))),
                () -> verify_past_due(convertObjectIntoString(agreementInfo.get("pastDue"))),
                () -> verify_fees_due(convertObjectIntoString(agreementInfo.get("feesDue"))),
                () -> verify_total_past_due(convertObjectIntoString(agreementInfo.get("totalPastDue"))),
                () -> verify_amount_due(convertObjectIntoString(agreementInfo.get("amountDue"))),
                () -> verify_total_amount_due(convertObjectIntoString(agreementInfo.get("totalAmountDue"))),
                () -> verify_down_payment(convertObjectIntoString(agreementInfo.get("downPayment")))
        );
        return me();
    }

    public AgreementPage verify_agreement_details(List memberId, List agreementId, DataBaseHandler dataBaseHandler) {
        for (int i = 0; i < agreementId.size(); i++) {
            Map<String, String> memberInfo = (Map<String, String>) memberId.get(i);
            Map<String, String> agreementInfo = (Map<String, String>) agreementId.get(i);
            click_close_button();
            click(memberLink);
            search_member(memberInfo.get("phoneNumber")).
                    select_member(memberInfo.get("email"));
            verify_agreement_details(agreementInfo);

        }
        return me();
    }
}

