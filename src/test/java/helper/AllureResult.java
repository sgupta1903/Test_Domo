package helper;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static helper.AppConstants.*;


@Slf4j
public class AllureResult {
    public void setUpAllureEnvironment() {
        log.info("Environment Set up for allure has started");

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put(ENVIRONMENT, ServicePropertyFileReader.getInstance(ENV).getValue(ENV))
                        .put(BROWSER, ServicePropertyFileReader.getInstance(ENV).getValue(BROWSER))
                        .put(OPERATING_SYSTEM, ServicePropertyFileReader.getInstance(ENV).getValue(OS))
                        .put(BROWSER_LAUNCH, ServicePropertyFileReader.getInstance(ENV).getValue(BROWSER_LAUNCH))
                        .build(), System.getProperty("user.dir") + ALLURE_RESULTS);
    }
}