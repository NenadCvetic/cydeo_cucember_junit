package com.cydeo.step_definitions;

import com.cydeo.pages.GoogleSearchPage;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.security.Key;

public class GoogleStepDefinitions {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();



    @When("user type {string} and clicks enter")
    public void user_type_and_clicks_enter(String string) {

        googleSearchPage.searchBox.sendKeys(string + Keys.ENTER);

    }
    @Then("user sees {string} in the google title")
    public void user_sees_in_the_google_title(String string) {

        String expectedTitle =  string + " - Google претрага";

        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals("Title is not as expected!",expectedTitle, actualTitle);



    }

    @Then("user sees apple in the google title")
    public void user_sees_apple_in_the_google_title() {

        String expectedTitle = "apple - Google претрага";

        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals("Title is not as expected!",expectedTitle, actualTitle);



    }

    @When("user is on Google search page")
    public void user_is_on_google_search_page() {

        Driver.getDriver().get("https://www.google.com");
    }
    @Then("user should see title is Google")
    public void user_should_see_title_is_google() {

        String expectedTitle = "Google";
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(expectedTitle,actualTitle);


    }


    @When("user type apple and clicks enter")
    public void userTypeAppleAndClicksEnter() {

        googleSearchPage.searchBox.sendKeys("apple" + Keys.ENTER);
    }
}
