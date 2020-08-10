package ui;

import config.EnvProperty;
import excelsupport.ExcelClient;
import helper.DataBaseHandler;
import helper.DriverInitilization;
import helper.RestHandler;
import listeners.XrayListener;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Listeners({XrayListener.MyListenerFactory.class, XrayListener.MyReporter.class})
public class AbstractAutoUITest {

    private ITestContext context;
    private EnvProperty envProperty;
    private DriverInitilization driverInitilization;

    @BeforeClass(alwaysRun = true)
    public void init(ITestContext context) {
        setTestResultOnXRay("A30TP-11914");
        this.context = context;
        this.driverInitilization = (DriverInitilization) context.getAttribute(DriverInitilization.class.getName());
        this.envProperty = (EnvProperty) context.getSuite().getAttribute(EnvProperty.class.getName());
    }

    public <T extends AbstractPage<T>> T getPage(Class<T> pageClass) {

        return get(pageClass, driverInitilization.getDriver());
    }

    public <T extends AbstractPage<T>> T getPageWithoutUI(Class<T> pageClass) {

        return get(pageClass, null);
    }

    public <T extends AbstractPage<T>> T get(Class<T> pageClass, WebDriver driverValue) {


        try {
            T page = pageClass.newInstance();

            Field fieldDriver = AbstractPage.class.getDeclaredField("driver");
            fieldDriver.setAccessible(true);
            fieldDriver.set(page, driverValue);

            Field fieldEnvProperty = AbstractPage.class.getDeclaredField("envProperty");
            fieldEnvProperty.setAccessible(true);
            fieldEnvProperty.set(page, this.envProperty);

            return page;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Store the new attribute in test context.
     *
     * @param name  String Attribute name.
     * @param value String Attribute value.
     */
    protected void store_test_data(String name, String value) {

        context.setAttribute(name, value);
    }

    protected void store_test_list(String name, List<String> list) {

        context.setAttribute(name, list);
    }

    /**
     * Retrieve the attribute from to test context.
     *
     * @param name String Attribute name.
     */
    protected String retrieve_test_data(String name) {

        return (String) context.getAttribute(name);
    }

    protected List retrieve_test_list(String name) {

        return (List) context.getAttribute(name);
    }

    /**
     * Get Excel Client Utility.
     *
     * @param section Environment Configuration Section to be referred.
     */
    protected ExcelClient getExcelClient(String section) {

        return new ExcelClient(envProperty, section);
    }

    /**
     * Get Excel Client Utility without providing information in ini file.
     *
     * @param path      Path of the excel sheet
     * @param sheetName Sheet to work upon
     */
    protected ExcelClient getExcelClientWithoutSection(String path, String sheetName) {

        return new ExcelClient(path, sheetName);
    }

    /**
     * Get Excel Client Utility.
     */
    protected ExcelClient getExcelClient(String content, String sheetName) {

        return new ExcelClient(envProperty, content, sheetName);
    }


    /**
     * Get DB Client Utility.
     *
     * @param section Environment Configuration Section to be referred.
     */
    protected DataBaseHandler getDBClient(String section) {

        return new DataBaseHandler(envProperty, section);
    }


    protected void setTestResultOnXRay(String testCaseId) {

        Reporter.getCurrentTestResult().setAttribute("test", testCaseId);
        Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
        logger.info("testcaseid-" + testCaseId);

    }

    /**
     * Get Rest Client Utility.
     *
     * @param section Environment Configuration Section to be referred.
     */
    protected RestHandler getRestClient(String section) {

        return new RestHandler(envProperty, section);
    }

    /**
     * Read Data from Excel
     *
     * @param filePath Excel File path,  @param sheetName Sheet Name
     */
    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int column = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][column];
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < column; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(cell);
                data[i - 1][j] = val;
            }
        }
        return data;
    }


    public Map getTestData(String fileName, String sectionName, String... keys) {
        Map sectionData = new HashMap<String, String>();
        Set<String> keySet = new HashSet<>();
        EnvProperty envProperty = EnvProperty.getInstance(fileName);

        for (String data : keys) {
            keySet.add(data);
        }
        for (String key : keySet) {
            sectionData.put(key, envProperty.getConfigPropertyValue(sectionName, key));
        }
        return sectionData;
    }

}