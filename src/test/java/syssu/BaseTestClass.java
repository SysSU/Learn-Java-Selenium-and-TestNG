package syssu;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;


import data.classes.Login;
import data.classes.Logins;
import utils.Data;


// Base test class that is used as the parent of all test classes.
public class BaseTestClass {
  public WebDriver driver;
  public static Data dataUtil = new Data();
  public List<Login> logins;


  // Method will be run before the first test method in the current class is invoked.
  @BeforeClass
  public void initSetup() {
    // Setup driver
    driver = new ChromeDriver();
    // Maximize window
    driver.manage().window().maximize();
    // Delete all cookies
    driver.manage().deleteAllCookies();
    // Set driver implicit wait
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));

    // Set login data from JSON
    Object loginsData = dataUtil.getJsonTestData("logins", Logins.class);
    Logins loginsObject = (Logins) loginsData;
    logins = loginsObject.logins;

  
  }

  // Method will be run after all the test methods in the current class have been run.
  @AfterSuite
  public void tearDown() {
    driver.quit();
    driver = null;
  }


  public void openUrl(String url) {
    driver.get(url);
  }


}
