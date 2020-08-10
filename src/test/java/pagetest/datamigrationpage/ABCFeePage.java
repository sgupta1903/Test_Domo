package pagetest.datamigrationpage;

import config.EnvProperty;
import helper.DataBaseHandler;
import io.qameta.allure.Step;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.io.IOException;
import java.util.*;

import static helper.AppConstants.*;
import static helper.AppConstants.EXPIRATION_YEAR_PREFIX;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static util.UtilityGeneric.getFormatedDate;
import static util.UtilityGeneric.get_converted_date;

//Created By Monika Phoughat
//Date : 28-10-2019

public class ABCFeePage extends AbcCommonAbstractPage<ABCFeePage> {
    EnvProperty envProperty = EnvProperty.getInstance(CASH_STORAGE_INI);
    Ini ini = new Wini(this.getClass().getResourceAsStream('/' + "cashstorage.ini"));
    List<Map<String, String>> feelistMap = new ArrayList<>();
    private String finalStartDate;
    static String feeType;
    private By feeTab = By.xpath("//i[@data-abc-id='feeTitleIcon']");
    private By feeCount = By.xpath("//td[@data-abc-id='type']");
    private String feeType1 = "//td[contains(text(),'";
    private String feeType2 = "')]";
    private String startDate1 = "//td[contains(text(),'";
    private String startDate2 = "')]/following-sibling::td[1]";
    private String flatAmount1 = "//td[contains(text(),'";
    private String flatAmount2 = "')]/following-sibling::td[5]";
    private String dataAbcId = "feesListRow";
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private String feeRowXpath = "//tr[@data-abc-id='feesListRow']/td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']";
    private String feeRowXpathWithoutGraceDays = "//tr[@data-abc-id='feesListRow']/td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']/../../td/span[text()='%s']//../../td/span[text()='%s']/../../td/span[text()='%s']";

    public ABCFeePage() throws IOException {
    }

    public By get_fee_row_xpath(String dataAbcId, String feeType, String startDate, String assessFee, String draftFee, String graceDays, String feeAmount, String feePercentage) {
        return By.xpath(String.format(feeRowXpath, feeType, startDate, assessFee, draftFee, graceDays, feeAmount, feePercentage));
    }

    @Step("Verifying Fee Details")
    public ABCFeePage verify_abc_fee_details(String feeType, String startDate, String assessFee, String draftFee, String graceDays, String feeAmount, String feePercentage) {
        verify(visibilityOfAllElementsLocatedBy(get_fee_row_xpath(dataAbcId, feeType, startDate, assessFee, draftFee, graceDays, feeAmount, feePercentage)));
        return me();
    }

    public By get_fee_row_xpath_without_grace(String dataAbcId, String feeType, String startDate, String assessFee, String draftFee, String feeAmount, String feePercentage) {
        return By.xpath(String.format(feeRowXpathWithoutGraceDays, feeType, startDate, assessFee, draftFee, feeAmount, feePercentage));
    }

    @Step("Verifying Fee Details with blank grace days")
    public ABCFeePage verify_abc_fee_details_without_grace(String feeType, String startDate, String assessFee, String draftFee, String feeAmount, String feePercentage) {
        verify(visibilityOfAllElementsLocatedBy(get_fee_row_xpath_without_grace(dataAbcId, feeType, startDate, assessFee, draftFee, feeAmount, feePercentage)));
        return me();
    }

    @Step("Click on Location")
    private ABCFeePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    public ABCFeePage click_fee_tab() {
        logger.info("Clicking on ABC Fee Tab");
        verify(elementToBeClickable(feeTab), 60, 2000);
        wait_until(2);
        return click(feeTab);
    }

    public int get_fee_count() {
        int rateCount = find_element_count(this.feeCount);
        return rateCount;
    }

    public By get_fee_type() {
        String abcFeeType = feeType1 + feeType + feeType2;
        return new By.ByXPath(abcFeeType);
    }

    public By get_start_date() {
        String startDate = startDate1 + feeType + startDate2;
        return new By.ByXPath(startDate);
    }

