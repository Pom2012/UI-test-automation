package com.model.pages.appConsole;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.base.Constants.IC;

public class Unauthportal extends ApplicationConsolePages {

    public void selectICFromCMSApps(String user, String apps, String icOpts, String selUApps) throws Exception {
        click(locateEleByXPathTextNormSpace(apps));
        isElementPresent(locateElementByCSS("[id='ngSelectApp']"));
        click(locateElementByCSS("[id='ngSelectApp']"));
        String modelApplication = env2.equals("SB") ? "ICSB-" + IC + " SB" : "IC-" + IC;
        type(locateElementByCSS("#textSearch"), modelApplication + Keys.ENTER);
        log.info(modelApplication + " is selected");
    }

    public void openDialog(String popUpText) throws Throwable {
        //TODO: use popUpText as a verification point (ex. header text), but unauth Header for SB is different
        // isElementPresent is expected to P/F
        // Made generic, move to Modal_Common
        String popUpLocId = env2.equals("SB") ? "cms-modal-icsb-title" : "cms-modal-ic-title";
        isElementPresent(locateElementByXPath("//div[contains(@style, 'display: block')]//*[@id='" + popUpLocId + "']"));
    }

    public void clickGetStartedAndHandleWinTab(String linkText) throws Exception {
//        scroll_Down(locateElementByXPath("//div[contains(@style, 'display: block')]//a[text()='Frequently Asked Questions']"));
        scroll_Down(locateElementByXPath("//div[contains(@style, 'display: block')]//div[contains(@class, 'modal-footer')]//a[@title='" + linkText + "']"));
        click(locateElementByXPath("//div[contains(@style, 'display: block')]//div[contains(@class, 'modal-footer')]//a[@title='" + linkText + "']"));
        handleNewTab(driver);
        System.out.println("Title of the new window: " + driver.getTitle());
    }

    public void searchICModuleAppInUnauthPortalPage(String appName, String inputPlaceholder) throws Exception {
        startUsingIframe();
        System.out.println("appName = " + appName);
        type(locateElementByXPath("//input[@placeholder='" + inputPlaceholder + "']"), appName + Keys.ENTER);
        wait(3000);
        //TODO: DATA: May not want this in the Java; and/or consolidate with getModelAppName(String application)
        click(locateEleByXPathTextNormSpace(environment.equals("VALAWS") ? appName + " - VAL1" : appName));
        endUsingIframe();
    }

    public void getAccessToICModuleAppVerification(String actualAppName) throws Exception {
        waitForCommonPageLoadingElements();
        longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locateElementByCSS("object")));
        if (!isElementPresentJSEByXPath("//td[contains(normalize-space(), 'This portlet is unavailable.')]")) {
            highLightElement(locateElementByXPath("//*[@id='rfMainContent']//h1"));
            expectedText = locateEleByXPathTextNormSpaceAttr("h1", actualAppName).getText();
            log.info("expected Application name" + expectedText);
            Assert.assertEquals(expectedText, actualAppName);
        } else {
            System.out.println("The portlet is not available ");
        }


    }

}
