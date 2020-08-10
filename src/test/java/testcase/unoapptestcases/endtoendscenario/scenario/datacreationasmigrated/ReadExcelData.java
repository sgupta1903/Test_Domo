package testcase.unoapptestcases.endtoendscenario.scenario.datacreationasmigrated;

import config.EnvProperty;
import helper.ServicePropertyFileReader;
import io.qameta.allure.Description;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pagetest.unoapppage.UnoAppLoginPage;
import ui.AbstractAutoUITest;

import java.io.IOException;

import static helper.AppConstants.MIGRATEDORGCREATED_INI;
import static util.UtilityGeneric.clear_ini_file;

public class ReadExcelData extends AbstractAutoUITest {
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    Ini ini = new Wini(this.getClass().getResourceAsStream('/' + ServicePropertyFileReader.getInstance("env").getPropertyValue("env") + '/' + "property.ini"));
    EnvProperty readExcelProperty = new EnvProperty(ini);
    String filePath = System.getProperty("user.dir") + readExcelProperty.getConfigPropertyValue("EXCEL", "migratedDataPath");
    String sheetName = readExcelProperty.getConfigPropertyValue("EXCEL", "sheetName");

    public ReadExcelData() throws IOException {
    }

    @Factory(dataProvider = "excelData")
    private Object[] factoryMethod(String clubNumber, String memberNumber, String clubAddress, String clubCity, String clubZip, String clubPhone, String agreementNumber, String memberName, String memberAddress, String memberCity, String memberZip, String memberCountry, String memberEmail, String memberWorkPhone, String memberHomePhone, String memberCellPhone,
                                   String memberEmergencyNumber, String memberSinceDate, String subscriptionType, String subscriptionAmount, String lastFour, String rountingNumber, String creditCardType, String paymentType) {
        CreateAllDataAsMigratedData instance = new CreateAllDataAsMigratedData(clubNumber, memberNumber, clubAddress, clubCity, clubZip, clubPhone, memberName, memberAddress, memberCity, memberZip, memberCountry, memberEmail, memberHomePhone, memberCellPhone,
                memberEmergencyNumber, memberSinceDate, subscriptionType, subscriptionAmount, lastFour, rountingNumber, creditCardType, paymentType);

        logger.info(clubNumber + ":" + memberNumber);
        return new Object[]{
                instance
        };
    }

    @DataProvider(name = "excelData")
    public Object[][] readExcel() throws IOException {
        return getExcelClientWithoutSection(filePath, sheetName)
                .read_excel(filePath, sheetName);
    }

    @BeforeTest
    @Description("Clearing the data from the ini file")
    public void clearIniFile() {
        logger.info("Removing the  data from the ini file");
        setTestResultOnXRay("A30TP-38483");
        clear_ini_file(MIGRATEDORGCREATED_INI);
    }

    @Test
    @Description("Login into Uno App with correct credentials and verify that login is successful")
    public void unoAppLogin() {
        logger.info("Login into the Uno Application with valid credentials");
        setTestResultOnXRay("A30TP-8217");
        getPage(UnoAppLoginPage.class)
                .login()
                .verify_successful_login();
    }
}