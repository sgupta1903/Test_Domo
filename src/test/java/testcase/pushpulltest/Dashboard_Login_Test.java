package testcase.pushpulltest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.pushpullPage.pushpullPage;
import ui.AbstractAutoUITest;

@Slf4j
public class Dashboard_Login_Test extends AbstractAutoUITest {

    @Test(priority = 0)
    public void domoLogin() {
        log.info("Login into the Domo Application");
        getPage(pushpullPage.class)
                .login();
    }
}
