package pagetest.obcUIPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;



public class EnterClubPage extends AbcCommonAbstractPage<EnterClubPage> {
    protected Logger logger = LoggerFactory.getLogger( getClass().getSimpleName());
    private By enterClub = By.xpath( "//input[@name='f_clubID']" );
    private By submitBtn = By.xpath("//input[@type='submit']");

    public EnterClubPage enter_club_number(String enterClub){
        logger.info( "Entering the  Club Number" );
        return enter( this.enterClub, enterClub );
    }

    @Step("Enter [{clubNumber}] in club number field")
    public EnterClubPage enter_club_no(String clubNumber){
        logger.info( "Entering Club Number"+clubNumber );
        enter_club_number(clubNumber);
        click(submitBtn);
        return me();
    }
}
