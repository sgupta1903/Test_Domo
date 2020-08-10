package testcase.unoapptestcases.endtoendscenario.login;
/*
Created By: Sapna Batan
Date: 07/24/2019
*/

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;

@Slf4j
public class UnoApp_Login_Test extends AbstractAutoUITest {

    @Test(groups = "login")
    @Description("Login to Uno App")
    public void unoAppLogin() {
        log.info("Login into the Uno Application");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }
}
