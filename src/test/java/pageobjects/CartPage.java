package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class CartPage extends Shared {
  WebDriver driver;
  
  public CartPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  @FindAll({
    @FindBy(css = ".cartSection h3")
  })
  List<WebElement> cartItems;

  // Check if at least one product is added to cart by product name
  public Boolean isProductAddedByName(String productName) {
    return cartItems
    .stream()
    .anyMatch(e -> e.getText().equals(productName));
  }




  
}

