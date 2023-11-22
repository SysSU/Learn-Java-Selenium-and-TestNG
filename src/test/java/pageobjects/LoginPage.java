package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.components.HeaderComp;

public class LoginPage extends Shared {
  private WebDriver driver;
  private HeaderComp headerComp;

  public LoginPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    this.headerComp = new HeaderComp(driver);
  }

  @FindBy(id = "userEmail")
  public WebElement userEmail;

  @FindBy(id = "userPassword")
  public WebElement userPassword;

  @FindBy(id = "login")
  public WebElement loginButton;

  public void login(String email, String password) {
    driver.get("https://rahulshettyacademy.com/client");
    waitForElement(userEmail);
    userEmail.sendKeys(email);
    waitForElement(userPassword);
    userPassword.sendKeys(password);
    waitForElement(loginButton);
    loginButton.click();
    // Check if user logged in
    headerComp.waitForElement(headerComp.signOutButton);

  }

  public Boolean isUserLoggedIn() {
    return driver.findElements(By.xpath(headerComp.signOutSelectorXpathString)).size() > 0;
  }
}
