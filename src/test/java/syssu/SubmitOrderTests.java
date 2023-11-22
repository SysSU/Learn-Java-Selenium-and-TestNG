package syssu;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.classes.Login;
import data.classes.Product;
import data.classes.Products;
import pageobjects.CartPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import pageobjects.components.HeaderComp;


public class SubmitOrderTests extends BaseTestClass {

  public SubmitOrderTests() {
    super();
  }

  @Test(dataProvider = "getProducts")
  public void submitOrder(Product product) {

    String productName = product.name;
    driver.get("https://rahulshettyacademy.com/client");
    ProductsPage productsPage = new ProductsPage(driver);

    // Get list of products and add product to cart by product name
    productsPage.addProductToCartByName(productName);

    // Validate product added to cart
    HeaderComp headerComp = new HeaderComp(driver);
    CartPage cartPage = headerComp.goToCart();
    Assert.assertTrue(cartPage.isProductAddedByName(productName), "Product not added to cart");
  }

  @BeforeClass
  public void beforeSuite() {
    LoginPage loginPage = new LoginPage(driver);
    Login userLoginDetails = logins.get(0);
    loginPage.login(userLoginDetails.email, userLoginDetails.password);
  }

  @DataProvider
  public static Product[] getProducts() {
    
    Object products = dataUtil.getJsonTestData("products", Products.class);
    Products productsObject = (Products) products;
    List<Product> productsList = productsObject.products;
    return productsList.toArray(new Product[productsList.size()]);
  }

}
