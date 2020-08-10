package pagetest.obcUIPage;

import datastore.StoreSecondaryPaymentMethodDetails;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;
import static util.UtilityGeneric.convertObjectIntoJsonString;

public class SecondaryPaymentMethodPage extends AbcCommonAbstractPage<SecondaryPaymentMethodPage> {
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    public  StoreSecondaryPaymentMethodDetails storeSecondaryPaymentMethodDetails = new StoreSecondaryPaymentMethodDetails();
    // OBC
    private By autoPaymentUpdateLink = By.xpath("//a[contains(text(),'Auto-Payment Update')]");
    private By payMode = By.xpath("//select[@id='f_payMode']");
    private By firstType = By.xpath("//td[contains(text(),'Over')]//preceding::tr[5]//td[1]");
    private By creditCardNumber = By.xpath("//input[@id='f_ccNumber']");
    private By bankAccountNumber = By.xpath("//input[@id='f_accountNumber']");
    private By routingNumber = By.xpath("//input[@id='f_routingNumber']");
    private By noBillingInfo = By.xpath("//td[contains(text(),'Your current auto-payment information needs to be')]");
    private By couponType = By.xpath("//td[contains(text(),'Coupon accounts do not require auto-payment inform')]");
    private By creditCardType = By.xpath("//select[@id='f_ccType']");
    private By creditCardExpirationDate = By.xpath("//input[@id='f_ccExpDate']");
    private By accountHolderNameFirstName = By.xpath("//input[@id='f_acctFirstName']");
    private By accountHolderNameLastName = By.xpath("//input[@id='f_acctLastName']");


    public StoreSecondaryPaymentMethodDetails store_secondary_payment_method() {
        click(autoPaymentUpdateLink);
        if (is_element_exist(noBillingInfo) || is_element_exist(couponType)) {
            logger.info("No Secondary Payment Method");
        } else {
            click(autoPaymentUpdateLink);
            String firstPaymentType = find_element_text(firstType);
            if (firstPaymentType.contains(EFT)) {
                select_by_value(payMode, "2");
                storeSecondaryPaymentMethodDetails.setSecondaryCreditCardNumber(find_element_value(creditCardNumber).replaceAll("[^0-9]", ""));
                storeSecondaryPaymentMethodDetails.setSecondaryAccountHolder(find_element_value(accountHolderNameFirstName) + " " + find_element_value(accountHolderNameLastName));
                storeSecondaryPaymentMethodDetails.setSecondaryCreditCardType(find_element_value(creditCardType));
                storeSecondaryPaymentMethodDetails.setSecondaryCreditCardExpirationDate(find_element_value(creditCardExpirationDate));

            } else {
                select_by_value(payMode, "1");
                storeSecondaryPaymentMethodDetails.setSecondaryBankAccountNumber(find_element_value(bankAccountNumber).replaceAll("[^0-9]", ""));
                storeSecondaryPaymentMethodDetails.setSecondaryRoutingNumber(find_element_value(routingNumber));
                storeSecondaryPaymentMethodDetails.setSecondaryAccountHolder(find_element_value(accountHolderNameFirstName) + " " + find_element_value(accountHolderNameLastName));
            }
        }
        logger.info(BLUE_UNDERLINED+"************************Secondary Payment method details are mentioned below*********************"+ANSI_RESET);
        logger.info(String.valueOf(storeSecondaryPaymentMethodDetails));
        logger.info(convertObjectIntoJsonString(storeSecondaryPaymentMethodDetails));
        return storeSecondaryPaymentMethodDetails;
    }
}

