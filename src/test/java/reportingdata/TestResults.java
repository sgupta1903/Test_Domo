package reportingdata;

import org.apache.commons.lang3.StringUtils;

public class TestResults {

    private String testName;
    private String sectionName;
    private int errLineNo;
    private String errorMessage;
    private String screenshotLink;
    private String testStartTime;
    private String testEndTime;
    private long testDuration;

    public String getTestName() {
        if(StringUtils.contains(testName, "."))
            return StringUtils.substringAfterLast(testName, ".");
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public int getErrLineNo() {
        return errLineNo;
    }
    public void setErrLineNo(int errLineNo) {
        this.errLineNo = errLineNo;
    }
    public String getErrorMessage() {
        if(StringUtils.contains(errorMessage, "Build info:"))
            return StringUtils.substringBefore(errorMessage, "Build info:");
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getScreenshotLink() {
        return screenshotLink;
    }
    public void setScreenshotLink(String screenshotLink) {
        this.screenshotLink = screenshotLink;
    }
    public String getTestStartTime() {
        return testStartTime;
    }
    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }
    public String getTestEndTime() {
        return testEndTime;
    }
    public void setTestEndTime(String testEndTime) {
        this.testEndTime = testEndTime;
    }
    public long getTestDuration() {
        return testDuration;
    }
    public void setTestDuration(long testDuration) {
        this.testDuration = testDuration;
    }

}
