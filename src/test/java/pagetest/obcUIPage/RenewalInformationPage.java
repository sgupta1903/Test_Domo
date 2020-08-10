package pagetest.obcUIPage;

import datastore.StoreRenewalDetails;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static helper.AppConstants.*;
import static util.UtilityGeneric.convertObjectIntoJsonString;

public class RenewalInformationPage extends AbcCommonAbstractPage<RenewalInformationPage> {
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    public  StoreRenewalDetails storeRenewalDetails = new StoreRenewalDetails();

    private By autoRenewFlag = By.xpath("//td[contains(text(),'Auto-Renew:')]/following-sibling::td[1]");
    private By renewalInformationLink = By.xpath("//a[contains(text(),'Renewal Information')]");
    private By renewType = By.xpath("//select[@id='f_autoRenew']");
    private By renewalDate = By.xpath("//input[@id='f_renewalDate']");
    private By renewalAmount = By.xpath("//input[@id='f_couponAmount']");
    private By noOfPayments = By.xpath("//input[@id='f_numPayments']");


    public StoreRenewalDetails store_renew_flag_and_information() {
        logger.info("Getting the Renew Flag Information  from OBC UI");
        storeRenewalDetails.setRenewalFlag(find_element_text(autoRenewFlag).trim());
        if (storeRenewalDetails.getRenewalFlag().equalsIgnoreCase("Y"))// selfenv.getConfigPropertyValue(AppConstants.MEMBER_RENEW_DETAILS, RENEWAL_FLAG).equals("Y"))
        {
            logger.info("Getting Renewal Information from OBC UI Application");
            click(renewalInformationLink);
            storeRenewalDetails.setRenewalType(find_element_value(renewType));
            String renewalType = storeRenewalDetails.getRenewalType();
            switch (renewalType) {
                case "O":
                    storeRenewalDetails.setRenewalType(AUTO_RENEW_TO_OPEN);
                    storeRenewalDetails.setRenewalDate(find_element_value(renewalDate));
                    storeRenewalDetails.setRenewalAmount("$" + find_element_value(renewalAmount).replaceAll(",", ""));
                    break;
                case "N":
                    storeRenewalDetails.setRenewalType(NON_RENEWAL_AGREEMENT);
                    break;
                case "T":
                    storeRenewalDetails.setRenewalType(AUTO_RENEW_TO_TERM);
                    storeRenewalDetails.setNoOfPayments(find_element_value(noOfPayments));
                    storeRenewalDetails.setRenewalDate(find_element_value(renewalDate));
                    storeRenewalDetails.setRenewalAmount("$" + find_element_value(renewalAmount).replaceAll(",", ""));
                    break;
            }
        }
        logger.info(BLUE_UNDERLINED + "*********************Renewal Details are mentioned below*******************************" + ANSI_RESET);
        logger.info(String.valueOf(storeRenewalDetails));
        logger.info(convertObjectIntoJsonString(storeRenewalDetails));
        wait_until(2);
        return storeRenewalDetails;
    }
}