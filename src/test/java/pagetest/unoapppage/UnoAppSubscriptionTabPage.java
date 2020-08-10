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
public class UnoAppSubscriptionTabPage extends AbcCommonAbstractPage<UnoAppSubscriptionTabPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    protected By subscriptionTab = By.xpath("//div[contains(text(),'Subscriptions')]");
    protected String subscriptionFrequency = "//p[@data-abc-id = 'frequency'][text() = '%s']";
    protected String subscriptionAmenity = "//p[@data-abc-id = 'profitCenter'][text() = '%s']";
    protected String subscriptionType = "//p[@data-abc-id = 'type'][text() = '%s']";


    public By get_subscription_frequency() {
        log.info("Getting payment frequency");
        return By.xpath(String.format(subscriptionFrequency, get_subscription_test_data(get_section_name(), "payFrequency")));
    }


    public By get_subscription_amenity() {
        log.info("Getting subscription amenity");
        return By.xpath(String.format(subscriptionAmenity, env.getConfigPropertyValue("item", "itemName")));
    }

    public By get_subscription_type() {
        log.info("Getting subscription type");
        return By.xpath(String.format(subscriptionType, get_subscription_test_data(get_section_name(), "subType")));
    }

    public UnoAppSubscriptionTabPage select_subscription_tab() {
        log.info("Selecting subscription tab");
        verify(presenceOfElementLocated(subscriptionTab), 60, 2000);
        click(subscriptionTab);
        return me();
    }

    public UnoAppSubscriptionTabPage verify_subscription_frequency() {
        log.info("Verifying subscription frequency");
        verify(presenceOfElementLocated(get_subscription_frequency()), 60, 2000);
        return me();
    }

    public UnoAppSubscriptionTabPage verify_subscription_amenity() {
        log.info("Verifying subscription amenity");
        verify(presenceOfElementLocated(get_subscription_amenity()), 60, 2000);
        return me();
    }

    public UnoAppSubscriptionTabPage verify_subscription_type() {
        log.info("Verifying subscription type");
        verify(presenceOfElementLocated(get_subscription_type()), 60, 2000);
        return me();
    }
}
