package listeners;

import config.EnvProperty;
import helper.ServicePropertyFileReader;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import pagetest.datamigrationpage.CentralDBPage;
import ui.AbstractAutoUITest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReadWriteDataListener  extends AbstractAutoUITest implements ISuiteListener{
    Ini ini = new Wini(this.getClass().getResourceAsStream('/' + ServicePropertyFileReader.getInstance("env").getPropertyValue("env") + '/' + "property.ini"));
    EnvProperty readExcelProperty = new EnvProperty(ini);
    String filePath = System.getProperty("user.dir") + readExcelProperty.getConfigPropertyValue("EXCEL", "path");
    String filePath1=System.getProperty( "user.dir" )+readExcelProperty.getConfigPropertyValue("EXCEL","path1");
    String sheetName = readExcelProperty.getConfigPropertyValue("EXCEL", "sheetName");
    Map<String, String> getData = new HashMap<>();

    public ReadWriteDataListener() throws IOException {
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Finishing");
    }

    public void dbConnection(EnvProperty readExcelProperty) throws IOException {
        log.info("Club Number" + ":" + ServicePropertyFileReader.getInstance("clubnumber").getPropertyValue("club"));
        String[] club = ServicePropertyFileReader.getInstance("clubnumber").getPropertyValue("club").split(",");
        for (int x = 0; x < club.length; x++) {
            String clubNumber = club[x];
            getData = getPageWithoutUI(CentralDBPage.class)
                    .get_member_list(clubNumber, getDBClientData(readExcelProperty, "DB_CONNECTION"));
            log.info("getData" + ":" + getData);
            log.info("filePath" + ":" + filePath);
            log.info("clubNumber" + ":" + clubNumber);
            getData.size();

            getExcelClient()
                    .write_excel_dm(getData, filePath, filePath1,clubNumber);

            /*getExcelClient()
                    .write_excel_dm(getData, filePath, clubNumber);*/
        }

    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Starting");
        try {
            dbConnection(readExcelProperty);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

