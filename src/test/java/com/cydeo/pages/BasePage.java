package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
In this class we will store WebElements that are common to all pages
 */

public class BasePage {

    public BasePage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy (xpath = "//button[.='View all orders']")
    public WebElement viewAllOrders;

    @FindBy (xpath = "//buton[.='View all products']")
    public WebElement viewAllProducts;

    @FindBy (xpath = ("(//button[@class='button nav__item'])[2]"))
    public WebElement order;

    @FindBy (xpath = "//button[.='Logout']")
    public WebElement logoutButton;



}