    public By get_fee_amount() {
        String feeAmount = flatAmount1 + feeType + flatAmount2;
        return new By.ByXPath(feeAmount);
    }

    public ABCFeePage verify_fee_type(String feeType) {
        logger.info("Verifying Fee Type");
        verify_value_matches(find_element_text(get_fee_type()).toUpperCase() + "_FEE", feeType);
        return me();
    }

    public ABCFeePage verify_start_date(String startDate) {
        logger.info("Verifying Start Date");
        startDate = startDate.substring(0, 10);
        startDate = getFormatedDate(startDate);
        startDate = startDate.replace("-", "/");
        verify_value_matches(find_element_text(get_start_date()), startDate);
        return me();
    }

    public ABCFeePage verify_fee_amount(String feeAmount) {
        logger.info("Verifying Fee Amount");
        feeAmount = feeAmount.substring(0, 0);
        verify_value_matches(find_element_text(get_fee_amount()).substring(1, 1), feeAmount);
        return me();
    }

    public ABCFeePage verify_fee_details(Map feeInfo) {
        logger.info("Verifying Fee Details");
        int count = get_fee_count();
        for (int i = 1; i <= count; i++) {
            if (feeInfo.get("fee_type").toString().substring(1, 4).toLowerCase().equals(find_element_text(get_fee_type()).substring(1, 4))) {
            }
            verify_fee_type(feeInfo.get("fee_type").toString());
            verify_start_date(feeInfo.get("start_date").toString());
            verify_fee_amount(feeInfo.get("fee_amount").toString());
        }
        return me();
    }

    public ABCFeePage click_abc_fee_tab() {
        click_fee_tab();
        return me();
    }

    public ABCFeePage verify_fee_details(String orgName, DataBaseHandler dataBaseHandler, List member_config_fee_id) {
        for (int i = 0; i < member_config_fee_id.size(); i++) {
            CentralDBPage centralDBPage = new CentralDBPage();
            Map<String, String> feeInfo = centralDBPage.getFeeData(member_config_fee_id.get(i).toString(), dataBaseHandler);
            feeType = feeInfo.get("fee_type").substring(1, 4).toLowerCase();
            verify_fee_details(feeInfo);
        }
        return me();
    }

    public void get_abc_fee_data(String clubNumber) {

        Map<String, String> abcFeeData = ini.get(clubNumber);
        String finalData = null;
        for (Map.Entry<String, String> entry : abcFeeData.entrySet()) {
            String dedData[] = entry.getValue().split("\t");
            if ("Pass".equalsIgnoreCase(dedData[0])) {
                System.out.println("This is a status");
            } else {

                String clubNumbers = entry.getValue().substring(1, 5).trim();
                String fees = entry.getValue().substring(6, 18).trim();
                String date = entry.getValue().substring(19, 32).trim();
                String accessFee = entry.getValue().substring(33, 38).trim();
                String chargeFee = entry.getValue().substring(39, 45).trim();
                String graceDays = entry.getValue().substring(46, 53).trim();
                String feeAmount = entry.getValue().substring(54, 63).trim();
                if (feeAmount.equalsIgnoreCase(".00")) {
                    feeAmount = "$0" + feeAmount;
                } else {
                    feeAmount = "$" + feeAmount;
                }
                //String feePercentage = entry.getValue().substring(64, 69).trim();
                String feePercentage = entry.getValue().substring(64, 68).trim();
                if (feePercentage.equalsIgnoreCase(".00")) {
                    feePercentage = "0" + feePercentage;
                }
                if (graceDays.isEmpty()) {
                    graceDays = "empty";
                }
                finalData = clubNumbers + "," + fees + "," + date + "," + accessFee + "," + chargeFee + "," + graceDays + "," + feeAmount + "," + feePercentage;
                parse_my_data(finalData);
            }
        }
    }

