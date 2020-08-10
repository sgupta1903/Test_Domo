package pagetest.obcUIPage;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class ObcAppLoginPage extends AbcCommonAbstractPage<ObcAppLoginPage> {
    protected Logger logger = LoggerFactory.getLogger( getClass().getSimpleName());

    private By username = By.xpath( "//input[contains(@id,'UserID')]" );
    private By password = By.xpath( "//input[contains(@id,'Password')]" );
    private By  loginBtn = By.xpath( "//input[@value='Login']" );
    private By homeBtn=By.xpath("//a[@class='f7whtL'][contains(text(),'Home')]");
    private String configUrl = "obcUrlProd";

    @Step("Enter [{username}] in user name field")
    public ObcAppLoginPage enter_user_name(String username){
        log.info( "Entering the User Name" );
        return enter( this.username, decode_content(username) );
    }

    @Step("Enter [{password}] in password field")
    public ObcAppLoginPage enter_user_password(String password){
        log.info( "Entering the Password" );
        return enter( this.password, decode_content(password) );
    }

    @Step("Click login button field")
    public ObcAppLoginPage click_login(){
        log.info( "Click on Login Button" );
        return click( loginBtn );
    }

    @Step("Login 'OBC' application")
    public ObcAppLoginPage login_obc_application(){
        log.info( "Login 'OBC' application" );
        load( "UNO", configUrl );
        handle_browser_exceptions();
        enter_user_name( getConfigParamValue( "UNO", "obcUserName" ) );
        enter_user_password( getConfigParamValue( "UNO", "obcPassword" ) );
        click_login();
        return me();
    }

    @Step("Verifying Login 'OBC' application")
    public ObcAppLoginPage verify_successful_login() {
        log.info("Verifying 'OBC' login");
        verify(presenceOfElementLocated(homeBtn), 60, 1000);
        return me();
    }
}