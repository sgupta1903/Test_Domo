package testares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GraphData {
    private String runId;
    private String productName;
    private String moduleName;
    private String testcaseTitle;
    private String testStatus;
    private String testData;
    private String failStacktrace;
    private String testBrowser;
    private String testMachine;
    private String imageLink;
    private String videoLink;
    private String testDevice;
    private String testOs;
    private String testDate;
    private String testStartTime;
    private String testEndTime;
    private String testSuite;
    private String runBy;
    private String errorMessage;
    private String executionMode;
    private String failType;

}
