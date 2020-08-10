package ui;

import config.EnvProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Slf4j
public class AbstractPage<T extends AbstractPage<T>> {
    private WebDriver driver;
    private EnvProperty envProperty;
    String parentHandle = null;
    private static final int DEFAULT_TIMEOUT = 100;
    SoftAssert softassert = new SoftAssert();
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public ArrayList<String> tabs = null;

    public String getName() {
        return me().getClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected T me() {
        return (T) this;
    }

    public T wait_until(int sleepInSecs) {
        try {
            Thread.sleep(sleepInSecs * 1000);
        } catch (IllegalArgumentException | InterruptedException ie) {
            Assert.fail("Unable to wait for the given time(in secs): " + sleepInSecs);
        }

        return me();
    }

    public T loadUrl(String configUrl) {
        String baseUrl = getConfigParamValue(configUrl);
        String pageUrl = getUrl();
        Assert.assertNotNull(baseUrl + pageUrl);
        this.driver.get(baseUrl + pageUrl);
        return me();
    }

    public T loadBaseUrl(String Url) {
        String baseUrl = Url;
        Assert.assertNotNull(baseUrl);
        this.driver.get(baseUrl);
        return me();
    }

    public T load(String section, String configUrl) {
        String baseUrl = getConfigParamValue(section, configUrl);
        String pageUrl = getUrl();
        Assert.assertNotNull(baseUrl + pageUrl);
        this.driver.get(baseUrl + pageUrl);
        return me();
    }

    public T loadUrl(String baseUrl, String relUrl) {
        String baseurl = getConfigParamValue(baseUrl);
        Assert.assertNotNull(baseurl + relUrl);
        this.driver.get(baseurl + relUrl);
        return me();
    }

    protected String getConfigParamValue(String param) {
        Map<String, String> applicationIDSection = this.envProperty.get(getApplicationId().toUpperCase());
        Assert.assertNotNull(String.valueOf(applicationIDSection), "No configuration section provided in configuration file");

        String paramValue = applicationIDSection.get(param);
        Assert.assertNotNull(paramValue);
        return paramValue;
    }

    public T switch_to_parent_window() {
        driver.switchTo().window(parentHandle); // switch back to the original window
        return me();
    }

    protected String getConfigParamValue(String section, String param) {
        Map<String, String> applicationIDSection = this.envProperty.get(section);
        Assert.assertNotNull(String.valueOf(applicationIDSection), "No configuration section provided in configuration file");

        String paramValue = applicationIDSection.get(param);
        Assert.assertNotNull(paramValue);
        return paramValue;
    }


    public String getApplicationId() {
        return "ABC";
    }

    protected String getUrl() {
        return "";
    }


    /**
     * Verify passed condition, wait for {@link EnvProperty#getTimeout()} before throwing
     * {@link TimeoutException}
     *
     * @param condition {@link ExpectedCondition} to verify.
     * @see WebDriverWait
     */
    @SuppressWarnings("hiding")
    protected <T> T verify(ExpectedCondition<T> condition) {

        return verify(condition, this.envProperty.getTimeout(), DEFAULT_TIMEOUT);
    }

    @SuppressWarnings("hiding")
    protected <T> T verify(ExpectedCondition<T> condition, long timeout, long sleep) {
        return new WebDriverWait(this.driver, timeout, sleep)
                .ignoring(NotFoundException.class, ElementNotVisibleException.class)
                .until(condition);
    }

    public T refresh_until(final By locator) {

        verify(new ExpectedCondition<Boolean>() {
            int loop = 5;

            @Override
            public Boolean apply(WebDriver driver) {
                while (loop-- > 0) {
                    if (driver.findElements(locator).size() > 0) {
                        return true;
                    }
                    // else, we need to refresh the page and check again.
                    driver.navigate().refresh();
                }
                // exhausted number of max attempts
                return false;
            }
        });

        return me();
    }


    public T wait_until(final ExpectedCondition<Boolean> condition,
                        final Callable action, long timeOutInSeconds, long sleepInMillis) {

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {

                try {
                    verify(condition, 0, 0);
                    return Boolean.TRUE;
                } catch (TimeoutException te) {
                    try {
                        action.call();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return Boolean.FALSE;
            }
        }, timeOutInSeconds, sleepInMillis);

        return me();
    }


    protected T wait_until(ExpectedCondition<WebElement> condition) {
        verify(condition);
        return me();
    }

    public T handle_browser_exceptions() {
        if (this.driver instanceof InternetExplorerDriver
                || this.driver instanceof RemoteWebDriver) {
            while (this.driver.getTitle().contains("Certificate Error")) {
                this.driver.get("javascript:document.getElementById('overridelink').click()");
            }
        }
        return me();
    }

    public T enter(By locator, String text) {
        WebElement textField = findElementAndVisible(locator);
        textField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        textField.clear();
        try {
            textField.sendKeys(text);
        } catch (StaleElementReferenceException ex) {
            textField = findElementAndVisible(locator);
            textField.sendKeys(text);
        }
        return me();
    }

    public T enter_by_action(By locator, String text) {
        WebElement textField = findElementAndVisible(locator);
        Actions myaction = new Actions(driver);
        myaction.moveToElement(textField).click().sendKeys(text).build().perform();
        return me();
    }

    public T enter_by_key() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        return me();
    }

