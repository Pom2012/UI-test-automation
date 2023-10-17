package com.model.stepDefinition.utilStepDef;

import com.model.utility.DataHelper;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.HashMap;
import java.util.List;

public class AppsAndRolesCreatStepDefs extends BasePage {


    @And("^provide the information for the below text fields and select dropdowns:$")
    public void provide_the_information_for_the_below_text_fields_and_select_dropdowns(DataTable appfields) throws Throwable {
        creatingMultpleAppsAndRoles(appfields);
    }

    public void creatingMultpleAppsAndRoles(DataTable appfields) throws Exception {
        final List<HashMap<String, String>> apps = DataHelper.data("newApps.xlsx", "SBApps");
        final List<HashMap<String, String>> roles = DataHelper.data("newApps.xlsx", "SBRoles");
        cucTabledata = appfields.asLists(String.class);
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < 9; k++) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(k).get(0) + "']"), apps.get(i).get(cucTabledata.get(k).get(0)));
            }
            click(locateElementByXPath("//button[normalize-space()='Verify']"));
            if (apps.get(i).get("Allow Mapped Role").equalsIgnoreCase("Y")) {
                selectFromDropDownMenuByValueAndElement("Y", locateElementByID("allowMappedRole"));
            }
//            click(locateEleByXPathTextNormSpaceAttr("button", "Add Application"));
//            handleAlert();
//            click(locateEleByXPathTextNormSpaceAttr("button", "Add New Role"));
//            for (int j = 0; j < 6; j++) {
//                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(j).get(1) + "']"), roles.get(i).get(cucTabledata.get(j).get(1)));
//            }
        }
    }
}
