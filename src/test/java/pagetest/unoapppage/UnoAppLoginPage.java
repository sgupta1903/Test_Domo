package pagetest.unoapppage;
/*
Created By: Sapna Batan
Updated By:
Jira Ticket:
Date: 02/15/2018
*/

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppLoginPage extends AbcCommonAbstractPage<UnoAppLoginPage> {
    private By username = By.xpath("//div[@data-abc-id='username']//input[@name='username']");
    private By password = By.xpath("//div[@data-abc-id='password']//input[@id='password']");
    private By unoAppHomePage = By.xpath("//i[@data-abc-id = 'homeIcon']");
    private By unoAppHomeHeader = By.xpath("//header[@data-abc-id='header']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By profileButton= By.xpath("//div[@data-abc-id = 'avatar']");
    private By signOutButton = By.xpath("//a[text() = 'Sign Out']");
    private String configUrl = "baseUrlPre";


    @Step("Verify UserName :{0}")
    public UnoAppLoginPage enter_user_name(String username) {
        log.info("Entering the User Name");
        return enter(this.username, decode_content(username));
    }

    @Step("Verify Password: {1}")
    public UnoAppLoginPage enter_user_password(String password) {
        log.info("Entering the User Password");
        return enter(this.password, decode_content(password));
    }

    @Step("Click on Login Button")
    public UnoAppLoginPage click_login() {
        log.info("Click on Login Button");
        return click(loginButton);
    }

    public UnoAppLoginPage login() {
        log.info("Loading Application URL");
        load("UNO", configUrl);
        handle_browser_exceptions();
        enter_user_name(getConfigParamValue("UNO", "userName"));
        enter_user_password(getConfigParamValue("UNO", "passWord"));
        click_login();
        return me();
    }

    public UnoAppLoginPage verify_successful_login() {
        log.info("Verifying login home is visible on Uno App page");
       verify(presenceOfElementLocated(unoAppHomeHeader), 60, 1000);
        return me();
    }

    public UnoAppLoginPage unoApp_logout() {
        log.info("logout of application and delete the data created");
        wait_until(3);
        verify(presenceOfElementLocated(profileButton), 60, 1000);
        click(profileButton);
        verify(presenceOfElementLocated(signOutButton), 60, 1000);
        click(signOutButton);
        return me();
    }
    public UnoAppLoginPage unoApp_logout_with_teardown() {
        log.info("logout of application and delete the data created");
        wait_until(3);
        verify(presenceOfElementLocated(unoAppHomePage), 60, 1000);
        click(unoAppHomePage);
        wait_until(1);
        click(profileButton);
        verify(presenceOfElementLocated(signOutButton), 60, 1000);
        click(signOutButton);
       wait_until(3);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        auth_token(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        auth_token_client(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        pg_auth_token(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        get_Merchant(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        delete_merchant_data_by_merchant_id(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        delete_org_data_by_org_id(restHandler, "");
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        delete_catalog_data_by_org_id(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        delete_taxation_data(restHandler);
        restHandler = getRestClient("REST_CLIENT_DETAILS");
        delete_billing_data(restHandler);
        return me();
    }
}