package com.model.utility;

import com.model.base.BasePage;
import com.model.locators.WelcomePage_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_Home_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

import static com.model.utility.Alerts_and_Requests.handleAlertAndReturnText;
import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.base.Constants.*;
import static com.model.base.Constants.IC_Servlets.*;
import static com.model.base.Constants.ModelApplications.*;
import static com.model.base.Constants.UVMod;
import static java.time.Duration.ofSeconds;

public class Model_CommonFunctions {
    public static WebDriver driver;
    public static String env;
    public static String env2;
    public static String environment;
    public static Date date = new Date();
    public static LocalDate currentDate = LocalDate.now();
    public static String expectedPwdEnd = String.format("%02d", currentDate.getYear() - 2000) +
            String.format("%02d", currentDate.getMonthValue()) + "01";
    public static String expectedPwd; //cannot be initialized until properties are read.
    public static LocalDate previousDate = currentDate.minusMonths(1);
    public static String previousPwdEnd = String.format("%02d", previousDate.getYear() - 2000) +
            String.format("%02d", previousDate.getMonthValue()) + "01";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    public static String Date = dateFormat.format(date);
    public static Logger log = LoggerFactory.getLogger(Model_CommonFunctions.class);
    public static WebDriverWait miniWait;
    public static WebDriverWait wait;
    public static WebDriverWait longWait;
    public static Wait fluentWait;
    public final static Duration TIMEOUT = ofSeconds(10);
    public final static Duration LONG_TIMEOUT = ofSeconds(30);
    public final static Duration PAGE_LOAD_TIMEOUT = ofSeconds(60);
    public static List<HashMap<String, String>> userData;
    public static List<HashMap<String, String>> appData;
    public static List<HashMap<String, String>> data4;
    public String actualStatus = null;
    public static String status = null;
    public static String testName;
    public static String button;
    public static String textA = null;
    public static String textB;
    public static String textVal = null;
    public static String actualText = null;
    public static String expectedText = null;
    public static String justification = null;
    WelcomePage_Locators CMMI_ApplicationsLocators;

    public Model_CommonFunctions() {
        Portal_UserRegistration_Step2_Locators ICPageLocators = new Portal_UserRegistration_Step2_Locators();
        PageFactory.initElements(driver, ICPageLocators);
        Portal_AppConsole_Home_Locators appConsoleLocators = new Portal_AppConsole_Home_Locators();
        PageFactory.initElements(driver, appConsoleLocators);
        CMMI_ApplicationsLocators = PageFactory.initElements(driver, WelcomePage_Locators.class);
        if (userData == null) userData = DataHelper.data("testData2.xlsx", env);
        if (data4 == null) data4 = DataHelper.data("NewUserRegData.xlsx", "Roles");
    }

    // the prefix defines in  POM.xml file
    public static String getExpectedPwd() {
        if (expectedPwd == null) expectedPwd = BasePage.prop.getProperty("password.prefix.current") + expectedPwdEnd;
        return expectedPwd;
    }

    public static HashMap<String, String> getUserByUserNumberFromExcelFile(String userNumber) {
        return getRowByDataSrcColHeaderAndValue(userData, "UserNumber", userNumber);
    }

    public static HashMap<String, String> getRowByDataSrcColHeaderAndValue(List<HashMap<String, String>> dataSrc,
                                                                           String colHeaderName, String value) {
        for (HashMap<String, String> row : dataSrc) {
            if (row.get(colHeaderName).equals(value)) return row;
        }
        Assert.fail("No row found by Column:" + colHeaderName + " : Value: " + value);
        return null;
    }

    public static List<HashMap<String, String>> getMultipleRowsByDataSrcColHeaderAndValue(List<HashMap<String, String>> dataSrc, String colHeader, String value) {
        List<HashMap<String, String>> matchingRows = new ArrayList<>();
        for (HashMap<String, String> row : dataSrc) {
            if (row.get(colHeader).equals(value)) matchingRows.add(row);
        }
        return matchingRows;
    }

