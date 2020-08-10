package helper;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import helper.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jayway.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
@RequiredArgsConstructor
@Slf4j
public class JiraIssueApi  {
    private final String jiraUrl;
    private final String username;
    private final String password;


    private RequestSpecification jiraApi() {
        return RestAssured.with()
                .baseUri(jiraUrl)
                //.noFiltersOfType()
                .auth()
                .preemptive()
                .basic(username, password)
                .accept(JSON)
                .contentType(JSON);
    }


    public String getIssueStatus(String issueIdOrKey) {
        String status = jiraApi()
                .basePath(AppConstants.JIRA_BASE_PATH)
                .queryParam(AppConstants.FIELDS, AppConstants.STATUS)
                .pathParam(AppConstants.ISSUE_KEY, issueIdOrKey)
                .get()
                .then().statusCode(SC_OK)
                .extract().jsonPath().getString(AppConstants.STATUS_NAME);
        log.debug(format("Status of %s: %s", issueIdOrKey, status));
        return status;
    }
}