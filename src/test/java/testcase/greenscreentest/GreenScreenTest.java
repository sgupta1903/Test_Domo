package testcase.greenscreentest;

import org.testng.annotations.Test;
import pagetest.greenscreenpage.StringSenderPage;
import ui.AbstractAutoUITest;
import java.io.IOException;

public class GreenScreenTest extends AbstractAutoUITest {

    @Test
    public void captureGreenScreenData() throws IOException
    {
        getPageWithoutUI(StringSenderPage.class)
                .send_string();
    }

}
