package pagetest.obcUIPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

public class SelectMemberAccountPage extends AbcCommonAbstractPage<SelectMemberAccountPage> {
    protected Logger logger = LoggerFactory.getLogger( getClass().getSimpleName());

    private By enterMemberNumber = By.xpath( "//input[@id='f_memberID']" );

    public SelectMemberAccountPage enter_member_num(String enterMemberNumber){
        logger.info( "Entering the  Member Number" );
        return enter( this.enterMemberNumber, enterMemberNumber );
    }

    @Step("Enter member number")
    public SelectMemberAccountPage enter_member_number(String memberNumber){
        logger.info( "Entering Member Number->"+memberNumber );
        enter_member_num(memberNumber);
        enter_by_key();
        return me();
    }
}