    public static void switchToTheModule(String feature) throws Exception {
        waitForCommonPageLoadingElements();
        String envVal = environment.endsWith("SB") ? "sb" : "";
        click(locateElementByXPath("//div[@id='cms-ic" + envVal + "-tile']//div[@role='application']"));
        click(locateEleByXPathTextNormSpaceAttr("a", feature));
        log.info("Clicked on portlet link: " + feature);
        waitForCommonPageLoadingElements();
        longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locateElementByCSS("object")));
        longWaitForElementAllowNull("//h1", "xpath");
        waitForCommonPageLoadingElements();
    }

    public static void selectByHeaderLink(String icHeader, String columnHeader, String link) throws Throwable {
        waitForCommonPageLoadingElements();
        endUsingIframe();
        waitForCommonPageLoadingElements();
        //ENH: Only tested for "My Apps", but may be adapted for more flexibillity
        String popUp = "//div[@id='" + toCamelCase(icHeader) + "Parent']";
        if (!isElementPresentJSEByXPath(popUp)) {
            click("//button[@title='" + icHeader + "']");
            waitForCommonPageLoadingElements();
            if (!isElementPresentJSEByXPath(popUp))
                Assert.fail("FAIL: ERROR: No popup submenu for: " + icHeader + " : EXPECTED POPUP: " + popUp);
        }
        String envVal = columnHeader.equals("IC") && environment.endsWith("SB") ? " SB" : "";
        click(popUp + "//*[text()='" + columnHeader + envVal + "']" + "/ancestor::ul//li//a[contains(text(), '" + link + "')]");
        waitForCommonPageLoadingElements();
        startUsingIframe();
        waitForCommonPageLoadingElements();
    }

    public static WebElement locateElementByXPathText(String text) throws Exception {
        return getElementByLocatorAndSearchType("//*[text() = '" + text + "']", "xpath");
    }

    public static WebElement locateElementByXPathContainsText(String text) {
        return getElementByLocatorAndSearchType("//*[contains(text(), '" + text + "')]", "xpath");
    }

    public static WebElement locateElementByXPath(String xpath) throws Exception {
        return getElementByLocatorAndSearchType(xpath, "xpath");
    }

    public static WebElement locateElementByID(String idText) {
        return getElementByLocatorAndSearchType(idText, "id");
    }

    public static WebElement locateElementByCSS(String cssText) {
        return getElementByLocatorAndSearchType(cssText, "css");
    }

    public static WebElement locateEleByXPathTextNormSpace(String text) {
        return getElementByLocatorAndSearchType("//*[normalize-space(text())='" + text + "']", "xpath");
    }

    public static WebElement locateEleByXPathTextNormSpaceAttr(String attr, String text) {
        return getElementByLocatorAndSearchType("//" + attr + "[normalize-space(text())='" + text + "']", "xpath");
    }

    public static WebElement locateEleByXPathContainsNormSpaceAttr(String attr, String text) {
        return getElementByLocatorAndSearchType("//" + attr + "[contains(normalize-space(), '" + text + "')]", "xpath");
    }

    public static WebElement locateElementByTypeClassAndContainsText(String eleType, String eleClass, String text) {
        return getElementByLocatorAndSearchType("//" + eleType + "[contains(@class, '" + eleClass + "') and contains(., '" + text + "')]", "xpath");
    }

    public static WebElement locateElementByTypeAttributeAndValue(String eleType, String eleAttr, String value) {
        return getElementByLocatorAndSearchType("//" + eleType + "[@" + eleAttr + "='" + value + "']", "xpath");
    }

    public static List<WebElement> locateElementsByTagName(String tagName) {
        return driver.findElements(By.tagName(tagName));
    }

    /**
     *  the purpose of this method is to handle the Selenium speed for searching web elements
     */
    public static WebElement getElementByLocatorAndSearchType(String locator, String searchType) {
        int retries = 10; // x wait 1000 milliseconds ~ inner loop is the full driver wait per element returned in list.
        List<WebElement> elementList = new ArrayList<>();
        while (retries > 0) {
            elementList = getElementList(locator, searchType);
            if (!elementList.isEmpty()) {
                if (elementList.size() > 1) {
                    log.warn("WARN: Get ELEMENT: " + elementList.size() + " found for: " + locator + " by " + searchType +
                            "\nWill return the first that qualifies as isElementPresent.\n" +
                            "See: getXPathfor1andOnly1Element or similar to be sure it is correct.");
                }
                for (WebElement webElement : elementList) {
                    if (isElementPresent(webElement)) {  //ENH: wait x retries, per instances. Should be a cleaner way.
                        return webElement;
                    }
                }
            }
            wait(1000);
            retries--;
        }
        if (elementList.size() >= 1) {
            log.warn("WARN: Get ELEMENT: None have true for .isDisplayed): " + elementList.size() + " found for: " + locator + " by " + searchType +
                    "\nWill return the first element though it is NOT .isDisplayed().\n" +
                    "See: getXPathfor1andOnly1Element or similar to be sure it is correct.");
            return elementList.get(0);
        }
        Assert.fail("ERROR: Element NOT found for: " + locator + " by " + searchType);
        return null;
    }

    public static List<WebElement> locateElementsByXPath(String XPath) {
        return driver.findElements(By.xpath(XPath));
    }

    public static List<WebElement> locateElementsByCss(String CSS) {
        return driver.findElements(By.cssSelector(CSS));
    }

    public static WebElement refreshWebElement(WebElement staleEle) {
        String eleString = staleEle.toString();
        waitForCommonPageLoadingElements();
        return getElementByLocatorAndSearchType(eleString.substring(eleString.indexOf("-> "), eleString.lastIndexOf(":")).trim(),
                eleString.substring(eleString.lastIndexOf(":")).replace("]]", "]").trim());
    }

    public static void clickElementByTypeAttrAndValue(String eleType, String eleAttr, String value) throws Exception {
        click(locateElementByTypeAttributeAndValue(eleType, eleAttr, value));

    }

    public static void type(WebElement element, String value) {
        int retries = 6;
        boolean isTyped = false;
        Exception possibleException = null;
        log.info("TRY: TYPE: " + element + " : " + value);
        while (retries > 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(value);
                wait(500);
                isTyped = true;
            } catch (StaleElementReferenceException se) {
                possibleException = se;
                element = refreshWebElement(element);
            } catch (Exception e) {
                possibleException = e;
            }
            if (isTyped) break;
            retries--;
            wait(200);
        }
        if (!isTyped)
            Assert.fail("FAIL: ERROR: Could not TYPE: " + element + " : " + value + " :\n" + possibleException);
        log.info("TYPED: " + element + " : " + value);
    }

    public static void searchValue(WebElement element, String value) {
        type(element, value);
    }

    //  Web elements verification method and returns boolean
    public static boolean isElementPresent(WebElement element) {
        //ENH: Retries added ~ Route findElements through here as possible.
        //ENH: This will wait per 1 or > 1 on element in list, possibly bloating wait time..
        //     See WARN to remove these cases.
        //NOTE: Built-in wait for an element to be present.  Use a different method if not-present is desirable.
        //ENH: if wait needs to update a stale reference, should this return the WebElement -or- null/Assert.fail
        try {
            element = waitForExpectedElement(element);
            log.debug("FOUND: isElementPresent: " + element);
            return true;
        } catch (Exception e) {
            log.error("ERROR: isElementPresent: " + element);
            e.printStackTrace();
            Screenshot.captureScreen(driver, driver.getTitle() + "UnableToFindElement_FAILED", testName);
        }
        return false;
    }

    public static boolean isElementPresent(String locator, String searchType) {
        //ENH: This needs to be rethought. If > 1 is a problem.  Also being in the DOM isn't always enough.
        List<WebElement> elementList = getElementList(locator, searchType);
        if (elementList.size() > 1) {
            log.warn("WARN: Get ELEMENT: " + elementList.size() + " found for: " + locator + " by " + searchType);
        }
        return elementList.size() > 0;
    }
    //DOC: Immediately check page w/o a wait

    public static boolean isInPageSource(String text) {
        return driver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }

    public static void readTable(String compareValue, WebElement table) {
        boolean foundValue = false;
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                log.debug("Web Table Cell contents: " + cell.getText());
                if (cell.getText().contains(compareValue)
                        || compareValue.contains(cell.getText())) {
                    cell.click();
                    System.out.println("Found value on table: " + compareValue);
                    highLightElement(cell);
                    foundValue = true;
                    break;
                }
            }
        }
        Assert.assertTrue("FAIL: Value not in Table: " + compareValue, foundValue);
    }

    public static String getTableCellTextByReportColAriaLabelAndRow(String report, String colName, String row) {
        WebElement ele = null;
        int retries = 3;
        while (retries > 0) {
            ele = waitForExpectedElement(getElementByLocatorAndSearchType("//table[contains(@aria-label, '" + report + "')]", "xpath"));
            if (ele != null) {
                waitForExpectedElement(getElementByLocatorAndSearchType("//table[contains(@aria-label, '" + report + "')]" +
                        "/tbody/tr/td", "xpath"));
            }
            if (ele != null) {
                ele = getElementByLocatorAndSearchType("(//table[contains(@aria-label, '" + report + "')]/tbody/tr" +
                        "//td[count(//table[contains(@aria-label, '" + report + "')]/thead/tr" +
                        "/th[contains(@aria-label, '" + colName + "')]/preceding-sibling::th) + 1])[" + row + "]", "xpath");
            }
            if (ele != null) break;
            wait(1000);
            retries--;
        }
        if (ele == null)
            Assert.fail("FAIL: Element Value never found for Report: " + report + " : colName: " + colName + " : Row: " + row);
        return ele.getText();
    }

    public static boolean readTable(String compareValue, WebElement table, int colNum) throws Throwable {
        boolean foundValue = false;
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                //System.out.println("content >>   " + cell.getText());
                if (cell.getText().contains(compareValue)) {
                    System.out.println("Found value on table:::" + compareValue);
                    WebElement anotherCell = cells.get(colNum);
                    highLightElement(anotherCell);
                    foundValue = true;
                    click(anotherCell);
                    break;
                } else if (compareValue.contains(cell.getText())) {
                    System.out.println("Found value on table:::" + compareValue);
                    highLightElement(cell);
                }
            }
        }
        return foundValue;
    }

    //WebElement getElementByLocatorAndSearchType(String locator, String searchType) {
    public static int totalEntriesNum(String text) {
        return Integer.parseInt(text.replaceAll(".*(?= of)", "").replaceAll("[^\\d]", "").trim());
    }

    public static String getTooltipText(String locator, String searchType) {
        try {

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        return getElementByLocatorAndSearchType(locator, searchType).getAttribute("title");
    }

    /**
     * get the web element value and return a list of web elements for further using
     */
    public static List<WebElement> getElementList(String locatorValue, String selLocType) {
        selLocType = selLocType.toLowerCase();
        List<WebElement> elementList = new ArrayList<>();
        switch (selLocType) {
            case "id":
                elementList = driver.findElements(By.id(locatorValue));
                break;
            case "name":
                elementList = driver.findElements(By.name(locatorValue));
                break;
            case "xpath":
                elementList = driver.findElements(By.xpath(locatorValue));
                break;
            case "css":
            case "css selector":
                elementList = driver.findElements(By.cssSelector(locatorValue));
                break;
            case "class":
            case "classname":
            case "class name":
                elementList = driver.findElements(By.className(locatorValue));
                break;
            case "tagname":
            case "tag name":
                elementList = driver.findElements(By.tagName(locatorValue));
                break;
            case "linktext":
            case "link text":
                elementList = driver.findElements(By.linkText(locatorValue));
                break;
            case "partiallinktext":
            case "partial link text":
                elementList = driver.findElements(By.partialLinkText(locatorValue));
                break;
            default:
                log.error("ERROR: Element not found with " + selLocType + ": " + locatorValue);
        }
        return elementList;
    }

    public static int getTotalSortableColumnNumbers(String xPathLocator) {
        List<WebElement> num = driver.findElements(By.xpath(xPathLocator));
        int t = 0;
        for (WebElement el : num) {
            if (!el.getAttribute("class").contains("sorting_disabled")) {
                t++;
            }
        }
        System.out.println("Quantity of sortable columns = " + t);
        return t;
    }

    public static String getTotalSortableColumnNames(String XPathLocator) {
        List<WebElement> num = driver.findElements(By.xpath(XPathLocator));
        String text = null;
        for (WebElement el : num) {
            if (!el.getAttribute("class").contains("sorting_disabled")) {
                text = el.getText();
                System.out.println("Sortable column name = " + text);
            }
        }
        return text;
    }

    public static void gotoXtab(String tab) throws Exception {
        wait(3000);
        ((JavascriptExecutor) driver).executeScript("scroll(0,-10000)");
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id='rfForm']/div[1]/div[1]/h1"));
        wait(3000);
        if ((tab).contains("Download Files") && pageTitle.getText().contains("Download Files")) {
            wait(3000);
            Screenshot.captureScreen(driver, driver.getTitle(), testName);
        }
        if ((tab).contains("Model Activity") && (pageTitle.getText()).contains("List of Downloaded Files")) {
            wait(3000);
            Screenshot.captureScreen(driver, driver.getTitle(), testName);
        } else {
            click(driver.findElement(By.linkText(tab)));
            wait(5000);
            Screenshot.captureScreen(driver, driver.getTitle(), testName);
        }
        wait(5000);
    }

    public static String getRequestID(WebElement confMessage) {
        String[] strSplit = confMessage.getText().split("Request ID:");
        String str = strSplit[1].substring(0, 7);
        String requestID = str.replaceAll(",$", "");
        if (requestID.equals("")) {
            System.out.println("request ID is not available");
        }
        System.out.println("Request ID: " + requestID);
        return requestID.trim();
    }

    public static String getRequestID(WebElement confMessage, String message) {
        String[] strSplit = null;
        if (message.equalsIgnoreCase("Request ID:")) {
            strSplit = confMessage.getText().split("Request ID:");
        }
        if (message.equalsIgnoreCase("request is")) {
            strSplit = confMessage.getText().split("request is");
        }
        if (strSplit == null) Assert.fail("FAIL: No Request ID was available.");
        String str = strSplit[1].substring(0, 7);
        String requestID = str.replaceAll(",$", "").replace(".", "");
        if (requestID.equals("")) {
            //ENH: Negative Test Cases? There are other Regex ways to validate that what remains is valid
            System.out.println("request ID is not available");
        } else {
            System.out.println("Request ID: " + requestID);
        }
        return requestID;
    }

    public static String getReqID(WebElement confMessage) {
        String[] strSplit = null;
        String message = confMessage.getText();
        if (message.contains("Request ID:")) {
            strSplit = confMessage.getText().split("Request ID:");
        }
        if (message.contains("request is")) {
            strSplit = confMessage.getText().split("request is");
        }
        if (strSplit == null) Assert.fail("FAIL: No Request ID was available.");
        String str = strSplit[1].substring(0, 7);
        String requestID = str.replaceAll(",$", "").replace(".", "");
        if (requestID.equals("")) {
            System.out.println("request ID is not available");
        } else {
            System.out.println("Request ID: " + requestID);
        }
        return requestID;
    }

    public static String getReqFromComformMsg(WebElement confMessage) {
        return highLightElement(confMessage).getText().replaceAll("[^\\d]", "");
    }

    public static String getTextofWebElementSimple(WebElement el) {
        String actualText = "";
        if (el.isDisplayed()) {
            System.out.println(actualText + " is displayed");
            actualText = el.getText();
        } else {
            System.out.println("Element is not found");
            try {
                actualText = el.getText();
            } catch (Exception ignored) {
            }
        }
        return actualText;
    }

    public static String getUserByRowId(int x) {
        return readDataFromExcel(x, "UserName", "testData2", env);
    }

    public static void getColorValue(String color, String expectedColor) {
        System.out.println("Getting the icon color value");
        String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);
        String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
        Assert.assertEquals("No such color", expectedColor, actualColor);
        System.out.println("The color value as expected");
    }

    public static int getEnvVal() {
        return env2.equalsIgnoreCase("SB") ? 1 : env2.equalsIgnoreCase("SBTEST") ? 2 : 3;
    }

    public static void click(WebElement element) throws Exception {
        int retries = 2;
        while (retries > 0) {
            try {
                element = waitForExpectedElement(element);
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("CLICKED: " + element);
                waitForCommonPageLoadingElements();
                return;
            } catch (Exception ignored) {
            }
            wait(200);
            retries--;
        }
        clickWithJSE(element);
    }

    public static void click(String xpath) throws Exception {
        click(locateElementByXPath(xpath));
    }

    public static void simpleClick(WebElement element) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if (element.isDisplayed()) {
            try {
                executor.executeScript("arguments[0].click();", element);
                System.out.println("Clicked on element = " + element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.error("ERROR: CLICK: No such element / element was not displayed, and not CLICKED: " + element);
            //ENH: seems wrong to continue.  Keeping it for the moment
            log.error("Continuing w/o CLICK: " + element);
        }
    }

    public static WebElement clickOnTextWithJSEAndNormSpaceAttr(String attr, String text) throws Exception {
        WebElement element = driver.findElement(By.xpath("//" + attr + "[normalize-space(text())='" + text + "']"));
        simpleClick(element);
        return element;
    }

    public static void clickWithJSE(WebElement element) throws Exception {
        simpleClick(element);
        waitForCommonPageLoadingElements();
    }

    public static void click_bytext(String Text) throws Exception {
        click(locateElementByXPath("//*[text() = '" + Text + "']"));
    }

    public static void click_byTitle(String title) throws Exception {
        click(locateElementByXPath("//*[@title = '" + title + "']"));
    }

    public static void click_byid(String ID) throws Exception {
        click(locateElementByXPath("//*[contains(@id, '" + ID + "')]"));
    }

    public static void clickCheckbox_byText(String checkBoxName) throws Exception {
        click(driver.findElement(By.xpath("//*[text() = '" + checkBoxName + "']")));
    }

    public static void clickLink_byXpath(String xpath) throws Exception {
        click(driver.findElement(By.xpath(xpath)));
    }

    public static void clickAfterHoverOver(WebElement mainElement, WebElement subElement) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(mainElement));
        BasePage.actions.moveToElement(mainElement).perform();
        click(subElement);
    }

    public static void typeWithJSById(String idlocator, String valueToType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        System.out.println("The value is: " + valueToType);
        jsExecutor.executeScript("document.getElementById('" + idlocator + "').value='" + valueToType + "'");
    }

    public static void typeWithJSByXPath(WebElement inputField, String valueToType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        System.out.println("The value is: " + valueToType);
        jsExecutor.executeScript("arguments[0].value='" + valueToType + "';", inputField);
    }

    public static void typeonForm(String typeonForm, String value) {
        type(driver.findElement(By.xpath("//*[contains(@title, '" + typeonForm + "')]")), value);
    }

    public static void typeonForm(WebElement form, String value) {
        type(form, value);
    }

    public static void searchby(String searchby, String value) {
        type(driver.findElement(By.xpath("//*[contains(@id, 'Filter_by_" + searchby + "')]")), value);
    }

    public static void searchBar(String xpathLocator, String searchText) throws Exception {
        if (isElementPresent(locateElementByXPath(xpathLocator))) {
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(xpathLocator))).clear();
            locateElementByXPath(xpathLocator).sendKeys(searchText);
        }
        wait(1000);
    }

    public static void select(Select element, String value) throws Exception {
        wait(1000);
        element.selectByVisibleText(value);
        Screenshot.captureScreen(driver, driver.getTitle(), testName);
        log.info(Date + "...>>>Selecting...>>>" + element + "...with value...>>>" + value);
    }

    public static void selectValue_FromDropdown(String tag, String value) {
        WebElement dropdown = driver.findElement(By.xpath("//select[@" + tag + " ='" + value + "']"));
        if (isElementPresent(dropdown)) {
            Select sel = new Select(dropdown);
            List<WebElement> options = sel.getOptions();
            Random rnd = new Random();
            int randomOpt = rnd.nextInt(options.size());// get random number from 0-last option
            sel.selectByIndex(randomOpt);
            System.out.println(Date + "...>>>Selected...>>>" + dropdown);
            System.out.println(Date + "...>>>Selected...>>>" + dropdown);
            wait(5000);
        }
    }

    public static void selectDropDownOption_ByVisibleText(String visibleText, WebElement dropDownName) throws Exception {
        selectFromDropDownMenu(dropDownName, visibleText);
    }

    public static void selectFromDropDownMenu(WebElement element, String text) throws Exception {
        element = highLightElement(element);
        new Select(wait.until(ExpectedConditions.elementToBeClickable(element))).selectByVisibleText(text);
        log.info("Selecting from: " + element + " : Text Option: " + text);
    }

    public static void selectFromDropDownMenuByValueAndElement(String value, WebElement element) {
        element = highLightElement(element);
        new Select(wait.until(ExpectedConditions.elementToBeClickable(element))).selectByValue(value);
        log.info("Selecting from: " + element + " : Option With Value: " + value);
    }

    public static void selectByIdAndOptionText(String id, String value) throws Throwable {
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(locateElementByID(id)));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='" + id + "']//option[contains(text(),'" + value + "')]")));
        selectFromDropDownMenu(locateElementByID(id), value);
    }

    public static void selectByLabelAndOptionText(String label, String value) throws Throwable {
        String xpath = "//label[contains(text(),'" + label + "')]/parent::div//select";
        WebElement ele = getElementByLocatorAndSearchType(xpath, "xpath");
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(xpath + "//option[contains(text(),'" + value + "')]")));
        selectFromDropDownMenu(ele, value);
    }

    public static void selectByLabelAndOptionTextUp(String label, String value) throws Throwable {
        String xpath = "//span[contains(text(),'" + label + "')]/parent::b/parent::div//select";
        WebElement ele = getElementByLocatorAndSearchType(xpath, "xpath");
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(xpath + "//option[contains(text(),'" + value + "')]")));
        selectFromDropDownMenu(ele, value);
    }

    public void selectEntriesDropDownVerification(String string, DataTable entriesValue, String
            selDropdownWebEl, String showingEntriesWebEl) {
        List<List<String>> lists = entriesValue.asLists(String.class);
        waitForCommonPageLoadingElements();
        IntStream.range(0, lists.get(0).size()).forEach(p -> {
            try {
                selectFromDropDownMenu(locateElementByXPath(selDropdownWebEl), lists.get(0).get(p));
                String expectedEntry = lists.get(0).get(p);
                System.out.println("Value = " + expectedEntry);
                waitForCommonPageLoadingElements();
                wait(1000);
                scroll_Into_View(locateElementByID(showingEntriesWebEl));
                String actualEntry = locateElementByID(showingEntriesWebEl).getText();
                String entryValue = getEntryValue(actualEntry, "to", "of");
                System.out.println("Full text = " + actualEntry);
                System.out.println("text = " + entryValue);
                verifyActualText(expectedEntry, entryValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void scroll_Into_View(WebElement element) {
        element = waitForExpectedElement(element);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // Scroll Down
        jse.executeScript("window.scrollBy(0, 5000);");
        wait(1000);
        // Scroll Up
        jse.executeScript("window.scrollBy(0, -5000);");
        wait(1000);
        jse.executeScript("arguments[0].scrollIntoView(true)", element);
        wait(1000);
    }

    public static void scroll_Down(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // Scroll Down
        jse.executeScript("window.scrollBy(0, 5000);");
        wait(1000);
        jse.executeScript("arguments[0].scrollIntoView(true)", element);
        wait(1000);
    }

    public static void scroll_Up(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // Scroll Up
        jse.executeScript("window.scrollBy(0, -1900);");
        wait(1000);
        jse.executeScript("arguments[0].scrollIntoView(true)", element);
        wait(1000);
    }

    public static void accessApplication(WebElement app) throws Exception {
        longWait.until(ExpectedConditions.visibilityOf(app));
        System.out.println("Application title: " + app);
        click(app);
        waitForCommonPageLoadingElements();
        wait(5000);
    }

    public static void verifyCheckBoxUnselected(List<WebElement> elements) {
        if (elements == null) {
            throw new NullPointerException("List of checkboxes is null");
        }
        for (WebElement ele : elements) {
            log.info("ELEMENT: " + ele + " is unselected: " + !ele.isSelected() + " on form: " + ele.findElement(By.xpath("./ancestor::form")).getAttribute("id"));
        }
    }

    public static void verifyActualText(String expectedText, String actualText) {
        if (expectedText == null || actualText == null) {
            throw new NullPointerException("Expected text or actual text is null");
        }
        log.info("\nExpected text = " + expectedText + "\nActual text = " + actualText);
        Assert.assertEquals(expectedText.trim(), actualText.trim(), "The expected text does not match the actual text");
    }

    public static void verifyCheckBoxIsSelected(List<WebElement> elements) {
        for (WebElement ele : elements) {
            log.info(ele + " is selected?: " + ele.isSelected());
        }
    }

    public static void verifyActualTextContainsExpectedAsSubstring(String actualText, String expectedText) {
        log.info("Actual text = " + actualText + "\nExpected text = " + expectedText);
        System.out.println("Actual text = " + actualText.trim() + "\nExpected text = " + expectedText.trim());
        Assert.assertTrue(actualText.trim().contains(expectedText.trim()));
    }

    public static String verifyAppUrl(String url, String appName) {
        //ENH: This seems outdated.  AHC is not in its URL, instead a cm:oid:
        int indexFirst;
        String foundMatch = "TRYING to find App Name: " + appName;
        int appNameLength = appName.length();
        if (url.contains(appName)) {
            indexFirst = url.indexOf(appName);
            if (indexFirst >= 0) {  // indexOf returns -1 if no match found
                foundMatch = url.substring(indexFirst, indexFirst + appNameLength);
            }
        } else {
            System.out.println("User is not on welcome application page");
        }
        return foundMatch;
    }

    public static String verifyErrorDisplayed(WebElement error, WebElement emptyField) {
        String errorMess = "";
        try {
            //ENH: This seems incorrect, or at least incomplete as it uses WebElement instead of the expected text/lack of text
            if (isElementPresent(emptyField))
                Assert.fail("FAIL: Test was expecting an error field: " + error + " : instead, EMPTY field is displayed as: " + emptyField); // verify if input field is empty

            errorMess = getTextofWebElementSimple(error);
            if (errorMess != null && !errorMess.isEmpty()) {
                System.out.println(errorMess);
            } else {
                System.out.println("Error is not displayed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return errorMess;
    }

    public static void verifyApplicationHomePages(WebElement app, WebElement homeApp, String foundApp) {
        String welcomePage = driver.getCurrentUrl();
        System.out.println(welcomePage);
/* //TODO: Outdated. What's this really trying to verify?
        String appName = verifyAppUrl(welcomePage, foundApp);
        String envFullName = env + env2;
        Assert.assertThat(isElementPresent(homeApp))).as("Assert Failed for " + appName + " application in " + envFullName).isEqualTo(true);
 */
        Assert.assertTrue(isElementPresent(homeApp));
    }

    public static void verifyWithTrueAssertions(WebElement el) {
        Assert.assertTrue((isElementPresent(el)));
        log.info("INFO: AS EXPECTED: Element is present: " + el);
    }

    public static void verifyApplicationHomePages(WebElement app, WebElement homeApp, String foundApp,
                                                  boolean iframeStatus) {
        try {
            String welcomePage = driver.getCurrentUrl();
            String appName = verifyAppUrl(welcomePage, foundApp);
            String envFullName = env + env2;
            if (iframeStatus) {
                driver.switchTo().frame(0);
                Assert.assertTrue(isElementPresent(homeApp));
                driver.switchTo().defaultContent(); // Parent page
            } else {
                boolean containsIframe = driver.getPageSource().contains("iframe");
                System.out.println("Iframe is present : " + containsIframe);
                Assert.assertTrue(isElementPresent(homeApp));
                driver.switchTo().defaultContent(); // Parent page
            }
        } catch (Exception e) {
            System.out.println("Trying other...>>>");
        }
    }

    public static void verifyIfElementPartofIframe(WebElement elem) throws Exception {
        boolean containsIframe = driver.getPageSource().contains("iframe");
        System.out.println("Iframe is present : " + containsIframe);
        if (containsIframe) {
            driver.switchTo().frame(0);
            wait.until(ExpectedConditions.visibilityOf(elem));
            click(elem);
            driver.switchTo().defaultContent();
        } else {
            wait.until(ExpectedConditions.visibilityOf(elem));
            click(elem);
        }
    }

    public static void verifyIfHoverOverElementPartofIframe(WebElement mainElem, WebElement subElem) throws
            Exception {
        boolean containsIframe = driver.getPageSource().contains("iframe");
        System.out.println("Iframe is present : " + containsIframe);
        if (containsIframe) {
            driver.switchTo().frame(0);
            clickAfterHoverOver(mainElem, subElem);
            driver.switchTo().defaultContent();
        } else {
            clickAfterHoverOver(mainElem, subElem);
        }
    }

    public static void verifyIfTablePartofIframe(String compareValue, WebElement table) {
        boolean containsIframe = driver.getPageSource().contains("iframe");
        System.out.println("Iframe is present : " + containsIframe);
        if (containsIframe) {
            driver.switchTo().frame(0);
            readTable(compareValue, table);
            driver.switchTo().defaultContent();
        } else {
            readTable(compareValue, table);
        }
    }

    public static void verifyIfElementPartofIframeScroll(WebElement elem) throws Exception {
        boolean containsIframe = driver.getPageSource().contains("iframe");
        System.out.println("Iframe is present : " + containsIframe);
        if (containsIframe) {
            driver.switchTo().frame(0);
            scroll_Into_View(elem);
            driver.switchTo().defaultContent();
        } else {
            click(elem);
        }
    }

    public static String verifyIfElementPartofIframeGetText(WebElement elem) {
        boolean containsIframe = driver.getPageSource().contains("iframe");
        System.out.println("Iframe is present : " + containsIframe);
        String text = "";
        if (containsIframe) {
            driver.switchTo().frame(0);
            text = getTextofWebElementSimple(elem);
            driver.switchTo().defaultContent();
        } else {
            getTextofWebElementSimple(elem);
        }
        return text;
    }

    public static boolean verifyCheckboxIsSelected(String checkBoxId) {
        if (locateElementByID(checkBoxId).isSelected()) {
            System.out.println("the checkbox is selected");
            return locateElementByID(checkBoxId).isSelected();
        } else {
            System.out.println("check box is not selected");
            return !locateElementByID(checkBoxId).isSelected();
        }
    }

    public static void verifyElementByTypeClassAndContainsText(String eleType, String eleClass, String text) {
        WebElement ele = null;
        try {
            ele = locateElementByTypeClassAndContainsText(eleType, eleClass, text);
        } catch (Exception ignored) {
        }
        Assert.assertNotNull(ele);
    }

    public static void startUsingIframe() {
        startUsingIframeByIndex(0);
    }

    public static void startUsingIframeByIndex(int index) {
        waitForCommonPageLoadingElements();
        if (driver.getPageSource().contains("iframe")) {
            longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        }
    }

    public static void endUsingIframe() {
        driver.switchTo().defaultContent();
    }

    public static void routingElemHandlingHelper(WebElement list, String str) {
        List<WebElement> liList = list.findElements(By.tagName("li"));
        wait(2000);
        for (WebElement el : liList) {
            if (el.getText().contains(str)) {
                System.out.println("Selected:" + el.getText());
                try {
                    click(el);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //TODO: @Deprecated (using real notation is causing distracting compiler warnings
    public static String handleAlert() {
        return handleAlertAndReturnText();
    }

    //TODO: Would be useful to read from Excel; undesirable to be in Java ; formerly named: isAWSApp
    public boolean isAppWOEnvPostfix(String application) {
        return Arrays.asList(AMRVT, ARGO, IPC, ONYD, PERS).contains(application);
    }

    public String getModelAppName(String application) {
        if (isAppWOEnvPostfix(application) || env2.equalsIgnoreCase("SB")) return application;
        return application + " - " + env + "1";
    }

    public String envCustAttr(String devsb, String devsbtest, String valaws) {
        //TODO: TEMP: Placeholder method so Java compiles. Would aim to move all data out of Java:
        if (environment.equals("DEVSB")) return devsb;
        if (environment.equals("DEVSBTEST")) return devsbtest;
        return valaws;
    }

    public static WebElement highLightElement(String elementXPath) throws Exception {
        return highLightElement(locateElementByXPath(elementXPath));
    }

    public static WebElement highLightElement(WebElement element) {
        element = waitForExpectedElement(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border: 3px solid red;');", element);
        log.info("HighLighting: " + element);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        return element;
    }

    public static WebElement waitForExpectedElement(WebElement element) {
        return waitForExpectedElement(element, wait);
    }

    public static WebElement waitForExpectedElement(WebElement element, WebDriverWait driverWait) {
        int retries = 3;
        Exception possibleException = null;
        while (retries > 0) {
            try {
                return driverWait.until(ExpectedConditions.visibilityOf(element));
            } catch (StaleElementReferenceException se) {
                possibleException = se;
                element = refreshWebElement(element);
            } catch (Exception ignored) {
            }
//ENH: get Error Code/Text - exit immediately if one is displayed   if (!findPossibleErrorText(driver, null).isEmpty()) break;
            wait(200);
            retries--;
        }
        Assert.fail("FAIL: ERROR: Element never found: " + element + " : " + possibleException);
        return null;
    }

    public static WebElement waitForElementAllowNull(String locator, String type, WebDriverWait driverWait) {
        //DOC: Useful to build a longWait w/o hitting the Assert.fail(),
        // or not-find an element when null is the desirable result.
        int retries = 2;
        List<WebElement> elementList;
        while (retries > 0) {
            try {
                elementList = getElementList(locator, type);
                if (!elementList.isEmpty())
                    return waitForExpectedElement(getElementByLocatorAndSearchType(locator, type), driverWait);
            } catch (Exception ignored) {
            }
            wait(200);
            retries--;
        }
        return null;
    }

    public static WebElement waitForElementAllowNull(String locator, String type) {
        return waitForElementAllowNull(locator, type, wait);
    }

    public static WebElement longWaitForElementAllowNull(String locator, String type) {
        return waitForElementAllowNull(locator, type, longWait);
    }

    private boolean emailIsValid(String email) {
        boolean isValid = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static void fluentWait() {
        fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30L)).
                pollingEvery(Duration.ofSeconds(5L)).ignoring(NoSuchElementException.class);
    }

    public static boolean areElementsPresent(List<WebElement> elementList) {
        boolean isPresent = false;
        if (elementList.isEmpty()) {
            System.out.println("Element not found");
        } else {
            isPresent = true;
            System.out.println("Element is found");
        }
        return isPresent;
    }

    public static void inputTextByLabelAndElementAndText(String label, String eleType, String value) {
        String xpath = "//label[contains(text(),'" + label + "')]/parent::div//" + eleType;
        type(getElementByLocatorAndSearchType(xpath, "xpath"), value);
    }

    public static void setInputBasedSelectByValueXPathAndOptionString(String valueXPath, String value) throws
            Throwable {
        if (!value.contains(";")) value = value + ";" + value;
        String[] values = value.split(";");
        type(getElementByLocatorAndSearchType(valueXPath, "xpath"), values[0].trim().replace("-", " "));
        values[1] = values[1].replaceAll("[^A-Za-z0-9 ]", " ").replaceAll("\\s+", " ").trim();
        String xpath = valueXPath + "/parent::div//" +
                "ul/li//*[contains(*, '" + values[1].replaceAll(" ", "') and contains(*, '") +
                "')]";
        if (!xpath.contains(" and contains(")) xpath += "/ancestor::a";
        wait(250);
        if (!isElementPresent(xpath, "xpath"))
            throw new NoSuchElementException("ERROR: Element: Input-based select Option: " + xpath);
        click(getElementByLocatorAndSearchType(xpath, "xpath"));
        wait(250);
        if (isElementPresent("//button[contains(text(), 'Add')]", "xpath"))
            click(getElementByLocatorAndSearchType("//button[contains(text(), 'Add')]", "xpath"));
    }

    public static void setInputBasedSelectByValueXPathAndOptionStringAn2(String valueXPath, String value) throws
            Throwable {
        if (!value.contains(";")) value = value + ";" + value;
        String[] values = value.split(";");
        type(getElementByLocatorAndSearchType(valueXPath, "xpath"), values[0].trim().replace("-", " "));
        values[1] = values[1].replaceAll("[^A-Za-z0-9 ]", " ").replaceAll("\\s+", " ").trim();
        String xpath = valueXPath + "/parent::formly-field-rt//*[contains(*, '" + values[1].replaceAll(" ", "') and contains(*, '") + "')]";
        if (!xpath.contains(" and contains(")) {
            xpath += "//div[2]";
            if (!isElementPresent(xpath, "xpath")) {
                xpath = xpath.substring(0, xpath.length() - 6);
                System.out.println("xpath = " + xpath);
                xpath = xpath + "button";
                System.out.println("xpath = " + xpath);
            }
        }
        wait(250);
        if (!isElementPresent(xpath, "xpath"))
            throw new NoSuchElementException("ERROR: Element: Input-based select Option: " + xpath);
        click(getElementByLocatorAndSearchType(xpath, "xpath"));
        wait(250);
        if (isElementPresent("//button[contains(text(), 'Add')]", "xpath"))
            click(getElementByLocatorAndSearchType("//button[contains(text(), 'Add')]", "xpath"));
    }

    public static void setInputBasedSelectByValueXPathAndOptionStringUp(String role, String label, String valueXPath, String value) throws
            Throwable {
        if (!value.contains(";")) value = value + ";" + value;
        String[] values = value.split(";");
        type(getElementByLocatorAndSearchType(valueXPath, "xpath"), values[0].trim().replace("-", " "));
        if (role.contains("OCM") && label.equalsIgnoreCase("NPI")) {
            System.out.println("scip this step ");
        } else {
            values[1] = values[1].replaceAll("[^A-Za-z0-9 ]", " ").replaceAll("\\s+", " ").trim();
            String valueXPath2 = valueXPath.replaceAll("//input", "");
            String xpath = valueXPath2 + "//typeahead-container[contains(*, '" + values[1].replaceAll(" ", "') and contains(*, '") + "')]";
            wait(250);
            if (!isElementPresent(xpath, "xpath"))
                throw new NoSuchElementException("ERROR: Element: Input-based select Option: " + xpath);
            click(getElementByLocatorAndSearchType(xpath, "xpath"));
        }
        wait(250);
        if (isElementPresent("//button[contains(text(), 'Add')]", "xpath")) {
            click(getElementByLocatorAndSearchType("//button[contains(text(), 'Add')]", "xpath"));
        }
    }

    public static void ensureCheckBoxIsCheckedByAriaLabel(String ariaLabel) throws Throwable {
        String xpath = "//input[@type='checkbox' and @aria-label='" + ariaLabel + "']";
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(xpath)));
        if (!((JavascriptExecutor) driver).executeScript(
                        "return document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.checked")
                .toString().equals("true")) {
            clickWithJSE(locateElementByXPath(xpath));
        }
    }

    public static String getXPathfor1andOnly1Element(String eleType, String value, String helperXPath) {
        String[] tryPatterns = {
                helperXPath + "//" + eleType + "[text()='" + value + "']",
                helperXPath + "//" + eleType + "[normalize-space(text())='" + value + "']",
                helperXPath + "//" + eleType + "[contains(text(), '" + value + "')]",
                "//" + eleType + "[text()='" + value + "']",
                "//" + eleType + "[normalize-space(text())='" + value + "']",
                "//" + eleType + "[contains(text(), '" + value + "')]",
                "//div[@ui-view='assignrole']//" + eleType + "[text()='" + value + "']",
                "//div[@ui-view='assignrole']//" + eleType + "[normalize-space(text())='" + value + "']",
                "//div[@ui-view='assignrole']//" + eleType + "[contains(text(), '" + value + "')]"
        };
        return get1andOnly1ElementByXPathPatternArrayAllowNull(tryPatterns);
    }

    public static String get1andOnly1ElementByXPathPatternArrayAllowNull(String[] tryPatterns) {
        for (String locator : tryPatterns) {
            if (isElementPresentJSEByXPath(locator))
                if (getCountOfElementAllowZero(locator, "xpath", true) == 1)
                    return locator;
        }
        return null;
    }

    public static void setCustomAttributeByLabelAndValue(String label, String value) throws Throwable {
        setFieldByLabelAndValue(label, value, "");
    }

    public static void setCustomAttributeByLabelAndValueAn2(String label, String value) throws Throwable {
        setFieldByLabelAndValue(label, value, "");
    }

    public static void setFieldByLabelAndValue(String label, String value) throws Throwable {
        setFieldByLabelAndValue(label, value, "");
    }

    public static void setFieldByLabelAndValue(String label, String value, String helperXPath) throws Throwable {
        log.info("TRY: FIND: Field Label: " + label + " : value: " + value);
        String labelXPath, actualValueElementType, valueXPath;
        labelXPath = getXPathfor1andOnly1Element("label", label, helperXPath);
        System.out.println("labelXPath = " + labelXPath);
        WebElement ele = null;
        int retries = 10; // x wait 200 milliseconds
        while (retries > 0) {
            try {
                ele = getElementByLocatorAndSearchType(labelXPath, "xpath");
            } catch (Exception ignored) {
            }
            if (ele != null) break;
            wait(200);
            retries--;
        }
        if (ele == null) throw new NoSuchElementException("Element not found: Label: " + label);
        log.info("FOUND: LABEL: " + label);
        retries = 10;
        String[] possibleValueElementType = {"select", "input[@aria-autocomplete='list']", "button[contains(@name, 'datePicker')]", "input", "textarea"};
        actualValueElementType = "";
        valueXPath = "";
        while (retries > 0) {
            for (String valueElementType : possibleValueElementType) {
                valueXPath = labelXPath + "/parent::div//" + valueElementType;
                System.out.println("valueXPath = " + valueXPath);
                if (isElementPresentJSEByXPath(valueXPath)) {
                    ele = waitForElementAllowNull(valueXPath, "xpath", BasePage.miniWait);
                    if (ele != null) {
                        actualValueElementType = valueElementType;
                        break;
                    }
                }
            }
            if (!actualValueElementType.isEmpty()) break;
            wait(200);
            retries--;
        }
        if (actualValueElementType.isEmpty())
            throw new NoSuchElementException("Element not found to receive a value: Label: " + label + " : " + value);
        log.debug("FOUND: Value Element: " + actualValueElementType);
        if (actualValueElementType.startsWith("button") && actualValueElementType.contains("date")) {
            click(ele);
            wait(200);
            valueXPath = labelXPath + "/parent::div";
            if (value.equalsIgnoreCase("Today"))
                click(locateElementByXPath(valueXPath + "//button[text()='Today']"));
            else if (value.equalsIgnoreCase("Tomorrow")) {
                valueXPath += "//table[contains(@class, 'picker')]";
                wait(1000);
                //REVIEW: IS this consistent?
                //REVIEW: IS this consistent?
                // TODAY: //table[contains(@class, 'picker')]//button//span[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]
                // THEREFORE, Tomorrow IF on the same week: Start Date:
                String tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::td/following-sibling::td[1]//button";
                if (isElementPresentJSEByXPath(tryTomorrow)) click(locateElementByXPath(tryTomorrow));
                else if (label.equalsIgnoreCase("End Date")) {
                    //assuming Start Date is already set, click the first active square, should = Start Date:
                    tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
                    click(locateElementByXPath(tryTomorrow));
                } else {
                    // Otherwise, Tomorrow is on next row (tr):
                    tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::tr/following-sibling::tr[1]";
                    tryTomorrow += isElementPresentJSEByXPath(tryTomorrow + "//td[contains(@ng-if, 'showWeeks')]") ?
                            "/td[2]//button" : "/td[1]//button";
                    click(locateElementByXPath(tryTomorrow));
                }
            } else {
                wait(1000);
                //ENH: given value, Will click first future date that CONTAINS it ~ is not left-padding zeros, or adding days, etc.
                //Calculate that in the call before this (for now), and pass the ideal string for this XPath
                valueXPath += "//table[contains(@class, 'picker')]";
                log.warn("WARN: Date selection only considers a future date: WIP: " + value);
                click(locateElementByXPath(valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(text(), '" + value + "')]/parent::button"));
            }
            //ENH: Relative position from 'first active', either Today or Start date, by changing the end [i] (but would need to account for the /tr change
            // ex. tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
        } else if (actualValueElementType.startsWith("input") && actualValueElementType.contains("list")) {
//            setInputBasedSelectByValueXPathAndOptionString(valueXPath, value);
            setInputBasedSelectByValueXPathAndOptionStringAn2(valueXPath, value);
        } else if (actualValueElementType.equals("input") || actualValueElementType.equals("textarea")) {
            type(locateElementByXPath(valueXPath), value);
        } else if (actualValueElementType.equals("select")) {
            selectByLabelAndOptionText(label, value);
        }
    }

    public static void setFieldByLabelAndMultiValues(String label, String value, String addButton, String
            helperXPath) throws Throwable {
        log.info("TRY: FIND: Field Label: " + label + " : value: " + value);
        String labelXPath, actualValueElementType, valueXPath;
        labelXPath = getXPathfor1andOnly1Element("label", label, helperXPath);
        WebElement ele = null;
        int retries = 10; // x wait 200 milliseconds
        while (retries > 0) {
            try {
                ele = getElementByLocatorAndSearchType(labelXPath, "xpath");
            } catch (Exception ignored) {
            }
            if (ele != null) break;
            wait(200);
            retries--;
        }
        if (ele == null) throw new NoSuchElementException("Element not found: Label: " + label);
        log.info("FOUND: LABEL: " + label);
        retries = 10;
        String[] possibleValueElementType = {"select", "input[@aria-autocomplete='list']", "button[contains(@name, 'datePicker')]", "input", "textarea"};
        actualValueElementType = "";
        valueXPath = "";
        while (retries > 0) {
            for (String valueElementType : possibleValueElementType) {
                valueXPath = labelXPath + "/parent::div//" + valueElementType;
                if (isElementPresentJSEByXPath(valueXPath)) {
                    ele = waitForElementAllowNull(valueXPath, "xpath", BasePage.miniWait);
                    if (ele != null) {
                        actualValueElementType = valueElementType;
                        break;
                    }
                }
            }
            if (!actualValueElementType.isEmpty()) break;
            wait(200);
            retries--;
        }
        if (actualValueElementType.isEmpty())
            throw new NoSuchElementException("Element not found to receive a value: Label: " + label + " : " + value);
        log.debug("FOUND: Value Element: " + actualValueElementType);
        if (actualValueElementType.startsWith("button") && actualValueElementType.contains("date")) {
            click(ele);
            wait(200);
            valueXPath = labelXPath + "/parent::div";
            if (value.equalsIgnoreCase("Today"))
                click(locateElementByXPath(valueXPath + "//button[text()='Today']"));
            else if (value.equalsIgnoreCase("Tomorrow")) {
                valueXPath += "//table[contains(@class, 'picker')]";
                wait(1000);
                //REVIEW: IS this consistent?
                // TODAY: //table[contains(@class, 'picker')]//button//span[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]
                // THEREFORE, Tomorrow IF on the same week: Start Date:
                String tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::td/following-sibling::td[1]//button";
                if (isElementPresentJSEByXPath(tryTomorrow)) click(locateElementByXPath(tryTomorrow));
                else if (label.equalsIgnoreCase("End Date")) {
                    //assuming Start Date is already set, click the first active square, should = Start Date:
                    tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
                    click(locateElementByXPath(tryTomorrow));
                } else {
                    // Otherwise, Tomorrow is on next row (tr):
                    tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::tr/following-sibling::tr[1]";
                    tryTomorrow += isElementPresentJSEByXPath(tryTomorrow + "//td[contains(@ng-if, 'showWeeks')]") ?
                            "/td[2]//button" : "/td[1]//button";
                    click(locateElementByXPath(tryTomorrow));
                }
            } else {
                wait(1000);
                //ENH: given value, Will click first future date that CONTAINS it ~ is not left-padding zeros, or adding days, etc.
                //Calculate that in the call before this (for now), and pass the ideal string for this XPath
                valueXPath += "//table[contains(@class, 'picker')]";
                log.warn("WARN: Date selection only considers a future date: WIP: " + value);
                click(locateElementByXPath(valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(text(), '" + value + "')]/parent::button"));
            }
            //ENH: Relative position from 'first active', either Today or Start date, by changing the end [i] (but would need to account for the /tr change
            // ex. tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
        } else if (actualValueElementType.startsWith("input") && actualValueElementType.contains("list")) {
            setInputBasedSelectByValueXPathAndOptionString(valueXPath, value);
        } else if (actualValueElementType.equals("input") || actualValueElementType.equals("textarea")) {
            type(locateElementByXPath(valueXPath), value);
        } else if (actualValueElementType.equals("select")) {
            selectByLabelAndOptionText(label, value);
        }
        if (label.contains("Simple attribute") && addButton.equalsIgnoreCase("Add")) {
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("button[name='add']")));
            click(locateElementByCSS("button[name='add']"));
        }
    }

    public static void setFieldByLabelAndAddMultiVal(String label, String value, String addButton, String
            helperXPath) throws Throwable {
        log.info("TRY: FIND: Field Label: " + label + " : value: " + value);
        String labelXPath, actualValueElementType, valueXPath;
        labelXPath = getXPathfor1andOnly1Element("span", label, helperXPath);
        WebElement ele = null;
        int retries = 10; // x wait 200 milliseconds
        while (retries > 0) {
            try {
                ele = getElementByLocatorAndSearchType(labelXPath, "xpath");
            } catch (Exception ignored) {
            }
            if (ele != null) break;
            wait(200);
            retries--;
        }
        if (ele == null) throw new NoSuchElementException("Element not found: Label: " + label);
        log.info("FOUND: LABEL: " + label);
        retries = 10;
        String[] possibleValueElementType = {"select", "input[@aria-autocomplete='list']", "button[contains(@name, 'datePicker')]", "input", "textarea"};
        actualValueElementType = "";
        valueXPath = "";
        while (retries > 0) {
            for (String valueElementType : possibleValueElementType) {
                valueXPath = labelXPath + "/parent::div//" + valueElementType;
                if (isElementPresentJSEByXPath(valueXPath)) {
                    ele = waitForElementAllowNull(valueXPath, "xpath", BasePage.miniWait);
                    if (ele != null) {
                        actualValueElementType = valueElementType;
                        break;
                    }
                }
            }
            if (!actualValueElementType.isEmpty()) break;
            wait(200);
            retries--;
        }
        if (actualValueElementType.isEmpty())
            throw new NoSuchElementException("Element not found to receive a value: Label: " + label + " : " + value);
        log.debug("FOUND: Value Element: " + actualValueElementType);
        if (actualValueElementType.startsWith("button") && actualValueElementType.contains("date")) {
            click(ele);
            wait(200);
            valueXPath = labelXPath + "/parent::div";
            if (value.equalsIgnoreCase("Today"))
                click(locateElementByXPath(valueXPath + "//button[text()='Today']"));
            else if (value.equalsIgnoreCase("Tomorrow")) {
                valueXPath += "//table[contains(@class, 'picker')]";
                wait(1000);
                //REVIEW: IS this consistent?
                // TODAY: //table[contains(@class, 'picker')]//button//span[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]
                // THEREFORE, Tomorrow IF on the same week: Start Date:
                String tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::td/following-sibling::td[1]//button";
                if (isElementPresentJSEByXPath(tryTomorrow)) click(locateElementByXPath(tryTomorrow));
                else if (label.equalsIgnoreCase("End Date")) {
                    //assuming Start Date is already set, click the first active square, should = Start Date:
                    tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
                    click(locateElementByXPath(tryTomorrow));
                } else {
                    // Otherwise, Tomorrow is on next row (tr):
                    tryTomorrow = valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(@class, 'text-info')]/ancestor::tr/following-sibling::tr[1]";
                    tryTomorrow += isElementPresentJSEByXPath(tryTomorrow + "//td[contains(@ng-if, 'showWeeks')]") ?
                            "/td[2]//button" : "/td[1]//button";
                    click(locateElementByXPath(tryTomorrow));
                }
            } else {
                wait(1000);
                //ENH: given value, Will click first future date that CONTAINS it ~ is not left-padding zeros, or adding days, etc.
                //Calculate that in the call before this (for now), and pass the ideal string for this XPath
                valueXPath += "//table[contains(@class, 'picker')]";
                log.warn("WARN: Date selection only considers a future date: WIP: " + value);
                click(locateElementByXPath(valueXPath + "//button[not(@disabled='disabled')]//*[contains(@ng-class, 'dt.current') and contains(text(), '" + value + "')]/parent::button"));
            }
            //ENH: Relative position from 'first active', either Today or Start date, by changing the end [i] (but would need to account for the /tr change
            // ex. tryTomorrow = "(//label[contains(text(), '" + label + "')]/parent::div//table[contains(@class, 'picker')]//tr//td//button[not(@disabled='disabled')])[1]";
        } else if (actualValueElementType.startsWith("input") && actualValueElementType.contains("list")) {
            setInputBasedSelectByValueXPathAndOptionString(valueXPath, value);
        } else if (actualValueElementType.equals("input") || actualValueElementType.equals("textarea")) {
            type(locateElementByXPath(valueXPath), value);
        } else if (actualValueElementType.equals("select")) {
            selectByLabelAndOptionText(label, value);
        }
        if (label.contains("Simple attribute") && addButton.equalsIgnoreCase("Add")) {
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("button[name='add']")));
            click(locateElementByCSS("button[name='add']"));
        }
        if (addButton.equalsIgnoreCase("Add")) {
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("button[name='add']")));
            click(locateElementByCSS("button[name='add']"));
        }
    }

    public static void setFieldByLabelAndAddMultiValUp(String role, String label, String value, String helperXPath) throws Throwable {
        log.info("TRY: FIND: Field Label: " + label + " : value: " + value);
        String labelXPath, actualValueElementType, valueXPath;
        labelXPath = getXPathfor1andOnly1Element("span", label, helperXPath);
        WebElement ele = null;
        int retries = 10; // x wait 200 milliseconds
        while (retries > 0) {
            try {
                ele = getElementByLocatorAndSearchType(labelXPath, "xpath");
            } catch (Exception ignored) {
            }
            if (ele != null) break;
            wait(200);
            retries--;
        }
        if (ele == null) throw new NoSuchElementException("Element not found: Label: " + label);
        log.info("FOUND: LABEL: " + label);
        retries = 10;
        String[] possibleValueElementType = {"select", "input", "input[@aria-autocomplete='list']", "button[contains(@name, 'datePicker')]", "textarea"};
        actualValueElementType = "";
        valueXPath = "";
        while (retries > 0) {
            for (String valueElementType : possibleValueElementType) {
                valueXPath = labelXPath + "/parent::b/parent::div//" + valueElementType;
                if (isElementPresentJSEByXPath(valueXPath)) {
                    ele = waitForElementAllowNull(valueXPath, "xpath", BasePage.miniWait);
                    if (ele != null) {
                        actualValueElementType = valueElementType;
                        break;
                    }
                }
            }
            if (!actualValueElementType.isEmpty()) break;
            wait(200);
            retries--;
        }
        if (actualValueElementType.isEmpty())
            throw new NoSuchElementException("Element not found to receive a value: Label: " + label + " : " + value);
        log.debug("FOUND: Value Element: " + actualValueElementType);
        if (actualValueElementType.contains("input")) {
            setInputBasedSelectByValueXPathAndOptionStringUp(role, label, valueXPath, value);
        } else if (actualValueElementType.equals("select")) {
            selectByLabelAndOptionTextUp(label, value);
        }
    }

    //DOC: Use to logout w/o affecting P/F of Test.
    public static void logoutCurrentUserWithRetry() {
/*      //ENH: DIRECT URL -- EX. if env (and/or) HD_URL is set, just return.
        return;
 */
        driver.switchTo().defaultContent();
        int retries = 2;
        while (retries > 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("logoutlink"))).click();
                log.info("The user logs out...");
                return;
            } catch (Exception ignored) {
            }
            wait(200);
            retries--;
        }
        //IF it gets here, there is a problem with the browser state - refresh the browser and try 1 more time:
        try {
            recoverBrowserAfterError();
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("logoutlink"))).click();
            log.info("The user logs out...");
        } catch (Exception e) {
            log.error("ERROR: User Log out did not work correctly: " + e.getMessage());
        }
    }

    //ENH: Want this to do more if possible
    public static boolean recoverBrowserAfterError() {
        Screenshot.captureScreen(driver, "BROWSER_REFRESH", testName);
        driver.navigate().refresh();
        wait(3000);
        return waitForCommonPageLoadingElements();
    }

    public static boolean waitForCommonPageLoadingElements() {
        //ENV: After recent changes, these elements are disappearing then reappearing, throwing off this hold
        int successes = 0;
        int retries = 60; // x wait 200 milliseconds
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (retries > 0 && successes < 3) {
            wait(200);
            while (retries > 0) {
                try {
                    if (driver.getPageSource().contains("</html>")
                            && js.executeScript("return document.readyState").toString().equals("complete")
                    ) break;
                } catch (Exception ignored) {
                }
                wait(200);
                retries--;
            }

            boolean isPageSpinnerHidden = false;
            boolean isNoLoadBar = false;
            boolean isNoLoadBarSpinner = false;
            while (retries > 0) {
                try {
                    isPageSpinnerHidden = (js.executeScript(
                            "return document.evaluate(\"//div[@id='cover-spin' and contains(@style, 'display: block')]\", " +
                                    "document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue")
                            == null);
                } catch (Exception ignored) {
                }
                try {
                    isNoLoadBar = (js.executeScript(
                            "return document.evaluate(\"//div[@id='loading-bar']\", " +
                                    "document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue")
                            == null);
                } catch (Exception ignored) {
                }
                try {
                    isNoLoadBarSpinner = (js.executeScript(
                            "return document.evaluate(\"//div[@id='loading-bar-spinner']\", " +
                                    "document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue")
                            == null);
                } catch (Exception ignored) {
                }
                if (isPageSpinnerHidden && isNoLoadBar && isNoLoadBarSpinner) break;
                wait(200);
                retries--;
            }
            successes++;
        }
        if (successes >= 3) return true;
        log.warn("WARN: waitForCommonPageLoadingElements did not exit successfully. Continuing and hoping for the best.");
        return false;
    }

    public static boolean isUIHanging(String area) {
        if (waitForCommonPageLoadingElements()) return false; //all clear
        log.warn("WARN: HANG: " + area + ": will refresh browser for: " + driver.getCurrentUrl());
        return !recoverBrowserAfterError(); //after refresh, waitForCommon could not determine page is available
    }

    //ENH: Built-in wait until populated.  Need separate method if a blank field is desirable.
    public static String getValueUsingJSByXPath(String xpath) {
        //ENH: WORKAROUND: AWS SBTEST: This much waiting should not be necessary ~ retries = 10 should be plenty
        waitForCommonPageLoadingElements();
        int retries = 150; // x wait 200 milliseconds
        String actualValue = "";
        while (retries > 0) {
            try {
                actualValue = ((JavascriptExecutor) driver).executeScript(
                                "return document.evaluate(\"" + xpath + "\", document, null, " +
                                        "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value")
                        .toString();
            } catch (Exception ignored) {
            }
            if (!actualValue.equals("")) break;
            wait(200);
            retries--;
        }
        return actualValue;
    }

    public static void waitForAppearance(By locator) {
        wait.until(d -> d.findElements(locator).size() > 0);
    }

    public static void switchToEnvIframeByID(String iFrameName) {
        try {
            driver.switchTo().frame(locateElementByXPath(iFrameName));
            System.out.println("\n" + Date + " switching to iFrame \"<<" + iFrameName + ">>\"\n");
        } catch (NoSuchFrameException ex) {
            System.out.println("Unable to locate frame: " + iFrameName
                    + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Unable to navigate to frame: " + iFrameName
                    + ex.getMessage());
        }
    }

    //NOTE: No/(only Implicit) wait -- no Assert.fail
    //ENH: JavaScript immediate result if Exists, returns false or true.
    // User for element not being present (after an appropriate wait)
    public static boolean isElementPresentJSEByXPath(String xpath) {
        waitForCommonPageLoadingElements();
        String actualValue = "";
        try {
            actualValue = ((JavascriptExecutor) driver).executeScript(
                    "return document.evaluate(\"" + xpath + "\", document, null, " +
                            "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue").toString();
        } catch (Exception ignored) {
        }
        return (!actualValue.isEmpty() && !actualValue.equals("null"));
    }

    public static int getCountOfElementAllowZero(String locator, String searchType, boolean includeOnlyVisible) {
        List<WebElement> elementList = getElementList(locator, searchType);
        if (!includeOnlyVisible) return elementList.size();
        List<WebElement> elementsVisible = new ArrayList<>();
        for (WebElement ele : elementList) {
            if (ele.isDisplayed()) elementsVisible.add(ele);
        }
        return elementsVisible.size();
    }

    public static void wait(int x) {
        try {
            Thread.sleep(x);
        } catch (Exception ignored) {
        }
    }

    public static String getEntryValue(String text, String begin, String end) {
        return text.replaceAll(".*(?=" + begin + ")", "")
                .replaceAll("[^" + end + "]*$", "")
                .replaceAll("[^\\d]", "");
    }

    public void waitForElementsVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static String modifyAppConsHeader(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString().replaceAll(" ", "");
    }

    public static void handleNewTab(WebDriver driver) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iter = allWindowHandles.iterator();
        int size = allWindowHandles.size();
        String child = null;
        for (int i = 0; i < size; i++) {
            child = iter.next();
        }
        driver.switchTo().window(child);
        log.info("User switched to new window tab...");
    }

    public static String removeAllAfterString(String string, String word) {
        return string.replaceAll("[*]." + word + "", "").trim();
    }

    public static String req_conf(String appConsTab) {
        String tabType;
        tabType = appConsTab.replaceAll("[a-z ]", "").toLowerCase();
        return tabType;
    }

    public static String toCamelCase(String orgStr) {
        String[] pieces = orgStr.split(" ");
        orgStr = pieces[0].toLowerCase();
        for (int i = 1; i < pieces.length; i++) {
            orgStr += pieces[i];
        }
        return orgStr;
    }

    public static boolean closeAllOtherWindows(WebDriver driver) throws InterruptedException {
        String Parent_Window = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String currentWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equals(Parent_Window)) {
                driver.switchTo().window(currentWindowHandle);
                driver.close();
                Thread.sleep(2000);
            }
        }
        driver.switchTo().window(Parent_Window);
        return driver.getWindowHandles().size() == 1;
    }

    public static void useStreamToSortNPrint(Map<Character, Integer> map) {
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(
                        m -> {
                            int coint = m.getValue();
                            while (coint > 0) {
                                System.out.println("m = " + m.getKey());
                                coint--;
                            }
                        }
                );
    }

    public static boolean verifyColumnSorting(WebDriver driver, String columnName, boolean ascending) {
        // Click on the column header to initiate sorting
        driver.findElement(By.xpath("//th[text()='" + columnName + "']")).click();
        // Retrieve the list of elements that represent the rows of the table
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        // Extract the values of the specified column from each row
        List<String> columnValues = new ArrayList<>();
        for (WebElement row : rows) {
            columnValues.add(row.findElement(By.xpath(".//td[text()='" + columnName + "']")).getText());
        }
        // Compare the values to determine if they are in the correct sort order
        if (ascending) {
            for (int i = 0; i < columnValues.size() - 1; i++) {
                if (columnValues.get(i).compareTo(columnValues.get(i + 1)) > 0) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < columnValues.size() - 1; i++) {
                if (columnValues.get(i).compareTo(columnValues.get(i + 1)) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean verifyColumnSortingInAllPages(WebDriver driver, String columnName, boolean ascending) {
        // Click on the column header to initiate sorting
        driver.findElement(By.xpath("//th[text()='" + columnName + "']")).click();
        // Retrieve the total number of pages
        int totalPages = Integer.parseInt(driver.findElement(By.xpath("//span[@class='total-pages']")).getText());
        for (int i = 1; i <= totalPages; i++) {
            // Retrieve the list of elements that represent the rows of the table
            List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
            // Extract the values of the specified column from each row
            List<String> columnValues = new ArrayList<>();
            for (WebElement row : rows) {
                columnValues.add(row.findElement(By.xpath(".//td[text()='" + columnName + "']")).getText());
            }
            // Compare the values to determine if they are in the correct sort order
            if (ascending) {
                for (int j = 0; j < columnValues.size() - 1; j++) {
                    if (columnValues.get(j).compareTo(columnValues.get(j + 1)) > 0) {
                        return false;
                    }
                }
            } else {
                for (int j = 0; j < columnValues.size() - 1; j++) {
                    if (columnValues.get(j).compareTo(columnValues.get(j + 1)) < 0) {
                        return false;
                    }
                }
            }
            // Click on the next page button
            driver.findElement(By.xpath("//span[@class='next-page']")).click();
        }
        return true;
    }


    public void verifyErrorMessages(DataTable errorList) {
        BasePage.cucTabledata = errorList.asLists(String.class);
        IntStream.range(0, 1).forEach(x -> IntStream.range(x, 1).forEach(y -> {
            try {
                highLightElement(locateElementByXPath("(//*[text() = ' " + BasePage.cucTabledata.get(x).get(y) + " '])"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }


    public void verifyErrorMessages2(DataTable errorList) {
        errorList.asLists(String.class).stream()
                .flatMap(List::stream)
                .distinct()
                .forEach(errorMessage -> {
                    try {
                        String xpath = "(//*[text() = ' " + errorMessage + " '])";
                        highLightElement(locateElementByXPath(xpath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void moduleTitleNameVerification(String expICModTitlName) {
        Map<String, String> icModLocs = new HashMap<>();
        icModLocs.put(AdmCenMod, "\"//*[@id='app']//div[@class='cicdim-title add-app-info']\"");
        icModLocs.put(RepCenBanner, "\"//*[@id='app']/parent::div//h2\"");
        icModLocs.put(ListModule, "\"//*[@id='app']//*[@class='cicdim-title']\"");
        icModLocs.put(HDMod, "\"//*[@id='app-hd']/parent::div//h2\"");
        icModLocs.put(AppConsMod, "\"//*[@id='skipToContent']//h2\"");
        icModLocs.put(UVMod, "\"//*[@id='uv-root']//h1\"");
        String modLoc;
        for (int i = 0; i < icModLocs.size(); i++) {
            if (icModLocs.containsKey(expICModTitlName)) {
                modLoc = icModLocs.get(expICModTitlName);
                try {
                    wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(modLoc)));
                    String actualModuleTitleName = locateElementByXPath(modLoc).getText();
                    Assert.assertTrue(locateElementByXPath(modLoc).isDisplayed());
                    verifyActualText(expICModTitlName, actualModuleTitleName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refreshPageIfNotDisplayed(String feature) {
        String module = null;
        if (feature.equalsIgnoreCase(AdmCon) || feature.equalsIgnoreCase(RepCen) || feature.equalsIgnoreCase(ListMngm)) {
            module = "#cmmi";
        }
        if (feature.equalsIgnoreCase(HDCen) || feature.equalsIgnoreCase(UserVer)) {
            module = "#skipToContent";
        }
        if (feature.equalsIgnoreCase(AppCon)) {
            module = "#skipToContentMain";
        }
        if (locateElementsByCss(module).size() == 0) {
            log.info("Module is not displayed. Refreshing " + feature + " page ");
            recoverBrowserAfterError();
            waitForCommonPageLoadingElements();
        } else {
            log.info("Module is displayed");
        }
    }


    /* //ENH: Unused per IDE.  Review before deleting.
        public static void verifyIfMissingElementPartofIframe(List<WebElement> elementList) throws Exception {
            boolean containsIframe = driver.getPageSource().contains("iframe");
            System.out.println("Iframe is present : " + containsIframe);
            if (containsIframe) {
                driver.switchTo().frame(0);
                Assert.assertFalse(areElementsPresent(elementList));
                //areElementsPresent(elementList);
                driver.switchTo().defaultContent();
            } else {
                Assert.assertFalse(areElementsPresent(elementList));
            }
        }

        public static void verifyTiles(String role, String expectedTile, WebElement tileElem) throws Exception {
            if (role.contains("Application Approver")) {
                String actualTile = verifyIfElementPartofIframeGetText(tileElem);
                if (actualTile.equalsIgnoreCase(expectedTile)) {
                    System.out.println(actualTile + " tile is present");
                }
            }
        }

        public static boolean verifyIfTextValueNull(String attribute, String value) {
            boolean isNull = false;
            if (getTextofWebElement(attribute, value) == null || getTextofWebElement(attribute, value).isEmpty()) {
                isNull = true;
                System.out.println("Text value is null or empty :" + isNull);
            } else {
                isNull = false;
                System.out.println("Text value is null or empty :" + isNull);
            }
            return isNull;
        }

        public static boolean verifyIfIframeElementPresent(String locator, String type) throws Exception {
            boolean containsIframe = driver.getPageSource().contains("iframe");
            boolean present;
            if (containsIframe) {
                driver.switchTo().frame(0);
                present = isElementPresent(locator, type);
                driver.switchTo().defaultContent();
            } else {
                present = isElementPresent(locator, type);
            }
            return present;
        }

        //ENH: JavaScript immediate result if Exists, returns false or true
        public static boolean isElementPresentJSEByXPath(String xpath) {
            String actualValue = "";
            try {
                actualValue = ((JavascriptExecutor) driver).executeScript(
                        "return document.evaluate(\"" + xpath + "\", document, null, " +
                                "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue").toString();
            } catch (Exception ignored) {}
            return !actualValue.equals("") && !actualValue.equals("null");
        }

        public static boolean readFile(String Login_XLSX_FILE_PATH, String compareValue) throws Exception {
            System.out.println("Reading file...>>>" + Login_XLSX_FILE_PATH);
            ArrayList<String> records = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(Login_XLSX_FILE_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                records.addAll(Arrays.asList(values));
            }
            System.out.println(Arrays.asList(values));
            System.out.println(Arrays.asList(values).contains(compareValue));
            return Arrays.asList(values).contains(compareValue);
        }

        public static void uploadFile(String fileLocation) throws Throwable {
            Screenshot.captureScreen(driver, driver.getTitle(), testName);
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys(fileLocation);
            wait(2000);
            type(driver.findElement(By.xpath("//*[contains(@title, 'Comment')]")), "Test Comment");
            wait(1000);
            click(driver.findElement(By.xpath("//*[text() = 'Upload']")));
            wait(10000);
            readTable("File Upload Success");
            // readTable(fileLocation);
        }

        public static boolean readTable(String compareValue) throws Throwable {
            boolean foundValue = false;
            ((JavascriptExecutor) driver).executeScript("scroll(0,5000)");
            WebElement table = driver.findElement(By.id("rfMainContent"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            for (WebElement row : allRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    System.out.println("content >>   " + cell.getText());
                    if (cell.getText().contains(compareValue)) {
                        // driver.findElement(By.xpath("//button[@title=\""+cells.get(5).getText()+"\"]")).click();
                        System.out.println("Found value on table:::" + compareValue);
                        highLightElement(cell);
                        foundValue = true;
                    }
    //				else if (compareValue.contains(cell.getText())) {
    //					System.out.println("Found value on table:::"+compareValue);
    //					highLightElement(cell);
    //				}
                }
            }
            return foundValue;
        }


        public static void verifyLInks(WebElement tile, WebElement link, WebElement tile1, WebElement link1) {
            try {
                if (env.equalsIgnoreCase("DEV")) {
                    verifyIfElementPartofIframe(tile);
                    verifyIfElementPartofIframeGetText(link);
                    verifyIfElementPartofIframe(tile1);
                    verifyIfElementPartofIframeGetText(link1);
                } else if (env.equalsIgnoreCase("VAL")) {
                    verifyIfElementPartofIframe(tile);
                    verifyIfElementPartofIframeGetText(link);
                    verifyIfElementPartofIframe(tile1);
                    verifyIfElementPartofIframeGetText(link1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //ENH: This needs to be rethought
        public static String verifyText(WebElement element) throws Exception {
            String attributeValue = "";
            if (isElementPresent(element)) {
                String[] attributes = {"tagName", "name", "type", "value", "class", "src", "text", "href", "css", "xpath"};
                String str = Arrays.toString(attributes);
                for (int i = 0; i < 10; i++) {
                    attributeValue = element.getAttribute(str);
                    if (attributeValue == null) {
                        System.out.println("Element value is null");
                        continue;
                    } else if (attributeValue.equals(element)) {
                        System.out.println(attributeValue);
                    }
                }
            }
            return attributeValue;
        }

        //ENH: This does not seem correct.
        public static void verifyTitleByText(String text){
            locateEleByXPathTextNormSpace(text);
        }

        public static WebElement switchToNewWindow(WebDriver driver, String iframeId) {
            driver.switchTo().frame(iframeId);
            WebElement window = driver.switchTo().activeElement();
            return window;
        }

        //method for registration
    public static void getRegistrationAnswers(String ans1, String ans2, String ans3) throws Exception {

        RegistrationDetails = ReadRegistrationData.RegistrationCredentials("UserName", "UserNumber");

        answer1 = RegistrationDetails.get(1);
        answer2 = RegistrationDetails.get(2);
        answer3 = RegistrationDetails.get(3);

        System.out.println("answer1...>>>" + ans1 + "_" + answer1);
        System.out.println("answer2...>>>" + ans2 + "_" + answer2);
        System.out.println("answer3...>>>" + ans3 + "_" + ans3);

        System.out.println("answer1...>>>" + ans1 + "_" + answer1);
        System.out.println("answer2...>>>" + ans2 + "_" + answer2);
        System.out.println("answer3...>>>" + ans3 + "_" + answer3);
    }

        public static void getRegistrationDataStep3(String uName, String pass, String confpass) throws Exception {
            RegistrationDetails = ReadRegistrationData.RegistrationCredentials(uName, "UserNumber");

            userName = RegistrationDetails.get(0);
            password = RegistrationDetails.get(1);
            confirmPassword = password;

            System.out.println("userName...>>>" + uName + "_" + userName);
            System.out.println("password...>>>" + pass + "_" + password);
            System.out.println("password...>>>" + confpass + "_" + confirmPassword);

            System.out.println("userName...>>>" + uName + "_" + userName);
            System.out.println("password...>>>" + pass + "_" + password);
            System.out.println("password...>>>" + confpass + "_" + confirmPassword);

        }

        public int getNumberOfEntries(WebElement str) {
            String entriesTxt = str.getText().trim();
            String[] aEntriesText = entriesTxt.split(" ");
            String totalEntriesText = aEntriesText[aEntriesText.length - 2];
            return Integer.parseInt(totalEntriesText);
        }

        public static String getTotalEntryValue(String text, String begin, String end) {
            return text.replaceAll(".*(?=" + begin + ")", "")
                    .replaceAll("[^" + end + "]*$", "")
                    .replaceAll("[^\\d]", "");
        }
     */

    /* //ENH: get Error Code/Text
//ENH: 403 503 - displayed error is not picked up by normal means in the driver.Page Source or by JavaScript.
// Investigate later.
    public static List<String> findPossibleErrorText(WebDriver driver, Scenario scenario) {
        List<String> allMatches = new ArrayList<>();
        String orgWindow = driver.getWindowHandle();
        for (String window :  driver.getWindowHandles()) {
            driver.switchTo().window(window);
            String errorText = getTextJSEByXPath("//div[contains(text, "error code")]);
            if (!errorText.contains("")) {
                Matcher m = Pattern.compile("error code (\\d+)")
                        .matcher(errorText);
                while (m.find()) {
                    if (scenario != null) scenario.log("ERROR: Error code: " + m.group());
                    allMatches.add(m.group().replaceAll("error code ", ""));
                }
            }
        }
        driver.switchTo().window(orgWindow);
        return allMatches;
    }

    public static String getTextJSEByXPath(String xpath) {
        waitForCommonPageLoadingElements();
        String actualValue = "";
        try {
            actualValue = ((JavascriptExecutor) driver).executeScript(
                    "return document.evaluate(\"" + xpath + "\", document, null, " +
                            "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue").toString();
        } catch (Exception ignored) {}
        return actualValue;
    }
 */

    /*
     DatePicker is a table. Thus we can navigate to each cell
     and if a cell matches with the current date then we will click it.
        public static void select_a_Day(List<WebElement> elementList, String currentDay) {

        elementList.stream().filter(element -> element.getText().contains(currentDay))
                .findFirst().ifPresent(WebElement::click);
        System.out.println(currentDay + " clicked");
    }
 */

    /* Not currently used, but may be useful soon
    public static String getTextofMultipleTextBoxes(List<WebElement> textboxesList) {
        String actualText = "";
        textboxesList = driver.findElements(By.xpath("//input[contains(@type,'Text')]"));
        int size = textboxesList.size();
        for (WebElement element : textboxesList) {
            if (isElementPresent(element)) {
                actualText = element.getText();
                System.out.println("Text is :" + actualText);
            }
        }
        return actualText;
    }

    public static String getTextofSingleTextBoxByTitle(String value) {
        String actualText = "";
        WebElement el = driver
                //input[@title='First Name']
                //input[contains(@type,'Text') and contains(@title,'" + value + "')]
                .findElement(By.xpath("//input[@title='" + value + "']"));
        if (isElementPresent(el)) {
            actualText = el.getText();
            System.out.println("Text is :" + actualText);
        }
        return actualText;
    }

    public static String getTextofWebElement(String value) {
        String actualText = "";
        WebElement el = driver.findElement(By.xpath("//*[contains(text(), '" + value + "')]"));
        if (isElementPresent(el)) {
            actualText = el.getText();
            System.out.println("Text is :" + actualText);
        }
        return actualText;
    }

    public static void getLinks(List<WebElement> links) throws Exception {
        try {
            int linkCount = links.size();
            System.out.println("Number of applications: " + linkCount);

            //iterate though the list and click each link:
            for (WebElement link : links) {
                System.out.println(link.getText());
                click(link);
                wait(3000);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }
 */

    /*
    public static void accessAppConsoleInDifferentEnv(String app) throws Exception {
        String tileID = "cms-ic-tile";
        if (!env2.endsWith("0")) tileID = "cms-ic" + env + env2 + "-tile";
        click(wait.until(ExpectedConditions.presenceOfElementLocated(By.id(tileID))));
        click(driver.findElement(By.xpath("//*[contains(text(), '" + app + "')]")));
    }
 */

}