    public T press_key_down() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).build().perform();
        return me();
    }

    private WebElement findElementAndVisible(By locator) {
        return verify(visibilityOfElementLocated(locator));
    }


    protected T click_mouseover(By locator) {
        WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
        mouseover(locator);
        clickable.click();
        return me();
    }


    protected T click_script(By locator) {
        WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", clickable);
        return me();
    }


    private WebElement findElement(ExpectedCondition<WebElement> condition) {
        return verify(condition);
    }

    protected T mouseover(By locator) {
        Actions mouseover = new Actions(driver);
        mouseover.moveToElement(findElementAndVisible(locator)).build().perform();
        return me();
    }

    protected T mouseover_with_click(By locator) {
        Actions mouseover = new Actions(driver);
        mouseover.moveToElement(findElementAndVisible(locator)).click().build().perform();
        return me();
    }


    public T browse_file(String filePath) {
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            Assert.fail("Unable to browse the file, path is: " + filePath);
        }

        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        wait_until(5);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        wait_until(2);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait_until(5);

        return me();
    }

    public T press_down_key() {
        Robot robot = null;

        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
        } catch (AWTException e) {
            Assert.fail("Unable to perform down key action");
        }

        return me();
    }

    public T press_end_key() {
        Robot robot = null;

        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_END);
        } catch (AWTException e) {
            Assert.fail("Unable to perform down key action");
        }

        return me();
    }

    public T press_enter_key() {
        Robot robot = null;

        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            Assert.fail("Unable to perform enter key action");
        }

        return me();
    }

    private Select findSelect(By locator) {
        final Select selectable = new Select(findElement(ExpectedConditions.elementToBeClickable(locator)));

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return selectable.getOptions().size() > 1;
            }

            @Override
            public String toString() {
                return String.format("element (%s) size > 1", selectable);
            }
        });

        return selectable;

    }

    private WebElement findElement(By locator) {
        return verify(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public T scan(By locator, String barcode) {
        WebElement body = findElement(locator);
        body.sendKeys("~" + barcode + Keys.RETURN);
        return me();
    }

	    /*public T click_button(By locator) {
	        click(locator);
	        return me();
	    }*/

    public T click_element(By locator) {
        click(locator);
        return me();
    }

    public T press_enter_key(By locator) {
        WebElement textField = findElementAndVisible(locator);
        textField.clear();
        try {
            textField.sendKeys(Keys.ENTER);
        } catch (StaleElementReferenceException ex) {
            textField = findElementAndVisible(locator);
            textField.sendKeys(Keys.ENTER);
        }
        return me();

    }

    public T click_link_repeat(By locator) {
        try {
            click(locator);
        } catch (ElementNotVisibleException enve) {
            click_link_repeat(locator);
        }
        return me();
    }

    public T click_checkbox(By locator) {
        driver.findElement(locator).click();
        return me();
    }


    public T submit(By locator) {
        WebElement submittable = findElementAndVisible(locator);
        submittable.submit();
        return me();
    }

    protected T click(By locator) {
        WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
        clickable.click();
        return me();
    }

    public T select_by_value(By locator, String value) {
        Select selectable = findSelect(locator);
        selectable.selectByValue(value);
        return me();
    }

    public T select_by_name(By locator, String name) {
        Select selectable = findSelect(locator);
        selectable.selectByVisibleText(name);
        return me();
    }

    public T deselect_all(By locator) {
        Select selectable = findSelect(locator);
        selectable.deselectAll();
        return me();
    }

    public T zoom_in(int xTimes) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            for (int i = 0; i < xTimes; i++) {
                robot.keyPress(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_ADD);
            }
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            Assert.fail("Unable to Zoom In: " + e.getMessage());
        }
        return me();
    }

    public T zoom_out(int xTimes) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            for (int i = 0; i < xTimes; i++) {
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
            }
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            Assert.fail("Unable to Zoom Out: " + e.getMessage());
        }
        return me();
    }

    public T capture_screenshot() {
        String screenShotsLocation = envProperty.getConfigPropertyValue("REPORTING", "screenShotsLocation");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

        String path = screenShotsLocation + "/" + getName() + "_" + sdf.format(new Date());

        return capture_screenshot(path);
    }

    public T capture_screenshot(String path) {

        if (!path.isEmpty()) {
            TakesScreenshot screenShot = (TakesScreenshot) this.driver;
            File file = screenShot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(path + ".png"));
            } catch (IOException e) {
                System.out.println("Unable to take screenshot for UI page: " + getName() + " with the file name: " + path + ".png");
            }
        }

        return me();
    }

    public T scroll_down_to_page(String xOffset, String yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ")");
        return me();
    }

    public T scroll_down_to_page(By locator, int xOffset, int yOffset) {
        WebElement scrollDownPage = findElementAndVisible(locator);
        Actions myaction = new Actions(driver);
        myaction.moveToElement(scrollDownPage).clickAndHold().moveByOffset(xOffset, yOffset).release().build().perform();
        myaction.sendKeys(Keys.PAGE_DOWN).build().perform();
        return me();
    }

    public T refesh_page(By locator) {
        WebElement body = findElement(locator);
        body.sendKeys(Keys.F5);
        /* driver.navigate().refresh();*/
        return me();
    }

    public T alert_comments(String text) {
        this.driver.switchTo().alert().sendKeys(text);
        return me();
    }

    public T alert_accept() {
        this.driver.switchTo().alert().accept();
        return me();
    }

    public T alert_cancel() {
        this.driver.switchTo().alert().dismiss();
        return me();
    }

    public T alert_escape() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        return me();
    }


    public String find_element_value(By locator) {
        return findElement(locator).getAttribute("value");
    }

    protected String find_element_attribute_value(By locator, String attributeName) {
        return findElement(locator).getAttribute(attributeName);
    }

    protected String find_element_attribute_value_without_wait(By locator, String attributeName) {
        return this.driver.findElement(locator).getAttribute(attributeName);
    }

    public String find_element_text(By locator) {
        return findElement(locator).getText();
    }

    public boolean is_text_present_on_page(String text) {
        return driver.getPageSource().contains(text);
    }

    public int find_element_count(By locator) {
        return driver.findElements(locator).size();
    }

    public String find_element_text_without_wait(By locator) {
        return driver.findElement(locator).getText();
    }

    public String find_current_url() {
        return this.driver.getCurrentUrl();
    }

    public T verify_element_selected(By locator) {
        verify(ExpectedConditions.elementToBeSelected(locator));
        return me();
    }

    public Boolean is_element_selected(By locator) {
        WebElement element = findElement(locator);
        return element.isSelected();
    }

    public Boolean is_element_enabled(By locator) {
        WebElement element = findElement(locator);
        return element.isEnabled();
    }

    public Boolean is_element_displayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public Boolean is_element_exist(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public T verify_element_disabled(By locator) {
        final WebElement element = findElement(locator);

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                if (!(element.isEnabled())) {
                    return Boolean.TRUE; //element is disabled
                }
                return Boolean.FALSE; //element is enabled
            }

            @Override
            public String toString() {
                return String.format("element (%s) to be disabled", element);
            }
        });
        return me();
    }

    public T verify_element_enabled(By locator) {
        final WebElement element = findElement(locator);

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                if (element.isEnabled()) {
                    return Boolean.TRUE; //element is enabled
                }
                return Boolean.FALSE; //element is disabled
            }

            @Override
            public String toString() {
                return String.format("element (%s) to be enabled", element);
            }
        });
        return me();
    }

    public T verify_element_not_present(By locator) {
        verify(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)));

        return me();
    }


    public T verify_element_by_text(final By locator, final String text) {

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return text.equalsIgnoreCase(find_element_text(locator));
                } catch (StaleElementReferenceException e) {
                    return Boolean.FALSE;
                }
            }

            @Override
            public String toString() {
                return String.format(" Expected: [%s] but found: [%s], locator is: [%s]", text, find_element_text(locator), locator);
            }
        });

        return me();
    }

    public T verify_value_matches(String attrValue1, String attrValue2) {

        Assert.assertEquals(attrValue1, attrValue2, "Values did not match");
        return me();
    }

    public T verify_value_matches_int(int attrValue1, int attrValue2) {

        Assert.assertEquals(attrValue1, attrValue2, "Values did not match");
        return me();
    }

    public T verify_element_by_text_partial_match(By locator, String text) {
        verify(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        return me();
    }

    public T verify_element_by_value(By locator, String text) {
        return verify_element_attribute_value(locator, "value", text);
    }

    public T verify_element_attribute_value(By locator, String attrName, String attrValue) {
        WebElement element = findElement(locator);
        Assert.assertEquals(element.getAttribute(attrName), attrValue);

        return me();
    }

    protected T verify_element_by_text_continue(By locator, String text) {
        String actual = find_element_text(locator);
        softassert.assertEquals(actual, text, "\n Expected : [" + text + "] but found : [" + actual + "] ");
        return me();
    }

    public T verify_continue_or_stop() {
        softassert.assertAll();
        return me();
    }

    public void refresh_page() {
        driver.navigate().refresh();
    }

    protected T clear_session() {
        driver.manage().deleteAllCookies();
        return me();
    }

    public T switch_to_frame(String nameOrId) {
        verify(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
        return me();
    }

    public T switch_to_window() {
        parentHandle = driver.getWindowHandle(); // get the current window handle
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        return me();
    }

    public T switch_to_frame(By locator) {
        verify(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        return me();
    }

    public T switch_to_root_page() {
        driver.switchTo().defaultContent();
        return me();
    }


    protected T drag_n_drop(By source, By target) {
        new Actions(driver).dragAndDrop(findElementAndVisible(source), findElementAndVisible(target)).perform();
        return me();
    }

    protected T click_and_handle_stale_element(By locator) {
        int count = 1;
        while (count == 1) {
            try {
                WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
                clickable.click();
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            count++;
        }
        return me();
    }

    public String handle_stale_find_element_text(By locator) {
        int count = 1;
        while (count <= 5) {
            try {
                WebElement webElement = findElement(locator);
                return webElement.getText();
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            count++;
            wait_until(1);
        }
        return null;
    }

    protected T clickMe(By locator) {
        WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
        mouseover(locator);
        clickable.click();
        return me();
    }


    protected T select_dropdown_temp(By locator) {
        WebElement element = findElement(ExpectedConditions.elementToBeClickable(locator));
        org.openqa.selenium.Point location = element.getLocation();
        Robot robot;
        try {
            robot = new Robot();
            robot.mouseMove(location.getX() + 10, location.getY() + 100);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
            softassert.fail("Unable to click mouse through mouse Error Message: " + e.getMessage());
        }
        return me();
    }


    protected T double_click(By locator) {
        WebElement clickable = findElement(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver).moveToElement(clickable).doubleClick(clickable).perform();
        return me();
    }


    public T wait_until_SelectOption(By locator, final String option) {
        final Select selectable = new Select(
                findElement(ExpectedConditions.elementToBeClickable(locator)));

        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return selectable.getFirstSelectedOption().getText()
                        .equals(option);
            }

            @Override
            public String toString() {
                return String.format("element (%s) selected option matches to the required option", selectable);
            }
        });

        return me();
    }

    public T wait_until_text_appears_on_page(final String text) {
        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getPageSource().contains(text);
            }

            @Override
            public String toString() {
                return String.format("page contains the text: (%s)", text);
            }
        });

        return me();
    }

    public T wait_until_page_loaded() {
        verify(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
            }

            @Override
            public String toString() {
                return String.format("The page is not loaded completly within the timeout");
            }
        });

        return me();
    }

    public T print_element(By element) {
        WebElement webElement = findElement(element);
        if (webElement != null) {
            System.out.println((webElement.getAttribute("innerHTML")));
        }
        return me();
    }

    public T scroll_to_element(By element) {
        WebElement webElement = findElement(element);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("arguments[0].scrollIntoView();", webElement);
        return me();
    }

    public String encode_content(String content) {
        return Base64.getEncoder().encodeToString(content.getBytes());
    }

    public String decode_content(String content) {
        byte[] decodedBytes = Base64.getDecoder().decode(content);
        return new String(decodedBytes);
    }

    public T scroll_up_down(int up, int down) {
        js.executeScript("window.scrollBy(" + up + "," + down);
        return me();
    }

    public T switch_to_new_tab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return me();
    }

    public T switch_to_parent_tab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return me();
    }

    public T close_child_tab() {
        String parent = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
        return me();
    }

    public String copy_url() {
        wait_until(2);
        String url = driver.getCurrentUrl().replace(getConfigParamValue("REST_CLIENT_DETAILS", "orgIdUrl"), "").replace("/locations", "");
        return url;
    }

    public T verify_all(Runnable... runnables) throws AssertionError {
        verify_all("The following asserts failed", runnables);
        return  me();
    }

    public T verify_all(String message, Runnable... runnables) throws AssertionError {
        List<Throwable> throwables = Collections.synchronizedList(new ArrayList<>());

        ExecutorService executor = Executors.newFixedThreadPool(runnables.length);

        Stream.of(runnables)
                .map(r -> CompletableFuture.runAsync(r, executor))
                .parallel()
                .forEach(it -> {
                            try {
                                it.get();
                            } catch (InterruptedException e) {
                                log.warn("### thread was interrupted");
                            } catch (ExecutionException e) {
                                throwables.add(e);
                            }
                        }
                );

        assert_all(message, throwables);
        return me();
    }

    private void assert_all(String msg, List<Throwable> throwables) {
        if (!throwables.isEmpty()) {
            StringBuilder sb = new StringBuilder(msg + ":\n");
            boolean first = true;
            for (Throwable throwable : throwables) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(throwable.getMessage());
            }
            throw new AssertionError(sb.toString());
        }
    }
}
