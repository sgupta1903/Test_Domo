package pagetest.pushpullPage;

import config.EnvProperty;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;
import java.io.FileInputStream;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class pushpullPage extends AbcCommonAbstractPage<pushpullPage> {

    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private By username = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@type='password']");
    private By loginBtn = By.xpath("//button[@name='submit']");
    private By dataBtn = By.xpath("//div[@id='nav']//a[5]");
    private By datasetLink = By.xpath("//a[@href='/datacenter/datasources']");
    private By noOfRowsTxt = By.xpath("//span[@dm-i18n='datacenter/rows']");
    private By optionsDropDown = By.xpath("//i[@class='icon-chevron-down xs margin-left-xsmall vertical-align-middle text-align-right']");
    private By runnowOption = By.xpath("//span[contains(text(),'Run Now')]");
    private By dataSetlink = By.xpath("//a[contains(text(),'DataSets')]");
    private By buzzLink = By.xpath("//i[@class='icon-chat-bubbles _24']");
    private String configUrl = "baseUrl";
    private By domoUserImage = By.xpath("//div[@class='avatar-placeholder tab']//div[@class='avatar-widget nano']");
    private By signOutBtn = By.xpath("//a[contains(text(),'Sign Out')]");
    private By popUp = By.xpath("//div[@class='modal trans-scale-1']//i[@class='icon-x modal-close ng-scope']");
    private By searchBox = By.xpath("//input[@id='superSearch1']");
    EnvProperty envProperty;
    private Properties properties;
    private FileInputStream in;
    int db_count;


    public By upsertXpath(String name) {
        logger.info("Getting xpath for dataset -" + name);
        return By.xpath("//div[@class='name ng-binding'][contains(text(),'" + name + "')]");
    }

    public By dataset_xpath(String name) {
        logger.info("Getting xpath for dataset -" + name);
        return By.linkText(name);
    }

    public By get_ran_successfully_xpath() {
        logger.info("Getting xpath");
        return By.xpath("//div[contains(text(),'Update Complete')]");
    }

    public pushpullPage search_dataset(String datasetName) {
        logger.info("search dataset");
        return enter(searchBox, datasetName);
    }

    @Step("Login with UserName :{0} , Password:{1}")
    public pushpullPage login() {
        logger.info("Loading Application URL");

        try {
            String domoUserName1 =System.getenv("DOMO_ADMIN_USERNAME");
            String domo_password1 =  System.getenv("DOMO_ADMIN_PASSWORD");
            properties = new Properties();
            in = new FileInputStream("src/test/resources/env.properties");
            properties.load(in);
            loadBaseUrl("https://abcfinancial.domo.com");
            handle_browser_exceptions();
            enter_user_name(domoUserName1);
            enter_user_password(domo_password1);
            click_login();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return me();
    }

    @Step("Verify UserName :{0}")
    public pushpullPage enter_user_name(String username) {
        logger.info("Entering the User Name");
        return enter(this.username, username);
    }

    @Step("Verify Password: {1}")
    public pushpullPage enter_user_password(String password) {
        logger.info("Entering the User Password");
        return enter(this.password, password);
    }

    public pushpullPage click_login() {
        logger.info("Click on Login Button");
        return click(loginBtn);
    }

    public pushpullPage click_data_tab() {
        logger.info("Click on data tab");
        return click(dataBtn);
    }

    public pushpullPage click_buzz_link() {
        logger.info("Click on buzz tab");
        wait_until(5);
        return click(buzzLink);
    }

    public String return_sql(String query) {
        logger.info("Return SQL query");
        return query;
    }

    public pushpullPage back_to_dataset() {
        logger.info("Click on back dataset link");
        return click(dataSetlink);
    }

    public pushpullPage click_pull_page(String upsertName) {
        logger.info("Click on pull page");
        return click(upsertXpath(upsertName));
    }

    public pushpullPage click_dataset(String datasetName) {
        logger.info("Click on " + datasetName);
        return click(dataset_xpath(datasetName));
    }

//    public pushpullPage close_popup(){
//        logger.info( "close popup" );
//        return click(popUp);
//    }

    public pushpullPage click_dataset_link() {
        logger.info("Click on dataset link");
        return click(datasetLink);
    }

    public int get_rows_count(String datasetName) {
        logger.info("getting rows");
        String str = find_element_text(noOfRowsTxt);
        String replaceRowsText = str.substring(0, str.indexOf(" "));
        String removeComma = replaceRowsText.replace(",", "");
        int result = Integer.parseInt(removeComma);
        logger.info(datasetName + " DOMO row count =" + String.valueOf(result));
        return result;
    }

    public pushpullPage click_options_dropdown() {
        logger.info("Click on options");
        return click(optionsDropDown);
    }

    public pushpullPage click_run_now_option() {
        logger.info("Click on run now");
        return click(runnowOption);
    }

    public pushpullPage verify_successful_login() {
        logger.info("Verifying login image is visible on domo page");
        verify(presenceOfElementLocated(domoUserImage), 60, 1000);
        return me();
    }

    public pushpullPage verify_run_successfully() {
        logger.info("Verifying run successfully");
        wait_until(presenceOfElementLocated(get_ran_successfully_xpath()));
        return me();
    }



    public pushpullPage verify_count(int domo_rows, int db_rows) {
        logger.info("Verify domo and db count");
        verify_value_matches_int(domo_rows, db_rows);
        return me();
    }

    public pushpullPage domo_logout() {
        logger.info("logout of application");
        verify(presenceOfElementLocated(domoUserImage), 60, 1000);
        click(domoUserImage);
        verify(presenceOfElementLocated(signOutBtn), 60, 1000);
        click(signOutBtn);
        return me();
    }
}
