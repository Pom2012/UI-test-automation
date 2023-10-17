package com.model.base;

import com.github.javafaker.Faker;
import com.model.utility.Driver;
import com.model.utility.Model_CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public abstract class BasePage extends Model_CommonFunctions implements Constants{
    public static String model;
    public static String browser;
    public static String url;
    public static String userID = null;
    public static String password = null;
    public static String icRole = null;
    public static String userSSN = null;
    public static String fName = null;
    public static String lName = null;
    public static String Email;
    public static String dEmail;
    public static String dPassword;
    public static Properties prop = new Properties();
    public static String attrValue = null;
    public static int MaxJastif = 500;
    public static Faker faker = new Faker();
    public static Random random = new Random();
    public static String listName = null;
    public static String envList = null;
    public static Actions actions;
    public static WebElement webElement = null;
    public static WebElement webElement2 = null;
    public static List<List<String>> cucTabledata = null;
    public static List<WebElement> listOfWE = null;
    public static List<List<String>> listValue = null;
    public static List<Map<String,String>> mapList =null;
    public static String REQUESTS_XLSX = "Requests/requests.xlsx";
    public static String templateName;
    public static String body4Creating = null;
    public static String body4Updating = null;
    public static String body4Deleting = null;
    public static String createdEmailTemplateOV = null;
    public static String updatedEmailTemplateOV = null;
    public static String deletedEmailTemplateOV = null;
    public static String createdEmailTemplateNV = null;
    public static String updatedEmailTemplateNV = null;
    public static String application = null;
    public static String appDipAcroName = null;
    public static List<String> requests=null;
    public static String requestID = null;
    public int cols = 0, rows = 0;

    public WebDriver getDriver() {
        if (BasePage.driver != null) return BasePage.driver;
//TODO: FIXED by reuseForks=false??: Unclear why some tests get
// "Session ID is null. Using WebDriver after calling quit()?" . Investigate when possible.
        try {
            if (testName == null || testName.equals("")) {
                System.out.println("ERROR: NULL DRIVER: Driver is null, as is Test Case: " + Date);
                BasePage.driver = Driver.initDriver("NULL DRIVER: " + Date);
            } else {
                System.out.println("ERROR: NULL DRIVER: Unclear why Driver is null for Test Case: " + testName);
                BasePage.driver = Driver.initDriver(testName);
            }
            return BasePage.driver;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ERROR: NULL DRIVER: Unclear why Driver is null for Test Case: " + testName);
        return null;
    }

    public void setDriver(WebDriver wd) {
        Model_CommonFunctions.driver = wd;
    }

    public static void openPortal(String testName) {
        System.out.println("    Test Name............>>>		" + testName);
        browser = prop.getProperty("browser");
        System.out.println("    Browser..............>>>		" + browser);
//        url = "https://google" + env.toLowerCase() + ".ddd.com";
        url = "https://google.com";
        driver.get(url);
        try {
            waitForCommonPageLoadingElements();
        } catch (Exception e) {
            System.out.println("DEBUG: waitForCommonPageLoadingElements: OpenPortal: " + testName + ": " + e.getMessage());
        }
        System.out.println("\n	The page loading is completed for: " + testName + "\n");
    }

}
