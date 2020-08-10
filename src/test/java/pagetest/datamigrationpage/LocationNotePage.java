package pagetest.datamigrationpage;

import io.qameta.allure.Step;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.io.IOException;
import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class LocationNotePage extends AbcCommonAbstractPage<LocationNotePage> {
    List<Map<String, String>> locationNoteMap = new ArrayList<>();

    private By loadLocationNoteList = By.xpath("//td[@data-abc-id='loadingCell']");
    private By locationName = By.xpath("//input[@id='noteLocationName']");
    private By code = By.xpath("//input[@id='locationActionCodeInput']");
    private By note = By.xpath("//div[@data-abc-id='locationActionCodeInput']//following::div[1]");
    private By locationNoteTab = By.xpath("//i[@data-abc-id='notesTitleIcon']");
    private By locationBtn = By.xpath("//a[@href='/uno-app/app/client-management']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";

    private String locationNoteXpath = "//tr[@data-abc-id='%s']/td//span[text()='%s']";
    private String dataAbcId = "notesListRow";
    Ini ini;

    {
        try {
            ini = new Wini(this.getClass().getResourceAsStream('/' + "cashstorage.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public By get_location_note_xpath(String dataAbcId, String note) {
        return By.xpath(String.format(locationNoteXpath, dataAbcId, note));
    }

    @Step("Verifying Deduction/Reimbursement item")
    public LocationNotePage verify_location_note_row(String note) {
        verify(visibilityOfAllElementsLocatedBy(get_location_note_xpath(dataAbcId, note)));
        return me();
    }

    private LocationNotePage verify_location_name(String expectedLocationName) {
        logger.info("Verifying location name");
        verify_element_by_value(locationName, expectedLocationName);
        return me();
    }


    @Step("Click on Location")
    private LocationNotePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    @Step("Click On Location Note Tab")
    private LocationNotePage click_location_note_tab() {
        logger.info("Clicking on Location note tab");
        click(locationNoteTab);
        return me();
    }

    private LocationNotePage verify_note(String expectedNote) {
        logger.info("Verifying location note");
        verify_element_by_value(note, expectedNote);
        return me();
    }

    private LocationNotePage verify_code(String expectedCode) {
        logger.info("Verifying location code");
        verify_element_by_value(code, expectedCode);
        return me();
    }

    public void parse_my_data(String str) {
        Map<String, String> newMap = new HashMap<>();
        logger.info(str);
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));

        newMap.put("clubNumbers", myList.get(0));
        newMap.put("date", myList.get(1));
        newMap.put("locationNote", myList.get(2));

        locationNoteMap.add(newMap);
    }


    public void get_lacation_note_details(String clubNumber) {
        String finalLocationNoteDetails = null;
        Map<String, String> locationNoteMap = ini.get(clubNumber);
        for (Map.Entry<String, String> entry : locationNoteMap.entrySet()) {
            String locationNoteData[] = entry.getValue().split("\t");
            if (locationNoteData[0].equalsIgnoreCase("Pass")) {
                logger.info("This is a status");
            } else {
                finalLocationNoteDetails = parse_raw_data(entry.getValue().trim());
                logger.info(finalLocationNoteDetails);
                parse_my_data(finalLocationNoteDetails);
            }
        }
    }

    public String parse_raw_data(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(" ")));
        for (int i = 0; i < myList.size(); i++) {
            if (i < 2) {
                stringBuffer.append(myList.get(i) + ",");
            }
        }
        myList.subList(0, 2).clear();
        StringBuffer myFinalString = new StringBuffer();
        for (int j = 0; j < myList.size(); ++j) {
            myFinalString.append(myList.get(j) + " ");

        }
        StringBuffer locationNotes = stringBuffer.append(myFinalString);
        return String.valueOf(locationNotes).trim();
    }

    public LocationNotePage verify_location_note_details(String clubNumber) {
        logger.info("Verifying location payment details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_location_note_tab();

        get_lacation_note_details(clubNumber);

        for (int i = 0; i < locationNoteMap.size(); ++i) {
            verify_location_note_row(locationNoteMap.get(i).get("locationNote"));
        }
        return me();
    }
}

