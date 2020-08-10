package pagetest.datamigrationpage;

import config.EnvProperty;
import helper.ServicePropertyFileReader;
import io.qameta.allure.Step;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;


import java.io.IOException;
import java.util.*;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static util.UtilityGeneric.get_converted_date;

public class LocationPaymentPage extends AbcCommonAbstractPage<LocationPaymentPage> {

    EnvProperty envProperty = EnvProperty.getInstance(CASH_STORAGE_INI);
    //   ReadingDataFromIni readingDataFromIni = new ReadingDataFromIni();
    //Ini ini = new Wini(this.getClass().getResourceAsStream('/' + ServicePropertyFileReader.getInstance(ENV).getPropertyValue(ENV) + '/' + PROPERTY_INI));
    Ini ini = new Wini(this.getClass().getResourceAsStream('/' + "cashstorage.ini"));
    List<Map<String, String>> mapReimbursement = new ArrayList<>();
    List<Map<String, String>> mapDeduction = new ArrayList<>();

    //Deduction Row
    private By deductionsTab = By.xpath("//*[@data-value='Deductions']");
    private By reimbursementsTab = By.xpath("//*[@data-value='Reimbursements']");
    private By type = By.xpath("//span[@data-abc-id='typeText']");
    private By startDate = By.xpath("//span[@data-abc-id='startDateText']");
    private By code = By.xpath("//span[@data-abc-id='reasonCodeText']");
    private By amount = By.xpath("//span[@data-abc-id='amountText']");
    private By noOfPayments = By.xpath("//td[@data-abc-id='numberOfPayments']");
    private By description = By.xpath("//span[@data-abc-id='descriptionText']");
    private By status = By.xpath("//span[@data-abc-id='statusText']");

