package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductsPage extends Shared {
  WebDriver driver;

  public ProductsPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  @FindBy(css = ".card")
  List<WebElement> productCards;

  @FindBy(xpath = ".//button[contains(text(), 'Add To Cart')]")
  List<WebElement> addToCartButtons;

  String addToCartXpath = ".//button[contains(text(), 'Add To Cart')]";
  String productNameCSSSelector = "h5";

  // Check if at least one product is added to cart by product name
  public String addProductToCartByName(String productName) {
    waitForLoading();
    WebElement product = productCards.stream()
        .filter(e -> e.findElement(By.cssSelector(productNameCSSSelector)).getText().equals(productName)).findFirst().get();
    Assert.assertTrue(product.isDisplayed(), "Product '" + productName + "' not displayed");
    clickAddToCartButton(product);
    return product.findElement(By.cssSelector(productNameCSSSelector)).getText();
  }

  public void clickAddToCartButton(WebElement productCard) {
    WebElement addToCartButton = productCard.findElement(By.xpath(addToCartXpath));
    addToCartButton.click();
    waitForLoading();
  }

  public void waitForLoading() {
    
    if(driver.findElements(By.cssSelector(".ng-animating")).size() > 0) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    }
    
  }

}
