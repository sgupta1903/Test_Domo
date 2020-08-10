package pagetest.paymentgatewaypage;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagetest.businessapppage.AbcCommonAbstractPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
@Slf4j
public class PaymentGatewayLoginPage extends AbcCommonAbstractPage<PaymentGatewayLoginPage>
{

    private By username = By.xpath( "//input[@id='username']" );
    private By password = By.xpath( "//input[@id='password']" );
    private By  loginBtn = By.xpath( "//button[@type='submit']" );
    private By signOutBtn = By.xpath("//a[text() = 'Sign Out']");
    private By pGHomePage = By.xpath( "//div[@data-abc-id='avatar']" );
    private String configUrl = "baseUrlPaymentGateway";

    @Step("Verify UserName :{0}")
    public PaymentGatewayLoginPage enter_user_name(String username){
        log.info( "Entering the User Name" );
        return enter( this.username, decode_content(username ));
    }
    @Step("Verify Password: {1}")
    public PaymentGatewayLoginPage enter_user_password(String password){
        log.info( "Entering the User Password" );
       return enter( this.password, decode_content(password) );
    }
    public PaymentGatewayLoginPage click_login(){
        log.info( "Click on Login Button" );
        return click(loginBtn);
    }
    public PaymentGatewayLoginPage switch_tab()
    {
        wait_until( 4 );
        log.info( "Switch to New tab" );
        return switch_to_new_tab();

    }
    public PaymentGatewayLoginPage login_pg(){
        log.info( "Loading Application URL" );
        wait_until( 1 );
        switch_tab();
        load( "PG", configUrl );
        handle_browser_exceptions();
        enter_user_name( getConfigParamValue( "PG", "userName" ) );
        enter_user_password( getConfigParamValue( "PG", "passWord" ) );
        click_login();

        return me();
    }
    public PaymentGatewayLoginPage verify_successful_login(){
        log.info( "Verifying login image is visible on Uno App page" );
        verify( ExpectedConditions.presenceOfElementLocated(  pGHomePage),60,1000 ) ;
        return me();
    }
    public PaymentGatewayLoginPage payment_gateway_logout(){
        log.info( "Logout of Payment Gateway application" );
        wait_until(3);
        verify( presenceOfElementLocated(pGHomePage),60,1000 ) ;
        click(pGHomePage);
        verify( presenceOfElementLocated(signOutBtn),60,1000 ) ;
        click(signOutBtn);
        wait_until( 1 );
        switch_to_parent_tab();
        close_child_tab();
        return me();
    }

}
