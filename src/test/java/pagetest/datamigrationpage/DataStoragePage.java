package pagetest.datamigrationpage;

import config.EnvProperty;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.List;

import static util.UtilityGeneric.*;

import static helper.AppConstants.SRC_TO_RESOURCE_PATH;

@Slf4j
public class DataStoragePage extends AbcCommonAbstractPage<DataStoragePage> {

    @Step("Clearing the ini file -> {0}")
    public DataStoragePage clear_ini_file(String fileName) {
        log.info("Clear ini file has started");
        EnvProperty env = EnvProperty.getInstance(fileName);
        env.clearIniFile();
        return me();
    }

    @Step("Clearing the ini file -> {0}")
    public List<String> getAllTestCaseIds(String... testCaseIds) {
        log.info("Collecting all string values into list");
        List<String> testCaseList = returnListOfString(testCaseIds);
        return testCaseList;
    }

    @Step("Parsing data from text file -> {0} into ini file -> {1}. And Test case id is -> {2} ")
    public DataStoragePage read_store_text_file_to_ini(String textFileName, String iniFileName, List<String> testCaseIds) {

        log.info("Reading data from text file and storing as per club number in ini file has started ... ");
        EnvProperty iniFile = EnvProperty.getInstance(iniFileName);
        String filePath = System.getProperty("user.dir") + SRC_TO_RESOURCE_PATH + textFileName;
        convert_xml_formated_text_file_into_ini(filePath, iniFile, testCaseIds);
        return me();

    }

    @Step("Check if file exist in resources or not")
    public DataStoragePage check_file_exist( String fileName , int timeFrame) {
        log.info("Checking if the file exist in resources or not has started ......");
        check_file_existence_in_resources(fileName , timeFrame);

        return me();
    }

}