    //Deduction Sidebar
    private By deductionStatus = By.xpath("//input[@id='paymentStatus']");
    private By deductionType = By.xpath("//input[@id='paymentTypeInput']");
    private By deductionStartDate = By.xpath("//input[@id='startDateInput']");
    private By deductionNoOfPayments = By.xpath("//input[@id='numberOfPayments']");
    private By frequency = By.xpath("//input[@id='frequencyInput']");
    private By reimbursementDeductionCode = By.xpath("//input[@id='reimbursementDeductionCodeInput']");
    private By deductionAmount = By.xpath("//input[@id='amount']");
    private By cancellationDate = By.xpath("//input[@id='cancellationDateInput']");
    private By nextDueDate = By.xpath("//input[@id='nextDueDateInput']");
    private By currentBalance = By.xpath("//input[@id='currentBalance']");
    private By lastPaymentDate = By.xpath("//input[@id='lastPaymentDateInput']");
    private By lastPaymentAmount = By.xpath("//input[@id='lastPaymentAmount']");
    private By noOfPaymentsPaid = By.xpath("//input[@id='numberOfPaymentsPaid']");
    private By totalAmountPaid = By.xpath("//input[@id='totalAmountPaid']");
    private By glDepartment = By.xpath("//input[@id='glDepartment']");
    private By glAccount = By.xpath("//input[@id='glAccount']");
    private By cancellationReason = By.xpath("//div[@data-abc-id='cancellationReason']");
    private By paymentReason = By.xpath("//div[@data-abc-id='paymentReason']");
    private By beginningBalance = By.xpath("//input[@id='beginningBalance']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By paymentTab = By.xpath("//*[@data-abc-id='paymentTitleIcon']");
    private By deductionRow = By.xpath("//*[@data-value='Deductions']//following::td[1]");
    private By closeButton = By.xpath("//button//i[@data-abc-id='closeDrawerButtonIcon']");
    private By deductionDescription = By.xpath("//input[@id='glDepartment']//following::textarea[2]");
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private String deductionList1 = "//td[contains(text(),'";
    private String deductionList2 = "')]";
    private String dataAbcId = "locationPaymentsListRow";

    private String paymentRawXpath = "//tr[@data-abc-id='%s']/td/span[text()='%s']//../../td/span[text()='%s']/../../td/span[text()='%s']//../../td/span[text()='%s']";

    public LocationPaymentPage() throws IOException {
    }

    public By get_payment_xpath(String dataAbcId, String type, String code, String amount, String description) {
        return By.xpath(String.format(paymentRawXpath, dataAbcId, type, code, amount, description));
    }

    @Step("Verifying Deduction/Reimbursement item")
    public LocationPaymentPage verify_deduction_reimburshment_item(String type, String code, String amount, String description) {
        verify(visibilityOfAllElementsLocatedBy(get_payment_xpath(dataAbcId, type, code, amount, description)));
        return me();
    }

    @Step("Click on Location")
    private LocationPaymentPage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    @Step("Click on Close Button")
    private LocationPaymentPage click_close_button() {
        logger.info("Click on close button");
        click(closeButton);
        return me();
    }

    @Step("Click on Payment Tab")
    private LocationPaymentPage click_payment_tab() {
        logger.info("Click on Payment Tab");
        click(paymentTab);
        return me();
    }

    @Step("Click on Deduction Tab")
    private LocationPaymentPage click_deducation_tab() {
        logger.info("Click on Deduction Tab");
        click(deductionsTab);
        return me();
    }

    @Step("Click on Reimbursement Tab")
    private LocationPaymentPage click_reimbursement_tab() {
        logger.info("Click on Reimbursement Tab");
        click(reimbursementsTab);
        return me();
    }

    @Step("Click on Deduction Row")
    private LocationPaymentPage click_deducation_reimbursement_row() {
        logger.info("Click on Deduction row");
        click(deductionRow);
        return me();
    }

    public LocationPaymentPage click_deduction_rows() {
        logger.info("Clicking Deduction row....");
        String subItemUno = deductionList1 + "Recurring" + deductionList2;
        wait_until(1);
        click(By.xpath(subItemUno));
        return me();
    }

    @Step("Verifying type")
    private LocationPaymentPage verify_type(String expectedType) {
        logger.info("Verifying decution type on deduction row");
        verify_value_matches(find_element_text(this.type), expectedType);
        return me();
    }

    @Step("Verifying start Date")
    private LocationPaymentPage verify_start_date(String expectedStartDate) {
        logger.info("Verifying start date on deduction row");
        verify_value_matches(find_element_text(this.startDate), expectedStartDate);
        return me();
    }

    @Step("Verifying deduction code")
    private LocationPaymentPage verify_code(String expectedCode) {
        logger.info("Verifying Code on deduction row");
        verify_value_matches(find_element_text(this.code), expectedCode);
        return me();
    }

    @Step("Verifying deduction code")
    private LocationPaymentPage verify_amount(String expectedAmount) {
        logger.info("Verifying Amount on deduction row");
        verify_value_matches(find_element_text(this.amount), expectedAmount);
        return me();
    }

    @Step("Verifying number of payments")
    private LocationPaymentPage verify_number_of_payments(String expectedNumberOfPayments) {
        logger.info("Verifying Number of Payments deduction row");
        verify_value_matches(find_element_text(this.noOfPayments), expectedNumberOfPayments);
        return me();
    }

    @Step("Verifying Descriptions")
    private LocationPaymentPage verify_description(String expectedDescription) {
        logger.info("Verifying Description deduction row");
        verify_value_matches(find_element_text(this.description), expectedDescription);
        return me();
    }

    @Step("Verifying Status")
    private LocationPaymentPage verify_status(String expectedStatus) {
        logger.info("Verifying status on  deduction row");
        verify_value_matches(find_element_text(this.status), expectedStatus);
        return me();
    }

    //Deduction-Reimbursement verification

    @Step("Verifying Deduction/Reimbursement status on sidebar ")
    private LocationPaymentPage verify_payment_status(String expectedStatus) {
        logger.info("Verifying status on  deduction/reimbursement sidebar");
        verify_element_attribute_value(this.deductionStatus, "placeholder", expectedStatus);
        return me();
    }

    @Step("Verifying Deduction/Reimbursement start date on sidebar ")
    private LocationPaymentPage verify_payment_start_date(String expectedStartDate) {
        logger.info("Verifying start date on  deduction/reimbursement sidebar");
        verify_element_attribute_value(this.deductionStartDate, "value", expectedStartDate);
        return me();
    }

    @Step("Verifying Deduction/Reimbursement type date on sidebar ")
    private LocationPaymentPage verify_payment_type(String expectedType) {
        logger.info("Verifying type on  deduction/reimbursement sidebar");
        verify_element_attribute_value(this.deductionType, "placeholder", expectedType);
        return me();
    }

    @Step("Verifying Deduction/Reimbursement amount date on sidebar ")
    private LocationPaymentPage verify_payment_amount(String expectedAmount) {
        logger.info("Verifying amount on  deduction/reimbursement sidebar");
        verify_element_attribute_value(this.deductionAmount, "value", expectedAmount);
        return me();
    }

    @Step("Verifying Deduction/Reimbursement number of payments  on sidebar ")
    private LocationPaymentPage verify_payment_number_of_payments(String expectedNumberOfPayments) {
        logger.info("Verifying number on payments deduction/reimbursement sidebar");
        verify_element_attribute_value(this.deductionNoOfPayments, "value", expectedNumberOfPayments);
        return me();
    }

    @Step("Verifying Deduction/Reimbursement description  on sidebar ")
    private LocationPaymentPage verify_payment_description(String expectedDescription) {
        logger.info("Verifying description on  deduction/reimbursement sidebar");
        verify_element_by_text(this.deductionDescription, expectedDescription);
        return me();
    }

    @Step("Verifying frequency")
    private LocationPaymentPage verify_payment_frequency(String expectedFrequency) {
        logger.info("Verifying Frequency");
        verify_element_attribute_value(this.frequency, "placeholder", expectedFrequency);
        return me();
    }

    @Step("Verifying Reimbursement/Deduction Code")
    private LocationPaymentPage verify_payment_code(String expectedCode) {
        logger.info("Verifying Reimbursement/Deduction code");
        verify_element_attribute_value(this.reimbursementDeductionCode, "placeholder", expectedCode);
        return me();
    }

    @Step("Verifying Cancellation Date")
    private LocationPaymentPage verify_payment_cancellation_date(String expectedCancellationDate) {
        logger.info("Verifying Cancellation Date");
        verify_element_attribute_value(this.cancellationDate, "placeholder", expectedCancellationDate);
        return me();
    }

    @Step("Verifying Next Due Date")
    private LocationPaymentPage verify_payment_next_due_date(String expectedNextDueDate) {
        logger.info("Verifying Next Due Date Date");
        verify_element_attribute_value(this.nextDueDate, "placeholder", expectedNextDueDate);
        return me();
    }

    @Step("Verifying Current Balance")
    private LocationPaymentPage verify_payment_current_balance(String expectedCurrentBalance) {
        logger.info("Verifying current balance");
        verify_element_attribute_value(this.currentBalance, "placeholder", expectedCurrentBalance);
        return me();
    }

    @Step("Verifying Last Payment Date")
    private LocationPaymentPage verify_payment_last_payment_date(String expectedLastPaymentDate) {
        logger.info("Verifying Last payment date");
        verify_element_attribute_value(this.lastPaymentDate, "placeholder", expectedLastPaymentDate);
        return me();
    }

    @Step("Verifying Last Payment Amount")
    private LocationPaymentPage verify_payment_last_payment_Amount(String expectedLastPaymentAmount) {
        logger.info("Verifying Last payment Amount");
        verify_element_attribute_value(this.lastPaymentAmount, "placeholder", expectedLastPaymentAmount);
        return me();
    }

    @Step("Verifying Number Of Payment Paid")
    private LocationPaymentPage verify_payment_number_of_payment_paid(String expectedNoOfPaymentPaid) {
        logger.info("Verifying Number of payment paid");
        verify_element_attribute_value(this.noOfPaymentsPaid, "placeholder", expectedNoOfPaymentPaid);
        return me();
    }

    @Step("Verifying Total Amount Paid")
    private LocationPaymentPage verify_payment_total_amount_paid(String expectedTotalAmountPaid) {
        logger.info("Verifying Total amount paid");
        verify_element_attribute_value(this.totalAmountPaid, "placeholder", expectedTotalAmountPaid);
        return me();
    }

    @Step("Verifying G/L Department")
    private LocationPaymentPage verify_payment_gl_department(String expectedGlDepartment) {
        logger.info("Verifying GL Department");
        verify_element_attribute_value(this.glDepartment, "placeholder", expectedGlDepartment);
        return me();
    }

    @Step("Verifying G/L Account")
    private LocationPaymentPage verify_payment_gl_account(String expectedGlAmount) {
        logger.info("Verifying GL Department");
        verify_element_attribute_value(this.glAccount, "placeholder", expectedGlAmount);
        return me();
    }

    @Step("Verifying Cancellation Reason")
    private LocationPaymentPage verify_payment_cancellation_reason(String expectedCancellationReason) {
        logger.info("Verifying Cancellation Reason");
        verify_element_by_text(this.cancellationReason, expectedCancellationReason);
        return me();
    }

    @Step("Verifying Beginning Balance")
    private LocationPaymentPage verify_payment_beginning_balance(String expectedBegBalance) {
        logger.info("Verifying Beginning Balance");
        verify_element_attribute_value(this.beginningBalance, "placeholder", expectedBegBalance);
        return me();
    }

    public void parseMyData(String str) {
        Map<String, String> newMap = new HashMap<>();
        logger.info(str);
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));
        if (myList.get(1).equalsIgnoreCase("R")) {
            newMap.put("type", "Recurring");
        }

