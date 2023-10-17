package com.model.locators.hd_ManageUsersLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HD_ManageUsers_Locators {


    @FindBy(css = "[id='cms-helpdesk-tile-selected']")
    public WebElement hdManageUsers;

    @FindBy(id = "cms-entSearchTabLi")
    public WebElement enterpriseSearch;

    @FindBy(id = "cms-appSearchTab")
    public WebElement applicationSearch;

    @FindBy(id = "cms-enterprisesearch-uid")
    public WebElement userID_searchBox;

    @FindBy(id = "enterprise_search_button")
    public WebElement searchBtn;

    @FindBy(id = "cms-hd-as-actions-container")
    public WebElement selectDropDown;

    @FindBy(id = "cms-generateTempPw")
    public WebElement justifationField;

    @FindBy(id = "cms-cms-generateTempPwdBtn")
    public WebElement generatePWBtn;

    @FindBy(id = "cms-submit-req")
    public WebElement submitBtn;

    @FindBy(id = "tempPwd")
    public WebElement tempPWText;


}
