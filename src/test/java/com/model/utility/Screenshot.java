package com.model.utility;

import com.model.base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
	static Date date = new Date() ;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

	public static String captureScreen(WebDriver driver, String title, String testName) {
		String fullPathFileName = testName;
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			fullPathFileName = BasePage.prop.getProperty("screenshotDir") + "/" + dateFormat2.format(date) + "/" + title + "/" +
					testName.replaceAll("[^0-9a-zA-Z ]", "_") +
					"_" + dateFormat.format(new Date()) + ".png";
			fullPathFileName = fullPathFileName.replaceAll("\\s+", " ").replaceAll("_+", "_");
			FileUtils.copyFile(source, new File(fullPathFileName));
		}
		catch (Exception e) {
			System.err.println("Failed to capture screenshot: " + e.getMessage());
		}
		return fullPathFileName.replace("/", "_").replaceAll("_+", "_");
	}
}
