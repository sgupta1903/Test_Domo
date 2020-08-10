package listeners;

import annotations.Blocker;
import config.EnvProperty;
import helper.AppConstants;
import helper.DriverInitilization;
import io.qameta.allure.Allure;
import helper.JiraIssueApi;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeClass;
import reporter.LogToReporterAppender;
import testares.TestAstraListener;
import util.UtilityGeneric;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static helper.AnnotationHelper.*;
import static io.qameta.allure.Allure.addByteAttachmentAsync;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Slf4j
public class TestInitilizationListener extends TestListenerAdapter {
    private EnvProperty envProperty;
    private static final String pattern = "%d{HH:mm:ss.SSS} %-5level %logger{0} - %msg%n";
    private JiraIssueApi jiraIssueApi;
    TestAstraListener Test = new TestAstraListener();

    @Override
    public void beforeConfiguration(ITestResult result) {
        checkBlockers(result);
    }

    @Override
    public void onStart(ITestContext context) {
        Test.onStartAstra(context);
        UtilityGeneric.registerReporterAppender(pattern);
        super.onStart(context);
        context.setAttribute(DriverInitilization.class.getName(), new DriverInitilization((EnvProperty) context.getSuite().getAttribute(EnvProperty.class.getName())));
        this.envProperty = (EnvProperty) context.getSuite().getAttribute(EnvProperty.class.getName());
        jiraIssueApi = new JiraIssueApi(envProperty.getConfigPropertyValue(AppConstants.JIRA, AppConstants.JIRA_URL), envProperty.getConfigPropertyValue(AppConstants.JIRA, AppConstants.JIRA_USERNAME), envProperty.getConfigPropertyValue(AppConstants.JIRA, AppConstants.JIRA_PASSWORD));

    }

    @Override
    public void onTestStart(ITestResult result) {
        Test.onTestStartAstra(result);
        checkBlockers(result);
        System.out.println("Test is starting... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Test.onTestSuccessAstra(result);
        System.out.println("Test has been Passed... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
        attachTestLogs(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test is Skipped... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Test.onTestFailureAstra(result);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
        System.err.println("Test Failed... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
        DriverInitilization driverInitilization = (DriverInitilization) result.getTestContext().getAttribute(DriverInitilization.class.getName());
        if (driverInitilization.getMyDriver() == null) {
            System.out.println("Screenshot not taken because execution has no active driver");
        } else {
            TakesScreenshot screenShot = ((TakesScreenshot) driverInitilization.getDriver());
            File file = screenShot.getScreenshotAs(OutputType.FILE);
            Path content = Paths.get(String.valueOf(file));
            try (InputStream is = Files.newInputStream(content)) {
                Allure.addAttachment(result.getMethod().getMethodName() + "_" + dateFormat.format(new Date(result.getEndMillis())), "image/png", is, ".png");
                attachTestLogs(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private CompletableFuture<byte[]> attachTestLogs(ITestResult currentTestResult) {
        return addByteAttachmentAsync("Test log", "text/plain", LogToReporterAppender.getLogsString(currentTestResult)::getBytes);
    }


    @Override
    public void onFinish(ITestContext testContext) {
        Test.onFinishAstra(testContext);
        super.onFinish(testContext);
        DriverInitilization driverInitilization = (DriverInitilization) testContext.getAttribute(DriverInitilization.class.getName());
        driverInitilization.quitDriver();
    }

    private void checkBlockers(ITestResult tr) {
        Method testMethod = tr.getMethod().getConstructorOrMethod().getMethod();
        List<String> blockers = getBlockers(testMethod.getDeclaringClass(), testMethod);

        if (!blockers.isEmpty()) {
            throw new SkipException("Skipped due to blocker JIRA ticket: " + blockers);
        }
    }

    private List<String> getBlockers(final Class testClass, Method testMethod) {
        return Stream.of(
                getAnnotationsOnMethod(testMethod, Blocker.class).stream(),
                getAnnotationsOnMethodClass(testMethod, Blocker.class).stream(),
                getAnnotationsOnClass(testClass, Blocker.class).stream())
                .reduce(Stream::concat).orElseGet(Stream::empty).distinct()
                .filter(this::isActive).map(Blocker::value).collect(toList());
    }

    private boolean isActive(Blocker annotation) {
        try {
            String issueStatus = jiraIssueApi.getIssueStatus(annotation.value());
            boolean matches = stream(annotation.statuses()).anyMatch(condition -> condition.equalsIgnoreCase(issueStatus));
            if (matches) {
                log.warn(format("JIRA %s has %s status", annotation.value(), issueStatus));
            }
            return matches;
        } catch (Exception | AssertionError e) {
            log.error("Unable to get JIRA status: " + e.getMessage());
            return false;
        }
    }


}
