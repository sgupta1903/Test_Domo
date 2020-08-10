package testcase.datamigrationtest.getdatafromexcel;
/*
Created By: Monika Phoughat mphoughat@bhavnacorp.com
Updated By: Shilpi Gupta
Data Factory to Execute Test Execution
Date: 9/29/2019
*/

import config.EnvProperty;
import helper.AppConstants;
import helper.ServicePropertyFileReader;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import testcase.datamigrationtest.ObcToUnoAppTest;
import ui.AbstractAutoUITest;

import java.io.IOException;
@Slf4j
public class GetDataFromExcelTest extends AbstractAutoUITest {
    Ini ini = new Wini(this.getClass().getResourceAsStream('/'+ ServicePropertyFileReader.getInstance("env").getPropertyValue("env")+'/' + "property.ini"));
    EnvProperty readExcelProperty = new EnvProperty(ini);
    String filePath=System.getProperty( "user.dir" )+readExcelProperty.getConfigPropertyValue("EXCEL","path");
    String sheetName=readExcelProperty.getConfigPropertyValue("EXCEL","sheetName");
    public GetDataFromExcelTest() throws IOException {
    }


    @Factory(dataProvider="excelData")
    private Object[] factoryMethod(String clubNumber, String memberNumber) {
        ObcToUnoAppTest instance = new ObcToUnoAppTest(clubNumber,memberNumber);
        //get the club number from jenkins and read data from file
        log.info("Club Number" + ":" +ServicePropertyFileReader.getInstance("clubnumber").getPropertyValue("club"));
        log.info(clubNumber + ":" + memberNumber);
        return new Object[] {
                instance
        };
    }
    @DataProvider(name = "excelData")
    public Object[][] readExcel() throws IOException {
        return getExcelClientWithoutSection(filePath,sheetName)
                .read_excel(filePath,sheetName);
    }

}