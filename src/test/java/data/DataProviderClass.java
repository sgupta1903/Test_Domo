package data;

import config.EnvProperty;
import helper.ServicePropertyFileReader;
import io.qameta.allure.TmsLink;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.testng.annotations.DataProvider;
import pagetest.businessapppage.AbcCommonAbstractPage;
import testcase.datamigrationtest.GreenScreenToRCPVerificationTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static helper.AppConstants.*;

public class DataProviderClass /*extends AbcCommonAbstractPage<DataProviderClass>*/ {
    Ini ini = new Wini(this.getClass().getResourceAsStream('/'+ ServicePropertyFileReader.getInstance("env").getPropertyValue("env")+'/' + "property.ini"));
    EnvProperty env = new EnvProperty(ini);
    public DataProviderClass() throws IOException {
    }


    @DataProvider(name = "migration-cash-storage")
   public Object[] dataProviderForClubNumbers(Method method) {
        TmsLink tmsLink=method.getAnnotation(TmsLink.class);
        String testCaseID = tmsLink.value();
        Object[] clubNumbers = get_club_with_test_case_id().split(",");
        clubNumbers= Arrays.stream(clubNumbers).filter(s -> s.toString().contains(testCaseID)).toArray();
        return clubNumbers;
    }

    public String get_club_with_test_case_id() {
        String clubWithTestCase = "";
        if((env.getConfigPropertyValue(CASH_STORAGE, CLUB_NUMBER)!=null))
        {
            if(env.getConfigPropertyValue(CASH_STORAGE, TEST_CASE_ID)!=null) {
                String[] clubNumbers = env.getConfigPropertyValue(CASH_STORAGE, CLUB_NUMBER).split(",");
                String[] testCaseIds = env.getConfigPropertyValue(CASH_STORAGE, TEST_CASE_ID).split(",");
                for (int clubNumIterator = 0; clubNumIterator < clubNumbers.length; clubNumIterator++) {
                    String currentClubNumber = clubNumbers[clubNumIterator];
                    for (int testCaseIdIterator = 0; testCaseIdIterator < testCaseIds.length; testCaseIdIterator++) {
                        clubWithTestCase = clubWithTestCase + currentClubNumber + "_" + testCaseIds[testCaseIdIterator] + ",";
                    }
                }
            }
        }
        return clubWithTestCase;
    }

    public String get_club_with_member_and_test_case_id() {
        String clubWithMemberAndTestCase = "";
        if((env.getConfigPropertyValue(CASH_STORAGE, CLUB_WITH_MEMBER_NUMBER)!=null)) {
            String[] clubNumbers = env.getConfigPropertyValue(CASH_STORAGE, CLUB_WITH_MEMBER_NUMBER).split(",");
            String[] testCaseIds = env.getConfigPropertyValue(CASH_STORAGE, MEMBER_TEST_CASE_ID).split(",");
            for (int clubNumIterator = 0; clubNumIterator < clubNumbers.length; clubNumIterator++) {
                String currentClubNumber = clubNumbers[clubNumIterator];
                for (int testCaseIdIterator = 0; testCaseIdIterator < testCaseIds.length; testCaseIdIterator++) {
                    clubWithMemberAndTestCase = clubWithMemberAndTestCase + currentClubNumber + "_" + testCaseIds[testCaseIdIterator] + ",";
                }
            }
        }
        return clubWithMemberAndTestCase;
    }

    @DataProvider(name = "migration-data-with_member_number")
    public Object[] dataProviderForClubNumbersWithMemberNumber(Method method) {
        TmsLink tmsLink=method.getAnnotation(TmsLink.class);
        String testCaseID = tmsLink.value();
        Object[] clubNumbers = get_club_with_member_and_test_case_id().split(",");
        clubNumbers= Arrays.stream(clubNumbers).filter(s -> s.toString().contains(testCaseID)).toArray();
        return clubNumbers;
    }
}