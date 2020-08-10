package testares;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.PropFileHelper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static helper.AppConstants.*;

/**
 * AresDashboard provides the facility to store test results i.e. module data
 * and test results data to affle dashboard AresDashboard has 3 sections as Live
 * Executions, Dashboard and Detailed TestResults
 * <p>
 * Live Executions section - Where you will see current Run Overall Progress in
 * terms of percentage - Overall pass percentage of the current test execution -
 * List of module names in current run
 * <p>
 * Dashboard - Where you find graphs as Automation health, Trends, Platform
 * coverage, Module/Functional Area, Test Failure Analysis, Consistently failed
 * tests and Automation Execution Time Trend
 * <p>
 * Automation Trends - This graph shows the number of tests executed (passed and
 * Failed) date-wise Platform Coverage - It shows the number of tests executed
 * (passed and Failed) against different browser Top5 Consistently Failed tests
 * - If a test fails consistently, it will be shown here Module/Functional Area
 * - This graph shows the module wise tests executed both passed and failed
 * tests Test Failure Analysis - This chart represents error messages for failed
 * tests Automation Execution Time Trend - This graph represents execution time
 * of last 5 tests, you will have module selection dropdown, upon selecting
 * module graph will be updated
 * <p>
 * Detailed TestResults - Under this section, you find Testcase title, Date,
 * Test Result, Error Message, project name, browser name and ImageLink for
 * failed Testcase title - Test method name lie here Date - Test execution date
 * ex. 2018-09-10 Test Result - Test status whether it is Passed or Failed Error
 * Message - Test failure reason, if it assertion failure corresponding
 * assertion message will be shown here Project Name - Name of the project
 * created in dashboard Browser Name - Browser name on which execution happened
 * ImageLink - Screenshot for failed test, ideally http global link is preferred
 * <p>
 * Gets ARES dashboard properties from DashboardResources
 * <p>
 * Sends API requests to dashboard upon requests received from listener
 */
public class AresDashboard {

    /**
     * Sends Test Results to dashboard with the mentioned parameters This method
     * is called at the end of testcase
     *
     * @param runID         - Run ID of current execution
     * @param suiteName     - test suite name
     * @param sModuleName   - module name
     * @param sTestCaseName - name of the test case
     * @param sTestStatus   - test execution status
     * @param imagePath     - screenshot path if test fails
     * @param errorMessage  - test execution error message
     * @param videoLink     - video link of test execution
     * @param sBrowserName  - browser name on which execution happened
     * @param sStartTime    - test start time
     * @param sEndTime      - test end time
     * @return HttpResponse - HttpResponse object will be returned
     */
    String filepath = "src/test/resources/TestAstra.ini";
    Map<String, String> help = PropFileHelper.getSensorValue(filepath, "ARES_REPORTING");
    RunIdData data = new RunIdData();

    public HttpResponse postTestResults(String runID, String suiteName, String sModuleName, String sTestCaseName,
                                        String sTestStatus, String imagePath, String errorMessage, String videoLink, String sBrowserName,
                                        String sStartTime, String sEndTime) {

        HttpResponse response = null;
        String URL = help.get(TESTDETAILSURL);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URL);
        post.setHeader(USER_TOKEN, help.get(TOKEN));
        post.setHeader(PROJECT_ID, help.get(PROJECTID));
        post.setHeader(CONTENTTYPE, HEADER);

