package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTableLoginPage {


    public WebTableLoginPage () {
        PageFactory.initElements(Driver.getDriver(),this);
    }



    @FindBy (name = "username")
    public WebElement inputUsername;

    @FindBy (name = "password")
    public WebElement inputPassword;

    @FindBy (xpath = "//button[.='Login']")
    public WebElement loginButton;

    /**
     * No parameters
     * When we call this method it will directly login using
     *
     * Username: Test
     * Password: Tester
     */
    public void login (String s) {

        this.inputUsername.sendKeys("Test");
        this.inputPassword.sendKeys("Tester");
        this.loginButton.click();

    }


    /**
     * This method will accept two arguments and login.
     * @param username
     * @param password
     */

    public void login (String username, String password) {

        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.loginButton.click();

    }


    public void loginWithConfig (){

        inputUsername.sendKeys(ConfigurationReader.getProperty("webTable.username"));
        inputUsername.sendKeys(ConfigurationReader.getProperty("webTable.pw"));
        loginButton.click();
    }



}
