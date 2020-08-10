package testcase.pushpulltest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.pushpullPage.pushpullPage;
import ui.AbstractAutoUITest;

@Test(groups = "domo-logout")
@Slf4j
public class Dashboard_Logout_Test extends AbstractAutoUITest {
    
    @Test(priority = 16)
    public void domoLogout() {
        log.info("Logout from the Domo Application");
        getPage(pushpullPage.class)
                .domo_logout();
    }
}
