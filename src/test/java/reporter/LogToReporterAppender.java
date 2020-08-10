package reporter;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;
import org.testng.Reporter;

public class LogToReporterAppender<LoggerEvent> extends AppenderBase<LoggerEvent> {

    private static PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    private String name = "REPORTER";


    public static String getLogsString(ITestResult testResult) {
        boolean hasSeparator = encoder.getPattern().endsWith("%n");
        return StringUtils.join(Reporter.getOutput(testResult), hasSeparator ? "" : "\n");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void append(LoggerEvent event) {
        String log = encoder.getLayout().doLayout((ILoggingEvent) event);
        Reporter.log(log);
    }

    public void setEncoder(PatternLayoutEncoder encoder) {
        LogToReporterAppender.encoder = encoder;
    }
}