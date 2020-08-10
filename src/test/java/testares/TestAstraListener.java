package testares;

import helper.PropFileHelper;
import org.json.simple.JSONObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.*;

import java.util.Map;
import java.util.NoSuchElementException;

import static helper.AppConstants.POSTRESULTTOARESDASHBOARD;

/**
 * TestNG listeners which are called at different levels during test suite
 * execution
 */
public class TestAstraListener extends TestListenerAdapter {

    public static AresDashboard aresDashboard;
    private static String sRunID;
    private String testStartTime = null;
    private String testEndTime = null;
    private static String suiteName = "";
    String filepath = "src/test/resources/TestAstra.ini";
    Map<String, String> help = PropFileHelper.getSensorValue(filepath, "ARES_REPORTING");

    /**
     * Creating AresDashboard object, and calling createRunIDDetails method
     * which will create a unique run Id for every test execution
     */

    public String getRunID() {
        return this.sRunID;
    }

    /**
     * This method will be called if a test case is failed.
     * Purpose - For attaching captured screenshots and videos in ReportNG report
     */


    public void onTestFailureAstra(ITestResult result) {

        // The following code is used to post data to dashboard
        ITestContext context = result.getTestContext();
        testEndTime = UtilityMethods.getDateTimeInSpecificFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String testName = result.getName();
        Exception expObject = new Exception(result.getThrowable());
        String errormessage = expObject.toString().split("\n")[0].substring(20);
        errormessage = JSONObject.escape(errormessage.replaceAll("[\r\n]+", " "));

        String exceptionCategory = null;
        Exception exceptionType = new Exception(result.getThrowable());

        if (exceptionType instanceof NoSuchElementException) {
            exceptionCategory = exceptionType.getMessage().split(System.getProperty("line.separator"))[0].split(":")[0]
                    .replaceAll("[\r\n]+", " ");
        } else if (exceptionType instanceof StaleElementReferenceException) {
            exceptionCategory = "element is not attached to the page document. Hence, Stale Element Reference Exception occured";
        }
        String failStackTrace = UtilityMethods.getStackTraceFromListners(result.getThrowable());

        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
            aresDashboard.postTestResults(getRunID(), suiteName, result.getTestContext().getName(), testName, "FAILED", "-",
                    errormessage, "-", "chrome", testStartTime, testEndTime);
        }

    }

    /**
     * This method will be called if a test case is skipped.
     */
    public void onTestSkipped(ITestResult result) {

    }

    /**
     * This method will be called if a test case is passed. Purpose - For
     * attaching captured videos in ReportNG report
     */
    public void onTestSuccessAstra(ITestResult result) {

        // The following code is used to post data to dashboard
        ITestContext context = result.getTestContext();
        String testName = result.getName();
        testEndTime = UtilityMethods.getDateTimeInSpecificFormat("yyyy-MM-dd HH:mm:ss.SSS");

        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
            aresDashboard.postTestResults(getRunID(), suiteName, result.getTestContext().getName(), testName, "PASSED", "-",
                    "-", "-", UtilityMethods.getBrowserName("Chrome"), testStartTime, testEndTime);
        }
    }

    /**
     * This method will be called before a test case is executed. Purpose - For
     * starting video capture and launching balloon popup in ReportNG report
     *
     * @param result - ITestResult object
     */
    public void onTestStartAstra(ITestResult result) {

//		 To capture test start time, used to post data to automation dashboard
        testStartTime = UtilityMethods.getDateTimeInSpecificFormat("yyyy-MM-dd HH:mm:ss.SSS");

    }

    /**
     * Invoked after test class in instantiated
     * <p>
     * We're capturing number of tests in a module, sending tests count to dashboard in post module data method
     */

    public void onStartAstra(ITestContext context) {

        int noOfTests = 0;
        ITestNGMethod[] method = context.getAllTestMethods();
        for (ITestNGMethod m : method) {
            noOfTests++;
        }

        System.out.println("ONSTART: " + context.getName());
        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
            // posting module data to dashboard at the test starting
            aresDashboard.postModuleData(getRunID(), context.getName(), String.valueOf(noOfTests), "started");
        }
    }

    /**
     * Invokes after all the test runs
     * <p>
     * We're capturing test count and sending test count to dashboard
     */

    public void onFinishAstra(ITestContext context) {


//		The following code captures number of tests in module
        int noOfTests = 0;
        ITestNGMethod[] method1 = context.getAllTestMethods();
        for (ITestNGMethod m : method1) {
            noOfTests++;
        }

        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
//	 		posting module data to dashboard at the test ending
            aresDashboard.postModuleData(getRunID(), context.getName(), String.valueOf(noOfTests), "ended");
        }


    }

    public void onFinishAres(ISuite arg0) {

        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
            sRunID = aresDashboard.createRunIDDetails("ended");
        }
    }

    /**
     * This method starts before suite execution starts
     */
    public void onStartAstra(ISuite iSuite) {
        String filepath = "src/test/resources/TestAstra.ini";
        Map<String, String> help = PropFileHelper.getSensorValue(filepath, "ARES_REPORTING");

        aresDashboard = new AresDashboard();

        if (help.get(POSTRESULTTOARESDASHBOARD).equals("true")) {
            sRunID = aresDashboard.createRunIDDetails("started");
        }

        // Suite name is to store data to dashboard
        suiteName = iSuite.getName();
    }

}
