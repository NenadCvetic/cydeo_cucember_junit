package com.cydeo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
    Creating a private Constructor, so we are closing access to the
    object of this class from the outside of the class
     */
    private Driver () {

    }

    /*
    We make WebDriver private, because we want to close access from outside the class
    We make it static because we will use it in a static method.
     */

    //private static WebDriver driver;

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a re-usable utility method which will return the same driver instance when we call it
     */

    public static WebDriver getDriver () {

        if (driverPool.get() == null){

            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "remote-chrome":

                    try {

                        String gridAddress = "3.95.222.3";

                        URL url = new URL( "http://" + "3.95.222.3" + ":4444/wd/hub");

                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

                        desiredCapabilities.setBrowserName("chrome");

                        driverPool.set(new RemoteWebDriver(url,desiredCapabilities));


                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }

        }

        return driverPool.get();

    }

    /*
    This method will make sure our driver value is always null after using quit () method
     */

    public static void closeDriver () {

        if (driverPool.get() != null){
            driverPool.get().quit(); // this line will terminate the existing session. Value will not even be null
            driverPool.remove();
        }


    }


}
