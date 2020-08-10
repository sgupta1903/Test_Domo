package pagetest.paymentgatewaypage;

import datastore.StoreMemberDetails;
import datastore.StorePaymentMethodDetails;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
//import static pagetest.obcUIPage.AccountSummaryPage.storeMemberDetails;
//import static pagetest.obcUIPage.AccountSummaryPage.storePaymentMethodDetails;

@Slf4j
public class CompanyTabPage extends AbcCommonAbstractPage<CompanyTabPage> {
    private String expMonth, expYear;
    private By configLink = By.xpath("//i[@data-abc-id='cogsIcon']");
    private By addCompBtn = By.xpath("//span[text() = 'Add Company']");
    private By searchCompany = By.xpath("//div[@data-abc-id='contextSwitcherInput']//input");
    private By companyName = By.xpath("//input[@id='name']");
    private By compShortName = By.xpath("//input[@id='shortCode']");
    private By savetBtn = By.xpath("//span[@data-abc-id = 'submitCompanyForm']");
    private By tokenTab = By.xpath("//div[contains(text(),'Tokens')]");
    private By creditCardRadioBtn = By.xpath("//label[contains(text(),'Credit Card')]");
    private By bankAccountRadioBtn = By.xpath("//label[contains(text(),'Bank Account')]");
    private By searchTokenField = By.xpath("//input[contains(@id,'TokenSearchInput')]");
    private String tokenRowInfoCreditCard = "//tr[@data-abc-id='cardTokensListRow'][td[normalize-space()='";
    private String tokenRowInfoBankAccount = "//tr[@data-abc-id='bankTokensListRow'][td[normalize-space()='";
    private String tokenRowInfo2 = "']][last()]";
    private By closeButton = By.xpath("//i[@data-abc-id='closeDrawerButtonIcon']");
    //Bank Account
    private By routingNumber = By.xpath("//input[@id='routingNumber']");
    private By accountNumberLastFour = By.xpath("//input[@id='accountNumberLastFour']");
    private By bankAccountType = By.xpath("//input[@id='bankAccountTypeInput']");
    //Credit Card
    private By expirationYear = By.xpath("//input[@id='expirationYearInput']");
    private By expiryMonth = By.xpath("//input[@id='expirationMonthInput']");
    private By loadAllPayorsList = By.xpath("//td[@data-abc-id='loadingCell']");
    private By zipCode = By.xpath("//input[@id='postalCode']");

    public CompanyTabPage config_link() {
        log.info("Click on Config Link");
        wait_until(1);
        return click(configLink);
    }

    public CompanyTabPage enter_company_name(String name) {
        log.info("Entering the Company Name");
        wait_until(2);
        return enter(this.companyName, name);
    }

    public CompanyTabPage enter_comp_short_name(String shortName) {
        log.info("Entering the Company Name");
        wait_until(2);
        return enter(this.compShortName, shortName);
    }

    public CompanyTabPage click_save() {
        log.info("Click on Save Company Button");
        verify(elementToBeClickable(savetBtn), 60, 2000);
        click(savetBtn);
        return me();
    }

    public CompanyTabPage select_company(String dataSetName) {
        log.info(" Select state " + dataSetName + " data set");
        verify(presenceOfElementLocated(searchCompany), 60, 2000);
        wait_until(2);
        enter(searchCompany, dataSetName);
        wait_until(3);
        enter_by_key();
        return me();
    }

    public CompanyTabPage search_company(String companyName) {
        log.info("Searching existing Company");
        config_link();
        wait_until(1);
        select_company(companyName);
        return me();
    }

    /*This method is used for future when user able to create company */
    public CompanyTabPage add_company() {
        log.info("Creating Company");
        config_link();
        click(addCompBtn);
        enter_company_name("ABC Financial Billing Services");
        enter_comp_short_name("ABC");
        click_save();
        return me();
    }

    public CompanyTabPage verify_account_holder_name(String accountHolderName) {
        log.info("Verifying Account Holder Name");
        is_text_present_on_page(accountHolderName);
        return me();
    }

    public CompanyTabPage verify_routing_number(String routingNumber) {
        log.info("Verifying Routing Number");
        verify_element_by_value(this.routingNumber, routingNumber);
        return me();
    }

    public CompanyTabPage verify_account_number_last_four(String accountNumberLastFour) {
        log.info("Verifying Account Holder Last Four Digits");
        verify_element_by_value(this.accountNumberLastFour, accountNumberLastFour);
        return me();
    }

