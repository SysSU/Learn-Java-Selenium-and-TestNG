package pageobjects.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.CartPage;
import pageobjects.Shared;

public class HeaderComp extends Shared {
  WebDriver driver;

  public HeaderComp(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public String signOutSelectorXpathString = "//*[ contains(text(), 'Sign Out' ) ]";

  @FindBy(xpath = "//*[ contains(text(), 'Sign Out' ) ]")
  public WebElement signOutButton;

  @FindBy(css = "[routerlink*='cart']")
  public WebElement cartLink;

  public CartPage goToCart() {
    cartLink.click();
    return new CartPage(driver);
  }
  
}
