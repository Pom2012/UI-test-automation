package com.model.pages.appConsole;

import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmailNotifications extends BasePage {

    public static java.util.Date date3 = new Date();
    public static SimpleDateFormat dayFormat2 = new SimpleDateFormat("MMddyyyyHHmmss");
    public static String dayAndTimeAsNumb = dayFormat2.format(date3);

    public static void emailTemplateSetUp(String template, String subjectLine, String from, String bodyText, String enableDisable, String html) throws Exception {
        String body1;
        switch (subjectLine) {
            case "Template":
            case "Creating a new template":
                templateName = template + dayAndTimeAsNumb;
                body1 = bodyText + dayAndTimeAsNumb;
                body4Creating = body1;
                locateElementByCSS("#mat-input-1").sendKeys(templateName);
                locateElementByCSS("#mat-input-2").sendKeys(subjectLine);
                locateElementByCSS("#mat-input-3").sendKeys(from);
                locateElementByCSS("#mat-input-4").sendKeys(body1);
                selectFromDropDownMenu(locateElementByCSS("#mat-input-5"), enableDisable);
                if (html.equalsIgnoreCase("Yes"))
                    click(locateElementByCSS("#mat-checkbox-3-input"));
                else {
                    System.out.println("the checkbox is not selected");
                }
                break;
            case "Updating the new template":
                templateName = template + dayAndTimeAsNumb;
                System.out.println("Email template name is " + templateName);
                String body2 = bodyText + dayAndTimeAsNumb;
                body4Updating = body2;
                locateElementByCSS("#mat-input-2").clear();
                locateElementByCSS("#mat-input-2").sendKeys(subjectLine);
                locateElementByCSS("#mat-input-4").clear();
                locateElementByCSS("#mat-input-4").sendKeys(body2);
                System.out.println("Updated body = " + body2);
                break;
            case "Deleting the new template":
                templateName = template + dayAndTimeAsNumb;
                System.out.println("Email template name is " + templateName);
                String body3 = bodyText + dayAndTimeAsNumb;
                body4Deleting = body3;
                type(locateElementByCSS("#mat-input-1"), templateName);
                type(locateElementByCSS("#mat-input-2"), subjectLine);
                type(locateElementByCSS("#mat-input-4"), body3);
                System.out.println("Deleted body = " + body3);
                selectFromDropDownMenu(locateElementByCSS("#mat-input-5"), enableDisable);
                break;
        }
    }

    public void verifySelectedReceiveNoEmailNotifOpt(DataTable emNotOpts) throws Exception {
        cucTabledata = emNotOpts.asLists(String.class);
        waitForCommonPageLoadingElements();
        click(locateElementByXPath("//*[contains(text(),'" + cucTabledata.get(0).get(0) + "')]/parent::label//input"));
        for (int i = 0; i < cucTabledata.size(); i++) {
            String s = cucTabledata.get(i).get(0);
            System.out.println("s = " + s);
            if (cucTabledata.get(i).get(1).equalsIgnoreCase("selected")) {
                Assert.assertTrue(locateElementByXPath("//*[contains(text(),'" + s + "')]/parent::label//input").isSelected());
            } else {
                Assert.assertFalse(locateElementByXPath("//*[contains(text(),'" + s + "')]/parent::label//input").isSelected());
            }
        }
        System.out.println("cucTabledata.get(0).get(2) = " + cucTabledata.get(0).get(2));
        if (cucTabledata.get(0).get(2).equals("Y")) {
            click(locateElementByCSS("[class='dialog-header'] button"));
        }
    }

    public void verifyUnSelectedReceiveNoEmailNotifOpt(DataTable emNotOpts) throws Exception {
        cucTabledata = emNotOpts.asLists(String.class);
        listOfWE = driver.findElements(By.xpath("//div[@id='reqDialog']//input"));
        click(locateElementByXPathContainsText(cucTabledata.get(0).get(0)));
        verifyCheckBoxUnselected(listOfWE);
        for (int i = 1; i <= 4; i++) {
            click(locateElementByXPath("(//div[@id='reqDialog']//input)[" + i + "]"));
        }
        verifyCheckBoxIsSelected(listOfWE);
    }

    public void emailSettingsVerification(String featureValue) throws Exception {
        verifyActualText(locateElementByCSS("[id='req-access-email-pref-icon'] font").getText(), featureValue);
        highLightElement(locateElementByID("cicdim-email-pref-icon"));
        Assert.assertTrue(locateElementByID("req-access-email-pref-icon").isDisplayed());
    }

    public void verifyEmailNotificationTitleAndCheckBoxOptions(DataTable emNotOpts) {
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("[ui-view='emailSettings']"))).isDisplayed();
        locateElementsByCss("[ui-view='emailSettings'] input").forEach(Model_CommonFunctions::isElementPresent);
        emNotOpts.asLists().forEach(p -> {
            locateElementsByCss("[class='mat-checkbox-label'] ").forEach(v -> {
                if (v.getText().contains(p.get(0))) {
                    verifyActualText(p.get(0), v.getText());
                }
            });
        });
    }

    public void verifydefaultEmailNotificationCheckBoxOptions(DataTable emNotOpts) {
//input[aria-checked="true"]
        for (List<String> list : emNotOpts.asLists()) {
            for (WebElement element : locateElementsByCss("[ui-view='emailSettings'] input")) {
                if(list.get(1).contains("deselected")){
                    element.isSelected();
                    System.out.println(list.get(0)+ "isSelected = " + element);
                    break;
                }else {
                    boolean isSelected = !element.isSelected();
                    System.out.println(list.get(0)+ "isSelected = " + isSelected);
                }
                break;
            }
        }
////*[@class='mat-checkbox-label']/parent::label//input
//        emNotOpts.asLists().forEach(l -> {
//                    locateElementsByCss("[ui-view='emailSettings'] input").forEach(p -> {
//                        if (l.get(1).equalsIgnoreCase("deselected")) {
//                            System.out.println(l.get(0) + " deSelected = " + p.getAttribute("element"));
//                        }else {
//                            System.out.println(l.get(0) + " deSelected = " + p.getAttribute("aria-checked"));
//                        }
//                    });
//                }
//        );


    }
}
