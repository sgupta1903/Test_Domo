package pagetest.unoapppage;
/*
Created By: Shilpi Gupta
Updated By:
Jira Ticket:
Date: 09/19/2018
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UtilityGeneric.get_section_name;

@Slf4j
public class UnoAppWalletTabPage extends AbcCommonAbstractPage<UnoAppWalletTabPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    protected By walletTab = By.xpath("//div[contains(text(),'Wallet')]");
    protected By payMethodTablePlusIcon = By.xpath("//i[@data-abc-id = 'paymentMethodTablePlusIcon']");
    protected By transactionTypeInvoice = By.xpath("//td[@data-abc-id = 'transactionType'][text() = 'Invoice']");
    protected By transactionTypeStatement = By.xpath("//td[@data-abc-id = 'transactionType'][text() = 'Statement']");
    protected By referenceNumber = By.xpath("//td[@data-abc-id = 'referenceNumber']");
    protected String referenceNumberText = "//tr[@data-abc-id = 'paymentMethodTableRowRow']['%s']//td[@data-abc-id = 'referenceNumber']";
    protected String walletPaymentMethod = "//time[text() = '%s']";
    protected String walletAccountHolder = "//time[text() = '%s']";
    protected String createdDate = "//td[@data-abc-id = 'date'][text() = '%s']";

    public By get_payment_method(String type) {
        log.info("Getting payment method");
        if (type.equals("card")) {
            return By.xpath(String.format(walletPaymentMethod, "Credit Card"));
        } else {
            return By.xpath(String.format(walletPaymentMethod, get_subscription_test_data(get_section_name(), "payMethod")));
        }

    }

    public By get_created_date(String date) {
        log.info("Getting payment frequency");
        return By.xpath(String.format(createdDate, date));
    }

    public By get_reference_number(String position) {
        log.info("Getting reference number");

        return By.xpath(String.format(referenceNumberText, position));
    }

    public By get_account_holder_name(String type) {
        log.info("Getting account holder name");
        if (type.equals("card")) {
            return By.xpath(String.format(walletAccountHolder, get_subscription_test_data(get_section_name(), "cardHolderName")));
        } else {
            return By.xpath(String.format(walletAccountHolder, get_subscription_test_data(get_section_name(), "accountHolderName")));
        }
    }

    public UnoAppWalletTabPage select_wallet_tab() {
        log.info("Selecting Wallet tab");
        verify(presenceOfElementLocated(walletTab), 60, 2000);
        click(walletTab);
        return me();
    }

    public UnoAppWalletTabPage verify_wallet_payment_method_bank() {
        log.info(" Verifying wallet payment method");
        verify(presenceOfElementLocated(get_payment_method("bank")), 60, 2000);
        return me();
    }

    public UnoAppWalletTabPage verify_wallet_payment_method_card() {
        log.info(" Verifying wallet payment method");
        verify(presenceOfElementLocated(get_payment_method("card")), 60, 2000);
        return me();
    }

    public UnoAppWalletTabPage verify_wallet_bank_account_holder_name() {
        log.info("Verifying wallet account holder name");
        verify(presenceOfElementLocated(get_account_holder_name("bank")), 60, 2000);
        return me();
    }

    public UnoAppWalletTabPage verify_wallet_card_account_holder_name() {
        log.info("Verifying wallet account holder name");
        verify(presenceOfElementLocated(get_account_holder_name("card")), 60, 2000);
        return me();
    }

    public UnoAppWalletTabPage verify_invoice_generated() {
        log.info("Verifying wallet account invoice generation");
        click_plus_icon();
        wait_until(2);
        verify_invoice_displayed();
        verify_statement_displayed();
        verify_reference_number_displayed();
        return me();

    }

    public UnoAppWalletTabPage click_plus_icon() {
        log.info("Clicking on plus icon to get invoice data");
        wait_until(2);
        verify(presenceOfElementLocated(payMethodTablePlusIcon));
        click(payMethodTablePlusIcon);
        return me();
    }

    public UnoAppWalletTabPage verify_invoice_displayed() {
        log.info("Verify that invoices are displayed");
        verify(presenceOfElementLocated(transactionTypeInvoice));

        return me();
    }

    public UnoAppWalletTabPage verify_statement_displayed() {
        log.info("Verify that statements are displayed");
        verify(presenceOfElementLocated(transactionTypeStatement));
        return me();
    }

    public UnoAppWalletTabPage verify_reference_number_displayed() {
        log.info("Verify that reference number is displayed");
        verify(presenceOfElementLocated(referenceNumber));

        return me();
    }

    public UnoAppWalletTabPage verify_reference_number_not_null() {
        log.info("Cverify reference number is not null");
        for (int i = 0; i <= find_element_count(referenceNumber); i++) {
            find_element_text(get_reference_number(String.valueOf(i + 1)));
        }
        return me();
    }

    public UnoAppWalletTabPage verify_created_date() {
        log.info("Verifying created date for dummy data");
        verify(presenceOfElementLocated(
                get_created_date(env.getConfigPropertyValue("INVOICEVERIFICATION", "createdDate"))));

        return me();
    }
}