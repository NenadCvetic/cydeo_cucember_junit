package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {


     /*
    This method will accept int (in seconds) and execute Thread.sleep for give duration
     */

    public static void sleep(int second) {
        second *= 1000;

        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }


    public static void switchWindowAndVerify (String expectedInUrl, String expectedInTitle) {

        for (String each : Driver.getDriver().getWindowHandles()) {

            Driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }

        }
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.contains(expectedInTitle));


    }

    /*
    This method accepts a String "expectedTitle" and Asserts if it is true
     */

    public static void verifyTitle (String expectedTitle) {

        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);

    }

    public static void verifyUrlContains (String expectedInUrl){

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInUrl));

    }

    public static void clickAndVerifyRadioButton (String nameAttribute, String idAttribute){

        for (WebElement each : Driver.getDriver().findElements(By.xpath("//input[@name='" + nameAttribute + "']"))) {
            if (each.getAttribute("id").equalsIgnoreCase(idAttribute)){
                each.click();
                break;
            }

        }

    }

    //creating utility method for ExplicitWait, so we don't have to repeat the lines
  public static void waitForInvisibilityOf (WebElement webElement) {

       Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }


    /**
     * This method will accept dropdown as WebElement and return all the options in a List of String
     * @param dropdownElement
     * @return
     */
    public static List<String> dropdownOptionsAsString (WebElement dropdownElement){

        Select select = new Select(dropdownElement);

        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        List <String> actualMonths = new ArrayList<>();

        for (WebElement each : actualOptionsAsWebElement) {

            actualMonths.add(each.getText());

        }

        return actualMonths;
    }


    /**
     * This method will accept group of radio button as list of web elements
     * It will loop through the List, and click to the radio button with provided attributeValue
     * @param radioButtons
     * @param attributeValue
     */
    public static void clickRadioButton ( List <WebElement> radioButtons ,String attributeValue) {

        for (WebElement each : radioButtons) {

            if (each.getAttribute("value").equalsIgnoreCase(attributeValue)){
                each.click();
                break;
            }

        }


    }


}