    public CompanyTabPage verify_credit_type(String creditType) {
        log.info("Verifying Credit Type");
        is_text_present_on_page(creditType.toLowerCase());
        return me();
    }

    public CompanyTabPage verify_expiry_year(String expiryYearPG) {
        log.info("Verifying Expiry Year");
        verify_element_attribute_value(expirationYear, "placeholder", expiryYearPG);
        return me();
    }

    public CompanyTabPage verify_expiry_month(String expiryMonthPG) {
        log.info("Verifying Expiry Month");
        verify_element_attribute_value(expiryMonth, "placeholder", expiryMonthPG);
        return me();
    }

    public CompanyTabPage verify_zip_code(StoreMemberDetails storeMemberDetails) {
        logger.info("Verifying Zip Code");
        if (storeMemberDetails.getMemberAddress().equalsIgnoreCase(BAD_ADDRESS)) {
            logger.info("Bad Address");
        } else
            verify_value_matches(find_element_value(zipCode).substring(0, 5), storeMemberDetails.getMemberZipCode().substring(0, 5));
        return me();
    }

    public CompanyTabPage click_token_credit_card(StorePaymentMethodDetails storePaymentMethodDetails) {
        log.info("Clicking Credit Card Token");
        String tokenRow = storePaymentMethodDetails.getPayorName();
        String token = tokenRowInfoCreditCard + tokenRow + tokenRowInfo2;
        wait_until(1);
        click(By.xpath(token));
        return me();
    }

    public CompanyTabPage click_token_bank_account(StorePaymentMethodDetails storePaymentMethodDetails) {
        log.info("Clicking bank Account Token");
        String tokenRow = storePaymentMethodDetails.getPayorName();
        String token = tokenRowInfoBankAccount + tokenRow + tokenRowInfo2;
        wait_until(1);
        click(By.xpath(token));
        return me();
    }

    public CompanyTabPage click_close_button() {
        log.info("Clicking on  Close Button");
        verify(elementToBeClickable(closeButton), 60, 2000);
        wait_until(2);
        return click(closeButton);
    }

    public CompanyTabPage verify_payment_details_obc(StorePaymentMethodDetails storePaymentMethodDetails,StoreMemberDetails storeMemberDetails) {
        log.info("Verifying Payment Details in Payment Gateway ");
        log.info("Clicking on token Tab");
        click(tokenTab);
        String paymentType = storePaymentMethodDetails.getPaymentType();
        switch (paymentType) {
            case CREDIT_CARD:
                log.info("Verifying Credit Card Details");
                String[] expirationDateWithYear = storePaymentMethodDetails.getExpirationDate().split("/");
                expYear = EXPIRATION_YEAR_PREFIX + expirationDateWithYear[1];
                int newExpMonth = Integer.parseInt(expirationDateWithYear[0]);
                if (newExpMonth >= 10) {
                    expMonth = String.valueOf(newExpMonth);
                } else
                    expMonth = expirationDateWithYear[0].substring(1);

                click(creditCardRadioBtn);
                enter(searchTokenField, storePaymentMethodDetails.getLastFour());
                wait_until(3);
                click(loadAllPayorsList);
                wait_until(1);
                click(loadAllPayorsList);
                click_token_credit_card(storePaymentMethodDetails);

                verify_all(
                        () -> verify_account_holder_name(storePaymentMethodDetails.getPayorName()),
                        () -> verify_expiry_year(expYear),
                        () -> verify_expiry_month(expMonth),
                        () -> verify_zip_code(storeMemberDetails)
                );
                click_close_button();
                break;

            case BANK_ACCOUNT:
                log.info("Verifying  Bank Account Details");
                wait_until(3);
                enter(searchTokenField, storePaymentMethodDetails.getLastFour());
                click(loadAllPayorsList);
                wait_until(1);
                click(loadAllPayorsList);
                click_token_bank_account(storePaymentMethodDetails);
                verify_all(
                        () -> verify_routing_number(storePaymentMethodDetails.getRoutingNumber()),
                        () -> verify_account_number_last_four(storePaymentMethodDetails.getLastFour()),
                        () -> verify_account_holder_name(storePaymentMethodDetails.getPayorName())
                );
                click_close_button();
                break;
            case NO_BILLING_INFO:
                log.info("No Billing Information Present");
                break;
        }
        return me();
    }
}
