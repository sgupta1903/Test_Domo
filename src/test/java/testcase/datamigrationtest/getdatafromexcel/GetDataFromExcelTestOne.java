package testcase.datamigrationtest.getdatafromexcel;
/*
Created By: Monika Phoughat mphoughat@bhavnacorp.com
Updated By: Shilpi Gupta
Data Factory to Execute Test Execution
Date: 9/29/2019
*/

import config.EnvProperty;
import helper.DataBaseHandler;
import helper.ServicePropertyFileReader;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import pagetest.datamigrationpage.CentralDBPage;
import testcase.datamigrationtest.ObcToUnoAppTest;
import ui.AbstractAutoUITest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GetDataFromExcelTestOne extends AbstractAutoUITest {
    Ini ini = new Wini(this.getClass().getResourceAsStream('/' + ServicePropertyFileReader.getInstance("env").getPropertyValue("env") + '/' + "property.ini"));
    EnvProperty readExcelProperty = new EnvProperty(ini);
    String filePath = System.getProperty("user.dir") + readExcelProperty.getConfigPropertyValue("EXCEL", "path1");
    String sheetName = readExcelProperty.getConfigPropertyValue("EXCEL", "sheetName");
    Map<String, String> getData = new HashMap<>();
    DataBaseHandler dBConnection = getDBClientData(readExcelProperty, "DB_CONNECTION");

    public GetDataFromExcelTestOne() throws IOException {
    }

    public void dbConnection() throws IOException {
        log.info("Club Number" + ":" + ServicePropertyFileReader.getInstance("clubnumber").getPropertyValue("club"));
        String[] club = ServicePropertyFileReader.getInstance("clubnumber").getPropertyValue("club").split(",");
        for (int x = 0; x < club.length; x++) {
            String clubNumber = club[x];
            getData = getPageWithoutUI(CentralDBPage.class)
                    .get_member_list(clubNumber, dBConnection);
            log.info("getData" + ":" + getData);
            log.info("filePath" + ":" + filePath);
            log.info("clubNumber" + ":" + clubNumber);
            List<String> valueList = new ArrayList<String>(getData.values());
            String[] club1 = valueList.get(0).split(",");
            int final1 = ((club1.length / 2) * 2);
            int Initial = (club1.length / 2) + 1;
            getExcelClient()
                    .write_excel_dm(getData, filePath, clubNumber, final1, Initial);
        }
    }

    @Factory(dataProvider = "excelData")
    private Object[] factoryMethod(String clubNumber, String memberNumber) {
        ObcToUnoAppTest instance1 = new ObcToUnoAppTest(clubNumber, memberNumber);
        log.info(clubNumber + ":" + memberNumber);
        return new Object[]{
                instance1
        };
    }

    @DataProvider(name = "excelData")
    public Object[][] readExcel() throws IOException {
        dbConnection();
        log.info("Member" + ":" + getData);
        return getExcelClientWithoutSection(filePath, sheetName)
                .read_excel(filePath, sheetName);
    }
}
