package pagetest.datamigrationpage;

import config.EnvProperty;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.OUTPUT_INI;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

//Created By Monika Phoughat
//Date : 14-09-2019
public class SearchClientProfilePage extends AbcCommonAbstractPage<SearchClientProfilePage> {

    EnvProperty envProperty = EnvProperty.getInstance(OUTPUT_INI);

    private By accountHolderName = By.xpath("//*[@id='Account-holder']");
    private By routingNumber = By.xpath("//*[@id='routing-Number']");
    private By lastFour = By.xpath("//*[@id='account-number']");
    //private By bankingTab = By.xpath("//ul/li[3]");
    private By bankingTab = By.xpath("//*[@data-abc-id='bankingTitleIcon']");
    private By accountType = By.xpath("//input[@id='account-typeInput']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By locationBtn = By.xpath("//i[@data-abc-id='buildingIcon']");
    private By orgSwitcherClearIcon = By.xpath("//i[@data-abc-id='organizationSwitcherInputIconAfter']");

    @Step("Click on Location")
    private SearchClientProfilePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }


    public SearchClientProfilePage click_banking_tab() {
        logger.info("Click on Banking Tab");
        verify(presenceOfElementLocated(bankingTab), 60, 2000);
        wait_until(2);
        return click(bankingTab);
    }

    public SearchClientProfilePage verify_accountHolderName(String expectedAccountHolderName) {
        logger.info("Verifying Account Holder Name");
        verify_element_by_value(accountHolderName, expectedAccountHolderName);
        return me();
    }

    public SearchClientProfilePage verify_routingNumber(String expectedRoutingNumber) {
        logger.info("Verifying Routing Number");
        verify_element_by_value(routingNumber, expectedRoutingNumber);
        return me();
    }

    public SearchClientProfilePage verify_lastFour(String expectedLastFour) {
        logger.info("Verifying Last Four Digit of Bank Account Number");
        verify_element_by_value(lastFour, expectedLastFour);
        return me();
    }

    public SearchClientProfilePage verify_account_type(String expectedAccountType) {
        logger.info("Verifying Account Type");
        verify_element_attribute_value(accountType, "placeholder", expectedAccountType);
        return me();
    }

    public SearchClientProfilePage verify_client_profile_details(Map clientProfileInfoDbMap) {
        logger.info("Verifying Client Profile Details");
        wait_until(2);
        click_banking_tab();

        verify_all(
                () -> verify_accountHolderName(clientProfileInfoDbMap.get("name").toString()),
                () -> verify_routingNumber(clientProfileInfoDbMap.get("routingNumber").toString()),
                () -> verify_lastFour(clientProfileInfoDbMap.get("lastFour").toString())
        );
        return me();
    }

    //GreenScreen Data Validation
    public SearchClientProfilePage verify_banking_details(String clubNumber) {
        String accountType = null;
        String accountHolderName=null;
        if (envProperty.getConfigPropertyValue(clubNumber, "Bank_Account_Type").equalsIgnoreCase("C")) {
            accountType = "Checking";
        } else if (envProperty.getConfigPropertyValue(clubNumber, "Bank_Account_Type").equalsIgnoreCase("S")) {
            accountType = "Savings";
        }
        if(envProperty.getConfigPropertyValue(clubNumber, "Account_Holder_Name").equalsIgnoreCase(""))
        {
            accountHolderName=envProperty.getConfigPropertyValue(clubNumber, "Location_Name");
        }
        else{
            accountHolderName=envProperty.getConfigPropertyValue(clubNumber, "Account_Holder_Name");
        }

        logger.info("Verifying Banking Details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_banking_tab();

        String finalAccountType = accountType;
        String finalAccountHolderName = accountHolderName;
        try {
            verify_all(
                    () -> verify_accountHolderName(finalAccountHolderName),
                    () -> verify_routingNumber(envProperty.getConfigPropertyValue(clubNumber, "Routing_Number")),
                    () -> verify_lastFour(envProperty.getConfigPropertyValue(clubNumber, "Account_Number").replaceAll("[*]", "")),
                    () -> verify_account_type(finalAccountType)
            );
        } finally {
            clearing_organisation();
        }
        return me();
    }
    @Step("Clearing Last organisation tested from organisation search box")
    public SearchClientProfilePage clearing_organisation() {
        click_org_switcher_clear_icon();
        return me();
    }
    public SearchClientProfilePage click_org_switcher_clear_icon(){
        click(orgSwitcherClearIcon);
        return me();
    }
}