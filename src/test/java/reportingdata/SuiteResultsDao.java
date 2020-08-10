package reportingdata;

import org.testng.ISuite;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import java.util.List;

public class SuiteResultsDao {

    private List<ISuite> suites;
    private List<SuiteResults> suiteResultsLst;

    public SuiteResultsDao(List<ISuite> suites) {
        this.suites = suites;
    }

    public List<SuiteResults> getSuiteResultsLst() {

        suiteResultsLst = new ArrayList<SuiteResults>();
        DateFormat dfTest = new SimpleDateFormat("HH:mm:ss");
        DateFormat dfSuite = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a, z");

        SuiteResults suiteData;
        //Iterating over each suite included in the test
        for (ISuite suite : suites) {
            suiteData = new SuiteResults();
            int total = 0, passed = 0, failed = 0, skipped = 0;
            int suiteDuration = 0;
            Date suiteStartTime = null, suiteEndTime = null;
            List<TestResults> successLst = new ArrayList<>();
            List<TestResults> failedLst = new ArrayList<>();

            //Getting the results for the said suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();

            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                Set<ITestResult> pass = tc.getPassedTests().getAllResults();
                Set<ITestResult> fail = tc.getFailedTests().getAllResults();
                Set<ITestResult> skip = tc.getSkippedTests().getAllResults();

                passed += pass.size();
                failed += fail.size();
                skipped += skip.size();
                total = passed + failed + skipped;

                TestResults tstResult;
                long testDuration;
                Date testStartTime;
                Date testEndTime;
                for (ITestResult tr : pass) {
                    tstResult = new TestResults();
                    tstResult.setTestName(tr.getTestClass().getName());
                    tstResult.setSectionName(tr.getName());
                    testStartTime = new Date(tr.getStartMillis());
                    tstResult.setTestStartTime(dfTest.format(testStartTime));
                    testEndTime = new Date(tr.getEndMillis());
                    tstResult.setTestEndTime(dfTest.format(testEndTime));
                    testDuration = TimeUnit.MILLISECONDS.toSeconds(tr.getEndMillis() - tr.getStartMillis());
                    tstResult.setTestDuration(testDuration);

                    successLst.add(tstResult);

                    suiteDuration += testDuration;

                    if (suiteStartTime == null) {
                        suiteStartTime = testStartTime;
                    } else {
                        if (suiteStartTime.after(testStartTime)) {
                            suiteStartTime = testStartTime;
                        }
                    }

                    if (suiteEndTime == null) {
                        suiteEndTime = testEndTime;
                    } else {
                        if (suiteEndTime.before(testEndTime)) {
                            suiteEndTime = testEndTime;
                        }
                    }

                } //end of passed test results

                for (ITestResult tr : fail) {
                    tstResult = new TestResults();
                    StackTraceElement[] elements = tr.getThrowable().getStackTrace();
                    for (StackTraceElement ste : elements) {
                        if (ste.getClassName().equalsIgnoreCase(tr.getTestClass().getName())) {
                            tstResult.setErrLineNo(ste.getLineNumber());
                        }
                    }

                    tstResult.setTestName(tr.getTestClass().getName());
                    tstResult.setSectionName(tr.getName());
                    testStartTime = new Date(tr.getStartMillis());
                    tstResult.setTestStartTime(dfTest.format(testStartTime));
                    testEndTime = new Date(tr.getEndMillis());
                    tstResult.setTestEndTime(dfTest.format(testEndTime));
                    testDuration = TimeUnit.MILLISECONDS.toSeconds(tr.getEndMillis() - tr.getStartMillis());
                    tstResult.setTestDuration(testDuration);
                    tstResult.setErrorMessage(tr.getThrowable().getMessage());

                    failedLst.add(tstResult);

                    suiteDuration += testDuration;

                    if (suiteStartTime == null) {
                        suiteStartTime = testStartTime;
                    } else {
                        if (suiteStartTime.after(testStartTime)) {
                            suiteStartTime = testStartTime;
                        }
                    }

                    if (suiteEndTime == null) {
                        suiteEndTime = testEndTime;
                    } else {
                        if (suiteEndTime.before(testEndTime)) {
                            suiteEndTime = testEndTime;
                        }
                    }

                } //end of failed test results


                for (ITestResult tr : skip) {
                    //TO DO
                } //end of skipped test results

            }

            suiteData.setSuiteName(suite.getName());
            if (suiteStartTime != null) {
                suiteData.setSuiteStartTime(dfSuite.format(suiteStartTime));
            }
            if (suiteEndTime != null) {
                suiteData.setSuiteEndTime(dfSuite.format(suiteEndTime));
            }
            suiteData.setSuiteDuration(suiteDuration);

            suiteData.setTotalTests(total);
            suiteData.setTotalPassed(passed);
            suiteData.setTotalFailed(failed);
            suiteData.setTotalSkipped(skipped);
            suiteData.setPassedTests(successLst);
            suiteData.setFailedTests(failedLst);

            //Adding to list of suites
            suiteResultsLst.add(suiteData);
        } //end of suite results loop

        return suiteResultsLst;

    } //end of method

}
