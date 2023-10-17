package com.model.stepDefinition.usRegStepDef;

import com.model.pages.prtlModule.PortalPages;
import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step3_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class NewUserRegistrationExcel_StepDefinition extends BasePage {

    Portal_UserRegistration_Step2_Locators newUserReg2;
    Portal_UserRegistration_Step3_Locators newUserReg3;
    PortalPages portalPages = new PortalPages();

    public List<HashMap<String, String>> datamap;

    public NewUserRegistrationExcel_StepDefinition() {
        datamap = DataHelper.data("NewUserRegData.xlsx", env);
        newUserReg2 = PageFactory.initElements(driver, Portal_UserRegistration_Step2_Locators.class);
        newUserReg3 = PageFactory.initElements(driver, Portal_UserRegistration_Step3_Locators.class);
    }

    @Then("^click step 2 Next button$")
    public void click_step_2_next_button() throws Exception {
        clickWithJSE(newUserReg2.step2NextLink2);
    }

    @Then("^click step 3 Next button$")
    public void click_step_3_next_button() throws Exception {
        clickWithJSE(newUserReg3.step3NextLink);
    }

    @And("^enters and/or selects data for \"([^\"]*)\" from excel \"([^\"]*)\"$")
    public void enters_and_or_selects_data_for_from_excel(String attribute, String row) throws Exception {
        portalPages.newUserRegistration(attribute, row);
    }

    @And("^\"([^\"]*)\" entered \"([^\"]*)\" data$")
    public void entered_data(String strArg1, String attribute) throws Exception {
        int index = Integer.parseInt(strArg1) - 1;
        String value = datamap.get(index).get(attribute);
        switch (attribute) {
            case "FirstName":
                type(newUserReg2.fName, value);
                break;
            case "LastName":
                type(newUserReg2.lName, value);
                break;
            case "SSNumber":
                type(newUserReg2.socialSecNumber, value);
                break;
            case "BirthMonth":
                selectFromDropDownMenu(newUserReg2.birthMonthDropDown, value);
                break;
            case "BirthDate":
                selectFromDropDownMenu(newUserReg2.birthDateDropDown, value);
                break;
            case "BirthYear":
                selectFromDropDownMenu(newUserReg2.birthYearDropDown, value);
                break;
            case "Street":
                type(newUserReg2.homeAddress, value);
                break;
            case "City":
                type(newUserReg2.city, value);
                break;
            case "State":
                selectFromDropDownMenu(newUserReg2.state, value);
                break;
            case "ZipCode":
                type(newUserReg2.zipCode, value);
                break;
            case "Email":
                type(newUserReg2.eMail, value);
                break;
            case "ConfirmEmail":
                type(newUserReg2.eMailConfirm, value);
                break;
            case "PhoneNumber":
                type(newUserReg2.phoneNumber, value);
                break;
            case "UserName":
                type(newUserReg3.uName, env+value);
                break;
            case "Password":
                type(newUserReg3.passw, value);
                break;
            case "ConfirmPassword":
                type(newUserReg3.passwConf, value);
                break;
            case "Challenge_Question_1":
                selectFromDropDownMenu(newUserReg3.secQuestion, value);
                break;
            case "Challenge_Question_1_Answer":
                type(newUserReg3.secQuestionAnswer, value);
                break;
            default:
                System.out.println("No a such value");
                ;
        }
    }

    @And("^click submit user button$")
    public void click_submit_user_button() throws Exception {
        click(newUserReg3.submitUser);
    }

    @Then("^message \"([^\"]*)\" displays$")
    public void message_displays(String value) {
        //TODO: This method should do something. Alert message?
        wait(2000);
    }


    @And("^clicks \"([^\"]*)\" link$")
    public void clicks_link(String text) throws Throwable {
        Model_CommonFunctions.click_bytext(text + ".");
    }

    /*
    //ENH: outdated? actions/portalFunc/ReadRegistrationData
        //ENH: Call a method to load this data if needed, but should not have a main method
        public static void main(String[] args) throws Exception {
            RegistrationCredentials("CDX_DEV_HELP2", "emptyLastName");
        }


        public static final String Registration_XLSX_FILE_PATH = BasePage.prop.getProperty("externalFilesRead") + "testData.xlsx";
        public static ArrayList<String> config = new ArrayList<>();
        public static ArrayList<String> RegistrationDetails = new ArrayList<>();

        public static ArrayList<String> RegistrationCredentials(String uName, String uNumber) throws Exception {
            RegistrationDetails = new ArrayList<>();
            FileInputStream input_document = new FileInputStream(Registration_XLSX_FILE_PATH);
            XSSFWorkbook xlsx_workbook = new XSSFWorkbook(input_document);
            XSSFSheet xlsx_worksheet = xlsx_workbook.getSheet("IDM_REGISTRATION");
            int rownum = xlsx_worksheet.getLastRowNum();
            int colnum = xlsx_worksheet.getRow(0).getLastCellNum();
            String[][] data = new String[rownum][colnum];

            int ROW_uNumberlocationonExcel = 0;
            int COLUMN_uNumberlocationonExcel = 0;

            for (int i = 0; i < rownum; i++) {
                Row row = xlsx_worksheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            data[i][j] = cell.getStringCellValue();

    //ENH: never used? COL seems hardcoded below which is likely not great.
    //Refactor to use a ExcelFile method
                            if (cell.getStringCellValue().contains(uNumber)) {
                                // print cell row and column
                                // System.out.println("row:"+i+" column:"+j);
                                // System.out.println(cell.getStringCellValue());
                                // RegistrationDetails.add(cell.getStringCellValue());

                                 COLUMN_uNumberlocationonExcel = j;
                            }

                            if (cell.getStringCellValue().contains(uName)) {
                                // System.out.println("row:"+i+" column:"+j);
                                // System.out.println(cell.getStringCellValue());
                                //RegistrationDetails.add(cell.getStringCellValue());
                                ROW_uNumberlocationonExcel = i;
                            }

                        }
                    }
                }
            }

            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][1]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][2]); // [index = column number]
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][3]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][4]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][5]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][6]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][7]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][8]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][9]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][10]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][11]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][12]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][13]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][14]);
            RegistrationDetails.add(data[ROW_uNumberlocationonExcel][15]);

            System.out.println(RegistrationDetails);

            return RegistrationDetails;
        }
     */


    @Then("selects {string} from {string}")
    public void selects_from(String checkOpts, String text) throws Exception {
        if (checkOpts.equalsIgnoreCase("No")) click(locateElementByID("cms-newuser-notusBased"));
    }
}
