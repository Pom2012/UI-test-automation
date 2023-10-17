package com.model.utility;

import com.model.base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

     public static WebDriver initDriver(String testName) throws Exception {
        WebDriver TC_driver = null;
        boolean isLocalBrowser = true;
        boolean isVideoCapture = Boolean.parseBoolean(BasePage.prop.getProperty("e34.video"));
        String GRID_URL = "";
        if (BasePage.prop.getProperty("selenium.server").equalsIgnoreCase("BOX")) {
            GRID_URL = BasePage.prop.getProperty("BOX_URL");
            isLocalBrowser = false;
        }
        else if (BasePage.prop.getProperty("selenium.server").equalsIgnoreCase("GRID")) {
            GRID_URL = BasePage.prop.getProperty("GRID_URL");
            isLocalBrowser = false;
        }
        String browserType = BasePage.prop.getProperty("browser").toLowerCase();
        if ( browserType.startsWith("chrome")|| browserType.contains("-chrome") ) {
            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
            chromePrefs.put("download.prompt_for_download", false);
            ChromeOptions optionsChr = new ChromeOptions();
            optionsChr.setExperimentalOption("prefs", chromePrefs);
            optionsChr.setCapability("profile.default_content_setting_values.automatic_downloads", 1);
            if (browserType.contains("headless")) {
                optionsChr.setHeadless(true);
                optionsChr.addArguments("--ignore-certificate-errors");
            }
            if (browserType.contains("incognito")) {
                optionsChr.addArguments("--incognito");
            }
            if (isLocalBrowser) {
                WebDriverManager.chromedriver().setup();
                TC_driver = new ChromeDriver(optionsChr);
            } else {
                optionsChr.setCapability("e34:l_testName", testName);
                optionsChr.setCapability("e34:video", isVideoCapture);
                optionsChr.setCapability("e34:token", BasePage.prop.getProperty("selenium.box.token"));
                TC_driver = new RemoteWebDriver(new URL(GRID_URL), optionsChr);
            }
        }
        if ( browserType.startsWith("firefox")|| browserType.contains("-firefox") ) {
            FirefoxOptions optionsFF = new FirefoxOptions();
            if (browserType.contains("headless")) {
                optionsFF.setHeadless(true);
            }
            if (browserType.contains("incognito")) {
                optionsFF.addArguments("-private");
            }
            if (browserType.contains("-linux") ) {
                optionsFF.setCapability("platform", Platform.LINUX);
            }
            if (isLocalBrowser) {
                WebDriverManager.firefoxdriver().setup();
                TC_driver = new FirefoxDriver(optionsFF);
            } else {
                optionsFF.setCapability("e34:l_testName", testName);
                optionsFF.setCapability("e34:video", isVideoCapture);
                optionsFF.setCapability("e34:token", BasePage.prop.getProperty("selenium.box.token"));
                TC_driver = new RemoteWebDriver(new URL(GRID_URL), optionsFF);
            }
        }
        if ( browserType.startsWith("ie")|| browserType.contains("-ie") ) {
            InternetExplorerOptions optionsIE = new InternetExplorerOptions();
            optionsIE.setCapability("ignoreProtectedModeSettings", true);
            optionsIE.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            optionsIE.ignoreZoomSettings()
                     .destructivelyEnsureCleanSession();
            if (isLocalBrowser) {
                WebDriverManager.iedriver().setup();
                TC_driver = new InternetExplorerDriver(optionsIE);
            } else if (BasePage.prop.getProperty("selenium.server").equalsIgnoreCase("GRID")) {
                optionsIE.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                TC_driver = new RemoteWebDriver(new URL(GRID_URL), optionsIE);
            } else {
                throw new Exception("IE is not supported by Selenium Box");
            }
        }
        if (browserType.startsWith("edge")||browserType.contains("-edge")) {
            EdgeOptions optionsEdge = new EdgeOptions();
            if (isLocalBrowser) {
                WebDriverManager.edgedriver().setup();
                TC_driver = new EdgeDriver(optionsEdge);
            } else {
                optionsEdge.setCapability("e34:l_testName", testName);
                optionsEdge.setCapability("e34:video", isVideoCapture);
                optionsEdge.setCapability("e34:token", BasePage.prop.getProperty("selenium.box.token"));
                TC_driver = new RemoteWebDriver(new URL(GRID_URL), optionsEdge);
            }
        }
        if ( browserType.startsWith("safari")|| browserType.contains("-safari") ) {
            SafariOptions optionsSaf = new SafariOptions();
            if (isLocalBrowser) {
                WebDriverManager.getInstance(SafariDriver.class).setup();
                TC_driver = new SafariDriver(optionsSaf);
            } else {
                optionsSaf.setCapability("browserName", "webkit");
                optionsSaf.setCapability("e34:l_testName", testName);
                optionsSaf.setCapability("e34:video", isVideoCapture);
                optionsSaf.setCapability("e34:token", BasePage.prop.getProperty("selenium.box.token"));
                TC_driver = new RemoteWebDriver(new URL(GRID_URL), optionsSaf);
            }
        }

        if (TC_driver == null ) throw new Exception("WebDriver was not initialized: " + browserType + " : " + testName);
        TC_driver.manage().window().maximize();
        System.out.println("    The window maximizing....");
        TC_driver.manage().deleteAllCookies();
        System.out.println("    All cookies are deleted....");
        BasePage.miniWait = new WebDriverWait(TC_driver, Duration.ofMillis(10));
        BasePage.wait = new WebDriverWait(TC_driver, BasePage.TIMEOUT);
        BasePage.longWait = new WebDriverWait(TC_driver, BasePage.LONG_TIMEOUT);
        BasePage.fluentWait = new FluentWait<>(TC_driver).withTimeout(Duration.ofSeconds(30)).
                pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        TC_driver.manage().timeouts().implicitlyWait(BasePage.TIMEOUT);
        TC_driver.manage().timeouts().pageLoadTimeout(BasePage.PAGE_LOAD_TIMEOUT);
        System.out.println("    Browser Window: " + TC_driver.getWindowHandle());
        return TC_driver;
    }
}
