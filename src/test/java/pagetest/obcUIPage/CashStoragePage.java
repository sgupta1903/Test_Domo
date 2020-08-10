package pagetest.obcUIPage;

import config.EnvProperty;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;


@Slf4j
public class CashStoragePage extends AbcCommonAbstractPage<CashStoragePage> {

    EnvProperty envProperty = EnvProperty.getInstance(GREEN_SCREEN_INI);

    private By abcRateTab = By.xpath(".//li[./i[contains(@class, 'money-bill-wave')]]");
    private By abcRatesDropdown = By.xpath("//div[@data-abc-id='ABCRatesOption']");
    private By clubBeginDate = By.xpath("//span[@data-abc-id='typeText']//following::td[1]");
    private By memberBeginDate = By.xpath("//span[@data-abc-id='typeText']//following::td[2]");
    private By perTransactionAmount = By.xpath("//span[@data-abc-id='typeText']//following::td[3]");
    private By locationBtn = By.xpath("//i[@data-abc-id='buildingIcon']");
    private By searchLocation = By.xpath("//input[@id='searchInput']");
    private String locNo1 = "//td[contains(text(),'";
    private String locNo2 = "')]";
    private By orgSwitcher = By.xpath("//div[@id='orgSwitcher']");
    private By orgSwitcherClearIcon = By.xpath("//i[@data-abc-id='organizationSwitcherInputIconAfter']");


    private String get_converted_date(String acutalFormattedDate) {
        String actualDate = null;
        String finalExpectedDate = null;
        int length = acutalFormattedDate.length();
        if (length == 5) {
            actualDate = "0" + acutalFormattedDate;
            String month = actualDate.substring(0, 2);
            String day = actualDate.substring(2, 4);
            String year = "20" + actualDate.substring(4, 6);
            finalExpectedDate = month + "/" + day + "/" + year;
        } else if (length == 6) {
            String month = acutalFormattedDate.substring(0, 2);
            String day = acutalFormattedDate.substring(2, 4);
            String year = "20" + acutalFormattedDate.substring(4, 6);
            finalExpectedDate = month + "/" + day + "/" + year;
        } else {
            finalExpectedDate = acutalFormattedDate;
        }
        return finalExpectedDate;
    }

    public CashStoragePage click_org_switcher_clear_icon(){
        click(orgSwitcherClearIcon);
        return me();
    }

    @Step("Click on Location")
    private CashStoragePage click_location(String clubNumber) {
        logger.info("Searching for Migrated Location in the Search Bar");
        String[] finalClubNumber = clubNumber.split("_");
        enter(searchLocation, finalClubNumber[0]);
        logger.info("Clicking on Migrated Location");
        String location = locNo1 + finalClubNumber[0] + locNo2;
        wait_until(1);
        click(By.xpath(location));
        return me();
    }

    @Step("Click on Abc Rate Tab")
    private CashStoragePage click_abc_rate_tab() {
        logger.info("Click on Abc Rate Tab");
        click(abcRateTab);
        return me();
    }

    @Step("Verifying Club Begin Date")
    private CashStoragePage verify_club_begin_date(String clubBeginDate) {
        logger.info("Verifying club begin date");
        verify_value_matches(find_element_text(this.clubBeginDate), clubBeginDate);
        return me();
    }

    @Step("Verifying Member Begin Date")
    private CashStoragePage verify_member_begin_date(String memberBeginDate) {
        logger.info("Verifying member begin date");
        verify_value_matches(find_element_text(this.memberBeginDate), memberBeginDate);
        return me();
    }

    @Step("Verifying Per Transaction amount")
    private CashStoragePage verify_per_transaction_amount(String perTransactionAmount) {
        logger.info("Verifying per transaction amount");
        verify_value_matches(find_element_text(this.perTransactionAmount), perTransactionAmount);
        return me();
    }

    @Step("Verifying cash storage details")
    public CashStoragePage verify_cash_storage_details(String clubNumber) {
        logger.info("Verifying cash storage details");
        click(locationBtn);
        click_location(clubNumber);
        wait_until(2);
        click_abc_rate_tab();
        click(abcRatesDropdown);
        enter_by_action(abcRatesDropdown, CASH_STORAGE_FEE);
        enter_by_key();
        wait_until(3);
        logger.info(clubNumber+"club number ++++++++");
        logger.info(envProperty.getConfigPropertyValue(clubNumber, CLUB_BEGIN_DATE)+"++++++++++");
        try {
            verify_all(
                    () -> verify_club_begin_date(get_converted_date(envProperty.getConfigPropertyValue(clubNumber, CLUB_BEGIN_DATE))),
                    () -> verify_member_begin_date(get_converted_date(envProperty.getConfigPropertyValue(clubNumber, MEMBER_BEGIN_DATE))),
                    () -> verify_per_transaction_amount("$0" + envProperty.getConfigPropertyValue(clubNumber, CASH_CHARGE))
            );
        }
        finally{
            clearing_organisation();
        }
        return me();
    }
    @Step("Clearing Last organisation tested from organisation search box")
    public CashStoragePage clearing_organisation() {
        click_org_switcher_clear_icon();
        return me();
    }
}
