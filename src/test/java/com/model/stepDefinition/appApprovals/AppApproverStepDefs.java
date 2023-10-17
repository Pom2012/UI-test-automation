package com.model.stepDefinition.appApprovals;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import static com.model.utility.Model_CommonFunctions.*;

public class AppApproverStepDefs {

    @And("^views \"([^\"]*)\" tile, clicks on it and see \"([^\"]*)\"$")
    public void views_tile_clicks_on_it_and_see(String approvalsTile, String myPendAppTitle) throws Throwable {
        click(locateElementByCSS("#cms-approvals-tile"));
        String actualHeader = locateElementByCSS("#cms-pending-approvals-header").getText();
        Assert.assertEquals("No such header", actualHeader, myPendAppTitle);
        click(locateElementByCSS("#cms-homepage-header-logo-unauth"));
    }

    @Then("^clicks on \"([^\"]*)\" tile and see the below:$")
    public void clicks_on_tile_and_see_the_below(String annRoleCert, DataTable data) throws Throwable {
        List<List<String>> cucTabledata = data.asLists(String.class);
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathContainsText(annRoleCert))).click();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//*[contains(text(),'" + cucTabledata.get(0).get(1) + "')])[2]")));
        for (int i = 0; i < cucTabledata.size(); i++) {
            if (i == 0) continue;
            if (i == 1) {
                highLightElement(locateElementByXPath("(//a[normalize-space()='" + cucTabledata.get(i).get(1) + "'])"));
                highLightElement(locateElementByID("certList"));
            }
            if (i == 2) {
                clickWithJSE(locateElementByID("cms-certSearchTabLi"));
                click(locateElementByID("cms-certSearch"));
                highLightElement(locateElementByXPath("(//a[normalize-space()='" + cucTabledata.get(i).get(1) + "'])"));
                highLightElement(locateElementByID("cert_searchTabsContainer"));
            }
        }
        click(locateElementByCSS("#cms-homepage-header-logo-unauth"));
    }
}
