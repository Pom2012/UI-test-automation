package com.model.stepDefinition;

import com.model.utility.Driver;
import com.model.utility.Screenshot;
import com.model.base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import model_Runner.model_TestRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public synchronized void setUpTC(Scenario scenario) throws Exception {
        if (model_TestRunner.TR_Signature == null) model_TestRunner.initTestRun();
        BasePage.log.info("\n\n-- START: @Before Test Case Scenario: " + scenario.getName() + "  --------------\n");
        this.printThreadInfo(scenario.getName());
        System.out.println("   IC Environment.......>>>		" + BasePage.env2);
        BasePage.driver = Driver.initDriver(scenario.getName());
    }

    @After
    public synchronized void tearDownTC(Scenario scenario) {
        BasePage.log.info("\n\n-- @Test Case actions COMPLETE  --------------\n");
        if (scenario.isFailed()) {
            BasePage.log.error("This scenario is failed. Taking a screenshot...!!!");
            BasePage.log.error("The url >>>> " + BasePage.driver.getCurrentUrl());
            TakesScreenshot ts = (TakesScreenshot) BasePage.driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            String fullPathFileName = Screenshot.captureScreen(BasePage.driver, "FAILED", scenario.getName().replace(" ","_"));
            scenario.attach(screenshot, "image/png", fullPathFileName);
            scenario.log("ERROR: URL: " + BasePage.driver.getCurrentUrl());
            scenario.log("ERROR: userName: " + BasePage.userID);
        }
        if (BasePage.driver != null) {
            BasePage.driver.quit();
        }
        this.printThreadInfo(scenario.getName());
        BasePage.log.info("-- END: @After Test Case Scenario: " + scenario.getName() + "  --------------");
    }

    private void printThreadInfo(String scenarioName) {
        Thread.currentThread().setName(scenarioName);
        BasePage.log.info("__START: DEBUG: scenarioName__:");
        BasePage.log.info(Thread.currentThread().getName() + " Thread.currentThread().getName()");
        BasePage.log.info(Thread.currentThread().getId() + " Thread.currentThread().getId()");
        BasePage.log.info(Thread.activeCount() + " Thread.activeCount()");
        BasePage.log.info(Thread.currentThread().getThreadGroup().getName() + " Thread.currentThread().getThreadGroup().getName()");
        BasePage.log.info(Thread.currentThread().getThreadGroup().activeCount() + " Thread.currentThread().getThreadGroup().activeCount()");
        BasePage.log.info("__END: DEBUG: scenarioName__:");
    }
}
