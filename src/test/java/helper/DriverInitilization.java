package helper;

import config.EnvProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import ru.stqa.selenium.factory.WebDriverPool;

public class DriverInitilization {

    EnvProperty envProperty;
    WebDriver driver;
    String parentWindow;
    String browserLaunch;

    public DriverInitilization(EnvProperty envProperty) {
        this.envProperty = envProperty;
    }

    public WebDriver getDriver() {
        if (this.driver == null) {
            this.driver = initilizingWebDriver();
            this.parentWindow = driver.getWindowHandle();
            this.driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriver getMyDriver() {
        return this.driver;
    }

    @SuppressWarnings("deprecation")
    public WebDriver initilizingWebDriver() {
        String browser = envProperty.getConfigPropertyValue("Default", "browser").toLowerCase();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        switch (browser) {

            case "internet explorer":
                WebDriverManager.iedriver().setup();
                return WebDriverPool.DEFAULT.getDriver(new InternetExplorerOptions());
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
            case "chrome":
                if (ServicePropertyFileReader.getInstance("env").getValue("os").equals("Windows")) {
                    WebDriverManager.chromedriver().setup();
                    return WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
                } else {

                    System.setProperty("webdriver.chrome.driver", ServicePropertyFileReader.getInstance("env").getPropertyValue("os"));
                    ChromeOptions co = new ChromeOptions();
                    browserLaunch = ServicePropertyFileReader.getInstance("env").getPropertyValue("browserLaunch");
                    if ("headless".equals(browserLaunch)) {
                        co.addArguments("--headless");
                    }

                    co.addArguments("--disable-infobars");
                    co.addArguments("--ignore-certifcate-errors");
                    co.addArguments("test-type");
                    co.addArguments("--disable-gpu");
                    co.addArguments("--disable-dev-shm-usage");
                    co.addArguments("--window-size=1920x1080");
                    co.addArguments("--disable-extensions");
                    co.addArguments("--no-sandbox");
                    co.addArguments("--disable-default-apps");
                    co.addArguments("--disable-background-networking");
                    co.addArguments("--disable-background-timer-throttling");
                    co.addArguments("--disable-breakpad");
                    co.addArguments("--disable-client-side-phishing-detection");
                    co.addArguments("--disable-features=site-per-process");
                    co.addArguments("--disable-hang-monitor");
                    co.addArguments("--disable-popup-blocking");
                    co.addArguments("--disable-prompt-on-repost");
                    co.addArguments("--disable-sync");
                    co.addArguments("--disable-translate");
                    co.addArguments("--metrics-recording-only");
                    co.addArguments("--no-first-run");
                    co.addArguments("--safebrowsing-disable-auto-update");
                    co.addArguments("--enable-automation");
                    co.addArguments("--password-store=basic");
                    co.addArguments("--use-mock-keychain");
                    co.addArguments("--hide-scrollbars");
                    co.addArguments("--mute-audio");
                    co.addArguments("--disable-infobars");
                    co.addArguments("--whitelisted-ips");
                    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, co);
                    return new ChromeDriver(desiredCapabilities);
                }
                }
                return null;
        }

        public void quitDriver () {
            if (driver != null) {
                WebDriverPool.DEFAULT.dismissAll();
                driver = null;
                parentWindow = null;
            }
        }


    }
