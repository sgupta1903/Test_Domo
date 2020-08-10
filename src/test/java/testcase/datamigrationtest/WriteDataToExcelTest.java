package testcase.datamigrationtest;
/*
Created By: Praveen
Updated By: Sapna
Date: 02/12/2020
*/

import config.EnvProperty;
import helper.ServicePropertyFileReader;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import ui.AbstractAutoUITest;
import java.io.IOException;

@Slf4j
public class WriteDataToExcelTest extends AbstractAutoUITest {
    Ini ini = new Wini(this.getClass().getResourceAsStream('/'+ ServicePropertyFileReader.getInstance("env").getPropertyValue("env")+'/' + "property.ini"));
    EnvProperty readExcelProperty = new EnvProperty(ini);
    String filePath=System.getProperty( "user.dir" )+readExcelProperty.getConfigPropertyValue("EXCEL","path");
    String sheetName=readExcelProperty.getConfigPropertyValue("EXCEL","sheetName");
    public WriteDataToExcelTest() throws IOException {
    }

    @Factory(dataProvider="excelData")
    private Object[] factoryMethod(String clubNumber, String memberNumber) {
        StoreMigratedDataTest instance = new StoreMigratedDataTest(clubNumber,memberNumber,filePath,sheetName);
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