        newMap.put("code", myList.get(3));
        newMap.put("description", myList.get(4));
        newMap.put("amount", myList.get(5));
        newMap.put("lastPaymentDate", myList.get(6));

        if (myList.get(2).equalsIgnoreCase("R"))
            mapReimbursement.add(newMap);
        else {
            mapDeduction.add(newMap);
        }
    }

    public Map<String, String> get_ded_reimburs_data(String clubNumber) {
        Map<String, String> ded_reimburs_map = new HashMap<>();

        Map<String, String> ded = ini.get(clubNumber);
        int index = 0;
        for (Map.Entry<String, String> entry : ded.entrySet()) {
            String dedReimburseData[] = entry.getValue().split(" ");
            if (dedReimburseData[0].equalsIgnoreCase("Pass")) {
                logger.info("This is a Pass status");
            } else if (dedReimburseData[1].equalsIgnoreCase("R")) {
                logger.info(entry.getValue());
                ded_reimburs_map.put("key" + index, parse_raw_data(entry.getValue().trim()));
                index++;
            }
        }
        return ded_reimburs_map;
    }

    public String parse_raw_data(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(" ")));
        for (int i = 0; i < myList.size(); i++) {
            if (i < 4) {
                stringBuffer.append(myList.get(i) + ",");
            }
        }
        myList.subList(0, 4).clear();
        StringBuffer myNewString = new StringBuffer();
        StringBuffer myFinalString = new StringBuffer();
        for (int j = myList.size() - 1; j >= 0; j--) {
            if (!(j == myList.size() - 1 || j == myList.size() - 2)) {
                myNewString.append(myList.get(j) + " ");
            } else {
                myFinalString.append("," + myList.get(j));
            }
        }
        String firstUpatedValue = myNewString.toString();
        stringBuffer = revers_description_details(firstUpatedValue.trim(), stringBuffer);
        String secondUpdatedValue = myFinalString.toString();
        stringBuffer = reverse_amount_date_details(secondUpdatedValue.trim(), stringBuffer);
        logger.info("chcek2:->" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public StringBuffer revers_description_details(String str, StringBuffer stringBuffer) {
        String str1[] = str.split(" ");
        for (int k = str1.length - 1; k >= 0; k--) {
            if (k == 0) {
                stringBuffer.append(str1[k]);
            } else {
                stringBuffer.append(str1[k] + " ");
            }
        }
        return stringBuffer;
    }

    public StringBuffer reverse_amount_date_details(String str, StringBuffer stringBuffer) {
        String str1[] = str.split(",");
        for (int k = str1.length - 1; k > 0; k--) {

            stringBuffer.append("," + str1[k]);

        }
        return stringBuffer;
    }

    public LocationPaymentPage verify_deduction_details() {

        if (mapDeduction.size() > 0) {

            for (int i = 0; i <= mapDeduction.size() - 1; ++i) {
                int finalValue = i;

                verify_deduction_reimburshment_item(mapDeduction.get(finalValue).get("type"), mapDeduction.get(finalValue).get("code"),
                        "$" + mapDeduction.get(finalValue).get("amount"), mapDeduction.get(finalValue).get("description"));

                click(get_payment_xpath(dataAbcId, mapDeduction.get(finalValue).get("type"), mapDeduction.get(finalValue).get("code"),
                        "$" + mapDeduction.get(finalValue).get("amount"), mapDeduction.get(finalValue).get("description")));

                try {
                    verify_all(
                            () -> verify_payment_type(mapDeduction.get(finalValue).get("type")),
                            () -> verify_payment_code(mapDeduction.get(finalValue).get("code") + " - " + mapDeduction.get(finalValue).get("description")),
                            () -> verify_payment_description(mapDeduction.get(finalValue).get("description")),
                            () -> verify_payment_amount("$" + mapDeduction.get(finalValue).get("amount"))
                    );
                } finally {
                    click_close_button();
                }
            }
        }
        return me();
    }

    public LocationPaymentPage verify_reimbursement_details() {

        if (mapReimbursement.size() > 0) {
            click_reimbursement_tab();

            for (int i = 0; i <= mapReimbursement.size() - 1; ++i) {
                int finalValue = i;

                verify_deduction_reimburshment_item(mapReimbursement.get(finalValue).get("type"), mapReimbursement.get(finalValue).get("code"),
                        "$" + mapReimbursement.get(finalValue).get("amount"), mapReimbursement.get(finalValue).get("description"));

                click(get_payment_xpath(dataAbcId, mapReimbursement.get(finalValue).get("type"), mapReimbursement.get(finalValue).get("code"),
                        "$" + mapReimbursement.get(finalValue).get("amount"), mapReimbursement.get(finalValue).get("description")));

                try {
                    verify_all(
                            () -> verify_payment_type(mapReimbursement.get(finalValue).get("type")),
                            () -> verify_payment_code(mapReimbursement.get(finalValue).get("code") + " - " + mapReimbursement.get(finalValue).get("description")),
                            () -> verify_payment_description(mapReimbursement.get(finalValue).get("description")),
                            () -> verify_payment_amount("$" + mapReimbursement.get(finalValue).get("amount"))
                    );
                } finally {
                    click_close_button();
                }

            }
        }
        return me();
    }

    @Step("Verifying Location Payment details")
    public LocationPaymentPage verify_payment_details(String clubNumber) {

        Map<String, String> paymentMap = get_ded_reimburs_data(clubNumber);
        for (Map.Entry<String, String> entry : paymentMap.entrySet()) {
            parseMyData(entry.getValue());
        }

        logger.info("Verifying location payment details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_payment_tab();
        verify_deduction_details();
        verify_reimbursement_details();
        return me();
    }
}

