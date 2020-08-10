package listeners;


import config.EnvProperty;
import helper.AllureResult;
import helper.ServicePropertyFileReader;
import helper.XrayExecutionResultGeneration;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import testares.TestAstraListener;

import java.io.File;
import java.io.IOException;

import static helper.AppConstants.*;

public class SuiteInitilizationListerner implements ISuiteListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuiteInitilizationListerner.class);
    private AllureResult allureResult = new AllureResult();
    TestAstraListener testAstra = new TestAstraListener();

    @Override
    public void onStart(ISuite suite) {

        try {
            testAstra.onStartAstra(suite);
            Ini ini = new Wini(this.getClass().getResourceAsStream('/' + ServicePropertyFileReader.getInstance(ENV).getPropertyValue(ENV) + '/' + PROPERTY_INI));
            LOGGER.info("Running on " + ServicePropertyFileReader.getInstance(ENV).getPropertyValue(ENV) + " Environment");
            EnvProperty envProperty = new EnvProperty(ini);
            suite.setAttribute(EnvProperty.class.getName(), envProperty);
            FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + ALLURE_RESULTS));


        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        testAstra.onFinishAres(suite);
        LOGGER.info("On finish : Allure environment set up will start running");
        allureResult.setUpAllureEnvironment();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOGGER.info("Shutdown Hook is running !");
                new XrayExecutionResultGeneration().generateXrayResult();
            }
        });
        LOGGER.info("Application Terminating ...");

    }
}