        StringEntity params = null;
        try {

            PostResults result = new PostResults();
            GraphData postData = new GraphData();
            postData.setRunId(runID);
            postData.setProductName(help.get(PRODUCTNAME));
            postData.setModuleName(sModuleName);
            postData.setTestcaseTitle(sTestCaseName);
            postData.setTestStatus(sTestStatus);
            postData.setTestData("-");
            postData.setFailStacktrace("-");
            postData.setTestBrowser(sBrowserName);
            postData.setTestMachine(UtilityMethods.machineName());
            postData.setImageLink(imagePath);
            postData.setVideoLink(videoLink);
            postData.setTestDevice(help.get(TESTDEVICE));
            postData.setTestOs(System.getProperty(OS_NAME));
            postData.setTestDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            postData.setTestStartTime(sStartTime);
            postData.setTestEndTime(sEndTime);
            postData.setTestSuite(suiteName);
            postData.setRunBy(help.get(PROJECTUSER));
            postData.setErrorMessage(errorMessage);
            postData.setExecutionMode(MODEOFEXECUTION);
            postData.setFailType("-");

            result.setGraphData(postData);

            params = new StringEntity(new ObjectMapper().writeValueAsString(result));
            post.setEntity(params);
            response = client.execute(post);

            String sResponse = readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Posts module level data to Affle dashboard It helps to represent
     * Module/Functional area in dashboard It gets total number of tests in a
     * module and updates the module specific data in dashboard
     *
     * @param runID       - Run ID of current execution
     * @param sModuleName - Module name of project if you have more than one module
     * @param sTestCount  - Number of tests in module
     * @param sStatus     - status, values will be started/ended
     */
    public void postModuleData(String runID, String sModuleName, String sTestCount, String sStatus) {
        ModuleData data = new ModuleData();
        ProcessData data1 = new ProcessData();

        if (sStatus.equalsIgnoreCase("started")) {
            data.setToken(help.get(TOKEN));
            data.setRunId(runID);
            data.setWs_name(help.get(WSNAME));
            data.setProject_name(help.get(PROJECTNAME));
            data.setModule_name(sModuleName);
            String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            data.setStarttime(startDate);
            data.setTotaltests(sTestCount);
            data.setStatus(sStatus);

            StringEntity params = null;
            String URL = help.get(ADDMODULEDATAURL);
            HttpResponse response = null;
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(URL);
            post.setHeader(CONTENTTYPE, HEADER);
            try {
                params = new StringEntity(new ObjectMapper().writeValueAsString(data));
                post.setEntity(params);
                response = client.execute(post);
                String sResponse = readResponse(response);
                System.out.println("MODULE DATA RESPONSE : " + sResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            data1.setToken(help.get(TOKEN));
            data1.setRunId(runID);
            data1.setWs_name(help.get(WSNAME));
            data1.setProject_name(help.get(PROJECTNAME));
            data1.setModule_name(sModuleName);
            String setEndtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            data1.setEndtime(setEndtime);
            data1.setTotaltests(sTestCount);
            data1.setStatus(sStatus);

            StringEntity params = null;
            String URL = help.get(ADDMODULEDATAURL);
            HttpResponse response = null;
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(URL);
            post.setHeader(CONTENTTYPE, HEADER);
            try {
                params = new StringEntity(new ObjectMapper().writeValueAsString(data1));
                post.setEntity(params);
                response = client.execute(post);
                String sResponse = readResponse(response);
                System.out.println("MODULE DATA RESPONSE : " + sResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Creates run id for every execution This method is called once for every
     * execution at listener level in static block Created Run Id will be used
     * for subsequent API calls in current execution
     *
     * @param sStatus - status, value will be started
     * @return - Run id will be returned as string object
     */
    public String createRunIDDetails(String sStatus) {
        data.setToken(help.get(TOKEN));
        data.setWs_name(help.get(WSNAME));
        data.setProject_name(help.get(PROJECTNAME));
        data.setStatus(sStatus);

        StringEntity params = null;
        String URL = help.get(CREATERUNIDURL);

        HttpResponse response = null;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URL);
        // add header
        post.setHeader(CONTENTTYPE, HEADER);
        try {
            params = new StringEntity(new ObjectMapper().writeValueAsString(data));
            post.setEntity(params);

            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sResponse = readResponse(response);
        String runID = null;
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            json = (JSONObject) parser.parse(sResponse);
            array = (JSONArray) parser.parse(json.get("data").toString());
            JSONObject array1 = (JSONObject) array.get(0);
            runID = array1.get("runId").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return runID;
    }

    /**
     * Reads response as String object from HttpResponse object
     *
     * @param response - HttpResponse will be parameterized
     * @return - response as String will be returned to called method
     */
    public static String readResponse(HttpResponse response) {
        BufferedReader rd = null;
        StringBuffer result = new StringBuffer();
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

}