    public void parse_my_data(String str) {
        Map<String, String> newMap = new HashMap<>();
        logger.info(str);
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));

        newMap.put("clubNumbers", myList.get(0));
        newMap.put("feeType", myList.get(1));
        if (myList.get(1).equalsIgnoreCase("Late")) {
            newMap.put("feeType", "Late");
        } else if (myList.get(1).equalsIgnoreCase("Conv")) {
            newMap.put("feeType", "Convenience");
        } else if (myList.get(1).equalsIgnoreCase("NSF")) {
            newMap.put("feeType", "Service");
        }
        newMap.put("startDate", myList.get(2));

        String assessFee = myList.get(3);
        String draftFee = myList.get(4);
        if ((newMap.get("feeType").equalsIgnoreCase("Late") || newMap.get("feeType").equalsIgnoreCase("service"))) {
            switch (assessFee) {
                case "Y":
                    newMap.put("assessFee", "Yes");
                    newMap.put("draftFee", "Yes");
                    break;
                case "N":
                    newMap.put("assessFee", "No");
                    newMap.put("draftFee", "No");
            }
        } else if (newMap.get("feeType").equalsIgnoreCase("Convenience")) {

            switch (assessFee) {
                case "Y":
                    newMap.put("assessFee", "Yes");
                    break;
                case "N":
                    newMap.put("assessFee", "No");
            }
            switch (draftFee) {
                case "Y":
                    newMap.put("draftFee", "Yes");
                    break;
                case "N":
                    newMap.put("draftFee", "No");
            }
        }
        newMap.put("graceDays", myList.get(5));
        newMap.put("feeAmount", myList.get(6));
        newMap.put("feePercentage", myList.get(7));
        feelistMap.add(newMap);
    }

    public String get_actual_formatted_date(String date) {
        for (int i = 0; i < feelistMap.size() - 1; ++i) {
            String[] startDateWithYear = date.split("/");
            if (startDateWithYear[0].length() < 2) {
                finalStartDate = "0" + startDateWithYear[0] + "/" + startDateWithYear[1] + "/" + "20" + startDateWithYear[2];
            } else {
                finalStartDate = startDateWithYear[0] + "/" + startDateWithYear[1] + "/" + "20" + startDateWithYear[2];
            }

            if (startDateWithYear[0].length() < 2 && Integer.parseInt(startDateWithYear[2]) >= 79 && Integer.parseInt(startDateWithYear[2]) <= 99) {
                finalStartDate = "0" + startDateWithYear[0] + "/" + startDateWithYear[1] + "/" + "19" + startDateWithYear[2];
            } else if (startDateWithYear[0].length() <= 2 && Integer.parseInt(startDateWithYear[2]) >= 60 && Integer.parseInt(startDateWithYear[2]) <= 99) {
                finalStartDate = startDateWithYear[0] + "/" + startDateWithYear[1] + "/" + "19" + startDateWithYear[2];
            }

        }
        return finalStartDate;
    }

    //GreenScreen Data Validation for Abc Fee
    public ABCFeePage verify_abc_fee_details(String clubNumber) {
        get_abc_fee_data(clubNumber);
        logger.info("Verifying Banking Details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_fee_tab();

        for (int i = 0; i < feelistMap.size() - 1; ++i) {

            String finalStartDate = get_actual_formatted_date(feelistMap.get(i).get("startDate"));

            if (feelistMap.get(i).get("feeType").equalsIgnoreCase("Unauth")) {
                logger.info("Unauthorized Fee doesn't exist on page");
            } else {
                if (feelistMap.get(i).get("graceDays").equalsIgnoreCase("empty")) {
                    verify_abc_fee_details_without_grace(feelistMap.get(i).get("feeType"), finalStartDate, feelistMap.get(i).get("assessFee"), feelistMap.get(i).get("draftFee"), feelistMap.get(i).get("feeAmount"), feelistMap.get(i).get("feePercentage") + "%");
                } else {
                    verify_abc_fee_details(feelistMap.get(i).get("feeType"), finalStartDate, feelistMap.get(i).get("assessFee"), feelistMap.get(i).get("draftFee"), feelistMap.get(i).get("graceDays"), feelistMap.get(i).get("feeAmount"), feelistMap.get(i).get("feePercentage") + "%");
                }
            }
        }
        return me();
    }
}

