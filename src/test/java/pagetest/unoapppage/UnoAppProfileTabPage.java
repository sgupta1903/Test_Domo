package pagetest.unoapppage;

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppProfileTabPage extends AbcCommonAbstractPage<UnoAppProfileTabPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);

    protected By profileTab = By.xpath("//div[contains(text(),'Profile')]");
    protected By personalInformationSection = By.xpath("//h3[contains(text(),'Personal Information')]");
    protected By contactInformationSection = By.xpath("//h3[contains(text(),'Contact Information')]");

    public UnoAppProfileTabPage select_profile_tab() {
        log.info("Selecting profile tab");
        verify(presenceOfElementLocated(profileTab), 60, 2000);
        click(profileTab);
        return me();
    }

    public UnoAppProfileTabPage verify_personal_info_section() {
        log.info("Verifying personal information section presence");
        verify(presenceOfElementLocated(personalInformationSection), 60, 2000);
        return me();
    }

    public UnoAppProfileTabPage verify_contact_info_section() {
        log.info("Verifying contact information section presence");
        verify(presenceOfElementLocated(contactInformationSection), 60, 2000);
        return me();
    }

}
