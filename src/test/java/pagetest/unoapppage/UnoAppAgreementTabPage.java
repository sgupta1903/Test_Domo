package pagetest.unoapppage;

/*
Created By: Shilpi Gupta
Updated By:
Date: 09/19/2018
*/

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
@Slf4j
public class UnoAppAgreementTabPage extends AbcCommonAbstractPage<UnoAppAgreementTabPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);

    protected By agreementTab = By.xpath("//div[text()= 'Agreement']");
    protected By agreementInformation = By.xpath("//div/h3[text() = 'Agreement Information']");
    protected By agreementStatus = By.xpath("//div[@data-abc-id = 'agreementStatus'][contains(.,'Status: Ok')]");

    public UnoAppAgreementTabPage select_agreement_tab() {
        log.info("Selecting agreement tab");
        verify(presenceOfElementLocated(agreementTab), 60, 2000);
        click(agreementTab);
        return me();
    }

    public UnoAppAgreementTabPage verify_agreement_information_presence() {
        log.info("Verifying that agreement information is present");
        verify(presenceOfElementLocated(agreementInformation));
        return me();
    }

    public UnoAppAgreementTabPage verify_agreement_status_ok() {
        log.info("Verifying that agreement status as ok");
        verify(presenceOfElementLocated(agreementStatus));
        return me();
    }
}
